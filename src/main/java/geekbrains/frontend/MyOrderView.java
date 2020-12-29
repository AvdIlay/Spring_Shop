package geekbrains.frontend;

import com.vaadin.flow.router.Route;
import geekbrains.services.OrderService;

@Route("orders")
public class MyOrderView extends OrderView {
    public MyOrderView(OrderService orderService) {
        super(orderService);
    }
}
