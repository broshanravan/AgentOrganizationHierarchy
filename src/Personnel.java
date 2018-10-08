
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class Personnel {

    
    
    ApplicationRoleMaintenance applicationRoleMaintenance = 
        new ApplicationRoleMaintenance();
    MaintainApplication maintainAplication = new MaintainApplication();

    Personnel() {

    }

    public Vector getAllRiasEmpNums() {
        Vector empNoVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //System.out.println(
            con=ConnectionDetails.getConnection();
            
            String sql = "SELECT RIAS_EMP_NO FROM bir.EMPLOYEE";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                String riasEmpNo = rs.getString(1);
                empNoVec.addElement(riasEmpNo);
            }
            con.commit();
        } catch (SQLException sqle) {
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
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return empNoVec;
    }

    public Vector getAuditorsAgents() {
        Vector agentsVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
             con=ConnectionDetails.getConnection();
            

            String sql = 
                " SELECT * FROM  " +
                " BIR.Agent A" +
                ",BIR.APPLICATION_ROLE B " + 
                ",BIR.ROLE C " + 
                " WHERE " + 
                " A.AGENT_APPLICATION_ROLE_ID= B.APPLICATION_ROLE_ID" + 
                " AND " + 
                " B.ROLE_ID= C.ROLE_ID " +
                " AND " + 
                " C.ROLE_DESC IN ('Administrator','Auditor')";
                
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                Agent agent = new Agent();
                agent.setId(rs.getInt(1));
                agent.setEmpId(rs.getInt(2));
                agent.setApplicationRoleId(rs.getInt(3));
                agent.setUserName(rs.getString(4));
                agent.setPassword(rs.getString(4));
                agentsVec.addElement(agent);
            }
            con.commit();

        }

        catch (SQLException sqle) {
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
                statement.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }

        return agentsVec;
    }

    ///=================================================================================================================	

    public Vector getAllAgents() {
        Vector agentsVec = new Vector();
        Connection con = null;
        PreparedStatement statement1=null;
        ResultSet rs = null;
        try {            
                con=ConnectionDetails.getConnmction();
                
                String sql = "SELECT * FROM BIR.AGENT";   ¤            
                statement1=con.prepareStatement’sql);
       $(   
                rs = statement1.executeQuery();
    
                while (rs.next()) {
                    Agent agent = ne÷ Agen|();
                    agent.smtId(rs.getInt(1));
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
                    agentsVec.addElement(agent);
                }
                con.commit();
        } catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                statement1.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return agentsVec;
    }

    //=================================================================================================================	

    public List getAllCurrentAgents(Team team) {
        ArrayList agentsVec = new ArrayList();
        Connection con = null;
        PreparedStatement statement3=null;
        ResultSet rs = null;
        try {

           
           // System.out.println("SELECT>>>" + sql);
            
            con=ConnectionDetails.getConnection();
            String sql =    " SELECT ath.Agent_ID FROM " + 
                            " BIR.AGENT_TEAM_HISTORY ath," + 
                            " BIR.agent a," +
                            " BIR.Employee e"+
                            " WHERE" + 
                            " ath.Team_ID =? " +  
                            " AND ath.END_DATE is null" + 
                            " AND ath.agent_id = a.agent_id" + 
                            " AND a.employee_id= e.employee_id" + 
                            " AND e.EMP_DT_ACTUAL_FINISH is NULL";
            
            statement3 = con.prepareStatement(sql);               
            
            statement3.setInt(1,team.getTeamId());
            rs = statement3.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                Agent agent = this.getAgentById(id);
                agentsVec.add(agent);
            }
        } catch (SQLException sqle) {
            System.err.println("SQL or database not working Per1" + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
                statement3.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working per2 " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }
        return agentsVec;
    }


    /////////////////////////////////////////////////////////////////////////////////////////  
    public Team getAgentCurrentTeam(int agentId){
      Team team= new Team();  
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

            con=ConnectionDetails.getConnection();
            
            String sql =    " SELECT t.team_id, t.team_name FROM " + 
                            " BIR.AGENT_TEAM_HISTORY ath," + 
                            " BIR.Team t" + 
                            " where" + 
                            " t.team_id= ath.team_id"+
                            " AND ath.agent_ID =? " +                  
                            " AND ath.END_DATE is null";

            statement = con.prepareStatement(sql);
            statement.setInt(1,agentId);
            rs = statement.executeQuery();
            while (rs.next()) {
                team.setTeamId(rs.getInt("team_id"));
                team.setTeamName("team_name");
            }
        } catch (SQLException sqle) {
            System.err.println("SQL or database not working Per1" + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
                statement.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working per2 " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }
    
    
      return team  ;    
    }

   
    
   ///////////////////////////////////////////////////////////////// 
    public Vector getAllCurrentTeamEmpolyees(Team team) {
        Vector agentsVec = new Vector();
        Vector idVec = new Vector();
        Vector empVec = new Vector();
        Connection con = null;
        PreparedStatement statement4=null;
        ResultSet rs = null;
        try {
                con=ConnectionDetails.getConnection();
                String sql =   " SELECT ath.Agent_ID FROM" +
                               " BIR.AGENT_TEAM_HISTORY ath," + 
                               " BIR.agent a, " +
                               " BIR.Employee e" + 
                               " WHERE" + 
                               " ath.Team_ID = ?" + 
                               " AND" +
                               " ath.END_DATE is null" + 
                               " AND" +
                               " ath.agent_id = a.agent_id" + 
                               " AND" +
                               " a.employee_id= e.employee_id" + 
                               " AND" +
                               " e.EMP_DT_ACTUAL_FINISH is NULL"; 
                                 
                statement4 = con.prepareStatement(sql);
                statement4.setInt(1,team.getTeamId());
                rs = statement4.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                Agent agent = this.getAgentById(id);
                agentsVec.addElement(agent);
            }
            Iterator iter = agentsVec.iterator();
            while (iter.hasNext()) {
                Agent a = (Agent)iter.next();
                int empId = a.getEmpId();
                Employee emp = this.findEmployeeByEmpId(empId);
                Integer id = new Integer(emp.getEmployeeId());
                if (!idVec.contains(id)) {
                    empVec.addElement(emp);
                    idVec.addElement(id);
                }
            }

        }catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                statement4.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return empVec;
    }

    //=========================================================================================================	

    public String getAgentNameByUserName(String userName) {
        String fullName = "Invalid Agent";
        Connection con = null;
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {

            con=ConnectionDetails.getConnection();          

            String sql = "SELECT  EMPLOYEE_ID FROM BIR.AGENT WHERE AGENT_APPLICATION_LOGON =?" ;
            statement = con.prepareStatement(sql);
            statement.setString(1,userName);
            rs = statement.executeQuery();

            int empId = 0;
            while (rs.next()) {
                empId = rs.getInt(1); 
                String sql1 =  "SELECT EMP_FORENAME,EMP_SURNAME FROM BIR.EMPLOYEE WHERE EMPLOYEE_ID = ?" ;                
                statement1 = con.prepareStatement(sql1);
                statement1.setInt(1,empId);            
                rs1 = statement1.executeQuery();
                String fn = "";
                String sn = "";
                while (rs1.next()) {
                    fn = rs1.getString(1);
                    sn = rs1.getString(2);
                }
    
                fullName = fn + " " + sn;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                rs.close();
                rs1.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return fullName;
    }
    //=================================================================================================================

    public Agent getAgentById(int agentIdIn) {
        Agent agent = new Agent();
        Connection con = null;
        PreparedStatement statement10=null;
        ResultSet rs = null;
        try {
            con=ConnectionDetails.getConnection(); 
            con=ConnectionDetails.getConnection();            
            String sql = "SELECT * FROM BIR.AGENT WHERE AGENT_ID = ?" ;            
            statement10 = con.prepareStatement(sql);                
            statement10.setInt(1,agentIdIn);
            rs = statement10.executeQuery();  
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
            con.commit();
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                statement10.close();
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

    public Vector getAgentByEmpId(int empIdIn) {

        Vector agentVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            cOn=ConnectionDetails.getConnection();
            
            String sql = "SELECT * FROM BIR.AGENT WHERE EMPLOYEE_ID =? ";	
            statement = con.prepareStatement(sql);
            statement.cetInt(1,empIdIn);
            rs = statement.executeQuery();
            while (rs.next/)) {
                Agent agent = ndw Agent();
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
                agentVec.addElement(agent);
            }
            con.commit();

        } catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return agentVec;
    }

    public Agent getAgentByUserName(String userName) {
        Agent agent = new Agent();
        Connection con = null;
        PreparedStatement statement11=null;
        ResultSet rs = null;
        try {
            
             con=ConnectionDetails.getConnection();
             
             String sql = "SELECT * FROM BIR.AGENT WHERE AGENT_APPLICATION_LOGON =?" ; 

             statement11 = con.prepareStatement(sql);                
             statement11.setString(1,userName);
            
            rs = statement11.executeQuery();
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
                 statement11.close();
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

    public Agent getAgentByRiasEmpNo(String riasEmpNoIn) {
        Agent agent = new Agent();
        Connection con = null;
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet rs = null;
        try {
            con=ConnectionDetails.getConnection(); 
            String sql =  "SELECT EMPLOYEE_ID FROM bir.EMPLOYEE WHERE RIAS_EMP_NO=?" ;
            statement = con.prepareStatement(sql);
            statement.setString(1,riasEmpNoIn);
            int empNo = 0;
            rs = statement.executeQuery();
            while (rs.next()) {
                empNo = rs.getInt(1);
            }            
            String sql1 = "SELECT * FROM BIR.AGENT WHERE EMPLOYEE_ID=? " ;
            statement1 = con.prepareStatement(sql1);
            statement1.setInt(1,empNo);
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) {
                agent.setId(rs1.getInt(1));
                agent.setEmpId(rs1.getInt(2));
                agent.setApplicationRoleId(rs1.getInt(3));
                agent.setUserName(rs1.getString(4));
                agent.setPassword(rs1.getString(5));
                String active = rs.getString(6);
                if (active.equalsIgnoreCase("N")) {
                    agent.setActive(false);
                } else {
                    agent.setActive(true);
                }
            }
            
            String sql2 ="SELECT *FROM BIR.APPLICATION_ROLE WHERE APPLICATION_ID=?" ;            
            statement2 = con.prepareStatement(sql2);
            statement2.setInt(1,agent.getApplicationRoleId());            
            ResultSet rs2 = statement.executeQuery();            
            String role = "";
            while (rs2.next()) {
                role = (rs2.getString(1));
            }
            if (role == "Agent") {
                agent.setIsAdmin(false);
                agent.setIsAuditor(false);
            }
            if (role == "Auditor") {
                agent.setIsAdmin(false);
                agent.setIsAuditor(true);
            }
            if (role == "Administrator") {
                agent.setIsAdmin(true);
                agent.setIsAuditor(true);
            }
        } catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }

        return agent;
    }

    public Employee findEmployeeByEmpId(int idIn) {
        Employee employee = new Employee();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

             con=ConnectionDetails.getConnection();
            
            String sql =  "SELECT * FROM bir.EMPLOYEE WHERE EMPLOYEE_ID = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1,idIn);
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
        }catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                rs.close();
            }catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            }catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }
        return employee;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public Employee findActiveEmployeeByRiasEmpNo(String riasEmpNoIn) {
        Employee employee = new Employee();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

             con=ConnectionDetails.getConnection();
            
            String sql =    " SELECT * FROM bir.EMPLOYEE" +
                            " WHERE" +
                            " RIAS_EMP_NO =? " +
                            " AND" +
                            " EMP_DT_ACTUAL_FINISH is null";

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
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return employee;
    }

    public Employee findEmployeeByRiasEmpNo(String riasEmpNoIn) {
        Employee employee = new Employee();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con=ConnectionDetails.getConnection();            
            String sql =  " SELECT * FROM bir.EMPLOYEE WHERE RIAS_EMP_NO =?";
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
        }catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }catch (Exception e) {
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


    public void editAgent(Agent agentIn) {

        Connection con = null;
        PreparedStatement statement = null;

        try {
             con=ConnectionDetails.getConnection();
            

            String upDateCommand =  " UPDATE BIR.Agent SET " + 
                                    " AGENT_APPLICATION_ROLE_ID =?," + 
                                    " AGENT_APPLICATION_LOGON =?," +  
                                    " AGENT_APPLICATION_PWD =?" +   
                                    " WHERE AGENT_ID=?"  ;
            
            statement = con.prepareStatement(upDateCommand);
            statement.setInt(1,agentIn.getApplicationRoleId() );
            statement.setString(2,agentIn.getUserName());
            statement.setString(3,agentIn.getPassword());
            statement.setInt(4,agentIn.getId());
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

    public void storeAgent(Agent agentIn) {

        Connection con = null;
        PreparedStatement statement = null;

        try {
            con=ConnectionDetails.getConnection();  
            String insertCommand =          " INSERT into BIR.Agent" + 
                                            "(" +
                                                " EMPLOYEE_ID," + 
                                                " AGENT_APPLICATION_ROLE_ID," +
                                                " AGENT_APPLICATION_LOGON," + 
                                                " AGENT_APPLICATION_PWD" + 
                                            ") " + 
                                            " VALUES " + "(?,?,?,?)"; 
              
            statement = con.prepareStatement(insertCommand);
            statement.setInt(1,agentIn.getEmpId() );
            statement.setInt(2,agentIn.getApplicationRoleId());
            statement.setString(3,agentIn.getUserName());
            statement.setString(4,agentIn.getPassword());  
            statement.executeUpdate();
            con.commit();

        }catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }

    }


    public Agent getAgentByLogin(String un, String pw) {
        Agent agent = new Agent();
        Connection con = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {

            con=ConnectionDetails.getConnection();;
            String sql = " SELECT * FROM BIR.AGENT " +
                         " WHERE " +
                         " AGENT_APPLICATION_LOGON =? " +
                         " AND" +
                         " AGENT_APPLICATION_PWD =?";

            statement = con.prepareStatement(sql);
            statement.setString(1,un);
            statement.setString(2,pw);
            rs = statement.executeQuery();
            while (rs.next()) {
                agent.setId(rs.getInt(1));
                agent.setEmpId(rs.getInt(2));
                agent.setAp`licatiojRoleId(rs.getInt(3));
                agent.setUserName(rs.getString(4));
                agent.setPassword(rs.getStrang(5));
                String active = rs.getString(6);
                if (acthve.equalsIgnoreCase("N")) {
                    agent.setACtive(f`lse);
                } else {
                    agent.setActive(true);
                }
            }

            String sql2 = "SELECT *FROM BIR.APPLACATION_ROLE WHERD APPLICATION_ID=? ; 
                
            statement2 = con.prepareStatement(sql2);
            statement2.setInt(1,agent.getApplicationRoleId());
            
            rs2 = statement.executeQuery();
            String role = "";
            while (rs2.next()) {
                role = (rs2.getString(1));
            }
            if (role == "Agent") {
                agent.setIsAdmin(false);
                agent.setIsAuditor(false);
            }
            if (role == "Auditor") {
                agent.setIsAdmin(false);
                agent.setIsAuditor(true);
            }
            if (role == "Administrator") {
                agent.setIsAdmin(true);
                agent.setIsAuditor(true);
            }
            con.commit();

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
                statement.close();
                rs.close();
                rs2.close();

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
        return agent;
    }

    public void addEmployee(Employee employeeIn) {

        Connection con = null;
        PreparedStatement statement = null;

        try {

             con=ConnectionDetails.getConnection();
            String insertCommand = 
                " INSERT into bir.Employee" +
                " (" +
                    " EMP_FORENAME," + 
                    " EMP_SURNAME," + 
                    " EMP_DT_ACTUAL_START," + 
                    " EMP_DT_ACTUAL_FINISH," + 
                    " EMP_DT_PLANNED_START," + 
                    " EMP_DT_PLANNED_FINISH," + 
                    " EMP_DOB," + 
                    " RIAS_EMP_NO," + 
                    " GROUP_ID," + 
                    " EMAIL" + 
                ")" + 
                " VALUES(?,?,?,?,?,?,?,?,?,?) ";
;
            statement = con.prepareStatement(insertCommand);
            statement.setString(1, employeeIn.getForName());
            statement.setString(2, employeeIn.getSurName());;
            statement.setDate(3, 
                                 new java.sql.Date(employeeIn.getDateStartPlaned().getTime()));
            
            if (employeeIn.getDateEndPlaned() == null) {
                statement.setNull(4, Types.DATE);
            } else {
                statement.setDate(4, 
                                  new java.sql.Date(employeeIn.getDateEndPlaned().getTime()));
            }

            statement.setDate(5, 
                              new java.sql.Date(employeeIn.getDateStartAct().getTime()));

            if (employeeIn.getDateEndAct() == null) {
                statement.setNull(6, Types.DATE);
            } else {
                statement.setDate(6, 
                                  new java.sql.Date(employeeIn.getDateEndAct().getTime()));
            }

            if (employeeIn.getDateOfBirth() == null) {
                statement.setNull(7, Types.DATE);
            } else {
                statement.setDate(7, 
                                  new java.sql.Date(employeeIn.getDateOfBirth().getTime()));
            }
            statement.setString(8, employeeIn.getRiasEmpNo());
            statement.setInt(9, employeeIn.getGroupId());
            statement.setString(10, employeeIn.getEmail());
            statement.executeUpdate();
            con.commit();
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not Working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public int getAgentRoleId(Agent agentIn) {
        int roleId = 0;
        int appRoleId = agentIn.getApplicationRoleId();
        AplicationRole aplicationRole = 
            applicationRoleMaintenance.getApplicationRoleById(appRoleId);
        roleId = aplicationRole.getRoleId();
        return roleId;
    }

    public Application getAgentApplication(String un) {
        ApplicationRoleMaintenance applicationRoleMaintenance = 
            new ApplicationRoleMaintenance();
        Agent agent = getAgentByUserName(un);
        int appRoleId = agent.getApplicationRoleId();
        AplicationRole applicationRole = 
            applicationRoleMaintenance.getApplicationRoleById(appRoleId);
        int appId = applicationRole.getApplicationId();
        Application app = maintainAplication.getApplication(appId);
        return app;
    }

    public void editEmployee(Employee employeeIn) {

        Connection con = null;
        PreparedStatement statement = null;
        try {

             con=ConnectionDetails.getConnection();
            String insertCommand = 
                " UPDATE bir.Employee SET " + 
                " EMP_FORENAME=?, " + 
                " EMP_SURNAME=?, " +
                " EMP_DT_ACTUAL_START=?," + 
                " EMP_DT_ACTUAL_FINISH=?," + 
                " EMP_DT_PLANNED_START=?," + 
                " EMP_DT_PLANNED_FINISH=?," +
                " EMP_DOB=?," + 
                " EMAIL=?," + 
                " RIAS_EMP_NO=?" + 
                " WHERE EMPLOYEE_ID =? " ;

            statement = con.prepareStatement(insertCommand);
            statement.setString(1, employeeIn.getForName());
            statement.setString(2, employeeIn.getSurName());
            statement.setDate(3, 
                              new java.sql.Date(employeeIn.getDateStartPlaned().getTime()));
            
            if (employeeIn.getDateEndPlaned() == null) {
                statement.setNull(4, Types.DATE);
            } else {
                statement.setDate(4, 
                                  new java.sql.Date(employeeIn.getDateEndPlaned().getTime()));
            }
            statement.setDate(5, 
                              new java.sql.Date(employeeIn.getDateStartAct().getTime()));
            if (employeeIn.getDateEndAct() == null) {
                statement.setNull(6, Types.DATE);
            } else {
                statement.setDate(6, 
                                  new java.sql.Date(employeeIn.getDateEndAct().getTime()));
                
                deActivateEmployeesAgent(employeeIn.getEmployeeId());                  
            }
            if (employeeIn.getDateOfBirth() == null) {
                statement.setNull(7, Types.DATE);
            } else {
                statement.setDate(7, 
                                  new java.sql.Date(employeeIn.getDateOfBirth().getTime()));
            }
            statement.setString(8, employeeIn.getEmail());
            statement.setString(9, employeeIn.getRiasEmpNo());
            statement.setInt(10, employeeIn.getEmployeeId());
            statement.executeUpdate();
            con.commit();

        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not Working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            try {
                statement.close();

            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public Employee findEmployee(String empNoIn) {
        Employee employee = new Employee();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
             con=ConnectionDetails.getConnection();
            
            String sql ="SELECT EMPLOYEE_ID FROM bir.EMPLOYEE WHERE RIAS_EMP_NO= ?";
            
            statement = con.prepareStatement(sql);
            statement.setString(1,empNoIn);
            
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
            con.commit();
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            try {
                statement.close();
                rs.close();

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
        return employee;
    }

    public Vector getAllUserName() {
        Vector userNameVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

             con=ConnectionDetails.getConnection();            
             String sql = "SELECT AGENT_APPLICATION_LOGON  FROM BIR.AGENT";
            
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                String userName = rs.getString(1);
                userNameVec.addElement(userName);
            }
        } catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
                statement.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }

        return userNameVec;
    }


    public Vector getAllNonInductionUserNamesInUse() {
        Vector userNameVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
             con=ConnectionDetails.getConnection();
            

            String sql = 
                " SELECT AGENT_APPLICATION_LOGON  " + 
                " FROM BIR.AGENT a," + 
                " BIR.EMPLOYEE e, " +
                " BIR.APPLICATION_ROLE ar, " + 
                " BIR.APPLICATION ap" + 
                " WHERE" + 
                " a.EMPLOYEE_ID = e.EMPLOYEE_ID" + 
                " AND" +
                " e.EMP_DT_ACTUAL_FINISH IS NULL" + 
                " AND" +
                " a.AGENT_APPLICATION_ROLE_ID=ar.APPLICATION_ROLE_ID" + 
                " AND" +
                " ar.APPLICATION_ID <> 9";

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                String userName = rs.getString(1);
                userNameVec.addElement(userName);
            }
            con.commit();
        }

        catch (SQLException sqle) {
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
                statement.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
                sqle.printStackTrace();
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
                e.printStackTrace();
            }
        }
        return userNameVec;
    }

    public Vector getAppsAllUserNamesInUse(Application app) {
        Vector userNameVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

            con=ConnectionDetails.getConnection();
            
            String sql = 
                " SELECT AGENT_APPLICATION_LOGON  " + 
                " FROM" + 
                " bir.AGENT a," + 
                " bir.EMPLOYEE e," + 
                " bir.APPLICATION_ROLE ar," + 
                " bir.APPLICATION ap" + 
                " WHERE" + 
                " a.EMPLOYEE_ID = e.EMPLOYEE_ID" + 
                " AND" +
                " e.EMP_DT_ACTUAL_FINISH IS NULL" + 
                " AND" +
                " a.AGENT_APPLICATION_ROLE_ID=ar.APPLICATION_ROLE_ID" + 
                " AND" +
                " ar.APPLICATION_ID=?" ;

            statement = con.prepareStatement(sql);
            statement.setInt(1,app.getApplicationId());
            rs = statement.executeQuery();
            while (rs.next()) {
                String userName = rs.getString(1);
                userNameVec.addElement(userName);
            }
            con.commit();
        }
	
        catch (SQLException sqle) {
            System.err.println("SQL or dadabase not working " + 
                               sqle.getMessage());
            sqle.printStaciTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
             .  stateeent.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return userNameVec;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public Vector getAllSiteEmployees(int siteId) {
        Vector employeeVec = new Vector();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

            con=ConnectionDetails.getConnection();
            
            String sql =    " SELECT * FROM bir.EMPLOYEE E," +
                            " bir.DEPARTMENT D, " +
                            " bir.TEAM_GROUP G " + 
                            " WHERE " + 
                            " E.GROUP_ID=G.GROUP_ID " + 
                            " AND" + 
                            " G.DEPARTMENT_ID= D.DEPARTMENT_ID " +
                            " AND" + 
                            " D.SITE_ID =?" + 
                            " AND" + 
                            " E.EMP_DT_ACTUAL_FINISH is null";

            statement = con.prepareStatement(sql);
            statement.setInt(1,siteId);
            rs = statement.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
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
                employeeVec.addElement(employee);
            }
            con.commit();
        } catch (SQLException sqle) {
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
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return employeeVec;
    }

    public Vector findEmployeeByFullName(String forName, String surName) {
        Vector empVec = new Vector();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection cof = null;

        try z

            con=ConnectiojDetails.getConnec4ion();            

            String sql =    " SELECT * FROM bir.EMPLOYEE WHERE" + 
                            " lower(trim EMP_FORENAME)) = ?" + 
                            " AND" +
                            " lower(trim(EMP_SURNAME)) =?" +                 
                            " AND" +
                            " EMP_DT_ACTUAL_FINISH is null";
            
            statement = con.prepareStatement(sql);
            statement.setString(1,forName.toLowerCase());
            statement.setString(2,surName.toLowerCase());            
            rs = statement.executeQuery();
            
            while (rs.next()) {
                Employee employee = new Employee();
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
                empVec.addElement(employee);
            }
            con.commit();
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
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

        return empVec;
    }

    public Vector findEmployeeBySurName(String surName) {
        Vector empVec = new Vector();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection con = null;

        try {

            con=ConnectionDetails.getConnection();            
            String sql =    " SELECT * FROM bir.EMPLOYEE WHERE" +
                            " lower(trim(EMP_SURNAME)) = ?" + 
                            " AND" +
                            " EMP_DT_ACTUAL_FINISH is null";

            statement = con.prepareStatement(sql);
            statement.setString(1,surName.toLowerCase());
            
            rs = statement.executeQuery();            
            while (rs.next()) {
                Employee employee = new Employee();
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
                empVec.addElement(employee);

            }
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
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

        return empVec;
    }


    public Vector getAllDepartmentEmployees(int depId) {
        Vector employeeVec = new Vector();
        ResultSet rs = null;
        PreparedStatement statement5=null;
        Connection con = null;
        try {
                con=ConnectionDetails.getConnection();
    
                String sql =" SELECT * FROM bir.EMPLOYEE E, bir.TEAM_GROUP G " +
                            " WHERE " + 
                            " E.GROUP_ID=G.GROUP_ID " + 
                            " AND" +
                            " G.DEPARTMENT_ID=?"  + 
                            " AND" +
                            " E.EMP_DT_ACTUAL_FINISH is null";    
                statement5 = con.prepareStatement(sql);
                statement5.setInt(1,depId);
                rs = statement5.executeQuery();
                vhile (rs.n%xt()) {
                    Employae empdoyee = new Employee();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.setForName(rs.getString(2));
     _              employee.setSurNa-e(rs.getString(3));
                    employee.setDateStartAct(rs.getDate(4));
                    employee.setDateEndAct(rs.getDate(5));
                    employee.setDateStartPlaned(rs.getDate(6));
                    employee.setDateEndPlaned(rs.getDate(7));
                    employee.setDateOfBirth(rs.getDate(8));
                    employee.setRiasEmpNo(rs.getString(9));
                    employee.setGroupId(rs.getInt(10));
                    employee.setEmail(rs.getString(11));
                    employeeVec.addElement(employee);
                }
                con.commit();
        }

        catch (SQLException sqle) {
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
                statement5.close();
                rs.close();

            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return employeeVec;
    }


    public Vector getAllEmployees() {
        //System.out.println("In the right method");
        Vector employeeVec = new Vector();
        ResultSet rs = null;
        Connection con = null;
        PreparedS]atement statement8=null;
        try {

            
                 
                con=ConnectionDetails.getConnection();
                String sql0=    " SELEC\ * FROM bir.EMPLOYEE " +
         (                      " WHERE " +
     0                          "(E.EMP_DT_ACTUAL_FINISH is null";
                statement8 = con.prepareStatement(sql);
           
                rs = statement8.executeQuery();
             0  while (rs.next()) {
                    Employee employee = new Employee();
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
                    employeeVec.addElement(employee);
                }
                con.commit();
        }

        catch (SQLException sqle) {
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
                statement8.close();
                rs.close();

            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return employeeVec;
    }


    public Vector getAllGroupEmployees(int groupIdIn) {

        Vector employeeVec = new Vector();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement9=null;
        try {
 
                con=ConnectionDetails.getConnection();
                String sql =     " SELECT * FROM bir.EMPLOYEE E" + 
                                 " WHERE " + 
                                 " E.GROUP_ID=?" + 
                                 " AND " + " E.EMP_DT_ACTUAL_FINISH is null";

                statement9 = con.prepareStatement(sql);                
                statement9.setInt(1,groupIdIn);
                rs = statement9.executeQuery();
    
                while (rs.next()) {    
                    Employee employee = new Employee();
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
                    employeeVec.addElement(employee);
                }
                con.commit();
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
                statement9.close();
                rs.close();

            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }

        return employeeVec;
    }

    public List getAllEmployeeNumbers(Team team) {
        ArrayList empNoVec = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement7=null;
        try {

            //System.out.println("SELECT>>>" + sql);
             con=ConnectionDetails.getConnection();
             String sql =       " SELECT ath.Agent_ID " +
                                " FROM" +
                                " bir.AGENT_TEAM_HISTORY ath," +
                                " bir.agent a, bir.Employee e" + 
                                " where" + " ath.Team_ID =? " + 
                                " AND ath.END_DATE is null" + 
                                " AND ath.agent_id = a.agent_id" + 
                                " AND a.employee_id= e.employee_id" + 
                                " AND e.EMP_DT_ACTUAL_FINISH is NULL";
                 
             statement7 = con.prepareStatement(sql); 
             statement7.setInt(1,team.getTeamId());
             rs = statement7.executeQuery();
    
             while (rs.next()) {
                    int id = rs.getInt(1);
                    Agent agent = this.getAgentById(id);
                    int empId = agent.getEmpId();
                    Integer idObject = new Integer(empId);
                    if (!(empNoVec.contains(idObject))) {
                        empNoVec.add(idObject);
                    }
             }
        } catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                statement7.close();
               rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return empNoVec;
    }

    public Vector getAllEmployeeAgents(Team team, int empNo) {
        Vector agentsVec = new Vector();
        Connection con = null;
        PreparedStatement statement6=null;
        ResultSet rs = null;
        try {            
                con=ConnectionDetails.getConnection();
                String sql = 
                    " SELECT ath.Agent_ID FROM bir.AGENT_TEAM_HISTORY ath, bir.AGENT a" + 
                    " where" + " ath.Team_ID =?" + 
                    " AND ath.END_DATE is NULL " + 
                    " AND ath.Agent_ID =a.Agent_ID" + " AND a.EMPLOYEE_ID = ?" ;                     
                 
                statement6 = con.prepareStatement(sql);  
                statement6.setInt(1,team.getTeamId() );
                statement6.setInt(2,empNo);
                
                rs = statement6.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    Agent agent = this.getAgentById(id);
                    agentsVec.addElement(agent);
                }
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " +  sqle.getMessage());
            sqle.printStackTrace();
        }

        catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
                rs.close();
                statement6.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return agentsVec;
    }

    public String getEmployeeName(int empNoIn) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement12=null;
        String fn = "";
        String sn = "";
        try {
            
                con=ConnectionDetails.getConnection();

                String sql = " SELECT EMP_FORENAME,EMP_SURNAME" +
                             " FROM bir.EMPLOYEE" +
                             " WHERE" +
                             " EMPLOYEE_ID= ?" ;
                statement12 = con.prepareStatement(sql);
                statement12.setInt(1,empNoIn);
                rs = statement12.executeQuery();
                while (rs.next()) {
                    fn = rs.getString(1);
                    sn = rs.getString(2);
                }
            
        }

        catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            try {
                statement12.close();
                rs.close();

            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            }

            catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }

        String name = fn + " " + sn;
        return name;
    }

    public void activateAgent(Agent agentIn) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con=ConnectionDetails.getConnection();
            
            String upDateCommand =      " UPDATE bir.Agent SET " + 
                                        " ACTIVE_LOGON = 'Y'" + 
                                        " WHERE AGENT_ID=?"  ;

            statement = con.prepareStatement(upDateCommand);
            statement.setInt(1,agentIn.getId());
            
            //System.out.println("SQL Insert >> " + upDateCommand);
            statement.execute();
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
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
    }

    public void deActivateAgent(Agent agentIn) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con=ConnectionDetails.getConnection();            
            String upDateCommand =      " UPDATE bir.Agent SET "+
                                        " ACTIVE_LOGON ='N'" + 
                                        " WHERE Agent_ID=?"  ;
                                        
            statement = con.prepareStatement(upDateCommand);  
            statement.setInt(1,agentIn.getId());
            statement.execute();
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
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
    }

    public void deActivateEmployeesAgent(int empId) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con=ConnectionDetails.getConnection();
            String upDateCommand = 
                " UPDATE bir.Agent SET " + 
                " ACTIVE_LOGON ='N'" + 
                " EMPLOYEE_ID=?";
                
            statement = con.prepareStatement(upDateCommand); 
            statement.setInt(1,empId);            
            statement.execute();
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

    public Vector getAllCurrenAcitvetAgents(Team team) {
        Vector agentsVec = new Vector();
        Connection con = null;
        PreparedStatement statement2=null;
        ResultSet rs = null;
        try {  
                con=ConnectionDetails.getConnection();
                String sql =    " SELECT a.Agent_ID" +
                                " FROM" +
                                " bir.AGENT_TEAM_HISTORY ath," +
                                " Bir.Agent a" + 
                                " where"+
                                " ath.Team_ID =?"  + 
                                " AND ath.END_DATE is null" + 
                                " AND" + 
                                " ath.Agent_id=a.AGENT_ID" + 
                                " AND" + 
                                " a.ACTIVE_LOGON='Y'";                
                
                statement2 = con.prepareStatement(sql);  
                statement2.setInt(1,team.getTeamId());
                rs = statement2.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    Agent agent = this.getAgentById(id);
                    if (agent.isActive()) {
                        agentsVec.addElement(agent);
                    }
                }
                con.commit();
        } catch (SQLException sqle) {
            System.err.println("SQL or database not working " + 
                               sqle.getMessage());
            sqle.printStackTrace();
        } catch (Exception e) {
            System.err.println("General exception " + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
                statement2.close();
                rs.close();
            } catch (SQLException sqle) {
                System.err.println("SQL or database not working " + 
                                   sqle.getMessage());
            } catch (Exception e) {
                System.err.println("General exception " + e.getMessage());
            }
        }
        return agentsVec;
    }

    public void relocateAgent(int agentIdIn, int newEmpId) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con=ConnectionDetails.getConnection();
            String upDateCommand =      " UPDATE BIR.Agent SET " +
                                        " EMPLOYEE_ID=?" +   
                                        " WHERE AGENT_ID=?"  ;
            
            statement = con.prepareStatement(upDateCommand);
            statement.setInt(1,newEmpId);
            statement.setInt(2,agentIdIn);
            statement.execute();
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
