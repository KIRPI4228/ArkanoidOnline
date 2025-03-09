package ru.arkanoid.backend.controllers.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.arkanoid.backend.exceptions.ServiceException;
import ru.arkanoid.backend.helpers.RestHelper;
import ru.arkanoid.backend.models.user.rest.UserResetPasswordModel;
import ru.arkanoid.backend.models.user.rest.UserSignInModel;
import ru.arkanoid.backend.models.user.rest.UserSignUpModel;
import ru.arkanoid.backend.serializers.TokenSerializer;
import ru.arkanoid.backend.services.UserAuthenticationService;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user/auth")
@RequiredArgsConstructor
public class UserAuthRestController {

    private final UserAuthenticationService service;
    private final TokenSerializer tokenSerializer;

    @PostMapping("sign-up")
    @PreAuthorize("!isAuthenticated()")
    public ResponseEntity signUp(@RequestBody UserSignUpModel model) {
        return RestHelper.getResponse(() -> service.signUp(model));
    }

    @PostMapping("sign-in")
    @PreAuthorize("!isAuthenticated()")
    public ResponseEntity signIn(@RequestBody UserSignInModel model) {
        return RestHelper.getResponse(() -> service.signIn(model));
    }

    @PostMapping("sign-up/verify")
    @PreAuthorize("!isAuthenticated()")
    public ResponseEntity signUpVerify(@RequestHeader("Authorization") String authHeader, @RequestParam("key") String key) {
        return RestHelper.getResponse(() -> service.verifySignUp(Optional.ofNullable(tokenSerializer.serialize(authHeader))
                .orElseThrow(() -> new ServiceException()), key));
    }

    @PostMapping("reset/verify")
    @PreAuthorize("!isAuthenticated()")
    public ResponseEntity resetPasswordVerify(@RequestHeader("Authorization") String authHeader, @RequestParam("key") String key) {
        return RestHelper.getResponse(() -> service.verifyResetPassword(Optional.ofNullable(tokenSerializer.serialize(authHeader))
                .orElseThrow(() -> new ServiceException()), key));
    }

    @PostMapping("reset")
    @PreAuthorize("!isAuthenticated()")
    public ResponseEntity reset(@RequestBody UserResetPasswordModel model) {
        return RestHelper.getResponse(() -> service.resetPassword(model));
    }

    @GetMapping("/is-authorized")
    public ResponseEntity isAuthorized(@RequestHeader("Authorization") String authHeader) {
        return RestHelper.getResponse(() -> service.isAuthorized(tokenSerializer.serialize(authHeader)));
    }
}
