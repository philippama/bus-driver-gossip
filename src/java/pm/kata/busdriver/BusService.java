package pm.kata.busdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BusService {

    private List<Driver> drivers = new ArrayList<>();

    void addDriver(Driver driver) {
        drivers.add(driver);
    }

    List<Driver> getDrivers() {
        return drivers;
    }

    void moveDrivers() {
        drivers.forEach(Driver::goToNextStop);
        drivers.forEach(driver ->
                drivers.forEach(otherDriver -> {
                    if ((otherDriver != driver) && (Objects.equals(otherDriver.getCurrentStop(), driver.getCurrentStop()))) {
                        driver.addGossip(otherDriver.getAllGossip());
                    }
                })
        );
    }

    public int moveDriversForShift(int maxMoves) {
        int numMoves = 0;
        for ( ; numMoves < maxMoves; numMoves++) {
            moveDrivers();
            if (allGossipExchanged()) {
                break;
            }
        }
        return numMoves;
    }

    private boolean allGossipExchanged() {
        Optional<Boolean> isExchanged = drivers.stream()
                .map(driver -> driver.getAllGossip().size() == drivers.size())
                .reduce((d1, d2) -> d1 && d2);
        ;
        return isExchanged.orElse(Boolean.TRUE);
    }
}
