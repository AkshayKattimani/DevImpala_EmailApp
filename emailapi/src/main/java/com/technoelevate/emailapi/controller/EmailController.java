package com.technoelevate.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.technoelevate.emailapi.entities.EmailRequest;
import com.technoelevate.emailapi.entities.EmailResponse;
import com.technoelevate.emailapi.services.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	@Autowired
	private EmailService emailService;

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to email api";
	}

//	api to send email
	@PostMapping("/mail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
		System.out.println(request);
		boolean emailStatus = this.emailService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
		if(emailStatus) {
			return ResponseEntity.ok(new EmailResponse("Email sent succesfully..."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Error, Please try again..."));
		}
	}
}
