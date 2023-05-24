package UsedStore.Service;


import UsedStore.Dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    public int insertSale(HashMap<String,Object>map){
        return itemDao.insertSale(map);
    }
}
