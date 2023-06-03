package UsedStore.Dao;

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

    private static String namespace = "mapper.myPageMapper";

    public List<HashMap<String, Object>> getPrice(HashMap<String, Object> map) {
        return sqlSession.selectList(namespace+"getPrice", map);
    }
}
