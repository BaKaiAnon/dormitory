package jmu.zzx.dormitory.utils;


import jmu.zzx.dormitory.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class SimpleMail {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String to, String subject, String content, String name) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject(subject +"————"+ name);
        mail.setText(content);
        mail.setFrom("779213268@qq.com");
        mail.setTo(to);
        mailSender.send(mail);
    }
}