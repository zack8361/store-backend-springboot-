package UsedStore.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional


public class UserDao {
    @Autowired
    SqlSession sqlSession;
    private static String namespace = "mapper.userMapper";
    public void insertTest(){
        sqlSession.insert(namespace+".insertTest");
    }
}
