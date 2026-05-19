package Projet_INTIA.INTIA.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ============================================================
 * CONFIGURATION OPENAPI (SWAGGER)
 * ============================================================
 *
 * Cette classe permet de configurer la documentation automatique
 * de l'API REST via Swagger (OpenAPI 3).
 *
 * OBJECTIFS :
 * - Documenter les endpoints REST
 * - Permettre les tests API via interface graphique
 * - Améliorer la maintenabilité et la compréhension du backend
 */
@Configuration
public class OpenApiConfig {

    /**
     * Bean principal OpenAPI utilisé par Springdoc Swagger
     *
     * @return configuration personnalisée de l'API INTIA
     */
    @Bean
    public OpenAPI customOpenAPI() {

        // Je définis ici les informations globales de l'API
        return new OpenAPI()
                .info(new Info()
                        .title("INTIA Assurance API")
                        .version("1.0.0")
                        .description("API REST de gestion des clients, assurances et succursales INTIA")
                        .contact(new Contact()
                                .name("INTIA Assurance Team")
                                .email("contact@intia-assurance.cm")));
    }
}