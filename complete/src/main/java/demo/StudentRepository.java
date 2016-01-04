package demo;
import hello.Person;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String>{

	Student findByName(String name);

}
