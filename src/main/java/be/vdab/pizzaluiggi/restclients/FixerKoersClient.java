package be.vdab.pizzaluiggi.restclients;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import be.vdab.pizzaluiggi.exceptions.KoersClientException;

@Component
@Order(1)
class FixerKoersClient implements KoersClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(FixerKoersClient.class);
	private final URL url;
	
	public FixerKoersClient() {
		try {
			url = new URL("http://data.fixer.io/api/latest?access_key=3d0a4fecd7d5a53aa66487323c7dc519&symbols=USD");
		} catch (MalformedURLException ex) {
			String fout = "Fixer URL is verkeerd.";
			LOGGER.error(fout, ex); 
			throw new KoersClientException(fout);
		}
	}	
	@Override
	public BigDecimal getDollarKoers() {
		try (Scanner scanner = new Scanner(url.openStream())) {
			//{"success":true,"timestamp":1535786954,"base":"EUR","date":"2018-09-01","rates":{"USD":1.162149}}
			String lijn = scanner.nextLine();
			int beginPositieKoers = lijn.indexOf("USD") + 5;
			int accoladePositie = lijn.indexOf('}', beginPositieKoers);
			LOGGER.info("koers gelezen via Fixer");
			return new BigDecimal(lijn.substring(beginPositieKoers, accoladePositie));
		} catch (IOException | NumberFormatException ex) {
			String fout = "kan koers niet lezen via Fixer";
			LOGGER.error(fout, ex);
			throw new KoersClientException(fout);
		}
	}
	
}
