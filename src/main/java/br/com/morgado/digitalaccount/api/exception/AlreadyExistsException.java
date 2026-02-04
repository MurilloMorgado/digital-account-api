package br.com.morgado.digitalaccount.api.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message){
        super(message);
    }
    
}
