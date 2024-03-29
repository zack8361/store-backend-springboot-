package UsedStore.Service;


import UsedStore.Dao.ChatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatDao chatDao;
    public int insertList(HashMap<String, Object> map) {
        return chatDao.insertList(map);
    }
    public List<HashMap<String, Object>> getChatReq(HashMap<String, Object> map) {
        return chatDao.getChatReq(map);
    }

}
