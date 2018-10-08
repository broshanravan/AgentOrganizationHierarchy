import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AgentRelocationScreen extends JDialog {
    Personnel personnel= new Personnel();
    Employee emp= new Employee();
    private String userName ;
    Agent agent;
    ButtonListener buttonListener = new ButtonListener();
    
    
    JLabel userNameLbl= new JLabel("Username");
    JLabel eurEmpLbl= new JLabel("Current Owner");
    
    JLabel expLbl= new JLabel("Enter Employee Number of the New owner and Click on Search Button");
    JLabel empNumLbl= new JLabel("New Owner Name");
    
    JTextField userNameFld = new JTextField();
    JTextField curEmpFld = new JTextField();    
    
    JTextField empNumFld = new JTextField();
    JTextField newEmpFld = new JTextField();
    
    JButton searchBtn= new JButton("Search");
    JButton assignBtn= new JButton("Re-Assign");
    JButton cancelBtn= new JButton("Cancel");
    
    
    public AgentRelocationScreen(String userNameIn,MainScreen ms) {
        super(ms,true);
        this.setModal(true);
        userName=userNameIn;  
        setTitle("Agent Re-Location screen");        
        setLayout(null);
        setResizable(false);
        setupScreen();
        setSize(405,260);
        setVisible(true);
        setLocationRelativeTo(ms);
        this.setModal(true);
             
    }
    
   
    
    public AgentRelocationScreen() {
        setTitle("Agent Re-Location screen");
        setLayout(null);
        setResizable(false);
        setupScreen();
        setSize(405,260);
        setVisible(true);
    }
    
    private void setupScreen(){
       getContentPane().add(userNameLbl);
       getContentPane().add(eurEmpLbl);
       getContentPane().add(expLbl);
       getContentPane().add(empNumLbl);
       getContentPane().add(userNameFld);
       getContentPane().add(curEmpFld);
       getContentPane().add(empNumFld);
       getContentPane().add(newEmpFld);
       getContentPane().add(searchBtn);
       getContentPane().add(assignBtn);
       getContentPane().add(cancelBtn);
       
       userNameLbl.setBounds(10,10,100,20);
       eurEmpLbl.setBounds(10,40,100,20);       
       expLbl.setBounds(0,90,400,20);
       empNumLbl.setBounds(10,160,150,20);
       
       
       userNameFld.setBounds(130,10,200,20);
       curEmpFld.setBounds(130,40,200,20);
       empNumFld.setBounds(130,130,100,20);
       newEmpFld.setBounds(130,160,200,20);
       
       searchBtn.setBounds(250,130,80,20);
        
       cancelBtn.setBounds(20,200,95,22);
       assignBtn.setBounds(250,200,95,22);
       
       searchBtn.addActionListener(buttonListener);
       cancelBtn.addActionListener(buttonListener);
       assignBtn.addActionListener(buttonListener);
       
       findAgent(userName);
       
       userNameFld.setEditable(false);
       curEmpFld.setEditable(false);
       newEmpFld.setEditable(false);
       userNameFld.setBackground(Color.WHITE);
       curEmpFld.setBackground(Color.WHITE);
       newEmpFld.setBackground(Color.WHITE);
    }
    
    
    private void findAgent(String userName) {
        agent= personnel.getAgentByUserName(userName);
        int empId= agent.getEmpId();
        String name = personnel.getEmployeeName(empId);
        userNameFld.setText(userName);
        curEmpFld.setText(name);
        
    }
    
    private void findEmployee() {
        String riasrmpno=empNumFld.getText();
        if(riasrmpno==null|| riasrmpno.trim().equalsIgnoreCase("")) {
            
        }else {
            try
            {
                emp=personnel.findEmployeeByRiasEmpNo(riasrmpno);
                if(emp.getEmployeeId()!=0)
                {
                String name = emp.getForName()+ " "+emp.getSurName();
                newEmpFld.setText(name);
                } else {
                    throw new ParametrizedException("Invalid RIAS Employee Number, Please try again");
                }
            }
            catch (ParametrizedException e) {
                JOptionPane.showMessageDialog(this,e.getMessage());
            }
        } 
              
    }
    
    private void reAsign() {
        try{
        
        if(emp.getEmployeeId()==0) {
        
            throw new ParametrizedException("First Select a valid employee");
            
        }else {
            personnel.relocateAgent(agent.getId(),emp.getEmployeeId());            
            JOptionPane.showMessageDialog(this,"User Name "+ agent.getUserName()+ " Have been Relocated to "+ emp.getForName()+ " "+ emp.getSurName());
            setVisible(false);
        }
        }catch(ParametrizedException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    private void exit(){
        this.setVisible(false);
    }
    public class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Search")) {     
                findEmployee();
            } else if(e.getActionCommand().equals("Re-Assign")) {
                reAsign();  
            } else if(e.getActionCommand().equals("Cancel")) {
                exit();
            }    
        }
    }
    
    public static void main(String args[]){
       AgentRelocationScreen AgentRelocationScreen= new AgentRelocationScreen();
    
    
    }
}
