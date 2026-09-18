package br.edu.ifsul.tds_sergio.infra.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean de Configuração da Documentação da API
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API da Disciplina Tópicos em Desenvolvimento de Software")
                        .description("API Rest da aplicação tds_sergio, contendo as funcionalidades, principalmente, para CRUD de questões.")
                        .contact(new Contact()
                                .name("Tarefas TDS")
                                .email("sergiorosario.pl012@academico.ifsul.edu.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://localhost:8080/api/licenca")));
    }
}