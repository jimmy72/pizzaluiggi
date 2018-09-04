package be.vdab.pizzaluiggi.repositories;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.pizzaluiggi.entities.Pizza;
import be.vdab.pizzaluiggi.exceptions.PizzaNotFoundException;

@Repository
public class JdbcPizzaRepository implements PizzaRepository {

	private final NamedParameterJdbcTemplate TEMPLATE;
	private final SimpleJdbcInsert INSERT;
	
	private static final String SELECT_AANTAL_PIZZAS = "select count(*) from pizzas";
	private static final String DELETE_PIZZA = "delete from pizzas where id=:id";
	private static final String UPDATE_PIZZA = "update pizzas set naam=:naam, prijs=:prijs," +
			"pikant=:pikant where id=:id";
	
	public JdbcPizzaRepository(NamedParameterJdbcTemplate template, DataSource dataSource) {
		this.TEMPLATE = template;
		this.INSERT = new SimpleJdbcInsert(dataSource); 
		INSERT.withTableName("pizzas"); 
		INSERT.usingGeneratedKeyColumns("id");
	}

	@Override
	public void create(Pizza pizza) {
		Map<String, Object> kolomWaarden = new HashMap<>(); 
		kolomWaarden.put("naam", pizza.getNaam()); 
		kolomWaarden.put("prijs", pizza.getPrijs());
		kolomWaarden.put("pikant", pizza.isPikant());
		Number id = INSERT.executeAndReturnKey(kolomWaarden); 
		pizza.setId(id.longValue());
	}

	@Override
	public Optional<Pizza> read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Pizza pizza) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("naam", pizza.getNaam());
		parameters.put("prijs", pizza.getPrijs());
		parameters.put("pikant", pizza.isPikant());
		parameters.put("id", pizza.getId());
		if(TEMPLATE.update(UPDATE_PIZZA, parameters) == 0) {
			throw new PizzaNotFoundException();
		}
	}

	@Override
	public void delete(long id) {
		if(TEMPLATE.update( DELETE_PIZZA, Collections.singletonMap("id", id) ) == 0) {
			throw new PizzaNotFoundException();
		};
	}

	@Override
	public List<Pizza> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long findAantalPizzas() {
		return TEMPLATE.queryForObject(SELECT_AANTAL_PIZZAS, Collections.emptyMap(), Long.class);
	}

	@Override
	public List<BigDecimal> findUniekePrijzen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> findByPrijs(BigDecimal prijs) {
		// TODO Auto-generated method stub
		return null;
	}

}
