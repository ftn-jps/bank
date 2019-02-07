package ftnjps.bank.transaction;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnjps.bank.carddetails.CardDetails;
import ftnjps.bank.process.ProcessPayment;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private ProcessPayment processPayment;

	@Value("${frontend.url}")
	private String frontendUrl;
	@Value("${bank.iin}")
	private String bankIin;

	@PostMapping
	public ResponseEntity<?> startTransaction(@RequestBody @Valid Transaction transaction) {
		System.out.println("Creating transaction with token: " + transaction.getToken());

		Transaction newTransaction = transactionService.add(transaction);
		System.out.println("Transaction with token: " + transaction.getToken() + " successfully created");
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Redirecting user to fill in credit card details");
		headers.add("Location",
				frontendUrl + "/#/transaction/" + newTransaction.getToken());
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@PostMapping("/{token}")
	public ResponseEntity<?> processTransaction(
			@PathVariable String token,
			@RequestBody @Valid CardDetails cardDetails
	) {
		System.out.println("Getting transaction with token: " + token);
		Transaction transaction = transactionService.findByToken(token);
		System.out.println("Transaction with token: " + transaction.getToken() + " successfully obtained");
		boolean response;

		System.out.println("Determing weather merchant's bank is local or remote bank");
		if(cardDetails.getPan().startsWith(bankIin)) {
			System.out.println("Merchant's bank is local");
			response = processPayment.local(cardDetails, transaction);
		}
		else {
			System.out.println("Merchant's bank is remote");
			response = processPayment.remoteRequest(cardDetails, transaction);
		}

		final String successUrl = new String("{ \"url\" : \""+ transaction.getSuccessUrl() +"\" }");
		final String errorUrl = new String("{ \"url\" : \""+ transaction.getErrorUrl() +"\" }");
		System.out.println("Redirecting...");
		if (response)
			return new ResponseEntity<>(successUrl, HttpStatus.OK);
		else
			return new ResponseEntity<>(errorUrl, HttpStatus.OK);
	}

	@PostMapping("/amount/{amount}")
	public ResponseEntity<Boolean> processPccTransaction(
			@PathVariable double amount,
			@RequestBody @Valid CardDetails cardDetails) {
		boolean response;
		System.out.println("Remote bank payment...");
		response = processPayment.remotePay(cardDetails, amount);

		if(!response){
			System.out.println("Remote bank payment failed!");
		} else {
			System.out.println("Remote bank payment success!");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
