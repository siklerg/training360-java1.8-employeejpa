package training360.employeejpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Employee employee){

		em.persist(employee);
	}

	@Transactional
	public List<Employee> listEmployees(){
		return em.createQuery("select distinct e from Employee e left join fetch e.skills order by e.name", Employee.class).getResultList(); // lazy probléma megoldása
	}

	@Transactional
	public void updateEmployee (long id, String modifiedName){
		var employee = em.find(Employee.class, id);
		employee.setName(modifiedName);
	}

}
