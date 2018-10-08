

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

public class AgentStripPanel extends JPanel
{
  private Agent agent;
  protected Action m_action;
  
  // m_clickedPath
  MainScreen ms;
  JTextField userNameFld = new JTextField();
  JTextField applicationFld = new JTextField();
  JTextField roleFld = new JTextField();
  Personnel personnel= new Personnel();
  JRadioButton activateRadio = new JRadioButton("Activate");
  JRadioButton deActivateRadio = new JRadioButton("deactivate");
  
  JCheckBox activeBox = new JCheckBox();
  CheckBoxListener checkBoxListener = new  CheckBoxListener(); 
  BasicBorders.SplitPaneBorder spb = new BasicBorders.SplitPaneBorder(Color.LIGHT_GRAY,Color.LIGHT_GRAY);

  ButtonGroup group1 = new ButtonGroup(); 
   
  public AgentStripPanel(Agent agentIn, MainScreen msIn)
  {
    setLayout(null);
    setSize(700,40);
    setBackground(Color.WHITE);
    agent = agentIn;
    setupScreen();
    pobulateAgentDetails();
    ms=msIn;
    
  }
  
    public AgentStripPanel(Agent agentIn)
    {
      setLayout(null);
      setSize(700,40);
      setBackground(Color.WHITE);
      agent = agentIn;
      setupScreen();
      pobulateAgentDetails();
      
      
    }
  
  public void setupScreen()
  {
        
        this.setBorder(BorderFactory.createEtchedBorder(1));
        add(userNameFld);
        add(applicationFld);
        add(roleFld );
        add(activeBox); 
        //this.setBorder(spb);
        userNameFld.setEditable(false);
        applicationFld.setEditable(false);
        roleFld.setEditable(false);

        userNameFld.setBounds(10,10,100,20);  
        applicationFld.setBounds(140,10,100,20);
        roleFld.setBounds(270,10,100,20);    
        activeBox.setBounds(400,5,20,20);
        
        if(agent.isActive())
        {
          activeBox.setSelected(true);
        }
        
        userNameFld.setBackground(Color.WHITE); 
        applicationFld.setBackground(Color.WHITE); 
        roleFld.setBackground(Color.WHITE);   
        activeBox.setBackground(Color.WHITE); 
        activeBox.addActionListener(checkBoxListener);
        setPopUpMenues();
  }
  public void pobulateAgentDetails()
  {
        int appRoleId = agent.getApplicationRoleId();
        ApplicationRoleMaintenance appRoleMaint = new ApplicationRoleMaintenance();
        AplicationRole appRole =appRoleMaint.getApplicationRole(appRoleId);
        
        int appId = appRole.getApplicationId();
        int roleId = appRole.getRoleId();
        
        MaintainApplication appMaint = new MaintainApplication();
        Application app = appMaint.getApplication(appId);
        
        RoleInventory roleInv = new RoleInventory();
        Role role = roleInv.getRole(roleId);
        
        userNameFld.setText(agent.getUserName());
        applicationFld.setText(app.getApplicationDesc());
        roleFld.setText(role.getRoleName()); 
  }
  
  public Agent getAgent()
  {
    return agent;
  }
  public void setPopUpMenues()
  {
    final JPopupMenu menu = new JPopupMenu();
    JMenuItem item = new JMenuItem("Maintain Agent");
    JMenuItem item1 = new JMenuItem("Relocate Login");
    
    item.addActionListener(new MenuListener());
    item1.addActionListener(new MenuListener());
    
    menu.add(item);
    menu.add(item1);
    
    // Set the component to show the popup menu
    this.addMouseListener(new MouseAdapter()
    {
        public void mousePressed(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
        public void mouseReleased(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    });
    roleFld.addMouseListener(new MouseAdapter()
    {
        public void mousePressed(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
        public void mouseReleased(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    });
    
    userNameFld.addMouseListener(new MouseAdapter()
    {
        public void mousePressed(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
        public void mouseReleased(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    });
    
    applicationFld.addMouseListener(new MouseAdapter()
    {
        public void mousePressed(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
        public void mouseReleased(MouseEvent evt) 
        {
            if (evt.isPopupTrigger()) 
            {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    });
  }
  
  public class CheckBoxListener implements ActionListener
	{
      public void actionPerformed(ActionEvent e)
      {
          if (activeBox.isSelected())
          {
              personnel.activateAgent(agent);
          }
          else if(!(activeBox.isSelected()))
          {
            personnel.deActivateAgent(agent);
          }
      }
  }
  
  
  public class MenuListener implements ActionListener
	{
      public void actionPerformed(ActionEvent e)
      {
          if (e.getActionCommand().equals("Maintain Agent"))
          {
              AgentUpdateDialog AgentUpdateDialog = new AgentUpdateDialog(agent.getId(),ms);		
          }
          
          else if (e.getActionCommand().equals("Relocate Login"))
          {
              System.err.println("USERNAME HERE IS>>>-------------------------->>"+agent.getUserName());
              AgentRelocationScreen agentRelocationScreen = new AgentRelocationScreen(agent.getUserName(),ms);               
          }
          
      }
  }
  
}