import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AgentSearchDialog extends JDialog
{
  private static final int height = 200;
  private static final int width= 400;
  Personnel personnel = new Personnel();
  ApplicationRoleMaintenance appRoleMaint = new ApplicationRoleMaintenance();
  MaintainApplication appMaint = new MaintainApplication();
  JLabel userNameLbl = new JLabel("UserName");
  JLabel appLbl = new JLabel("Applicatione");
  JLabel roleLbl = new JLabel("Role");
  JLabel teamLbl = new JLabel("Team");
  
  JTextField  userNameFld = new JTextField();
  JTextField  appFld = new JTextField();
  JTextField  roleFld = new JTextField();
  JTextField  teamFld = new JTextField();
  
  JButton searchBtn = new JButton("Find"); 
  JButton okBtn = new JButton("OK"); 
  
  ButtonListener buttonListener = new ButtonListener();
  
  public void setupScreen()
  {
      getContentPane().add(userNameLbl); 
      getContentPane().add(appLbl);
      getContentPane().add(roleLbl); 
      getContentPane().add(teamLbl);
      
      
      getContentPane().add(userNameFld); 
      getContentPane().add(appFld); 
      getContentPane().add(roleFld);
      getContentPane().add(teamFld);
      
      getContentPane().add(searchBtn); 
      getContentPane().add(okBtn);  
      
      //Adding listeners
      searchBtn.addActionListener(buttonListener); 
      okBtn.addActionListener(buttonListener); 
      
      // disable eddit
 
      appFld .setEditable(false); 
      roleFld.setEditable(false); 
      teamFld.setEditable(false); 
      
      //Setting bounds
      userNameLbl.setBounds(10,10,120,20); 
      appLbl.setBounds(10,40,120,20); 
      roleLbl .setBounds(10,70,120,20); 
      teamLbl.setBounds(10,100,120,20); 
      
      
      userNameFld.setBounds(140,10,120,20);  
      appFld .setBounds(140,40,120,20); 
      roleFld.setBounds(140,70,120,20); 
      teamFld.setBounds(140,100,120,20); 
      
      searchBtn.setBounds(270,10,100,25); 
      okBtn.setBounds(150,140,100,25);  
  }
  
  
  public AgentSearchDialog(MainScreen ms)
  {
    super (ms,true);
    getContentPane().setLayout(null);
    setSize(width,height);
    setupScreen();
      setLocationRelativeTo(ms);
    setVisible(true);
  }
  
  public AgentSearchDialog()
  {
    setLayout(null);
    setSize(width,height);
    setupScreen();
    setVisible(true);
  }
  
  public void findAgent()
  {
    try
		{
			if (userNameFld.getText().equals(""))
				throw new ParametrizedException("Please Enter the agents UserName");
				
				String originalUn = userNameFld.getText();
				Agent agent = personnel.getAgentByUserName(userNameFld.getText());
        
        if (agent.getId()==0)
				throw new ParametrizedException("Invalid username, please try agein");
        
				Employee emp = personnel.findEmployeeByEmpId( agent.getEmpId());
				String n = emp.getForName()+" "+ emp.getSurName();	
				userNameFld.setText(n);
        
        int appRoleId = agent.getApplicationRoleId();
        AplicationRole appRole = appRoleMaint.getApplicationRole(appRoleId);
        int appId = appRole.getApplicationId();
        Application application = appMaint.getApplication(appId);
        String appName = application.getApplicationDesc();
        appFld.setText(appName);     
        RoleInventory roleInventory = new RoleInventory();
        int roleId = personnel.getAgentRoleId(agent);
        Role role = roleInventory.getRole(roleId);
        String roleName = role.getRoleName();
        roleFld.setText(roleName); 	
				MaintainAgentTeamHistory maintainAgentTeamHistory = new MaintainAgentTeamHistory();
				Team team =maintainAgentTeamHistory.getAgentsCurrentTeam(agent);
				String teamName = team.getTeamName();		
				teamFld.setText(teamName);
				
		}
		catch(ParametrizedException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
  }
  public void exit()
  {
    setVisible(false);
  }
  public class ButtonListener implements ActionListener
	{
    	public void actionPerformed(ActionEvent e)
    	{
    		if(e.getActionCommand().equals("Find"))
                {
                       findAgent();
                }
        if(e.getActionCommand().equals("OK"))
        {
               exit();
        }
      }
  }
  
}