package UsedStore.Controller;

import UsedStore.Service.MyPageService;
import UsedStore.Service.UserService;
import UsedStore.Vo.MyPageVO;
import UsedStore.Vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/")

public class MyPageController {
    @Autowired
    MyPageService mypageService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/myPage")
    public ResponseEntity<Object> getName (HttpSession session, @RequestBody HashMap<String,Object> map ) throws Exception{

        MyPageVO vo = mypageService.getName(map);
        HashMap<String,String> responseData = new HashMap<>();

        // vo에 아무것도 담겨서 오지않는다면 중복된값이 없는것이다.
        if(vo == null){
            responseData.put("status","200");
            responseData.put("message","이름 전송");
        }
        else {
            responseData.put("status","500");
            responseData.put("message","이름 전송 실패");
        }
        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);
    }
}
