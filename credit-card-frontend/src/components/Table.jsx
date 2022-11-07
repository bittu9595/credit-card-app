/**
 * Table component to render credit card info.
 */
import React from "react";

const Table = (props) => {
  return (
    <table className="table">
      <thead className="thead-dark">
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Card Number</th>
          <th scope="col">Balance</th>
          <th scope="col">Limit</th>
        </tr>
      </thead>
      <tbody>
        {props.cards.map((card) => (
          <tr key={card.cardNumber}>
            <td> {card.cardHolderName} </td>
            <td> {formatNumber(card.cardNumber)} </td>
            <td> £0 </td>
            <td> {card.cardLimit} </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
/**
 * Function to format card number
 * @param {*} number - credit card number.
 * @returns formatted credit card number.
 */
const formatNumber = (number) => {
  return number
    .replace(/ /g, "")
    .replace(/-/g, "")
    .replace(/(.{4})/g, "$1 ");
};

export default Table;
