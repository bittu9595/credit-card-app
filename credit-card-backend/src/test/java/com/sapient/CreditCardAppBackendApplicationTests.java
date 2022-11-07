package com.sapient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;	
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


import com.sapient.controller.CreditCardController;
import com.sapient.model.Creditcard;
import com.sapient.repository.CreditCardRepository;
import com.sapient.service.CreditCardService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CreditCardController.class)
class CreditCardAppBackendApplicationTests {
	Creditcard mockCard = new Creditcard("4917484589897107","mock user",50000,0);
	Creditcard mockCard1 = new Creditcard("4917484589897108","mock user",50000,0);

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
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcards").accept(MediaType.APPLICATION_JSON);
		 mockMvc.perform(MockMvcRequestBuilders
		            .get("/creditcards")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$.data", hasSize(2)));

}
	@Test
	public void getCreditCardsFailure() throws Exception {
		List<Creditcard> mockCreditCards = new ArrayList<>(Arrays.asList(mockCard, mockCard1));
		
		Mockito.when(creditCardService.findAll()).thenThrow(InternalServerError.class);
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/creditcards").accept(MediaType.APPLICATION_JSON);
		 mockMvc.perform(MockMvcRequestBuilders
		            .get("/creditcards")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isInternalServerError());
		            

}
	@Test
	public void addCreditCardSuccess() throws Exception {
		Creditcard mockCreditCard = mockCard;
		
		Mockito.doCallRealMethod().when(creditCardService).addCreditCard(mockCreditCard);
		creditCardService.addCreditCard(mockCreditCard);
		mockMvc.perform(MockMvcRequestBuilders
	            .post("/creditcards")
	            .contentType(MediaType.APPLICATION_JSON));

}
}
