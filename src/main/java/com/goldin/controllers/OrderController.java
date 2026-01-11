package com.goldin.controllers;

import com.goldin.entity.User;
import com.goldin.mapper.dto.SurfTo;
import com.goldin.mapper.dto.UserTo;
import com.goldin.service.SurfService;
import com.goldin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private SurfService surfService;

    @PostMapping
    public String proceedOrder(@RequestParam("itemId") long id, Model model){
        SurfTo item = surfService.findById(id);
        model.addAttribute("item", item);
        return "order";
    }

}
