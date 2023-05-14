package UsedStore.Dao;

import UsedStore.Vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Repository
@Transactional


public class UserDao {
    @Autowired
    SqlSession sqlSession;
    private static String namespace = "mapper.userMapper";
    public void insertTest(){
        sqlSession.insert(namespace+".insertTest");
    }

    public UserVO login(HashMap<String, Object> map){
       return sqlSession.selectOne(namespace +".login",map);
    }
}
