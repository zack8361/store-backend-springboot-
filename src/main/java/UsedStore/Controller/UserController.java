package UsedStore.Controller;

import UsedStore.Core.AES128;
import UsedStore.Service.UserService;
import UsedStore.Vo.ItemVO;
import UsedStore.Vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")

public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody HashMap<String, Object> map) throws Exception {
        String res = String.valueOf(map.get("id"));
//        System.out.println(res);
        map.put("id", aes128.decrypt((String) map.get("id")));
        UserVO vo = userService.login(map);

        HashMap<String, String> responseData = new HashMap<>();
        if (vo == null) {
            responseData.put("status", "500");
            responseData.put("message", "아이디 혹은 비밀번호를 확인해주세요");
        } else {
            responseData.put("status", "200");
            responseData.put("message", "로그인 성공하였습니다!");
            responseData.put("userId", res);
        }

        System.out.println(map);
        String loginResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(loginResult);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(HttpSession session, @RequestBody HashMap<String, Object> map) throws Exception {

        map.put("id", aes128.decrypt((String) map.get("id")));
        int result = userService.insertUser(map);
        HashMap<String, String> responseData = new HashMap<>();

        if (result == 1) {
            responseData.put("status", "200");
            responseData.put("message", "회원가입에 성공하였습니다!");
        } else {
            responseData.put("status", "500");
            responseData.put("message", "회원가입에 실패하였습니다!");
        }
        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);

    }

    @PostMapping("/checkEmail")
    public ResponseEntity<Object> checkEmail(HttpSession session, @RequestBody HashMap<String, Object> map) throws Exception {

        UserVO vo = userService.checkEmail(map);

        HashMap<String, String> responseData = new HashMap<>();
        // 데이터가 담겨있다면.
        if (vo == null) {
            responseData.put("status", "200");
            responseData.put("message", "사용 가능한 이메일 입니다!");
        } else {
            responseData.put("status", "500");
            responseData.put("message", "이미 가입된 이메일이 있습니다.");
        }
        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);
    }

    @PostMapping("/checkNickName")
    public ResponseEntity<Object> checkNickName(HttpSession session, @RequestBody HashMap<String, Object> map) throws Exception {

        UserVO vo = userService.checkNickName(map);
        HashMap<String, String> responseData = new HashMap<>();

        // vo에 아무것도 담겨서 오지않는다면 중복된값이 없는것이다.
        if (vo == null) {
            responseData.put("status", "200");
            responseData.put("message", "사용 가능한 닉네임 입니다!");
        } else {
            responseData.put("status", "500");
            responseData.put("message", "이미 사용중인 닉네임 입니다!");
        }
        String registerResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(registerResult);
    }

    @GetMapping("/user/userInfo")
    public ResponseEntity<Object> getUser(@RequestParam HashMap<String, Object> map) throws Exception {
        map.put("userId", aes128.decrypt((String) map.get("userId")));
        List<HashMap<String, Object>> result = userService.getUser(map);
        String Result = objectMapper.writeValueAsString(result);
        return ResponseEntity.ok(Result);
    }

    //판매중 보여주기
    @GetMapping("/item")
    public ResponseEntity<Object> getItem(@RequestParam HashMap<String, Object> map) throws Exception {
        System.out.println(map);
        map.put("userId", aes128.decrypt(map.get("userId").toString()));
        System.out.println(map);
        List<HashMap<String, Object>> result = userService.getPrice(map);
        String Result = objectMapper.writeValueAsString(result);

        return ResponseEntity.ok(Result);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> getUpdate(@RequestBody HashMap<String, Object> map) throws Exception {
//        System.out.println(map);
        map.put("id", aes128.decrypt((String) map.get("id")));
        HashMap<String, String> responseData = new HashMap<>();
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo = (HashMap<String, String>) map.get("userInfo");
        for (Map.Entry<String, String> entry : userInfo.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            responseData.put(key, value.toString());
        }
        responseData.put("id", (String) map.get("id"));

        System.out.println(responseData);

        int result = userService.update(responseData);


        if (result == 1) {
            responseData.put("status", "200");
            responseData.put("message", "수정 되었습니다!");
        } else {
            responseData.put("status", "500");
            responseData.put("message", "수정 실패 하였습니다!");
        }
        String updateResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(updateResult);
    }


    @Value("${profile.upload.path}")
    private String imagePath;

    @PostMapping("/user/profile")
    public ResponseEntity<Object> updateUserProfile(@RequestParam HashMap<String, Object> map,
                                                    @RequestPart("images") List<MultipartFile> images) throws Exception {
        String profileImageFilePath = "";

        // 업로드된 이미지 처리
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String filename = StringUtils.cleanPath(image.getOriginalFilename());
                Path destination = Paths.get(imagePath, filename);
                Files.copy(image.getInputStream(), destination.toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING);

                profileImageFilePath = destination.toString();
                System.out.println("프로필 이미지 경로: " + profileImageFilePath);
            }
        }

        // 프로필 사진 업데이트
        map.put("id", aes128.decrypt((String) map.get("id")));
        map.put("profileImg", profileImageFilePath);
        int result = userService.updateProfileImage(map);

        // 응답 데이터 생성
        HashMap<String, String> responseData = new HashMap<>();

        if (result == 1) {
            responseData.put("status", "200");
            responseData.put("message", "프로필 사진 변경 성공!");
        } else {
            responseData.put("status", "500");
            responseData.put("message", "프로필 사진 변경 실패");
        }

        String updateProfileResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(updateProfileResult);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Object> deleteUser(@RequestBody HashMap<String, Object> map) throws Exception {
        map.put("id", aes128.decrypt((String) map.get("id")));
        int result = userService.deleteUser(map);
        HashMap<String, String> responseData = new HashMap<>();
        String deleteResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(deleteResult);
    }
}