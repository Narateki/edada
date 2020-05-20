package edadamanager.controller;

import edadamanager.model.Category;
import edadamanager.model.Recipe;
import edadamanager.repository.CategoryRepository;
import edadamanager.repository.RecipeRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @RequestMapping("/findall")
    public String findall(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }


    @RequestMapping("/load")
    public String loadAll(){
        return "categories";
    }
}
