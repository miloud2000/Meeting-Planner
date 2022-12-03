package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"meeting.palnner.controller", "meeting.palnner.serviceImpl","meeting.palnner.serviceInterface"})
@EnableJpaRepositories("meeting.palnner.repository")
@EntityScan("meeting.palnner.entity")
public class AppConfiguration {

}
