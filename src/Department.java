
import java.util.Vector;

/*
 * Created on 16-Mar-2005
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
public class Department 
{
	private String name;
	private int id;
	private int siteId;
	private int managerId;
	
	
	public void setDepartmentName(String nameIn)
	{
		name = nameIn;
	}
	
	public void setDepartmentId(int idIn)
	{
		id = idIn;
	}
	
	public void setManagerId(int manIdIn)
	{
		managerId = manIdIn;
	}
	
	public void setDepartmentSiteId(int siteIdIn)
	{
		siteId = siteIdIn;
	}
	
	public String getDepartmentName()
	{
		return name ;
	}
	
	public int getDepartmentId()
	{
		return id ;
	}
	
	public int getDepartmentSiteId()
	{
		return siteId ;
	}
	
	public int getManagerId()
	{
		return managerId;
	}
	
	public Vector getAutorisedManagers()
	{
		Vector managersVec = new Vector();
		int siteId =  getDepartmentSiteId();
		MaintainSites maintainSites = new MaintainSites();
		Site site = maintainSites.getSite(siteId);
		Employee siteManager = site.getAutorisedManager();
		Personnel personnel = new Personnel();
		Employee DepartmentManager = personnel.findEmployeeByEmpId(managerId);
		if(!(siteManager==null))
		{
			managersVec.addElement(siteManager);	
		}
		
		if(!(DepartmentManager==null))
		{
			managersVec.addElement(siteManager);
		}
		return managersVec;
		
	}
	
}
