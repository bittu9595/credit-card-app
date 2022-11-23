/**
 * Credit card page to add and display credit card.
 */
import axios from "axios";
import { useEffect, useState } from "react";
import { apiUrl } from "../ApiUrl";
import CardList from "../components/CardList";
import Form from "../components/Form";
import ValidationErrors from "../components/ValidationErrors";

const CreditCardPage = (props) => {
  const [card, setCard] = useState({
    cardHolderName: "",
    cardNumber: "",
    cardLimit: 0,
  });
  const [validationErrors, setValidationErrors] = useState({
    cardHolderName: false,
    cardNumber: false,
    cardLimit: false,
  });
  const [cardList, setCardList] = useState([]);
  /**
   * Function to get credit cards information from server.
   */
  const getCreditCards = async () => {
    try {
      const creditCardData = await axios.get(apiUrl.creditCardUrl);
      setCardList(creditCardData.data.data);
    } catch (err) {
      alert(err.response.data.message);
    }
  };
  /**
   * React hook to make side effects.
   */
  useEffect(() => {
    getCreditCards();
  }, []);
  /**
   * Function to handle input change.
   * @param {*} event - input event.
   */
  const handleInputChange = (event) => {
    let obj = { [event.target.name]: event.target.value };
    setCard(() => ({ ...card, ...obj }));
  };
  /**
   * Function to check if the form is valid or not.
   * @returns true or false based on form validity.
   */
  const isFormValid = () => {
    const nameRegex = /^[^0-9.]+$/;
    const limitRegex = /^[0-9]+$/;
    let errors = { cardHolderName: true, cardNumber: true, cardLimit: true };
    if (nameRegex.test(card.cardHolderName)) errors.cardHolderName = false;
    if (checkCardNumber()) errors.cardNumber = false;
    if (limitRegex.test(card.cardLimit)) errors.cardLimit = false;
    setValidationErrors(errors);
    return !Object.values(errors).some((error) => error === true);
  };
  /**
   * Function to check validity of length of credit card number.
   * @returns true or false based on credit card validity.
   */
  const checkCardNumber = () => {
    const number = card.cardNumber
      .replace(/ /g, "")
      .replace(/-/g, "")
      .replace(" ", "");
    return number.length >= 16 && number.length <= 19;
  };
  /**
   * Function to add credit card to server.
   */
  const addCreditCard = async () => {
    if (isFormValid()) {
      try {
        const response = await axios.post(apiUrl.creditCardUrl, card);
        if (response.status === 200) {
          setCardList([...cardList, card]);
          setCard({
            cardHolderName: "",
            cardNumber: "",
            cardLimit: 0,
          });
        }
      } catch (err) {
        alert(err.response.data.message);
      }
    }
  };

  return (
    <div>
      <h1>Credit Card System</h1>
      <p> Use the form below to add a new Credit Card to the System </p>

      <Form
        card={card}
        handleInputChange={handleInputChange}
        addCard={addCreditCard}
      />

      <ValidationErrors errors={validationErrors} />

      <CardList cards={cardList} />
    </div>
  );
};
export default CreditCardPage;
