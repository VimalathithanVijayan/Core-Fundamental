
package fr.epita.iam.services;
/**
 * @author VIMAL
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoDeleteException;
import fr.epita.iam.exceptions.DaoSaveException;
import fr.epita.iam.exceptions.DaoSearchException;
import fr.epita.iam.exceptions.DaoUpdateException;


public class JDBCIdentityDAO implements IdentityDAO{
	public Connection c;
	/**
	 * @throws SQLException
	 * 
	 */
	public  Connection getconnection() throws SQLException{
			try{
				this.c.getSchema();
			}
			catch(Exception e){
				String user="root";
				String pass="root";
				String constring = "jdbc:derby://localhost:1527/iamcore;create=true";
				this.c= DriverManager.getConnection(constring, user, pass);
			}
			
			return this.c;
			
			
		}
	
	 /**
	  * Search Method
	  */
    
    public List<Identity> search(Identity criteria) throws DaoSearchException {
		List<Identity> returnedList = new ArrayList<Identity>();
		String qry="SELECT * from newcore where uid=?";
		try (Connection conn = this.getconnection();
			PreparedStatement pstmt = conn
					.prepareStatement(qry)){
			 
			pstmt.setString(1, criteria.getUid());

			ResultSet results = pstmt.executeQuery();

			while (results.next()) {
				String FullName = results.getString("Fullname");
				String email = results.getString("email");
				String uid=results.getString("uid");
				returnedList.add(new Identity(FullName,email, uid));

			}
		} catch (SQLException sqle) {
			DaoSearchException daose = new DaoSearchException();
			daose.initCause(sqle);
			throw daose;
		}

		return returnedList;
	    }
	  
	 /**
	  * Insert Method
	  */
	 	  
	public void insert(Identity identity) throws DaoSaveException {
	
		String query = "INSERT INTO newcore(Fullname,email,uid) VALUES(?,?,?)";
		 
        try (Connection conn = this.getconnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
        	pstmt.setString(1, identity.getFullName());
            pstmt.setString(2, identity.getEmail());
            pstmt.setString(3, identity.getUid());
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
		
	}
	/**
	 * Update Method
	 */
	
	public void update(Identity identity) throws DaoUpdateException {
		 String query = "UPDATE newcore SET Fullname = ?, email = ? WHERE uid = ?" ;
	                
	 
	        try (Connection conn = this.getconnection();
	                PreparedStatement pstmt = conn.prepareStatement(query)) {
	 
	            // set the corresponding param
	            pstmt.setString(1, identity.getFullName());
	            pstmt.setString(2, identity.getEmail());
	            pstmt.setString(3, identity.getUid());
	            // update 
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
		
    /**
     *  delete method
     */
	
	public void delete(Identity identity) throws DaoDeleteException { 
		  String query = "DELETE FROM newcore WHERE uid = ?";
			 
	        try (Connection conn = this.getconnection();
	                PreparedStatement pstmt = conn.prepareStatement(query)) {
	 
	            // set the corresponding param
	            pstmt.setString(1, identity.getUid());
	            // execute the delete statement
	            pstmt.executeUpdate();
	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	
	/**
	 * Database Connection
	 */
	public void releaseResources() {
		try {
			this.c.close();
		} catch (SQLException e) {
			System.out.println("SQLException  :" + e);
		}
	}
		
	}


	

