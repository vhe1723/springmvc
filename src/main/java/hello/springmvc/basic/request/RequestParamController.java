package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {     //점진적으로 발전하는 파라미터 조회방법 1-2-3-4

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username"); //POST방식, GET방식 모두 조회가능
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody       //@RestController와 같은역할 (http body에 문자열을 바로 넣어줌)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age{}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody       //@RestController와 같은역할 (http body에 문자열을 바로 넣어줌)
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            //http://localhost:8080/request-param-v2?username=he22222llo&age=20
            @RequestParam String username, // 요청파라미터 이름랑 같다면 생략가능
            @RequestParam int age) {

        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody       //@RestController와 같은역할 (http body에 문자열을 바로 넣어줌)
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        //http://localhost:8080/request-param-v2?username=he22222llo&age=20
        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            //http://localhost:8080/request-param-v2?username=he22222llo&age=20
            //@RequestParam이 명시된 경우 true가 default, 생략된 경우는 false가 default
            @RequestParam(required = true) String username, // username이 없으면 400에러(client에러) default값이다
            @RequestParam(required = false) int age) {       //500에러 (서버에러) int에는 null이 들어갈수없음
//            @RequestParam(required = false) Integer age){ //null로 넣어주기위해 integer사용

        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //값이 들어오지않을경우 defaultValue로 설정한 값이된다.
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age{}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age ){
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username={}, age{}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData={}", helloData); //helloData=HelloData(username=hello, age=20)  자동으로 String형태로 만들어줌
//
//        return "ok";
//    }
    //위의코드를 @ModelAttribute어노테이션을 사용하여 바꾼 코드
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        //ModelAttribute는 선언한 객체에 자동으로 요청파라미터들을 set해준다.
        log.info("username={}, age{}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); //helloData=HelloData(username=hello, age=20)  자동으로 String형태로 만들어줌

        return "ok";
    }
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){ //ModelAttribute생략

        //String, int, Integer ==>RequestParam
        //나머지는 ModelAttribute        둘다 생략가능
        log.info("username={}, age{}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); //helloData=HelloData(username=hello, age=20)  자동으로 String형태로 만들어줌

        return "ok";
    }
}
