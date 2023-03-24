package top.tswinter.nowcoder_community.mapper;

import org.apache.ibatis.annotations.*;
import top.tswinter.nowcoder_community.entity.LoginTicket;

@Mapper
public interface LoginTicketMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into `login_ticket`(`user_id`, `ticket`, `status`, `expired`) " +
            "values(#{userId}, #{ticket}, #{status}, #{expired})")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select("select `id`, `user_id`, `ticket`, `status`, `expired` from `login_ticket` where `ticket` = #{ticket}")
    LoginTicket selectByTicket(String ticket);

    @Update("update `login_ticket` set `status` = #{status} where `ticket` = #{ticket}")
    int updateStatus(String ticket, int status);
}
