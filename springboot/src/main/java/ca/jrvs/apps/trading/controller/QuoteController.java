package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.service.QuoteService;
import ca.jrvs.apps.trading.util.ResponseExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @RequestMapping to set the root path for all endpoints in this controller.
@Controller
@RequestMapping("/quote")
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(path = "/iex/ticker/{ticker}") // indicate HTTP verb and resource path, {ticker} is user input
    @ResponseStatus(HttpStatus.OK) // expected HTTP status
    @ResponseBody // Springboot will auto-convert the IexQuote object into a JSON string
    public IexQuote getQuote(@PathVariable String ticker) {
        // @PathVariable to cooperate with @GetMapping
        try {
            return quoteService.findIexQuoteByTicker(ticker);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseStatusException(ex);
        }
    }

}
