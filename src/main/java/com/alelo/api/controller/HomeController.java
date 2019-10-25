package com.alelo.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
@Api(value = "homepage")
public class HomeController {

	@ApiOperation(value = "Pagina inicial do desafio Alelo", response = String.class)
    @RequestMapping(method= RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado")
    })
    public String index(){
        return "Pagina inicial do desafio Alelo!" +
        		"Mais informações podem ser acessadas pela documentação do Swagger em: /swagger-ui.html";
    }
}
