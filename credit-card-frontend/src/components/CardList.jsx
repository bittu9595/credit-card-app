/**
 * List Component to display cards in table view.
 */
/**
 * Adding more comments to learn rebase.
 */
import React from "react";
import Table from "./Table";

const CardList = (props) => {
  return (
    <>
      <label className="larger"> Existing Cards </label>
      {props.cards.length === 0 ? (
        <div> No Cards </div>
      ) : (
        <Table cards={props.cards} />
      )}
    </>
  );
};

export default CardList;
