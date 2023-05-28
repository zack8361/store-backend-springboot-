package UsedStore.Service;


import UsedStore.Dao.MyPageDao;
import UsedStore.Dao.UserDao;
import UsedStore.Vo.MyPageVO;
import UsedStore.Vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class MyPageService {

    @Autowired
    private MyPageDao myPageDao;

    // 이름 정보 가져오기
    public MyPageVO getName(HashMap<String, Object> map){
        return myPageDao.getName(map);
    }

}
