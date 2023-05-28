package UsedStore.Dao;

import UsedStore.Vo.MyPageVO;
import UsedStore.Vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Repository
@Transactional
public class MyPageDao {
    @Autowired
    SqlSession sqlSession;

    private static String namespace = "mapper.userMapper";

    // 이름 정보 가져오기
    public MyPageVO getName(HashMap<String, Object> map) {
        return sqlSession.selectOne(namespace +".myPage",map);
    }
}
