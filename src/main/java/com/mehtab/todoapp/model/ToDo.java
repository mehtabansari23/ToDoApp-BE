package com.mehtab.todoapp.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ToDo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "Name is mandatory!")
	@Column(unique = true)
	private String name;

	private Boolean completed;

	public ToDo() {
	}

	public ToDo(Integer id, String name, Boolean done) {
		super();
		this.id = id;
		this.name = name;
		this.completed = done;
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

	public Boolean isCompleted() {
		return completed;
	}

	public void setCompleted(Boolean done) {
		this.completed = done;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completed, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDo other = (ToDo) obj;
		return Objects.equals(completed, other.completed) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", name=" + name + ", completed=" + completed + "]";
	}

}
