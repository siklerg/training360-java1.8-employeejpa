package training360.employeejpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest @Sql(statements = { "delete from employee_skills", "delete from employee", "delete from skill"})
public class EmployeejpaApplicationTests {

	@Autowired EmployeeRepository employeeRepository;

	@Test public void saveThanList() {

		employeeRepository.save(new Employee("Jane Doe"));
		employeeRepository.save(new Employee("Jack Doe"));

		var employees = employeeRepository.listEmployees();
		assertEquals(List.of("Jack Doe", "Jane Doe"),
				employees.stream().map(employee -> employee.getName()).collect(Collectors.toList()));

	}

	@Test public void testEmployeeUpdate() {
		//Given
		var employee = new Employee("John");
		employeeRepository.save(employee);
		employeeRepository.updateEmployee(employee.getId(), "Jack");

		//When
		var employees = employeeRepository.listEmployees();

		//Then
		assertEquals(1, employees.size());
		assertEquals("Jack", employees.get(0).getName());
	}

	@Test public void testSaveSkills() {
		var employee = new Employee("John Doe");
		employee.setSkills(List.of(new Skill("Ruby", 1), new Skill("Python", 3), new Skill("Java", 5), new Skill("dotNet", 0),
				new Skill("Angular", 4)));
		employeeRepository.save(employee);

		var employees = employeeRepository.listEmployees();
		var skill = new Skill("Python", 3);

		//assertTrue(employees.get(0).getSkills().contains(skill));

		assertTrue(employees.get(0).getSkills()
				.stream()
				.anyMatch(s -> s.getName().equals("Python") && s.getLevel()==3));
	}
}
