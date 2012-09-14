package br.com.scrum.util.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 5325374947957436790L;
	
	public BusinessException (String msg) {
		super(msg);
	}
	
	public BusinessException (Throwable causa) {
		super(causa);
	}
	
	public BusinessException (String msg, Throwable causa) {
        super(msg, causa);
    }

}
