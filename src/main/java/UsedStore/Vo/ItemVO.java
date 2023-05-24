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
    private int categoryId;
    private String itemGender;
    private String itemTag;
    private String itemTitle;
}
