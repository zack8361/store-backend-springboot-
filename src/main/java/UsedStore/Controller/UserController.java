package UsedStore.Controller;
import UsedStore.Service.UserService;
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
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/register")
    public ResponseEntity<Object> register(HttpSession session, @RequestBody HashMap<String,Object> map ) throws Exception{
        System.out.println(map);
        userService.insertUser(map);
        HashMap<String,String> responseData = new HashMap<>();
        responseData.put("status","200");
        responseData.put("message","회원가입에 성공하였습니다!");


        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);
    }
}