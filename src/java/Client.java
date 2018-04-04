/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Coldwellaj
 */
public class Client {
    private int id;
    private String f_name;
    private String l_name;
    private String email;
    private String phone;
    
    public Client(){
        
    }
    
    public Client(int id, String f_name, String l_name, String email, String phone) {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setFName(String f_name) {
        this.f_name = f_name;
    }
    
    public void setLName(String l_name) {
        this.l_name = l_name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getFName(){
        return this.f_name;
    }
    
    public String getLName(){
        return this.l_name;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getPhone(){
        return this.phone;
    }
    
}
