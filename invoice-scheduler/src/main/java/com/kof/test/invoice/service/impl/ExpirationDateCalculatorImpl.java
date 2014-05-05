package com.kof.test.invoice.service.impl;

import java.util.Calendar;

import com.kof.test.invoice.domain.PaymentMode;
import com.kof.test.invoice.service.ExpirationDateCalculator;

/**
 * Calculates expiration date. Its value depends on payment mode:
 * <ul>
 * <li><b>DF</b>: expiration date is equals to invoice date</li>
 * <li><b>DF60</b>: expiration date is equals to invoice date plus 60 calendar days</li>
 * <li><b>DFFM</b>: expiration date is equals to last day of invoice date month</li>
 * </ul> 
 * 
 * @author adrianoda
 * 
 */
public class ExpirationDateCalculatorImpl implements ExpirationDateCalculator {

	/**
	 * {@inheritDoc}
	 */
	public Calendar calculate(Calendar invoiceDate, PaymentMode paymentMode) {
		Calendar expirationDate = (Calendar) invoiceDate.clone();
		switch (paymentMode) {
    		case DF:
    			// Do nothing, expiration date is equals to invoiceDate
    			break;
    		case DF60:
    			expirationDate.add(Calendar.DAY_OF_MONTH, 60);
    			break;
    		case DFFM:
    			int lastDayOfMouth = expirationDate.getActualMaximum(Calendar.DAY_OF_MONTH);
    			expirationDate.set(Calendar.DAY_OF_MONTH, lastDayOfMouth);
    			break;
    		default:
    			throw new IllegalArgumentException("Specified payment mode is not valid: [" + paymentMode.name() + "]");
		}
		return expirationDate;
	}

}
