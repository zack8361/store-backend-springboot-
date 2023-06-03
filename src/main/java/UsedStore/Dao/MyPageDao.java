package UsedStore.Dao;

import UsedStore.Vo.MyPageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
@Transactional

public class MyPageDao {
    @Autowired
    SqlSession sqlSession;

    private static String namespace = "mapper.userMapper";

    public List<MyPageVO> getPrice() {
        return sqlSession.selectList(namespace+"getPrice");
    }
}
