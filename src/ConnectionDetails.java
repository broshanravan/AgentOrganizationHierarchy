
import java.sql.*;
import java.sql.SQLException;
public class ConnectionDetails 
{
 
         public static Connection con = null;     
         //public static String dbconnection = "jdbc:oracle:thin:@rias-bir-dba01-vip:1521:BIRLO1";
         public static String dbconnection = "jdbc:oracle:thin:@mo-bir-db03-vip:1521:BIRTO1";
         public static String agentUsername = "app_agent";
         public static String agentPassword = "birtest";
         
  
  
  public ConnectionDetails()
  {
      
  }
  public static boolean isClosed()
  {
     boolean cl=false;
                try
                {
                  if(con.isClosed())
                  {
                    cl=true;
                  }
                
                }
                catch(SQLException sqle)
                {
                  System.err.println("SQL or database not working " + sqle.getMessage());
                  sqle.printStackTrace();
                }
                
                return cl;
  }
  
  public static Connection getConnection()
  {
          if(con==null||isClosed())
          {
            try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con = DriverManager.getConnection(dbconnection, agentUsername, agentPassword);               
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
          return con;
  }
  
  public static void closeConnection()
  {
    try
	    {
          con.close();
          
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