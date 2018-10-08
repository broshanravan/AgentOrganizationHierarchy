
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;
import java.nio.Buffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class ClassOfBusinessDialoug extends JDialog
{
  
  private int height =160;
	private int width=390;
	int departmentId;
  
  ClassOfBusinessInventory  cobInv = new ClassOfBusinessInventory();
	ButtonListener buttonListener = new ButtonListener();
	BusinessFunctionInventory bFInv = new BusinessFunctionInventory ();
	//labels
	JLabel nameLbl = new JLabel("Class of Business Name");
	JLabel codeLbl = new JLabel(" Class of Business Code");
  JLabel writeOffLbl = new JLabel("Write Off Price");
	
	//textFields
	JTextField nameFld = new JTextField();
	JTextField codeFld = new JTextField();
	//JTextField writeOffFld= new JTextField("00.00");
  JFormattedTextField  writeOffFld = new JFormattedTextField(new DecimalFormat("00.00"));
  
	//Buttons
	
	JButton addBtn = new JButton("Add");	
	JButton exitBtn = new JButton("Close");
  
  
  public ClassOfBusinessDialoug(MainScreen ms)
  {
    super(ms,true);
    setModal(true);
    setSize(width,height);
    getContentPane().setLayout(null);
    setTitle("Add Class Of Business");
    setUpScreen();
    setLocationRelativeTo(ms);
    setVisible(true);
    
  }
  
  public ClassOfBusinessDialoug()
  {
    setModal(true);
    setSize(width,height);
    getContentPane().setLayout(null);
    setTitle("Add Class Of Business");
    setUpScreen();
    setVisible(true);
    
  }
  private void setUpScreen()
	{
		//adding Labels
		getContentPane().add(nameLbl);
    getContentPane().add(codeLbl);
    getContentPane().add(writeOffLbl);
    
		
		//adding text Fields
		
		getContentPane().add(nameFld);
    getContentPane().add(codeFld);
    getContentPane().add(writeOffFld);
    
		
		//AddingButtons 
		getContentPane().add(addBtn); 
		getContentPane().add(exitBtn );
		
		//Adding Button Listeners
		exitBtn.addActionListener(buttonListener); 
		addBtn.addActionListener(buttonListener); 
		
		//setting Bounds
		nameLbl.setBounds(10,10,140,20);
		nameFld.setBounds(160,10,200,20);
    
    codeLbl.setBounds(10,40,140,20);
		codeFld.setBounds(160,40,80,20);
    
    writeOffLbl.setBounds(10,70,140,20);
		writeOffFld.setBounds(160,70,80,20);
		
		addBtn.setBounds(10,100,100,20);
		
		exitBtn.setBounds(260,100,100,20) ;
    
    codeFld.setDocument(new JTextFieldLimit(3, true, false));
    writeOffFld.setDocument(new JTextFieldLimit(5, false, true));
 
    addBtn.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped1(evt);
                                                              }
                                                          });
  
		writeOffFld.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  
                                                                  KeyTyped(evt,i );
                                                              }
                                                          });

	}
  int i=0;
  private void KeyTyped1(java.awt.event.KeyEvent evt) 
  {
    //System.out.println("keyTyped");
          if(evt.getKeyChar() == KeyEvent.VK_ENTER)
           addClassOfBusiness();
  }
  
  private void KeyTyped(java.awt.event.KeyEvent evt,int i) 
  {
    //System.out.println("keyTyped");
       
       try
       {
        
          if(!(evt.getKeyChar() == '.'))
          {
            String wr = writeOffFld.getText();
            
            
            i++;
          }
          /*
          if((evt.getKeyChar() ==KeyEvent.VK_BACK_SPACE)&&(""))
          {
            
            i--;
          }
          if((!(evt.getKeyChar() ==KeyEvent.VK_DELETE)&&("")))
          {
            i--;
          }
          */
          if(
              !(evt.getKeyChar() == '1')&&
              !(evt.getKeyChar() == '2')&&
              !(evt.getKeyChar() == '3')&&
              !(evt.getKeyChar() == '4')&&
              !(evt.getKeyChar() == '5')&&
              !(evt.getKeyChar() == '6')&&
              !(evt.getKeyChar() == '7')&&
              !(evt.getKeyChar() == '8')&&
              !(evt.getKeyChar() == '9')&&
              
              !(evt.getKeyChar() ==KeyEvent.VK_BACK_SPACE)&&
              !(evt.getKeyChar() ==KeyEvent.VK_DELETE)&&
              !(evt.getKeyChar() ==KeyEvent.VK_KP_LEFT)&&
              !(evt.getKeyChar() ==KeyEvent.VK_KP_RIGHT)&&
              !(evt.getKeyChar() == '.')
    
            )
                   
            {
                   throw new ParametrizedException("Please enter only numbers in this field");
            }//
            if(i>1)
              throw new ParametrizedException("Not a valid number");
                      
       }
       catch(ParametrizedException e)
      { 
        
        JOptionPane.showMessageDialog(this,e.getMessage());
        
        Selector selector = null;;
              try
              {
                selector = Selector.open();
              }
              catch (IOException e1) 
              {
              }
             
             
              
             Iterator iter = selector.selectedKeys().iterator();
             while(iter.hasNext())
             {
                        SelectionKey selKey = (SelectionKey)iter.next();
                        iter.remove();
                        try 
                        {
                              processSelectionKey(selKey);
                              selKey.cancel();
                        } 
                        catch (IOException e2) 
                        {
                              // Handle error with channel and unregister
                              
                        }
        
                        
             }
       }
        
  }
  
  public void processSelectionKey(SelectionKey selKey) throws IOException 
  {
        // Since the ready operations are cumulative,
        // need to check readiness for each operation
        if (selKey.isValid() && selKey.isConnectable()) 
        {
            // Get channel with connection request
            SocketChannel sChannel = (SocketChannel)selKey.channel();
    
            boolean success = sChannel.finishConnect();
            if (!success) 
            {
                // An error occurred; handle it
    
                // Unregister the channel with this selector
                selKey.cancel();
            }
        }
        
        if (selKey.isValid() && selKey.isReadable()) 
        {
            // Get channel with bytes to read
            SocketChannel sChannel = (SocketChannel)selKey.channel();
    
            // See e174 Reading from a SocketChannel
        }
        
        if (selKey.isValid() && selKey.isWritable()) 
        {
            // Get channel that's ready for more bytes
            SocketChannel sChannel = (SocketChannel)selKey.channel();
    
            // See e175 Writing to a SocketChannel
        }
    }

  
  public void addClassOfBusiness()
  {
   
   this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
   Vector cobNamesVec= cobInv.getAllCobNames();
   Vector cobCodesVec = cobInv.getAllCobCodes();
   String cobName =nameFld.getText().trim() ;
	 String cobCode = codeFld.getText().trim();
	 float writeOff =Float.parseFloat(writeOffFld.getText().trim());
   try
   {
     if(cobName.equalsIgnoreCase(""))
        throw new ParametrizedException("Please Enter Class of Business Description");
     if(cobCode.equalsIgnoreCase(""))
        throw new ParametrizedException("Please Enter class of Business code");  
        
      if(cobNamesVec.contains(cobName))
        throw new ParametrizedException(" Class of Business Description Already Exists, please try again"); 
      if(cobCodesVec.contains(cobCode))
        throw new ParametrizedException("Class of Business code Already Exists, please try again");
     
    ClassOfBusiness cob = new ClassOfBusiness();
    cob.setCobId(cobCode);
    cob.setCobName(cobName);
    cob.setWriteOff(writeOff);
    
    cobInv.StoreClassOfBusiness(cob);
    JOptionPane.showMessageDialog(this,"Class of Business " + cobName + " is added to the database");
   
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
				addClassOfBusiness();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}
}