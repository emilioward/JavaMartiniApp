import React, { useEffect, useState } from "react";
import axios from "axios";

import { makeStyles } from "@material-ui/core/styles";
import Grid from "@material-ui/core/Grid";
import Paper from "@material-ui/core/Paper";
import Typography from "@material-ui/core/Typography";
import ButtonBase from "@material-ui/core/ButtonBase";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing(1),
    maxWidth: 500,
    maxHeight: 500,
    borderRadius: 20,
    margin: "auto",
  },
  image: {
    width: 250,
    height: 250,
  },
  img: {
    margin: "auto",
    display: "block",
    maxWidth: "100%",
    maxHeight: "100%",
    borderRadius: 10,
  },
}));

export default function ComplexGrid() {
  const classes = useStyles();

  const [cocktails, setCocktails] = useState([]);

  const fetchCocktailData = () => {
    axios.get("http://localhost:8080/api/V1/cocktail/").then((response) => {
      console.log(response);
      setCocktails(response.data);
    });
  };

  useEffect(() => {
    fetchCocktailData();
  }, []);

  return cocktails.map((cocktails, index) => {
    return (
      <div className={classes.root} key={cocktails.id}>
        <Paper className={classes.paper}>
          <Grid container spacing={12}>
            <Grid item xs={12}>
              <Typography gutterBottom variant="h5">
                <p>{cocktails.name}</p>
              </Typography>
              <Typography variant="body2" color="textSecondary">
                UUID:
                <p>{cocktails.id}</p>
              </Typography>
              <ButtonBase className={classes.image}>
                <img
                  className={classes.img}
                  alt="complex"
                  src="static/images/notfound.jpg"
                />
              </ButtonBase>
            </Grid>
          </Grid>
        </Paper>
        <br></br>
      </div>
    );
  });
}
