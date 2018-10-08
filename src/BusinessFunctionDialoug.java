
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BusinessFunctionDialoug extends JDialog
{
  private int height =130;
	private int width=390;
	int departmentId;
	ButtonListener buttonListener = new ButtonListener();
	BusinessFunctionInventory bFInv = new BusinessFunctionInventory ();
	//labels
	JLabel nameLbl = new JLabel("BusinessFunction Name");
	//JLabel teamMngrLbl = new JLabel("Team Manager");
	
	//textFields
	JTextField nameFld = new JTextField();
	//JTextField managerFld = new JTextField();
	
	//Buttons
	
	JButton addBtn = new JButton("Add");	
	JButton exitBtn = new JButton("Close");
  
  public BusinessFunctionDialoug(MainScreen ms)
  {
    super(ms,true);
    setModal(true);
		setSize(width,height);
		setTitle("Budiness Function Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(ms);
		setVisible(true);
  }
  
  public BusinessFunctionDialoug()
  {
    setModal(true);
		setSize(width,height);
		setTitle("Budiness Function Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
		setVisible(true);
  }
  private void setUpScreen()
	{
		//adding Labels
		getContentPane().add(nameLbl);
		
		//adding text Fields
		
		getContentPane().add(nameFld);
		
		//AddingButtons 
		getContentPane().add(addBtn); 
		getContentPane().add(exitBtn );
		
		//Adding Button Listeners
		exitBtn.addActionListener(buttonListener); 
		addBtn.addActionListener(buttonListener); 
		
		//setting Bounds
		nameLbl.setBounds(10,30,140,20);
		nameFld.setBounds(150,30,210,20);
		
		addBtn.setBounds(10,70,100,20);;
		
		exitBtn.setBounds(260,70,100,20) ;
    
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
           addBusinessFunction();
  }

  public void addBusinessFunction()
  {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
    {
      Vector bfVec=bFInv.getAllBusinessFunctionNames();
      
      String bfName=nameFld.getText().trim();
      if(bfName.equalsIgnoreCase(""))
        throw new ParametrizedException("Please Enter Business function Description");
      if(bfVec.contains(bfName))
        throw new ParametrizedException("Business function Description Already Exists"); 
      BusinessFunction bf = new BusinessFunction();
      bf.setBusFunName(nameFld.getText());
      
      bFInv.storeBusiness(bf);
      
      JOptionPane.showMessageDialog(this,"Business Function " +bfName+" Added to the System");
    }
   catch(ParametrizedException e)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      JOptionPane.showMessageDialog(this,e.getMessage());
		}
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
  
  public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Add"))
			{
				addBusinessFunction();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}
}