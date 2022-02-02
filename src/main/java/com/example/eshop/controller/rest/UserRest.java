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
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRest {

    private final CustomUserDetailsService service;
    private final UserMapper mapper;

    @GetMapping
    public List<UserDto> getAll(Pageable pageable) {
        return mapper.toDtos(service.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> add(@RequestBody UserCreatorDto dto) {
        User user = mapper.toUser(dto);
        service.add(user);
        return ResponseEntity.ok(Map.of("redirect",  "/login?error=activation"));
    }

    @GetMapping("/activate")
    public ModelAndView activatePage(@RequestParam("key") String code) throws ObjectNotFoundException {
        ModelAndView modelAndView = new ModelAndView("activate");
        User user = service.findByActivationCode(code);

        modelAndView.addObject("User", user);
        return modelAndView;
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody UserVerificationDto dto) {
        User user = mapper.toUser(dto);
        service.activate(user);

        return ResponseEntity.ok(Map.of("redirect",  "/login"));
    }

}
