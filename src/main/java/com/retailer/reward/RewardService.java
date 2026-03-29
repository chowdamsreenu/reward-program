package com.retailer.reward;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class RewardService {
	private final TransactionRepository repository;

    public RewardService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<RewardResponse> calculateRewards() {

        List<Transaction> transactions = repository.getAllTransactions();

        // Group by customer
        Map<Long, List<Transaction>> customerMap =
                transactions.stream()
                        .collect(Collectors.groupingBy(Transaction::getCustomerId));
// 101 → [txn1, txn2, txn5]
//	102 → [txn3, txn4]
        List<RewardResponse> responseList = new ArrayList<>();
// Loop Each Transaction
        for (Long customerId : customerMap.keySet()) {

            Map<String, Integer> monthlyPoints = new HashMap<>();
            int totalPoints = 0;

            for (Transaction txn : customerMap.get(customerId)) {
//Calculate Points
                int points = RewardUtil.calculatePoints(txn.getAmount());
//Extract Month
                String month = txn.getDate()
                        .getMonth()
                        .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
 // Store Monthly Points
                monthlyPoints.put(month,
                        monthlyPoints.getOrDefault(month, 0) + points);
 // Add Total
                totalPoints += points;
            }

            responseList.add(new RewardResponse(customerId, monthlyPoints, totalPoints));
        }

        return responseList;
    }

}
