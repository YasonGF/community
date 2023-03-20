package top.tswinter.nowcoder_community.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.tswinter.nowcoder_community.Application;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
class MailClientTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    void sendTextMail() {
        mailClient.sendMail("fy981b@qq.com", "Test", "welcome");
        System.out.println("send successful!");
    }

    @Test
    void sendHTMLMail() {
        Context context = new Context();
        context.setVariable("username","赵心怡");

        String content = templateEngine.process("/mail/demo", context);

        System.out.println(content);

        mailClient.sendMail("fy981b@qq.com", "Test html", content);
        System.out.println("send successful!");
    }
}