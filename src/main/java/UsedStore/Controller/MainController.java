package UsedStore.Controller;


import UsedStore.Service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/sale")
    public ResponseEntity<Object> insertSale (HttpSession session, @RequestBody HashMap<String,Object> map ) throws Exception{
        // map 으로 찍어보고
        System.out.println(map);
        int result = itemService.insertSale(map);
        HashMap<String,String> responseData = new HashMap<>();

        if(result == 1){
            responseData.put("status","200");
            responseData.put("message","상품등록에 성공하였습니다!");
        }
        else {
            responseData.put("status","500");
            responseData.put("message","상품등록에 실패하였습니다");
        }
        String insetSaleResult = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(insetSaleResult);
    }
}
