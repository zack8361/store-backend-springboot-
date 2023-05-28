package UsedStore.Controller;


import UsedStore.Service.ItemService;
import UsedStore.Vo.ItemVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/showItems")
    public ResponseEntity<Object> test(HttpSession session) throws Exception {
        List<ItemVO> list = itemService.showMain();
        String insetSaleResult = objectMapper.writeValueAsString(list);
        return ResponseEntity.ok(insetSaleResult);
    }
}
