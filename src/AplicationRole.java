
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
public class AplicationRole 
{
	int roleId;
	int applicationId; 
	int applicationRoleId;
	
	
	public AplicationRole()
	{
		
	}
	
	public AplicationRole(int roleIdIn,int applicationIdIn,int applicationRoleIdIn)
	{
		roleId = roleIdIn;
		applicationId =  applicationIdIn;
		applicationRoleId = applicationRoleIdIn;
	}
	
	
	public void setRoleId(int roleIdIn)
	{
		roleId = roleIdIn;
	}
	
	//mutarors
	public void setApplicationRoleId(int applicationRoleIdIn)
	{
		applicationRoleId = applicationRoleIdIn;
	}
	
	public void setApplicationId(int applicationIdIn)
	{
		applicationId = applicationIdIn;
	}
	
	//Acessors
	public int getRoleId()
	{
		return roleId;
	}
	
	public int getApplicationRoleId()
	{
		return applicationRoleId;
	}
	
	public int getApplicationId()
	{
		return applicationId;
	}
}
