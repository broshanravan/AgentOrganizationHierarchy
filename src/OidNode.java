
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
public class OidNode
{
	protected int m_id;
	protected String m_name;
	public OidNode(int id,String name)
	{
		m_id =id;
		m_name = name;
	}
	public int getId()
	{
		return m_id;
	}
	
	public String getName()
	{
		return m_name;
	}
	
	public String toString()
	{
		return m_name;
	}
}
