package com.peng.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class MailClient {

    //因为我们在这个工具类中还需要完成写日志的记录
   private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

     //自动的注入我们在Spring中注入这个类
    @Autowired
    private JavaMailSender mailSender;

    //我们可以直接使用你value注解将配置文件中的数据获取出来  通过key获取value
    @Value("${spring.mail.username}")
    private String form;

    public void sendMail(String to,String subJect,String content){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(form);
            helper.setTo(to);
            helper.setSubject(subJect);
            helper.setText(content,true); //这个位置是指传入进来html数据也能被识别
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
