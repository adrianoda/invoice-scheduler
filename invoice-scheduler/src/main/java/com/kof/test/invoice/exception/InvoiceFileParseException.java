package com.kof.test.invoice.exception;

/**
 * Convenient exception to identify invoice file parse error
 * 
 * @author adrianoda
 * 
 */
public class InvoiceFileParseException extends Exception {

	private static final long serialVersionUID = -4494713002960881959L;

	public InvoiceFileParseException() {
		super();
	}

	public InvoiceFileParseException(String message) {
		super(message);
	}

	public InvoiceFileParseException(Throwable e) {
		super(e);
	}

	public InvoiceFileParseException(String message, Throwable e) {
		super(message, e);
	}

}
