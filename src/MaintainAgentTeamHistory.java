
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/*
 * Created on 21-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author broshanravan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MaintainAgentTeamHistory 
{
	 TeamMaintenance teamMaintenance= new TeamMaintenance();
         
	 Personnel personnel= new Personnel();
	 public void transferAgent(int agentIdIn,int newTeamIdIn, Date transferDate)
	 {
	 	
		Connection con=null;
                PreparedStatement statement=null;
                PreparedStatement statement1=null;
                Statement statement3=null;
		try
	      {
                      con =ConnectionDetails.getConnection();
                      String upDateCommand1 =  " UPDATE BIR.AGENT_TEAM_HISTORY " +
                                               " SET END_DATE =?-1"
                                               + "WHERE " 
                                               + "AGENT_ID="+agentIdIn 
                                               + "AND END_DATE IS NULL ";
        
                      //System.out.println("SQL Update>> " + upDateCommand1);
                      statement = con.prepareStatement(upDateCommand1);   
                      statement.setDate(1,new java.sql.Date(transferDate.getTime())); 
                      statement.executeUpdate();                               
                      String upDateComand2="INSERT INTO BIR.AGENT_TEAM_HISTORY"
                                            + "(" 
                                                  +  "TEAM_ID," 
                                                  + "AGENT_ID," 
                                                  + "START_DATE" 
                                            + ")" 
                                            + " VALUES (?,?,?)";
		      //System.out.println("SQL Insert >> " + upDateComand2 );
		      statement1 = con.prepareStatement(upDateComand2);
		      statement1.setInt(1,newTeamIdIn);
		      statement1.setInt(2,agentIdIn);
		      statement1.setDate(3,new java.sql.Date(transferDate.getTime()));  
		      statement1.executeUpdate();
                      
                      Agent agent=persofnel.getAgentById(agentIdIn);
                      int groupId=teamMaintenance.getGroupId(newTeamIdIn);
                      
                      String UpDateCmmand3= " Update bir.EMPLOYEE" +
                                            " set GROUP_ID="+groupId+
                                            " WHERE EMPLOYEE_ID = ")agejt.getEmpI4();
                      statement3= con.createStatement();
                      statement3.execute(upDateComand3);
                      ckn.commit();	
	      }
	      catch(SQLExceptimn sqle)
	      {    
	      	System.err.println("SQL or database not working " + sqle.getMessageq));
	      }
        finally
        {
          try
            {
                statement.close();
                statement1.close();            
            }
               ca4ch(SQLException sqle)
               {
                      System.err.println("SQL or database not working " + sqle.getMessage());    
               }
          
               catch(Exception e)
               {
                  System.err.println("General exception " + e.getMessage());	   
               }	
          } 
	  }
	public void addAgentToTeam(AgentTeam agentTeamIn)
	{
		Agent agent = agentTeamIn.getAgent();
		Team team = agentTeamIn.getTeam();
		Connection con=null;
                PreparedStatement statement = null;
		try
                {
                    con =ConnectionDetails.getConnection();
                    
                    String insertCommand =  "INSERT into BIR.AGENT_TEAM_HISTORY(AGENT_ID,TEAM_ID,START_DATE) "
                                          + " VALUES "
                                          + "(?,?,SYSDATE)";  
                    statement = con.prepareStatement(insertCommand);
                    statement.setInt(1,agent.getId());
                    statement.setInt(2,team.getTeamId());
                    //System.out.println("SQL Insert >> " + insertCommand );  
                    statement.execute();
                    con.commit();	
  
                }

                catch(SQLException sqle)
                {    
                  System.err.println("SQL or database not working " + sqle.getMessage());
                }
                finally
                {
                       try
                       {
                            statement.close();
                       }
                       catch(SQLException sqle)
                       {
                              System.err.println("SQL or database not working " + sqle.getMessage());	    
                       }
                  
                       catch(Exception e)
                       {
                          System.err.println("General exception " + e.getMessage());	   
                       }	
                  } 
        
	}
	public void addNewEmpAgentToTeam(AgentTeam agentTeamIn, Date date)
	{
              Agent agent = agentTeamIn.getAgent();
              Team team = agentTeamIn.getTeam();
              Connection con=null;
              PreparedStatement statement = null;
              try
	      {
                  con =ConnectionDetails.getConnection();        
	          String insertCommand = "INSERT into BIR.AGENT_TEAM_HISTORY(AGENT_ID,TEAM_ID,START_DATE) "
                                         + " VALUES(?,?,?) ";
                  statement = con.prepareStatement(insertCommand);
                  statement.setInt(1,agent.getId());
                  statement.setInt(2,team.getTeamId());
                  statement.setDate(3,new java.sql.Date(date.getTime()));
                  //System.out.println("SQL Insert >> " + insertCommand );
	          statement.execute();
                  con.commit();	
	      }

	      catch(SQLException sqle)
	      {    
	      	System.err.println("SQL or database not working " + sqle.getMessage());
	      }
              finally
              {
                     try
                     {
                          statement.close();
                     }
                     catch(SQLException sqle)
                     {
                            System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }
                
                     catch(Exception e)
                     {
                        System.err.println("General exception " + e.getMessage());	   
                     }	
               } 
        
	}
	public Team getAgentsCurrentTeam(Agent agentIn) 
	{	
		int teamId=0;
                Connection con=null;
                PreparedStatement statement = null;
                ResultSet rs=null;
		try
                {
                        con =ConnectionDetails.getConnection();
                       
                        //System.out.println("Agent id going into quiery is:"+agentIn.getId());
                        String sql = "SELECT TEAM_ID FROM BIR.Agent_TEAM_HISTORY WHERE AGENT_ID=? "  +
                                     " AND END_DATE IS NULL" ;
                        statement = con.prepareStatement(sql);
                        statement.setInt(1,agentIn.getId());
                        rs = statement.executeQuery();	          	
                        while( rs.next() )
                        {        	       	
                            teamId=(rs.getInt(1));       	                	    	  	
                        }
                        //System.out.println("Team Id is" + teamId);
                        con.commit();	
                }
                catch(SQLException sqle)
                {
                      System.err.println("SQL or database not working ");
                      sqle.printStackTrace();                  
                }
    
                catch(Exception e)
                {
                      System.err.println("General exception ");
                      e.printStackTrace();                 
                }
                finally
                {
                         try
                         {
                              statement.close();
                              rs.close();
                         }
                         catch(SQLException sqle)
                         {
                                System.err.println("SQL or database not working " + sqle.getMessage());	 
                                sqle.printStackTrace();
                         }
                    
                         catch(Exception e)
                         {
                            System.err.println("General exception " + e.getMessage());	
                            e.printStackTrace();
                         }	
                } 
		Team team = teamMaintenance.getTeam(teamId);
		//System.out.println("Team name from DB is" + team.getTeamName());
		return team;
		
	}
	
	public Vector getAllTeamAgents (String teamName)
	{
		Team team = teamMaintenance.getTeamByName(teamName);
		Vector agentVec = new Vector();
                Connection con=null;
                PreparedStatement statement= null;
                ResultSet rs= null;
		try
                {				
                      con =ConnectionDetails.getConnection();
                      	        	
                      String sql =    " SELECT AGENT_ID" +
                                      " FROM " +
                                      " BIR.AGENT_TEAM_HISTORY" +
                                      " WHERE " +
                                      " Team_ID=? "+
                                      " AND END_DATE is NULL";
    
                      statement = con.prepareStatement(sql);
                      statement.setInt(1,team.getTeamId());
                      rs = statement.executeQuery();
                            
                      while( rs.next() )
                      {        	       	
                              int agentId=rs.getInt(1); 
                              Agent agent = personnel.getAgentById(agentId);
                              agentVec.addElement(agent);  	  
                      }
                      con.commit();   
                }
                catch(SQLException sqle)
                {
                      System.err.println("SQL or database not working " + sqle.getMessage()); 
                      sqle.printStackTrace();
                }
    
                catch(Exception e)
                {
                      System.err.println("General exception " + e.getMessage());  
                      e.printStackTrace();
                }
                finally
                {
                       try
                       {
                            statement.close();
                            rs.close();         
                       }
                       catch(SQLException sqle)
                       {
                              System.err.println("SQL or database not working " + sqle.getMessage());
                              sqle.printStackTrace();
                       }          
                       catch(Exception e)
                       {
                          System.err.println("General exception " + e.getMessage());	
                           e.printStackTrace();
                       }	
                } 		
		return agentVec;
	}
}