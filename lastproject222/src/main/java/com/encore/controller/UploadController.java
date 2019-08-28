package com.encore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public List<MultipartFile> upload(MultipartFile[] files) {
		return null;
  
        // PROCESS...
  
    }



}
