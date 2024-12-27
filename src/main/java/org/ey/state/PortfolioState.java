package org.ey.state;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public interface PortfolioState {
    PortfolioStatus getStatus(); // Devuelve el estado actual de la cartera.

    PortfolioState handleEvent(ResolutionEvent event); // Define cómo manejar el evento y retornar el nuevo estado.
}