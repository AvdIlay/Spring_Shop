package geekbrains;

import geekbrains.entities.Order;
import geekbrains.entities.Product;
import geekbrains.entities.User;
import geekbrains.repositories.OrderRepository;
import geekbrains.services.CartService;
import geekbrains.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static geekbrains.entities.Order.Status.MANAGING;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void addOrderTest() {
        cartService.setAddress("SPb");
        cartService.setPhone("89115552233");

        User user = userService.findById(1L);

        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setPrice(new BigDecimal(100 + (long) i * 10));
            product.setTitle("Product" + (long) i);
            cartService.add(product);
        }

        Order order = new Order();
        order.setItems(cartService.getItems());
        order.setAddress(cartService.getAddress());
        order.setPhoneNumber(cartService.getPhone());
        order.setUser(user);
        order.setPrice(cartService.getPrice());
        order.setStatus(MANAGING);
        order.setPhoneNumber(user.getPhone());

        List<Order> orders = orderRepository.findAll();

        Assertions.assertNotNull(orders);
        Assertions.assertEquals(5, cartService.getItems().size());
        Assertions.assertEquals(5, order.getItems().size());
        Assertions.assertEquals("SPb", order.getAddress());
    }
}