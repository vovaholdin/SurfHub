package com.goldin.controllers;

import com.goldin.entity.CartItem;
import com.goldin.entity.User;
import com.goldin.mapper.dto.SurfTo;
import com.goldin.repository.UserRepository;
import com.goldin.service.CartService;
import com.goldin.service.SurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/surfShop")
public class ShopController {
    @Autowired
    private SurfService surfService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String shop(Model model) {
        model.addAttribute("surfs", surfService.findAll());
        return "surfShop";
    }

    @PostMapping("/cart/add")
    public String addCart(@RequestParam("surfId") Long surfId){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        cartService.addToCart(user, surfService.findById(surfId), 1);
        return "redirect:/surfShop";
    }
}
