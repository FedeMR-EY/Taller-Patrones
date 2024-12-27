package org.ey.state;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class VipState implements PortfolioState {
    @Override
    public PortfolioStatus getStatus() {
        return PortfolioStatus.VIP;
    }

    @Override
    public PortfolioState handleEvent(ResolutionEvent event) {
        return switch (event) {
            case MARKET_COLLAPSE, EXTREME_RISK -> new ClosedState();
            case DEBT_DEFAULT,BULL -> new DefensiveState();
            case BEAR -> new EmptyState();
            default -> this;
        };
    }
}
