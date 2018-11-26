package ftnjps.bank.bankclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankClientServiceImpl implements BankClientService {

	@Autowired
	private BankClientRepository bankClientRepository;

	@Override
	public BankClient findOne(Long id) {
		return bankClientRepository.findById(id).orElse(null);
	}

	@Override
	public List<BankClient> findAll() {
		return bankClientRepository.findAll();
	}

	@Override
	public BankClient findByPan(String pan) {
		return bankClientRepository.findByCardDetails_Pan(pan);
	}

	@Override
	public BankClient findByMerchantId(String merchantId) {
		return bankClientRepository.findByMerchantId(merchantId);
	}

	@Override
	public BankClient add(BankClient input) {
		return bankClientRepository.save(input);
	}

}
