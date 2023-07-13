package com.trabalho.springmvc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.trabalho.springmvc.dao.ContatoDAO;
import com.trabalho.springmvc.form.AtualizarContatoForm;
import com.trabalho.springmvc.form.ContatoForm;
import com.trabalho.springmvc.form.DeleteContatoForm;
import com.trabalho.springmvc.model.Contato;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.utils.FileUtils;

@Service
public class ContatoService {
    @Autowired
    private ContatoDAO contatoDAO;

	private static final String PATH = "fotos_contato"+File.separator;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public ContatoService(){
        this.contatoDAO = new ContatoDAO();
    }

	@Transactional(rollbackFor = {Exception.class})
	public void salvar(ContatoForm cform, HttpServletRequest request, BindingResult bindingResult) {
		try{
			FileUtils.validateFoto(cform.getFoto(), bindingResult);

			if(cform.getNome() == null || cform.getNome().equals("")){
				bindingResult.rejectValue("nome", null, "nome invalido");
			}
			if(cform.getTelefone() == null || cform.getTelefone().equals("")){
				bindingResult.rejectValue("telefone", null, "telefone invalido");
			}

			if(!bindingResult.hasErrors()){
				String fileName = null;

				if(!cform.getFoto().isEmpty())
					fileName = FileUtils.saveFile(cform.getFoto(), request.getServletContext().getRealPath("")+PATH);

				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				Contato contato = new Contato();
				contato.setNome(cform.getNome());
				contato.setTelefone(cform.getTelefone());
				contato.setFoto(fileName);
				contato.setUsuario(usuario);
				this.contatoDAO.salvar(contato);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Transactional
	public void atualizar(AtualizarContatoForm cform, HttpServletRequest request, BindingResult bindingResult) {
		try{
			FileUtils.validateFoto(cform.getFoto(), bindingResult);
			
			if(!pattern.matcher(cform.getId()).matches()){
				bindingResult.rejectValue("id", null, "valor invalido");
			}
			if(cform.getNome() == null || cform.getNome().equals("")){
				bindingResult.rejectValue("nome", null, "nome invalido");
			}
			if(cform.getTelefone() == null || cform.getTelefone().equals("")){
				bindingResult.rejectValue("telefone", null, "telefone invalido");
			}
			
			if(!bindingResult.hasErrors()){
				Optional<Contato> contato = this.findById(Long.valueOf(cform.getId()));

				if(!contato.isPresent()){
					bindingResult.rejectValue("id", null, "contato não encontrado");
					return;
				}

				if(!cform.getFoto().isEmpty()){
					FileUtils.apagarArquivo(request, PATH + contato.get().getFoto());
					String fileName = FileUtils.saveFile(cform.getFoto(), request.getServletContext().getRealPath("")+PATH);
					contato.get().setFoto(fileName);
				}
				
				contato.get().setNome(cform.getNome());
				contato.get().setTelefone(cform.getTelefone());
				this.contatoDAO.salvar(contato.get());
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    public Optional<Contato> findById(Long id) {
		return this.contatoDAO.findById(id);
	}

    public List<Contato> findAllByUser(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		return this.contatoDAO.findAllByUser(usuario.getId());
	}

	@Transactional
	public void deletar(HttpServletRequest request, HttpServletResponse response, DeleteContatoForm deleteContatoForm, BindingResult bindingResult) {
		if(!pattern.matcher(deleteContatoForm.getId()).matches()){
			bindingResult.rejectValue("id", null, "valor invalido");
		}

		if(!bindingResult.hasErrors()){
			Optional<Contato> c = this.findById(Long.valueOf(deleteContatoForm.getId()));

			if(!c.isPresent()){
				bindingResult.rejectValue("id", null, "contato não encontrado");
				return;
			}

			if(c.get().getFoto() != null){
				FileUtils.apagarArquivo(request, PATH + c.get().getFoto());
			}

			this.contatoDAO.deletar(c.get());
		}
	}

	public void viewFoto(HttpServletRequest request, HttpServletResponse response, String fileName){
		FileUtils.viewFile(request, response, PATH, fileName, false);
	}

	public void exportarCSV(HttpServletRequest request, HttpServletResponse response){
		var usuario = (Usuario) request.getSession().getAttribute("usuario");
		List<Contato> contatos = this.contatoDAO.findAllByUser(usuario.getId());
		List<String[]> rows = contatos.stream().map(c -> new String[]{ c.getNome(),c.getTelefone() }).toList();

		try {
			File csvOutputFile = File.createTempFile("contatos.csv",".csv");//cria arquivo temporário pra não deixar salvo no disco
			PrintWriter pw = new PrintWriter(csvOutputFile);

			rows.stream().map(this::convertToCSV).forEach(pw::println);
			pw.close();

			response.setContentType("text/csv");
			response.addHeader("Content-Disposition", "attachment; filename=contatos.csv");
        	response.setContentLength((int)csvOutputFile.length());
		
			FileInputStream fileInputStream = new FileInputStream(csvOutputFile);
			OutputStream outputStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
        	int bytesRead = -1;

			while((bytesRead = fileInputStream.read(buffer)) != -1){
				outputStream.write(buffer,0,bytesRead);
			}

			fileInputStream.close();
        	outputStream.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private String convertToCSV(String[] data) {
		return Stream.of(data)
		.map(this::escapeSpecialCharacters)
		.collect(Collectors.joining(";"));
	}

	private String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(";") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}
}
