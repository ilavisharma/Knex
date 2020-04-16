package Knex;

public class KnexException extends Exception {
    String message;

    public KnexException(String message) {
        this.message= message;
    }

    @Override
    public String toString() {
        return "KnexException: " +message;
    }
}
