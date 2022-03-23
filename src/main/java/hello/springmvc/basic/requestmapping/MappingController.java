package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET) //배열로도 제공한다. ex)/hello-basic, /test-test
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }
    /**
     * 축약 어노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같을경우 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
//    public String mappingPath(@PathVariable("userId") String data) {
//        log.info("mappingPath userid={}", data);
//        return "ok";
    public String mappingPath(@PathVariable String userId) {    //변수명이 같을 때 생략가능
        log.info("mappingPath userid={}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    //http://localhost:8080/mapping/users/userA/orders/100
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
//    http://localhost:8080/mapping-param?mode=debug
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     *특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    //header에 임의 키-밸류 추가 (mode, mapping)
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }
    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */

    @PostMapping(value = "/mapping-consume", consumes = "application/json") //contentType이 json인 경우만 호출이 됨.
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }
    /**

     * Accept 헤더 기반 Media Type * produces = "text/html"
     * produces = "!text/html" * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")    //Accept = produces    text/html인 경우에만 호출
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
