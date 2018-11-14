package com.example.ZRabbitMqSpringBootExample.controller;

import com.example.ZRabbitMqSpringBootExample.async.StudentProducer;
import com.example.ZRabbitMqSpringBootExample.model.Employee;
import com.example.ZRabbitMqSpringBootExample.model.Student;
import com.example.ZRabbitMqSpringBootExample.sender.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Publisher publisher;

    @Autowired
    private StudentProducer studentProducer;
    // private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/send")
    public String message(Employee employee) {

        publisher.sendDetails(employee);

        return "message sent successfully";


    }


    @GetMapping("/abc")
    public void message(Student student) {

        studentProducer.SendingDetails(student);

    }

}
