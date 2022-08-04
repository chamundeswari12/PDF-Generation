package com.example.demo;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.lowagie.text.DocumentException;

@Controller
public class UserController {
 
    @Autowired
    private UserServices service;
    @Autowired
    UserRepo userRepository;
    
    
    @PostMapping("/addingUser")
    public ResponseEntity<?> addingusers(@RequestBody User user)
    {
    	
    	userRepository.save(user);
    	return new ResponseEntity<>(HttpStatus.OK);
    	
    }
         
//    @GetMapping("/users/export/pdf")
//    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//         
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//         
//        List<User> listUsers = service.listAll();
//         
//        UserPDFExporter exporter = new UserPDFExporter(listUsers);
//        exporter.export(response);
//         
//    }
    
    
    
    @GetMapping("/users/export/pdf")
    public void export(HttpServletResponse respose) throws DocumentException, IOException
    {
    	
    	
    	//respose.setContentType("application/pdf");
    	  DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
    	String HeaderKey="Content-Disposition";
    	String HeaderValue="attachment;filename=listOfEmps"+currentDateTime+".pdf";
    	respose.setHeader(HeaderKey, HeaderValue);
    	List<User> listtt=service.listAll();
    	UserPdfGeneration export=new UserPdfGeneration(listtt);
    	export.exportPdf(respose);
    	
    	
    	

    	
    	
    }
    
}
