package kata.babysitter.base;

import kata.babysitter.exception.BabysitterException;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppStart {

    public static void main(String args[]) {
        LocalDateTime startTime = LocalDateTime.of(2020, 7, 20,22,30);
        LocalDateTime bedTime = LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(23,0));
        LocalDateTime endTime = LocalDateTime.of(startTime.toLocalDate().plusDays(1), LocalTime.of(2,0));
        try {
            Babysitter babysitter = new Babysitter(startTime, endTime, bedTime);
            System.out.println("TOTAL PAY: "+babysitter.calculateTotalPay());
        } catch(BabysitterException e) {
            System.out.println(e.toString());
        }
    }

}
