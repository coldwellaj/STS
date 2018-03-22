/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author coldwellaj
 */
public class Team {
    private int id;
    private String teamName;
    private int teamLeaderId;
    private String teamLeaderName;
    
    public Team(){
        id = -1;
        teamName = "null";
        teamLeaderId = -1;
        teamLeaderName = "";
    }
    
    public Team(int id, String teamName, int leader, String teamLeaderName){
        this.id = id;
        this.teamName = teamName;
        this.teamLeaderId = leader;
        this.teamLeaderName = teamLeaderName;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getTeamName(){
        return this.teamName;
    }
    
    public String getTeamLeaderName(){
        return this.teamLeaderName;
    }
    
    public int getTeamLeader(){
        return this.teamLeaderId;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
    
    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }
    
    public void setTeamLeader(int teamLeader){
        this.teamLeaderId = teamLeader;
    }
}
