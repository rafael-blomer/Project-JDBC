package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		Department teste = new Department(5, "Teste");
		
		System.out.println("=== TEST1: INSERT ===");
		depDao.insert(new Department(null, "testeINSERT"));
		
		System.out.println("\n=== TEST2: UPDATE ===");
		depDao.update(teste);
		
		System.out.println("\n=== TEST3: DELETE ===");
		System.out.println("Digite o id do departamento que você deseja deletar:");
		int idDelete = sc.nextInt();
		depDao.deleteById(idDelete);
		
		System.out.println("\n=== TEST4: FIND BY ID ===");
		System.out.println("Digite o id do departamento que você deseja buscar:");
		int idFind = sc.nextInt();
		System.out.println(depDao.findById(idFind));
		
		System.out.println("\n=== TEST5: FIND ALL ===");
		for (Department x : depDao.findAll()) {
			System.out.println(x);
		}
		
		
		sc.close();
	}
}
