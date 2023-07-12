package com.trabalho.springmvc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.dao.ContatoDAO;
import com.trabalho.springmvc.form.ContatoForm;
import com.trabalho.springmvc.model.Contato;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.utils.FileUtils;

@Service
public class ContatoService {
    @Autowired
    private ContatoDAO contatoDAO;

	private static final String PATH = "fotos_contato"+File.separator;

	public ContatoService(){
        this.contatoDAO = new ContatoDAO();
    }

	@Transactional(rollbackFor = {Exception.class})
	public void salvar(ContatoForm cform, HttpServletRequest request) {
		try{
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
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Transactional
	public void atualizar(ContatoForm cform, HttpServletRequest request) {
		try{
			Contato contato = this.findById(cform.getId()).orElseThrow();

			if(!cform.getFoto().isEmpty()){
				FileUtils.apagarArquivo(request, PATH + contato.getFoto());
				String fileName = FileUtils.saveFile(cform.getFoto(), request.getServletContext().getRealPath("")+PATH);
				contato.setFoto(fileName);
			}
			
			contato.setNome(cform.getNome());
			contato.setTelefone(cform.getTelefone());
			this.contatoDAO.salvar(contato);
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
	public void deletar(HttpServletRequest request, HttpServletResponse response, String id) {
		Contato c = this.findById(Long.valueOf(id)).orElseThrow();

		if(c.getFoto() != null){
			FileUtils.apagarArquivo(request, PATH + c.getFoto());
		}

		this.contatoDAO.deletar(c);
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
