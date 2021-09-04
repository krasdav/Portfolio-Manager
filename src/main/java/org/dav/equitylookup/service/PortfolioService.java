package org.dav.equitylookup.service;

import org.dav.equitylookup.exceptions.PortfolioNotFoundException;
import org.dav.equitylookup.exceptions.ShareNotFoundException;
import org.dav.equitylookup.model.Coin;
import org.dav.equitylookup.model.Portfolio;
import org.dav.equitylookup.model.Share;
import org.dav.equitylookup.model.dto.PortfolioDTO;

import java.io.IOException;
import java.util.List;

public interface PortfolioService {

    void savePortfolio(Portfolio portfolio);

    List<Portfolio> getAllPortfolios();

    Portfolio getPortfolioById(long id);

    Portfolio getPortfolioByName(String name) throws PortfolioNotFoundException;

    void addShare(Share share, String portfolioName) throws PortfolioNotFoundException;

    void removeShare(Share share, String portfolioName) throws PortfolioNotFoundException;

    Share getShareById(long id, String portfolionName) throws PortfolioNotFoundException, ShareNotFoundException;

    void removeShareById(long id, String portfolioName) throws PortfolioNotFoundException, ShareNotFoundException;

    void addAnalysisDetails(PortfolioDTO portfolioDTO) throws IOException;

    void addCoin(Coin coin, String portfolioName)throws PortfolioNotFoundException;

    void removeCoinById(long id, String portfolioName) throws PortfolioNotFoundException, ShareNotFoundException;

    void removeCoin(Coin coin, String portfolioName) throws PortfolioNotFoundException;

    Coin getCoinById(long id, String portfolionName) throws PortfolioNotFoundException, ShareNotFoundException;
}
