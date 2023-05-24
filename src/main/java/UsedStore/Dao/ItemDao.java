package UsedStore.Dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Repository
@Transactional
public class ItemDao {
    @Autowired
    SqlSession sqlSession;
    private static String namespace = "mapper.itemMapper";

    public int insertSale(HashMap<String,Object> map) {
        return sqlSession.insert(namespace+".insertProduct",map);
    }
}
