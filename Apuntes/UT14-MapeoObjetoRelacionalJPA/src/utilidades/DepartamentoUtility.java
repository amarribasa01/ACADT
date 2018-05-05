package utilidades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Departamento;

public class DepartamentoUtility {

	public static void InsertarDepartamento(Departamento dep) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(dep);

		entityManager.getTransaction().commit();
		entityManager.close();

		JPAUtility.close();
		System.out.println("Entidad departamento guardada");

	}

	public static void SelectAllDepartamentos() {
		EntityManager entityManager = JPAUtility.getEntityManager();

		List<Departamento> resultList = entityManager.createQuery("Select d From Departamento d", Departamento.class)
				.getResultList();
		System.out.println("Cantidad de departamentos:" + resultList.size());
		for (Departamento next : resultList) {
			System.out.println("Departamento: " + next);
		}
		entityManager.close();
		JPAUtility.close();

	}

	public static Departamento SelectDepartamentoByName(String name) {
		Departamento dep = null;
		EntityManager entityManager = JPAUtility.getEntityManager();
		Query query = entityManager.createQuery("Select d from Departamento d where d.dnombre = :name");
		query.setParameter("name", name);
		try {
			dep = (Departamento) query.getSingleResult();
			System.out.println("Departamento : " + dep);
		} catch (NoResultException ex) {
			System.out.println("No se ha encontrado ningún departamento con ese nombre");
		}
		entityManager.close();
		JPAUtility.close();
		return dep;
	}

	public static void UpdateDepartamento(int ndep) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		try {
			Departamento dept = entityManager.find(Departamento.class, ndep);
			dept.setDnombre("ADMINISTRACION");
			entityManager.persist(dept);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		entityManager.close();
		JPAUtility.close();

	}
	
	public static void DeleteDepartamento(int ndep) {
		EntityManager entityManager = JPAUtility.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		try {
			Departamento dept = entityManager.find(Departamento.class, ndep);
			entityManager.remove(dept);
			tx.commit();
			System.out.println("Departamento eliminado con éxito");
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		entityManager.close();
		JPAUtility.close();

	}
}
