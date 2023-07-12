package com.trabalho.springmvc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static String saveFile(MultipartFile multipartFile, String folder)throws IOException{
        String fileName  =  UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "");
        File pathFile = new File(folder);

        if(!pathFile.exists()){
            pathFile.mkdir();
        }
 
        pathFile = new File(folder + fileName);

        multipartFile.transferTo(pathFile);

        return fileName;
    }

    public static void viewFile(HttpServletRequest request, HttpServletResponse response, String pasta, String fileName, boolean baixar){
		String dataDirectory = request.getServletContext().getRealPath("")+pasta;
    	Path file = Paths.get(dataDirectory, fileName);

		if (Files.exists(file)) {
			String mimeType = request.getServletContext().getMimeType(dataDirectory+fileName);
			response.setContentType(mimeType);
			response.addHeader("Content-Disposition", (!baixar ? "inline; " : "attachment;") +"filename="+fileName);
        	response.setContentLength((int)file.toFile().length());
		
			try{
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

    public static void apagarArquivo(HttpServletRequest request, String path){
        File file = new File(request.getServletContext().getRealPath("")+path);
        file.delete();
    }

	public static void validateFoto(MultipartFile file, Errors errors){
		List<String> mimeTypes = new ArrayList<String>(){{
			add("image/gif");
			add("image/jpeg");
			add("image/svg+xml");
			add("image/bmp");
			add("image/png");
			add("image/webp");
		}};

		if(!file.isEmpty()){
			if(file.getSize() > 10485760){ //10MB
				errors.rejectValue("foto", "", "tamanho do arquivo muito grande");
			}
			if(!mimeTypes.contains(file.getContentType())){
				errors.rejectValue("foto","", "tipo de arquivo inválido");
			}
		}
	}

	public static void validateDocumento(MultipartFile file, Errors errors){
		List<String> mimeTypes = new ArrayList<String>(){{
			add("image/gif");
			add("image/jpeg");
			add("image/svg+xml");
			add("image/bmp");
			add("image/png");
			add("application/pdf");
			add("application/x-sh");
			add("text/css");
			add("text/plain");
			add("text/html");
			add("application/x-sh");
			add("application/java-archive");
			add("text/javascript");
			add("application/json");
			add("application/x-httpd-php");
		}};

		if(file.isEmpty()){
			errors.rejectValue("arquivo", "", "informe o arquivo");
		}
		if(file.getSize() > 10485760){
			errors.rejectValue("arquivo", "", "tamanho do arquivo muito grande");
		}
		if(!mimeTypes.contains(file.getContentType())){
			errors.rejectValue("arquivo","", "tipo de arquivo inválido");
		}
	}
}
