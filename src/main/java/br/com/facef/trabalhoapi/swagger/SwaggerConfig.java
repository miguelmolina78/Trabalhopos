package br.com.facef.trabalhoapi.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.facef.trabalhoapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 message")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbiddensssssssssssss!")
                                        .build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 POST")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("FORBID POST!")
                                        .build()))
                .globalResponseMessage(RequestMethod.DELETE,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 DELETTEEE")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbiddensssssssssssss!")
                                        .build()))
                .globalResponseMessage(RequestMethod.PUT,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 PUT")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbiddensssssssssssss!")
                                        .build()))
                .apiInfo(this.informacoesApi().build());
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Api-TrabalhoPOS");
        apiInfoBuilder.description("BackEnd modelado para apresentação de solução de empresa de cursos/treinamentos." +
                "Usado exclusivamente no Curso de Desenvolvimento de aplicações web e móveis escaláveis ");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.license("Open Source");

        return apiInfoBuilder;

    }

}