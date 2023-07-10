package com.trabalho.springmvc.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    public static String saveFile(MultipartFile multipartFile, String folder)throws IOException{
        String fileName  = multipartFile.getOriginalFilename();
        File pathFile = new File(folder);
        //check if directory exist, if not, create directory
        if(!pathFile.exists()){
            pathFile.mkdir();
        }
 
        pathFile = new File(folder + fileName);

        multipartFile.transferTo(pathFile);

        return folder + fileName;
    }
}
