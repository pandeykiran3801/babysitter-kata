package kata.babysitter.unittest;

import kata.babysitter.base.Babysitter;
import kata.babysitter.exception.BabysitterException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BabySitterTest {

    @Test
    public void validateTotalPayPositiveScenario() {
        LocalDateTime startTime = LocalDateTime.of(2020, 07, 20,22,00);
        LocalDateTime bedTime = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(23,0));
        LocalDateTime endTime = LocalDateTime.of(startTime.toLocalDate().plusDays(1), LocalTime.of(2,0));
        try {
            Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);
            double pay = babysitter.calculateTotalPay();
            Assert.assertEquals(pay, 52, "Pay did not match!");
        } catch(BabysitterException e) {
            Assert.assertFalse(false, "Invalid input data.");
        }
    }

    @Test
    public void validateInvalidStartTimeScenario() {
        LocalDateTime startTime = LocalDateTime.of(2020, 07, 20,14,00);
        LocalDateTime bedTime = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(23,0));
        LocalDateTime endTime = LocalDateTime.of(startTime.toLocalDate().plusDays(1), LocalTime.of(2,0));
        try {
            new Babysitter(startTime, endTime, bedTime);
            Assert.assertTrue(false, "Didn't throw exception!");
        } catch(BabysitterException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void validateInvalidEndTimeScenario() {
        LocalDateTime startTime = LocalDateTime.of(2020, 07, 20,19,00);
        LocalDateTime bedTime = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(23,0));
        LocalDateTime endTime = LocalDateTime.of(startTime.toLocalDate().plusDays(1), LocalTime.of(10,0));
        try {
            new Babysitter(startTime, endTime, bedTime);
            Assert.assertTrue(false, "Didn't throw exception!");
        } catch(BabysitterException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void validateCalculateHrsPositiveScenario() {
        LocalDateTime startTime = LocalDateTime.of(2020, 07, 20,22,00);
        LocalDateTime bedTime = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(23,0));
        LocalDateTime endTime = LocalDateTime.of(startTime.toLocalDate().plusDays(1), LocalTime.of(2,0));
        try {
            Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);
            double hrs = babysitter.calculateHrs(startTime, bedTime);
            Assert.assertEquals(hrs, 1, "Start time to bedtime Hours did not match!");

            hrs = babysitter.calculateHrs(bedTime, babysitter.getMidnight());
            Assert.assertEquals(hrs, 1, "Bed time to midnight Hours did not match!");

            hrs = babysitter.calculateHrs(babysitter.getMidnight(), endTime);
            Assert.assertEquals(hrs, 2, "Midnight to end time Hours did not match!");

        } catch(BabysitterException e) {
            Assert.assertFalse(false, "Invalid input data.");
        }
    }
}
