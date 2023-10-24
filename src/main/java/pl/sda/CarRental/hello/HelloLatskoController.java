package pl.sda.carrental.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello/latsko")
public class HelloLatskoController {

    @GetMapping
    public String hello() {
        return "Hello Stachu";
    }
}
