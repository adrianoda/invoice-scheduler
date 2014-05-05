package com.kof.test.invoice.domain;

import java.util.Calendar;

/**
 * Hold invoice data
 * 
 * @author adrianoda
 */
public class InvoiceModel {

	private String invoiceId;

	private Calendar invoiceDate;

	private PaymentMode paymentMode;

	private Calendar expirationDate;

	public InvoiceModel(String invoiceId, Calendar invoiceDate, PaymentMode paymentMode) {
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.paymentMode = paymentMode;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Calendar getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Calendar invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

}
