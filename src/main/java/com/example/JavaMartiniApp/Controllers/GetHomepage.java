package com.example.JavaMartiniApp.Controllers;

import com.example.JavaMartiniApp.Model.Cocktail;
import com.example.JavaMartiniApp.Services.CocktailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetHomepage {

//    private final CocktailService cocktailService;

//    public GetHomepage(CocktailService cocktailService) {
//        this.cocktailService = cocktailService;
//    }


//    @GetMapping(path = "/home")
//    public String getAllCocktails(Model model) {
//        List<Cocktail> cocktails = cocktailService.selectAllCocktails();
//        model.addAttribute("cocktailData", cocktails);
//        return "index";
//    }
}
