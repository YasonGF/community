package top.tswinter.nowcoder_community.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.tswinter.nowcoder_community.entity.LoginTicket;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginTicketMapperTest {

    @Autowired
    private LoginTicketMapper ticketMapper;

    @Test
    void insertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(155);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 60 * 60 * 1000));
        loginTicket.setTicket("abc");
        loginTicket.setStatus(1);
        ticketMapper.insertLoginTicket(loginTicket);
        System.out.println("ok!ok!");
    }

    @Test
    void selectByTicket() {
        LoginTicket loginTicket = ticketMapper.selectByTicket("45629853");
        System.out.println(loginTicket);
    }

    @Test
    void updateStatus() {
        System.out.println("----------------------------------------------------");
        LoginTicket loginTicket = ticketMapper.selectByTicket("45629853");
        System.out.println(loginTicket);
        ticketMapper.updateStatus("45629853", 0);
        System.out.println("====================================================");
        loginTicket = ticketMapper.selectByTicket("45629853");
        System.out.println(loginTicket);
        System.out.println("----------------------------------------------------");

    }
}