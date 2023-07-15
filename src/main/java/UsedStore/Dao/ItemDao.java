package UsedStore.Dao;


import UsedStore.Vo.ItemVO;
import UsedStore.Vo.WishListVO;
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

    public WishListVO getWishList(HashMap<String,Object>map) {
        return sqlSession.selectOne(namespace +".getWishList",map);
    }

    public int wishListCnt(HashMap<String, Object> map) {
        return sqlSession.selectOne(namespace+".wishListCnt",map);
    }

    public int insertWishList(HashMap<String, Object> map) {
        return sqlSession.insert(namespace+".wishListInsert",map);
    }

    public List<WishListVO> allwishlist(HashMap<String, Object> map) {
        return sqlSession.selectList(namespace+".allwishlist",map);
    }
    public List<ItemVO> myPageWish(HashMap<String, Object> map) {
        return sqlSession.selectList(namespace+".myPageWish",map);
    }
}
