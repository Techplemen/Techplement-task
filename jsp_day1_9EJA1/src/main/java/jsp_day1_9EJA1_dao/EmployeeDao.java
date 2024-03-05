package jsp_day1_9EJA1_dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jsp_day1_9EJA1_dto.Employee;

public class EmployeeDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sharath");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public Employee saveEmployee(Employee employee) {

		entityTransaction.begin();
		entityManager.persist(employee);
		entityTransaction.commit();

		return employee;
	}

	public Employee getEmployeeByEmail(String email) {
		Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.email=?1");
		query.setParameter(1, email);
		Employee employee = (Employee) query.getSingleResult();
		return employee;
	}

	public Employee getEmployeeByPhone(long phone) {
		Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.phone=?1");
		query.setParameter(1, phone);
		try {
			Employee employee = (Employee) query.getSingleResult();
			return employee;
		} catch (Exception e) {
			System.out.println("no object found for selected phonenumber");
			return null;
		}
	}
	
	public Employee getEmployeeById(int id) {
		Employee employee=entityManager.find(Employee.class, id);
		return employee;
	}

	public List<Employee> getAllEmployees() {
		Query query = entityManager.createQuery("SELECT e FROM Employee e");
		List<Employee> employees = query.getResultList();
		return employees;
	}

	public boolean deleteEmployee(int id) {

		Employee employee = entityManager.find(Employee.class, id);
		if (employee != null) {
			entityTransaction.begin();
			entityManager.remove(employee);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public Employee updateEmployee(Employee employee) {

		entityTransaction.begin();
		entityManager.merge(employee);
		entityTransaction.commit();

		return employee;
	}

}
