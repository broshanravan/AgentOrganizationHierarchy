
public class Manager 
{
          private int managerId;
          private int employeeId;
          private int t_G_DKey;
          private String identifier;
     
          public Manager()
          {
          }
          public Manager(
                            int managerIdIn,
                            int employeeIdIn, 
                            String identifierIn,
                            int t_G_DKeyIn 
                        )
          {
            managerId=managerIdIn;
            employeeId=employeeIdIn;
            identifier=identifierIn;
            t_G_DKey=t_G_DKeyIn;   
          }
          
          public void setManagerId(int managerIdIn)
          {
            managerId=managerIdIn;
          }
          public int getManagerId()
          {
            return managerId;
          }
          
          public void setEmployeeId(int employeeIdIn)
          {
            employeeId=employeeIdIn;
          }
          
          public int getEmployeeId()
          {
            return employeeId;
          }
          
          
          public void setIdentifier(String identifierIn)
          {
             identifier=identifierIn;
          }
          
          public String getIdentifier()
          {
             return identifier;
          }
            
          public void setT_G_DKey(int t_G_DKeyIn)
          {
            t_G_DKey=t_G_DKeyIn;
          }
          public int getT_G_DKey()
          {
            return t_G_DKey;
          }
  
    
}