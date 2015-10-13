package br.com.progpc.showcase.arquillian.entity;
 
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
@Entity
@Table(schema = "LALALA", name = "TB_GAME")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;

    @NotNull
    @Size(min = 3, max = 50)
	private String title;
 
    public Game() {}
 
    public Game(Long id) {
    	this.id = id;
    }

    public Game(String title) {
        this.title = title;
    }

    public Game(Long id, String title) {
    	this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }

}
