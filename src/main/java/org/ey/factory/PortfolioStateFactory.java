package org.ey.factory;

import org.ey.enums.PortfolioStatus;
import org.ey.state.*;

public class PortfolioStateFactory {
    public static PortfolioState getState(PortfolioStatus status) {
        return switch (status) {
            case CLOSED -> new ClosedState();
            case EMPTY -> new EmptyState();
            case DEFENSIVE -> new DefensiveState();
            case ACTIVE -> new ActiveState();
            case VIP -> new VipState();
        };
    }
}