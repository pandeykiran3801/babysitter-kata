package kata.babysitter.exception;

import kata.babysitter.base.Babysitter;

public class BabysitterException extends Exception {

    public BabysitterException(String message) {
        super("BabysitterException: " + message);
    }

}
