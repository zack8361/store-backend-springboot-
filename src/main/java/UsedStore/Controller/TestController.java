package UsedStore.Controller;
import UsedStore.Dao.UserDao;
import UsedStore.Service.UserService;
import UsedStore.Vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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

        UserVO loginData= userService.login(map);
        System.out.println(loginData.getUserEmail());
        return ResponseEntity.ok().build();
    }
}