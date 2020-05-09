package com.example.JavaMartiniApp.Interface;

import com.example.JavaMartiniApp.Model.Cocktail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CocktailInterface {

    int insertCocktail(Cocktail cocktail);

    List<Cocktail> selectAllCocktails();

    Optional<Cocktail> selectCocktailById(UUID id);

    int updateCocktailById(UUID id, Cocktail cocktail);

    int deleteCocktailById(UUID id);

}
