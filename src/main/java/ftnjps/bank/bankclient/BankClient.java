package ftnjps.bank.bankclient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnjps.bank.carddetails.CardDetails;

@Entity
public class BankClient {
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@OneToOne
	private CardDetails cardDetails;

	@JsonProperty(access = Access.READ_ONLY)
	private double balance;
	/*
	 * ID that the bank gives to the merchant when registering for online payment
	 *
	 * If client is not a merchant, this should be set to null
	 */
	private String merchantId;
	/*
	 *  Password that the bank gives to the merchant when registering for online payment
	 *
	 *  If client is not a merchant, this should be set to null
	 */
	@Size(min = 6, max = 100)
	private String merchantPassword;

	public BankClient() {}
	public BankClient(CardDetails cardDetails,
			double balance,
			String merchantId,
			String merchantPassword) {
		this.cardDetails = cardDetails;
		this.balance = balance;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CardDetails getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantPassword() {
		return merchantPassword;
	}
	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
 }
