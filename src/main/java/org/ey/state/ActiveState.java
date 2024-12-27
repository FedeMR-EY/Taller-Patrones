package org.ey.state;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class ActiveState implements PortfolioState {
    @Override
    public PortfolioStatus getStatus() {
        return PortfolioStatus.ACTIVE;
    }

    @Override
    public PortfolioState handleEvent(ResolutionEvent event) {
        return switch (event) {
            case BEAR, MARKET_COLLAPSE -> new EmptyState();
            case OUT_OF_INVESTORS, DEBT_DEFAULT -> new DefensiveState();
            case EXTREME_RISK -> new ClosedState(); //Primera REGLA: Listo.
            case BULL -> new VipState();
            default -> this;
        };
    }
}