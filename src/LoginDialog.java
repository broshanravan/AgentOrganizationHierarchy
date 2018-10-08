import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class LoginDialog extends JDialog
{

  MainScreen mainScreen;
  public static final int width= 300;
  public static final int hight= 110;
  
  LogIn logIn = new LogIn();
  ButtonListener listener1= new ButtonListener();
  //Labels
  JLabel jlblUserName = new JLabel("UserName");
  JLabel jlblPassword = new JLabel("Password");

  //TextFields
  JTextField jtxtUserName = new JTextField();
  JPasswordField jtxtPassword = new JPasswordField();

  //Buttons
  JButton jbLogin = new JButton("Login");
  JButton jbCancel = new JButton("Cancel");


  private void setupScreen()
  {

      //Adding labels
      getContentPane().add(jlblUserName);
      jlblUserName.setForeground(Color.black);

      getContentPane().add(jlblPassword);
      jlblPassword.setForeground(Color.black);

      //Adding fields
      getContentPane().add(jtxtUserName);
      getContentPane().add(jtxtPassword );

      //Adding buttons
      getContentPane().add(jbLogin);
      getContentPane().add(jbCancel);
      //jbLogin.setEnabled(false);

      //Adding button listeners

      jbLogin.addActionListener(listener1);;
      jbCancel.addActionListener(listener1);;


      //Seting Bounds
      jlblUserName.setBounds(10,10,100,20);
      jtxtUserName.setBounds(120,10,150,20);

      jlblPassword.setBounds(10,40,100,20);
      jtxtPassword.setBounds(120,40,150,20);


      jbLogin.setBounds(30,65,80,15);
      jbCancel.setBounds(170,65,80,15);
      
      jbLogin.addKeyListener(new java.awt.event.KeyAdapter() 
                                                            {
                                                                public void keyTyped(java.awt.event.KeyEvent evt) 
                                                                {
                                                                    keyTyped(evt);
                                                                }
                                                            });
                                                            
      jtxtUserName.addKeyListener(new java.awt.event.KeyAdapter() 
                                                            {
                                                                public void keyTyped(java.awt.event.KeyEvent evt) 
                                                                {
                                                                    KeyTyped(evt);
                                                                }
                                                            });
      
      jtxtPassword.addKeyListener(new java.awt.event.KeyAdapter() 
                                                            {
                                                                public void keyTyped(java.awt.event.KeyEvent evt) 
                                                                {
                                                                    KeyTyped(evt);
                                                                }
                                                            });                                                       
   
  }

    private void KeyTyped(java.awt.event.KeyEvent evt)  {    
            if(evt.getKeyChar() == KeyEvent.VK_ENTER)
             logIn();
    }


  public LoginDialog(MainScreen ms){      
      super(ms,true);
      mainScreen= ms;
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(width,hight);
      setResizable(false);
      setTitle("Login");
      addWindowListener(new WindowDestroyer());
      setResizable(false);
      getContentPane().setLayout(null);
      setupScreen();
      setModal(true);
      jbLogin.setSelected(true);
      setLocationRelativeTo(mainScreen);      
  }  
  
  public void logIn(){  	
      
     try{ 
         String un=jtxtUserName.getText().trim() ;
         String pw=String.valueOf(jtxtPassword.getPassword()); 
         
         if(un.trim().equalsIgnoreCase("")){
             throw new ParametrizedException("Please Enter a Valid Username");
         }else if(pw.trim().equalsIgnoreCase("")){
             throw new ParametrizedException("Please Enter a Valid Password");
         }
         
         System.out.println("Password is "+pw);
      
         if (logIn.logginValid(un,pw)){
              this.setVisible(false);
         }else{
                  jtxtPassword.setText("");
                  throw new ParametrizedException("Invalid Username or Password, Please try Again");
         }
     }catch(ParametrizedException e){
              JOptionPane.showMessageDialog(this,e.getMessage());
     }
           
  }
  
   public class ButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){

          if (e.getActionCommand().equals("Login")) {      	         	
              logIn();              
          }
          else if (e.getActionCommand().equals("Cancel")){
              System.exit(0);
          }
          else{
              System.out.println("error in button display");
          }
      }
  }
	
}


