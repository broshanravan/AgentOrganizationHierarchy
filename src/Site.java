
/*
 * Created on 15-Feb-2005
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
public class Site
{
	int siteId;
	String siteName;
	int managerId;
	
	public Site()
	{
		
	}
	
	
	public Site(int siteIdIn,String siteNameIn,int managerIdIn)
	{
		siteId = siteIdIn;
		siteName = siteNameIn;
		managerId = managerIdIn;
	}
	//Mutators
	public void setManagerId(int managerIdIn)
	{
		managerId = managerIdIn;
	}
	
	public void setSiteId(int siteIdIn)
	{
		siteId = siteIdIn;
	}
	
	public void setSiteName(String siteNameIn)
	{
		siteName = siteNameIn;
	}
	
	//Accessors
	public int getSiteId()
	{
		return siteId;
	}
	
	public String getSiteName()
	{
		return siteName;
	}
	public int getManagerId()
	{
		return managerId;
	}
	
	public Employee getAutorisedManager()
	{
		Personnel personnel = new Personnel();
		Employee emp = personnel.findEmployeeByEmpId(managerId);
		return emp;
	}
}
