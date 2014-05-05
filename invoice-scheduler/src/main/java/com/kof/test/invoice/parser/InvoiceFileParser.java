package com.kof.test.invoice.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.kof.test.invoice.domain.InvoiceModel;
import com.kof.test.invoice.domain.PaymentMode;
import com.kof.test.invoice.exception.InvoiceFileParseException;
import com.kof.test.invoice.exception.InvoiceFileWriterException;
import com.kof.test.invoice.scheduler.InvoiceHandler;

/**
 * Invoice file parser
 * 
 * @author adrianoda
 * 
 */
public class InvoiceFileParser {

	private Charset charset;

	public InvoiceFileParser() {
		this.charset = Charset.forName("UTF-8");
	}

	public InvoiceFileParser(InvoiceHandler invoiceHandler, Charset charset) {
		this.charset = charset;
	}

	/**
	 * Parse input invoice file. For each entry invoice handler is invoked.
	 * 
	 * @param fileName
	 * @throws InvoiceFileParseException
	 * @throws InvoiceFileWriterException
	 */
	public void parse(String fileName, InvoiceHandler invoiceHandler) throws InvoiceFileParseException, InvoiceFileWriterException {
		Path file = FileSystems.getDefault().getPath(fileName);
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] spLine = line.split(";");
				if (spLine.length != 3) {
					throw new InvoiceFileParseException("Invalid number of element for file line [" + line + "], expected 3 but found " + spLine.length);
				}

				// Invoice model is built and invoice handler is invoked
				String invoiceId = spLine[0].trim();
				Calendar invoiceDate = parseDate(spLine[1].trim());
				PaymentMode paymentMode = parsePaymentMode(spLine[2].trim());
				InvoiceModel model = new InvoiceModel(invoiceId, invoiceDate, paymentMode);
				invoiceHandler.handle(model);
			}
		} catch (IOException e) {
			throw new InvoiceFileParseException("An error occurred during parsing file [" + fileName + "]", e);
		}

		// Communicate to invoice handler that file parsing is finished
		invoiceHandler.close();
	}

	/**
	 * Retrieve PaymentMode enum from equivalent String
	 * 
	 * @param paymentMode
	 * @return PaymentMode enum from equivalent String
	 * @throws InvoiceFileParseException
	 */
	private PaymentMode parsePaymentMode(String paymentMode) throws InvoiceFileParseException {
		PaymentMode pm;
		try {
			pm = PaymentMode.valueOf(paymentMode);
		} catch (Exception e) {
			throw new InvoiceFileParseException("Specified payment mode [" + paymentMode + "] is not valid");
		}
		return pm;
	}

	/**
	 * Parse string to date
	 * 
	 * @param invoiceDate
	 * @return parsed date
	 * @throws InvoiceFileParseException
	 */
	private Calendar parseDate(String invoiceDate) throws InvoiceFileParseException {
		Calendar invD;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			invD = Calendar.getInstance();
			invD.setTime(sdf.parse(invoiceDate));
		} catch (ParseException e) {
			throw new InvoiceFileParseException("Specified date is not valid [" + invoiceDate + "], expected date format is dd/MM/yyyy", e);
		}
		return invD;
	}

}
