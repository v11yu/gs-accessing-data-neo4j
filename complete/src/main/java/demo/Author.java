package demo;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Author {
	@GraphId
	public String name;
	
	public Author(){}
	public Author(String name){this.name = name;}
	
	
}
