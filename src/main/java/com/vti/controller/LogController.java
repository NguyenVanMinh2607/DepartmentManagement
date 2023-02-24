package com.vti.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/logs")
@Log4j2
public class LogController {

    @GetMapping
    public String logs() {
       log.info("INFO...");
       log.debug("DEBUG...");
       log.info("INFO...");
       log.warn("WARN...");
       log.error("ERROR...");
       log.trace("TRACE...");
       return "Please check console to se logs...";
    }
}
