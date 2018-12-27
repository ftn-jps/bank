package ftnjps.bank.transaction;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnjps.bank.carddetails.CardDetails;
import ftnjps.bank.process.ProcessPayment;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private ProcessPayment processPayment;

	@Value("${frontend.url}")
	private int frontendUrl;
	@Value("${bank.iin}")
	private String bankIin;

	@PostMapping
	public ResponseEntity<?> startTransaction(@RequestBody @Valid Transaction transaction) {
		Transaction newTransaction = transactionService.add(transaction);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location",
				frontendUrl + "/#/transaction/" + newTransaction.getToken());
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@PostMapping("/{token}")
	public ResponseEntity<?> processTransaction(
			@PathVariable String token,
			@RequestBody @Valid CardDetails cardDetails) {
		Transaction transaction = transactionService.findByToken(token);

		boolean response;
		if(cardDetails.getPan().startsWith(bankIin)) {
			response = processPayment.local(cardDetails, transaction);
		}
		else {
			response = processPayment.remoteRequest(cardDetails, transaction);
		}

		HttpHeaders headers = new HttpHeaders();
		if (response)
			headers.add("Location", transaction.getSuccessUrl());
		else
			headers.add("Location", transaction.getFailUrl());
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}
}
