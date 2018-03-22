/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author coldwellaj
 */
public class User {
    String id;
    String email;
    String password;
    String user_type;
    String first_name;
    String last_name;
    String department;
    
    // Constructors
    public User() {
     this.email = "";
     this.password = "";
     this.user_type = "";
     this.first_name = "";
     this.last_name = "";
     this.department = "";
    }
    
    public User(String email, String password, String user_type,
            String first_name, String last_name, String department) {
     this.email = email;
     this.password = password;
     this.user_type = user_type;
     this.first_name = first_name;
     this.last_name = last_name;
     this.department = department;
    }
    
    // Getters
    public String getId() {
        return this.id;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getUserType() {
        return this.user_type;
    }
    public String getFirstName() {
        return this.first_name;
    }
    public String getLastName() {
        return this.last_name;
    }
    public String getDepartment() {
        return this.department;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserType(String user_type) {
        this.user_type = user_type;
    }
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}
