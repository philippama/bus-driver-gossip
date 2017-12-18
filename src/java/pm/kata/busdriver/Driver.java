package pm.kata.busdriver;

import java.util.HashSet;
import java.util.Set;

public class Driver {

    private Integer[] route;
    private Set<String> gossip = new HashSet<String>();
    private int currentStopIndex = -1;

    public Driver(Integer[] route, String initialGossip) {

        this.route = route;
        addGossip(initialGossip);
    }

    public Integer getCurrentStop() {
        if (currentStopIndex < 0) {
            return 0;
        }
        return route[currentStopIndex];
    }

    void goToNextStop() {
        currentStopIndex++;
        if (currentStopIndex >= route.length) {
            currentStopIndex = 0;
        }
    }

    void addGossip(String gossip) {
        this.gossip.add(gossip);
    }

    void addGossip(Set<String> gossip) {
        this.gossip.addAll(gossip);
    }

    public Set<String> getAllGossip() {
        return gossip;
    }
}
