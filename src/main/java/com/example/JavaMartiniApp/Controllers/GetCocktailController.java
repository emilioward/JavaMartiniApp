package com.example.JavaMartiniApp.Controllers;

import com.example.JavaMartiniApp.Model.Cocktail;
import com.example.JavaMartiniApp.Services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping("api/V1/cocktail")
@RestController
public class GetCocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public GetCocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @PostMapping
    public void addCocktail(@RequestBody @Valid @NotNull Cocktail cocktail) {
        cocktailService.addCocktail(cocktail);
    }

    @GetMapping(path = "/{id}")
    public Cocktail getCocktailById(@PathVariable("id") UUID id) {
        return cocktailService.selectCocktailById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteCocktailById(@PathVariable("id") UUID id) {
        cocktailService.deleteCocktailById(id);
    }

    @PutMapping(path = "/update/{id}")
    public void updateCocktailById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Cocktail cocktailToUpdate) {
        cocktailService.updateCocktailById(id, cocktailToUpdate);
    }

}
