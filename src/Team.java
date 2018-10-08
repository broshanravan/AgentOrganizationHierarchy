
/*
 * Created on 18-Feb-2005
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
import java.util.*;
public class Team 

{
          private String teamName;
          private int teamId;
          private int managerId;
          private int bFId;
          private String classOfBusId;
          private boolean active;
          private boolean perm;
  
          TeamMaintenance  teamMaintenace = new TeamMaintenance();
                
                public Team()
                {
                        
                }
                
        
                public Team (int idIn , String teamNameIn,int managerIdIn,int bFIdIn,String classOfBusIdIn,boolean activeIn,boolean permIn)
                {
                        teamId =idIn;
                        teamName = teamNameIn;
                        managerId = managerIdIn;
            bFId=bFIdIn;
            classOfBusId = classOfBusIdIn;
            perm = permIn;
            active = activeIn;
                }
                //mutators
                
                public void setManagerId(int managerIdIn)
                {
                        managerId = managerIdIn;
                }
                public void setTeamId(int idIn)
                {
                        teamId =idIn;
                }
                
                public void setTeamName (String nameIn)
                {
                        teamName = nameIn;
                }
          
          public void setBusneseFunId(int bFIdIn)
                {
                        bFId =bFIdIn;
                }
          
          public void setClassOfBusId (String classOfBusIdIn)
                {
                        classOfBusId = classOfBusIdIn;
                }
          public void setPerm(boolean permIn)
          {
            perm= permIn;
          }
          public void setActive(boolean activeIn)
          {
            active = activeIn;
          }
                
                //Accerrors
                
                public int getTeamId()
                {
                        return teamId;
                }
                
                public String getTeamName()
                {
                        return teamName;
                }
                public int getManagerId()
                {
                        return managerId;
                }
          
          public int getBusneseFunId ()
                {
                        return bFId;
                }
          
          public String getClassOfBusId ()
                {
                        return classOfBusId;
                }
                public boolean isPerm()
          {
            return perm;
          }
          
          public boolean isActive()
          {
            return active;
          }
                
                
          public Vector getAllAutorisedManagers()
          {
                        
                        int groupId=teamMaintenace.getGroupId(getTeamId());
                        GroupInventory groupInventory = new GroupInventory();
                        Group group = groupInventory.getGroup(groupId);
                        Vector managersVec = group.getAllAutorizedManagers();
                        Personnel personnel = new Personnel();
                        Employee teamManager = personnel.findEmployeeByEmpId(getManagerId());
                        if(!(teamManager==null))
                        {
                                managersVec.addElement(teamManager);
                        }
                        
                        return managersVec;
                         
          }
	
}
