package fr.epita.iam.services;

/**
 * @author VIMAL
 */

public class Authenticator {
	
	/**
	 * Checking the authentication
	 * @param userName
	 * @param password
	 * @return
	 */
public static boolean authenticate(String userName, String password)
{
		
		return "Admin".equals(userName) && "Admin".equals(password);
}

}
