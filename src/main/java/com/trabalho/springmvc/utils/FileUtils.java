package com.trabalho.springmvc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static String saveFile(MultipartFile multipartFile, String folder)throws IOException{
        String fileName  = multipartFile.getOriginalFilename();
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
}
