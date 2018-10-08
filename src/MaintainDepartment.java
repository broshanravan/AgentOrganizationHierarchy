
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/*
 * Created on 16-Mar-2005
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
public class MaintainDepartment 
{
	public void renameDepartment(int departmentIdIn ,String newName)
	{
              Connection con = null;
              PreparedStatement statement = null;
              try
	      {

	          con =ConnectionDetails.getConnection();
	          
	          String upDateComand= " UPDATE BIR.DEPARTMENT SET DEPARTMENT_NAME=?" +
                                       " WHERE DEPARTMENT_ID=?";
	          
	          statement = con.prepareStatement(upDateComand);
	          statement.setString(1,newName);
	          statement.setInt(2,departmentIdIn);
                  //System.out.println("SQL Update >> " + upDateComand );
	          statement.execute();
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
	public Department getDepartment(int departmentIdIn)
	{
		Department department= new Department();
		Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
                {

	          con =ConnectionDetails.getConnection();
	          
	          String sql = "SELECT * FROM BIR.DEPARTMENT WHERE DEPARTMENT_ID =?" ;
	          statement = con.prepareStatement(sql);
	          statement.setInt(1,departmentIdIn);
                  rs = statement.executeQuery();	         
	          while( rs.next()) {      	
                      department.setDepartmentId( rs.getInt(1));
                      department.setDepartmentName(rs.getString(2));
                      department.setDepartmentSiteId(rs.getInt(3));
                      department.setManagerId(rs.getInt(4));     		            		            		
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
              return department;
	}
	
	public int getSiteId(int departmentIdIn)
	{
		int siteId=0;
		Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try{
                      con =ConnectionDetails.getConnection();                      
                      String sql = "SELECT SITE_ID FROM BIR.DEPARTMENT WHERE DEPARTMENT_ID =?";
                      statement = con.prepareStatement(sql);
                      statement.setInt(1,departmentIdIn);
                      rs = statement.executeQuery();	         
                      while(rs.next()){ 		
                            siteId=rs.getInt(1);	            		            		            		
                      }   
                }
                catch(SQLException sqle){
                  System.err.println("SQL or database not working " + sqle.getMessage());                  
                }    
                catch(Exception e){
                  System.err.println("General exception " + e.getMessage());                 
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
                return siteId;
	}
	
	public Department getDepartmentByName(String departmentNameIn)
	{
		Department department= new Department();
		Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try{
                    con =ConnectionDetails.getConnection();                                   
                    String sql = "SELECT * FROM BIR.DEPARTMENT WHERE DEPARTMENT_NAME =?";
                    statement = con.prepareStatement(sql);
                    statement.setString(1,departmentNameIn);                    
                    rs = statement.executeQuery();       
                    while( rs.next() ){      	
                        department.setDepartmentId( rs.getInt(1));
                        department.setDepartmentName(rs.getString(2));
                        department.setDepartmentSiteId(rs.getInt(3));
                        department.setManagerId(rs.getInt(4));          		            		            		
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
                 return department;
	}
        
    public Department getSiteDepartmentByName(int siteId, String departmentNameIn)
    {
            Department department= new Department();
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try
            {

                    con =ConnectionDetails.getConnection();
                                
                    String sql = " SELECT * FROM BIR.DEPARTMENT " +
                                 " WHERE DEPARTMENT_NAME =?" +
                                 " AND" +
                                 " SITE_ID=?";
                    
                    statement = con.prepareStatement(sql);
                    statement.setString(1,departmentNameIn);
                    statement.setInt(2,siteId);                    
                    
                    rs = statement.executeQuery();       
                    while( rs.next() )
                    {       
                            department.setDepartmentId( rs.getInt(1));
                            department.setDepartmentName(rs.getString(2));
                            department.setDepartmentSiteId(rs.getInt(3));
                            department.setManagerId(rs.getInt(4));                                                                          
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
             return department;
    }
	
	
	static final String getSequenceSQL = "SELECT SEQ_GROUP_ID.NEXTVAL FROM dual";
	
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
	public void storeDepartment(String deparmentNameIn,int siteIdIn)
	{
	      
        Connection con = null;
        PreparedStatement statement = null;
        try
	      {
	        
	      	con =ConnectionDetails.getConnection();
	        

	          String insertCommand = "INSERT into BIR.DEPARTMENT (" +
                                                                          "DEPARTMENT_NAME," +
                                                                          "SITE_ID" +
                                                                   " ) "
                                                         + " VALUES(?,?) ";
	          
	          statement = con.prepareStatement(insertCommand);
	          statement.setString(1,deparmentNameIn);
	          statement.setInt(2,siteIdIn);
                  //System.out.println("Insert>>>"+ insertCommand);
	          statement.executeQuery();
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
				
	
	public Vector getAllInActiveDepartments()
	{
              Vector departmentVec = new Vector();
              Connection con = null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              try
	      {

	          con =ConnectionDetails.getConnection();	          
	          String sql = "SELECT * FROM BIR.DEPARTMENT WHERE ACTIVE_DEPARTMENT='N'";
                  statement = con.prepareStatement(sql);
	          //System.out.println("Select>>> "+sql);
	          rs = statement.executeQuery();
	          while( rs.next() )
	          {
                            Department department= new Department();  
                            department.setDepartmentId( rs.getInt(1));
                            department.setDepartmentName(rs.getString(2));
                            department.setDepartmentSiteId(rs.getInt(3));
                            department.setManagerId(rs.getInt(4));
                            departmentVec.addElement(department);
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
		return departmentVec;
	}
  
  
  
  public Vector getAllActiveDepartments(int siteId)
  {
		Vector departmentVec = new Vector();
                Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
                {

                      con =ConnectionDetails.getConnection();
                      
                      String sql = " SELECT * FROM BIR.DEPARTMENT " +
                                   " WHERE SITE_ID = ?" +
                                   " AND " +
                                   " ACTIVE_DEPARTMENT='Y'";
                      //System.out.println("Select>>> "+sql);
                      statement = con.prepareStatement(sql);
                      statement.setInt(1,siteId);
                      rs = statement.executeQuery();
                      while(rs.next()){
                              Department department= new Department();  
                              department.setDepartmentId( rs.getInt(1));
                              department.setDepartmentName(rs.getString(2));
                              department.setDepartmentSiteId(rs.getInt(3));
                              department.setManagerId(rs.getInt(4));
                              departmentVec.addElement(department);
                      }
                     con.commit();    
		
                }
                catch(SQLException sqle){
                  System.err.println("SQL or database not working " + sqle.getMessage());                  
                }
    
                catch(Exception e){
                  System.err.println("General exception " + e.getMessage());                 
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
		return departmentVec;
	}
	public Vector getAllDepartmentNames()
	{
		Vector departmentNameVec = new Vector();
                Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
                {
                      con =ConnectionDetails.getConnection();
                      
                      String sql = "SELECT BIR.DEPARTMENT_NAME FROM DEPARTMENT" ;
                      statement = con.prepareStatement(sql);
                      rs = statement.executeQuery();    
                      while( rs.next()){	            	  
                          String n =rs.getString(1);
                          departmentNameVec.addElement(n);             
                      }
                      con.commit();   
                }
                catch(SQLException sqle){
                  System.err.println("SQL or database not working " + sqle.getMessage());
                  
                }    
                catch(Exception e){
                  System.err.println("General exception " + e.getMessage());
                }
                finally{
                         try{
                              statement.close();
                              rs.close();                      
                         }
                         catch(SQLException sqle){
                              System.err.println("SQL or database not working " + sqle.getMessage());	    
                         }
                    
                         catch(Exception e) {
                            System.err.println("General exception " + e.getMessage());	   
                         }	
                }	
		return departmentNameVec;
	}
	public Vector getAllCurrentDepartmentNames(int siteId)
	{
		Vector departmentNameVec = new Vector();
                Connection con = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
		try
                {
                      con =ConnectionDetails.getConnection();
                      
                      String sql =  " SELECT DEPARTMENT_NAME FROM BIR.DEPARTMENT " +
                                    " WHERE site_ID =? " +
                                    " AND ACTIVE_DEPARTMENT='Y' " ;                      
                      
                      statement = con.prepareStatement(sql);
                      statement.setInt(1,siteId);
                      //System.out.println("SELECT>>>"+sql);
                      rs = statement.executeQuery( );    
                      while( rs.next() )
                      {	            	  
                          String n =rs.getString(1);
                          departmentNameVec.addElement(n);	            		            	            	
                      }
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
               return departmentNameVec;
	}
	
	public void asignDepartmentManager(int departmentId, int empNo)
	{
		
              Connection con = null;
              PreparedStatement statement = null;
              ResultSet rs = null;
              try
	      {

	          con =ConnectionDetails.getConnection();
	          

	          String sql =  " UPDATE BIR.DEPARTMENT " +
                                " SET MANAGER_ID=? " +
                                " WHERE DEPARTMENT_ID =? ";	          
	  	  statement = con.prepareStatement(sql);
	  	  statement.setInt(1,empNo);
	  	  statement.setInt(2,departmentId);
                  //System.out.println("UPDATE>>>"+sql);

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
    
    public void activateDepartment(int depIdIn)
    {
      Connection con=null;
      PreparedStatement statement = null;
      ResultSet rs = null;
      try
      {
              con =ConnectionDetails.getConnection();
                           
              String updateComand =" UPDATE BIR.DEPARTMENT " +
                                   " SET ACTIVE_DEPARTMENT='Y' "+
                                   " WHERE DEPARTMENT_ID=?";
              statement = con.prepareStatement(updateComand);
              statement.setInt(1,depIdIn);
              //System.out.println("UPDATE>>>>"+updateComand);
              rs = statement.executeQuery();
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
    }
    
    public void deActivateDepartment(int depIdIn)
    {
          Connection con=null;
          PreparedStatement statement = null;
          ResultSet rs = null;
          try
          {
              con =ConnectionDetails.getConnection();
                           
              String updateComand =     " UPDATE BIR.DEPARTMENT" +
                                        " SET" +
                                        " ACTIVE_DEPARTMENT='N' "+
                                        " WHERE" +
                                        " DEPARTMENT_ID=?";
              
             statement = con.prepareStatement(updateComand);
             statement.setInt(1,depIdIn);
             //System.out.println("UPDATE>>>>"+updateComand);
             rs = statement.executeQuery( );
             con.commit();	
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
}
