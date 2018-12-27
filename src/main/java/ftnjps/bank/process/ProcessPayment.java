package ftnjps.bank.process;

import ftnjps.bank.carddetails.CardDetails;
import ftnjps.bank.transaction.Transaction;

public interface ProcessPayment {

	boolean local(CardDetails cardDetails, Transaction transaction);
	boolean remoteRequest(CardDetails cardDetails, Transaction transaction);
	boolean remotePay(CardDetails cardDetails, double amount);

}
