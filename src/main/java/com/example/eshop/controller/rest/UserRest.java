package com.example.eshop.controller.rest;

import com.example.eshop.dto.UserCreatorDto;
import com.example.eshop.dto.UserDto;
import com.example.eshop.dto.UserVerificationDto;
import com.example.eshop.dto.mapper.UserMapper;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.User;
import com.example.eshop.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRest {

    private final CustomUserDetailsService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAll(Pageable pageable) {
        return userMapper.toDtos(userService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> add(@Valid @RequestBody UserCreatorDto dto) {
        User user = userMapper.toUser(dto);
        userService.add(user);
        return ResponseEntity.ok(Map.of("redirect",  "/login?error=activation"));
    }

    @GetMapping("/activate")
    public ModelAndView activatePage(@RequestParam("key") String code) throws ObjectNotFoundException {
        ModelAndView modelAndView = new ModelAndView("activate");
        User user = userService.findByActivationCode(code);

        modelAndView.addObject("User", user);
        return modelAndView;
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@Valid @RequestBody UserVerificationDto dto) {
        User user = userMapper.toUser(dto);
        userService.activate(user);

        return ResponseEntity.ok(Map.of("redirect",  "/login"));
    }

}
