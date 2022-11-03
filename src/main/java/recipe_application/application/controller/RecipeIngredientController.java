package recipe_application.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_application.application.data.service.RecipeIngredientService;
import recipe_application.application.dto.forms.recipeIngredientForm.AddRecipeForm;
import recipe_application.application.dto.forms.recipeIngredientForm.CreateRecipeIngredientForm;
import recipe_application.application.dto.forms.recipeIngredientForm.UpdateRecipeIngredientForm;
import recipe_application.application.dto.views.RecipeIngredientView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/v1/recipe-ingredient")
@CrossOrigin("*")
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;


    @Autowired
    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<RecipeIngredientView>> getRecipeIngredientList() {
        return ResponseEntity.ok(recipeIngredientService.findAll());
    }

    @GetMapping("/ingredient-id/{ingredientId}")
    public ResponseEntity<List<RecipeIngredientView>> getRecipeIngredientByIngredientId(@PathVariable("ingredientId") Integer ingredientId) {
        return ResponseEntity.ok(recipeIngredientService.findAllByIngredientId(ingredientId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RecipeIngredientView> getRecipeIngredientById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(recipeIngredientService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<RecipeIngredientView> addRecipeIngredient(@Valid @RequestBody CreateRecipeIngredientForm createRecipeIngredientForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeIngredientService.save(createRecipeIngredientForm));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecipeIngredientView> updateRecipeIngredient(@PathVariable("id") Integer id, @Valid @RequestBody UpdateRecipeIngredientForm updateRecipeIngredientForm) {
        updateRecipeIngredientForm.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeIngredientService.update(updateRecipeIngredientForm));
    }

    @PostMapping("/add-recipe")
    public ResponseEntity<RecipeIngredientView> addRecipeToRecipeIngredient(@Valid @RequestBody AddRecipeForm addRecipeForm) {
        recipeIngredientService.addRecipe(addRecipeForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeIngredientService.addRecipe(addRecipeForm));
    }

    @GetMapping("/remove-recipe/{id}")
    public ResponseEntity<Void> removeRecipeFromRecipeIngredient(@PathVariable("id") Integer id) {
        recipeIngredientService.removeRecipe(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        recipeIngredientService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
