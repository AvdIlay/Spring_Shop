package geekbrains.frontend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import geekbrains.entities.Order;
import geekbrains.services.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Route("orders")
@Transactional
public class OrderView extends AbstractView {
    private final OrderService orderService;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
        initOrderView();
    }

    @Transactional
    public void initOrderView() {
        Grid<Order> orderGrid = new Grid<>(Order.class);
        List<Order> order = orderService.getByUserId(1L);
        orderGrid.setItems(order);
        orderGrid.setColumns("address", "items", "phoneNumber", "price", "status");

        add(orderGrid);
    }

    @Override
    @Transactional
    public void add(Component... components) {
        super.add(components);
    }

}
