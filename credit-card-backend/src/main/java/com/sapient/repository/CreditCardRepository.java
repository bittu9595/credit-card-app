/**
 * Repository file to query database based on client requests.
 */
package com.sapient.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.sapient.constants.DbConstants;
import com.sapient.model.Creditcard;
import com.sapient.rowmapper.CreditCardRowMapper;

@Component
public class CreditCardRepository {
	public static final String GET_ONE_QUERY = "SELECT * FROM CREDIT_CARD WHERE number = ";
	public static final String GET_ALL_QUERY = "SELECT * FROM CREDIT_CARD";
	public static final String INSERT_QUERY = "INSERT INTO CREDIT_CARD (name, number, card_limit, balance) VALUES (?, ?, ?, ?)";
	public static final String CREATE_TABLE_QUERY = "DROP TABLE IF EXISTS CREDIT_CARD; " + "CREATE TABLE CREDIT_CARD"
			+ " (name VARCHAR(255), " + " number VARCHAR(20) PRIMARY KEY, " + " card_limit INTEGER, "
			+ " balance FLOAT)";

	static JdbcTemplate jdbcTemplate;
/**
 * Function to fetch all credit cards from database.
 * @return credit cards.
 */
	public List<Creditcard> findAll() {
		return jdbcTemplate.query(GET_ALL_QUERY, new CreditCardRowMapper());
	}
/**
 * Function to get specific one credit card info from db.
 * @param cardNumber card number info to be fetched.
 * @return specific credit card info.
 */
	public List<Creditcard> findOne(String cardNumber) {
		 List<Creditcard> creditCards =  jdbcTemplate.query(GET_ONE_QUERY + "'" + cardNumber + "'", new CreditCardRowMapper());
		 return creditCards;
	}
 /**
  * Function to add credit card to database.
  * @param card - card to be added to database.
  */
	public void addCreditCard(Creditcard card) {
		jdbcTemplate.update(INSERT_QUERY,
				new Object[] { card.getCardHolderName(), card.getCardNumber(), card.getCardLimit(), 0 });
	}
/**
 * Function to initialize connection to database and create table.
 */
	public static void init() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DbConstants.JDBC_DRIVER);
		dataSource.setUrl(DbConstants.DB_URL);
		dataSource.setUsername(DbConstants.USER);
		dataSource.setPassword(DbConstants.PASS);

		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute(CREATE_TABLE_QUERY);
	}
}
