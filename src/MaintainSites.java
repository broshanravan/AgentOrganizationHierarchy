
import java.applet.Applet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/*
 * Created on 15-Feb-2005
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
public class MaintainSites 
{
	
	Connection conn;
	Connection connec;
	
	public MaintainSites()
	{
		
	}
	
	public MaintainSites(Connection connIn)
	{
		conn= connIn;
	}
	
	public Site getSite(int siteIdIn)
	{
             Site site= new Site();
             Connection con = null;
             Statement statement = null;
             ResultSet rs = null;
             try
	     {

	          con =ConnectionDetails.getConnection();
	          statement = con.createStatement();
	          String sql = "SELECT * FROM BIR.SITE WHERE SITE_ID ="+ siteIdIn;
                  //System.out.println("SELECT>>> " + sql);
	          rs = statement.executeQuery(sql);
	          while(rs.next())
	          {      	
                      site.setSiteId( rs.getInt(1));
	              site.setSiteName(rs.getString(2));
	              site.setManagerId(rs.getInt(3));
	              //System.out.println("Site Id from DB Is: " + site.getSiteId());
                      //System.out.println("Site name from DB Is: " + site.getSiteName());
                      //System.out.println("Manager Id from DB Is: " + site.getManagerId());
	            		            		            		
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
	    return site;
	}
	public void asignSiteManager(int siteIdIn,int managerIdIn)
	{
              Connection con = null;
              Statement statement = null;
              try
	      {

	          con =ConnectionDetails.getConnection();
                  statement = con.createStatement();
	          String upDateComand = " UPDATE BIR.SITE SET MANAGER_ID="+managerIdIn+
                                        " WHERE SITE_ID ="+ siteIdIn ;
	          //System.out.println("UPDATE>>> "+upDateComand);	          
	          statement.executeQuery(upDateComand);
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
	public Site getSiteByName(String siteNameIn)
	{
              Site site= new Site();
              Connection con = null;
              Statement statement = null;
              ResultSet rs= null;
              try
	      {
	          con =ConnectionDetails.getConnection();
	          statement = con.createStatement();
	          String sql = "SELECT * FROM BIR.SITE WHERE SITE_NAME ='"+ siteNameIn+"'";
	          //System.out.println("SELECT>>> "+sql);
	          rs = statement.executeQuery( sql );	         
	          while( rs.next() )
	          {      	
	            	site.setSiteId( rs.getInt(1));
	            	//System.out.println("rs1>>> "+rs.getInt(1));
	            	site.setSiteName(rs.getString(2));
	            	//System.out.println("rs2>>> "+rs.getString(2));
	            	site.setManagerId(rs.getInt(3));	            		            		            		
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
               return site;
	}
	 
	public void storeSite(String siteNameIn)
	{
	      Connection con = null;
              Statement statement = null;
              try
	      {	        
                  con =ConnectionDetails.getConnection();
	          statement = con.createStatement();
	          String insertCommand = "INSERT into BIR.SITE(SITE_NAME) "
                                        + " VALUES "
                                                  + "("
                                                          + "'" + siteNameIn + "'" 
                                                  +  ")";

	          //System.out.println("SQL Insert >> " + insertCommand );
	          statement.execute(insertCommand);
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
				
	
	public synchronized Vector getAllSites()
	{
              Vector sitesVec = new Vector();
              Connection con = null;
              Statement statement = null;
              ResultSet rs = null;
              try
	      {
                    con =ConnectionDetails.getConnection();
                    statement = con.createStatement();
                    String sql = "SELECT * FROM BIR.Site" ;
                    rs = statement.executeQuery( sql );  
                     while( rs.next() )
                     {
                          Site site= new Site();                           
                          site.setSiteId( rs.getInt(1));
                          site.setSiteName(rs.getString(2));
                          site.setManagerId(rs.getInt(3));
                          sitesVec.addElement(site);	            		            	
                          
                     }
                     con.commit();   
	    }
	   
            catch(SQLException sqle)
	    {
	      System.err.println("SQL or database not working 12" + sqle.getMessage());
	      sqle.printStackTrace();
	    }

	    catch(Exception e)
	    {
	      System.err.println("General exception from arhcive class12: " + e.getMessage());
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
              return sitesVec;
		
	}
	
	public synchronized  Vector getAllSiteNames()
	{
		Vector siteNameVec = new Vector();
                Connection con = null;
                Statement statement = null;
                ResultSet rs = null;
		try
	        {
                      con =ConnectionDetails.getConnection();
                      statement = con.createStatement();
                      String sql = "SELECT SITE_NAME FROM BIR.Site" ;
                      rs = statement.executeQuery( sql );
                      while( rs.next() )
                      {           	  
                            String n =rs.getString(1);
                            siteNameVec.addElement(n);	            		            	  	
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
                 return siteNameVec;
	}
}
