package org.ey.strategy;

import org.ey.enums.ResolutionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LessOrEqualStrategy implements PolicyStrategy {
    @Override
    public void apply(Map<String, String> movement, ArrayList<ResolutionEvent> allEvents, double compareToValue, List<ResolutionEvent> policyEvents) {
        double amount = Double.parseDouble(movement.get("amount"));
        if (amount <= compareToValue) {
            System.out.println("Eventos a quitar: "+policyEvents);
            for (var event : policyEvents) {
                allEvents.remove(event);
            }
        }
    }
}