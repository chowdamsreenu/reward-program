package com.retailer.reward;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class RewardUtilTest {	
	  @Test
	    void testRewardAbove100() {
	        assertEquals(90, RewardUtil.calculatePoints(120));
	    }

	    @Test
	    void testRewardBetween50And100() {
	        assertEquals(20, RewardUtil.calculatePoints(70));
	    }

	    @Test
	    void testRewardBelow50() {
	        assertEquals(0, RewardUtil.calculatePoints(40));
	    }

}
