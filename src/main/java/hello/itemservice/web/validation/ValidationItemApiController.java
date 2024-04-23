package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    /**
     * @ModelAttribute 는 필드 단위로 적용되기 때문에 특정 필드가 타입에 맞지 않아 오류가 발생해도 나머지는 정상 처리
     * 반면 HttpMessageConverter는 전체 객체 단위로 적용 -> @RequestBody는 일부라도 오류가 발생해 객체 변환이 안 되면 처리 불가
     */
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        // 검증 오류가 발생한 경우 ItemSaveForm 객체로 바꾸는 데에 실패 -> Controller 자체가 호출되지 않는다.

        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");
        return form;
    }
}
