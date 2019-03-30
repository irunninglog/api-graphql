package com.irunninglog.graphql.controller;

import com.irunninglog.graphql.object.Ping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class PingController {

    @RequestMapping("/ping")
    public Ping ping() {
        return new Ping();
    }

}
