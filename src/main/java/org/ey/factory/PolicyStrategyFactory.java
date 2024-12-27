package org.ey.factory;

import org.ey.strategy.GreaterOrEqualStrategy;
import org.ey.strategy.GreaterThanStrategy;
import org.ey.strategy.LessOrEqualStrategy;
import org.ey.strategy.PolicyStrategy;

public class PolicyStrategyFactory {
    public static PolicyStrategy getStrategy(String comparator) {
        return switch (comparator.trim().toLowerCase()) {
            case "greater_than" -> new GreaterThanStrategy();
            case "greater_or_equal" -> new GreaterOrEqualStrategy();
            case "less_or_equal" -> new LessOrEqualStrategy();
            default -> throw new IllegalArgumentException("Unknown comparator: " + comparator);
        };
    }
}
