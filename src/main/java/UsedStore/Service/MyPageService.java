package UsedStore.Service;


import UsedStore.Dao.MyPageDao;
import UsedStore.Dao.UserDao;
import UsedStore.Vo.MyPageVO;
import UsedStore.Vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class MyPageService {

    @Autowired
    private MyPageDao myPageDao;


    public List<MyPageVO> getPrice() {
        return myPageDao.getPrice();
    }
}
