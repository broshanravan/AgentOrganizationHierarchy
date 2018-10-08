


import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;


/*
 * Created on 18-Feb-2005
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
public class TeamMaintenance 
{
    public Vector getAllCurrentTeamNames(int groupIdIn)
    {
		Vector nameList = new Vector();
                PreparedStatement statement = null;
                ResultSet rs= null;
                Connection con  = null;  
		try{
                  con =ConnectionDetails.getConnection();
                   String sql =    " SELECT tgh.TEAM_ID " +
                                    " FROM BIR.TEAM t , " +
                                    " BIR.TEAM_GROUP_HISTORY tgh " +
                                    " WHERE BIR.tgh.GROUP_ID =?"+ 
                                    " AND tgh.END_DATE is NULL"+
                                    " AND tgh.TEAM_ID = t.TEAM_ID"+
                                    " AND t.ACTIVE_TEAM = 'Y'";
            
                   statement = con.prepareStatement(sql);
                   statement.setInt(1,groupIdIn);
                   ////System.out.println("SELECT>>>> "+ sql);
                   rs = statement.executeQuery();
                   while( rs.next() )
                   {
                        ////System.out.println("Inside RS ");
                        int id =rs.getInt(1);
                        ////System.out.println(" team Id from DB is "+ id);
                        Team team=this.getTeam(id) ; 
                        String name = team.getTeamName();
                        nameList.addElement(name);	            		            	      	
                   }
                   con.commit();	
                } catch(SQLException sqle) {
                  System.err.println("SQL or database not working " + sqle.getMessage());
                  sqle.printStackTrace();
                }    
                catch(Exception e) {
                  System.err.println("General exception " + e.getMessage());
                  e.printStackTrace();
                }
                finally {
                     try{
                          statement.close();
                          rs.close();                      
                     }catch(SQLException sqle){
                            System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }
                
                     catch(Exception e)
                     {
                        System.err.println("General exception " + e.getMessage());	   
                     }	
                 } 
                 return nameList;
       }
	
	public Vector getAllCurrentTeams(int groupIdIn)
	{
		Vector teamList = new Vector();
                Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try{
	          con =ConnectionDetails.getConnection();	          

	          String sql =    " SELECT tgh.TEAM_ID FROM BIR.TEAM t,"
                                + " BIR.TEAM_GROUP_HISTORY tgh"
                                +"  WHERE" 
                                + " tgh.GROUP_ID =?" 
                                + " AND tgh.END_DATE is NULL"
                                + " AND tgh.TEAM_ID = t.TEAM_ID"
                                + " AND t.ACTIVE_TEAM = 'Y'";
            
                   statement = con.prepareStatement(sql);
                   statement.setInt(1,groupIdIn);
                  ///System.out.println("Select>>>> "+ sql);
                  rs = statement.executeQuery();

	          ////System.out.println("Befor  Loop in teamInv getAllCurrentTeams ");
	          while( rs.next() ){
	          	////System.out.println("Inside RS");
	          	int id =rs.getInt(1);
	            	////System.out.println("Id in select is "+ id);
	            	Team team=this.getTeam(id) ;     	
	            	teamList.addElement(team);	            		            		            	
                  }
                  ////System.out.println("SELECT>>>> "+ sql);
                  con.commit();	
	    } catch(SQLException sqle){
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }
            catch(Exception e){
	      System.err.println("General exception " + e.getMessage());
	      e.printStackTrace();
	    }
            finally {
                   try{
                      statement.close();
                      rs.close();
                   }catch(SQLException sqle){
                       System.err.println("SQL or database not working " + sqle.getMessage());	    
                   }              
                   catch(Exception e) {
                      System.err.println("General exception " + e.getMessage());	   
                   }	
             } 
             return teamList;
	}
	
	public Team getTeam(int teamIdIn)
	{
             Team team= new Team();
             Connection con = null;
             PreparedStatement statement = null;
             ResultSet rs = null;
             try{
	          con =ConnectionDetails.getConnection();	          
	          String sql = "SELECT * FROM BIR.TEAM WHERE TEAM_ID =?";
                  statement = con.prepareStatement(sql);
                  statement.setInt(1,teamIdIn);
                  rs = statement.executeQuery();
            
	          while( rs.next()) {      	
	            	team.setTeamId( rs.getInt(1));
	            	team.setTeamName(rs.getString(2));
	            	team.setManagerId(rs.getInt(3));
                        team.setBusneseFunId(rs.getInt(4));	
                        team.setClassOfBusId(rs.getString(5));              
                        String active= rs.getString(6);
                        
                        if(active.equalsIgnoreCase("Y")) {
                          team.setActive(true);
                        }
                        
                        else if(active.equalsIgnoreCase("N")){
                          team.setActive(false);
                        }                        
                        String perm= rs.getString(7);
                        if(perm.equalsIgnoreCase("Y")) {
                          team.setPerm(true);
                        }                        
                        else if(perm.equalsIgnoreCase("N"))
                        {
                          team.setPerm(false);
                        }
	          }
	         
		
	    }
	    catch(SQLException sqle)
	    {
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }

	    catch(Exception e)
	    {
	      System.err.println("General exception " + e.getMessage());
	     
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
               }
          
               catch(Exception e)
               {
                  System.err.println("General exception " + e.getMessage());	   
               }	
        } 

	    return team;
	}
	
	public Team getTeamByName(String teamNameIn)
	{
               Team team= new Team();
               Connection con = null;
               PreparedStatement statement = null;
               ResultSet rs = null;
               try
	      {
                  con =ConnectionDetails.getConnection();
	          
	          String sql = "SELECT * FROM BIR.TEAM WHERE TEAM_NAME =?";
                  statement = con.prepareStatement(sql);
                  statement.setString(1,teamNameIn);
                  rs = statement.executeQuery();
	         
	          while( rs.next()){      	
                      team.setTeamId( rs.getInt(1));
                      team.setTeamName(rs.getString(2));
                      team.setManagerId(rs.getInt(3));
                      team.setBusneseFunId(rs.getInt(4)); 
                      team.setClassOfBusId(rs.getString(5));
                      String active= rs.getString(6);                          
                      if(active.equalsIgnoreCase("Y")) {
                        team.setActive(true);
                      }                          
                      else if(active.equalsIgnoreCase("N")){
                        team.setActive(false);
                      }                          
                      String perm= rs.getString(7);
                      
                      if(perm.equalsIgnoreCase("Y")) {
                        team.setPerm(true);
                      }
                      
                      else if(perm.equalsIgnoreCase("N")) {
                        team.setPerm(false);
                      }
	          }	           
		
              } catch(SQLException sqle) {
                System.err.println("SQL or database not working " + sqle.getMessage());
                sqle.printStackTrace();
              }  
              catch(Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
              }
              finally{
                try {
                      statement.close();
                      rs.close();            
                  }catch(SQLException sqle) {
                            System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }                
                     catch(Exception e){
                        System.err.println("General exception " + e.getMessage());	   
                     }	
                } 
                return team;
	}
        
  public Team getActiveTeamByName(String teamNameIn)
  {
         Team team= new Team();
         Connection con = null;
         PreparedStatement statement = null;
         ResultSet rs = null;
         try
        {
            con =ConnectionDetails.getConnection();
            
            String sql = "SELECT * FROM BIR.TEAM WHERE TEAM_NAME =?"+
                         " AND" +
                         " ACTIVE_TEAM='Y'";
            statement = con.prepareStatement(sql); 
            statement.setString(1,teamNameIn);
            rs = statement.executeQuery();
           
            while( rs.next() ) {             
                team.setTeamId( rs.getInt(1));
                team.setTeamName(rs.getString(2));
                team.setManagerId(rs.getInt(3));
                team.setBusneseFunId(rs.getInt(4)); 
                team.setClassOfBusId(rs.getString(5));
                String active= rs.getString(6);
                
                if(active.equalsIgnoreCase("Y")) {
                  team.setActive(true);
                }                    
                else if(active.equalsIgnoreCase("N")){
                  team.setActive(false);
                }                    
                String perm= rs.getString(7);                    
                if(perm.equalsIgnoreCase("Y")) {
                  team.setPerm(true);
                }                    
                else if(perm.equalsIgnoreCase("N")) {
                  team.setPerm(false);
                }
            }                
          
        }catch(SQLException sqle){
          System.err.println("SQL or database not working " + sqle.getMessage());
          sqle.printStackTrace();
        }
        catch(Exception e){
          System.err.println("General exception " + e.getMessage());
          e.printStackTrace();
        }
        finally {
          try{
                statement.close();
                rs.close();            
            } catch(SQLException sqle){
                      System.err.println("SQL or database not working " + sqle.getMessage());         
               }          
               catch(Exception e){
                  System.err.println("General exception " + e.getMessage());         
               }  
          } 
          return team;
  }
	
	static final String getSequenceSQL = "SELECT BIR.SEQ_TEAM_ID.NEXTVAL FROM dual";
	
	private static long getNextSeqVal (Connection conn) throws SQLException {
	    PreparedStatement stmt = conn.prepareStatement(getSequenceSQL);
	    ResultSet rs   = stmt.executeQuery();
	    rs.next();
	    long id = rs.getLong(1);
	    rs.close();
	    stmt.close();
	    return id;

	  } 
	
	public void storeTeam(String teamNameIn, int groupId, int bFIdIn,String cobIdIn, boolean perm)
	{
	     Connection con = null;
             PreparedStatement statement = null;
             PreparedStatement statement1 = null;
             try
	     {
	        
	      	  con =ConnectionDetails.getConnection();
	          String insertCommand =    "INSERT into BIR.TEAM"
                                          + "("
                                          + " TEAM_ID," 
                                          + " TEAM_NAME," 
                                          + " business_function_id,"
                                          + " CLASS_OF_BUSINESS_ID,"
                                          + " PERMANENT_TEAM "
                                          + ") "
                                          + " VALUES(?,?,?,?,?)";

	          statement = con.prepareStatement(insertCommand );
	          long var = getNextSeqVal(con);
	          statement.setLong(1,var);
                  statement.setString(2,teamNameIn);
                  statement.setInt(3,bFIdIn);
	          statement.setString(4,cobIdIn);
            
                  if(perm==false)
                  {
                     statement.setString(5,"N");
                  }
                  else
                  {
                     statement.setString(5,"Y");
                  }
                    
                  ////System.out.println("SQL Insert >> " + insertCommand );
                  statement.executeUpdate();	          
                  String insertComand2 =     " INSERT INTO " 
                                            + " BIR.TEAM_GROUP_HISTORY(" 
                                                                          + " GROUP_ID," 
                                                                          + " TEAM_ID," 
                                                                          + " START_DATE" 
                                                                  + " )" 
                                            + " VALUES" 
                                                                  + " (?,?,SYSDATE)";	      
                   ////System.out.println("SQL Insert >> " + insertComand2 );
                   statement1 = con.prepareStatement(insertComand2);
	           statement1.setInt(1,groupId);
	           statement1.setLong(2,var);
                   statement1.execute();
                   con.commit();	
	       }

               catch(SQLException sqle)
               {    
                  System.err.println("SQL or database not working " + sqle.getMessage());
                  sqle.printStackTrace();
               }
        
              finally
              {
                     try
                     {
                        statement.close();
                        statement1.close();                  
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
	public void relocateTeam(int teamIdIn, int newGroupId,Date dateIn)
	{
              Connection con = null;
              PreparedStatement statement = null;
              PreparedStatement statement1 = null;
              try
	      {
	        
                    con =ConnectionDetails.getConnection();        
                    String upDateComand1=   " UPDATE BIR.TEAM_GROUP_HISTORY " 
                                          + " SET END_DATE=?-1"
                                          + " WHERE " 
                                          + " TEAM_ID="+ teamIdIn 
                                          + " AND END_DATE is NULL ";                                                                    
                    
                    ////System.out.println("SQL Insert >> " + upDateComand1 );
                    statement = con.prepareStatement(upDateComand1);   
                    statement.setDate(1,new java.sql.Date(dateIn.getTime())); 
                    statement.executeUpdate();                     
                    
                    String upDateComand2=     "INSERT INTO BIR.TEAM_GROUP_HISTORY(" 
                                                                                + " GROUP_ID," 
                                                                                + " TEAM_ID," 
                                                                                + " START_DATE" 
                                                                                + ")" 
                    + " VALUES (?,?,?)";                    
                    ////System.out.println("SQL Insert >> " + upDateComand2 );
                    statement1 = con.prepareStatement(upDateComand2);
                    statement1.setInt(1,newGroupId);
                    statement1.setInt(2,teamIdIn);
                    statement1.setDate(3,new java.sql.Date(dateIn.getTime()));  
                    statement1.executeUpdate(); 
                    con.commit();	
	      }

	      catch(SQLException sqle)
	      {    
	      	System.err.println("SQL or database not working " + sqle.getMessage());
                sqle.printStackTrace();
                sqle.printStackTrace();
	      }
              finally
              {
                  try
                    {
                        statement.close();
                        statement1.close();                    
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

	}
	
	public int getGroupId(int teamId)
	{
              int groupId =0;
              Connection con = null;
              PreparedStatement statement = null;
              ResultSet rs= null;
              try
	      {
	          con =ConnectionDetails.getConnection();
	          
	          String sql =    " SELECT GROUP_ID " +
                                  " FROM BIR.TEAM_GROUP_HISTORY" +
                                  " WHERE" +
                                  " TEAM_ID =?" +
                                  " AND" +
                                  " END_DATE is NULL";    
	           statement = con.prepareStatement(sql);
	           statement.setInt(1,teamId);
                  rs = statement.executeQuery();
	          while( rs.next() ){
                      groupId =rs.getInt(1);
	          }
	       }
	       catch(SQLException sqle) {    
                  System.err.println("SQL or database not working " + sqle.getMessage());
                  sqle.printStackTrace();
	       }	
               finally{
                  try {
                        statement.close();
                        rs.close();                  
                    }catch(SQLException sqle) {
                              System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }
                     catch(Exception e) {
                          System.err.println("General exception " + e.getMessage());	   
                     }	
               }
               return groupId;
		
	}
	public void  upDateTeam(Team team){
              Connection con = null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              
              try
	      {

	        con =ConnectionDetails.getConnection();
                String perm="Y";
                if(team.isPerm()==false)
                {
                   perm="N";
                }
	        String sql =   " UPDATE BIR.Team "           +
                               " SET TEAM_NAME =? "          +
                               " business_function_id=?"     +
                               " CLASS_OF_BUSINESS_ID=?"     +
                               " PERMANENT_TEAM=?"           +
                               " WHERE TEAM_ID =?" ;
	         
	         statement = con.prepareStatement(sql);
                 statement.setString(1,team.getTeamName());
	         statement.setInt(2,team.getBusneseFunId());
	         statement.setString(3,team.getClassOfBusId());
	         statement.setString(4,perm);
	         statement.setInt(5,team.getTeamId());
                 
	         rs = statement.executeQuery();
	         ////System.out.println("Update>>> "+ sql);
                 con.commit();	
	      }catch(SQLException sqle){    
	      	System.err.println("SQL or database not working " + sqle.getMessage());
                sqle.printStackTrace();
	      }	
              finally{
                  try{
                      statement.close();
                      rs.close();                  
                  }catch(SQLException sqle){
                       System.err.println("SQL or database not working " + sqle.getMessage());	    
                  }                
                  catch(Exception e) {
                      System.err.println("General exception " + e.getMessage());	   
                  }	
               } 
                      
	}
  
        public void  editTeam(int teamId, String newName, int bFIdIn)
	{
		Connection con = null;
                PreparedStatement statement = null;
		try{

                      con =ConnectionDetails.getConnection();
                      
                      String sql = " UPDATE BIR.Team "    +
                                   " SET TEAM_NAME =? "   +
                                   " business_function=?" +   
                                   " WHERE TEAM_ID =?" ;
                      
                    statement = con.prepareStatement(sql);
	            statement.setString(1,newName);
	            statement.setInt(2,bFIdIn);
	            statement.setInt(3,teamId);
                    statement.executeUpdate();
                    ////System.out.println("SELECT>>>> "+ sql);
                    con.commit();	
	      }


	      catch(SQLException sqle){    
	      	System.err.println("SQL or database not working " + sqle.getMessage());
                sqle.printStackTrace();
	      }	
              
              finally{
                try{
                      statement.close();
                  } catch(SQLException sqle) {
                            System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }
                     catch(Exception e) {
                        System.err.println("General exception " + e.getMessage());	   
                     }	
                } 
        
	}
	//Old Team ManagerMethod for one manager per team
	public void assignTeamManager(int teamIdIn,int managerIdIn)
	{
		Connection con = null;
                PreparedStatement statement = null;
                try
	        {

	          con =ConnectionDetails.getConnection();
	          
	          String updateStatment= " UPDATE BIR.TEAM SET " +
                                         " MANAGER_ID=?"+
			  		 " WHERE TEAM_ID=?";
	                
	          statement = con.prepareStatement(updateStatment);  
		  statement.setInt(1,managerIdIn);
		  statement.setInt(2,teamIdIn);
	          statement.executeUpdate();  
                  con.commit();	
		 }catch(SQLException sqle){    
		      	System.err.println("SQL or database not working " + sqle.getMessage());
                        sqle.printStackTrace();
		   }
                   finally{
                       try{
                            statement.close();;
                       } catch(SQLException sqle){
                          System.err.println("SQL or database not working " + sqle.getMessage());	    
                       }
                       catch(Exception e){
                          System.err.println("General exception " + e.getMessage());	   
                       }	
                   } 

	}
  
  public void activateTeam(int teamIdIn)
  {
      Connection con = null;
      PreparedStatement statement = null;
      PreparedStatement statement1 = null;
      try
      {
              con =ConnectionDetails.getConnection();              
              String updateStatment=      " UPDATE BIR.TEAM SET ACTIVE_TEAM = 'Y'"
                                        + " WHERE TEAM_ID =?";  
              
              statement = con.prepareStatement(updateStatment); 
              statement.setInt(1,teamIdIn);
              
              String updateStatment1=     " UPDATE BIR.TEAM_GROUP_HISTORY  tgh" 
                                        + " SET END_DATE = ''"
                                        + " WHERE " 
                                        + " TEAM_ID=?"  
                                        + " AND END_DATE =(SELECT MAX(END_DATE) from BIR.TEAM_GROUP_HISTORY tghs"
                                        + " WHERE tghs.TEAM_ID =? "  
                                        +" ) "; 
              
              statement1 = con.prepareStatement(updateStatment1);
              statement1.setInt(1,teamIdIn);
              statement1.setInt(2,teamIdIn); ;  
              statement.execute(); 
              statement.execute();  
              con.commit();	
      }
      catch(SQLException sqle)
      {    
            System.err.println("SQL or database not working " + sqle.getMessage());
            sqle.printStackTrace();
      }
      finally{
           try{
               statement.close();            
           } catch(SQLException sqle){
               System.err.println("SQL or database not working " + sqle.getMessage());	    
           }
           catch(Exception e){
               System.err.println("General exception " + e.getMessage());	   
           }	
       } 
  }
  
  public void deActivateTeam(int teamIdIn) {
           Connection con = null;
           PreparedStatement statement = null;
           PreparedStatement statement1 = null;
           try{
                      con =ConnectionDetails.getConnection();                     
                      String updateStatment=    " UPDATE BIR.TEAM SET ACTIVE_TEAM = 'N'"
                                              + " WHERE TEAM_ID =?";
                            
                      statement = con.prepareStatement(updateStatment); 
                      statement.setInt(1,teamIdIn);
                      String updateStatment1=   " UPDATE BIR.TEAM_GROUP_HISTORY " 
                                              + " SET END_DATE=SYSDATE "
                                              + " WHERE " 
                                              + " TEAM_ID=?" 
                                              + " AND END_DATE is NULL ";                                              
                      
                      statement1 = con.prepareStatement(updateStatment1);
                      statement1.setInt(1,teamIdIn);                    
                      statement.executeUpdate();  
                      statement1.executeUpdate();  
                      con.commit();	
            }catch(SQLException sqle){    
                System.err.println("SQL or database not working " + sqle.getMessage());
                sqle.printStackTrace();
            }
           finally {
              try {
                    statement.close();
                }catch(SQLException sqle) {
                      System.err.println("SQL or database not working " + sqle.getMessage());	    
                }          
                catch(Exception e){
                  System.err.println("General exception " + e.getMessage());	   
                }	
           }
    }
    
    public Vector getAllInactiveTeams(){
     Vector teamVec = new Vector(); 
		 Connection con = null;
     PreparedStatement statement = null;
     ResultSet rs = null;
		try
	      {

	          con =ConnectionDetails.getConnection();
	          
	          String sql = "SELECT * FROM BIR.TEAM WHERE ACTIVE_TEAM = 'N'";
                  statement = con.prepareStatement(sql);
                  rs = statement.executeQuery();
            
	          while( rs.next() ){      	
	            	Team team= new Team();
                        team.setTeamId( rs.getInt(1));
                        team.setTeamName(rs.getString(2));
                        team.setManagerId(rs.getInt(3));
                        team.setBusneseFunId(rs.getInt(4)); 
                        team.setClassOfBusId(rs.getString(5));	
                        String active= rs.getString(6);
                        if(active.equalsIgnoreCase("Y")){
                          team.setActive(true);
                        }                        
                        else if(active.equalsIgnoreCase("N")) {
                          team.setActive(false);
                        }                                                
                        String perm= rs.getString(7);
                        if(perm.equalsIgnoreCase("Y")){
                          team.setPerm(true);
                        }                        
                        else if(perm.equalsIgnoreCase("N")){
                          team.setPerm(false);
                        }                        
                        teamVec.addElement(team);
	          }
	          con.commit();	
		
	    }catch(SQLException sqle) {
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }
            catch(Exception e){
	      System.err.println("General exception " + e.getMessage());
	     
	    }
            finally{
                     try{
                        statement.close();
                        rs.close();                  
                     }catch(SQLException sqle){
                          System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }
                      catch(Exception e) {
                        System.err.println("General exception " + e.getMessage());	   
                     }	
             } 
	    return teamVec;
    }
    
    public Vector getAllActiveTeams()
    {
            Vector teamVec = new Vector(); 
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
	          con =ConnectionDetails.getConnection();	          
	          String sql = "SELECT * FROM BIR.TEAM WHERE ACTIVE_TEAM = 'Y'";
	          statement = con.prepareStatement(sql);
	          rs = statement.executeQuery();
                  ////System.out.println("Select>>>>"+sql);
	          while( rs.next() ){      	
	            	  Team team= new Team();
                          team.setTeamId( rs.getInt(1));
                          team.setTeamName(rs.getString(2));
                          team.setManagerId(rs.getInt(3));
                          team.setBusneseFunId(rs.getInt(4)); 
                          team.setClassOfBusId(rs.getString(5));                          
                          String active= rs.getString(6);                          
                          if(active.equalsIgnoreCase("Y")){
                            team.setActive(true);
                          }                          
                          else if(active.equalsIgnoreCase("N")){
                            team.setActive(false);
                          }                                                   
                          String perm= rs.getString(7);
                          
                          if(perm.equalsIgnoreCase("Y")){
                            team.setPerm(true);
                          }
                          
                          else if(perm.equalsIgnoreCase("N")){
                            team.setPerm(false);
                          }                 
                          teamVec.addElement(team);
	          }	         
		
	    } catch(SQLException sqle){
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }

	    catch(Exception e){
	      System.err.println("General exception " + e.getMessage());	     
	    }
            finally{
                 try{
                    statement.close();
                    rs.close();                  
                 }catch(SQLException sqle)
                 {
                        System.err.println("SQL or database not working " + sqle.getMessage());	    
                 }                
                 catch(Exception e){
                    System.err.println("General exception " + e.getMessage());	   
                 }	
              } 
              return teamVec;
    }
    
    public Vector getAllSiteActiveTeamNames(int siteIdIn)
    {
            Vector teamVec = new Vector(); 
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try  {    
                  con =ConnectionDetails.getConnection();                  
                  String sql =  " SELECT t.TEAM_NAME FROM" +
                                " BIR.TEAM t," +
                                " BIR.TEAM_GROUP_HISTORY tgh," +
                                " BIR.TEAM_GROUP tg," +
                                " BIR.DEPARTMENT d," +
                                " BIR.SITE s" +
                                " WHERE" +
                                " t.team_Id= tgh.Team_Id" +
                                " AND" +
                                " tgh.END_DATE IS NULL" +
                                " AND" +
                                " tgh.group_Id=tg.group_id" +
                                " AND" +
                                " tg.department_Id = d.department_id" +
                                " AND" +
                                " d.Site_id=?"  +
                                " AND" +
                                " ACTIVE_TEAM = 'Y'";                  
                  
                  statement = con.prepareStatement(sql);
                  statement.setInt(1,siteIdIn);
                  ////System.out.println("Select>>>>"+sql);
                  rs = statement.executeQuery();                  
                  while( rs.next() )
                  {             
                          String n= rs.getString(1);                                          
                          teamVec.addElement(n);
                  }              
                
            }catch(SQLException sqle) {
              System.err.println("SQL or database not working " + sqle.getMessage());
              sqle.printStackTrace();
            }
            catch(Exception e){
              System.err.println("General exception " + e.getMessage());             
            }
            finally {
                 try{
                    statement.close();
                    rs.close();                  
                 } catch(SQLException sqle){
                        System.err.println("SQL or database not working " + sqle.getMessage());         
                 }
            
                 catch(Exception e){
                    System.err.println("General exception " + e.getMessage());         
                 }  
              } 
              return teamVec;
    }
    
  public Vector getAllSiteActiveTeams(int siteId)
  {
          Vector teamVec = new Vector(); 
          Connection con = null;
          PreparedStatement statement = null;
          ResultSet rs = null;
          try
          {
  
                con =ConnectionDetails.getConnection();
                
                String sql =  " SELECT distinct" +
                              " t.TEAM_ID," +
                              " t.TEAM_NAME," +
                              " t.MANAGER_ID," +
                              " t.BUSINESS_FUNCTION_ID," +
                              " t.CLASS_OF_BUSINESS_ID," +
                              " t.ACTIVE_TEAM," +
                              " t.PERMANENT_TEAM" +
                              " FROM" +
                              " BIR.TEAM t," +
                              " BIR.TEAM_GROUP_HISTORY tgh," +
                              " BIR.TEAM_GROUP tg," +
                              " BIR.DEPARTMENT d," +
                              " BIR.SITE s" +
                              " WHERE" +
                              " t.team_Id= tgh.Team_Id" +
                              " AND" +
                              " tgh.END_DATE IS NULL" +
                              " AND" +
                              " tgh.group_Id=tg.group_id" +
                              " AND" +
                              " tg.department_Id = d.departMent_id" +
                              " AND" +
                              " d.Site_id=?" +
                              " AND" +
                              " ACTIVE_TEAM = 'Y'" +
                              " ORDER BY" +
                              " t.TEAM_NAME";
                
                statement = con.prepareStatement(sql);
                statement.setInt(1,siteId);                
                rs = statement.executeQuery();
                while( rs.next()) {             
                        Team team= new Team();
                        team.setTeamId( rs.getInt(1));
                        team.setTeamName(rs.getString(2));
                        team.setManagerId(rs.getInt(3));
                        team.setBusneseFunId(rs.getInt(4)); 
                        team.setClassOfBusId(rs.getString(5));                          
                        String active= rs.getString(6);                          
                        if(active.equalsIgnoreCase("Y")){
                          team.setActive(true);
                        }
                        
                        else if(active.equalsIgnoreCase("N")){
                          team.setActive(false);
                        }                                                   
                        String perm= rs.getString(7);
                        
                        if(perm.equalsIgnoreCase("Y")) {
                          team.setPerm(true);
                        }
                        
                        else if(perm.equalsIgnoreCase("N")) {
                          team.setPerm(false);
                        }                 
                        teamVec.addElement(team);
                }              
              
          } catch(SQLException sqle) {
            System.err.println("SQL or database not working " + sqle.getMessage());
            sqle.printStackTrace();
          }
          catch(Exception e) {
            System.err.println("General exception " + e.getMessage());             
          }
          finally {
               try{
                  statement.close();
                  rs.close();                  
               }catch(SQLException sqle){
                      System.err.println("SQL or database not working " + sqle.getMessage());         
               }              
               catch(Exception e)
               {
                  System.err.println("General exception " + e.getMessage());         
               }  
            } 
            return teamVec;
  }
  
  public Group getTeamsCurentGroup(int teamId){
  
      Group group = new Group();
      Connection con = null;
      PreparedStatement statement = null;
      ResultSet rs = null;
      try{      
            con =ConnectionDetails.getConnection();
           
            String sql =  " SELECT * FROM" +
                          " BIR.TEAM t," +
                          " BIR.TEAM_GROUP_HISTORY tgh," +
                          " BIR.TEAM_GROUP tg" +
                          " WHERE" +
                          " t.team_Id= tgh.Team_Id" +
                          " AND" +
                          " tgh.END_DATE IS NULL" +
                          " AND" +
                          " tgh.group_Id=tg.group_id" +
                          " AND" +
                          " tg.ACTIVE_GROUP='Y'" +
                          " AND" +
                          " t.TEAM_ID=?";
            
            statement = con.prepareStatement(sql);
            statement.setInt(1,teamId);            
            rs = statement.executeQuery();                  
            while( rs.next() ) {             
                group.setGroupId(rs.getInt("GROUP_ID")); 
                group.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
                group.setGroupName(rs.getString("GROUP_NAME"));
            }              
          
      }catch(SQLException sqle) {
        System.err.println("SQL or database not working " + sqle.getMessage());
        sqle.printStackTrace();
      }      
      catch(Exception e) {
        System.err.println("General exception " + e.getMessage());             
      }
      finally{
               try{
                  statement.close();
                  rs.close();                  
               }catch(SQLException sqle) {
                      System.err.println("SQL or database not working " + sqle.getMessage());         
               }          
               catch(Exception e){
                  System.err.println("General exception " + e.getMessage());         
               }  
        } 
        return group;
      
  }
  
  
    public Site getTeamsCurentSite(int teamId){
    
        Site  site = new Site();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
        
              con =ConnectionDetails.getConnection();
              
              String sql =  " SELECT * FROM" +
                            " BIR.SITE s," +
                            " BIR.DEPARTMENT d," +
                            " BIR.TEAM t," +
                            " BIR.TEAM_GROUP_HISTORY tgh," +
                            " BIR.TEAM_GROUP tg" +
                            " WHERE" +
                            " t.TEAM_ID=?"+
                            " AND" + 
                            " t.team_Id= tgh.Team_Id" +
                            " AND" +
                            " tgh.END_DATE IS NULL" +
                            " AND" +
                            " tgh.group_Id=tg.group_id" +
                            " AND " +
                            " tg.DEPARTMENT_ID=d.DEPARTMENT_ID" +
                            " AND" +
                            " d.SITE_ID=s.SITE_ID" +
                            " AND" +
                            " tg.ACTIVE_GROUP='Y'" ;
              
            statement = con.prepareStatement(sql);
            statement.setInt(1,teamId);
              
            rs = statement.executeQuery();                  
            while( rs.next() ) {             
                  site.setSiteId(rs.getInt("SITE_ID")); 
                  site.setSiteName(rs.getString("SITE_NAME"));
            }              
            
        }catch(SQLException sqle){
          System.err.println("SQL or database not working " + sqle.getMessage());
          sqle.printStackTrace();
        }
        
        catch(Exception e){
          System.err.println("General exception " + e.getMessage());             
        }
        finally{
             try {
                statement.close();
                rs.close();                  
             }catch(SQLException sqle){
                    System.err.println("SQL or database not working " + sqle.getMessage());         
             }            
             catch(Exception e){
                System.err.println("General exception " + e.getMessage());         
             }  
          } 
          return site;
        
    }
  
  
    
}
