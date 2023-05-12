package com.example.SpringEmailDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringEmailDemoApplication {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendSimpleEmail() throws Exception {
		List<String> toEmails = Arrays.asList("edulindorodrigues33@gmail.com", "eduga.rodriguesp@gmail.com", "roserodriguesacejp@gmail.com");
		String subject = "Test email";
		String body = "Olá, este é um teste do disparador de emails.";
		senderService.sendSimpleEmail(toEmails, subject, body);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendEmailWithAttachment() throws Exception {
		List<String> toEmails = Arrays.asList("edulindorodrigues33@gmail.com", "eduga.rodriguesp@gmail.com", "roserodriguesacejp@gmail.com");

		String subject = "Teste de e-mail com anexo";
		String body = "Este é um e-mail de teste com um anexo.";
		String attachmentPath = "C:/Users/eduli/Downloads/Texto TEste.txt";

		senderService.sendEmailWithAttachment(toEmails, subject, body, attachmentPath);
	}

	@EventListener(ApplicationReadyEvent.class)
    public void sendEmailWithHTML() throws Exception {
		List<String> toEmails = Arrays.asList("edulindorodrigues33@gmail.com", "eduga.rodriguesp@gmail.com", "roserodriguesacejp@gmail.com");

		String subject = "Assunto do email";
		String body = "<h1>Conteúdo HTML do email</h1><h2>Conteúdo HTML do email</h2><h3>Conteúdo HTML do email</h3><p>Olá, este é um email com HTML!</p>";
		senderService.sendHtmlEmail(toEmails, subject, body);
	}
}
