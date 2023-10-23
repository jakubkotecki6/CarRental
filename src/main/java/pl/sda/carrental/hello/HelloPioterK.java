package pl.sda.carrental.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello/pioter")
public class HelloPioterK {
    @GetMapping
    public String hello(){
        return "Hello pioter";
    }
}
