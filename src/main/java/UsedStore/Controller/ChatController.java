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
    public ChatVO handleMessage(@Payload ChatVO chatVO){

        System.out.println("여기왓나?");
        System.out.println(chatVO.getUserId());
        System.out.println(chatVO.getMessage());
        return chatVO;
    }
}
