

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
public class MaintainApplication 
{
	public Vector getAllApplications()
	{
		Vector appList = new Vector();
                Connection con = null; 
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
                {
                        con =ConnectionDetails.getConnection();                        
                        String sql = "SELECT * FROM BIR.APPLICATION" ;
                        statement = con.prepareStatement(sql);
                        rs = statement.executeQuery(  );
                        while( rs.next() )
                        {
                            Application application= new Application();	            	
                            application.setApplicationId( rs.getInt(1));
                            application.setApplicationCode(rs.getString(2));
                            application.setApplicationDesc(rs.getString(3));
                            appList.addElement(application);	            		            	
                            
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
                return appList;
	}
  
////////////////////////////////////////////////////////////////////////////

  public Vector getAllApplicationCods()
  {
		Vector appCodeList = new Vector();
                Connection con = null; 
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
                {

	          con =ConnectionDetails.getConnection();	          
	          String sql = "SELECT APPLICATION_CODE FROM BIR.APPLICATION" ;
                  statement = con.prepareStatement(sql);
	          rs = statement.executeQuery();
                  while( rs.next()){
                      String code = rs.getString(1);
                      code=code.trim();
                      appCodeList.addElement(code);	            		            	          	
                  }
                  con.commit();	
	    }
	    catch(SQLException sqle) {
	      System.err.println("SQL or database not working " + sqle.getMessage());
	      sqle.printStackTrace();
	    }

	    catch(Exception e)
	    {
	      System.err.println("General exception " + e.getMessage());
	      e.printStackTrace();
	    }
            finally{
                     try{
                          statement.close();
                          rs.close();                  
                     }
                     catch(SQLException sqle){
                          System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }                
                     catch(Exception e){
                        System.err.println("General exception " + e.getMessage());	   
                     }	
              } 
              return appCodeList;
  }

///////////////////////////////////////////////////////////////////////////////

public Vector getAllApplicationNames()
	{
              Vector appNameList = new Vector();
              Connection con = null; 
              PreparedStatement statement = null;
              ResultSet rs = null;
              try
	      {
	          con =ConnectionDetails.getConnection();
	          
	          String sql = "SELECT APPLICATION_DESC FROM BIR.APPLICATION" ;
                  statement = con.prepareStatement(sql);
	          rs = statement.executeQuery();
                  while( rs.next()) {	            		            	
	            	String name =  rs.getString(1);
                        name = name.trim();
	            	appNameList.addElement(name);    	
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
               return appNameList;
	}

//////////////////////////////////////////////////////////////////////////////////
	
	public Application getApplication(int appIdIn)
	{
		Application application= new Application();
		Connection con = null;
                PreparedStatement statement= null;
                ResultSet rs = null;
		try
                {
                      con =ConnectionDetails.getConnection();                      
                      String sql = "SELECT * FROM BIR.APPLICATION WHERE APPLICATION_ID =?" ;
                      statement = con.prepareStatement(sql);
                      statement.setInt(1,appIdIn);
                      rs = statement.executeQuery();	         
                      while( rs.next() )
                      {      	
                              application.setApplicationId( rs.getInt(1));
                              application.setApplicationCode(rs.getString(2));
                              application.setApplicationDesc(rs.getString(3));	            		            		            		
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
                return application;
	}
	
	public Application getApplicationByName(String appDescIn)
	{
		Application application= new Application();
		Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try {
	          con =ConnectionDetails.getConnection();	          
	          String sql = "SELECT * FROM BIR.APPLICATION WHERE APPLICATION_DESC =?";
                  statement = con.prepareStatement(sql);
                  statement.setString(1,appDescIn);
	          rs = statement.executeQuery();
     
	          while( rs.next()) {      	
                      application.setApplicationId( rs.getInt(1));
                      application.setApplicationCode(rs.getString(2));
                      application.setApplicationDesc(rs.getString(3));            		            		            		
	          }
	          con.commit();	
		
                }
                catch(SQLException sqle) {
                      System.err.println("SQL or database not working " + sqle.getMessage());
                      sqle.printStackTrace();
                }
    
                catch(Exception e) {
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
                      return application;
                }
	 
	public void storeAplication(String appCodeIn,String appDescIn)
	{
	      
              Connection con = null;
              PreparedStatement statement = null;        
              try
	      {
	      	  con =ConnectionDetails.getConnection();
	                      
	          String insertCommand = " INSERT into BIR.APPLICATION(APPLICATION_CODE,APPLICATION_DESC) "+ 
                                         " VALUES (?,?)";

	          statement = con.prepareStatement(insertCommand);
	          statement.setString(1,appCodeIn);
	          statement.setString(2,appDescIn);
                  //System.out.println("SQL Insert >> " + insertCommand );
	          statement.execute();
                  con.commit();	
	      }


	      catch(SQLException sqle){    
	      	System.err.println("SQL or database not working " + sqle.getMessage());
                sqle.printStackTrace();
	      }
              finally{
                     try{
                          statement.close();
                     }
                     catch(SQLException sqle) {
                          System.err.println("SQL or database not working " + sqle.getMessage());	    
                     }
                
                     catch(Exception e) {
                        System.err.println("General exception " + e.getMessage());	   
                     }	
               } 
	}				
	
}
