package com.yellastrodev.entroworld.game_core.entities.flowers;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public abstract class Flower extends EnEntity
{
	
	public Flower (Engine fcontroller)
	{
		super(fcontroller);
	}

	

	@Override
	public void initComponents(CoupeOfSheets fStore, float fScale)
	{
		StatisticComponent stc=getStatistics();
		addComponent(stc);
		mPositionComp = PositionOnMapComponent.setNewPos();
		addComponent(mPositionComp);
		DisplayComp fdispComp=initDisplayComp(fScale);
		addComponent(fdispComp);
	}
	
	public abstract DisplayComp initDisplayComp(float fScale)
	

	@Override
	public void initProcessManager(CoupeOfSheets fStore, Engine fEngine)
	{
		mProcessManager = new FlowerProcessManager();
	}

	@Override
	public void initLiveCicle()
	{
		// TODO: Implement this method
	}
	
}
