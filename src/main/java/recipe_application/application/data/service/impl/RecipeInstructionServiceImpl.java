package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.converter.Converter;
import recipe_application.application.data.repo.RecipeInstructionRepository;
import recipe_application.application.data.repo.RecipeRepository;
import recipe_application.application.data.service.RecipeInstructionService;
import recipe_application.application.dto.forms.recipeInstructionForm.CreateRecipeInstructionForm;
import recipe_application.application.dto.forms.recipeInstructionForm.UpdateRecipeInstructionForm;
import recipe_application.application.dto.views.RecipeInstructionView;
import recipe_application.application.exception.ResourceNotFoundException;
import recipe_application.application.model.Recipe;
import recipe_application.application.model.RecipeInstruction;

import java.util.Collection;
import java.util.List;


@Service
public class RecipeInstructionServiceImpl implements RecipeInstructionService {

    private final RecipeInstructionRepository recipeInstructionRepository;
    private final RecipeRepository recipeRepository;
    private final Converter converter;

    @Autowired
    public RecipeInstructionServiceImpl(RecipeInstructionRepository recipeInstructionRepository, RecipeRepository recipeRepository, Converter converter) {
        this.recipeInstructionRepository = recipeInstructionRepository;
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    @Override
    public RecipeInstructionView save(CreateRecipeInstructionForm createRecipeInstructionForm) {
        if(createRecipeInstructionForm == null ){
            throw new IllegalArgumentException ("createRecipeInstructionForm is null");
        }

        RecipeInstruction recipeInstruction = new RecipeInstruction(
                createRecipeInstructionForm.getTitle(),
                createRecipeInstructionForm.getInstruction()
        );

        RecipeInstruction savedRecipeInstruction = recipeInstructionRepository.save(recipeInstruction);
        return converter.recipeInstructionToView(savedRecipeInstruction);
    }

    @Override
    public RecipeInstructionView findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeInstructionRepository.findById(id).isPresent()){
            converter.recipeInstructionToView(recipeInstructionRepository.findById(id).get());
        }

        throw new ResourceNotFoundException("Recipe instruction with id " + id + " not found.");
    }

    @Override
    public Collection<RecipeInstructionView> findAll() {
        Collection<RecipeInstruction> recipeInstructionList = (Collection<RecipeInstruction>) recipeInstructionRepository.findAll();
        return converter.recipeInstructionListToViewList(recipeInstructionList);
    }

    @Override
    public RecipeInstructionView update(UpdateRecipeInstructionForm updateRecipeInstructionForm) {
        if(updateRecipeInstructionForm == null ){
            throw new IllegalArgumentException ("updateRecipeInstructionForm is null");
        }

        RecipeInstruction recipeInstruction = recipeInstructionRepository.findById(updateRecipeInstructionForm.getId()).isPresent() ?
                recipeInstructionRepository.findById(updateRecipeInstructionForm.getId()).get() :
                null;

        if(recipeInstruction == null){
            throw new ResourceNotFoundException("Recipe instruction with id " + updateRecipeInstructionForm.getId() + " not found.");
        }

        recipeInstruction.setInstruction(updateRecipeInstructionForm.getInstruction());
        recipeInstruction.setTitle(updateRecipeInstructionForm.getTitle());
        recipeInstructionRepository.save(recipeInstruction);

        return converter.recipeInstructionToView(recipeInstruction);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeInstructionRepository.existsById(id)){
            if(isAssociated(id)){
                return false;
            }
        }

        recipeInstructionRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean delete(RecipeInstruction recipeInstruction) {
        if(recipeInstruction == null ){
            throw new IllegalArgumentException ("recipeInstruction is null");
        }

        if(recipeInstructionRepository.existsById(recipeInstruction.getId())){
            if(isAssociated(recipeInstruction.getId())){
                return false;
            }
        }

        recipeInstructionRepository.deleteById(recipeInstruction.getId());
        return true;
    }

    private boolean isAssociated(Integer id){
        List<Recipe> recipeList = recipeRepository.findAllByInstructionId(id);

        return recipeList.size() > 0;
    }

}
