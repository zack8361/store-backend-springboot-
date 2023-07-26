package UsedStore.Controller;

import UsedStore.Vo.ChatVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/app/message")
    @SendTo("/topic/chat")
    public ChatVO handleMessage(@Payload ChatVO chatVO) {
        System.out.println("메시지가 도착했습니다");
        System.out.println(chatVO.getUserId());
        System.out.println(chatVO.getMessage());

        // 받은 메시지를 그대로 리턴하거나, 필요에 따라 추가 작업 수행 후 리턴
        return chatVO;
    }
}
