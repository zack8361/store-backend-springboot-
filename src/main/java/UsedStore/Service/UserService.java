package UsedStore.Service;

import UsedStore.Dao.UserDao;
import UsedStore.Vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

//TestMapper 인터페이스 받아 데이터 조회하는 클래스

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;

    // 회원가입
    public int insertUser(HashMap<String, Object> map){
        return userDao.insertUser(map);
    }

    // 로그인
    public UserVO login(HashMap<String, Object> map){
        return userDao.login(map);
    }

    // 이메일 중복처리
    public UserVO checkEmail(HashMap<String,Object> map){
        return userDao.checkEmail(map);
    }

    // 닉네임 중복처리.
    public UserVO checkNickName(HashMap<String,Object>map){
        return userDao.checkNickName(map);
    }

    // 유저 정보 get.

    public UserVO getUser(String userId) {
        List<UserVO> users = userDao.getUser(userId);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }


}