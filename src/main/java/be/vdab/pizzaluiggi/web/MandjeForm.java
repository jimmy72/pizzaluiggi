package be.vdab.pizzaluiggi.web;

import javax.validation.constraints.Min;

class MandjeForm {
	
	@Min(1)
	private long pizzaId;

	public long getPizzaId() {
		return this.pizzaId;
	}

	public void setPizzaId(long pizzaId) {
		this.pizzaId = pizzaId;
	}
}
