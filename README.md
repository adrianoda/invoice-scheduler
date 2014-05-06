invoice-scheduler
=================

Simple program that provides to:

<ul>
  <li>Read invoice input parameter file (see in-invoice.txt, formatted as below:
    <br>
    invoiceId; invoiceDate; paymentMode
  </li>
  <li>Calculate invoice expiration date from invoiceDate and paymentMode:
    <ul>
      <li><b>DF</b>: expiration date is equals to invoice date</li>
      <li><b>DF60</b>: expiration date is equals to invoice date plus 60 calendar days</li>
      <li><b>DFFM</b>: expiration date is equals to last day of invoice date month</li>
    </ul>
  </li>
  <li>Sort invoices by expiration date ascending</li>
  <li>Write invoice output file (see out-invoice.txt), formatted as below:
    <br>
    invoiceId; invoiceDate; expirationDate
  </li>
</ul>
