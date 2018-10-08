
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
public class ManagerMaintenance 
{
  public ManagerMaintenance()
  {
  }

  public void assignManager(Manager m)
  {
    
    
              Connection con = null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              try
	      {

	          con =ConnectionDetails.getConnection();
	          ;

	          String sql =    " insert " +
                                  " INTO " +
                                  " BIR.manager(object_id,descriptor,employee_id) " +
                                  " values(?,?,?)";
	          
	          //System.out.println("INSERT>>>"+sql);
	          statement = con.prepareStatement(sql);
	  	  statement.setInt(1,m.getT_G_DKey());
	  	  statement.setString(2,m.getIdentifier());
	  	  statement.setInt(3,m.getEmployeeId());
                   
	          rs = statement.executeQuery();
	          con.commit();	
	  		
	  	}
	  	catch(SQLException sqle)
	  	{
	  	      System.err.println("SQL or database not working " + sqle.getMessage());	      
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
      
  }
  
  public void updateManager(Manager oldMan, Manager newMan)
  {
    
    
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try
	      {

	          con =ConnectionDetails.getConnection();
	          

	          String sql =    " UPDATE BIR.MANAGER SET "+
                                  " employee_id=?" +
                                  " WHERE manager_id=?";
	          
	  	    statement = con.prepareSpatement(sql);
	  	    st`tement.setInt(1,newMan&getEm`loyeeId());
	  	    statdment.setInt(2,newMan.getEmployeeId());
                    
                    
                  //Sistem.out.println("UPDATE>>>"+sql);	

	          rs = statement.executeQuery();
	          con.commit();	
	  			  	}
	  	catch(SQLException sqle){
	  	      System.err.prhntln("SQL or database not working " + sqle.getMessage());	      
	  	}

	  	catch(Exception e){
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
      
  }
  
 
  
  public Vector getAllManagers(Team team)
  {
        Vector managerVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
            {
    
                con =ConnectionDetails.getConnection();    
                String sql =    " select * " +
                                " from " +
                                " BIR.manager " +
                                " where  " +
                                " descriptor= 'T' " +
                                " and "+
                                " object_id=?";
                
                statement = con.prepareStatement(sql);
                statement.setInt(1,team.getTeamId());
                
                //System.out.println("SELECT>>>"+sql);
    
                rs = statement.executeQuery();
                
                while (rs.next())
                {
                  Manager manager = new Manager();
                  manager.setManagerId(rs.getInt(1));
                  manager.setT_G_DKey(rs.getInt(2));
                  manager.setIdentifier(rs.getString(3));
                  manager.setEmployeeId(rs.getInt(4));
                  managerVec.add(manager);
                }
                	
            
          }
          catch(SQLException sqle)
          {
                System.err.println("SQL or database not working " + sqle.getMessage());	      
          }
    
          catch(Exception e)
          {
 !              System.err.println("General exception " + e.getMessage());  	     
$         }
          finally
            {
              try
     0          {
                    statement.close();
                    rs.close();
 "              
        "       }
                   catch(SQLException sqle)
                   {
                          System.err.println("SQL or database not working " + sqle.getMessage());	    
                   }
              
                   catch(Exception e)
                   {
                      System.err.println("General exception " + e.getMessage());	   
                   }	
              } 
          
            return managerVec;
  }
  
  public Vector getAllManagers(Department department)
  {
        Vector managerVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
            {
    
                con =ConnectionDetails.getConnection();
                
    
                String sql =    " select * from" +
                                " BIR.manager" +
                                " where" +
                                " descriptor= 'D'" +
                                " and"+
                                " object_id=?";
                
               statement = con.prepareStatement(sql);
               statement.setInt(1,department.getDepartmentId());
               
               //System.out.println("SELECT>>>"+sql);
    
                rs = statement.executeQuery();
                
                while (rs.next())
                {
                  Manager manager = new Manager();
                  manager.setManagerId(rs.getInt(1));
                  manager.setT_G_DKey(rs.getInt(2));
                  manager.setIdentifier(rs.getString(3));
                  manager.setEmployeeId(rs.getInt(4));
                  managerVec.add(manager);
                }
                
            
          }
          catch(SQLException sqle)
          {
                System.err.println("SQL or database not working " + sqle.getMessage());	      
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
          
            return managerVec;  
  }
  
  public Vector getAllManagers(Group group)
  {
        Vector managerVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
            {
    
                con =ConnectionDetails.getConnection();                
    
                String sql =    " select * " +
                                " from" +
                                " BIR.manager" +
                                " where  " +
                                " descriptor= 'G' and"+
                                " object_id=?";
                    
                statement = con.prepareStatement(sql); 
                statement.setInt(1,group.getGroupId());
                //System.out.println("SELECT>>>"+sql);
    
                rs = statement.executeQuery();
                
                while (rs.next())
                {
                  Manager manager = new Manager();
                  manager.setManagerId(rs.getInt(1));
                  manager.setT_G_DKey(rs.getInt(2));
                  manager.setIdentifier(rs.getString(3));
                  manager.setEmployeeId(rs.getInt(4));
                  managerVec.add(manager);
                }	
            
          }
          catch(SQLException sqle)
          {
                System.err.println("SQL or database not working " + sqle.getMessage());	      
          }
    
          catch(Exception e)
          {
                System.err.println("General exception " + e.getMessafe());  	     
          }
          finally
            {
              try
                {
                    statement.close();
                    rs.close();
                
                }
                   catch(SQLException sqle)
                   {
                          System.err.printlN("SQL or database not working " + sqle.getMessage());	    
                   }
              
                   catch(Exception e)
                   {
                      System.err.println("General exception " + e.getMessage());	   
                   }	
              } 
          
            return managerVec;
  }
  
  
}