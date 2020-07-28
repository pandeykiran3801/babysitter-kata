package kata.babysitter.base;

import kata.babysitter.constants.Pay;
import kata.babysitter.exception.BabysitterException;

import java.time.*;

public class Babysitter {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime bedTime;

    public Babysitter(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime bedTime) throws BabysitterException {
        setStartTime(startTime);
        setEndTime(endTime);
        setBedTime(bedTime);
    }

    private boolean isStartTimeValid(LocalDateTime startTime) {
        LocalDateTime actualStartTime = LocalDate.of(startTime.getYear(), startTime.getMonth(),startTime.getDayOfMonth()).atTime(LocalTime.of(17, 0));
        return startTime.isAfter(actualStartTime);
    }

    private boolean isEndTimeValid(LocalDateTime endTime) {
        LocalDateTime actualEndTime = LocalDate.of(this.startTime.getYear(), this.startTime.getMonth(), this.startTime.getDayOfMonth()).plusDays(1).atTime(LocalTime.of(4, 0,1));
        return endTime.isBefore(actualEndTime);
    }

    public double calculateTotalPay(){
        double startTimeToBedTime = calculateHrs(this.startTime, this.bedTime) * Pay.START_TO_BEDTIME;
        double bedTimeToMidnight = calculateHrs(this.bedTime, getMidnight()) * Pay.BEDTIME_TO_MIDNIGHT;
        double midnightToEnd = calculateHrs(getMidnight(), this.endTime) * Pay.MIDNIGHT_TO_END;
        return startTimeToBedTime + bedTimeToMidnight + midnightToEnd;
    }

    public double calculateHrs(LocalDateTime start, LocalDateTime end){
        Duration duration = Duration.between(start, end);
        long hours = duration.toHours();
        double minutes = (duration.toMinutes() - (hours * 60))/60.0;
        double total = hours+minutes;
        if(total < 0) return 0;
        return total;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getMidnight() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        return LocalDateTime.of(startTime.toLocalDate().plusDays(1), midnight);
    }

    public void setStartTime(LocalDateTime startTime) throws BabysitterException {
        if(!isStartTimeValid(startTime)) throw new BabysitterException("Start time not valid!");
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) throws BabysitterException {
        if(!isEndTimeValid(endTime)) throw new BabysitterException("End time not valid!");
        this.endTime = endTime;
    }

    public LocalDateTime getBedTime() {
        return bedTime;
    }

    public void setBedTime(LocalDateTime bedTime) {
        this.bedTime = bedTime;
    }
}
