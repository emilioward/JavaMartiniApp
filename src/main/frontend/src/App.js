import React from "react";

import Typography from "@material-ui/core/Typography";

import "./App.css";
import ComplexGrid from "./Components/Grid/GetComplexGrid";
import CreateCocktail from "./Components/CreateCocktail/CreateNewCocktail";

function App() {
  return (
    <div className="App">
      <Typography variant="h2" gutterBottom className="AppTitle">
        Cocktail App
      </Typography>
      <div className="CreateButton">
        <CreateCocktail />
      </div>
      <div className="Grid">
        <ComplexGrid />
      </div>
    </div>
  );
}

export default App;
