package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //Byte코드를 문자로 받을때는 항상 인코딩을 설정해줘야한다.
                                                                                            //default값은 os에 설정된 기본 인코딩 또는 자바 실행시 기본설정되는 인코딩 등.
        log.info("messageBody = {}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException { //스프링 제공기능 "HttpEntitiy"
                                                                                                      //httpBody의 파라미터를 문자로 변환하여 가져옴.
        String messageBody = httpEntity.getBody();
                            //httpEntity.getHeader();
        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(@RequestBody String messageBody){     //httpBody @ResponseBody 응답

        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
    }
}
