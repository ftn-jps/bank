package ftnjps.bank.process;

import ftnjps.bank.carddetails.CardDetails;
import ftnjps.bank.transaction.Transaction;

public interface ProcessPayment {

	boolean local(CardDetails cardDetails, Transaction transaction);

}
