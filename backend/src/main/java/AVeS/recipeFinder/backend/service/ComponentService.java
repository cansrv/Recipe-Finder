package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.entity.dto.ComponentDTO;
import AVeS.recipeFinder.backend.entity.model.Component;
import AVeS.recipeFinder.backend.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentService {

    private  ComponentRepository componentRepository;
    private IngredientService ingredientService;

    @Autowired
    public ComponentService(ComponentRepository componentRepository, IngredientService ingredientService) {
        this.componentRepository = componentRepository;
        this.ingredientService = ingredientService;
    }

    public Component addComponent(ComponentDTO componentDTO) {
        Component component = Component.builder()
                .ingredient(ingredientService.getIngredientById(componentDTO.getIngredientId()))
                .quantity(componentDTO.getQuantity())
                .unit(componentDTO.getUnit())
                .build();

        return componentRepository.save(component);
    }

    public void addComponent(Component component){
        componentRepository.save(component);
    }

    public void deleteComponent(Long id) {
        componentRepository.deleteById(id);
    }

    public void updateComponent(Component component) {
        componentRepository.save(component);
    }

    public Component getComponentById(Long id) {
        return componentRepository.findById(id).orElse(null);
    }





}
