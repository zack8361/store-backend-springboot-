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
    @GetMapping("/mypage")
    public ResponseEntity<Object> getItem(MyPageVO myPageVO)throws Exception{
        List<MyPageVO> list = myPageService.getPrice();
        String result = objectMapper.writeValueAsString(list);
        System.out.println("하이하이");
        return ResponseEntity.ok(result);
    }
}