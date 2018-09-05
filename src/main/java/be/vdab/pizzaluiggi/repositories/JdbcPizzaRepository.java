package be.vdab.pizzaluiggi.repositories;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.pizzaluiggi.entities.Pizza;
import be.vdab.pizzaluiggi.exceptions.PizzaNotFoundException;

@Repository
public class JdbcPizzaRepository implements PizzaRepository {

	private final NamedParameterJdbcTemplate TEMPLATE;
	private final SimpleJdbcInsert INSERT;
	
	private final RowMapper<Pizza> PIZZA_ROW_MAPPER = (resultSet, rowNum) -> 
		new Pizza(resultSet.getLong("id"), resultSet.getString("naam"),
				resultSet.getBigDecimal("prijs"),
				resultSet.getBoolean("pikant"));
	private final RowMapper<BigDecimal> PRIJZEN_ROW_MAPPER = (resultSet, rowNum) ->
		resultSet.getBigDecimal("prijs");

	private static final String SELECT_AANTAL_PIZZAS = "select count(*) from pizzas";
	private static final String DELETE_PIZZA = "delete from pizzas where id=:id";
	private static final String UPDATE_PIZZA = "update pizzas set naam=:naam, prijs=:prijs," +
			"pikant=:pikant where id=:id";
	private static final String SELECT_ALL = "select id, naam, prijs, pikant from pizzas order by id";
	private static final String SELECT_BY_PRIJS_BETWEEN = "select id, naam, prijs, pikant from pizzas"
			+ " where prijs between :van and :tot"
			+ " order by prijs";
	private static final String READ = "select id, naam, prijs, pikant from pizzas where id=:id";
	private static final String SELECT_UNIEKE_PRIJZEN = "select distinct prijs from pizzas order by prijs";
	private static final String SELECT_BY_PRIJS = "select id, naam, prijs, pikant from pizzas where prijs= :prijs";
	
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
		try{
			return Optional.of(TEMPLATE.queryForObject(READ,
					Collections.singletonMap("id",  id), PIZZA_ROW_MAPPER));
		}catch(IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
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
		return TEMPLATE.query(SELECT_ALL, PIZZA_ROW_MAPPER);
	}
	
	@Override
	public List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("van", van);
		parameters.put("tot", tot);
		return TEMPLATE.query(SELECT_BY_PRIJS_BETWEEN, parameters, PIZZA_ROW_MAPPER);
	}

	@Override
	public long findAantalPizzas() {
		return TEMPLATE.queryForObject(SELECT_AANTAL_PIZZAS, Collections.emptyMap(), Long.class);
	}

	@Override
	public List<BigDecimal> findUniekePrijzen() {
		return TEMPLATE.query(SELECT_UNIEKE_PRIJZEN, PRIJZEN_ROW_MAPPER);
	}

	@Override
	public List<Pizza> findByPrijs(BigDecimal prijs) {
		return TEMPLATE.query(SELECT_BY_PRIJS, Collections.singletonMap("prijs", prijs), PIZZA_ROW_MAPPER);
	}

}
