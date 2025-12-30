package com.goldin.controllers;

import com.goldin.entity.CartItem;
import com.goldin.entity.Surf;
import com.goldin.entity.User;
import com.goldin.mapper.dto.UserTo;
import com.goldin.repository.UserRepository;
import com.goldin.service.CartService;
import com.goldin.service.SurfService;
import com.goldin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurfService surfService;

    @GetMapping
    public String cart(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        user = userRepository.findByIdRealUser(user.getId()).orElseThrow();
        List<Surf> surfs = user.getCart()
                .stream()
                .map(CartItem::getSurf)
                .collect(Collectors.toList());
        int sum = surfs.stream()
                .mapToInt(surf -> Integer.parseInt(surf.getPrice()))
                .sum();
        model.addAttribute("surfs", surfs);
        model.addAttribute("sum", sum);
        return "cart";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("itemId") long id) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        cartService.removeFromCart(user, surfService.finfByIdRealSurf(id), 1);
        return "redirect:/cart";
    }


}
