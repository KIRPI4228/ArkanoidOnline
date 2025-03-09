package ru.arkanoid.backend.controllers.rest.withdraw;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arkanoid.backend.helpers.RestHelper;
import ru.arkanoid.backend.serializers.TokenSerializer;
import ru.arkanoid.backend.services.UserService;
import ru.arkanoid.backend.services.WithdrawRequestService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cashier/withdraw")
@RequiredArgsConstructor
public class WithdrawRestController {
    private final WithdrawRequestService withdrawRequestService;
    private final UserService userService;
    private final TokenSerializer tokenSerializer;

    @GetMapping("withdraws")
    public ResponseEntity withdraws(@RequestHeader("Authorization") String authHeader) {
        return RestHelper.getResponse(
                () -> withdrawRequestService.getWithdrawRequests(userService.getUser(tokenSerializer.serialize(authHeader)))
        );
    }
}
