package com.kof.test.invoice.scheduler;

import com.kof.test.invoice.exception.InvoiceFileParseException;
import com.kof.test.invoice.exception.InvoiceFileWriterException;

/**
 * Common interface for Invoice Scheduler process 
 * 
 * @author adrianoda
 *
 */
public interface InvoiceScheduler {

	/**
	 * Execute invoice scheduled
	 * @param inputFileName input file
	 * @param outputFileName output file
	 * @throws InvoiceFileParseException
	 * @throws InvoiceFileWriterException
	 */
	public void execute(String inputFileName, String outputFileName) throws InvoiceFileParseException, InvoiceFileWriterException;

}
