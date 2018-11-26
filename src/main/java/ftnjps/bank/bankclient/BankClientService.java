package ftnjps.bank.bankclient;

import java.util.List;

public interface BankClientService {

	BankClient findOne(Long id);
	List<BankClient> findAll();
	BankClient findByPan(String pan);
	BankClient findByMerchantId(String merchantId);
	BankClient add(BankClient input);

}
