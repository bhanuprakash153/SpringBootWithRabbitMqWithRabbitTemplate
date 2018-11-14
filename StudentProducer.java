package com.example.ZRabbitMqSpringBootExample.async;


import com.example.ZRabbitMqSpringBootExample.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StudentProducer {

    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentProducer.class);

    @Autowired
    public StudentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void SendingDetails(Student student) {

        try {
            rabbitTemplate.convertAndSend(Config.addToSubscriberList, objectMapper.writeValueAsString(student));
            LOGGER.info("\n" + "sending message:" + student.getName() + "\n" + student.getId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.error("failed to sending the student details" + e.getMessage());
        }

    }
}
