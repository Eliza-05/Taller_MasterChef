package edu.dosw.taller.model.services;

import edu.dosw.taller.controller.dtos.*;
import edu.dosw.taller.controller.Exception.*;
import edu.dosw.taller.model.entities.*;
import edu.dosw.taller.model.persistence.repository.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Crea una nueva receta a partir de los datos del DTO
     */
    @Override
    public RecipeResponseDto createRecipe(RecipeRequestDto dto) {
        
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new RecipeValidationException("El título de la receta es obligatorio");
        }
        
        if (dto.getIngredients() == null || dto.getIngredients().isEmpty()) {
            throw new RecipeValidationException("La receta debe tener al menos un ingrediente");
        }
        
        if (dto.getSteps() == null || dto.getSteps().isEmpty()) {
            throw new RecipeValidationException("La receta debe tener al menos un paso");
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());

        List<Ingredient> ingredients = dto.getIngredients().stream()
            .map(ingredientDto -> new Ingredient(
                ingredientDto.getName(),
                ingredientDto.getQuantity(),
                ingredientDto.getUnit()
            ))
            .toList();
        recipe.setIngredients(ingredients);

        List<Step> steps = dto.getSteps().stream()
            .map(stepDto -> new Step(
                stepDto.getOrder(),
                stepDto.getDescription()
            ))
            .toList();
        recipe.setSteps(steps);

        AuthorType authorType;
        try {
            authorType = AuthorType.valueOf(dto.getAuthor().getType());
        } catch (IllegalArgumentException e) {
            throw new InvalidAuthorTypeException("Tipo de autor no válido: " + dto.getAuthor().getType() + 
                ". Valores permitidos: VIEWER, PARTICIPANT, CHEF_JUDGE");
        }
        
        Author author = new Author(dto.getAuthor().getName(), authorType);
        recipe.setAuthor(author);

        if (authorType == AuthorType.PARTICIPANT) {
            if (dto.getSeason() == null) {
                throw new InvalidSeasonException("La temporada es obligatoria para recetas de participantes");
            }
            if (dto.getSeason() <= 0) {
                throw new InvalidSeasonException("La temporada debe ser un número positivo");
            }
            recipe.setSeason(dto.getSeason());
        }

        recipe.setCreatedAt(Instant.now());
        recipe.setUpdatedAt(Instant.now());

        Recipe savedRecipe = recipeRepository.save(recipe);
        return mapToResponseDto(savedRecipe);
    }

    /**
     * Busca todas las recetas
     */
    @Override
    public List<RecipeResponseDto> searchAllRecipes() {
        return recipeRepository.findAll().stream()
            .map(this::mapToResponseDto)
            .toList();
    }

    /**
     * Busca una receta por ID
     */
    @Override
    public RecipeResponseDto searchRecipeById(String id) {
        Recipe recipe = recipeRepository.findById(id)
            .orElseThrow(() -> new RecipeNotFoundException("Receta no encontrada con ID: " + id));
                
        return mapToResponseDto(recipe);
    }

    /**
     * Busca recetas por tipo de autor
     */
    @Override
    public List<RecipeResponseDto> searchRecipesByAuthorType(String type) {
        AuthorType authorType;
        try {
            authorType = AuthorType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new InvalidAuthorTypeException("Tipo de autor no válido: " + type + 
                ". Valores permitidos: VIEWER, PARTICIPANT, CHEF_JUDGE");
        }
        
        return recipeRepository.findByAuthorType(authorType).stream()
            .map(this::mapToResponseDto)
            .toList();
    }

    /**
     * Busca recetas por temporada
     */
    @Override
    public List<RecipeResponseDto> searchRecipesBySeason(Integer season) {
        if (season <= 0) {
            throw new InvalidSeasonException("La temporada debe ser un número positivo");
        }
        
        return recipeRepository.findBySeason(season).stream()
            .map(this::mapToResponseDto)
            .toList();
    }

    /**
     * Busca recetas por ingrediente
     */
    @Override
    public List<RecipeResponseDto> searchRecipesByIngredient(String ingredient) {
        if (ingredient == null || ingredient.trim().isEmpty()) {
            throw new RecipeValidationException("El ingrediente a buscar no puede estar vacío");
        }
        
        return recipeRepository.findByIngredientsNameContainingIgnoreCase(ingredient).stream()
            .map(this::mapToResponseDto)
            .toList();
    }

    /**
     * Elimina una receta por ID
     */
    @Override
    public void deleteRecipe(String id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException("No se puede eliminar. Receta no encontrada con ID: " + id);
        }
        recipeRepository.deleteById(id);
    }

    /**
     * Actualiza una receta existente
     */
    @Override
    public RecipeResponseDto updateRecipe(String id, RecipeRequestDto dto) {
        Recipe existingRecipe = recipeRepository.findById(id)
            .orElseThrow(() -> new RecipeNotFoundException("Receta no encontrada con ID: " + id));

        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new RecipeValidationException("El título de la receta es obligatorio");
        }

        existingRecipe.setTitle(dto.getTitle());
        
        List<Ingredient> ingredients = dto.getIngredients().stream()
            .map(ingredientDto -> new Ingredient(
                ingredientDto.getName(),
                ingredientDto.getQuantity(),
                ingredientDto.getUnit()
            ))
            .toList();
        existingRecipe.setIngredients(ingredients);
        
        List<Step> steps = dto.getSteps().stream()
            .map(stepDto -> new Step(
                stepDto.getOrder(),
                stepDto.getDescription()
            ))
            .toList();
        existingRecipe.setSteps(steps);
        
        AuthorType authorType;
        try {
            authorType = AuthorType.valueOf(dto.getAuthor().getType());
        } catch (IllegalArgumentException e) {
            throw new InvalidAuthorTypeException("Tipo de autor no válido: " + dto.getAuthor().getType());
        }
        
        Author author = new Author(dto.getAuthor().getName(), authorType);
        existingRecipe.setAuthor(author);
        
        if (authorType == AuthorType.PARTICIPANT) {
            if (dto.getSeason() == null) {
                throw new InvalidSeasonException("La temporada es obligatoria para recetas de participantes");
            }
            if (dto.getSeason() <= 0) {
                throw new InvalidSeasonException("La temporada debe ser un número positivo");
            }
            existingRecipe.setSeason(dto.getSeason());
        } else {
            existingRecipe.setSeason(null); 
        }

        existingRecipe.setUpdatedAt(Instant.now());
        
        Recipe updatedRecipe = recipeRepository.save(existingRecipe);
        return mapToResponseDto(updatedRecipe);
    }
    
    /**
     * Mapea una entidad Recipe a un DTO de respuesta
     */
    private RecipeResponseDto mapToResponseDto(Recipe recipe) {
        RecipeResponseDto dto = new RecipeResponseDto();
        dto.setId(recipe.getId());
        dto.setTitle(recipe.getTitle());
        
        List<IngredientDto> ingredients = recipe.getIngredients().stream()
            .map(ingredient -> {
                IngredientDto ingredientDto = new IngredientDto();
                ingredientDto.setName(ingredient.getName());
                ingredientDto.setQuantity(ingredient.getQuantity());
                ingredientDto.setUnit(ingredient.getUnit());
                return ingredientDto;
            })
            .toList();
        dto.setIngredients(ingredients);
        
        List<StepDto> steps = recipe.getSteps().stream()
            .map(step -> {
                StepDto stepDto = new StepDto();
                stepDto.setOrder(step.getOrder());
                stepDto.setDescription(step.getDescription());
                return stepDto;
            })
            .toList();
        dto.setSteps(steps);
        
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(recipe.getAuthor().getName());
        authorDto.setType(recipe.getAuthor().getType().name());
        dto.setAuthor(authorDto);
        
        dto.setSeason(recipe.getSeason());
        
        dto.setCreatedAt(recipe.getCreatedAt());
        dto.setUpdatedAt(recipe.getUpdatedAt());
        
        return dto;
    }
}