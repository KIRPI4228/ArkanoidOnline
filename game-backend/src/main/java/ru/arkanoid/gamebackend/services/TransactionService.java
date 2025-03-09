package ru.arkanoid.gamebackend.services;

import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.proto.lib.TransactionResponse;

public interface TransactionService {
    TransactionResponse buyGame(Session session);
    TransactionResponse payOutGame(Session session);
}
