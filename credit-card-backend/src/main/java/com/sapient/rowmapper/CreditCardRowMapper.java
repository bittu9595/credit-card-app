package com.sapient.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sapient.model.Creditcard;

public class CreditCardRowMapper implements RowMapper<Creditcard> {
	public Creditcard mapRow(ResultSet rs, int rowNum) throws SQLException {
		Creditcard card= new Creditcard();
		card.setCardHolderName(rs.getString("name"));
		card.setCardNumber(rs.getString("number"));
		card.setCardLimit(rs.getInt("card_limit"));
		card.setCardBalance(rs.getFloat("balance"));
		return card;
	}
}
