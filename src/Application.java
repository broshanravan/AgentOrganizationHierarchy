
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
public class Application 
{
	int appId=-1;
	String appCode;
	String appDesc;
	
	
	public Application()
	{
		
	}
	
	//accessors
	
	public int getApplicationId()
	{
		return appId;
	}
	
	public String getApplicationCode()
	{
		return appCode;
	}
	public String getApplicationDesc()
	{
		return appDesc;
	}
	
	//Mutators
	public void setApplicationId(int appIdIn)
	{
		 appId = appIdIn;
	}
	
	public void setApplicationCode(String appCodeIn)
	{
		 appCode = appCodeIn;
	}
	public void setApplicationDesc(String appDescIn)
	{
		 appDesc = appDescIn;
	}
	
	
}
