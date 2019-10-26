package com.alelo.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * A classe <code>com.alelo.api.entity</code> é um modelo que descreve a entidade Estádio 
 * 
 * @author André
 * @version 1.0.0
 * @see ApiModelProperty;
 *
 */

@Entity
@Table(name = "STADIUM")
@ApiModel
@Validated
public class Stadium extends Object implements Serializable {

		private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@ApiModelProperty(notes = "Id do estádio" , position = 0, value = "id", name = "id", dataType = "int", hidden = true)
	    private Integer id;

		@ApiModelProperty(notes = "Nome do estádio" , position = 1, value = "name", name = "name", dataType = "String", example = "Arena da Baixada", required = true)
		@NotNull(message = "Nome do Estadio não pode ser null")
		@NotEmpty(message = "Nome do Estadio não pode ser vazio")
		private String name;

		@ApiModelProperty(notes = "Capacidade de público máximo sentado", position = 2, value = "seatingCapacity", name = "seatingCapacity", dataType = "int", example = "10000", required = true)
		@Range(min=0, max=1000000)
		private int seatingCapacity;

		@ApiModelProperty(notes = "Número de restaurantes associados", position = 3, value = "associatedRestaurants", name = "associatedRestaurants", dataType = "int", example = "100", required = true)
		@Range(min=0, max=1000000)
		private int associatedRestaurants;

	    public Stadium() {
			
		}

		public Stadium(String name){
	    	this.name = name;
	    }
	    
		public Stadium(Integer id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		public Stadium(String name, int seatingCapacity, int associatedRestaurants) {
			super();
			this.name = name;
			this.seatingCapacity = seatingCapacity;
			this.associatedRestaurants = associatedRestaurants;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	    public int getSeatingCapacity() {
			return seatingCapacity;
		}

		public void setSeatingCapacity(int seatingCapacity) {
			this.seatingCapacity = seatingCapacity;
		}

		public int getAssociatedRestaurants() {
			return associatedRestaurants;
		}

		public void setAssociatedRestaurants(int associatedRestaurants) {
			this.associatedRestaurants = associatedRestaurants;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Stadium other = (Stadium) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Stadium [id=" + id + ", name=" + name + "]";
		}
		
}
