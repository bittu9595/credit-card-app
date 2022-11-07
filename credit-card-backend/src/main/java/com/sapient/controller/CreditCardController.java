/**
 * Controller file to trigger specific request.
 */
package com.sapient.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.model.Creditcard;
import com.sapient.responsehandler.CreditcardResponseHandler;
import com.sapient.service.CreditCardService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class CreditCardController {
	@Autowired
	private CreditCardService creditcardService;

	/**
	 * Function to get all credit cards and send to client.
	 * @return credit cards.
	 */
	@GetMapping(value = "/creditcards")
	public ResponseEntity<Object> getCreditCards() {
		try {
			List<Creditcard> creditCards = creditcardService.findAll();
			return CreditcardResponseHandler.generateSuccessResponse("Successfully fetched data!", HttpStatus.OK, creditCards);
		} catch (Exception e) {
			return CreditcardResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Function to create a new credit card.
	 * @param creditCard - credit card info to be added in database.
	 * @return success or failure messages.
	 */
	@PostMapping(value = "/creditcards", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCreditCard(@RequestBody Creditcard creditCard) {
		try {
			creditcardService.addCreditCard(creditCard);
			return CreditcardResponseHandler.generateResponse("Success!", HttpStatus.OK);
		} catch (Exception e) {
			return CreditcardResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
