package UsedStore.Service;
import UsedStore.Dao.ItemDao;
import UsedStore.Vo.ItemVO;

import UsedStore.Vo.WishListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    public int insertSale(HashMap<String,Object>map, List<MultipartFile> imageBytesList){
        return itemDao.insertSale(map);
    }

    public List<ItemVO> showMain(){
        return itemDao.showMain();
    }

    public List<ItemVO> showAll() {
        return itemDao.showAll();
    }

    public WishListVO getWishList(HashMap<String, Object> map) {

        return itemDao.getWishList(map);
    }

    public int wishListCnt(HashMap<String, Object> map) {
        return itemDao.wishListCnt(map);
    }

    public int insertWishList(HashMap<String, Object> map) {
        return itemDao.insertWishList(map);
    }

    public List<WishListVO> allwishlist(HashMap<String, Object> map) {
        return itemDao.allwishlist(map);
    }
}
