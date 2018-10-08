
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
public class ApplicationRoleMaintenance 
{
	public Vector getAllApplicationRoles()
	{
		Vector appRoleVec = new Vector();
		
		return appRoleVec;
	}
	
	public Vector getAllApplicationRoles(String appNameIn)
	{
           Vector appRoleList = new Vector();
           Connection con = null;
           PreparedStatement statement= null;
           ResultSet rs = null;
           try
          { 
              MaintainApplication maintainApp = new MaintainApplication();
              Application app = maintainApp.getApplicationByName(appNameIn);
              int id =app.getApplicationId();
		          
              con =ConnectionDetails.getConnection(); 
              //System.out.println("Application Id is " +id);

              String sql = "SELECT * FROM BIR.APPLICATION_ROLE WHERE APPLICATION_ID =?" ;
              statement = con.prepareStatement(sql);
              statement.setInt(1,id);            
              rs = statement.executeQuery();

              while( rs.next() ){
                    AplicationRole appRole= new AplicationRole();
                    
                    appRole.setApplicationRoleId( rs.getInt(1));
                    appRole.setApplicationId(rs.getInt(2));
                    appRole.setRoleId(rs.getInt(3));
                    appRoleList.addElement(appRole);	            		            	
                    
              }
              con.commit();			
	 }catch(SQLException sqle){
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
            }catch(SQLException sqle)
               {
                      System.err.println("SQL or database not working " + sqle.getMessage());	    
               }catch(Exception e){
                  System.err.println("General exception " + e.getMessage());	   
               }	
          } 
			
	   return appRoleList;
	}
	
	public AplicationRole getApplicationRole(int appRoleIdIn)	{
            AplicationRole aplicationRole = new AplicationRole();
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
	          con =ConnectionDetails.getConnection();
	          String sql = "SELECT * FROM BIR.APPLICATION_ROLE WHERE APPLICATION_ROLE_ID =?" ;
	          statement = con.prepareStatement(sql);
	          statement.setInt(1,appRoleIdIn);
	          rs = statement.executeQuery();    
	          while( rs.next()) {      	
                        aplicationRole.setApplicationRoleId(rs.getInt(1));
                        aplicationRole.setApplicationId( rs.getInt(2));
                        aplicationRole.setRoleId(rs.getInt(3));          		            		            		
	          }
	    }
	    catch(SQLException sqle){
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
                  }
                  catch(SQLException sqle) {
                              System.err.println("SQL or database not working " + sqle.getMessage());	    
                  }
                  catch(Exception e){
                      System.err.println("General exception " + e.getMessage());	   
                  }	
            } 
            return aplicationRole;
        }
	
	public AplicationRole getApplicationRoleById(int appRoleIdIn)
	{
            AplicationRole aplicationRole = new AplicationRole();
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try{
	          con =ConnectionDetails.getConnection();  
	          String sql = "SELECT * FROM BIR.APPLICATION_ROLE WHERE APPLICATION_ROLE_ID =?";
	          statement = con.prepareStatement(sql);
	          statement.setInt(1,appRoleIdIn);
                  rs = statement.executeQuery(); 
	          while( rs.next() ) {      	
                        aplicationRole.setApplicationRoleId(rs.getInt(1));
                        aplicationRole.setApplicationId( rs.getInt(2));
                        aplicationRole.setRoleId(rs.getInt(3));	            		            		            		
	            }
	    }catch(SQLException sqle) {
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
                }
               catch(SQLException sqle){
                      System.err.println("SQL or database not working " + sqle.getMessage());	    
               }catch(Exception e){
                  System.err.println("General exception " + e.getMessage());	   
               }	
          }       		
           return aplicationRole;
	}
	
	public AplicationRole getApplicationRole(String appNameIn , String roleNameIn)
	{
		AplicationRole aplicationRole = new AplicationRole();
		
		MaintainApplication maintainApplication = new MaintainApplication();
		Application app1 = maintainApplication.getApplicationByName(appNameIn);
		RoleInventory roleInventory = new RoleInventory();
		Role role1 = roleInventory.getRoleByName(roleNameIn);
		Connection con=null;
                PreparedStatement statement = null;
                ResultSet rs = null;
                try {
                      con =ConnectionDetails.getConnection();	          
                      String sql =    " SELECT * " +
                                      " FROM BIR.APPLICATION_ROLE" +
                                      " WHERE" +
                                      " ROLE_ID =?" + 
                                      " AND" +
                                      " APPLICATION_ID =?";	          
                      statement = con.prepareStatement(sql);
                      statement.setInt(1,role1.getRoleId());
                      statement.setInt(2,app1.getApplicationId());
                      rs = statement.executeQuery();
                      while( rs.next() ) {      	
                        aplicationRole.setApplicationRoleId(rs.getInt(1));
                        aplicationRole.setApplicationId( rs.getInt(2));
                        aplicationRole.setRoleId(rs.getInt(3));	            		            		            		
                      }
                }catch(SQLException sqle){
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
                    } catch(SQLException sqle) {
                              System.err.println("SQL or database not working " + sqle.getMessage());	    
                       }                  
                       catch(Exception e){
                          System.err.println("General exception " + e.getMessage());	   
                       }	
                  }
      
		return aplicationRole;
	}
	public void StoreApplicationRole(String appNameIn,String roleNameIn){
		MaintainApplication maintainApplication = new MaintainApplication();
		Application app1 = maintainApplication.getApplicationByName(appNameIn);		
		int appId = app1.getApplicationId();
		RoleInventory roleInventory = new RoleInventory();		
		Role role1 = roleInventory.getRoleByName(roleNameIn);	
		int roleId = role1.getRoleId();		
		Connection con = null;
                PreparedStatement statement = null;
    
		try{
                  con =ConnectionDetails.getConnection();	          

	          String insertCommand = " INSERT into " +
                                         " BIR.APPLICATION_ROLE(APPLICATION_ID,ROLE_ID) " +
                                         " VALUES(?,?)";
	          statement = con.prepareStatement(insertCommand);
	          statement.setInt(1,appId);
	          statement.setInt(2,roleId);
	          //System.out.println("SQL Insert >> " + insertCommand );
	          statement.execute();
                  con.commit();	
	      }catch(SQLException sqle) {    
	      	System.err.println("SQL or database not working " + sqle.getMessage());
                sqle.printStackTrace();
	      }
            finally{
              try{
                    statement.close();
                }catch(SQLException sqle){
                          System.err.println("SQL or database not working " + sqle.getMessage());	    
                  }catch(Exception e){
                      System.err.println("General exception " + e.getMessage());	   
                   }	
            } 
		
	}

	public String getRoleName(int appRoleIdIn){
            String roleName;
            AplicationRole appRole = this.getApplicationRole(appRoleIdIn);
            int roleId = appRole.getRoleId();		
            RoleInventory roleInventory = new RoleInventory();		
            Role role = roleInventory.getRole(roleId);
            roleName = role.getRoleName();		
            return roleName;
	}

}
