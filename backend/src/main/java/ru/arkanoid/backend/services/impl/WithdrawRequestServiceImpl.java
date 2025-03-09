package ru.arkanoid.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.arkanoid.backend.filters.FilterActions;
import ru.arkanoid.backend.filters.finance.combo.WithdrawRequestFilter;
import ru.arkanoid.backend.models.finance.WithdrawRequestCreationModel;
import ru.arkanoid.backend.repositories.withdraw.WithdrawRequestRepository;
import ru.arkanoid.backend.serializers.finance.WithdrawRequestCreationSerializer;
import ru.arkanoid.backend.services.WithdrawRequestService;
import ru.arkanoid.backend.user.User;
import ru.arkanoid.backend.user.finance.WithdrawRequest;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WithdrawRequestServiceImpl implements WithdrawRequestService {
    private final WithdrawRequestCreationSerializer withdrawRequestCreationSerializer;
    private final WithdrawRequestFilter withdrawRequestFilter;
    private final WithdrawRequestRepository repository;


    @Override
    public void accept(UUID uuid) {
        // TODO: make accept
    }

    @Override
    public void decline(UUID uuid) {
        // TODO: make decline
    }

    @Override
    public void create(User user, WithdrawRequestCreationModel model) {
        var request = withdrawRequestCreationSerializer.userUuid(user.getUuid()).serialize(model);
        withdrawRequestFilter.doFilter(request, FilterActions::ThrowException);

        repository.save(request);
    }

    @Override
    public List<WithdrawRequest> getWithdrawRequests(User user) {
        return repository.getAllWithdrawRequests(user.getUuid());
    }
}
