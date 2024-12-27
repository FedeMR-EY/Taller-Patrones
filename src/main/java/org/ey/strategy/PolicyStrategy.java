package org.ey.strategy;

import org.ey.enums.ResolutionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PolicyStrategy {
    void apply(Map<String, String> movement, ArrayList<ResolutionEvent> allEvents, double compareToValue, List<ResolutionEvent> policyEvents);
}