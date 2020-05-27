package edadamanager.controller;

import com.google.gson.Gson;
import edadamanager.model.Day;
import edadamanager.model.Ration;
import edadamanager.model.Recipe;
import edadamanager.repository.*;
import edadamanager.wrapper.DayWrapper;
import edadamanager.wrapper.RationWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
    private RationService rationService;
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
        List<Day> sdays = new ArrayList<>();
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

    @RequestMapping(value = "/addjs", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    public @ResponseBody String findRecipeJS(@RequestBody RationWrapper rWR) {
        System.out.println(rWR);
        Ration ration = rationService.saveDays(rWR);
        List<Recipe> listR = recipeService.findRecipes(ration);
        List<Map<Integer, String>> recipesForDays = new ArrayList<>();
        for (Day d : ration.getDays()) {
            Double dCalory = ration.calcDayCalory(d);
            Map<Integer, String> mapR = new HashMap<>();
            for (Recipe r: listR) {
                mapR.put(r.getId(), r.toStringWithCalory(dCalory));
            }
            recipesForDays.add(mapR);
        }


        String s = new Gson().toJson(recipesForDays);

        ByteBuffer buffer = StandardCharsets.UTF_8.encode(s);

        String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();

        return utf8EncodedString;
    }

    @RequestMapping(value = "/saveDays", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    public ModelAndView saveDays(@RequestBody DayWrapper dayWrapper) {
        Ration r = rationService.saveDays(dayWrapper);
        return new ModelAndView("endSave", "msg", "Рацион "+r.getName()+" успешно сохранен.");
    }

    @RequestMapping(value="/ration", params="id")
    public String getCoutryByCode(@RequestParam(value = "id") Ration ration, Model model){
        model.addAttribute("ration", ration);
        return "ration";
    }

    @RequestMapping(value="/rations")
    public String getCoutryByCode(Pageable page, Model model){
        model.addAttribute("page", rationService.findAllPages(page));
        return "rations";
    }
}
