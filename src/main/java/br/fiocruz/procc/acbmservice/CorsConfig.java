package br.fiocruz.procc.acbmservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/subpasta/**") // Substitua "/subpasta/**" pelo caminho da sua subpasta
                .allowedOrigins("*") // Defina os domínios permitidos ou use "*" para permitir todos
                .allowedMethods("*") // Defina os métodos HTTP permitidos
                .allowedHeaders("*") // Defina os cabeçalhos permitidos ou use "*" para permitir todos
                .allowCredentials(true) // Permitir envio de cookies de origens diferentes
                .maxAge(3600); // Tempo de validade do preflight request em segundos
    }
}
