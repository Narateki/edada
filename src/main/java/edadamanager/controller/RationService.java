package edadamanager.controller;

import edadamanager.converter.IdsToRecipes;
import edadamanager.model.Day;
import edadamanager.model.Ration;
import edadamanager.model.Recipe;
import edadamanager.repository.*;
import edadamanager.wrapper.DayWrapper;
import edadamanager.wrapper.RationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service("rationService")
public class RationService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RationRepository rationRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public Ration saveDays(RationWrapper rWR) {
        List<Day> daySet = new ArrayList<>();
        for (int i = 0; i < rWR.getMasl().size(); i++){
            Day day = new Day(rWR.getMileage().get(i), rWR.getMasl().get(i), rWR.getTotalRise().get(i));
            daySet.add(day);
        }
        Ration ration = rationRepository.findById(rWR.getId());
        ration.setDays(daySet);
        return rationRepository.save(ration);
    }

    public Ration saveDays(DayWrapper dayWrapper) {
        Ration ration = rationRepository.findById(dayWrapper.getId());
//        List<Recipe> breakfast = recipeRepository.findAllByIdIn(dayWrapper.getBreakfast());
//        List<Recipe> lunch = recipeRepository.findAllByIdIn(dayWrapper.getLunch());
//        List<Recipe> dinner = recipeRepository.findAllByIdIn(dayWrapper.getDinner());
        List<Day> daySet = ration.getDays();
        for (int i = 0; i < dayWrapper.getBreakfast().size(); i++){
            daySet.get(i).setBreakfast(recipeRepository.findOne(dayWrapper.getBreakfast().get(i)));
            daySet.get(i).setLunch(recipeRepository.findOne(dayWrapper.getLunch().get(i)));
            daySet.get(i).setDinner(recipeRepository.findOne(dayWrapper.getDinner().get(i)));
        }
        return rationRepository.save(ration);
    }

    public Page<Ration> findAllPages(Pageable page) {
        return rationRepository.findAllPages(page);
    }
}
