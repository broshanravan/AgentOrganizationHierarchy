import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/*
 * Created on 03-Mar-2005
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
public class UpDateTeamDialog extends JDialog
{
	private int height =210;
	private int width=400;
	int teamId=0;
        Team team;
	ButtonListener buttonListener= new ButtonListener();
	TeamMaintenance teamMaintenance = new TeamMaintenance();
        GroupSubTree groupSubTree;
        MainScreen ms;
  
  CheckBoxListener checkBoxListener = new CheckBoxListener();
  
  JCheckBox indBox = new JCheckBox();
  
  
	//labels
	JLabel nameLbl = new JLabel("Team Name");
        JLabel bFLbl = new JLabel("Business Function");
	JLabel cobLbl = new JLabel("ClassOf Business");  
        JLabel permLbl = new JLabel("Is Induction?");
	
	//textFields
	JTextField nameFld = new JTextField();
	JTextField ansFld = new JTextField();
	
	//Buttons
	//JButton editBtn = new JButton("Edit");
	JButton addBtn = new JButton("Update");
	
	//JButton findBtn = new JButton("Find");
	JButton exitBtn = new JButton("Close");
  
        //Combo box
        JComboBox bFCombo = new JComboBox();
        JComboBox cobCombo = new JComboBox();
  
	
	public UpDateTeamDialog(int teamIdIn,GroupSubTree groupSubTreeIn,MainScreen msIn)
	{
		super(msIn);
                ms=msIn;
                groupSubTree=groupSubTreeIn;
		teamId = teamIdIn;
		setSize(width,height);
		setTitle("Team Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(ms);  
		setVisible(true);
	}

	public void upDateTeam()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String origTeamName=team.getTeamName();
                GroupInventory groupInv= new GroupInventory();
                MaintainDepartment maintainDep = new MaintainDepartment();
                try
                {          
                      if (nameFld.getText().equalsIgnoreCase(""))  
                          throw new ParametrizedException("Please Enter team Name");
                        
                      String function = (String)bFCombo.getSelectedItem();
                      if (function.equalsIgnoreCase("Please Select"))  
                          throw new ParametrizedException("Please Select a Bussiness function");
                       
                       String cobName =  (String)cobCombo.getSelectedItem();  
                      if (cobName.equalsIgnoreCase("Please Select"))  
                          throw new ParametrizedException("Please Select a Class ofBuisness");  
                        
                      String newTeamName = nameFld.getText().trim();                      
                      int groupId=teamMaintenance.getGroupId(teamId);
                      Group g=groupInv.getGroup(groupId);
                      int depId= g.getDepartmentId();                    
                      Department dep = maintainDep.getDepartment(depId);
                      int siteId = dep.getDepartmentSiteId();                      
                      Vector teamNamesVec= teamMaintenance.getAllSiteActiveTeamNames(siteId);
                      
                      if ((!(origTeamName.equalsIgnoreCase(newTeamName)))&&(teamNamesVec.contains(newTeamName)))  
                        throw new ParametrizedException("Team name in use. Please try again");
                                            
                      BusinessFunctionInventory bFInv = new BusinessFunctionInventory();              
                      String bfName =(String) bFCombo.getSelectedItem();              
                      //System.out.println("BF Name going to inventory is: "+ bfName);              
                      BusinessFunction bf = bFInv.getBusinessFunctionByName(bfName);
                      int bfId = bf.getBusFunId();              
                      ClassOfBusinessInventory cobInv = new ClassOfBusinessInventory();              
                      ClassOfBusiness cob = cobInv.getCobByName(cobName);
                      String cobId = cob.getCobId();               
                      cob.show();
                      bf.show();
                      Team updatedTeam = new Team();              
                      updatedTeam.setTeamId(teamId);
                      updatedTeam.setTeamName(newTeamName);
                      updatedTeam.setClassOfBusId(cobId);
                      updatedTeam.setBusneseFunId(bfId);              
                      boolean perm=true;                      
                      if(indBox.isSelected())
                      {
                        perm=false;
                      }                      
                      updatedTeam.setPerm(perm);              
                      teamMaintenance.upDateTeam(updatedTeam);                          
                      clearFields();
                      
                      JOptionPane.showMessageDialog(this, "Team " + newTeamName + " Updated");
                      ms.refreshGroupNode(groupSubTree);
                      setVisible(false);
                }
                catch(ParametrizedException e)
                {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        JOptionPane.showMessageDialog(this,e.getMessage());
                }
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
        public void clearFields()
        {
          nameFld.setText("");
          bFCombo.setSelectedItem("Please Select");
          cobCombo.setSelectedItem("Please Select");
        }
	private void setUpScreen()
	{
              //adding Labels
              getContentPane().add(nameLbl);    
              getContentPane().add(indBox);   
              getContentPane().add(ansFld);     
              getContentPane().add(permLbl);
              //adding text Fields
              getContentPane().add(nameFld);    
              getContentPane().add(bFCombo);
              getContentPane().add(bFLbl);    
              getContentPane().add(cobCombo);
              getContentPane().add(cobLbl);
              bFCombo.setBackground(Color.WHITE);
              cobCombo.setBackground(Color.WHITE);
              ansFld.setEditable(false);
              ansFld.setBackground(Color.WHITE);
              
              //AddingButtons    	 
              getContentPane().add(addBtn); 
              getContentPane().add(exitBtn );
              
              //Adding Button Listeners
              exitBtn.addActionListener(buttonListener); 
              addBtn.addActionListener(buttonListener); 
              
              bFCombo.addItem("Please Select");
              cobCombo.addItem("Please Select");
    
              //setting Bounds
              nameLbl.setBounds(10,30,140,20);
              nameFld.setBounds(150,30,210,20);

              bFLbl.setBounds(10,60,140,20);
              bFCombo.setBounds(150,60,210,20);
              
              cobLbl.setBounds(10,90,140,20);
              cobCombo.setBounds(150,90,210,20);
              
              permLbl.setBounds(10,120,140,20);
              indBox.setBounds(210,120,20,20);
              ansFld.setBounds(150,120,50,20);
    
              addBtn.setBounds(10,150,100,20);;		
              exitBtn.setBounds(260,150,100,20);
    
              pupulateCobCombo();
              pobulateFunctionCombo();   
              indBox.addActionListener(checkBoxListener);
              displayTeam();		
              addBtn.addKeyListener(new java.awt.event.KeyAdapter() 
                                                                    {
                                                                        public void keyTyped(java.awt.event.KeyEvent evt) 
                                                                        {
                                                                            KeyTyped(evt);
                                                                        }
                                                                    });
                   
              nameFld.addKeyListener(new java.awt.event.KeyAdapter() 
                                                            {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });
		
	}
        private void KeyTyped(java.awt.event.KeyEvent evt) 
        {
          //System.out.println("keyTyped");
                if(evt.getKeyChar() == KeyEvent.VK_ENTER)
                 upDateTeam();
        }
        
        public void pupulateCobCombo()
        {
          ClassOfBusinessInventory cobInv = new ClassOfBusinessInventory();
          Vector cobVec = cobInv.getAllCobs();
          Iterator iter = cobVec.iterator();
          while(iter.hasNext())
          {
              ClassOfBusiness cob = (ClassOfBusiness)iter.next();
              String name = cob.getCobName();
              cobCombo.addItem(name);
          } 
        }
  
        public void pobulateFunctionCombo()
        {
          BusinessFunctionInventory bFInv = new BusinessFunctionInventory();
          Vector bFVec = bFInv.getAllBusinessFunctions();
          Iterator iter = bFVec.iterator();
          while(iter.hasNext())
          {
              BusinessFunction bf = (BusinessFunction)iter.next();
              String bFName = bf.getBusFunName();
              bFCombo.addItem(bFName);
          }
          
        }
        public void displayTeam()
        {
          
          team =  teamMaintenance.getTeam(teamId);
          
          int bfId = team.getBusneseFunId();
          //System.out.println("BF Id Id "+ bfId);
          BusinessFunctionInventory bFInv = new BusinessFunctionInventory();
          BusinessFunction bf=bFInv.getBusinessFunctionById(bfId);
          
          String cobId= team.getClassOfBusId();
          //System.out.println("cod Id Id "+ cobId);
          ClassOfBusinessInventory cobInv = new ClassOfBusinessInventory();
          
          ClassOfBusiness cob = cobInv.getCobById(cobId);
          
          
          if (team.isPerm())
          {
            ansFld.setText("No");
            indBox.setSelected(false);
          }
          else
          {
            ansFld.setText("YES");
            indBox.setSelected(true);
          }
          
          nameFld.setText(team.getTeamName()); 
          bFCombo.setSelectedItem(bf.getBusFunName());
          cobCombo.setSelectedItem(cob.getCobName());
          
        }
      
        public class ButtonListener implements ActionListener
        {
                public void actionPerformed(ActionEvent e)
                {
                        if(e.getActionCommand().equals("Update"))
                        {
                                upDateTeam();
                        }
                        if(e.getActionCommand().equals("Close"))
                        {
                                setVisible(false);
                        }
                }
         }	
        
        public class CheckBoxListener implements ActionListener
        {
		public void actionPerformed(ActionEvent e)
		{
			if(indBox.isSelected())
			{
				ansFld.setText("YES");
			}
			else
			{
				ansFld.setText("NO");
			}
		}
	}	
}
