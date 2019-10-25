package com.alelo.api.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alelo.api.entity.Stadium;
import com.alelo.api.service.StadiumService;

@RestController

@RequestMapping("/stadiums")
@Api(value= "Stadiums", description = "Estadios Controler onde ficam os endpoints dos da API ")
public class StadiumController {

	    @Autowired 
	    private StadiumService stadiumService;
	    
	    @ApiOperation(value = "Listagem de Estadios")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Retornou a lista com sucesso"),
	            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
	            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
	            @ApiResponse(code = 404, message = "O recurso não foi encontrado")
	    })
	    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
		@ApiParam
	    public ResponseEntity<?> list(Model model) {
	        try {
	            List<Stadium> stadiums = new ArrayList<Stadium>();
	            stadiumService.listAllStadiums().forEach(stadiums :: add);

	            if(stadiums.size() == 0)
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            else
	                return new ResponseEntity<>(stadiums, HttpStatus.OK);

	        } catch(Throwable exception) {
	            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

	            errorAsJson.put("verb", "GET");
	            errorAsJson.put("exception", exception.getClass());
	            errorAsJson.put("path", "/stadium");
	            errorAsJson.put("timestamp", new Date());
	            errorAsJson.put("message", exception.getMessage());

	            return new ResponseEntity<>(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @ApiOperation(value = "Visualizar Estadio")
	    @ApiResponses(value = {
	            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
	            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
	            @ApiResponse(code = 404, message = "O recurso não foi encontrado")
	    })
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
		@ApiParam
	    public ResponseEntity<?> showStadium(@PathVariable Integer id, Model model) {
	        try {
	            Stadium stadium = stadiumService.getStadiumById(id);

	            if(null == stadium)
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            else
	                return new ResponseEntity<>(stadium, HttpStatus.OK);

	        } catch(Throwable exception) {
	            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

	            errorAsJson.put("verb", "GET");
	            errorAsJson.put("exception", exception.getClass());
	            errorAsJson.put("path", "/stadium/" + id);
	            errorAsJson.put("timestamp", new Date());
	            errorAsJson.put("message", exception.getMessage());

	            return new ResponseEntity<>(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
	    @ApiOperation(value = "Cadastrar Estadio")
	    @ApiResponses(value = {
	            @ApiResponse(code = 201, message = "Criado a lista com sucesso"),
	            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
	            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
	            @ApiResponse(code = 404, message = "O recurso não foi encontrado")
	    })
	    public ResponseEntity<?> saveStadium(@RequestBody Stadium stadium) {
	        try {
	        	Stadium storedStadium = stadiumService.getStadiumByName(stadium.getName());
	            if(null != storedStadium)
	                return new ResponseEntity<>(storedStadium, HttpStatus.CONFLICT);
	            else {
	                
	                stadiumService.saveStadium(stadium);

	                return new ResponseEntity<>(stadium, HttpStatus.CREATED);
	            }
	        } catch(Throwable exception) {
	            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

	            errorAsJson.put("verb", "POST");
	            errorAsJson.put("exception", exception.getClass());
	            errorAsJson.put("path", "/stadium");
	            errorAsJson.put("timestamp", new Date());
	            errorAsJson.put("message", exception.getMessage());

	            return new ResponseEntity<>(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	    @ApiOperation(value = "Alterar Estadio")
	    @ApiResponses(value = {
	            @ApiResponse(code = 201, message = "Criado a lista com sucesso"),
	            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
	            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
	            @ApiResponse(code = 404, message = "O recurso não foi encontrado")
	    })
	    public ResponseEntity<?> updateStadium(@PathVariable Integer id, @RequestBody Stadium stadium) {
	        try {
	            Stadium storedStadium = stadiumService.getStadiumById(id);

	            if(null == storedStadium) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	            } else {
	                storedStadium.setName(stadium.getName());
	                storedStadium.setSeatingCapacity(stadium.getSeatingCapacity());
	                storedStadium.setAssociatedRestaurants(stadium.getAssociatedRestaurants());

	                stadiumService.saveStadium(storedStadium);

	                return new ResponseEntity<>(storedStadium, HttpStatus.OK);
	            }
	        } catch(Throwable exception) {
	            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

	            errorAsJson.put("verb", "PUT");
	            errorAsJson.put("exception", exception.getClass());
	            errorAsJson.put("path", "/stadium/" + id);
	            errorAsJson.put("timestamp", new Date());
	            errorAsJson.put("message", exception.getMessage());

	            return new ResponseEntity<>(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	    @ApiOperation(value = "Deletar Estadio")
	    @ApiResponses(value = {
	            @ApiResponse(code = 204, message = "Sem conteúdo"),
	            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
	            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
	    })
	    public ResponseEntity<?> delete(@PathVariable Integer id) {
	        try {
	            Stadium storedStadium = stadiumService.getStadiumById(id);

	            if(null == storedStadium) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            } else {
	                stadiumService.deleteStadium(id);

	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }
	        } catch(Throwable exception) {
	            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

	            errorAsJson.put("verb", "DELETE");
	            errorAsJson.put("exception", exception.getClass());
	            errorAsJson.put("path", "/stadium/" + id);
	            errorAsJson.put("timestamp", new Date());
	            errorAsJson.put("message", exception.getMessage());

	            return new ResponseEntity<>(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
}
