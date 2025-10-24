package edu.dosw.taller.controller;

import edu.dosw.taller.controller.dtos.*;
import edu.dosw.taller.model.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // 1, 2, 3: Registrar receta según tipo de autor
    @PostMapping("/televidente")
    public ResponseEntity<RecipeResponseDto> createViewerRecipe(@RequestBody RecipeRequestDto dto) {
        dto.getAuthor().setType("VIEWER");
        return ResponseEntity.ok(recipeService.createRecipe(dto));
    }

    @PostMapping("/participante")
    public ResponseEntity<RecipeResponseDto> createParticipantRecipe(@RequestBody RecipeRequestDto dto) {
        dto.getAuthor().setType("PARTICIPANT");
        return ResponseEntity.ok(recipeService.createRecipe(dto));
    }

    @PostMapping("/chef")
    public ResponseEntity<RecipeResponseDto> createChefRecipe(@RequestBody RecipeRequestDto dto) {
        dto.getAuthor().setType("CHEF_JUDGE");
        return ResponseEntity.ok(recipeService.createRecipe(dto));
    }

    // 4: Listar todas
    @GetMapping
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.searchAllRecipes());
    }

    // 5: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable String id) {
        return ResponseEntity.ok(recipeService.searchRecipeById(id));
    }

    // 6, 7, 8: Listar por tipo de autor
    @GetMapping("/participantes")
    public ResponseEntity<List<RecipeResponseDto>> getParticipantRecipes() {
        return ResponseEntity.ok(recipeService.searchRecipesByAuthorType("PARTICIPANT"));
    }

    @GetMapping("/televidentes")
    public ResponseEntity<List<RecipeResponseDto>> getViewerRecipes() {
        return ResponseEntity.ok(recipeService.searchRecipesByAuthorType("VIEWER"));
    }

    @GetMapping("/chefs")
    public ResponseEntity<List<RecipeResponseDto>> getChefRecipes() {
        return ResponseEntity.ok(recipeService.searchRecipesByAuthorType("CHEF_JUDGE"));
    }

    // 9: Recetas por temporada
    @GetMapping("/temporada/{season}")
    public ResponseEntity<List<RecipeResponseDto>> getRecipesBySeason(@PathVariable Integer season) {
        return ResponseEntity.ok(recipeService.searchRecipesBySeason(season));
    }

    // 10: Buscar por ingrediente
    @GetMapping("/ingrediente/{ingredient}")
    public ResponseEntity<List<RecipeResponseDto>> getRecipesByIngredient(@PathVariable String ingredient) {
        return ResponseEntity.ok(recipeService.searchRecipesByIngredient(ingredient));
    }

    // 11: Eliminar receta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    // 12: Actualizar receta
    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> updateRecipe(@PathVariable String id, @RequestBody RecipeRequestDto dto) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, dto));
    }
}