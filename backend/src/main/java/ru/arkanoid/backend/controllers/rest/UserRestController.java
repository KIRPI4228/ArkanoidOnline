package ru.arkanoid.backend.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arkanoid.backend.helpers.RestHelper;
import ru.arkanoid.backend.models.user.rest.UserChangePasswordModel;
import ru.arkanoid.backend.serializers.TokenSerializer;
import ru.arkanoid.backend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService service;
    private final TokenSerializer tokenSerializer;

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestHeader("Authorization") String authHeader, @RequestBody UserChangePasswordModel model) {
        return RestHelper.getResponse(() -> service.changePassword(tokenSerializer.serialize(authHeader), model));
    }

    @PostMapping("/change-username")
    public ResponseEntity changeUsername(@RequestHeader("Authorization") String authHeader, @RequestParam("newUsername") String newUsername) {
        return RestHelper.getResponse(() -> service.changeUsername(tokenSerializer.serialize(authHeader), newUsername));
    }


    @GetMapping("/info")
    public ResponseEntity info(@RequestHeader("Authorization") String authHeader) {
        return RestHelper.getResponse(() -> service.getUser(tokenSerializer.serialize(authHeader)));
    }
}
