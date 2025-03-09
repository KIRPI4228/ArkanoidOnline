package ru.arkanoid.backend.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arkanoid.backend.helpers.RestHelper;
import ru.arkanoid.backend.models.finance.WithdrawRequestCreationModel;
import ru.arkanoid.backend.serializers.TokenSerializer;
import ru.arkanoid.backend.services.CashierService;
import ru.arkanoid.backend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cashier")
@RequiredArgsConstructor
public class CashierRestController {

    private final CashierService cashierService;
    private final UserService userService;
    private final TokenSerializer tokenSerializer;


    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestHeader("Authorization") String authHeader, @RequestParam("amount") int amount) {
        return RestHelper.getResponse(
                () -> cashierService.deposit(userService.getUser(tokenSerializer.serialize(authHeader)), amount)
        );
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestHeader("Authorization") String authHeader, @RequestBody WithdrawRequestCreationModel model) {
        return RestHelper.getResponse(
                () -> cashierService.withdraw(userService.getUser(tokenSerializer.serialize(authHeader)), model)
        );
    }

    @PostMapping("/exchange")
    public ResponseEntity exchangeCoins(@RequestHeader("Authorization") String authHeader) {
        return RestHelper.getResponse(
                () -> cashierService.exchangeCoins(userService.getUser(tokenSerializer.serialize(authHeader)))
        );
    }
}
