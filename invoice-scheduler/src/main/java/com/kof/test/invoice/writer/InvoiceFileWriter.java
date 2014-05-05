package com.kof.test.invoice.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.kof.test.invoice.domain.InvoiceModel;
import com.kof.test.invoice.exception.InvoiceFileWriterException;

/**
 * Convenient service to write invoice files.
 * 
 * @author adrianoda
 * 
 */
public class InvoiceFileWriter {

	private Charset charset;

	private BufferedWriter writer;
	private String fileName;

	public InvoiceFileWriter() {
		this.charset = Charset.forName("UTF-8");
	}

	public InvoiceFileWriter(Charset charset) {
		this.charset = charset;
	}

	/**
	 * Initialise writer
	 * 
	 * @param fileName
	 * @throws InvoiceFileWriterException
	 */
	public void open(String fileName) throws InvoiceFileWriterException {
		this.fileName = fileName;
		Path file = FileSystems.getDefault().getPath(fileName);
		try {
			this.writer = Files.newBufferedWriter(file, charset);
		} catch (IOException e) {
			throw new InvoiceFileWriterException("An error occurred while opening output file [" + fileName + " ], ", e);
		}
	}

	/**
	 * Writer specified element
	 * 
	 * @param model
	 * @throws InvoiceFileWriterException
	 */
	public void write(InvoiceModel model) throws InvoiceFileWriterException {
		if (writer == null) {
			throw new IllegalStateException("Unable to write output file, writer not initialized.");
		}
		String entry = model.getInvoiceId() + "; " + parseDate(model.getInvoiceDate()) + "; " + parseDate(model.getExpirationDate());
		try {
			writer.write(entry);
			writer.newLine();
		} catch (IOException e) {
			throw new InvoiceFileWriterException("An error occurred during output file [" + fileName + " ] write, ", e);
		}
	}

	/**
	 * Close current writer
	 * 
	 * @throws InvoiceFileWriterException
	 */
	public void close() throws InvoiceFileWriterException {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				throw new InvoiceFileWriterException("An error occurred while closing output file [" + fileName + " ], ", e);
			}
		}
	}

	/**
	 * Parse date to String
	 * 
	 * @param date
	 * @return date as string
	 */
	private String parseDate(Calendar date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String pDate = df.format(date.getTime());
		return pDate;
	}

}
