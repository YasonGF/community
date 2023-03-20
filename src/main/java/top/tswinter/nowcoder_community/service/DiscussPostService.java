package top.tswinter.nowcoder_community.service;

import top.tswinter.nowcoder_community.entity.DiscussPost;

import java.util.List;

public interface DiscussPostService {

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit);

    public int findDiscussPostRows(int userId);


    

}
