package sc2002;

/**
 * User data structure (model)
 * 
 * @author koid qian yu
 * @version 1.0
 * @since 24/11/2023
 */
public class User {

	/**
	 * name of the user
	 */
	protected String name;
	/**
	 * unique user id  of the user
	 */
	protected String userID;
	/**
	 * faculty of the user
	 */
	protected String faculty;

	
	/**
	 * constructor of User class 
	 * 
	 * @param name name of the user
	 * @param userID unique user id of the user
	 * @param faculty faculty of the user
	 */
	public User(String name, String userID, String faculty) {
		this.name = name;
		this.userID = userID;
		this.faculty = faculty;
		
	}

	/**
	 * get the name of the user 
	 * 
	 * @return name of the user
	 */
	public String GetName() {
		return name;
	}

	/**
	 * get the user id of the user 
	 * 
	 * @return user id of the user
	 */
	public String GetUserID() {
		return userID;
	}

	/**
	 * get the faculty of the user 
	 * 
	 * @return faculty of the user
	 */
	public String GetFaculty() {
		return faculty;
	}

}
