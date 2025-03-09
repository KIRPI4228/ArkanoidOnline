package ru.arkanoid.gamebackend.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@RequiredArgsConstructor
public class FixedUpdater extends Updater {
    private final Executor threadPoolExecutor;
    @Getter
    private volatile boolean isActive;

    @Override
    protected void execute(UpdaterExecutor executor) {
        threadPoolExecutor.execute(() -> executor.execute());
    }

    @Override
    @Async
    public void start() {
        isActive = true;

        long nowTime;
        long updateTime;
        long waitTime;

        final int targetFps = 50;
        final long optionalTime = 1000000000 / targetFps;

        while (isActive) {
            nowTime = System.nanoTime();

            dispose();



            updateTime = System.nanoTime() - nowTime;
            waitTime = (optionalTime - updateTime) / 1000000;

            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {

            }

            //LoggerFactory.getLogger("updater").info("fps: " + (1000 / waitTime) + "\t queue: " + ((ThreadPoolTaskExecutor)threadPoolExecutor).getQueueSize());
        }
    }

    @Override
    public void stop() {
        isActive = false;
    }
}
