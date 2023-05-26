package com.letg.day_cut.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * mail工具类
 */
@Component
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String clientMail;


    /**
     * @param subject  主题
     * @param content  内容
     * @param fileList 文件
     * @param to       接收方
     * @throws MessagingException
     */
    private void sendMail(String subject, String content, List<File> fileList, String to,boolean isHtml) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //是否为多文件上传
        boolean mutipart = !CollectionUtils.isEmpty(fileList);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, mutipart);
        helper.setFrom(clientMail);
        helper.setTo(to);
        helper.setSubject(subject);
        //发送html格式
        helper.setText(content, isHtml);

        //附件
        for (File file : fileList) {
            helper.addAttachment(file.getName(), file);
        }

        mailSender.send(mimeMessage);

    }

    /**
     * 发送Html邮件
     * @param subject
     * @param mod
     * @param params
     * @param to
     * @param fileList
     * @throws MessagingException
     */
    public void sendHtmlMsg(String subject, String mod, Map<String, String> params, String to, List<File> fileList) throws MessagingException {
        sendMail(subject, parse(mod, params), fileList, to,true);
    }

    /**
     * 发送简单消息邮件
     * @param subject
     * @param msg
     * @param to
     * @param fileList
     * @throws MessagingException
     */

    public void sendSimpleMsg(String subject, String msg, String to, List<File> fileList) throws MessagingException {
        sendMail(subject,msg,fileList,to,false);
    }

    private String parse(String mod, Map<String, String> params) {
        int length = mod.length();
        int left = 0;
        while ((left = mod.indexOf("$")) != -1 && left < length) {
            for (int right = left; right < length; right++) {
                if (mod.charAt(right) == '}') {
                    String key = mod.substring(left + 2, right);
                    mod = mod.substring(0, left) + params.get(key) + mod.substring(right + 1);
                    length = mod.length();
                    left += key.length() + 1;
                    break;
                }
            }
        }
        return mod;
    }

}
