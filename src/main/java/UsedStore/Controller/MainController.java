package UsedStore.Controller;
import java.nio.file.Paths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import java.util.HashMap;
import java.util.List;




import UsedStore.Core.AES128;
import UsedStore.Service.ItemService;
import UsedStore.Vo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AES128 aes128;



    @Value("${image.upload.path}")
    private String imagePath;

    @PostMapping("/sale")
    public ResponseEntity<Object> insertSale(@RequestPart("images") List<MultipartFile> images,
                                             @RequestPart("saleItemInfo") String saleItemInfoJson) throws Exception {

        String imageFilePath ="";
        // 업로드된 이미지 처리
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String filename = StringUtils.cleanPath(image.getOriginalFilename());
                Path destination = Paths.get(imagePath, filename); // Paths.get 메서드를 사용하여 경로 생성
                Files.copy(image.getInputStream(), destination.toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING); // toAbsolutePath 메서드를 사용하여 절대 경로로 저장


                 imageFilePath = destination.toString();

                System.out.println("Image Path: " + imageFilePath); // 데이터베이스에 저장된 이미지 경로 출력
            }
        }

        // saleItemInfoJson을 원하는 형식으로 변환하여 사용
        HashMap<String, Object> saleItemInfo = objectMapper.readValue(saleItemInfoJson, new TypeReference<HashMap<String, Object>>() {});

        saleItemInfo.put("imagePath",imageFilePath);
        System.out.println("saleItemInfo = " + saleItemInfo);

        // SaleItemInfo 객체에 필요한 데이터와 이미지를 저장하고 DB에 등록하는 로직 실행
        int result = itemService.insertSale(saleItemInfo, images);

        // 응답 데이터 생성
        HashMap<String, String> responseData = new HashMap<>();

        if (result == 1) {
            responseData.put("status", "200");
            responseData.put("message", "상품 등록에 성공하였습니다!");
        } else {
            responseData.put("status", "500");
            responseData.put("message", "상품 등록에 실패하였습니다.");
        }

        String insertSaleResult = objectMapper.writeValueAsString(responseData);

        // 데이터 확인을 위한 출력
        System.out.println("images: " + images);
        System.out.println("saleItemInfo: " + saleItemInfo);

        return ResponseEntity.ok(insertSaleResult);
    }

    // main 에서 보여주는 모든 물품.
    @GetMapping("/showItems")
    public ResponseEntity<Object> test(HttpSession session) throws Exception {
        List<ItemVO> list = itemService.showMain();
        String insetSaleResult = objectMapper.writeValueAsString(list);
        return ResponseEntity.ok(insetSaleResult);
    }

    // all 에서 보여주는 모든 물품.
    @GetMapping("/all")
    public ResponseEntity<Object> all(ItemVO vo) throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@@all 왔니?");
        List<ItemVO> list = itemService.showAll();
        String Result = objectMapper.writeValueAsString(list);
        return ResponseEntity.ok(Result);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<Object> wishList (@RequestParam HashMap<String ,Object> map) throws Exception{
//        들어온 ID 값 복호화 하여 다시 map 에 put
        map.put("userID", aes128.decrypt((String) map.get("userID")));
//        COUNT 뽑는거

        System.out.println("map = " + map);


//        나와 이 아이템 아이디 두개가 연관 되어있는 컬럼이 있는지.
        WishListVO wishList = itemService.getWishList(map);
        HashMap<String,String> responseData = new HashMap<>();
        int result = itemService.wishListCnt(map);

//        만약 DB에 나와 아이디가 있는 컬럼이 없다면?
        if(wishList == null){
            responseData.put("status","500");
            responseData.put("message","데이터가 없음");
            responseData.put("count",String.valueOf(result));
        }
        else {
            responseData.put("status","200");
            responseData.put("message","데이터가 있음");
            responseData.put("count",String.valueOf(result));
        }

        String Result = objectMapper.writeValueAsString(responseData);
        return ResponseEntity.ok(Result);
    }

    @PostMapping("/wishlist")
    public ResponseEntity<Object> insertWishList(@RequestParam HashMap<String, Object> map) throws Exception {
        System.out.println("map = " + map);
        map.put("id", aes128.decrypt((String) map.get("userID")));

        System.out.println("여기는 postmapping");

        return null;
    }
}
