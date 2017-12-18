package pm.kata.busdriver;

import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DriverTest {

    @Test
    public void driverTravelsRouteRepeatedly() {
        Integer[] route = {1,2,4};
        Driver driver = new Driver(route, "");
        assertEquals(new Integer(0), driver.getCurrentStop());

        driver.goToNextStop();
        assertEquals(new Integer(1), driver.getCurrentStop());

        driver.goToNextStop();
        assertEquals(new Integer(2), driver.getCurrentStop());

        driver.goToNextStop();
        assertEquals(new Integer(4), driver.getCurrentStop());

        driver.goToNextStop();
        assertEquals(new Integer(1), driver.getCurrentStop());

        driver.goToNextStop();
        assertEquals(new Integer(2), driver.getCurrentStop());

        driver.goToNextStop();
        assertEquals(new Integer(4), driver.getCurrentStop());
    }

    @Test
    public void driverHasGossip() {
        String gossip = "Obama is pregnant";
        Driver driver = new Driver(new Integer[]{1,2}, gossip);

        Set<String> driverGossip = driver.getAllGossip();

        assertEquals(1, driverGossip.size());
        assertTrue(driverGossip.contains(gossip));
    }

    @Test
    public void driverGetsOtherDriversGossip() {
        String gossip1_1 = "Obama is pregnant";
        String gossip1_2 = "Wednesdays are being cancelled";
        String gossip2_1 = "More";

        Driver driver1 = new Driver(new Integer[]{1,2}, gossip1_1);
        driver1.addGossip(gossip1_2);

        Driver driver2 = new Driver(new Integer[]{1,2}, gossip2_1);

        driver2.addGossip(driver1.getAllGossip());

        Set<String> driverGossip = driver2.getAllGossip();

        assertEquals(3, driverGossip.size());
        assertTrue(driverGossip.contains(gossip1_1));
        assertTrue(driverGossip.contains(gossip1_2));
        assertTrue(driverGossip.contains(gossip2_1));
    }


}