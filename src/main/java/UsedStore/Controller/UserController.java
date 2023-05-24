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


    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpSession session, @RequestBody HashMap<String,Object> map) throws Exception{

        UserVO vo = userService.login(map);
        HashMap<String,String> responseData = new HashMap<>();
        if(vo == null){
            responseData.put("status","500");
            responseData.put("message","아이디 혹은 비밀번호를 확인해주세요");
        }
        else {
            responseData.put("status","200");
            responseData.put("message","로그인 성공하였습니다!");
        }
        String loginResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(loginResult);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(HttpSession session, @RequestBody HashMap<String,Object> map ) throws Exception{
        
        System.out.println(map);
        int result = userService.insertUser(map);
        HashMap<String,String> responseData = new HashMap<>();

        if(result == 1) {
            responseData.put("status", "200");
            responseData.put("message", "회원가입에 성공하였습니다!");
        }
        else {
            responseData.put("status","500");
            responseData.put("message","회원가입에 실패하였습니다!");
        }
        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<Object> checkEmail (HttpSession session,@RequestBody HashMap<String,Object> map) throws Exception{

        UserVO vo = userService.checkEmail(map);

        HashMap<String,String> responseData = new HashMap<>();
        // 데이터가 담겨있다면.
        if(vo == null){
            responseData.put("status","200");
            responseData.put("message","사용 가능한 이메일 입니다!");
        }
        else {
            responseData.put("status","500");
            responseData.put("message","이미 가입된 이메일이 있습니다.");
        }
        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);
    }

    @PostMapping("/checkNickName")
    public ResponseEntity<Object> checkNickName (HttpSession session, @RequestBody HashMap<String,Object> map ) throws Exception{

        UserVO vo = userService.checkNickName(map);
        HashMap<String,String> responseData = new HashMap<>();

        // vo에 아무것도 담겨서 오지않는다면 중복된값이 없는것이다.
        if(vo == null){
            responseData.put("status","200");
            responseData.put("message","사용 가능한 닉네임 입니다!");
        }
        else {
            responseData.put("status","500");
            responseData.put("message","이미 사용중인 닉네임 입니다!");
        }
        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);
    }


}