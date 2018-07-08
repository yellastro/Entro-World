package com.yellastrodev.entroworld;
import java.util.*;
import android.renderscript.*;

public class TypedList<E extends java.lang.Object> extends
		ArrayList<E>
{

	
	public E get(Class type)
	{
		for(E tt:this)
			if(tt.getClass()==type)
				return tt;
		return null;
	}
	
}
