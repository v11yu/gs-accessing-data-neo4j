package demo;

import java.io.File;
import java.io.IOException;

import hello.Person;
import hello.PersonRepository;

import org.neo4j.graphdb.Transaction;
import org.neo4j.io.fs.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Component;


@Component
public class Apple {
	@Autowired StudentRepository sRepository;

	@Autowired GraphDatabase graphDatabase;
	public void work(){
		Student greg = new Student("Greg");
		Student roy = new Student("Roy");
		Student craig = new Student("Craig");
		Book b = new Book("math");
		System.out.println("Before linking up with Neo4j...");
		for (Student person : new Student[] { greg, roy, craig }) {
			System.out.println(person);
		}

		Transaction tx = graphDatabase.beginTx();
		try {
			sRepository.save(greg);
			sRepository.save(roy);
			sRepository.save(craig);

			greg = sRepository.findByName(greg.name);
			greg.worksWith(roy);
			greg.worksWith(craig);
			greg.addBook(b);
			
			sRepository.save(greg);
			roy.addBook(b);
			roy = sRepository.findByName(roy.name);
			roy.worksWith(craig);
			// We already know that roy works with greg
			sRepository.save(roy);

			// We already know craig works with roy and greg

			System.out.println("Lookup each person by name...");
			for (String name : new String[] { greg.name, roy.name, craig.name }) {
				System.out.println(sRepository.findByName(name));
			}


			tx.success();
		} finally {
			tx.close();
		}
	}
	public static void main(String[] args) throws IOException {
		FileUtils.deleteRecursively(new File("accessingdataneo4j.db"));
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Apple app = (Apple) context.getBean("apple");
		app.work();
	}
}
