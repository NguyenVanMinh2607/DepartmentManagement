package com.vti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String findByCode(@RequestParam String code) {
        // localeContextHolder : nguời nắm giữ ngôn ngữ
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    @GetMapping("/en")
    public String findByCodeEn(@RequestParam String code) {
        return messageSource.getMessage(code, null, Locale.ENGLISH);
    }

    @GetMapping("/vi")
    public String findByCodeVi(@RequestParam String code) {
        return messageSource.getMessage(code, null, new Locale("vi"));
    }
//
//    @GetMapping("/ja")
//    public String findByCodeJa(@RequestParam String code) {
//        return messageSource.getMessage(code, null, Locale.JAPANESE);
//    }
}
