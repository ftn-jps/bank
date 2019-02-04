package ftnjps.bank.bankinfo;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank-info")
public class BankInfoController {

	@Value("${bank.name}")
	private String bankName;

	@GetMapping
	public ResponseEntity<?> getBankInfo() {
		HashMap<String, String> response = new HashMap<>();
		response.put("bankName", bankName);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
