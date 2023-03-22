package top.tswinter.nowcoder_community.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class CommunityUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String MD5(String salt) {
        if(!StringUtils.hasText(salt))  return null;
        return DigestUtils.md5DigestAsHex(salt.getBytes());
    }
}
