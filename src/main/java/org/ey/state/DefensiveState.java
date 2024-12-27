package org.ey.state;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class DefensiveState implements PortfolioState {
    @Override
    public PortfolioStatus getStatus() {
        return PortfolioStatus.DEFENSIVE;
    }

    @Override
    public PortfolioState handleEvent(ResolutionEvent event) {
        return switch (event) {
            case BULL -> new ActiveState();
            case BEAR, DEBT_DEFAULT, MARKET_COLLAPSE -> new EmptyState();
            case OUT_OF_INVESTORS,EXTREME_RISK -> new ClosedState();
            default -> this;
        };
    }
}
