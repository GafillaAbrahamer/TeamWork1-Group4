
package com.ecoembes.tw1.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {
  @Bean public OpenAPI ecoembesOpenAPI(){
    return new OpenAPI().info(new Info().title("Ecoembes TW1 API").version("v0.1")
      .description("Prototype central server API (no persistence, in-memory state). Use the X-Token header for authenticated endpoints."))
      .externalDocs(new ExternalDocumentation().description("Swagger UI").url("/swagger-ui/index.html"));
  }
}
