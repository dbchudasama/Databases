package beans;

public class User {
	private String email = "";
	private String password = "";
	
	private String message = "";  //Variable to display message if validation is unsuccessful
	
	public String getMessage() {
		return message;
	}

	//Bean has to have a default constructor (no params) otherwise it is no longer a bean. 
	public User() {
		
	}
	
	//Constructor using fields
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//Validation subroutine to validate email address and password entered
	public boolean validate() {
	
		if(email == null) {
			message = "Invalid email address";
			return false;
		}
		
		else if(password == null) {
			message = "Invalid password";
			return false;
		}
		
		//Using all alpha numeric characters (and format) for email address mismatch.
		else if(!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
			message = "Invalid email address";
			return false;
		}
		
		else if (password.length() < 8) {
			message = "Password must be at least 8 characters";
			return false;
		}
		//"\\w*" = zero or more alpha numeric characters
		//"\\s" = space
		//Handles issues if ANY space is found in the password
		else if(password.matches("\\w*\\s+\\w*")) {
			message = "Password cannot contain space.";
			return false;
		}
			return true; //If everything validates ok return true else false
	}

}