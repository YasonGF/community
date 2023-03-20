package top.tswinter.nowcoder_community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;

    private Integer type;
    private Integer status;
    private String activationCode;

    private String headerUrl;

    private Date createTime;

}
