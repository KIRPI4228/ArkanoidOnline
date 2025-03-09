package ru.arkanoid.gamebackend.services.impl;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.arkanoid.gamebackend.services.TransactionService;
import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.proto.lib.TransactionRequest;
import ru.arkanoid.proto.lib.TransactionResponse;
import ru.arkanoid.proto.lib.TransactionServiceGrpc;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private static final int BUY_GAME_AMOUNT = 300;
    private static final int PAY_OUT_GAME_AMOUNT = 500;

    @GrpcClient("grpc-client")
    private TransactionServiceGrpc.TransactionServiceBlockingStub client;

    @Override
    public TransactionResponse buyGame(Session session) {
        return client.buyGame(TransactionRequest.newBuilder()
                .setToken(session.getToken())
                .setAmount(BUY_GAME_AMOUNT)
                .build());
    }

    @Override
    public TransactionResponse payOutGame(Session session) {
        return client.payOutGame(TransactionRequest.newBuilder()
                .setToken(session.getToken())
                .setAmount(PAY_OUT_GAME_AMOUNT)
                .build());
    }
}
