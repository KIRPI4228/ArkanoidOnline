package ru.arkanoid.gamebackend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
    private static final int CORES = Runtime.getRuntime().availableProcessors();

    @Bean
    public Executor threadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORES-2);
        threadPoolTaskExecutor.setKeepAliveSeconds(10);

        return threadPoolTaskExecutor;
    }
}
