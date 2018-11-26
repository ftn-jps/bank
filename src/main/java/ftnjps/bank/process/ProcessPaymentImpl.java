package ftnjps.bank.process;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnjps.bank.bankclient.BankClient;
import ftnjps.bank.bankclient.BankClientService;
import ftnjps.bank.carddetails.CardDetails;
import ftnjps.bank.transaction.Transaction;

@Service
@Transactional
public class ProcessPaymentImpl implements ProcessPayment {

	@Autowired
	private BankClientService bankClientService;

	@Override
	public boolean local(CardDetails cardDetails, Transaction transaction) {
		BankClient toClient = bankClientService
				.findByMerchantId(transaction.getMerchantId());
		BankClient fromClient = bankClientService
				.findByPan(cardDetails.getPan());
		if(fromClient == null
				|| toClient == null)
			return false;

		Calendar validUntilCheck = new GregorianCalendar();
		validUntilCheck.setTimeInMillis(cardDetails.getValidUntilTimestamp());
		Calendar validUntilTrue = new GregorianCalendar();
		validUntilTrue.setTimeInMillis(fromClient.getCardDetails().getValidUntilTimestamp());

		if( !cardDetails.getName().equals(fromClient.getCardDetails().getName())
				|| !cardDetails.getCvc().equals(fromClient.getCardDetails().getCvc())
				|| validUntilCheck.get(Calendar.YEAR) != validUntilTrue.get(Calendar.YEAR)
				|| validUntilCheck.get(Calendar.MONTH) != validUntilTrue.get(Calendar.MONTH)
				|| fromClient.getCardDetails().getValidUntilTimestamp() < new Date().getTime())
			return false;

		if((fromClient.getBalance() - transaction.getAmount()) < 0)
			return false;

		toClient.setBalance(toClient.getBalance() + transaction.getAmount());
		fromClient.setBalance(fromClient.getBalance() - transaction.getAmount());

		return true;
	}

}
