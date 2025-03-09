package ru.arkanoid.gamebackend.update.scheduler.tasks;

import lombok.Getter;
import ru.arkanoid.gamebackend.helpers.TimeHelper;

import java.util.UUID;
import java.util.function.BooleanSupplier;

@Getter
public class Waiter implements SchedulerTask {
    private final UUID id = UUID.randomUUID();

    private final long startTime;

    private final Runnable runnable;

    private final BooleanSupplier supplier;

    public Waiter(BooleanSupplier supplier, Runnable runnable) {
        this.runnable = runnable;
        this.supplier = supplier;

        startTime = TimeHelper.getCurrentMillis();
    }

    @Override
    public boolean isExecutionTime(long currentTime) {
        return supplier.getAsBoolean();
    }
}
