package UsedStore.Vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVO {
    private int itemID;
    private String userID;
    private String itemName;
    private String itemContent;
    private String itemPrice;
    private String categoryId;
    private String itemGender;
    private String itemTag;
    private String itemTitle;
    private String itemState;
    private String itemExchange;
    private String address;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
//
    private int status;
}
