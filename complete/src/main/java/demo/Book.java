package demo;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Book {
	@GraphId
	public String name;
	
	public Book(){}
	public Book(String name){ this.name = name;}
	
//	@RelatedTo(type="AUTHOR", direction=Direction.BOTH)
//	public @Fetch Set<Author> authors;
//	 
//	public void addAuthor(Author au){
//		if(authors == null) authors = new HashSet<Author>();
//		authors.add(au);
//	}
}
