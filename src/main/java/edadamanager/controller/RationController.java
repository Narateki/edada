package edadamanager.controller;

import com.google.gson.Gson;
import edadamanager.model.Day;
import edadamanager.model.Ration;
import edadamanager.model.Recipe;
import edadamanager.repository.*;
import edadamanager.wrapper.RationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping("/rations")
public class RationController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RationRepository rationRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addRecipe(Model model) {

        Ration ration = new Ration();
        model.addAttribute("ration", ration);
        model.addAttribute("inventory", inventoryRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "addRation";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRecipePost(@Valid Ration ration, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors())
            return "addRecipe";
        Set<Day> sdays = new HashSet<>();
        for(int i = 0; i < ration.getQdays(); i++) {
            Day d = new Day();
            sdays.add(d);
        }
        ration.setDays(sdays);
        Ration r = rationRepository.save(ration);
        redirectAttributes.addAttribute("rId", r.getId());
        return "redirect:/rations/addDays";
    }

    @RequestMapping(value = "/addDays", method = RequestMethod.GET)
    public String addDays(@RequestParam(value = "rId", required = true) Integer rId, Model model) {

        Ration ration = rationRepository.findById(rId);
        model.addAttribute("ration", ration);
        return "addDays";
    }

    @RequestMapping(value = "/addjs", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody String findRecipeJS(@RequestBody RationWrapper rWR) {
        System.out.println(rWR);
        List<Recipe> listR = recipeService.findRecipes(rWR);
        Map<Integer, String> mapR = new HashMap<>();
        for (Recipe r: listR) {
            mapR.put(r.getId(), r.toStringWithCalory(r.calcCalory()));
        }

        String s = new Gson().toJson(mapR);

        ByteBuffer buffer = StandardCharsets.UTF_8.encode(s);

        String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
        //return "redirect:/recipies/findall";
        return utf8EncodedString;
    }
}
