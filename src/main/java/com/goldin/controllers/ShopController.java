package com.goldin.controllers;

import com.goldin.mapper.dto.SurfTo;
import com.goldin.service.SurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/surfShop")
public class ShopController {
    @Autowired
    private SurfService surfService;


    @GetMapping
    public String shop(Model model) {
        SurfTo byId = surfService.findById(2L);
        model.addAttribute("surfs", byId);
        return "surfShop";
    }
}
