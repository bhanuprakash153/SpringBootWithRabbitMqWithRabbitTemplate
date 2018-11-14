package com.example.ZRabbitMqSpringBootExample.async;

import com.example.ZRabbitMqSpringBootExample.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class StudentConsumer {


    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentConsumer.class);


    @RabbitListener(queues = {Config.addToSubscriberList})
    public void receivingDetails(String message) {

        Student student1 = null;
        try {
            student1 = objectMapper.readValue(message, Student.class);



            LOGGER.info("receiving messages started:" + "\n" + "name of student:" + student1.getName() + "\n" + "id of student:" + student1.getId());

            // System.out.println(student1);

            //service.studentDetails(student1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to receive message:" + e.getMessage());
        }


    }
}
