/**
 * Service file for business logic and validations.
 */
package com.sapient.service;

import java.util.InputMismatchException;
import java.util.List;
import org.springframework.stereotype.Service;

import com.sapient.model.Creditcard;
import com.sapient.repository.CreditCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
	
@Service
public class CreditCardService {
	@Autowired
	private CreditCardRepository creditcardRepository;
/**
 * Function to validate credit card based on its length, and format and throw specific errors.
 * @param card - credit card to be validated.
 */
	private void validateCreditCard(Creditcard card) {
		if (!card.getCardNumber().matches("[0-9]+")) {
			throw new InputMismatchException("All digits of credit card number should be numeric.");
		}
		if (card.getCardNumber().length() > 19) {
			throw new InputMismatchException("credit card number cannot be more than 19 digits.");
		}
		if (!checkLuhn10(card.getCardNumber())) {
			throw new InputMismatchException("credit card number does not follow Luhn10 format.");
		}
		if (!creditcardRepository.findOne(card.getCardNumber()).isEmpty()) {
			throw new InputMismatchException("Credit card number already exist.");
		}
	}
/**
 * Function to check if credit card follows Luhn10 structure.
 * @param cardNumber - credit card number to be validated.
 * return true or false based on validation.
 */
	private boolean checkLuhn10(String cardNumber) {
		String number = cardNumber.replaceAll("[ -]", "");
	    int sum = 0;
	    for (int i=0; i<number.length(); i++){
	    int digit = (int) number.charAt(i) - '0';
	      if (i % 2 == 0) {
	        digit *= 2;
	        if (digit > 9)
	          digit -= 9;
	      }
	      sum += digit;
	    }
	    System.out.print(sum % 10);
	    return (sum % 10) == 0;
	}
/**
 * Function to add credit card after validation is successful.
 * @param creditCard - credit card to be added in db.
 */
	public void addCreditCard(Creditcard creditCard) {
		validateCreditCard(creditCard);
		creditcardRepository.addCreditCard(creditCard);
	}
/**
 * Function to return all credit cards present in system.
 * @return credt cards.
 */
	public List<Creditcard> findAll() {
		// TODO Auto-generated method stub
		return creditcardRepository.findAll();
	}

}