package top.tswinter.nowcoder_community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import top.tswinter.nowcoder_community.entity.DiscussPost;
import top.tswinter.nowcoder_community.mapper.DiscussPostMapper;
import top.tswinter.nowcoder_community.service.DiscussPostService;

import java.util.List;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {
    @Autowired
    @Qualifier(value = "discussPostMapper")
    private DiscussPostMapper discussPostMapper;
    @Override
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    @Override
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
