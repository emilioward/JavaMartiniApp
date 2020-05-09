package com.example.JavaMartiniApp.Services;

import com.example.JavaMartiniApp.Interface.CocktailDataAccessService;
import com.example.JavaMartiniApp.Model.Cocktail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CocktailService {

    private final CocktailDataAccessService cocktailDataAccessService;

    @Autowired
    public CocktailService(@Qualifier("MyDAO") CocktailDataAccessService cocktailDataAccessService){
        this.cocktailDataAccessService = cocktailDataAccessService;
    }

    public int addCocktail(Cocktail cocktail){
        return cocktailDataAccessService.insertCocktail(cocktail);
    }

    public List<Cocktail> selectAllCocktails(){
        return cocktailDataAccessService.selectAllCocktails();
    }

    public Optional<Cocktail> selectCocktailById(UUID id){
        return cocktailDataAccessService.selectCocktailById(id);
    }

    public int deleteCocktailById(UUID id){
        return cocktailDataAccessService.deleteCocktailById(id);
    }

    public int updateCocktailById(UUID id, Cocktail updateCocktail){
        return cocktailDataAccessService.updateCocktailById(id, updateCocktail);
    }

}