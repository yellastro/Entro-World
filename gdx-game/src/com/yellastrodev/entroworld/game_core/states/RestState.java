package com.yellastrodev.entroworld.game_core.states;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class RestState implements iStateOfProsess
{

	iProcessManager mEngine;
	
	public RestState(iProcessManager feng)
	{
		mEngine=feng;
	}
	
	@Override
	public String getAnimKey()
	{
		
		return "stand";
	}


	@Override
	public void Pause()
	{
		// TODO: Implement this method
	}


	@Override
	public void Start()
	{
		mEngine.isSearch=true;
	}


	@Override
	public void Update(float deltTime)
	{
		// TODO: Implement this method
	}

	@Override
	public void Break()
	{
		// TODO: Implement this method
	}

	@Override
	public void Fin()
	{
		// TODO: Implement this method
	}
	
}
