package UsedStore.Dao;


import UsedStore.Vo.ItemVO;
import UsedStore.Vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
@Transactional
public class ItemDao {
    @Autowired
    SqlSession sqlSession;
    private static String namespace = "mapper.itemMapper";

    public int insertSale(HashMap<String,Object> map) {
        return sqlSession.insert(namespace+".insertProduct",map);
    }

    public List<ItemVO> showMain(){
        return sqlSession.selectList(namespace +".showMain");
    }

    public List<ItemVO> showAll() {
        return sqlSession.selectList(namespace+".showAll");
    }
}
