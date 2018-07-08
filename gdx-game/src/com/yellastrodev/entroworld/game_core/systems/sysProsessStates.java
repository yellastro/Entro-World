package com.yellastrodev.entroworld.game_core.systems;
import java.util.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class sysProsessStates implements iSystem
{

	private ArrayList mdeleteStack=new ArrayList();
	@Override
	public void deleteNode(Object ds)
	{
		mdeleteStack.add(ds);
	}

	ArrayList<iProcessManager> mStates= new ArrayList<>();

	@Override
	public void start()
	{
		// TODO: Implement this method
	}

	@Override
	public void end()
	{
		// TODO: Implement this method
	}

	@Override
	public void update(float time)
	{
		for(iProcessManager prs:mStates)
			prs.Update(time);
		if(!mdeleteStack.isEmpty())
		{
			mStates.removeAll(mdeleteStack);
			mdeleteStack.clear();
		}
			
	}

	@Override
	public void addNode(Object node)
	{
		mStates.add((iProcessManager)node);
	}
	
}
