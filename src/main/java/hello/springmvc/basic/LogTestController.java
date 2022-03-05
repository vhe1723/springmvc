package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//일반적으로 controller어노테이션을 사용할 경우 반환시 view이름을 찾는다.
//RestController는 Http Body에 바로 입력된다.
@RestController
//롬복 제공 어노테이션
@Slf4j
public class LogTestController {
    //임포트시 Slf4j Logger를 써줘야 함.
    //@Slf4j 어노테이션 사용시 롬복이 밑 로그선언을 자동으로 해줌.
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        System.out.println("name = " + name);

        //로그찍을때 유의사항
        /* 결과값은 똑같지만 자바의 동작방식은 연산부터 하기때문에 "trace log = Spring"라는 연산을 거친다
           때문에 메모리사용 -> 원치않는 리소스 사용됨 */
        log.trace("trace log = " + name);

        /* 아래의 경우 메소드 호출시 파라미터만 넘김 -> 연산하지않음 */
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);
        return "ok";
    }
}

