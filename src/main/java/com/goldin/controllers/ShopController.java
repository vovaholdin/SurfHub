package com.goldin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/surfShop")
public class ShopController {
    @GetMapping
    public String shop() {
        return "surfShop";
    }
}
