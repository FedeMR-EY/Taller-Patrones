package org.ey.factory;

import org.ey.enums.ResolutionEvent;

import java.util.ArrayList;
import java.util.List;

public class ResolutionEventFactory {
    public static List<ResolutionEvent> getEvents(List<String> rawEvents) {
        List<ResolutionEvent> events = new ArrayList<>();

        for (var rawEvent : rawEvents) {
            switch (rawEvent) {
                case "EXTREME_RISK" -> {
                    events.add(ResolutionEvent.EXTREME_RISK);
                }
                case "BULL" -> {
                    events.add(ResolutionEvent.BULL);
                }
                case "BEAR" -> {
                    events.add(ResolutionEvent.BEAR);
                }
                case "DEBT_DEFAULT" -> {
                    events.add(ResolutionEvent.DEBT_DEFAULT);
                }
                case "MARKET_COLLAPSE" -> {
                    events.add(ResolutionEvent.MARKET_COLLAPSE);
                }
                case "AUDIT_RISK" -> {
                    events.add(ResolutionEvent.AUDIT_RISK);
                }
                case "OUT_OF_INVESTORS" -> {
                    events.add(ResolutionEvent.OUT_OF_INVESTORS);
                }
                default -> throw new IllegalStateException("Unexpected value: " + rawEvent);
            };
        }
        return events;
    }
}