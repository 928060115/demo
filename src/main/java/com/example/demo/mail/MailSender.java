package com.example.demo.mail;

import com.example.demo.entity.MailEntity;
import com.example.demo.utils.MailContentTypeEnum;
import com.example.demo.utils.PropertiesUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.List;
import java.util.Properties;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.mail
 * @Date: 2017/11/30
 * @Time: 14:08
 */
public class MailSender {
    //邮件实体
    private MailEntity mail = new MailEntity();

    /**
     * @Author: rogue
     * @Description: 设置邮件标题
     * @ClassName: MailSender
     * @Date: 2017/11/30
     * @Time: 14:09
     */
    public MailSender title(String title){
        mail.setTitle(title);
        return this;
    }

    /**
     * @Author: rogue
     * @Description: 设置邮件内容
     * @ClassName: MailSender
     * @Date: 2017/11/30
     * @Time: 14:10
     */
    public MailSender content(String content){
        mail.setContent(content);
        return this;
    }
    /**
     * @Author: rogue
     * @Description: 设置内容格式
     * @ClassName: MailSender
     * @Date: 2017/11/30
     * @Time: 14:17
     */
    public MailSender contentType(MailContentTypeEnum typeEnum){
        mail.setContentType(typeEnum.getValue());
        return this;
    }
    /**
     * @Author: rogue
     * @Description: 设置请求目标邮件地址
     * @ClassName: MailSender
     * @Date: 2017/11/30
     * @Time: 14:31
     */
    public MailSender targets(List<String> targets){
        mail.setList(targets);
        return this;
    }
    /**
     * @Author: rogue
     * @Description: 执行发送邮件
     * @ClassName: MailSender
     * @Date: 2017/11/30
     * @Time: 14:32
     */
    public void send() throws Exception {
        //默认使用html内容发送邮件
        if (mail.getContentType() == null){
            mail.setContentType(MailContentTypeEnum.HTML.getValue());
        }
        //设置邮件标题
        if (mail.getTitle() == null || mail.getTitle().trim().length() == 0){
            throw new Exception("邮件没有设置标题");
        }
        //设置邮件内容
        if (mail.getContent() == null || mail.getContent().trim().length() == 0){
            throw new Exception("邮件没有填写内容");
        }
        //添加邮件收件人
        if (mail.getList().size() == 0){
            throw new Exception("邮件没有添加收件人");
        }
        //读取mail.properties文件，获取邮件发送配置信息
        final PropertiesUtil propertiesUtil = new PropertiesUtil("mail");
        //创建Properties类用于记录邮箱的一些属性
        final Properties properties = new Properties();
        //表示SMTP发送邮件，必须进行身份认证
        properties.put("mail.smtp.auth","true");
        //设置smtp服务器地址及端口号
        properties.put("mail.smtp.host",propertiesUtil.getValue("mail.smtp.service"));
        properties.put("mail.smtp.port",propertiesUtil.getValue("mail.smtp.port"));
        //设置发送邮箱账户及密码
        properties.put("mail.user",propertiesUtil.getValue("mail.from.address"));
        properties.put("mail.password",propertiesUtil.getValue("mail.from.smtp.pwd"));
        properties.put("mail.nickname",propertiesUtil.getValue("mail.from.nickname"));

        String username = properties.getProperty("mail.user");
        String password = properties.getProperty("mail.password");
        //构建授权信息，用于进行SMTP进行身份认证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        };
        //使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getDefaultInstance(properties,authenticator);
        //创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        //设置发件人
        String nickName = MimeUtility.encodeText(properties.getProperty("mail.nickname"));
        InternetAddress from = new InternetAddress(nickName + "<" + username + ">");
        message.setFrom(from);

        //设置邮件标题
        message.setSubject(mail.getTitle());
        //html发送邮件
        if (mail.getContentType().equals(MailContentTypeEnum.HTML.getValue())){
            //设置邮件的内容体
            message.setContent(mail.getContent(),mail.getContentType());
        }else if (mail.getContentType().equals(MailContentTypeEnum.TEXT.getValue())){
            //文本发送邮件
            message.setText(mail.getContent());
        }

        //邮件收件人地址
        List<String> targets = mail.getList();
        for (int i = 0;i<targets.size();i++){
            try {
                //设置收件人邮箱
                InternetAddress to = new InternetAddress(targets.get(i));
                message.setRecipient(Message.RecipientType.TO,to);
                //发送邮件
                Transport.send(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
