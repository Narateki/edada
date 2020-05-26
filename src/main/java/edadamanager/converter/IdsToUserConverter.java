package edadamanager.converter;

import edadamanager.model.Ingredient;
import edadamanager.model.User;
import edadamanager.repository.IngredientRepository;
import edadamanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

public class IdsToUserConverter implements Converter<Set<Integer>, Set<User>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<User> convert(Set<Integer> ids) {
        return userRepository.findAllByIdIn(ids);
    }
}
