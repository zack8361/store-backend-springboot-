package UsedStore.Dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ChatDao {

    @Autowired
    SqlSession sqlSession;

    private static String namespace = "mapper.chatMapper";



    public int insertList(HashMap<String, Object> map) {
        return sqlSession.insert(namespace+ ".chatList",map);
    }

    public List<HashMap<String, Object>> getChatReq(HashMap<String, Object> map) {
        return sqlSession.selectList(namespace + ".getChatReq", map);
    }
}
