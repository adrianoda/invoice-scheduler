invoice-scheduler
=================

Simple program that provides to:

<ul>
  <li>Read invoice input parameter file (see in-file-example.txt, formatted as below:
    <br>
    invoiceId; invoiceDate; paymentMode
  </li>
  <li>Calculate invoice expiration date from invoiceDate and paymentMode:
    <ul>
      <li>paymentMode <b>DF</b> -&gt; expiration date is equals to invoice date</li>
      <li>paymentMode <b>DF60</b>-&gt; expirationdate is equals to invoice date plus 60 calendar days</li>
      <li>paymentMode <b>DFFM</b>-&gt; expiration date is equals to last day of invoice date month</li>
    </ul>
  </li>
  <li>Sort invoices by expiration date ascending</li>
  <li>Write invoice output file (see out-file-example.txt), formatted as below:
    <br>
    invoiceId; invoiceDate; expirationDate
  </li>
</ul>

To execute program run:

<code>java -classpath invoice-scheduler-0.0.1-SNAPSHOT.jar com.kof.test.invoice.InvoiceSchedulerLauncher [inputFileName] [outputFileName]</code>

If outputFileName is not provided then default file name is created ([inputFileName].out).
