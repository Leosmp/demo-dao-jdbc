package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TESTE 01: department findById ===");
		Department department = departmentDao.findById(3);
		System.out.println(department);
		
		System.out.println("\n=== TESTE 02: department findAll ===");
		List<Department> list = departmentDao.findAll();
		for (Department department2 : list) {
			System.out.println(department2);
		}
		
		System.out.println("\n=== TESTE 03: department insert ===");
		Department dep = new Department(null, "Luggage");
		departmentDao.insert(dep);
		System.out.println("Inserted: New Id = " + dep.getId());
		
		System.out.println("\n=== TESTE 04: department Update ===");
		department = departmentDao.findById(3);
		department.setName("Movies & Tv");
		departmentDao.update(department);
		System.out.println("Update completed" + department + "\n");
		
		System.out.println("\n=== TESTE 05: department delete ===");
		System.out.print("Enter an Id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
	}
}
