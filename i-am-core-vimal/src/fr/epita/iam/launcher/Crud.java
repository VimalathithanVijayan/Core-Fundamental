
package fr.epita.iam.launcher;

import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoDeleteException;
import fr.epita.iam.exceptions.DaoSaveException;
import fr.epita.iam.exceptions.DaoSearchException;
import fr.epita.iam.exceptions.DaoUpdateException;
import fr.epita.iam.services.JDBCIdentityDAO;

/**
 * @author VIMAL
 */

public class Crud {
	/**
	 * Creating Insert method
	 * 
	 * @param scanner
	 */

	public static void insert(Scanner scanner) {
		System.out.println("Identity Creation");
		System.out.println("please input the identity Full name :");
		String FullName = scanner.nextLine();
		System.out.println("identity email :");
		String email = scanner.nextLine();
		System.out.println("identity uid");
		String uid = scanner.nextLine();
		Identity identity = new Identity(FullName, email, uid);
		try {
			JDBCIdentityDAO dao = new JDBCIdentityDAO();
			dao.insert(identity);
			System.out.println("the save operation completed successfully");
		} catch (DaoSaveException e) {
			System.out.println("the save operation is not able to complete, details :" + e.getMessage());
		}
	}

	/**
	 * Updating method from Insert
	 * 
	 * @param scanner
	 * @throws DaoUpdateException
	 */

	public static void update(Scanner scanner) throws DaoUpdateException {
		System.out.println("Identity Update");
		System.out.println("Select an identity id you want to change");
		String idtoupdate = scanner.nextLine();
		System.out.println("Enter the new Full name");
		String nametochange = scanner.nextLine();
		System.out.println("enter the new email");
		String emailtochange = scanner.nextLine();

		Identity id = new Identity(nametochange, emailtochange, idtoupdate);
		JDBCIdentityDAO updateDAO = new JDBCIdentityDAO();
		updateDAO.update(id);
	}

	/**
	 * This is search method for searching previously entered data's
	 * 
	 * @param scanner
	 * @throws DaoSearchException
	 */
	public static void search(Scanner scanner) throws DaoSearchException {
		System.out.println("Identity Search");
		System.out.println("Enter the identity uid to search");
		String idtosearch = scanner.nextLine();
		Identity iden = new Identity(null, null, idtosearch);
		JDBCIdentityDAO searchDAO = new JDBCIdentityDAO();
		System.out.println(searchDAO.search(iden));
	}

	/**
	 * Delete method to delete the data's
	 * 
	 * @param scanner
	 * @throws DaoDeleteException
	 */
	public static void delete(Scanner scanner) throws DaoDeleteException {
		System.out.println("Identity Deletion");
		System.out.println("you have selected identity deletion");
		System.out.println("Enter the identity uid to delete");
		String idtodelete = scanner.nextLine();
		Identity ide = new Identity(null, null, idtodelete);
		JDBCIdentityDAO deleteDAO = new JDBCIdentityDAO();
		deleteDAO.delete(ide);
	}

}
