package com.exp.controllers;

import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data")
public class Controller {

    @GetMapping
    public ResponseEntity<Integer> get(@RequestParam long delay) throws InterruptedException {
        TimeUnit.SECONDS.sleep(delay);
        return ResponseEntity.ok(100);
    }

}
