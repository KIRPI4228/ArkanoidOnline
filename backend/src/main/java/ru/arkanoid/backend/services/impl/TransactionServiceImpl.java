package ru.arkanoid.backend.services.impl;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.arkanoid.backend.exceptions.ServiceException;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.services.UserService;
import ru.arkanoid.proto.lib.TransactionRequest;
import ru.arkanoid.proto.lib.TransactionResponse;
import ru.arkanoid.proto.lib.TransactionServiceGrpc;

@RequiredArgsConstructor
@GrpcService
public class TransactionServiceImpl extends TransactionServiceGrpc.TransactionServiceImplBase {
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void buyGame(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        try {
            var user = userService.getUser(request.getToken());

            user.getWallet().getCashBalance().deduct(request.getAmount(), "Game attempt");

            userRepository.save(user);

            responseObserver.onNext(TransactionResponse.newBuilder()
                    .setIsOk(true)
                    .build());
        } catch (ServiceException exception) {
            responseObserver.onNext(TransactionResponse.newBuilder()
                    .setResponse(exception.getMessage())
                    .setIsOk(false)
                    .build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void payOutGame(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        var user = userService.getUser(request.getToken());

        user.getWallet().getCoinBalance().topUp(request.getAmount(), "Game prize");

        userRepository.save(user);
    }
}
