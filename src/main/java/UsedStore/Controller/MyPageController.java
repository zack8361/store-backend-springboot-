package UsedStore.Controller;

import UsedStore.Core.AES128;
import UsedStore.Service.MyPageService;
import UsedStore.Service.UserService;
import UsedStore.Vo.ItemVO;
import UsedStore.Vo.MyPageVO;
import UsedStore.Vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/")

public class MyPageController {
    @Autowired
    MyPageService myPageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    //판매중 보여주기
    @GetMapping("/mypage/{userId}")
    public ResponseEntity<Object> getItem(@PathVariable String userId)throws Exception{
        HashMap<String,Object> map = new HashMap<>();
        map.put("userId",aes128.decrypt(userId));
        List<HashMap<String ,Object>> result = myPageService.getPrice(map);
        String Result = objectMapper.writeValueAsString(result);
        System.out.println("하이하이하이하이");
        return ResponseEntity.ok(Result);
    }
}