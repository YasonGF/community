package top.tswinter.nowcoder_community.util;

public interface CommunityConstant {
    int ACTIVATION_SUCCESS=0;
    int ACTIVATION_REPEAT=1;
    int ACTIVATION_FAIL=2;

    // 默认状态下登录凭证的超时时间
    int DEFAULT_EXPIRED_SECONDS = 3600*12;  // 12小时
    int REMEMBER_EXPIRED_SECONDS = DEFAULT_EXPIRED_SECONDS*2;  // 24小时

}
