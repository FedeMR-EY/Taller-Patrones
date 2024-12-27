package org.ey.state;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class EmptyState implements PortfolioState {
    @Override
    public PortfolioStatus getStatus() {
        return PortfolioStatus.EMPTY;
    }

    @Override
    public PortfolioState handleEvent(ResolutionEvent event) {
        return switch (event) {
            case BULL -> new ActiveState();
            case BEAR, MARKET_COLLAPSE -> new EmptyState();
            case DEBT_DEFAULT -> new DefensiveState();
            case EXTREME_RISK -> new ClosedState();
            default -> this;
        };
    }
}