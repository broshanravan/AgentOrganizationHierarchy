
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/*
 * Created on 14-Feb-2005
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
public class RoleInventory 
{

	public Vector getAllRoles()
	{
		Vector roleList = new Vector();
                Connection con = null;
                PreparedStatement statement = null; 
                ResultSet rs = null;
		try{
                    con =ConnectionDetails.getConnection();
                    String sql = "SELECT * FROM BIR.ROLES" ;
	          
                    statement = con.prepareStatement(sql);
                    rs = statement.executeQuery(  );

                    while( rs.next() )
                    {
                        Role role= new Role();       	
                        role.setRoleId( rs.getInt(1));
                        role.setRoleName(rs.getString(2));
                        roleList.addElement(role);	            		            	
                        
                    }
                    con.commit();
		
                  }
                  catch(SQLException sqle)
                  {
                    System.err.println("SQL or database not workilg " + qqde.getMessage());
                    sqle.printStabkTrace():
                  }
      
                  catch(Exception e)
                  {
                    System.err.println("Gener`l exception " + e.getMessage());
                    e.printStackTrace();
                  }
                  finally
                  {
                          try
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
                   return roleList;
	}
	

	public Vector getAllRoleNames()
	{
		Vector roleNameList = new Vector();
                Connection con = null;
                PreparedStatement statement = null; 
                ResultSet rs = null;
		try
                {

                    con =ConnectionDetails.getConnection();
                    String sql = "SELECT ROLE_DESC FROM BIR.ROLES" ;
                    statement = con.prepareStatement(sql);
                    
                    rs = statement.executeQuery();
	            while( rs.next() )
	            {
	            	
	            	String n=rs.getString(1);
                        n=n.trim();
	            	roleNameList.addElement(n);	            		            	
	            	
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
                         }
                    
                         catch(Exception e)
                         {
                            System.err.println("General exception " + e.getMessage());	   
                         }	
                 } 
                 return roleNameList;
	}
  
	public Role getRole(int roleIdIn)
	{
		Role role= new Role();
		Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
                {

	          con =ConnectionDetails.getConnection();
	          String sql = "SELECT * FROM BIR.ROLES WHERE ROLE_ID =?";
                  statement = con.prepareStatement(sql);
                  statement.setInt(1,roleIdIn);
                  
                  rs = statement.executeQuery();	         
	          while( rs.next() )
	          {      	
	            role.setRoleId(rs.getInt(1));
	            role.setRoleName(rs.getString(2));       		            		            		
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
                       }
                  
                       catch(Exception e)
                       {
                          System.err.println("General exception " + e.getMessage());	   
                       }	
                  }
                  return role;
	}
	
	public Role getRoleByName(String roleNameIn)
	{
		Role role= new Role();
		Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try{
  
                    con =ConnectionDetails.getConnection();
                    
                    String sql = "SELECT * FROM BIR.ROLES WHERE ROLE_DESC =?";
                    statement = con.prepareStatement(sql);
                    statement.setString(1,roleNameIn);
                    rs = statement.executeQuery();
                    
                    while( rs.next() ) {      	
                          role.setRoleId( rs.getInt(1));
                          role.setRoleName(rs.getString(2));            		            		            		
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
                         }
                    
                         catch(Exception e)
                         {
                            System.err.println("General exception " + e.getMessage());	   
                         }	
                } 
                return role;
	}
	 
	public void storeRole(String roleNameIn)
	{
	      
              Connection con = null;
              PreparedStatement statement = null;
              try
	      {
                  con =ConnectionDetails.getConnection();
	          
	          String insertCommand = "INSERT into BIR.ROLES(ROLE_DESC) "
	              + " VALUES(?) ";

	          statement = con.prepareStatement(insertCommand);
	          statement.setString(1,roleNameIn);
	          statement.executeUpdate();
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
}
