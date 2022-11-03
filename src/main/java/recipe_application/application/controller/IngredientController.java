package recipe_application.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_application.application.data.service.IngredientService;
import recipe_application.application.dto.forms.ingredientForm.CreateIngredientForm;
import recipe_application.application.dto.forms.ingredientForm.UpdateIngredientForm;
import recipe_application.application.dto.views.IngredientView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/v1/ingredient")
@CrossOrigin("*")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    public ResponseEntity<Collection<IngredientView>> getIngredientList() {
        return ResponseEntity.ok(ingredientService.findAll());
    }

    public ResponseEntity<IngredientView> getIngredientByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(ingredientService.findByIngredientNameIgnoreCase(name));
    }

    public ResponseEntity<List<IngredientView>> getAllIngredientByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(ingredientService.findByIngredientNameContainsIgnoreCase(name));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchForIngredient(@RequestParam("search") String search)  {
        if(search.equalsIgnoreCase("list") || search.equalsIgnoreCase("all")){
            return getIngredientList();
        }
        else if(ingredientService.findByIngredientNameContainsIgnoreCase(search.toLowerCase()).size() > 0){
            return getAllIngredientByName(search);
        }

        return getIngredientByName(search);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<IngredientView> getIngredientById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<IngredientView> addIngredient(@Valid @RequestBody CreateIngredientForm createIngredientForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.save(createIngredientForm));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<IngredientView> updateIngredient(@PathVariable("id") Integer id, @Valid @RequestBody UpdateIngredientForm updateIngredientForm) {
        IngredientView ingredientView = ingredientService.findById(id);

        updateIngredientForm.setId(ingredientView.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.update(updateIngredientForm));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        ingredientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
