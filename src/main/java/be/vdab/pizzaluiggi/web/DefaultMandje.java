package be.vdab.pizzaluiggi.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
class DefaultMandje implements Mandje, Serializable {

	private static final long serialVersionUID = 1L;
	private final List<Long> pizzaIds = new ArrayList<>();
	
	@Override
	public void addPizzaId(long pizzaId) {
		this.pizzaIds.add(pizzaId);
	}

	@Override
	public List<Long> getPizzaIds() {
		return this.pizzaIds;
	}

}
