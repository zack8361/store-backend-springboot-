package UsedStore.Service;


import UsedStore.Dao.ItemDao;
import UsedStore.Vo.ItemVO;
import UsedStore.Vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    public int insertSale(HashMap<String,Object>map){
        return itemDao.insertSale(map);
    }

    public List<ItemVO> showMain(){
        return itemDao.showMain();
    }
}
