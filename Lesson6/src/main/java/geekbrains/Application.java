package geekbrains;

import com.fasterxml.jackson.databind.ObjectMapper;
import geekbrains.album.Album;
import geekbrains.album.Music;
import geekbrains.album.Singers;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


@SpringBootApplication
@EnableScheduling
public class Application {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Queue myQueue() {
        return new Queue("Test", true);
    }

    @RabbitListener(queues = "Test")
    public void list(String msg) {
        System.out.println("Message from my queue: " + msg);
        StringReader reader = new StringReader(msg);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Singers subscriber = mapper.readValue(reader, Singers.class);
            System.out.println(subscriber.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 5000L)
    private void SendMessage() {
        Singers singers = new Singers("Серж ","Танкя́н " , "21.08.1967",
                new Album("Toxicity",15,"13.08.2001"));
        singers.addMusic(new Music("Loco","3.14"));
        singers.addMusic(new Music("Lom","3.34"));
        singers.addMusic(new Music("Pom","3.44"));
        System.out.println(".....Объект.....");
        System.out.println(singers.toString());
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, singers);

        } catch (IOException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend("example-66", "", writer.toString());
    }


}