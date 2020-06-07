import React, { useEffect, useState } from "react";
import axios from "axios";

import { v4 as uuidv4 } from "uuid";

import Button from "@material-ui/core/Button";

const postData = (value) => {
  axios
    .post("http://localhost:8080/api/V1/cocktail/", {
      id: uuidv4(),
      name: "ButtonClickWorked",
    })
    .then(function (response) {
      console.log(response);
    });
};

export default function CreateCocktail() {
  return (
    <Button variant="contained" color="primary" onClick={postData}>
      Create
    </Button>
  );
}
