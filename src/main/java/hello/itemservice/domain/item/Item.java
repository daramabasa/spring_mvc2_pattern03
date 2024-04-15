package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range; // hibernate validator가 있어야지 동작 시작
import org.hibernate.validator.constraints.ScriptAssert;

// validator 없이도 동작
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//기능이 약해서 권장하지 않음
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10,000원 넘게 작성해주세요.")
public class Item {

    @NotNull(groups = {UpdateCheck.class})
    private Long id;

    //@NotBlank(message = "사용자 지정 오류 메세지")
    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(max = 1000000, min = 1000)
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
