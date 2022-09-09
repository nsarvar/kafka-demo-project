package com.example.springkafkademo.consumer;

import com.raisin.operations.common.api.RetryProcessorFailedException;
import com.raisin.operations.kafka.Event;
import com.raisin.operations.kafka.api.KafkaRetryConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepositOrderUpdatedConsumer implements KafkaRetryConsumer {
    @Override
    public Void process(Event event) throws RetryProcessorFailedException {
        log.info(event.getBody());
        return null;
    }

    @KafkaListener(
        topics = "${spring.kafka.consumer.topics.demo-topic}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(final ConsumerRecord<String, String> message) {
        log.info(message.value());
    }
}
