package model.winning;

import java.util.Map;

public class WinningResult {
    private final Map<Rank, Integer> statistics;

    public WinningResult(final Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }

    public Double getRateOfReturn(final int money) {
        double totalReturn = calculateTotalReturn();
        return totalReturn / (double) money;
    }

    private int calculateTotalReturn() {
        return statistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}