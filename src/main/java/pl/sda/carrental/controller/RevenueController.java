package pl.sda.carrental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.Revenue;
import pl.sda.carrental.service.RevenueService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/revenue")
public class RevenueController {
    private final RevenueService revenueService;

    @GetMapping
    public List<Revenue> getRevenue() {
        return revenueService.getRevenue();
    }

    @PostMapping
    public Revenue addRevenue(@RequestBody Revenue revenue) {
        return revenueService.addRevenue(revenue);
    }

    @PutMapping
    public Revenue editRevenue(@RequestBody Revenue revenue) {
        return revenueService.editRevenue(revenue);
    }

    @DeleteMapping
    public void deleteRevenue() {
        revenueService.deleteRevenue();
    }
}
