package com.senac.aulafull.infra.external;

import com.senac.aulafull.domain.interfaces.IEnvioEmail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;

@Component
public class EnvioEmailRepository implements IEnvioEmail{

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void enviarEmailSimples(String para, String assunto, String texto) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nao-responda@meusite.com");
            message.setTo(para);
            message.setSubject(assunto);
            message.setText(texto);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException("Erro envio de email!");
        }

    }

    @Async
    public void enviarEmailComTemplate(String para, String assunto, String texto) {

        try {

            MimeMessage mensagem = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");
            String htmlTemplate = carregarTemplateEmail();

            String htmlFinal = htmlTemplate
                    .replace("${mensagem}",texto)
                    .replace("${dataEnvio}",String.valueOf(LocalDateTime.now()));

            helper.setFrom("nao-responda@suaempres.com");
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(htmlFinal,true);

            javaMailSender.send(mensagem);


        }catch (Exception e){
            throw new RuntimeException("Erro ao enviar email!");
        }

    }


    private String carregarTemplateEmail() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/email-template.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return  new String(bytes, StandardCharsets.UTF_8);
    }


}
