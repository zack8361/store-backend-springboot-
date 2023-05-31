package UsedStore.Dao;

import UsedStore.Vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
@Transactional


public class UserDao {
    @Autowired
    SqlSession sqlSession;

    private static String namespace = "mapper.userMapper";

    // 회원가입
    public int insertUser(HashMap<String,Object> map){
        return sqlSession.insert(namespace+".insertUser",map);
    }

    // 로그인
    public UserVO login(HashMap<String, Object> map){
       return sqlSession.selectOne(namespace +".login",map);
    }

    // email 중복처리
    public UserVO checkEmail(HashMap<String,Object> map){
        return sqlSession.selectOne(namespace+".checkEmail",map);
    }

    public UserVO checkNickName(HashMap<String,Object> map){
        return sqlSession.selectOne(namespace+".checkNickName",map);
    }

    public List<HashMap<String, Object>> getUser(HashMap<String, Object> map) {
        return sqlSession.selectList(namespace+".getUser",map);
    }
}
