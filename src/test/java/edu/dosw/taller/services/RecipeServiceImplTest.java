package edu.dosw.taller.services;

import edu.dosw.taller.controller.dtos.*;
import edu.dosw.taller.controller.Exception.*;
import edu.dosw.taller.model.entities.*;
import edu.dosw.taller.model.services.*;
import edu.dosw.taller.model.persistence.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    private RecipeRequestDto recipeRequestDto;
    private Recipe recipe;
    private AuthorDto authorDto;
    private IngredientDto ingredientDto;
    private StepDto stepDto;

    @BeforeEach
    void setUp() {
        authorDto = new AuthorDto();
        authorDto.setName("Chef Mario");
        authorDto.setType("CHEF_JUDGE");

        ingredientDto = new IngredientDto();
        ingredientDto.setName("pasta");
        ingredientDto.setQuantity("200 gramos");
        ingredientDto.setUnit("gramos");

        stepDto = new StepDto();
        stepDto.setOrder(1);
        stepDto.setDescription("Hervir agua con sal");

        recipeRequestDto = new RecipeRequestDto();
        recipeRequestDto.setTitle("Pasta Carbonara");
        recipeRequestDto.setAuthor(authorDto);
        recipeRequestDto.setIngredients(Arrays.asList(ingredientDto));
        recipeRequestDto.setSteps(Arrays.asList(stepDto));

        recipe = new Recipe();
        recipe.setId("1");
        recipe.setTitle("Pasta Carbonara");
        recipe.setAuthor(new Author("Chef Mario", AuthorType.CHEF_JUDGE));
        recipe.setIngredients(Arrays.asList(new Ingredient("pasta", "200 gramos", "gramos")));
        recipe.setSteps(Arrays.asList(new Step(1, "Hervir agua con sal")));
        recipe.setSeason(null);
        recipe.setCreatedAt(Instant.now());
        recipe.setUpdatedAt(Instant.now());
    }


    @Test
    void testCreateRecipe_Success() {

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);


        RecipeResponseDto result = recipeService.createRecipe(recipeRequestDto);

        assertNotNull(result);
        assertEquals("Pasta Carbonara", result.getTitle());
        assertEquals("Chef Mario", result.getAuthor().getName());
        assertEquals("CHEF_JUDGE", result.getAuthor().getType());
        assertEquals(1, result.getIngredients().size());
        assertEquals("pasta", result.getIngredients().get(0).getName());
        assertEquals("200 gramos", result.getIngredients().get(0).getQuantity());
        assertEquals(1, result.getSteps().size());
        assertEquals("Hervir agua con sal", result.getSteps().get(0).getDescription());
        
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testSearchRecipesByIngredient_ReturnsCorrectResults() {

        String ingredient = "pasta";
        List<Recipe> recipes = Arrays.asList(recipe);
        when(recipeRepository.findByIngredientsNameContainingIgnoreCase(ingredient))
            .thenReturn(recipes);


        List<RecipeResponseDto> result = recipeService.searchRecipesByIngredient(ingredient);


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pasta Carbonara", result.get(0).getTitle());
        assertEquals("pasta", result.get(0).getIngredients().get(0).getName());
        assertEquals("200 gramos", result.get(0).getIngredients().get(0).getQuantity());
        
        verify(recipeRepository, times(1))
            .findByIngredientsNameContainingIgnoreCase(ingredient);
    }

    @Test
    void testSearchRecipeById_NotFound_ThrowsException() {

        String nonExistentId = "999";
        when(recipeRepository.findById(nonExistentId)).thenReturn(Optional.empty());


        RecipeNotFoundException exception = assertThrows(
            RecipeNotFoundException.class,
            () -> recipeService.searchRecipeById(nonExistentId)
        );
        
        assertEquals("Receta no encontrada con ID: 999", exception.getMessage());
        verify(recipeRepository, times(1)).findById(nonExistentId);
    }


