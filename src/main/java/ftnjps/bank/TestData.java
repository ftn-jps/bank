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
		// Merchants
		CardDetails cd1 = new CardDetails(bankIin + "1111111111",
				"Merchant One",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd1);
		BankClient client1 = new BankClient(
				cd1,
				100000,
				"1111-1111",
				null);
		bankClientService.add(client1);

		CardDetails cd2 = new CardDetails(bankIin + "2222222222",
				"Merchant Two",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd2);
		BankClient client2 = new BankClient(
				cd2,
				100000,
				"2222-2222",
				null);
		bankClientService.add(client2);

		CardDetails cd3 = new CardDetails(bankIin + "3333333333",
				"Merchant Three",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd3);
		BankClient client3 = new BankClient(
				cd3,
				100000,
				"3333-3333",
				null);
		bankClientService.add(client3);

		CardDetails cd4 = new CardDetails(bankIin + "4444444444",
				"Merchant Four",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd4);
		BankClient client4 = new BankClient(
				cd4,
				100000,
				"4444-4444",
				null);
		bankClientService.add(client4);

		// Clients
		CardDetails cd5 = new CardDetails(bankIin + "5555555555",
				"Client One",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd5);
		BankClient client5 = new BankClient(
				cd5,
				100000,
				null,
				null);
		bankClientService.add(client5);

		CardDetails cd6 = new CardDetails(bankIin + "6666666666",
				"Client Two",
				"123",
				1605390265000l /* 14.11.2020. */);
		cardDetailsService.add(cd6);
		BankClient client6 = new BankClient(
				cd6,
				100000,
				null,
				null);
		bankClientService.add(client6);
	}

}
