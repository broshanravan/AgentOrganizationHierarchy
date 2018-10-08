

/*
 * Created on 21-Feb-2005
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
public class AgentTeam 
{
	private Agent agent;
	private Team team;
	
	
	public AgentTeam(Agent agentIn ,Team teamIn)
	{
		 agent= agentIn;
		 team= teamIn;
	}
	public void setTeam(Team teamIn)
	{
		team =teamIn;
	}
	
	public void setAgent(Agent agentIn)
	{
		agent=agentIn;
	}
	public Team getTeam()
	{
		return team ;
	}
	
	public Agent getAgent()
	{
		return agent;
	}
	
}

