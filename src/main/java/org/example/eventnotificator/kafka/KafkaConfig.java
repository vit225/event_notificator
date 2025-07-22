package org.example.eventnotificator.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<Integer, KafkaEvent> consumerFactory() {
        JsonDeserializer<KafkaEvent> delegate = new JsonDeserializer<>(KafkaEvent.class, false);
        delegate.addTrustedPackages("*");

        ErrorHandlingDeserializer<KafkaEvent> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(delegate);

        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "notificator-group");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configProperties, new IntegerDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, KafkaEvent> containerFactory(
            ConsumerFactory<Integer, KafkaEvent> consumerFactory
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<Integer, KafkaEvent>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}