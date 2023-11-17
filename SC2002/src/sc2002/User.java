package CAMS;

public class User {

	protected String name;
	protected String userID;
	protected String faculty;
	// protected String password;

	public User(String name, String userID, String faculty) {
		this.name = name;
		this.userID = userID;
		this.faculty = faculty;
		// this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getUserID() {
		return userID;
	}

	public String getFaculty() {
		return faculty;
	}

	/*
	 * public String getPassword() { return password; }
	 */

	/*
	 * public String toString() { return
	 * String.format("Name: %s\r\nUserID: %s\r\nFaculty: %s\r\n", name, userID,
	 * faculty); }
	 */
}
