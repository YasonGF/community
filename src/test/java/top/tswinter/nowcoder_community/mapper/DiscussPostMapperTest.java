package top.tswinter.nowcoder_community.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.tswinter.nowcoder_community.entity.DiscussPost;

import java.util.List;

@SpringBootTest
public class DiscussPostMapperTest {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void selectDiscussPosts() {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 5);
        discussPosts.forEach(System.out::println);
    }

    @Test
    public void selectDiscussPostRows() {
       int  rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }
}
