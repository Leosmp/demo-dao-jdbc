package application;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TESTE 01: seller findById ===");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);		
		
		System.out.println("\n=== TESTE 02: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj + "\n");
		}
		
		System.out.println("\n=== TESTE 03: seller findAll ===");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj + "\n");
		}
		
		System.out.println("=== TESTE 04: seller Insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted: New Id = " + newSeller.getId());
		
		System.out.println("=== TESTE 05: seller Update ===");
		seller = sellerDao.findById(1);
		seller.setName("Marhta Waine");
		sellerDao.update(seller);
		System.out.println("Update completed" + seller + "\n");	
		
		System.out.println("=== TESTE 06: seller Delete ===");
		System.out.print("Enter an Id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");	
		
		sc.close();
	}

}
