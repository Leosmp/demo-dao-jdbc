package application;

import java.util.List;
import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TESTE 01: seller findById ===");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		Department department = new Department(2, null);
		System.out.println("\n=== TESTE 02: seller findByDepartment ===");
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj + "\n");
		}
	}

}
