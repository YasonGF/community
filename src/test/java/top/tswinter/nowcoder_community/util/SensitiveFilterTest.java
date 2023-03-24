package top.tswinter.nowcoder_community.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SensitiveFilterTest {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void filter() {
        String text="我来测试是否能够过滤吸毒、嫖娼、赌博这些敏感词汇！！！";
        String filter = sensitiveFilter.filter(text);
        System.out.println(filter);
    }

}