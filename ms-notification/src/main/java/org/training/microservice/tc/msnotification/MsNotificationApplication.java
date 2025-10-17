package org.training.microservice.tc.msnotification;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@RequiredArgsConstructor
public class MsNotificationApplication {
    private final KafkaTemplate<Integer, String> kafkaTemplate;


    public void send(String str) {
        CompletableFuture<SendResult<Integer, String>> sendLoc = kafkaTemplate.send("my-topic",
                                                                                    str);
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group", concurrency = "2")
    public void method(String msg) {
        System.out.println("Msg received : " + msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(MsNotificationApplication.class,
                              args);
    }

    @Bean
    public NewTopic createNewTopic() {
        return TopicBuilder.name("my-topic")
                           .partitions(20)
                           .replicas(2)
                           .build();
    }

}
