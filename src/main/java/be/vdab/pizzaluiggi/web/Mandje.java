package be.vdab.pizzaluiggi.web;

import java.util.List;

interface Mandje {
	void addPizzaId(long pizzaId);
	List<Long> getPizzaIds();
}
