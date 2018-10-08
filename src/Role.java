

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
public class Role 
{
	int roleId;
	String roleName;
	
	public Role (int roleIdIn, String roleNameIn)
	{
		roleId = roleIdIn;
		roleName = roleNameIn;
	}
	
	public Role()
	{
		
	}
	
	//mutators
	
	public void setRoleId(int roleIdIn)
	{
		roleId = roleIdIn;
	}
	
	public void setRoleName(String roleNameIn)
	{
		roleName = roleNameIn;
	}
	
	//accessors
	
	public int getRoleId()
	{
		return roleId;
	}
	
	public String getRoleName()
	{
		return roleName;
	}

}
