/**
 * Component to validate error for each input.
 */
import React from "react";

const ValidationErrors = (props) => {
  return (
    <div className="validation-errors">
      {props.errors.cardHolderName && (
        <div className="alert">
          User Name field cannot contain numbers or be empty.
        </div>
      )}
      {props.errors.cardNumber && (
        <div className="alert">
          Card Number not valid. It must be a 16 to 19 digit number and should
          work against Luhn 10 algorithm.
        </div>
      )}
      {props.errors.cardLimit && (
        <div className="alert">Limit field must be a valid number.</div>
      )}
    </div>
  );
};

export default ValidationErrors;
