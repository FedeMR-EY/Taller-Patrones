package org.ey.state;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class ClosedState implements PortfolioState {
    @Override
    public PortfolioStatus getStatus() {
        return PortfolioStatus.CLOSED;
    }

    @Override
    public PortfolioState handleEvent(ResolutionEvent event) {
        // Las carteras en CLOSED no cambian de estado.
        return this;
    }
}