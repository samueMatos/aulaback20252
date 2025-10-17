package com.senac.aulafull.infra.external;

import com.senac.aulafull.domain.interfaces.IEnvioEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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

    }


}
