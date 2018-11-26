package ftnjps.bank.bankclient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankClientRepository extends JpaRepository<BankClient, Long>{

	BankClient findByCardDetails_Pan(String pan);
	BankClient findByMerchantId(String merchantId);

}
