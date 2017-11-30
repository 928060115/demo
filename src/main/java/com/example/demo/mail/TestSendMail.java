package com.example.demo.mail;

import com.example.demo.utils.MailContentTypeEnum;

import java.util.ArrayList;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.mail
 * @Date: 2017/11/30
 * @Time: 15:17
 */
public class TestSendMail {
    public static void main(String[] args) {
        try {
            new MailSender()
                    .title("springboot集成javaMail")
                    .content("springboot集成javaMail")
                    .contentType(MailContentTypeEnum.HTML)
                    .targets(new ArrayList<String>() {
                        {
                            add("ly_911004@163.com");
                        }
                    }).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
