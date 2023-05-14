package UsedStore.Controller;
import UsedStore.Dao.UserDao;
import UsedStore.Service.UserService;
import UsedStore.Vo.UserVO;
import com.google.protobuf.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody HashMap<String, Object> map) throws Exception{

        UserVO loginData = userService.login(map);
        if(loginData == null){
            System.out.println("없는 아이디야");
            return ResponseEntity.status(500).body("아이디 혹은 비밀번호를 확인해주세요");
        }
        System.out.println(loginData.getUserEmail());
        return ResponseEntity.ok("성공했다");
    }
}