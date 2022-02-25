package com.example.eshop.controller.rest;

import com.example.eshop.dto.UserCreatorDto;
import com.example.eshop.dto.UserVerificationDto;
import com.example.eshop.dto.mapper.UserMapper;
import com.example.eshop.exception.EmailExistsException;
import com.example.eshop.exception.ObjectNotFoundException;
import com.example.eshop.model.User;
import com.example.eshop.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailsService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody UserCreatorDto dto) throws EmailExistsException {
        User user = userMapper.toUser(dto);
        userService.add(user);
        return ResponseEntity.ok(Map.of("redirect",  "/login?activation"));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@Valid @RequestBody UserVerificationDto dto, @RequestParam("key") String code) throws ObjectNotFoundException {
        User user = userMapper.toUser(dto);

        if (!userService.isUserCode(user, code)) {
            throw new AccessDeniedException("Access denied");
        }
        userService.activate(user);
        return ResponseEntity.ok(Map.of("redirect",  "/login"));
    }
}
