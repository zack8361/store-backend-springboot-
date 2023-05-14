package UsedStore.Vo;

import lombok.Getter;
import lombok.Setter;

// getter, setter 메서드 생략
@Getter
@Setter
public class UserVO {
    private String userId;
    private String userName;
    private String userBirthday;
    private int userSex;
    private String userPhone;
    private String userEmail;
    private String userPw;
    private String userNickname;
}