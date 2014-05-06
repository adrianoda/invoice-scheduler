package com.kof.test.invoice;

import com.kof.test.invoice.exception.InvoiceFileParseException;
import com.kof.test.invoice.exception.InvoiceFileWriterException;
import com.kof.test.invoice.scheduler.InvoiceScheduler;
import com.kof.test.invoice.scheduler.impl.InvoiceSchedulerImpl;

/**
 * Invoice scheduler launcher
 * 
 * @author adrianoda
 *
 */
public class InvoiceSchedulerLauncher {

	public static void main(String[] args) {
		
		// Setup input parameters
		if (args.length < 1) {
			System.out.println("At least input file name must be specified as input parameter...");
			return;
		}
		String inputFileName = args[0];
		
		String outputFileName = null;
		if (args.length == 2) {
			outputFileName = args[1];
		} else {
			outputFileName = inputFileName + ".out";
		}

		// Scheduler execution
		InvoiceScheduler scheduler = new InvoiceSchedulerImpl();
		try {
			System.out.println("START SCHEDULER....");
			scheduler.execute(inputFileName, outputFileName);
			System.out.println("DONE!");
		} catch (InvoiceFileParseException | InvoiceFileWriterException e) {
			e.printStackTrace();
		} finally {
			System.out.println("END SCHEDULER.");
		}
	}

}
