package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;

@Service
@Transactional
public class UploadPathServiceImp implements UploadPathService {

    @Autowired
    ServletContext servletContext;

    @Override
    public File getFilePath(String modifiedFileName, String path) {
       boolean exists = new File(servletContext.getRealPath("/"+path+"/")).exists();
       if(!exists){
           new File(servletContext.getRealPath("/"+path+"/")).mkdir();
       }
       String modifiedFilePath = servletContext.getRealPath("/"+path+"/"+File.separator+modifiedFileName);
       File file = new File(modifiedFilePath);
       return file;
    }
}
