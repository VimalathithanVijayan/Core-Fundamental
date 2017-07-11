package fr.epita.iam.datamodel;
/**
 * @author VIMAL
 */

public class Identity {
	
	private String FullName;
	private String uid;
	private String email;
	
	/**
	 * @param Fullname
	 * @param uid
	 * @param email
	 */
	
	public Identity(String FullName, String email, String uid) {
		this.FullName = FullName;
		this.email = email;
		this.uid = uid;
	}

    /**
     * @return FullName
     */
	public String getFullName() {
		return FullName;
	}
    /**
     * 
     * @param Setters FullName
     */

	public void setDisplayName(String FullName) {
		this.FullName = FullName;
	}
     /**
      * 
      * @return  Uid from getters
      */

	public String getUid() {
		return uid;
	}
    /**
     * 
     * @param 
     */

	public void setUid(String uid) {
		this.uid = uid;
	}
    /**
     * 
     * @return Email
     */

	public String getEmail() {
		return email;
	}

    /**
     * 
     * @param Setting Email
     */
	public void setEmail(String email) {
		this.email = email;
	}

    /**
     * @return String
     */
	@Override
	public String toString() {
		return "Identity [FullName=" + FullName + ", email=" + email + ", uid=" + uid + "]";
	}
	

}
