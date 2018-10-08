
/*
 * Created on 14-Feb-2005
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
public class Agent
{
	private int id;
	private int empId;
	private String  riasEmpNo;
	private int appRoleId;
	boolean isAdmin=false;
	boolean isAuditor=false;
	String password;
	String userName;
  private boolean active = false;
	
	//parametrised Constructor
	public Agent(int idIn,String riasEmpNoIn, int appRoleIdIn)
	{
		id =idIn;
		riasEmpNo = riasEmpNoIn;
		appRoleId = appRoleIdIn;
	}
	
	//desault constructor
	public Agent()
	{
		
	}
	
	//Accessors
	
	public String getRiasEmployeeNumber()
	{
		return riasEmpNo;
	}
	
	public int getId()
	{
		return id;
	}
	
  public int getEmpId()
	{
		return empId;
	}
  public boolean isActive()
  {
    return active;
  }
	
	public int getApplicationRoleId()
	{
		return appRoleId;
	}
	
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}

	public boolean getIsAdmin()
	{
		return isAdmin;
	}
	
  
	public void setActive(boolean activeIn)
	{
		active = activeIn;
	}
  
	public boolean getIsAuditor()
	{
		return isAuditor;
	}
	//Mutarors
	public void setRiasEmployeeNumber(String riasEmpNoIn)
	{
		riasEmpNo = riasEmpNoIn;
	}
	
	public void setId(int idIn)
	{
		 id= idIn;
	}
	
  
	public void setEmpId(int empIdIn)
	{
		empId = empIdIn;
	}
  
	
	public void setApplicationRoleId(int appRoleIn)
	{
		 appRoleId= appRoleIn;
	}

	
	public void setUserName(String userNameIn)
	{
		 userName =userNameIn;
	}
	public void setPassword(String passwordIn)
	{
		 password =passwordIn;
	}

	public void setIsAdmin(boolean isAdminIn)
	{
		isAdmin = isAdminIn;
	}
	
	public void setIsAuditor(boolean isAuditorIn)
	{
		isAuditor = isAuditorIn;;
	}
}
