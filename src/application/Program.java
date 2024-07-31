package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST1: seller findById ===");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println("\n=== TEST2: seller findByDepartment ===");
		Department d = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(d);
		for (Seller s : list)
			System.out.println(s);
		
		System.out.println("\n=== TEST3: seller findAll ===");
		list = sellerDao.findAll();
		for (Seller s : list)
			System.out.println(s);
		
		System.out.println("\n=== TEST4: seller insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, d);
		sellerDao.insert(newSeller);
		System.out.println(newSeller.getId());
		
		System.out.println("\n=== TEST5: seller update===");
		seller = sellerDao.findById(1);
		seller.setName("RAFAEL");
		sellerDao.update(seller);
		System.out.println("Update Complete");
		
		System.out.println("\n=== TEST 6: seller delete =====");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
	}

}
