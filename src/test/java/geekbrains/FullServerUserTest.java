package geekbrains;

import geekbrains.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerUserTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void UserRestTest() {
        User[] users = restTemplate.getForObject("/api/v1/user", User[].class);
        assertThat(users).isNull();
    }
}