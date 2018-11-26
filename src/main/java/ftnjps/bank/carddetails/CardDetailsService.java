package ftnjps.bank.carddetails;

import java.util.List;

public interface CardDetailsService {

	CardDetails findOne(Long id);
	List<CardDetails> findAll();
	CardDetails add(CardDetails input);

}
