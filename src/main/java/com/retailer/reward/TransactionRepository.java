package com.retailer.reward;

import java.util.List;
import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Repository;
@Repository
public class TransactionRepository {
	 public List<Transaction> getAllTransactions() {

	        return Arrays.asList(
	            new Transaction(1L, 101L, 120, LocalDate.now().minusMonths(1)),
	            new Transaction(2L, 101L, 75, LocalDate.now().minusMonths(2)),
	            new Transaction(3L, 102L, 200, LocalDate.now().minusMonths(1)),
	            new Transaction(4L, 102L, 40, LocalDate.now().minusMonths(3)),
	            new Transaction(5L, 101L, 130, LocalDate.now().minusMonths(3))
	        );
	    }

}
