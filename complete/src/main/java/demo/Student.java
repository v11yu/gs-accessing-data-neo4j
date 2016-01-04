package demo;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Student {
	@GraphId Long id;
    public String name;
    
    public Student() {}
    public Student(String name) { this.name = name; }
    
    @RelatedTo(type="TEAMMATE", direction=Direction.BOTH)
    public @Fetch Set<Student> teammates;
    @RelatedTo(type="HAVE", direction=Direction.OUTGOING)
    public @Fetch Set<Book> books;
    
    public void addBook(Book book){
    	if(books == null){
    		books = new HashSet<Book>();
    	}
    	books.add(book);
    }
    public void worksWith(Student person) {
        if (teammates == null) {
            teammates = new HashSet<Student>();
        }
        teammates.add(person);
    }

    public String toString() {
        String results = name + "'s teammates include\n";
        if (teammates != null) {
            for (Student person : teammates) {
                results += "\t- " + person.name + "\n";
            }
        }
        return results;
    }
}
