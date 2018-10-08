import java.util.*;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

import java.sql.PreparedStatement;


public class ClassOfBusinessInventory 
{
  public ClassOfBusinessInventory()
  {
  }
  
  public Vector getAllCobs(){
        Vector cobVec = new Vector();
        Connection con = null;
        PreparedStatement statement= null;
        ResultSet rs = null;
        try{
              con =ConnectionDetails.getConnection();
              String sql = "SELECT * FROM BIR.CLASS_OF_BUSINESS" ;          
              statement = con.prepareStatement(sql);
              rs = statement.executeQuery();
              while( rs.next()){
                ClassOfBusiness cob = new ClassOfBusiness();                
                cob.setCobId( rs.getString(1));
                cob.setCobName(rs.getString(2));
                //System.out.println(cob.getCobName());	            		            	
                cobVec.add(cob);
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
            }catch(SQLException sqle){
                      System.err.println("SQL or database not working " + sqle.getMessage());	    
            }
            catch(Exception e) {
                      System.err.println("General exception " + e.getMessage());	   
            }	
        }     
        return cobVec;
  }
  
  public Vector getAllCobNames()
  {
        Vector cobNamesVec = new Vector();
        Connection con = null;
        PreparedStatement statement= null;
        ResultSet rs = null;
        try {
         con =ConnectionDetails.getConnection();              
         String sql = "SELECT CLASS_OF_BUSINESS FROM BIR.CLASS_OF_BUSINESS" ;              
         statement = con.prepareStatement(sql);
         rs = statement.executeQuery();

          while( rs.next()) {
            String cobName =rs.getString(1) ;          		            	
            cobNamesVec.add(cobName);
          }                
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
          return cobNamesVec;
  }
  
  public Vector getAllCobCodes(){
    Vector cobCodesVec = new Vector();
    Connection con = null;
    PreparedStatement statement= null;
    ResultSet rs = null;
    try
      {
          con =ConnectionDetails.getConnection();          
          String sql = "SELECT CLASS_OF_BUSINESS_ID FROM BIR.CLASS_OF_BUSINESS" ;
          statement = con.prepareStatement(sql);
          rs = statement.executeQuery();
          while( rs.next() ) {
            String cobCode =rs.getString(1) ;          		            	
            cobCodesVec.add(cobCode);
          }
            
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
            } catch(SQLException sqle)  {
                   System.err.println("SQL or database not working " + sqle.getMessage());	    
            }
            catch(Exception e) {
                 System.err.println("General exception " + e.getMessage());	   
            }	
          } 
    
    return cobCodesVec;
  }
  
  public ClassOfBusiness getCobByName(String cobName){
        ClassOfBusiness cob1 = new ClassOfBusiness();
        Connection con = null;
        PreparedStatement statement =null;
        ResultSet rs = null;
        try {
              con =ConnectionDetails.getConnection();             
              String sql = "SELECT * FROM BIR.CLASS_OF_BUSINESS WHERE CLASS_OF_BUSINESS =?" ;
              statement = con.prepareStatement(sql);
              statement.setString(1,cobName);
              rs = statement.executeQuery();    
              while( rs.next() ){
                ClassOfBusiness cob = new ClassOfBusiness();            	
                cob1.setCobId( rs.getString(1));
                cob1.setCobName(rs.getString(2));  		            		            	           	
              }                
        }catch(SQLException sqle){
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
          }catch(SQLException sqle){
               System.err.println("SQL or database not working " + sqle.getMessage());	    
           }
           catch(Exception e){
              System.err.println("General exception " + e.getMessage());	   
           }	
       }     
        return cob1;
  }
  
  public ClassOfBusiness getCobById(String cobId){
        ClassOfBusiness cob2 = new ClassOfBusiness();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs= null;
        try {
          con =ConnectionDetails.getConnection(); 
          String sql = "SELECT * FROM BIR.CLASS_OF_BUSINESS WHERE CLASS_OF_BUSINESS_ID =?" ;
          statement = con.prepareStatement(sql);
          statement.setString(1,cobId);
          rs = statement.executeQuery(  );    
          while( rs.next() ){
            ClassOfBusiness cob = new ClassOfBusiness();            	
            cob2.setCobId( rs.getString(1));
            cob2.setCobName(rs.getString(2));
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
           }catch(SQLException sqle){
                  System.err.println("SQL or database not working " + sqle.getMessage());	    
           }
           catch(Exception e) {
              System.err.println("General exception " + e.getMessage());	   
           }	
        }     
        return cob2;
  }
  
  public void StoreClassOfBusiness(ClassOfBusiness cobIn)
  {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs= null;
        try{
           con =ConnectionDetails.getConnection();               
               String sql = "Insert Into BIR.CLASS_OF_BUSINESS"
                            + " ("
                              + " CLASS_OF_BUSINESS_ID,"
                              + " CLASS_OF_BUSINESS,"
                              + " WRITE_OFF_PERC"
                            + ") " 
                            + " VALUES(?,?,? )";          
            statement = con.prepareStatement(sql);   
            statement.setString(1,cobIn.getCobId() );
            statement.setString(2,cobIn.getCobName());
            statement.setFloat(3,cobIn.getWriteOff() );
            //System.out.println("Inster>>>> "+ sql); 
            statement.executeQuery();
            con.commit();
        }catch(SQLException sqle)  {
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
           }catch(SQLException sqle){
               System.err.println("SQL or database not working " + sqle.getMessage());	    
           }          
           catch(Exception e) {
              System.err.println("General exception " + e.getMessage());	   
           }	
        }      
  }
}