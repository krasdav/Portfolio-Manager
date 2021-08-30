package org.dav.equitylookup.controller;

import lombok.RequiredArgsConstructor;
import org.dav.equitylookup.model.dto.UserDTO;
import org.dav.equitylookup.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/users/list")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/users/find")
    public String getUserByUsername(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user-find-form";
    }

//    @PostMapping("/users/find")
//    public String getUserByUsername(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Model model) throws IOException {
//
//        if (bindingResult.hasErrors()) {
//            return "user-find-form";
//        }
//
//        User user = modelMapper.map(userDTO, User.class);
//        user = userService.getUserByUsername(user.getUsername());
//        userService.updatePortfolioValue(user);
//
//        userDTO = modelMapper.map(user, UserDTO.class);
//
//        model.addAttribute("username", userService.getUserByUsername(userDTO.getUsername()).getUsername());
//        model.addAttribute("stocks", userService.getUserByUsername(userDTO.getUsername()).getStocks());
//        model.addAttribute("portfolio", userService.getUserByUsername(userDTO.getUsername()).getPortfolio());
//        return "user-find-res";
//    }
//
//    @PostMapping("/user/stocks/list")
//    public String listStocks(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Model model) throws IOException {
//        if (bindingResult.hasErrors()) {
//            return "user/user-stock-query";
//        }
//
//        User user = modelMapper.map(userDTO, User.class);
//        user = userService.getUserByUsername(user.getUsername());
//        userService.updatePortfolioValue(user);
//
//        userDTO = modelMapper.map(user, UserDTO.class);
//        model.addAttribute("stocks", userDTO.getStocks());
//        model.addAttribute("portfolioValue", userDTO.getPortfolio());
//        return "user/user-stock-list";
//    }
}
