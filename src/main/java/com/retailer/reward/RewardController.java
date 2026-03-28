package com.retailer.reward;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
	 private final RewardService service;

	    public RewardController(RewardService service) {
	        this.service = service;
	    }

	    /**
	     * GET API to fetch reward points
	     */
	    @GetMapping
	    public List<RewardResponse> getRewards() {
	        return service.calculateRewards();
	    }

}
