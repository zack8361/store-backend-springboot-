package UsedStore.Controller;

import UsedStore.Service.ItemService;
import UsedStore.Vo.ItemVO;
import UsedStore.Vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ItemService itemService;

    @GetMapping("/api")
    public List<ItemVO> test(HttpSession session) throws Exception{
        System.out.println("하이");

        List<ItemVO> list = itemService.showMain();
        System.out.println(list);
        return list;
    }
    
}
