package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.service.DashboardService;
import ca.jrvs.apps.trading.util.ResponseExceptionUtil;
import ca.jrvs.apps.trading.view.PortfolioView;
import ca.jrvs.apps.trading.view.TraderAccountView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @ApiOperation(value = "Show trader profile by traderId",
        notes = "Show trader and account details, traderId and accountId should be identical")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "traderId or accountId not found.")})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/profile/traderId/{traderId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public TraderAccountView getAccount(@PathVariable Integer traderId) {
        try {
            return dashboardService.getTraderAccount(traderId);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseStatusException(ex);
        }
    }

    @ApiOperation(value = "Show trader portfolio by traderId")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "traderId is not found.")})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/portfolio/traderId/{traderId}")
    public PortfolioView getPortfolioView(@PathVariable Integer traderId) {
        try {
            return dashboardService.getPortfolioViewByTraderId(traderId);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseStatusException(ex);
        }
    }
}
