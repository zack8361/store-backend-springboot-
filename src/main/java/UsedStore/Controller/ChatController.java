package UsedStore.Controller;

import UsedStore.Core.AES128;
import UsedStore.Service.ChatService;
import UsedStore.Vo.ChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    AES128 aes128;

    @Autowired
    ChatService chatService;
    @PostMapping("/chat/request")
    public ResponseEntity<Object> chatRequest(@RequestParam HashMap<String ,Object> map) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        map.put("userID", aes128.decrypt((String) map.get("userID")));
        int result = chatService.insertList(map);

        System.out.println("result = " + result);
        return ResponseEntity.ok(200);
    }




    @MessageMapping("/message/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatVO handleMessage(@Payload ChatVO chatVO, @DestinationVariable String roomId) {
        System.out.println("메시지가 도착했습니다");
        System.out.println(chatVO.getUserId());
        System.out.println(chatVO.getMessage());
        return chatVO;
    }

    @PostMapping("/chat/room")
    @ResponseBody
    public Map<String, String> createOrJoinRoom(@RequestBody Map<String, String> requestBody) {
        String sellerId = requestBody.get("sellerId"); // 판매자 아이디
        String buyerId = requestBody.get("buyerId"); // 구매자 아이디

        String roomId = sellerId + "_" + buyerId;
        System.out.println(roomId);
        
        Map<String, String> response = new HashMap<>();
        response.put("roomId", roomId);
        return response;
    }
}
