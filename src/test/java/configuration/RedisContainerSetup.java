package configuration;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisContainerSetup {

    private static final String DOCKER_IMAGE = "redis:5.0.3-alpine";
    private static final Integer PORT = 6380;

    static {
        GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse(DOCKER_IMAGE));
        redisContainer.addExposedPort(PORT);
        redisContainer.start();
    }
}
