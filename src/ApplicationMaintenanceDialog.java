
/*
 * Created on 22-Feb-2005
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


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/*
 * Created on 19-Jan-2005
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
public class ApplicationMaintenanceDialog extends JDialog
{
	private static final int hight =150;
	private static final int width =400;
	
	MaintainApplication maintainApplication= new MaintainApplication();
	ButtonListener buttonListener = new ButtonListener();	
	JLabel appLbl = new JLabel("Application Description");
	JLabel codeLbl = new JLabel("Application Code");
	
	JTextField appFld = new JTextField();
	JTextField codeFld = new JTextField();
	
	JButton addBtn = new JButton("Add");
	JButton closeBtn = new JButton("Close");
	
	public ApplicationMaintenanceDialog (MainScreen ms)
	{
		super(ms);
                setSize(width,hight);
		setResizable(false);
		setTitle("Application Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(ms);
		setVisible(true);
	}
	
	private void addAppToDB()
	{
		
    Application app = new Application();
		
    try
    {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String name=appFld.getText();      
        String cs=codeFld.getText();
        
        if (name.equalsIgnoreCase(""))
          throw new ParametrizedException("Please Enter the Application name");
        
        if (cs.equalsIgnoreCase(""))
          throw new ParametrizedException("Please Enter the Applicatioin code");
        
        name = name.trim();
        cs=cs.trim();
        
        Vector appNameList = maintainApplication.getAllApplicationNames();
        if (appNameList.contains(name))
          throw new ParametrizedException("Application name in use, Please Try again");
        
        Vector appCodeList = maintainApplication.getAllApplicationCods();
        Iterator iter = appCodeList.iterator();
        while (iter.hasNext())
        {
          String c = (String)iter.next();
          //System.out.println("App code is "+ c);
        }
        if (appCodeList.contains(cs))
          throw new ParametrizedException("Application code in use, Please Try again");
  
        
        maintainApplication.storeAplication(cs,name);
        appFld.setText("");
        codeFld.setText("");
        
        JOptionPane.showMessageDialog(this,"Application "+name+ " Added");
    }
    catch(ParametrizedException e)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      JOptionPane.showMessageDialog(this,e.getMessage());  
		}
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	

	public void setUpScreen()
	{
		getContentPane().add(appLbl);
		getContentPane().add(appFld);
		
		getContentPane().add(codeLbl);
		getContentPane().add(codeFld);
		
		getContentPane().add(addBtn);
		getContentPane().add(closeBtn);
		
		closeBtn.addActionListener(buttonListener);
		addBtn.addActionListener(buttonListener);
		
		appLbl.setBounds(10,30,200,20);
		appFld.setBounds(150,30,200,20);
		
		codeLbl.setBounds(10,60,200,20);
		codeFld.setBounds(150,60,200,20);
		
		addBtn.setBounds(10,90,100,25);
		closeBtn.setBounds(250,90,100,25);
    
    addBtn.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });
    
    
    appFld.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });

   codeFld.addKeyListener(new java.awt.event.KeyAdapter() 
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
           addAppToDB();
  }

	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
    	{
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
			else if(e.getActionCommand().equals("Add"))
			{  			
				addAppToDB();
			}
    	}
	}
}
	
