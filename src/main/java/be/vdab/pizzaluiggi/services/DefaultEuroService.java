package be.vdab.pizzaluiggi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import be.vdab.pizzaluiggi.exceptions.KoersClientException;
import be.vdab.pizzaluiggi.restclients.KoersClient;

@Service
class DefaultEuroService implements EuroService {
	private final KoersClient[] koersClients;
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEuroService.class);

	DefaultEuroService(KoersClient[] koersClients) {
		this.koersClients = koersClients;
	}
	
	public BigDecimal naarDollar(BigDecimal euro) {
		for (KoersClient koersClient : koersClients) { 
			try {
				return euro.multiply(koersClient.getDollarKoers()).setScale(2, RoundingMode.HALF_UP);
			} catch (KoersClientException ex) {
			  LOGGER.error("kan dollar koers niet lezen", ex);
			}
		}
		LOGGER.error("kan dollar koers van geen enkele bron lezen"); 
		return null;
	}
}
