package com.kof.test.invoice.scheduler;

import com.kof.test.invoice.domain.InvoiceModel;
import com.kof.test.invoice.exception.InvoiceFileWriterException;

/**
 * Common inteface to handle invoice elements
 * 
 * @author adrianoda 
 *
 */
public interface InvoiceHandler {

	/**
	 * Handle specified element
	 * @param model
	 */
	public void handle(InvoiceModel model);

	/**
	 * End elements handling
	 * @throws InvoiceFileWriterException
	 */
	public void close() throws InvoiceFileWriterException;

}
