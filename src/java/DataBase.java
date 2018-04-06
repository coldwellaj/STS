
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author coldwellaj
 */
public class DataBase { 
    // Database Creds
    static String jdbcURL = "jdbc:mysql://sagests.heliohost.org/ajcoldwe_sage"
            + "?zeroDateTimeBehavior=convertToNull";
    static String uName = "ajcoldwe_root";
    static String uPassword = "219907aJ1";    
    
    /**
     *
     * @param Email
     * @return
     */
    public static User getUser(String Email) {
        User hold = new User();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM users WHERE email = '" + Email + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                hold.setId(rs.getString("id"));
                hold.setEmail(rs.getString("email"));
                hold.setPassword(rs.getString("password"));
                hold.setUserType(rs.getString("user_type"));
                hold.setFirstName(rs.getString("first_name"));
                hold.setLastName(rs.getString("last_name"));
                hold.setDepartment(rs.getString("department"));
            }
            
            
            con.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return hold;
    }
    
    /**
     *
     * @param ticketId
     * @return
     */
    public static Ticket getTicket(String ticketId) {
        Ticket hold = new Ticket();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM tickets WHERE id = '" + ticketId + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                hold.setID(rs.getInt("id"));
                hold.setPriority(rs.getInt("priority"));
                hold.setStartDate(rs.getString("start_date"));
                hold.setEndDate(rs.getString("end_date"));
                hold.setCurrentUser(rs.getString("user"));
                hold.setDetails(rs.getString("details"));
                hold.setQueue(rs.getString("department"));
                hold.setClientId(rs.getString("client_id"));
                hold.setStatus(rs.getString("status"));
                hold.setResource(rs.getString("resource"));
                hold.setTicketTitle(rs.getString("title"));
            }
            
            
            con.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return hold;
    }
    
    /**
     *
     * @param user_id
     * @return
     */
    public static ArrayList<Ticket> getTickets(String user_id) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM tickets WHERE user = '" + user_id + 
                    "' and status != '4'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                Ticket hold = new Ticket();
                hold.setID(rs.getInt("id"));
                hold.setPriority(rs.getInt("priority"));
                hold.setStartDate(rs.getString("start_date"));
                hold.setEndDate(rs.getString("end_date"));
                hold.setCurrentUser(rs.getString("user"));
                hold.setDetails(rs.getString("details"));
                hold.setQueue(rs.getString("department"));
                hold.setClientId(rs.getString("client_id"));
                hold.setStatus(rs.getString("status"));
                hold.setResource(rs.getString("resource"));
                hold.setTicketTitle(rs.getString("title"));
                
                tickets.add(hold);
            }
            
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return tickets;
    }
    
    public static ArrayList<Ticket> getNewTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM tickets WHERE status = '1'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                Ticket hold = new Ticket();
                hold.setID(rs.getInt("id"));
                hold.setPriority(rs.getInt("priority"));
                hold.setStartDate(rs.getString("start_date"));
                hold.setEndDate(rs.getString("end_date"));
                hold.setCurrentUser(rs.getString("user"));
                hold.setDetails(rs.getString("details"));
                hold.setQueue(rs.getString("department"));
                hold.setClientId(rs.getString("client_id"));
                hold.setStatus(rs.getString("status"));
                hold.setResource(rs.getString("resource"));
                hold.setTicketTitle(rs.getString("title"));
                
                tickets.add(hold);
            }
            
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tickets;
    }
    
    /**
     *
     * @param user_id
     * @return
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                User hold = new User();
                hold.setFirstName(rs.getString("first_name"));
                hold.setLastName(rs.getString("last_name"));
                hold.setId(rs.getString("id"));
                hold.setEmail(rs.getString("email"));
                hold.setUserType(rs.getString("user_type"));
                hold.setDepartment(rs.getString("department"));
                
                users.add(hold);
            }
            
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return users;
    }
    
    /**
     *
     * @param user_id
     * @return
     */
    public static ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM clients";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                Client hold = new Client();
                hold.setFName(rs.getString("first_name"));
                hold.setLName(rs.getString("last_name"));
                hold.setId(rs.getInt("id"));
                hold.setEmail(rs.getString("email"));
                hold.setPhone(rs.getString("phone"));
                
                clients.add(hold);
            }
            
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return clients;
    }
    
    /**
     *
     * @param user_id
     * @return
     */
    public static ArrayList<User> getTeamUsers(String teamId) {
        ArrayList<User> users = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM teamsusers where team_id = '" + teamId + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            ArrayList<String> teamUsers = new ArrayList<>();
            
            while(rs.next()){
                teamUsers.add(rs.getString("user_id"));
            }
            
            for(int x = 0; x<teamUsers.size();x++){
                SQL = "SELECT * FROM users where id = '" + teamUsers.get(x) + "'";
                rs = stmt.executeQuery(SQL);
                rs.next();
                
                User hold = new User();
                hold.setFirstName(rs.getString("first_name"));
                hold.setLastName(rs.getString("last_name"));
                hold.setId(rs.getString("id"));
                hold.setEmail(rs.getString("email"));
                hold.setUserType(rs.getString("user_type"));
                hold.setDepartment(rs.getString("department"));
                
                users.add(hold);
            }
            

            
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return users;
    }
    
    /**
     *
     * @param user_id
     * @return
     */
    public static ArrayList<Team> getTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM teams";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                Team hold = new Team();
                hold.setTeamLeader(rs.getInt("user_leader"));
                hold.setTeamName(rs.getString("name"));
                hold.setId(rs.getInt("id"));
                
                teams.add(hold);
            }
            
            
            
            for (int x = 0; x < teams.size(); x++){
                SQL = "SELECT * FROM users where id='" + teams.get(x).getTeamLeader()
                        + "'";
                rs = stmt.executeQuery(SQL);
                rs.next();
                
                teams.get(x).setTeamLeaderName(rs.getString("first_name") + " "
                + rs.getString("last_name"));
            }
            
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return teams;
    }
    
    /**
     *
     * @param user_id
     * @return
     */
    public static ArrayList<Team> getUserTeams(String userId) {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT team_id FROM teamsusers where user_id = '" + userId + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            ArrayList<String> UserTeams = new ArrayList<>();
            
            while(rs.next()){
                UserTeams.add(rs.getString("team_id"));  
            }
            
            
                
            
            for (int x = 0; x < UserTeams.size(); x++){
                Team hold = new Team();
                SQL = "SELECT * FROM teams where id='" + UserTeams.get(x)
                        + "'";
                rs = stmt.executeQuery(SQL);
                rs.next();
                
                hold.setTeamLeader(rs.getInt("user_leader"));
                hold.setTeamName(rs.getString("name"));
                hold.setId(rs.getInt("id"));
                
                teams.add(hold);
            }
            
            for (int x = 0; x < teams.size(); x++){
                SQL = "SELECT * FROM users where id='" + teams.get(x).getTeamLeader()
                        + "'";
                rs = stmt.executeQuery(SQL);
                rs.next();
                
                teams.get(x).setTeamLeaderName(rs.getString("first_name") + " "
                + rs.getString("last_name"));
            }
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return teams;
    }
     
    /**
     *
     * @param newTicket
     * @return
     */
    public static boolean addNewTicket(Ticket newTicket) {
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = " insert into tickets (priority, start_date, "
                     + "end_date, details, department, client_id, "
                     + "status, title, resource) values"
                     + " (?, ?, ?, ?, ?, ?, ?, ?, ?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, newTicket.getPriority());
             preparedStmt.setString(2,newTicket.getStartDate());
             preparedStmt.setString(3,newTicket.getEndDate());
             preparedStmt.setString(4,newTicket.getDetails());
             preparedStmt.setString(5,newTicket.getQueue());
             preparedStmt.setString(6,newTicket.getClientId());
             preparedStmt.setString(7,"1");
             preparedStmt.setString(8,newTicket.getTicketTitle());
             preparedStmt.setString(9,newTicket.getResource());
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
            
        }
     }
     
    /**
     *
     * @param ticket
     * @return
     */
    public static boolean updateTicket(Ticket ticket) {
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = " update tickets set priority =?, start_date = ?, "
                     + "end_date = ?, user = ?, details = ?, department = ?, "
                     + "client_id= ?, resource = ?, title = ? where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, ticket.getPriority());
             preparedStmt.setString(2,ticket.getStartDate());
             preparedStmt.setString(3,ticket.getEndDate());
             preparedStmt.setString(4,ticket.getCurrentUser());
             preparedStmt.setString(5,ticket.getDetails());
             preparedStmt.setString(6,ticket.getQueue());
             preparedStmt.setString(7,ticket.getClientId());
             preparedStmt.setString(8,ticket.getResource());
             preparedStmt.setString(9,ticket.getTicketTitle());
             preparedStmt.setString(10,String.valueOf(ticket.getId()));
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
     }
    
    public static boolean setTicketStatus(String id, String status) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = " update tickets set status = ? where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, status);
             preparedStmt.setString(2,id);
             
            preparedStmt.execute();
            con.close();
            
            if (status == "4") {
                Client client = new Client();
                Ticket ticket = new Ticket();
                ticket = getTicket(id);
                client = getClient(ticket.getClientId());
                
                EmailServer.SendSurvey(client, ticket);
            }
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean assignTicket(String ticketId, String userEmail) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = " update tickets set user = ?, status = ? where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, userEmail);
             preparedStmt.setString(2, "2");
             preparedStmt.setString(3,ticketId);
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    public static boolean addNewTeam(Team newTeam){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = "insert into teams (name,user_leader) values (?,?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, newTeam.getTeamName());
             preparedStmt.setInt(2, newTeam.getTeamLeader());
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
            
        }
    }
    
    public static boolean assignTeamLeader(String teamId, String teamLeader) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = "update teams set user_leader = ? where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, teamLeader);
             preparedStmt.setString(2, teamId);
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean assignTeamUser(String teamId, String userId) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = "INSERT into teamsusers (user_id, team_id) values (?,?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, userId);
             preparedStmt.setString(2, teamId);
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean assignTeamTicket(String teamId, String ticketId) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = "update tickets set team_id = ? where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, teamId);
             preparedStmt.setString(2, ticketId);
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     *
     * @param ClientId
     * @return
     */
    public static Client getClient(String clientId) {
        Client hold = new Client();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM clients WHERE id = '" + clientId + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                hold.setId(rs.getInt("id"));
                hold.setEmail(rs.getString("email"));
                hold.setFName(rs.getString("first_name"));
                hold.setLName(rs.getString("last_name"));
                hold.setPhone(rs.getString("phone"));
            }
            
            
            con.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return hold;
    }
    
    /**
     *
     * @param newUser
     * @return
     */
    public static String addNewUser(User newUser) {
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM clients WHERE email = '" + newUser.getEmail() + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            
            if (rs.isBeforeFirst()){
                return "The email: " + newUser.getEmail() + " has already been taken";
            }
            else{
                con.close();
                con = DriverManager.getConnection(jdbcURL,uName,uPassword);
                stmt = con.createStatement();
                String query = " insert into users (email, password, "
                         + "user_type, first_name, last_name, department) values"
                         + " (?, ?, ?, ?, ?, ?)";
                 PreparedStatement preparedStmt = con.prepareStatement(query);
                 preparedStmt.setString(1, newUser.getEmail());
                 preparedStmt.setString(2,newUser.getPassword());
                 preparedStmt.setString(3,newUser.getUserType());
                 preparedStmt.setString(4,newUser.getFirstName());
                 preparedStmt.setString(5,newUser.getLastName());
                 preparedStmt.setString(6,newUser.getDepartment());

                preparedStmt.execute();
                con.close();

                return "true";
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
            
        }
     }
    
     /**
     *
     * @param ticket
     * @return
     */
    public static boolean updateUser(User user) {
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = " update users set email =?, user_type = ?, "
                     + "first_name = ?, last_name = ?, department = ? "
                     + " where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setString(1, user.getEmail());
             preparedStmt.setString(2,user.getUserType());
             preparedStmt.setString(3,user.getFirstName());
             preparedStmt.setString(4,user.getLastName());
             preparedStmt.setString(5,user.getDepartment());
             preparedStmt.setString(6,user.getId());
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
     }
    
    /**
     *
     * @param newAnswer
     * @return
     */
    public static boolean addSurveyEntry(SurveyAnswer newAnswer) {
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection(jdbcURL,uName,uPassword);
            
            Statement stmt = con.createStatement();
             String query = " insert into survey (ticket_id, "
                     + "client_id, q1, q2, q3, q4) values"
                     + " (?, ?, ?, ?, ?, ?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1, newAnswer.getTicketId());
             preparedStmt.setInt(2,newAnswer.getClientId());
             preparedStmt.setString(3,newAnswer.getQuestion1());
             preparedStmt.setString(4,newAnswer.getQuestion2());
             preparedStmt.setString(5,newAnswer.getQuestion3());
             preparedStmt.setString(6,newAnswer.getQuestion4());
             
            preparedStmt.execute();
            con.close();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
            
        }
     }
}
