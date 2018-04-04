/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Coldwellaj
 */
public class SurveyAnswer {
    private int id;
    private int clientId;
    private int ticketId;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    
    public void setQuestion1(String q1) {
        this.q1 = q1;
    }
    
    public void setQuestion2(String q2) {
        this.q2 = q2;
    }
    
    public void setQuestion3(String q3) {
        this.q3 = q3;
    }
    
    public void setQuestion4(String q4) {
        this.q4 = q4;
    }
    
    
    public int getId(){
        return this.id;
    }
    
    public int getClientId(){
        return this.clientId;
    }
    
    public int getTicketId(){
        return this.ticketId;
    }
    
    public String getQuestion1(){
        return this.q1;
    }
    
    public String getQuestion2(){
        return this.q2;
    }
    
    public String getQuestion3(){
        return this.q3;
    }
    
    public String getQuestion4(){
        return this.q4;
    }
    
}
