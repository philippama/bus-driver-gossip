package pm.kata.busdriver;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class BusServiceTest {

    @Test
    public void busServiceEmploysDrivers() {
        BusService busService = new BusService();
        Driver driver1 = new Driver(new Integer[]{11,12,13}, "Gossip 1");
        Driver driver2 = new Driver(new Integer[]{21,22,23}, "Gossip 2");

        busService.addDriver(driver1);
        busService.addDriver(driver2);

        List<Driver> drivers = busService.getDrivers();

        assertEquals(2, drivers.size());
        assertTrue(drivers.contains(driver1));
        assertTrue(drivers.contains(driver2));
    }

    @Test
    public void busServiceMovesDrivers() {
        BusService busService = new BusService();
        Driver driver1 = new Driver(new Integer[]{11,12,13}, "Gossip 1");
        Driver driver2 = new Driver(new Integer[]{21,22,23}, "Gossip 2");

        busService.addDriver(driver1);
        busService.addDriver(driver2);

        busService.moveDrivers();

        List<Driver> drivers = busService.getDrivers();
        assertEquals((Integer)11, drivers.get(0).getCurrentStop());
        assertEquals((Integer)21, drivers.get(1).getCurrentStop());

        busService.moveDrivers();

        drivers = busService.getDrivers();
        assertEquals((Integer)12, drivers.get(0).getCurrentStop());
        assertEquals((Integer)22, drivers.get(1).getCurrentStop());

        busService.moveDrivers();

        drivers = busService.getDrivers();
        assertEquals((Integer)13, drivers.get(0).getCurrentStop());
        assertEquals((Integer)23, drivers.get(1).getCurrentStop());
    }

    @Test
    public void driversMove2Times() {
        BusService busService = new BusService();
        Driver driver1 = new Driver(new Integer[]{11,12,13}, "Gossip 1");
        Driver driver2 = new Driver(new Integer[]{21,22,23}, "Gossip 2");

        busService.addDriver(driver1);
        busService.addDriver(driver2);

        int maxMoves = 2;

        int numberOfMoves = busService.moveDriversForShift(maxMoves);

        assertEquals(maxMoves, numberOfMoves);

        List<Driver> drivers = busService.getDrivers();
        assertEquals((Integer)12, drivers.get(0).getCurrentStop());
        assertEquals((Integer)22, drivers.get(1).getCurrentStop());
    }

    @Test
    public void driversMove3Times() {
        BusService busService = new BusService();
        Driver driver1 = new Driver(new Integer[]{11,12,13}, "Gossip 1");
        Driver driver2 = new Driver(new Integer[]{21,22,23}, "Gossip 2");

        busService.addDriver(driver1);
        busService.addDriver(driver2);

        int maxMoves = 3;

        int numberOfMoves = busService.moveDriversForShift(maxMoves);

        assertEquals(maxMoves, numberOfMoves);

        List<Driver> drivers = busService.getDrivers();
        assertEquals((Integer)13, drivers.get(0).getCurrentStop());
        assertEquals((Integer)23, drivers.get(1).getCurrentStop());
    }

    @Test
    public void driversExchangeGossipWhenAtSameStop() {
        BusService busService = new BusService();
        String initialGossip1 = "Gossip 1";
        String initialGossip21 = "Gossip 21";
        String initialGossip22 = "Gossip 22";
        Driver driver1 = new Driver(new Integer[]{1}, initialGossip1);
        Driver driver2 = new Driver(new Integer[]{1}, initialGossip21);
        driver2.addGossip(initialGossip22);

        busService.addDriver(driver1);
        busService.addDriver(driver2);

        busService.moveDrivers();

        List<Driver> drivers = busService.getDrivers();
        drivers.forEach(driver -> {
            Set<String> allGossip = driver.getAllGossip();
            assertEquals(3, allGossip.size());
            assertTrue(allGossip.contains(initialGossip1));
            assertTrue(allGossip.contains(initialGossip21));
            assertTrue(allGossip.contains(initialGossip22));
        });
    }

    @Test
    public void driversDoNotExchangeGossipWhenNotAtSameStop() {
        BusService busService = new BusService();
        String initialGossip1 = "Gossip 1";
        String initialGossip2 = "Gossip 2";
        Driver driver1 = new Driver(new Integer[]{1}, initialGossip1);
        Driver driver2 = new Driver(new Integer[]{2}, initialGossip2);

        busService.addDriver(driver1);
        busService.addDriver(driver2);

        busService.moveDrivers();

        List<Driver> drivers = busService.getDrivers();

        Set<String> allGossip = drivers.get(0).getAllGossip();
        assertEquals(1, allGossip.size());
        assertTrue(allGossip.contains(initialGossip1));

        allGossip = drivers.get(1).getAllGossip();
        assertEquals(1, allGossip.size());
        assertTrue(allGossip.contains(initialGossip2));
    }

    @Test
    public void driversMoveUntilAllGossipExchanged() {
        BusService busService = new BusService();
        String initialGossip1 = "Gossip 1";
        String initialGossip2 = "Gossip 2";
        Driver driver1 = new Driver(new Integer[]{1, 2, 3}, initialGossip1);
        Driver driver2 = new Driver(new Integer[]{4, 1}, initialGossip2);

        busService.addDriver(driver1);
        busService.addDriver(driver2);

        int numberOfMoves = busService.moveDriversForShift(100);

        assertEquals(3, numberOfMoves);
        List<Driver> drivers = busService.getDrivers();
        drivers.forEach(driver -> {
            Set<String> allGossip = driver.getAllGossip();
            assertEquals(2, allGossip.size());
            assertTrue(allGossip.contains(initialGossip1));
            assertTrue(allGossip.contains(initialGossip2));
        });
    }

    @Test
    public void countsStopsUntilAllGossipIsExchanged() {
        fail("Not yet written");
    }

    @Test
    public void returnsNeverIfNotAllGossipIsExchangedWithinMaxMoves() {
        fail("Not yet written");
    }
}
