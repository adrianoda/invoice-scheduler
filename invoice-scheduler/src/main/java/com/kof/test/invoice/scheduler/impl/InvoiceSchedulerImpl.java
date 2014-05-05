package com.kof.test.invoice.scheduler.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.kof.test.invoice.domain.InvoiceModel;
import com.kof.test.invoice.domain.InvoiceModelComparator;
import com.kof.test.invoice.exception.InvoiceFileParseException;
import com.kof.test.invoice.exception.InvoiceFileWriterException;
import com.kof.test.invoice.parser.InvoiceFileParser;
import com.kof.test.invoice.scheduler.InvoiceHandler;
import com.kof.test.invoice.scheduler.InvoiceScheduler;
import com.kof.test.invoice.service.ExpirationDateCalculator;
import com.kof.test.invoice.service.impl.ExpirationDateCalculatorImpl;
import com.kof.test.invoice.writer.InvoiceFileWriter;

/**
 * Invoice scheduler implementation. It provides to:
 * <ul>
 * <li>invokes the <b>parser</b> in order to read input file  (see execute method)</li>
 * <li>for each invoice read from input file it provides to calculate <b>expiration date</b> (see handle method)</li>
 * <li><b>sort</b> invoices by ascending expiration date (see close method)</li>
 * <li>invokes <b>file writer</b> in order to create output file with sorted invoices (see close method)</li>
 * </ul>
 * 
 * <br>
 * <p><b>IMPORTANT:</b> despite the fact that fileParser and fileWriter work on a stream of invoices, this class
 * will hold all invoices into local memory (invoices class variable). If we suppose to receive big file then this implementation
 * must be changed to avoid out of memory issues.</p>
 * <br>
 * 
 * @author adrianoda
 * 
 */
public class InvoiceSchedulerImpl implements InvoiceScheduler, InvoiceHandler {

	private InvoiceFileParser fileParser;
	private InvoiceFileWriter fileWriter;
	private ExpirationDateCalculator expDateCalc;

	private List<InvoiceModel> invoices = new ArrayList<InvoiceModel>();
	private String outputFileName;

	public InvoiceSchedulerImpl() {
		// Setup services
		this.expDateCalc = new ExpirationDateCalculatorImpl();
		this.fileWriter = new InvoiceFileWriter();
		this.fileParser = new InvoiceFileParser();
	}

	public void execute(String inputFileName) throws InvoiceFileParseException, InvoiceFileWriterException {
		execute(inputFileName, null);
	}

	public void execute(String inputFileName, String outputFileName) throws InvoiceFileParseException, InvoiceFileWriterException {
		// Set default output file name if not provided
		this.outputFileName = outputFileName != null ? outputFileName : inputFileName + ".out";
		fileParser.parse(inputFileName, this);
	}

	/**
	 * Handle invoice read from file. Also calculates expiration date.
	 */
	public void handle(InvoiceModel model) {
		Calendar expirationDate = expDateCalc.calculate(model.getInvoiceDate(), model.getPaymentMode());
		model.setExpirationDate(expirationDate);
		invoices.add(model);
	}

	/**
	 * Sort handled invoices and write output file
	 */
	public void close() throws InvoiceFileWriterException {
		Collections.sort(invoices, new InvoiceModelComparator());
		try {
			fileWriter.open(outputFileName);
			for (InvoiceModel model : invoices) {
				fileWriter.write(model);
			}
		} finally {
			fileWriter.close();
		}
	}

}
