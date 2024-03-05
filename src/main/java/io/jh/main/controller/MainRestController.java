package io.jh.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/main/v1")
public class MainRestController {

    @GetMapping(path = "/testMan")
    public String selectTest() {
        return "Hello World";
    }

}
