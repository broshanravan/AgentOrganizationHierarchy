import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn {
    
    //Test   
     
    int appRoleId =191;
    
    //live
    //int appRoleId =60;
    
    public LogIn() {
    
    }
    
    public boolean logginValid(String un, String pw){
        boolean isValid=false;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con=ConnectionDetails.getConnection();
           
            String sql =    " SELECT * FROM BIR.AGENT a WHERE " +
                            " a.AGENT_APPLICATION_LOGON =?" + 
                            " AND" +
                            " AGENT_APPLICATION_PWD =?" +  
                            " AND" +
                            " a.AGENT_APPLICATION_ROLE_ID=?" +
                            " AND" +
                            " a.ACTIVE_LOGON='Y'";
                
            statement = con.prepareStatement(sql);
            statement.setString(1,un);
            statement.setString(2,pw);
            statement .setInt(3,appRoleId);
            System.out.println("Check Login >>>>>>"+sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                isValid=true;
            }


        } catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
                rs.close();
                statement.close();

            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        
        return isValid;
    }
        
    
    public Agent getUser(String userName) {
        
        Agent agent = new Agent();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement;
        
        
        
        try {
                con=ConnectionDetails.getConnection();
                
                String sql = " SELECT * FROM BIR.AGENT a" +
                             " WHERE a.AGENT_APPLICATION_LOGON =?" +
                             " and" +
                             " a.AGENT_APPLICATION_ROLE_ID=?" ; 
                
                statement = con.prepareStatement(sql);
                statement .setString(1,userName);
                statement .setInt(2,appRoleId);
                rs = statement.executeQuery();
                while (rs.next()) {
                    agent.setId(rs.getInt(1));
                    agent.setEmpId(rs.getInt(2));
                    agent.setApplicationRoleId(rs.getInt(3));
                    agent.setUserName(rs.getString(4));
                    agent.setPassword(rs.getString(5));
                    String active = rs.getString(6);
                    if (active.equalsIgnoreCase("N")) {
                        agent.setActive(false);
                    } else {
                        agent.setActive(true);
                    }
                }            
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
               rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return agent;
    }
    
    
    public void addUser(Agent user){
        Connection con = null;
        PreparedStatement statement = null;

        try {
             con=ConnectionDetails.getConnection();            

            String insertCommand =  " INSERT into BIR.Agent a" + 
                                    "(" + 
                                            " a.EMPLOYEE_ID," + 
                                            " a.AGENT_APPLICATION_ROLE_ID," + 
                                            " a.AGENT_APPLICATION_LOGON," +                                    
                                            " a.AGENT_APPLICATION_PWD" + 
                                    ") " + 
                                    " VALUES (?,?,?,?)";                                     
                               
            statement=con.prepareStatement(insertCommand);
            statement.setInt(1,user.getEmpId());
            statement.setInt(2,appRoleId);
            statement.setString(3,user.getUserName().trim());
            statement.setString(4,user.getPassword().trim());
            
            
            statement.execute();
            con.commit();

        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        finally {
            try {
                statement.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
    }
    
    
    public Employee findEmployeeByRiasEmpNo(String riasEmpNoIn) {
        Employee employee = new Employee();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

            con=ConnectionDetails.getConnection();
            
            String sql = " SELECT * FROM bir.EMPLOYEE WHERE RIAS_EMP_NO =?" ;
            statement = con.prepareStatement(sql);
            statement.setString(1,riasEmpNoIn);
            rs = statement.executeQuery();

            while (rs.next()) {
                employee.setEmployeeId(rs.getInt(1));
                employee.setForName(rs.getString(2));
                employee.setSurName(rs.getString(3));
                employee.setDateStartAct(rs.getDate(4));
                employee.setDateEndAct(rs.getDate(5));
                employee.setDateStartPlaned(rs.getDate(6));
                employee.setDateEndPlaned(rs.getDate(7));
                employee.setDateOfBirth(rs.getDate(8));
                employee.setRiasEmpNo(rs.getString(9));
                employee.setGroupId(rs.getInt(10));
                employee.setEmail(rs.getString(11));
            }
            //System.out.println("SELECT>>>>> " + sql);
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return employee;
    }

    public void editUser(Agent agentIn) {

        Connection con = null;
        PreparedStatement statement = null;

        try {
             con=ConnectionDetails.getConnection();
             

            String upDateCommand =   " UPDATE BIR.Agent SET " +  
                                     " AGENT_APPLICATION_LOGON =?," + 
                                     " AGENT_APPLICATION_PWD =?" +  
                                     " WHERE" +
                                     " AGENT_ID=?" ;
                
            statement = con.prepareStatement(upDateCommand);
            
            statement.setString(1,agentIn.getUserName());
            statement.setString(2,agentIn.getPassword());
            statement.setInt(3,agentIn.getId());
            
            System.out.println("IN Edit User>>> UN="+agentIn.getUserName() +" PW+=" +agentIn.getPassword()+ "AGENT ID="+agentIn.getId());
            
            statement.executeUpdate();
            con.commit();
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }

    }
    
}
