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
    public int insertUser(HashMap<String, Object> map) {
        return userDao.insertUser(map);
    }

    // 로그인
    public UserVO login(HashMap<String, Object> map) {
        return userDao.login(map);
    }

    // 이메일 중복처리
    public UserVO checkEmail(HashMap<String, Object> map) {
        return userDao.checkEmail(map);
    }

    // 닉네임 중복처리.
    public UserVO checkNickName(HashMap<String, Object> map) {
        return userDao.checkNickName(map);
    }

    // user 정보 get
    public List<HashMap<String, Object>> getUser(HashMap<String, Object> map) {
        return userDao.getUser(map);
    }

//    public List<HashMap<String, Object>> getPrice(HashMap<String, Object> map) {
//        return UserDao.getPrice(map);
//    }
public List<HashMap<String, Object>> getPrice(HashMap<String, Object> map) {
        return userDao.getPrice(map);
}

//회원정보 수정
    public int update(HashMap<String, String> map) {
        return userDao.update(map);
    }

    public int updateProfileImage(HashMap<String, Object> map) {
        return userDao.updateProfileImage(map);
    }


    public int deleteUser(HashMap<String, Object> map) {
        return userDao.deleteUser(map);
    }
}