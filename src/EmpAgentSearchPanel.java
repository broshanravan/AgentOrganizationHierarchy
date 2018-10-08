
import java.awt.Color;
import javax.swing.*;

public class EmpAgentSearchPanel extends JPanel
{
  
  
  Agent agent;
  Team team;
  JTextField  usernameFld = new JTextField();
  JTextField  appFld = new JTextField();
  JTextField  roleFld = new JTextField();
  JTextField  teamFld = new JTextField();
  JTextField  groupFld = new JTextField();
  JTextField  departmentFld = new JTextField(); 
  
  public EmpAgentSearchPanel(Agent agentIn, Team teamIn)
  {
      setSize(900,40);
      setLayout(null);
      setupStrip();      
      setVisible(true);
      agent = agentIn;
      team= teamIn;
      showAgent(agent,team);
      
  }
  
  public void setupStrip()
  {
      add(usernameFld);
      add(appFld);
      add(roleFld);
      add(teamFld); 
      add(groupFld);
      add(departmentFld);
      
      usernameFld.setEditable(false); 
      appFld.setEditable(false);  
      roleFld.setEditable(false); 
      teamFld.setEditable(false); 
      
      setBackground(Color.WHITE);
      
      usernameFld.setBackground(Color.WHITE); 
      appFld.setBackground(Color.WHITE);  
      roleFld.setBackground(Color.WHITE); 
      teamFld.setBackground(Color.WHITE);
      
      usernameFld.setBounds(10,10,100,20); 
      appFld.setBounds(140,10,100,20);  
      roleFld.setBounds(270,10,100,20); 
      teamFld.setBounds(400,10,150,20); 
      groupFld.setBounds(580,10,150,20); 
      departmentFld.setBounds(760,10,150,20); 
      
  }
  
  public void showAgent (Agent a, Team t )
  {
      usernameFld.setText(a.getUserName()); 
      int appRoleId = agent.getApplicationRoleId();
      ApplicationRoleMaintenance appRoleMaint = new ApplicationRoleMaintenance();
      
      TeamMaintenance teamMaint = new TeamMaintenance();
      
      
      Group group=teamMaint.getTeamsCurentGroup(t.getTeamId());
      groupFld.setText(group.getGroupName());
      
      
      
      MaintainDepartment maintDep = new MaintainDepartment();
      
      Department dep =maintDep.getDepartment(group.getDepartmentId());
      
      
      departmentFld.setText(dep.getDepartmentName());
      
      
      AplicationRole appRole =appRoleMaint.getApplicationRole(appRoleId);
        
      int appId = appRole.getApplicationId();
      int roleId = appRole.getRoleId();
        
      MaintainApplication appMaint = new MaintainApplication();
      Application app = appMaint.getApplication(appId);
        
      RoleInventory roleInv = new RoleInventory();
      Role role = roleInv.getRole(roleId);
      
      appFld.setText(app.getApplicationDesc());  
      roleFld.setText(role.getRoleName());
      
      
      
      teamFld.setText(t.getTeamName());

  }
  
}