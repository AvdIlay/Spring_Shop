package geeakbrains.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceSecondApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSecondApp.class, args);
    }
}
