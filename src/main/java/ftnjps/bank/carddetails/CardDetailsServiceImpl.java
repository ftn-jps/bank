package ftnjps.bank.carddetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardDetailsServiceImpl implements CardDetailsService {

	@Autowired
	private CardDetailsRepository cardDetailsRepository;

	@Override
	public CardDetails findOne(Long id) {
		return cardDetailsRepository.findById(id).orElse(null);
	}

	@Override
	public List<CardDetails> findAll() {
		return cardDetailsRepository.findAll();
	}

	@Override
	public CardDetails add(CardDetails input) {
		return cardDetailsRepository.save(input);
	}

}
