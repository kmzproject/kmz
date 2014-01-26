package ru.kmz.server.engine.projects.update;

import ru.kmz.server.data.model.Order;
import ru.kmz.server.data.model.ProductElementTask;
import ru.kmz.server.data.utils.OrderDataUtils;

public class UpdateOrderStartFinishService {

	public void updateOnAddNewProduct(Order order, ProductElementTask product) {
		boolean isEdit = updateOrderByProduct(order, product);
		if (isEdit) {
			// TODO: запись в историю - изменилась дата заказа
		}
	}

	private boolean updateOrderByProduct(Order order, ProductElementTask product) {
		boolean isEdit = false;
		if (order.isEmptyTime() || order.getFinish().before(product.getFinish())) {
			order.setFinish(product.getFinish());
			isEdit = true;
		}
		if (order.isEmptyTime() || order.getStart().after(product.getStart())) {
			order.setStart(product.getStart());
			isEdit = true;
		}
		if (isEdit) {
			order = OrderDataUtils.edit(order);
		}
		return isEdit;
	}

	public void updateByOrderId(long orderId) {
		Order order = OrderDataUtils.getOrderAndLoadAllChild(orderId);
		order.setStart(null);
		order.setFinish(null);
		OrderDataUtils.edit(order);
		for (ProductElementTask product : order.getChilds()) {
			updateOrderByProduct(order, product);
		}
	}
}
