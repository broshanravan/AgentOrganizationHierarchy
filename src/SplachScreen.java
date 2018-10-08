

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import javax.swing.border.LineBorder;

public class SplachScreen extends JDialog {    
    final static int interval = 1300;
    Timer timer;
    JProgressBar pb;
    int i;    
    ImageIcon aohIcn = new ImageIcon(getClass().getResource("Icons/aoh3.png"));
    ImageIcon riasIcn = new ImageIcon(getClass().getResource("Icons/RiasLogo1.gif"));
    ImageIcon t1 = new ImageIcon(getClass().getResource("Icons/team.png"));
    ImageIcon t2 = new ImageIcon(getClass().getResource("Icons/team.png"));
    ImageIcon t3 = new ImageIcon(getClass().getResource("Icons/team.png"));
    ImageIcon birIcn = new ImageIcon(getClass().getResource("Icons/bir_logo6.gif"));
    ImageIcon perfIcn = new ImageIcon(getClass().getResource("Icons/perform.png"));    
    private static final int hight = 250;
    private static final int width = 450;
    JLabel aohLbl = new JLabel();
    JLabel t3Lbl = new JLabel();
    JLabel t1Lbl = new JLabel();
    JLabel t2Lbl = new JLabel();
    JLabel riasLbl = new JLabel();
    JLabel birLbl = new JLabel();
    JLabel perfLbl = new JLabel();    
    JLabel titleLbl = new JLabel("Agent Organization Hierarchy");
    JLabel verLbl = new JLabel("Version 8.0.0");
    public SplachScreen() {    
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(width, hight);
        setResizable(false);
        setUpScreen();
        setLocationRelativeTo(null);
        this.setVisible(true);        
        timer.start();
    }
    
    public void setUpScreen(){
        getContentPane().setBackground(new Color(255,255,242));
        getRootPane().setBorder(new LineBorder(Color.BLACK));
        //setBackground(Color.white);
        getContentPane().add(titleLbl);
        getContentPane().add(verLbl);
        titleLbl.setFont(new Font("Times new roman",1,28));
        verLbl.setFont(new Font("Times new roman",1,14));
        titleLbl.setForeground(Color.BLUE);
        verLbl.setForeground(Color.BLUE);
        titleLbl.setBounds(70,70,440,30);
        verLbl.setBounds(210,100,100,25);
        t1Lbl.setIcon(t1);
        t2Lbl.setIcon(t2);
        t3Lbl.setIcon(t3);
        
        riasLbl.setIcon(riasIcn);
        aohLbl.setIcon(aohIcn);
        birLbl.setIcon(birIcn);
        perfLbl.setIcon(perfIcn);
        
        getContentPane().add(t1Lbl); 
        getContentPane().add(t2Lbl);
        getContentPane().add(t3Lbl);
        getContentPane().add(birLbl);
        getContentPane().add(perfLbl);        
        
        getContentPane().add(aohLbl);
        getContentPane().add(riasLbl);
       
        
        aohLbl.setBounds(5,27,250,200);        
        t1Lbl.setBounds(9,208,20,20); 
        t2Lbl.setBounds(27,216,20,20); 
        t3Lbl.setBounds(45,208,20,20);
        
        //birLbl.setBounds(10,35,250,200);
        
        riasLbl.setBounds(180,10,250,40);
        birLbl.setBounds(365,195,150,50);
        perfLbl.setBounds(80,50,350,180);
        
        //JPanel jp_status = new JPanel();
        JPanel mSpan = new JPanel();
        mSpan.setLayout(new BorderLayout());        
        
        pb=new JProgressBar(0, 20);
        getContentPane().add(pb);
        pb.setValue(0);
        pb.setStringPainted(true);
        
        pb.setBounds(90,150,330,10);
        timer = new Timer(interval, new ActionListener() {
                  public void actionPerformed(ActionEvent evt) {
              
              if (i == 20){
                Toolkit.getDefaultToolkit().beep();
                timer.stop();
                pb.setValue(0);
                
              }
              i = i + 1;
                      pb.setValue(i);
                  }
              });
        this.setUndecorated(true);
        
    }  
    
    public void loadMainScreen(){
        MainScreen mainScreen = new MainScreen(this);
    }

    public static void main(String[] args) {
        SplachScreen splachScreen = new SplachScreen();
        splachScreen.loadMainScreen();
    }
}
