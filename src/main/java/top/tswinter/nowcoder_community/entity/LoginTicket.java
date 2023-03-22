package top.tswinter.nowcoder_community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.relational.core.sql.UpdateBuilder;

import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginTicket {
    private Integer id;
    private Integer userId;
    private String ticket;
    private Integer status;
    private Date expired;
}
