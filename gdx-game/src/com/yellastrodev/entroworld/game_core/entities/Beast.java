package com.yellastrodev.entroworld.game_core.entities;

import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public abstract class Beast extends Mob
{
	public BeastStatisticComponent mBeastStatisticks;
	
	public Beast(Engine fcontroller)
	{
		super(fcontroller);
		
	}

	@Override
	public void initLiveCicle()
	{
		mProcessManager.setLifeManager(
			new BeastLiveCycle((MobProcessManager)mProcessManager,this));
	}

}
