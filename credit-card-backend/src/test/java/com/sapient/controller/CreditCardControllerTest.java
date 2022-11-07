package com.sapient.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.model.Creditcard;
import com.sapient.repository.CreditCardRepository;
import com.sapient.service.CreditCardService;

@WebMvcTest(value = CreditCardController.class)
public class CreditCardControllerTest {

	Creditcard mockCard = new Creditcard("4917484589897107", "mock user", 50000, 0);
	Creditcard mockCard1 = new Creditcard("4917484589897108", "mock user", 50000, 0);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CreditCardService creditCardService;

	@Mock
	private CreditCardRepository creditCardRepository;

	@Before
	public void setUp() {
		List<Creditcard> mockCreditCards = new ArrayList<>(Arrays.asList(mockCard));
		Mockito.doReturn(mockCreditCards).when(creditCardRepository).findOne("1");
	}

	@Test
	public void getCreditCardsSuccess() throws Exception {
		List<Creditcard> mockCreditCards = new ArrayList<>(Arrays.asList(mockCard, mockCard1));

		Mockito.when(creditCardService.findAll()).thenReturn(mockCreditCards);
		// RequestBuilder requestBuilder =
		// MockMvcRequestBuilders.get("/creditcards").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(MockMvcRequestBuilders.get("/creditcards").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(2)));

	}

	@Test
	public void getCreditCardsFailure() throws Exception {
		List<Creditcard> mockCreditCards = new ArrayList<>(Arrays.asList(mockCard, mockCard1));

		Mockito.when(creditCardService.findAll()).thenThrow(InternalServerError.class);
		// RequestBuilder requestBuilder =
		// MockMvcRequestBuilders.get("/creditcards").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(MockMvcRequestBuilders.get("/creditcards").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());

	}

	@Test
	public void addCreditCardSuccess() throws Exception {
		Creditcard mockCreditCard = mockCard;
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/creditcards")
				.content(mapper.writeValueAsString(mockCard))
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk());
	}
	@Test
	public void addCreditCardFailure() throws Exception {
		Mockito.doThrow(new InputMismatchException("dummy exception")).when(creditCardService).addCreditCard(Mockito.any());
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/creditcards")
				.content(mapper.writeValueAsString(mockCard))
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isBadRequest());
	}
}
