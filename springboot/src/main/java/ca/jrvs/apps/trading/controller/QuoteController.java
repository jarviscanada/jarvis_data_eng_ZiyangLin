package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
import ca.jrvs.apps.trading.util.ResponseExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RequestMapping to set the root path for all endpoints in this controller.
@Api(value = "quote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Controller
@RequestMapping("/quote")
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @ApiOperation(value = "Show IexQuote", notes = "Show IexQuote for a given ticker/symbol.")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "ticker is not found in the IEX system.")})
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

    @ApiOperation(value = "Update quote table using IEX data",
            notes = "Update all quotes in the quote table, using IEX trading API as market data source.")
    @PutMapping(path = "/iexMarketData")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Quote> updateMarketData() {
        try {
            return quoteService.updateMarketData();
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseStatusException(ex);
        }
    }

    @ApiOperation(value = "Update a given quote in the quote table",
            notes = "Manually update a quote in the quote table using the IEX market data.")
    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Quote putQuote(@RequestBody Quote quote) {
        try {
            return quoteService.saveQuote(quote);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseStatusException(ex);
        }
    }


    @ApiOperation(value = "Update a given quote in the quote table",
            notes = "Add a new ticker/symbol to the quote table, so trader can trade this security.")
    @PostMapping(path = "/tickerId/{tickerId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "ticker is not found in the IEX system.")})
    @ResponseBody
    public Quote createQuote(@PathVariable String tickerId) {
        try {
            return quoteService.saveQuote(tickerId);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseStatusException(ex);
        }
    }

    @ApiOperation(value = "Show the daily list",
            notes = "Showing daily list for this trading system.")
    @GetMapping(path = "/dailyList")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Quote> getDailyList() {
        try {
            return quoteService.findAllQuotes();
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseStatusException(ex);
        }
    }
}
