package be.vdab.pizzaluiggi.web;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

class VanTotPrijsForm {
	@NotNull @Min(0)
	private BigDecimal van;
	@NotNull @Min(0)
	private BigDecimal tot;
	
	public BigDecimal getVan() {
		return van;
	}
	public void setVan(BigDecimal van) {
		this.van = van;
	}
	public BigDecimal getTot() {
		return tot;
	}
	public void setTot(BigDecimal tot) {
		this.tot = tot;
	}
	
}
