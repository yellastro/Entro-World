package com.yellastrodev.entroworld.game_core.states.action_states;

import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class BulletFly extends MoveState
{
	public BulletFly(iProcessManager fManag,StatisticComponent fStatic
					 ,PositionComp fCurr, PositionComp fTarget,
					 VelocityComp fVelos)
	{
		super(fManag,fStatic,fCurr,fTarget,fVelos,true);
	}

	@Override
	public String getAnimKey()
	{
		return "fly";
	}
	
	
}
