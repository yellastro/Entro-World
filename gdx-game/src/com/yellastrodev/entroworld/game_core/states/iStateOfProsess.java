package com.yellastrodev.entroworld.game_core.states;

public interface iStateOfProsess
{

	public String getAnimKey();


	public void Pause();

	public void Start();
	
	public void Update(float deltTime);
	
	public void Break();
	
	public void Fin();
}
