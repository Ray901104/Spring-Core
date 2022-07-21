package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycletest() {
        // 부모는 자식을 담을 수 있다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifecycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifecycleConfig {

        // destroyMethod 를 따로 지정하지 않을 경우 기본값으로 추론 (close, shutdown)하여 실행시켜준다 - 외부 라이브러리 대부분 두 이름 중 하나으 메소드를 사용하기 때문
        // 소멸 기능을 사용하기 싫으면 destroyMethod = "" 공백으로 지정하면 된다.
        @Bean(initMethod = "init")
//        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}