package com.sapient.model;

public class Creditcard {
private String cardNumber;
private String cardHolderName;
private int cardLimit;
private float cardBalance;
/**
 * Constructor to initialize class variables.
 */
public Creditcard(String cardNumber, String cardHolderName, int cardLimit,float cardBalance)   
{  
this.cardNumber = cardNumber;  
this.cardHolderName = cardHolderName;  
this.cardLimit = cardLimit;  
this.cardBalance = cardBalance;  
}
public Creditcard() {
	
}
/**
 * Function to return credit card number.
 */
public String getCardNumber() {
    return cardNumber;
    
}
/**
 * Function to set credit card number value.
 */
public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
}
/**
 * Function to return cardholder name.
 */
public String getCardHolderName() {
    return cardHolderName;
}
/**
 * Function to set value of cardholder name.
 */
public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
}
/**
 * Function to return card limit.
 */
public int getCardLimit() {
    return cardLimit;
}
/**
 * Function to set card limit value.
 */
public void setCardLimit(int cardLimit) {
    this.cardLimit = cardLimit;
}
/**
 * Function to return card balance.
 */
public float getCardBalance() {
    return cardBalance;
}
/**
 * Function to set card balance.
 */
public void setCardBalance(float cardBalance) {
    this.cardBalance = cardBalance;
}
}
