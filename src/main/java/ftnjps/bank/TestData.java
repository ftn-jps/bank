package ftnjps.bank;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ftnjps.bank.bankclient.BankClient;
import ftnjps.bank.bankclient.BankClientService;
import ftnjps.bank.carddetails.CardDetails;
import ftnjps.bank.carddetails.CardDetailsService;

@Component
public class TestData {

	@Autowired
	private BankClientService bankClientService;
	@Autowired
	private CardDetailsService cardDetailsService;
	@Value("${bank.iin}")
	private String bankIin;

	@PostConstruct
	private void init() {
		CardDetails cd1 = new CardDetails(bankIin + "1234567890",
				"test",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd1);
		BankClient client1 = new BankClient(
				cd1,
				100000,
				"test",
				"testtest");
		bankClientService.add(client1);

		CardDetails cd2 = new CardDetails(bankIin + "1111111111",
				"richard",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd2);
		BankClient client2 = new BankClient(
				cd2,
				100000,
				null,
				null);
		bankClientService.add(client2);
	}

}
