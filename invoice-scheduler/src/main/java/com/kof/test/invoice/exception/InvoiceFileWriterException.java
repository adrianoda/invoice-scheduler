package com.kof.test.invoice.exception;

/**
 * Convenient exception to identify invoice file write error
 * 
 * @author adrianoda
 *
 */
public class InvoiceFileWriterException extends Exception {

	private static final long serialVersionUID = 1778116641553050802L;

	public InvoiceFileWriterException() {
		super();
	}

	public InvoiceFileWriterException(String message) {
		super(message);
	}

	public InvoiceFileWriterException(Throwable e) {
		super(e);
	}

	public InvoiceFileWriterException(String message, Throwable e) {
		super(message, e);
	}

}
