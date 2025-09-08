package nl.bdbekhof.demo.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Demo API")
                .version("v1")
                .description("Student / Book / Teacher API")
        );
    }

    @Bean
    public GroupedOpenApi studentsApi() {
        return GroupedOpenApi.builder()
                .group("students")
                .pathsToMatch("/students/**")
                .build();
    }

    @Bean
    public GroupedOpenApi teachersApi() {
        return GroupedOpenApi.builder()
                .group("teachers")
                .pathsToMatch("/teachers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi booksApi() {
        return GroupedOpenApi.builder()
                .group("books")
                .pathsToMatch("/books/**")
                .build();
    }
}
