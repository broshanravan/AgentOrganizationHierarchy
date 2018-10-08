
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.Vector;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/*
 * Created on 17-Mar-2005
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
public class DepartmentUpdateScreen extends JDialog
{
	private int height =130;
	private int width=350;
	int departmentId;
        MainScreen ms;
	ButtonListener buttonListener = new ButtonListener();
        MaintainDepartment maintainDepartment = new MaintainDepartment();
	//labels
	JLabel nameLbl = new JLabel("New Name");
	//JLabel teamMngrLbl = new JLabel("Team Manager");
	
	//textFields
	JTextField nameFld = new JTextField();
	//JTextField managerFld = new JTextField();
	
	//Buttons
	
	JButton upDateBtn = new JButton("Update");	
	JButton exitBtn = new JButton("Close");
	
	public DepartmentUpdateScreen(int depIdIn, MainScreen msIn)
	{
		super(msIn);
                ms=msIn;
		departmentId = depIdIn;
		setSize(width,height);
		setTitle("Department Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setResizable(false);
                setLocationRelativeTo(ms);
		setVisible(true);
	}
	public void clearFields()
	{
		nameFld.setText("");
	}
	public void renameDepartment()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
		int siteId = maintainDepartment.getSiteId(departmentId);
		Vector namesVec=maintainDepartment.getAllCurrentDepartmentNames(siteId);
		try
		{
			if(nameFld.getText().equalsIgnoreCase(""))
				throw new ParametrizedException("Please enter department name");
			
			String departmentName = nameFld.getText();
			if(namesVec.contains(departmentName))
				throw new ParametrizedException("Department name in use Please re-enter");
			
                        maintainDepartment.renameDepartment(departmentId,departmentName);
			namesVec.addElement(departmentName);
			JOptionPane.showMessageDialog(this,"Department "+ departmentName + " is renamed");
                        ms.updateDepartmentNode(departmentId,siteId);
			setVisible(false);      
		}
		catch(ParametrizedException e){
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        JOptionPane.showMessageDialog(this,e.getMessage());
                }
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void setUpScreen()
	{
		//adding Labels
		getContentPane().add(nameLbl);
		
		//adding text Fields
		
		getContentPane().add(nameFld);
		
		//AddingButtons 
		getContentPane().add(upDateBtn); 
		getContentPane().add(exitBtn );
		
		//Adding Button Listeners
		exitBtn.addActionListener(buttonListener); 
		upDateBtn.addActionListener(buttonListener); 
		
		//setting Bounds
		nameLbl.setBounds(10,30,100,20);
		nameFld.setBounds(110,30,210,20);
		
		upDateBtn.setBounds(10,70,100,20);;
		
		exitBtn.setBounds(220,70,100,20) ;
                
                Department dep=maintainDepartment.getDepartment(departmentId);
                nameFld.setText(dep.getDepartmentName());
                
                nameFld.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });
                upDateBtn.addKeyListener(new java.awt.event.KeyAdapter() 
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
                renameDepartment();
        }
        
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Update"))
			{
				renameDepartment();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}	
}

