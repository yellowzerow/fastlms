package com.zerobase.fastlms.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class MailComponents {

    private final JavaMailSender javaMailSender;

    /*public void sendMailTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("fksdudchlrh@naver.com");
        message.setSubject("안녕하세요. 테스트 메일입니다.");
        message.setText("안녕하세요. 메일 보내기 테스트 중입니다. 행복한 하루 되쇼.");

        javaMailSender.send(message);
    }*/

    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;

        MimeMessagePreparator msg = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(text, true);
        };

        try {
            javaMailSender.send(msg);
            result = true;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }
}