// -----------------------------

    @Test
    void testCreateRecipe_WithParticipant_Success() {

        authorDto.setName("Juan Pérez");
        authorDto.setType("PARTICIPANT");
        recipeRequestDto.setSeason(3);
        
        Recipe participantRecipe = new Recipe();
        participantRecipe.setId("2");
        participantRecipe.setTitle("Pasta Carbonara");
        participantRecipe.setAuthor(new Author("Juan Pérez", AuthorType.PARTICIPANT));
        participantRecipe.setIngredients(Arrays.asList(new Ingredient("pasta", "200 gramos", "gramos")));
        participantRecipe.setSteps(Arrays.asList(new Step(1, "Hervir agua con sal")));
        participantRecipe.setSeason(3);
        participantRecipe.setCreatedAt(Instant.now());
        participantRecipe.setUpdatedAt(Instant.now());
        
        when(recipeRepository.save(any(Recipe.class))).thenReturn(participantRecipe);


        RecipeResponseDto result = recipeService.createRecipe(recipeRequestDto);

        assertNotNull(result);
        assertEquals("Pasta Carbonara", result.getTitle());
        assertEquals(3, result.getSeason());
        assertEquals("PARTICIPANT", result.getAuthor().getType());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testCreateRecipe_WithViewer_Success() {

        authorDto.setName("María García");
        authorDto.setType("VIEWER");
        
        Recipe viewerRecipe = new Recipe();
        viewerRecipe.setId("3");
        viewerRecipe.setTitle("Pasta Carbonara");
        viewerRecipe.setAuthor(new Author("María García", AuthorType.VIEWER));
        viewerRecipe.setIngredients(Arrays.asList(new Ingredient("pasta", "200 gramos", "gramos")));
        viewerRecipe.setSteps(Arrays.asList(new Step(1, "Hervir agua con sal")));
        viewerRecipe.setSeason(null);
        viewerRecipe.setCreatedAt(Instant.now());
        viewerRecipe.setUpdatedAt(Instant.now());
        
        when(recipeRepository.save(any(Recipe.class))).thenReturn(viewerRecipe);

        RecipeResponseDto result = recipeService.createRecipe(recipeRequestDto);


        assertNotNull(result);
        assertEquals("Pasta Carbonara", result.getTitle());
        assertEquals("VIEWER", result.getAuthor().getType());
        assertNull(result.getSeason());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }



    @Test
    void testCreateRecipe_TitleEmpty_ThrowsException() {
        recipeRequestDto.setTitle("");


        RecipeValidationException exception = assertThrows(
            RecipeValidationException.class,
            () -> recipeService.createRecipe(recipeRequestDto)
        );
        
        assertEquals("El título de la receta es obligatorio", exception.getMessage());
        verify(recipeRepository, never()).save(any(Recipe.class));
    }

    @Test
    void testCreateRecipe_NoIngredients_ThrowsException() {

        recipeRequestDto.setIngredients(Arrays.asList());


        RecipeValidationException exception = assertThrows(
            RecipeValidationException.class,
            () -> recipeService.createRecipe(recipeRequestDto)
        );
        
        assertEquals("La receta debe tener al menos un ingrediente", exception.getMessage());
        verify(recipeRepository, never()).save(any(Recipe.class));
    }

    @Test
    void testCreateRecipe_NoSteps_ThrowsException() {

        recipeRequestDto.setSteps(Arrays.asList());


        RecipeValidationException exception = assertThrows(
            RecipeValidationException.class,
            () -> recipeService.createRecipe(recipeRequestDto)
        );
        
        assertEquals("La receta debe tener al menos un paso", exception.getMessage());
        verify(recipeRepository, never()).save(any(Recipe.class));
    }

    @Test
    void testCreateRecipe_InvalidAuthorType_ThrowsException() {

        authorDto.setType("INVALID_TYPE");


        InvalidAuthorTypeException exception = assertThrows(
            InvalidAuthorTypeException.class,
            () -> recipeService.createRecipe(recipeRequestDto)
        );
        
        assertTrue(exception.getMessage().contains("Tipo de autor no válido"));
        verify(recipeRepository, never()).save(any(Recipe.class));
    }

    @Test
    void testCreateRecipe_ParticipantWithoutSeason_ThrowsException() {

        authorDto.setType("PARTICIPANT");
        recipeRequestDto.setSeason(null);

        InvalidSeasonException exception = assertThrows(
            InvalidSeasonException.class,
            () -> recipeService.createRecipe(recipeRequestDto)
        );
        
        assertEquals("La temporada es obligatoria para recetas de participantes", exception.getMessage());
        verify(recipeRepository, never()).save(any(Recipe.class));
    }



    @Test
    void testSearchRecipesByIngredient_EmptyIngredient_ThrowsException() {

        String emptyIngredient = "";


        RecipeValidationException exception = assertThrows(
            RecipeValidationException.class,
            () -> recipeService.searchRecipesByIngredient(emptyIngredient)
        );
        
        assertEquals("El ingrediente a buscar no puede estar vacío", exception.getMessage());
        verify(recipeRepository, never()).findByIngredientsNameContainingIgnoreCase(any());
    }

    @Test
    void testSearchRecipesByIngredient_NoResults_ReturnsEmptyList() {

        String ingredient = "chocolate";
        when(recipeRepository.findByIngredientsNameContainingIgnoreCase(ingredient))
            .thenReturn(Arrays.asList());

        List<RecipeResponseDto> result = recipeService.searchRecipesByIngredient(ingredient);


        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(recipeRepository, times(1))
            .findByIngredientsNameContainingIgnoreCase(ingredient);
    }

    @Test
    void testSearchRecipesByIngredient_MultipleResults() {
        String ingredient = "tomate";
        Recipe recipe2 = new Recipe();
        recipe2.setId("2");
        recipe2.setTitle("Salsa de Tomate");
        recipe2.setAuthor(new Author("Chef Ana", AuthorType.CHEF_JUDGE));
        recipe2.setIngredients(Arrays.asList(new Ingredient("tomate", "3 unidades", "unidades")));
        recipe2.setSteps(Arrays.asList(new Step(1, "Cortar tomates")));
        recipe2.setCreatedAt(Instant.now());
        recipe2.setUpdatedAt(Instant.now());

        when(recipeRepository.findByIngredientsNameContainingIgnoreCase(ingredient))
            .thenReturn(Arrays.asList(recipe, recipe2));


        List<RecipeResponseDto> result = recipeService.searchRecipesByIngredient(ingredient);


        assertNotNull(result);
        assertEquals(2, result.size());
        verify(recipeRepository, times(1))
            .findByIngredientsNameContainingIgnoreCase(ingredient);
    }



    @Test
    void testSearchRecipeById_Found_ReturnsRecipe() {

        String existingId = "1";
        when(recipeRepository.findById(existingId)).thenReturn(Optional.of(recipe));


        RecipeResponseDto result = recipeService.searchRecipeById(existingId);


        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Pasta Carbonara", result.getTitle());
        assertEquals("Chef Mario", result.getAuthor().getName());
        assertEquals("pasta", result.getIngredients().get(0).getName());
        verify(recipeRepository, times(1)).findById(existingId);
    }


    @Test
    void testDeleteRecipe_NotFound_ThrowsException() {

        String nonExistentId = "999";
        when(recipeRepository.existsById(nonExistentId)).thenReturn(false);


        RecipeNotFoundException exception = assertThrows(
            RecipeNotFoundException.class,
            () -> recipeService.deleteRecipe(nonExistentId)
        );
        
        assertEquals("No se puede eliminar. Receta no encontrada con ID: 999", exception.getMessage());
        verify(recipeRepository, never()).deleteById(any());
    }

    @Test
    void testDeleteRecipe_Success() {
        String existingId = "1";
        when(recipeRepository.existsById(existingId)).thenReturn(true);


        recipeService.deleteRecipe(existingId);


        verify(recipeRepository, times(1)).existsById(existingId);
        verify(recipeRepository, times(1)).deleteById(existingId);
    }



    @Test
    void testSearchAllRecipes_ReturnsAllRecipes() {

        List<Recipe> recipes = Arrays.asList(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);


        List<RecipeResponseDto> result = recipeService.searchAllRecipes();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pasta Carbonara", result.get(0).getTitle());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void testSearchAllRecipes_EmptyDatabase_ReturnsEmptyList() {

        when(recipeRepository.findAll()).thenReturn(Arrays.asList());


        List<RecipeResponseDto> result = recipeService.searchAllRecipes();


        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(recipeRepository, times(1)).findAll();
    }



    @Test
    void testSearchRecipesByAuthorType_Success() {

        String authorType = "CHEF_JUDGE";
        when(recipeRepository.findByAuthorType(AuthorType.CHEF_JUDGE))
            .thenReturn(Arrays.asList(recipe));


        List<RecipeResponseDto> result = recipeService.searchRecipesByAuthorType(authorType);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CHEF_JUDGE", result.get(0).getAuthor().getType());
        verify(recipeRepository, times(1)).findByAuthorType(AuthorType.CHEF_JUDGE);
    }

    @Test
    void testSearchRecipesByAuthorType_InvalidType_ThrowsException() {
        String invalidType = "INVALID_TYPE";


        InvalidAuthorTypeException exception = assertThrows(
            InvalidAuthorTypeException.class,
            () -> recipeService.searchRecipesByAuthorType(invalidType)
        );
        
        assertTrue(exception.getMessage().contains("Tipo de autor no válido"));
        verify(recipeRepository, never()).findByAuthorType(any());
    }



    @Test
    void testSearchRecipesBySeason_Success() {

        Integer season = 3;
        recipe.setSeason(season);
        when(recipeRepository.findBySeason(season)).thenReturn(Arrays.asList(recipe));


        List<RecipeResponseDto> result = recipeService.searchRecipesBySeason(season);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(season, result.get(0).getSeason());
        verify(recipeRepository, times(1)).findBySeason(season);
    }

    @Test
    void testSearchRecipesBySeason_InvalidSeason_ThrowsException() {
 
        Integer invalidSeason = -1;


        InvalidSeasonException exception = assertThrows(
            InvalidSeasonException.class,
            () -> recipeService.searchRecipesBySeason(invalidSeason)
        );
        
        assertEquals("La temporada debe ser un número positivo", exception.getMessage());
        verify(recipeRepository, never()).findBySeason(any());
    }



    @Test
    void testUpdateRecipe_Success() {

        String recipeId = "1";
        RecipeRequestDto updateDto = new RecipeRequestDto();
        updateDto.setTitle("Pasta Carbonara Actualizada");
        updateDto.setAuthor(authorDto);
        updateDto.setIngredients(Arrays.asList(ingredientDto));
        updateDto.setSteps(Arrays.asList(stepDto));

        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(recipeId);
        updatedRecipe.setTitle("Pasta Carbonara Actualizada");
        updatedRecipe.setAuthor(new Author("Chef Mario", AuthorType.CHEF_JUDGE));
        updatedRecipe.setIngredients(Arrays.asList(new Ingredient("pasta", "200 gramos", "gramos")));
        updatedRecipe.setSteps(Arrays.asList(new Step(1, "Hervir agua con sal")));
        updatedRecipe.setCreatedAt(Instant.now());
        updatedRecipe.setUpdatedAt(Instant.now());

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(updatedRecipe);


        RecipeResponseDto result = recipeService.updateRecipe(recipeId, updateDto);

        assertNotNull(result);
        assertEquals("Pasta Carbonara Actualizada", result.getTitle());
        verify(recipeRepository, times(1)).findById(recipeId);
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testUpdateRecipe_NotFound_ThrowsException() {

        String nonExistentId = "999";
        when(recipeRepository.findById(nonExistentId)).thenReturn(Optional.empty());


        RecipeNotFoundException exception = assertThrows(
            RecipeNotFoundException.class,
            () -> recipeService.updateRecipe(nonExistentId, recipeRequestDto)
        );
        
        assertEquals("Receta no encontrada con ID: 999", exception.getMessage());
        verify(recipeRepository, times(1)).findById(nonExistentId);
        verify(recipeRepository, never()).save(any(Recipe.class));
    }
}