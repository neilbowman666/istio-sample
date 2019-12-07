package cn.glogs.istio.sample.servicec.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/ping")
    public Pong ping() {
        return new Pong("module-c");
    }
}
