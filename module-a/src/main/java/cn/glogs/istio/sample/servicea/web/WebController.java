package cn.glogs.istio.sample.servicea.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

    RestTemplate restTemplate = new RestTemplate();

    private String moduleCUrl;

    @Autowired
    public WebController(@Value("${url.module-c}") String moduleCUrl) {
        this.moduleCUrl = moduleCUrl;
    }

    @GetMapping("/ping")
    public Pong ping() {
        return new Pong("module-a");
    }


    @GetMapping("/ping/module-c")
    public Pong pingB() {
        ResponseEntity<Pong> response = restTemplate.getForEntity(moduleCUrl + "/ping", Pong.class);
        return response.getBody();
    }
}
