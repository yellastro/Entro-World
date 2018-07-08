package com.yellastrodev.entroworld.game_core.components;

import java.util.*;

public class CoupeOfSheets
{
	public List<AnimationStore> mStores = new ArrayList<>();
	public CoupeOfSheets(AnimationStore fStore)
	{
		mStores.add(fStore);
	}
	public CoupeOfSheets(List<AnimationStore> fStores)
	{
		mStores = fStores;
	}
}
