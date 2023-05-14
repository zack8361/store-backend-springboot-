package UsedStore.Controller;
import UsedStore.Dao.UserDao;
import UsedStore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity login(){
        userService.insertTest();
        System.out.println("왔냐?");
        return ResponseEntity.ok().build();
    }
}