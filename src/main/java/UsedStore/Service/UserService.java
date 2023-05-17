package UsedStore.Service;

import UsedStore.Dao.UserDao;
import UsedStore.Vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

//TestMapper 인터페이스 받아 데이터 조회하는 클래스

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    public void insertUser(HashMap<String, Object> map){
        userDao.insertUser(map);
    }
    public UserVO login(HashMap<String, Object> map){
        return userDao.login(map);
    }
    
}