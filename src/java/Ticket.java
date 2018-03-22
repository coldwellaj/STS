/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author coldwellaj
 */
public class Ticket {
    private int id;
    private int priority;
    private String ticketTitle;
    private String startDate;
    private String endDate;
    private String currentUser;
    private String details;
    private String queue;
    private String clientId;
    private String status;
    private String resources;
    
    // constructors
    public Ticket(){
        this.id = 0;
        this.priority = 0;
        this.ticketTitle = "";
        this.startDate = "";
        this.endDate = "";
        this.currentUser = "coldwellaj@gmail.com";
        this.details = "";
        this.queue = "";
        this.clientId = "";
        this.status = "";
        this.resources = "";
    }
    
    public Ticket(int id, int priority, String startDate, String endDate,
            String currentUser, String details, String queue, String clientFName,
            String clientId, String status, String resources, String title) {
        this.id = id;
        this.priority = priority;
        this.ticketTitle = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentUser = currentUser;
        this.details = details;
        this.queue = queue;
        this.clientId = clientId;
        this.status = status;
        this.resources = resources;
    }
    
    // Getters
    public int getId() {
        return this.id;
    }
    public int getPriority() {
        return this.priority;
    }
    public String getTicketTitle() {
        return this.ticketTitle;
    }
    public String getStartDate() {
        return this.startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public String getCurrentUser() {
        return this.currentUser;
    }
    public String getDetails() {
        return this.details;
    }
    public String getQueue() {
        return this.queue;
    }
    public String getClientId(){
        return this.clientId;
    }
    public String getStatus() {
        return this.status;
    }
    public String getResource() {
        return this.resources;
    }
    
    // Setters
    public void setID(int id) {
        this.id = id;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setTicketTitle(String title) {
        this.ticketTitle = title;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public void setQueue(String queue) {
        this.queue = queue;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setResource(String resource) {
        this.resources = resource;
    }
}
