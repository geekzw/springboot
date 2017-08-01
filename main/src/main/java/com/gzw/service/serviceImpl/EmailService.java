package com.gzw.service.serviceImpl;

import com.gzw.domain.EmailModel;
import com.gzw.domain.ResultInfo;
import com.gzw.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by gujian on 2017/6/26.
 */
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private EmailModel model;

    @Autowired
    private JavaMailSender mailSender;

    public ResultInfo sendSimpleEmail() {
        return sendSimpleEmail(model.getEmailTitle(),model.getEmailContent());

    }

    public ResultInfo sendSimpleEmail(String title, String content) {
        ResultInfo resultInfo = null;
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(username);//发送者.
        message.setTo(model.getEmailTo());//接收者.
        message.setSubject(title);//邮件主题.
        message.setText(content);//邮件内容.
        try {
            mailSender.send(message);//发送邮件
            resultInfo = ResultInfo.getSuccessInfo(ResultCode.RESULT_SUCCESS);
        } catch (MailException m) {
            resultInfo = ResultInfo.getErrorInfo(0, m.getMessage());
        } finally {
            return resultInfo;
        }

    }

    public ResultInfo sendFileMail() {
        ResultInfo resultInfo = null;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(username);//发送者.
            helper.setTo(model.getEmailTo());//接收者.
            helper.setSubject("测试附件（邮件主题）");//邮件主题.
            helper.setText("这是邮件内容（有附件哦.）");//邮件内容.

            //附件1,获取文件对象.
            FileSystemResource file1 = new FileSystemResource(new File("/Users/gujian/Pictures/5.25日江西理工软件开发2班航拍照片/航拍照片/DJI_0026.JPG"));
            helper.addAttachment("头像1.jpg", file1);
            mailSender.send(mimeMessage);//发送邮件
            resultInfo = ResultInfo.getSuccessInfo(ResultCode.RESULT_SUCCESS);
        } catch (MessagingException e) {
            e.printStackTrace();
            resultInfo = ResultInfo.getErrorInfo(0, e.getMessage());
        } finally {
            return resultInfo;
        }
    }

}
