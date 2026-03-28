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

        List<RewardResponse> responseList = new ArrayList<>();

        for (Long customerId : customerMap.keySet()) {

            Map<String, Integer> monthlyPoints = new HashMap<>();
            int totalPoints = 0;

            for (Transaction txn : customerMap.get(customerId)) {

                int points = RewardUtil.calculatePoints(txn.getAmount());

                String month = txn.getDate()
                        .getMonth()
                        .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

                monthlyPoints.put(month,
                        monthlyPoints.getOrDefault(month, 0) + points);

                totalPoints += points;
            }

            responseList.add(new RewardResponse(customerId, monthlyPoints, totalPoints));
        }

        return responseList;
    }

}
