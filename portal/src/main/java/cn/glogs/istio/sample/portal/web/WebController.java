package cn.glogs.istio.sample.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

    RestTemplate restTemplate = new RestTemplate();

    private String moduleAUrl;

    private String moduleBUrl;

    @Autowired
    public WebController(
            @Value("${url.module-a}") String moduleAUrl,
            @Value("${url.module-b}") String moduleBUrl
    ) {
        this.moduleAUrl = moduleAUrl;
        this.moduleBUrl = moduleBUrl;
    }

    @GetMapping("/ping")
    public Pong ping() {
        return new Pong("istio-portal");
    }

    @GetMapping("/ping/module-a")
    public Pong pingA() {
        ResponseEntity<Pong> response = restTemplate.getForEntity(moduleAUrl + "/ping", Pong.class);
        return response.getBody();
    }

    @GetMapping("/ping/module-b")
    public Pong pingB() {
        ResponseEntity<Pong> response = restTemplate.getForEntity(moduleBUrl + "/ping", Pong.class);
        return response.getBody();
    }

    @GetMapping("/module-a/ping/module-c")
    public Pong pingCThroughModuleA() {
        ResponseEntity<Pong> response = restTemplate.getForEntity(moduleAUrl + "/ping/module-c", Pong.class);
        return response.getBody();
    }

    @GetMapping("/module-b/ping/module-c")
    public Pong pingCThroughModuleB() {
        ResponseEntity<Pong> response = restTemplate.getForEntity(moduleBUrl + "/ping/module-c", Pong.class);
        return response.getBody();
    }
}
