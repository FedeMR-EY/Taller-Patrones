package org.ey;

import org.ey.dao.PortfolioDAO;
import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;
import org.ey.factory.PolicyStrategyFactory;
import org.ey.factory.PortfolioStateFactory;
import org.ey.factory.ResolutionEventFactory;
import org.ey.state.ClosedState;
import org.ey.state.PortfolioState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolicyProcessor {
    final PortfolioDAO dao;
    boolean useSimplePolicies;

    public PolicyProcessor(final PortfolioDAO dao, boolean useSimplePolicies){
        this.dao = dao;
        this.useSimplePolicies = useSimplePolicies;
    }

    public final PortfolioDAO getDao() {
        return dao;
    }

    public void setUseSimplePolicies(boolean flag) {
        this.useSimplePolicies = flag;
    }

    // ### EJEMPLO. EN LOS TEST SE USARÁ EL METODO "process" ###
    public void processEjemplo(List<Map<String, Object>> policies, List<Map<String, String>> movements){
        System.out.println(movements);
        System.out.println(policies);

        movements.forEach(
                movement -> {
                    var id = movement.get("carteraId");
                    var oldStatus = dao.getPortfolioStatus(Long.parseLong(id));
                    var newHardcodedStatus =  PortfolioStatus.VIP;
                    //Ejemplo. El estado nuevo debe ser el resultado de procesar reglas de políticas.
                    System.out.println("Cartera Id: " + id +
                            ", Status Viejo: " + oldStatus + ", Status Nuevo: " + newHardcodedStatus);
                }
        );

    }

    public void process(List<Map<String, Object>> policies, List<Map<String, String>> movements) {

        movements.forEach(movement -> {

            System.out.println(movement);

            // Obtener lista inicial de eventos
            var allEvents = new ArrayList<>(ResolutionEvent.getAllEventsForProcess());

            System.out.println("Lista de eventos completa: "+allEvents);

            // Aplicar todas las políticas
            for (Map<String, Object> policy : policies) {
                var comparator = (String) policy.get("comparator");
                var compareToValue = Double.parseDouble((String) policy.get("compareToValue"));
                var policyRawEvents = (List<String>) policy.get("events");
                var policyEvents = ResolutionEventFactory.getEvents(policyRawEvents);
                var strategy = PolicyStrategyFactory.getStrategy(comparator);

                strategy.apply(movement, allEvents, compareToValue, policyEvents);
            }

            System.out.println("Lista de eventos mod: "+allEvents);

            // Determinar el evento final
            var resolvedEvent = allEvents.isEmpty() ? null : allEvents.get(0);

            System.out.println("Evento final: "+resolvedEvent);

            // Obtener el estado actual de la cartera
            var portfolioId = Long.parseLong(movement.get("carteraId"));
            var currentStatus = dao.getPortfolioStatus(portfolioId);

           System.out.println("Estado actual cartera: "+currentStatus);

            // Crear el estado actual usando la fábrica
            var currentState = PortfolioStateFactory.getState(currentStatus);

            // Resolver el nuevo estado
            var newState = resolvedEvent != null
                    ? currentState.handleEvent(resolvedEvent)
                    : currentState;

            System.out.println("Nuevo estado cartera: "+newState.getStatus());
            System.out.println("\n");

            // Actualizar estado
            dao.savePortfolioStatus(portfolioId, newState.getStatus());
        });
    }
}
