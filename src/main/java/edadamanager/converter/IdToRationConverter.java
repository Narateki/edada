package edadamanager.converter;

import edadamanager.model.Ration;
import edadamanager.model.Recipe;
import edadamanager.repository.RationRepository;
import edadamanager.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class IdToRationConverter implements Converter<Integer, Ration> {
    @Autowired
    private RationRepository rationRepository;

    @Override
    public Ration convert(Integer id) {
        return rationRepository.findById(id);
    }
}
