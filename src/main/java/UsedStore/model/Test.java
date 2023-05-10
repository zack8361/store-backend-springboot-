package UsedStore.model;

import lombok.Data;

// 데이터 담을 클래스
@Data
public class Test {
    private String user_id;
    private String user_name;
    private String user_birthday;
    private int user_sex;
    private String user_phone;
    private String user_email;
    private String user_pw;
    private String user_nickname;

    // getter, setter 메서드 생략
}