
import java.util.*;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

public class BusinessFunctionInventory 
{
  
  
  public BusinessFunctionInventory()
  {
  }
  
  public Vector getAllBusinessFunctionNames()
  {
        Vector bFNamesVec = new Vector();
        Connection con = null;
        PreparedStatement statement= null;
        ResultSet rs = null;
	try{
            con =ConnectionDetails.getConnection();  
            String sql = "SELECT BUSINESS_FUNCTION FROM BIR.BUSINESS_FUNCTION";        
            //System.out.println("SELECT>>>> "+ sql);          
            statement = con.prepareStatement(sql);             
            rs = statement.executeQuery();         
            while(rs.next()) {
              String bfName=rs.getString(1);
              bFNamesVec.addElement(bfName);
            }            	
	 }
         catch(SQLException sqle){    
              System.err.println("SQL or database not working " + sqle.getMessage());
              sqle.printStackTrace();
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
         return bFNamesVec;
  }
  
  public Vector getAllBusinessFunctions() {
        Vector bFVec = new Vector();
        Connection con = null;
        PreparedStatement statement= null;
        ResultSet rs = null;
	try{
            con =ConnectionDetails.getConnection();  
            String sql = "SELECT * FROM BIR.BUSINESS_FUNCTION";        
            //System.out.println("SELECT>>>> "+ sql);          
            statement = con.prepareStatement(sql);             
            rs = statement.executeQuery();         
            while(rs.next()){
              BusinessFunction bf = new BusinessFunction();
              bf.setBusFunId(rs.getInt(1));
              bf.setBusFunName(rs.getString(2));
              bFVec.addElement(bf);
            }
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
               catch(Exception e){
                  System.err.println("General exception " + e.getMessage());	   
               }	
          }    
          return bFVec;
  }
  
  public BusinessFunction getBusinessFunctionById(int bFId){
    BusinessFunction bf = new BusinessFunction();
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
            con =ConnectionDetails.getConnection();
            String sql = "SELECT * FROM BIR.BUSINESS_FUNCTION WHERE business_function_id =? " ;         
            //System.out.println("SELECT>>>> "+ sql);          
            statement = con.prepareStatement(sql); 
            statement.setInt(1,bFId);
            rs = statement.executeQuery();         
            while(rs.next()){
              bf.setBusFunId(rs.getInt(1));
              bf.setBusFunName(rs.getString(2));   
            }
            con.commit();
            	
     }catch(SQLException sqle) {    
	      	System.err.println("SQL or database not working " + sqle.getMessage());
          sqle.printStackTrace();
     }	
     finally {
          try {                
                statement.close();
                rs.close();            
            }catch(SQLException sqle){
                      System.err.println("SQL or database not working " + sqle.getMessage());	    
             }
             catch(Exception e) {
                  System.err.println("General exception " + e.getMessage());	   
             }	
          }     
    return bf;
  }
  
  public BusinessFunction getBusinessFunctionByName(String bFName) {
    BusinessFunction bf = new BusinessFunction();
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
            con =ConnectionDetails.getConnection();
            String sql = "SELECT * FROM BIR.BUSINESS_FUNCTION WHERE business_function =?";            
            //System.out.println("SELECT>>>> "+ sql);            
            statement = con.prepareStatement(sql);
            statement.setString(1,bFName);
            rs = statement.executeQuery();            
            while(rs.next())
            {
              bf.setBusFunId(rs.getInt(1));
              bf.setBusFunName(rs.getString(2));   
            }
            con.commit();	
      } catch(SQLException sqle){    
	  System.err.println("SQL or database not working " + sqle.getMessage());
          sqle.printStackTrace();
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
    return bf;
  }
  
  public void storeBusiness(BusinessFunction bF)
  {
      Connection con = null;
      PreparedStatement statement = null;
      ResultSet rs = null;
      String bfName = bF.getBusFunName();
      try {
            con =ConnectionDetails.getConnection();
            String sql = " Insert INTO" +
                         " BIR.BUSINESS_FUNCTION(business_function) " +
                         " VALUES(?)";            
            //System.out.println("Inster>>>> "+ sql);      
            statement = con.prepareStatement(sql); 
            statement.setString(1,bfName);
            rs = statement.executeQuery();
            con.commit();	
       }catch(SQLException sqle){    
            System.err.println("SQL or database not working " + sqle.getMessage());
            sqle.printStackTrace();
       }	
       finally{
           try {
              statement.close();
              rs.close();              
           } catch(SQLException sqle){
              System.err.println("SQL or database not working " + sqle.getMessage());	    
           }          
           catch(Exception e) {
              System.err.println("General exception " + e.getMessage());	   
           }	
      } 
    
  }
  
}