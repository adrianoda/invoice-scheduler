package com.kof.test.invoice.service;

import java.util.Calendar;

import com.kof.test.invoice.domain.PaymentMode;

/**
 * Common service to calculate expiration date
 * 
 * @author adrianoda
 * 
 */
public interface ExpirationDateCalculator {

	/**
	 * Calculate expiration date
	 * 
	 * @param invoiceDate
	 * @param paymentMode
	 * @return expiration date
	 */
	public Calendar calculate(Calendar invoiceDate, PaymentMode paymentMode);

}
