package geekbrains.services;



import geekbrains.aspect.Log;
import geekbrains.entities.Order;
import geekbrains.entities.OrderItem;
import geekbrains.entities.User;
import geekbrains.repositories.OrderItemRepository;
import geekbrains.repositories.OrderRepository;

import geekbrains.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static geekbrains.entities.Order.Status.MANAGING;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserService userService,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder() {
        User user = userService.findByUsername(SecurityUtils.getPrincipal().getUsername());

        Order order = new Order();
        order.setItems(cartService.getItems());
        order.setAddress(cartService.getAddress());
        order.setPhoneNumber(cartService.getPhone());
        order.setUser(user);
        order.setPrice(cartService.getPrice());
        order.setStatus(MANAGING);
        order.setPhoneNumber(user.getPhone());

        final Order savedOrder = orderRepository.save(order);
    }

    @Transactional
    public List<Order> getByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> getByUserName(String userName) {
        return orderRepository.findAllByUser_Phone(userName);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void saveAll(List<Order> orders) {
        orderRepository.saveAll(orders);
    }
}
