package com.letg.day_cut.test;


import com.letg.day_cut.constant.SystemConstants;
import com.letg.day_cut.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xxxxxxx@qq.com");
        message.setTo("xxxxxxx@qq.com");
        message.setSubject("主题");
        message.setText("文本内容");
        mailSender.send(message);
    }

    @Test
    public void sendComplexMsg() throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //多文件上传
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("xxxxxxx@qq.com");
        helper.setTo("xxxxxxx@qq.com");
        helper.setSubject("面试通知");
        //发送html格式
        helper.setText(parse(getMod(),getParams()),true);

        helper.addAttachment("xxxx公司简章.txt", new File("附件.txt"));
        helper.addAttachment("xxxx公司入职须知.txt", new File("附件.txt"));
        mailSender.send(mimeMessage);

    }

    private String getMod() {
        return "<div>\n" +
                "&nbsp;&nbsp;尊敬的#{username}先生（女士），欢迎您应聘我公司#{postion}职位，您的学识、经历给我们留下了良好的印象。为了彼此进一步的了解，特邀请你在#{joinTime}参加面试。收到请回复，谢谢！\n" +
                "电话：#{compTel},公司地址：#{compAddr}，收到请回复，谢谢！\n" +
                "</div>\n" +
                "<br>\n" +
                "<div style=\"float:right\">\n" +
                "\t<div>#{dept}</div>\n" +
                "\t<div>#{sendTime}</div>\n" +
                "</div>";
    }

    private String parse(String mod, Map<String, String> params) {
        int length = mod.length();
        int left = 0;
        while ((left = mod.indexOf("#")) != -1 && left < length) {
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


    public Map<String, String> getParams() {
        HashMap<String, String> map = new HashMap<>();
        map.put("projectName", "DayCut");
        map.put("loginIp", "192.168.1.1");
        map.put("verifyCode", "W65891");
        map.put("compTel", "17681384613");
        map.put("username", "niggersong");
        map.put("dept", "XX科技");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        map.put("sendTime", sdf.format(new Date()));
        return map;
    }


    @Autowired
    private MailUtil mailUtil;

    @Test
    public void testMailUtil() throws MessagingException {
        String subject = "忘记密码";
        String to = "512038254@qq.com";
        List<File> fileList = new ArrayList<>();

        mailUtil.sendHtmlMsg(subject, SystemConstants.getMailMod("forgetAcount"),getParams(),to,fileList);
    }
}
