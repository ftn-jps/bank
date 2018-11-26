package ftnjps.bank.carddetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class CardDetails {
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	// Payment card number
	@Pattern(regexp = "\\d{10,19}")
	@NotEmpty
	private String pan;
	@NotBlank
	private String name;
	// Card verification code
	@Pattern(regexp = "\\d{3,4}")
	@NotEmpty
	private String cvc;
	@Positive
	private long validUntilTimestamp;

	public CardDetails() {}
	public CardDetails(String pan,
			String name,
			String cvc,
			long validUntilTimestamp) {
		this.pan = pan;
		this.name = name;
		this.cvc = cvc;
		this.validUntilTimestamp = validUntilTimestamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	public long getValidUntilTimestamp() {
		return validUntilTimestamp;
	}
	public void setValidUntilTimestamp(long validUntilTimestamp) {
		this.validUntilTimestamp = validUntilTimestamp;
	}
}
