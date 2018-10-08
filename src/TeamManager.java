
public class TeamManager 
{
  private int managerId=0;
  private int teamId=0;
  private int employeeId=0;
  
  
  public TeamManager()
  {
  }
  
  public TeamManager(int managerIdIn, int teamIdIn,  int employeeIdIn)
  {  
      managerId=managerIdIn;
      teamId=teamIdIn;
      employeeId=employeeIdIn;
  }
  public void setManagerId(int managerIdIn)
  {
    managerId=managerIdIn;
  }
  public void setTeamId(int teamIdIn)
  {
    teamId=teamIdIn;
  }
  
  public int getManagerId()
  {
    return managerId;
  }
 
  public int getTeamId()
  {
    return teamId;
  }
  
}