package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        Assertions.assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        //new BindingResult().rejectValue("itemName", "required");
        //new FieldError("item", "itemName", null, false, messageCodes, null, null)

        Assertions.assertThat(messageCodes).containsExactly("required.item.itemName", "required.itemName", "required.java.lang.String", "required");
    }

    /**
     * MessageCodesResolver는 검증 오류 코드로 메세지 코드들을 생성한다.
     * 객체 오류는 code.object name ...
     * 필드 오류는 code.object name.field ...
     * 타입 오류 typeMismatch ...
     */
}
