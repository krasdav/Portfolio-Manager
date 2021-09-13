package org.dav.equitylookup;

import lombok.RequiredArgsConstructor;
import org.dav.equitylookup.config.BeanConfig;
import org.dav.equitylookup.model.CryptoShare;
import org.dav.equitylookup.model.Portfolio;
import org.dav.equitylookup.model.StockShare;
import org.dav.equitylookup.model.User;
import org.dav.equitylookup.model.cache.Stock;
import org.dav.equitylookup.service.CryptoService;
import org.dav.equitylookup.service.PortfolioService;
import org.dav.equitylookup.service.StockService;
import org.dav.equitylookup.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@RequiredArgsConstructor
@SpringBootApplication
@Controller
@EnableJpaRepositories("org.dav.equitylookup.repository")
public class EquityLookUpApplication {

    private final UserService userService;

    private final StockService stockService;

    private final PortfolioService portfolioService;

    private final PasswordEncoder passwordEncoder;

    private final CryptoService cryptoService;

    private final BeanConfig beanConfig;

    public static void main(String[] args) {
        SpringApplication.run(EquityLookUpApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            User michal = new User("Michal");
            michal.setRole("ROLE_USER");
            michal.setPassword(passwordEncoder.encode("pw"));
            userService.saveUser(michal);

            Stock google = stockService.getStock("GOOG");

            Stock apple = stockService.getStock("APPL");

            Stock intel = stockService.getStock("INTC");

            Portfolio portfolio = new Portfolio("Michal's Portfolio", michal);
            portfolioService.savePortfolio(portfolio);

            michal.setPortfolio(portfolio);

            StockShare stockShareGoogle = stockService.obtainShare(google.getTicker(), new BigDecimal("1599.12"), michal);

            StockShare stockShareIntel = stockService.obtainShare(intel.getTicker(), new BigDecimal("59.22"), michal);

            CryptoShare cryptoShareBTC = cryptoService.obtainCryptoShare(1,"BTC",new BigDecimal("43000"), michal);
            CryptoShare cryptoShareETH = cryptoService.obtainCryptoShare(2.32,"ETH",new BigDecimal("2500"), michal);


            portfolioService.addShare(stockShareGoogle, portfolio.getName());
            portfolioService.addShare(stockShareIntel, portfolio.getName());
            portfolioService.addCryptoShare(cryptoShareBTC, portfolio.getName());
            portfolioService.addCryptoShare(cryptoShareETH, portfolio.getName());

        };
    }

}
