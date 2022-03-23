package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody   //클래스에 붙혀주면 모든 메서드에 어노테이션을 붙일수 있다.
@RestController //@Controller + @ResponseBody
public class ResponseBodyController {

    @GetMapping("/response-body-stirng-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-stirng-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //    @ResponseBody       //view를 사용하지 않고, HTTP 메세지 컨버터를 통해 직접 입력된다. (ResponseEntity, HttpEntity도 마찬가지)
    @GetMapping("/response-body-stirng-v3")
    public String responseBodyV3() {
        return "ok";
    }


    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)  //repsonseBody어노테이션 사용으로 Status를 지정할 수 없기에 제공되는 어노테이션
//    @ResponseBody                   //HttpStatus를 동적으로 사용하고싶은경우 어노테이션은 하드코딩되있기때문에 v1예제 ResponseEntity를 이용한다.
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
