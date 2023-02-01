package pl.kurs.programmingschool.exceptions.model;


public class WrongIdException extends RuntimeException{
    public WrongIdException(String message) {
        super(message);
    }
}
