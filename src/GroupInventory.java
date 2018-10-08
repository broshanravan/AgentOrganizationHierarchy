
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/*
 * Created on 17-Feb-2005
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
public class GroupInventory 
{
	
	public void renameGroup(int groupIdIn ,String newName)
	{
		
    Connection con=null;
    PreparedStatement statement = null;
    PreparedStatement statement1= null;
    try {
          con =ConnectionDetails.getConnection();	          
          String upDateComand=  " UPDATE bir.TEAM_GROUP " +
                                " SET GROUP_NAME=?" +
                                " WHERE GROUP_ID=?";	          
          statement = con.prepareStatement(upDateComand);
          statement.setString(1,newName);
          statement.setInt(2,groupIdIn);
          //System.out.println("SQL Update >> " + upDateComand );
          statement.execute();
          
          String insertComand2 = "INSERT INTO TEAM_GROUP_ALIAS(" +
                                                                "GROUP_ID," +
                                                                "Group_ALIAS,"+
                                                                "START_DATE" +
                                                              ")" +
                                                              "VALUES(?,?,SYSDATE)";
          //System.out.println("SQL Insert >> " + insertComand2 );
          statement1 = con.prepareStatement(insertComand2);
          statement1.setInt(1,groupIdIn);
          statement1.setString(2,newName);
          statement1.execute();
          con.commit();	
	} catch(SQLException sqle)  {
		System.err.println("SQL or database not working " + sqle.getMessage());
          sqle.printStackTrace();
	 }
         catch(Exception e){
	      		System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
	 }
         finally{
          try {
                statement.close();
                statement1.close();            
            } catch(SQLException sqle) {
                      System.err.println("SQL or database not working " + sqle.getMessage());	    
              }
              catch(Exception e){
                  System.err.println("General exception " + e.getMessage());	   
              }	
          } 
        
	}
	public Group getGroup(int groupIdIn)
	{
		Group group= new Group();
		Connection con=null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
	      {

	          con =ConnectionDetails.getConnection();
	          

	          String sql = "SELECT * FROM BIR.TEAM_GROUP WHERE GROUP_ID =?" ;
                    
                  statement = con.prepareStatement(sql); 
                  statement.setInt(1,groupIdIn);
	          rs = statement.executeQuery();
	          while( rs.next()) {      	
                        group.setGroupId( rs.getInt(1));
                        group.setGroupName(rs.getString(2));
                        group.setDepartmentId(rs.getInt(3));
                        group.setManagerId(rs.getInt(4));	            		            		            		
	           }
                   con.commit();	
	    } catch(SQLException sqle){
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }catch(Exception e){
	      System.err.println("General exception " + e.getMessage());
	      e.printStackTrace();
	    }
      
      finally{
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
      
	    return group;
	}
	
	public Group getGroupByName(String groupNameIn)
	{
            Group group= new Group();
            Connection con=null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try {

              con =ConnectionDetails.getConnection();
              String sql = "SELECT * FROM BIR.TEAM_GROUP WHERE GROUP_NAME =?";
              statement = con.prepareStatement(sql) ; 
              statement.setString(1,groupNameIn);
              rs = statement.executeQuery( );
             
              while( rs.next() ){      	
                    group.setGroupId( rs.getInt(1));
                    group.setGroupName(rs.getString(2));
                    group.setDepartmentId(rs.getInt(3));
                    group.setManagerId(rs.getInt(4));	            		            		            		
                }
               con.commit();	
		
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
            }catch(SQLException sqle) {
                      System.err.println("SQL or database not working " + sqle.getMessage());	    
             }
             catch(Exception e){
                  System.err.println("General exception " + e.getMessage());	   
             }	
          }       
	    return group;
	}
	
	static final String getSequenceSQL = "SELECT BIR.SEQ_GROUP_ID.NEXTVAL FROM dual";
	
	private static long getNextSeqVal (Connection conn) throws SQLException 
	{

	    Statement stmt = conn.createStatement();
	    ResultSet rs   = stmt.executeQuery(getSequenceSQL);
	    rs.next();
	    long id = rs.getLong(1);
	    rs.close();
	    stmt.close();
	    return id;

	  }  
	public void storeGroup(String groupNameIn,int siteIdIn)
	{
	      
        Connection con= null;
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        try {
	        
                con =ConnectionDetails.getConnection();
	        String insertCommand = "INSERT into BIR.TEAM_GROUP(" +
                                                                    "GROUP_ID,"+
                                                                    "GROUP_NAME," +
                                                                    "DEPARTMENT_ID" +
                                                                  ") "
                                                                   + " VALUES(?,?,?) ";
                                               
	         statement = con.prepareStatement(insertCommand );
	         long var = getNextSeqVal(con);
	         statement.setLong(1,var);
                 statement.setString(2,groupNameIn);
                 statement.setInt(3,siteIdIn);;
	         //System.out.println("SQL Insert >> " + insertCommand );
	          System.out.println("The New PK IS >> " + var );
	         statement.executeUpdate();	
                 
	         String insertComand2 = "INSERT INTO BIR.TEAM_GROUP_ALIAS(" +
                                                                              "GROUP_ID," +
                                                                              "Group_ALIAS,"+
                                                                              "START_DATE" +
                                                                         ")" +
                                                                         "VALUES(?,?,SYSDATE)";								
			
	         //System.out.println("SQL Insert >> " + insertComand2 );
	         statement1 = con.prepareStatement(insertComand2);
	         statement1.setLong(1,var);
	         statement1.setString(2,groupNameIn);
                 
	         statement1.executeUpdate();
	         con.commit();	

	      } catch(SQLException sqle){    
	      	sqle.printStackTrace();
                sqle.printStackTrace();
	      }
              finally {
                  try{
                        statement.close();
                        statement1.close();            
                    }catch(SQLException sqle){
                              sqle.printStackTrace();	
                              sqle.printStackTrace();
                     }
                     catch(Exception e){
                          e.printStackTrace();
                          e.printStackTrace();
                     }	
               } 

	}
				
	
	public Vector getAllActiveGroups(int depId)
	{
            Vector groupVec = new Vector();
            Connection con=null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try{
                con =ConnectionDetails.getConnection();
	        

	        String sql = " SELECT * FROM BIR.TEAM_GROUP " +
                             " WHERE  DEPARTMENT_ID =?" +
                             " AND ACTIVE_GROUP = 'Y'";                          
	        statement = con.prepareStatement(sql); 
	        statement.setInt(1,depId);
	        //System.out.println("Select>>> "+sql);
	        rs = statement.executeQuery();

                while( rs.next() ){
                  Group group= new Group();	            	
                  group.setGroupId( rs.getInt(1));
                  group.setGroupName(rs.getString(2));
                  group.setDepartmentId(rs.getInt(3));
                  group.setManagerId(rs.getInt(4));
                  groupVec.addElement(group) ;                    
                }
                con.commit();	
		
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
            return groupVec;
	}
        public Vector getAllInActiveGroups() {
              Vector groupVec = new Vector();
              Connection con=null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              try
	      {

                  con =ConnectionDetails.getConnection();	          
	          String sql = "SELECT * FROM BIR.TEAM_GROUP WHERE ACTIVE_GROUP = 'N'";
	          statement = con.prepareStatement(sql);
	          //System.out.println("Select>>> "+sql);
	          rs = statement.executeQuery();

                  while( rs.next()){
                        Group group= new Group();	            	
                        group.setGroupId( rs.getInt(1));
                        group.setGroupName(rs.getString(2));
                        group.setDepartmentId(rs.getInt(3));
                        group.setManagerId(rs.getInt(4));
                        groupVec.addElement(group) ;	            	
                  }
                  con.commit();			
	    }
	    catch(SQLException sqle){
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }catch(Exception e){
	      System.err.println("General exception " + e.getMessage());
	      e.printStackTrace();
	    }
            finally{
                  try {
                        statement.close();
                        rs.close();            
                    } catch(SQLException sqle){
                           System.err.println("SQL or database not working " + sqle.getMessage());	    
                      }                  
                    catch(Exception e){
                        System.err.println("General exception " + e.getMessage());	   
                    }	
            } 
            return groupVec;
	}
  
	public Vector getAllGroupNames()
	{
            Vector groupNameVec = new Vector();
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try{
	          con =ConnectionDetails.getConnection();	          
	          String sql = " SELECT GROUP_ALIAS FROM BIR.TEAM_GROUP_ALIAS" ;
	          statement = con.prepareStatement(sql);
                  rs = statement.executeQuery();
                  while( rs.next() ){    	  
                      String n =rs.getString(1);
                      groupNameVec.addElement(n);	            		            	   	
                  }
                  con.commit();	
		
	    }catch(SQLException sqle){
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }
	    catch(Exception e){
                System.err.println("General exception " + e.getMessage()); 
                e.printStackTrace();
	    }
            finally{
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
             return groupNameVec;
	}
	
	public Vector getAllCurrentGroupNames(int depIdIn)
	{
              Vector groupNameVec = new Vector();
              Connection con=null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              try
	      {

	          con =ConnectionDetails.getConnection();
	          

	          String sql =  " SELECT tg.GROUP_NAME FROM " +
                                " BIR.TEAM_GROUP tg, " +
                                " BIR.Department d," +
                                " BIR.Site s" +
                                " WHERE" +
                                " s.site_id IN (" +
                                                " SELECT s.site_id FROM BIR.SITE WHERE" +
                                                " s.site_id=d.site_id" +
                                                " AND" +
                                                " d.department_id=?" +
                                              ")" +
                                " AND" +
                                " d.site_id=s.site_id" +
                                " AND" +
                                " d.DEPARTMENT_ID= tg.Department_id"+                                
                                " AND" +
                                " ACTIVE_GROUP ='Y'";

	          
	         statement = con.prepareStatement(sql);
                 statement.setInt(1,depIdIn);
                  
                 System.out.print("SELECT>>>>>"+sql);
                 rs = statement.executeQuery(  );
                 while( rs.next()){	            	  
                      String n =rs.getString(1);
                      groupNameVec.addElement(n);
                 }
                 con.commit();		
	    }catch(SQLException sqle){
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }
	    catch(Exception e){
	      System.err.println("General exception " + e.getMessage());
	      e.printStackTrace();
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
              return groupNameVec;
	}
	public void deactivateGroup(int groupIdIn)
        {
             Connection con=null;
             PreparedStatement statement = null;
             ResultSet rs = null;
             try{
	          con =ConnectionDetails.getConnection();	          	          
	          String updateComand = " UPDATE BIR.TEAM_GROUP  " +
                                        " SET ACTIVE_GROUP='N' "+
                                        " WHERE GROUP_ID=?"; 
                  
                  statement = con.prepareStatement(updateComand);
                  statement.setInt(1,groupIdIn);
                  //System.out.println("UPDATE>>>>"+updateComand);
	          rs = statement.executeQuery();
                  con.commit();	
              
             }catch(SQLException sqle){
	  	  System.err.println("SQL or database not working " + sqle.getMessage()); 
                  sqle.printStackTrace();
             } catch(Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
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
	  	
	}
	
  
        public void activateGroup(int groupIdIn){
              Connection con=null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              try{
                  con =ConnectionDetails.getConnection();              	          
                  String updateComand = " UPDATE BIR.TEAM_GROUP " +
                                        " SET ACTIVE_GROUP='Y' "+
                                        " WHERE GROUP_ID=?";                                   
                  statement = con.prepareStatement(updateComand);  
                  statement.setInt(1,groupIdIn);
                  //System.out.println("UPDATE>>>>"+updateComand);
                  rs = statement.executeQuery();
                  con.commit();	
              }catch(SQLException sqle){
                  System.err.println("SQL or database not working " + sqle.getMessage()); 
                  sqle.printStackTrace();
              }      
              catch(Exception e){
                  System.err.println("General exception " + e.getMessage());
                  e.printStackTrace();
              }
              finally{
                     try{
                          statement.close();
                          rs.close();              
                     }catch(SQLException sqle){
                            System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }            
                     catch(Exception e){
                        System.err.println("General exception " + e.getMessage());	   
                     }	
                } 
        }
	
	public void assignGroupManager(int groupIdIn, int managerIdIn){
              Connection con=null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              try{
	          con =ConnectionDetails.getConnection();  
	          String updateComand =" UPDATE BIR.TEAM_GROUP  " +
                                       " SET MANAGER_ID=?" +
                                       " WHERE GROUP_ID=?";	          
                  statement = con.prepareStatement(updateComand);
                  statement.setInt(1,managerIdIn);
                  statement.setInt(2,groupIdIn);
                  //System.out.println("UPDATE>>>>"+updateComand);
	          rs = statement.executeQuery();
                  	
               }catch(SQLException sqle){
                System.err.println("SQL or database not working " + sqle.getMessage()); 
                sqle.printStackTrace();
               }
               catch(Exception e){
                  System.err.println("General exception " + e.getMessage());
                  e.printStackTrace();
               }
               finally{
                   try{
                        con.commit();
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
}
