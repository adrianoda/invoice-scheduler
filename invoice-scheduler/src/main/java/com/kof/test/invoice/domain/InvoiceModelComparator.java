package com.kof.test.invoice.domain;

import java.util.Comparator;

/**
 * Invoices compare based on expiration date ascending
 * 
 * @author adrianoda
 *
 */
public class InvoiceModelComparator implements Comparator<InvoiceModel> {

	@Override
	public int compare(InvoiceModel model1, InvoiceModel model2) {
		return model1.getExpirationDate().compareTo(model2.getExpirationDate());
	}

}
