package edu.dosw.taller.controller;

import edu.dosw.taller.controller.dtos.*;
import edu.dosw.taller.model.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recetas")
@Tag(name = "Recetas", description = "API para gestión de recetas de MasterChef")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/televidente")
    @Operation(summary = "Crear receta de televidente", 
               description = "Crea una nueva receta enviada por un televidente")
    @ApiResponse(responseCode = "200", description = "Receta creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<RecipeResponseDto> createViewerRecipe(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la receta del televidente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RecipeRequestDto.class),
                examples = @ExampleObject(
                    name = "Receta de Televidente",
                    summary = "Ejemplo de receta enviada por un televidente",
                    value = """
                    {
                      "title": "Arepa Venezolana Casera",
                      "ingredients": [
                        {
                          "name": "harina de maíz precocida",
                          "quantity": "2",
                          "unit": "tazas"
                        },
                        {
                          "name": "agua tibia",
                          "quantity": "2.5",
                          "unit": "tazas"
                        },
                        {
                          "name": "sal",
                          "quantity": "1",
                          "unit": "cucharadita"
                        }
                      ],
                      "steps": [
                        {
                          "order": 1,
                          "description": "Mezclar la harina con sal en un bowl"
                        },
                        {
                          "order": 2,
                          "description": "Agregar agua tibia poco a poco mientras se mezcla"
                        },
                        {
                          "order": 3,
                          "description": "Amasar hasta obtener una masa suave"
                        },
                        {
                          "order": 4,
                          "description": "Formar bolitas y aplastar para hacer las arepas"
                        },
                        {
                          "order": 5,
                          "description": "Cocinar en plancha por 7 minutos de cada lado"
                        }
                      ],
                      "author": {
                        "name": "María González",
                        "type": "VIEWER"
                      }
                    }
                    """
                )
            )
        )
        @RequestBody RecipeRequestDto dto) {
        dto.getAuthor().setType("VIEWER");
        return ResponseEntity.ok(recipeService.createRecipe(dto));
    }

    @PostMapping("/participante")
    @Operation(summary = "Crear receta de participante", 
               description = "Crea una nueva receta de un participante del programa")
    public ResponseEntity<RecipeResponseDto> createParticipantRecipe(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la receta del participante",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Receta de Participante",
                    summary = "Ejemplo de receta de participante",
                    value = """
                    {
                      "title": "Risotto de Camarones al Vino Blanco",
                      "ingredients": [
                        {
                          "name": "arroz arborio",
                          "quantity": "300",
                          "unit": "gramos"
                        },
                        {
                          "name": "camarones frescos",
                          "quantity": "500",
                          "unit": "gramos"
                        },
                        {
                          "name": "vino blanco",
                          "quantity": "200",
                          "unit": "ml"
                        },
                        {
                          "name": "caldo de pescado",
                          "quantity": "1",
                          "unit": "litro"
                        },
                        {
                          "name": "queso parmesano",
                          "quantity": "100",
                          "unit": "gramos"
                        }
                      ],
                      "steps": [
                        {
                          "order": 1,
                          "description": "Limpiar y pelar los camarones, reservar las cáscaras para el caldo"
                        },
                        {
                          "order": 2,
                          "description": "Sofreír el arroz con aceite de oliva hasta que esté transparente"
                        },
                        {
                          "order": 3,
                          "description": "Agregar el vino blanco y cocinar hasta que se evapore"
                        },
                        {
                          "order": 4,
                          "description": "Añadir el caldo caliente de a poco, revolviendo constantemente"
                        },
                        {
                          "order": 5,
                          "description": "A los 15 minutos agregar los camarones"
                        },
                        {
                          "order": 6,
                          "description": "Terminar con queso parmesano y mantequilla"
                        }
                      ],
                      "author": {
                        "name": "Carlos Mendoza",
                        "type": "PARTICIPANT"
                      },
                      "season": 5
                    }
                    """
                )
            )
        )
        @RequestBody RecipeRequestDto dto) {
        dto.getAuthor().setType("PARTICIPANT");
        return ResponseEntity.ok(recipeService.createRecipe(dto));
    }

    @PostMapping("/chef")
    @Operation(summary = "Crear receta de chef", 
               description = "Crea una nueva receta creada por un chef juez")
    public ResponseEntity<RecipeResponseDto> createChefRecipe(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la receta del chef",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Receta de Chef",
                    summary = "Ejemplo de receta de chef profesional",
                    value = """
                    {
                      "title": "Pasta Carbonara Auténtica",
                      "ingredients": [
                        {
                          "name": "spaghetti",
                          "quantity": "400",
                          "unit": "gramos"
                        },
                        {
                          "name": "panceta italiana",
                          "quantity": "150",
                          "unit": "gramos"
                        },
                        {
                          "name": "huevos",
                          "quantity": "4",
                          "unit": "unidades"
                        },
                        {
                          "name": "queso pecorino romano",
                          "quantity": "100",
                          "unit": "gramos"
                        },
                        {
                          "name": "pimienta negra",
                          "quantity": "1",
                          "unit": "cucharadita"
                        }
                      ],
                      "steps": [
                        {
                          "order": 1,
                          "description": "Hervir agua con sal abundante para la pasta"
                        },
                        {
                          "order": 2,
                          "description": "Cortar la panceta en cubitos y dorar en sartén sin aceite"
                        },
                        {
                          "order": 3,
                          "description": "Batir huevos con queso pecorino y pimienta"
                        },
                        {
                          "order": 4,
                          "description": "Cocinar spaghetti al dente según instrucciones del paquete"
                        },
                        {
                          "order": 5,
                          "description": "Escurrir pasta reservando 1 taza del agua de cocción"
                        },
                        {
                          "order": 6,
                          "description": "Mezclar pasta caliente con panceta fuera del fuego"
                        },
                        {
                          "order": 7,
                          "description": "Agregar mezcla de huevos removiendo rápidamente para crear crema"
                        },
                        {
                          "order": 8,
                          "description": "Ajustar consistencia con agua de pasta si es necesario"
                        }
                      ],
                      "author": {
                        "name": "Chef Alessandro Borghese",
                        "type": "CHEF_JUDGE"
                      }
                    }
                    """
                )
            )
        )
        @RequestBody RecipeRequestDto dto) {
        dto.getAuthor().setType("CHEF_JUDGE");
        return ResponseEntity.ok(recipeService.createRecipe(dto));
    }

    @GetMapping
    @Operation(summary = "Obtener todas las recetas", 
               description = "Retorna una lista con todas las recetas disponibles")
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.searchAllRecipes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener receta por ID", 
               description = "Retorna una receta específica usando su identificador")
    public ResponseEntity<RecipeResponseDto> getRecipeById(
        @Parameter(description = "ID único de la receta", example = "507f1f77bcf86cd799439011")
        @PathVariable String id) {
        return ResponseEntity.ok(recipeService.searchRecipeById(id));
    }

    @GetMapping("/participantes")
    @Operation(summary = "Obtener recetas de participantes", 
               description = "Retorna todas las recetas creadas por participantes del programa")
    public ResponseEntity<List<RecipeResponseDto>> getParticipantRecipes() {
        return ResponseEntity.ok(recipeService.searchRecipesByAuthorType("PARTICIPANT"));
    }

    @GetMapping("/televidentes")
    @Operation(summary = "Obtener recetas de televidentes", 
               description = "Retorna todas las recetas enviadas por televidentes")
    public ResponseEntity<List<RecipeResponseDto>> getViewerRecipes() {
        return ResponseEntity.ok(recipeService.searchRecipesByAuthorType("VIEWER"));
    }

    @GetMapping("/chefs")
    @Operation(summary = "Obtener recetas de chefs", 
               description = "Retorna todas las recetas creadas por chefs jueces")
    public ResponseEntity<List<RecipeResponseDto>> getChefRecipes() {
        return ResponseEntity.ok(recipeService.searchRecipesByAuthorType("CHEF_JUDGE"));
    }

    @GetMapping("/temporada/{season}")
    @Operation(summary = "Obtener recetas por temporada", 
               description = "Retorna todas las recetas de una temporada específica")
    public ResponseEntity<List<RecipeResponseDto>> getRecipesBySeason(
        @Parameter(description = "Número de la temporada", example = "5")
        @PathVariable Integer season) {
        return ResponseEntity.ok(recipeService.searchRecipesBySeason(season));
    }

    @GetMapping("/ingrediente/{ingredient}")
    @Operation(summary = "Buscar recetas por ingrediente", 
               description = "Retorna recetas que contienen el ingrediente especificado")
    public ResponseEntity<List<RecipeResponseDto>> getRecipesByIngredient(
        @Parameter(description = "Nombre del ingrediente a buscar", example = "pasta")
        @PathVariable String ingredient) {
        return ResponseEntity.ok(recipeService.searchRecipesByIngredient(ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar receta", 
               description = "Elimina una receta específica usando su identificador")
    @ApiResponse(responseCode = "204", description = "Receta eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    public ResponseEntity<Void> deleteRecipe(
        @Parameter(description = "ID único de la receta a eliminar", example = "507f1f77bcf86cd799439011")
        @PathVariable String id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar receta", 
               description = "Actualiza una receta existente con nuevos datos")
    public ResponseEntity<RecipeResponseDto> updateRecipe(
        @Parameter(description = "ID único de la receta a actualizar", example = "507f1f77bcf86cd799439011")
        @PathVariable String id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Nuevos datos para la receta",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Actualizar Receta",
                    summary = "Ejemplo de actualización de receta",
                    value = """
                    {
                      "title": "Pasta Carbonara Mejorada",
                      "ingredients": [
                        {
                          "name": "spaghetti",
                          "quantity": "400",
                          "unit": "gramos"
                        },
                        {
                          "name": "panceta ahumada",
                          "quantity": "200",
                          "unit": "gramos"
                        },
                        {
                          "name": "huevos orgánicos",
                          "quantity": "5",
                          "unit": "unidades"
                        },
                        {
                          "name": "queso pecorino romano",
                          "quantity": "120",
                          "unit": "gramos"
                        }
                      ],
                      "steps": [
                        {
                          "order": 1,
                          "description": "Preparar agua con sal para la pasta"
                        },
                        {
                          "order": 2,
                          "description": "Dorar la panceta hasta que esté crujiente"
                        },
                        {
                          "order": 3,
                          "description": "Mezclar huevos con queso rallado"
                        },
                        {
                          "order": 4,
                          "description": "Cocinar pasta al dente"
                        },
                        {
                          "order": 5,
                          "description": "Combinar todo fuera del fuego para crear la salsa cremosa"
                        }
                      ],
                      "author": {
                        "name": "Chef Alessandro Borghese",
                        "type": "CHEF_JUDGE"
                      }
                    }
                    """
                )
            )
        )
        @RequestBody RecipeRequestDto dto) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, dto));
    }
}