package geekbrains.frontend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import geekbrains.entities.Order;
import geekbrains.security.CustomPrincipal;
import geekbrains.services.OrderService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderView extends AbstractView {
    private final OrderService orderService;
    private final Authentication authentication;

    protected Grid<Order> orderGrid;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
        initOrderView();
    }

    public void initOrderView() {
        orderGrid = new Grid<>(Order.class);
        List<Order> order = orderService.getByUserName(((CustomPrincipal)authentication.getPrincipal()).getUsername());
        orderGrid.setItems(order);
        orderGrid.setColumns("address", "items", "phoneNumber", "price", "status");

        add(orderGrid);
    }
}