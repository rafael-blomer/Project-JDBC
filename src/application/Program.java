package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST1: seller findById ===");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println("=== TEST2: seller findByDepartment ===");
		Department d = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(d);
		for (Seller s : list)
			System.out.println(s);
	}

}
