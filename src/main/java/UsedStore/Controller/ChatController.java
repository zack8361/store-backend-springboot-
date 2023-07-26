package UsedStore.Controller;

import UsedStore.Vo.ChatVO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatController {
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
