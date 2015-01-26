package com.iconmaster.srcbox.execute;

import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public abstract class Executor {
	public HashMap<String,SourceObject> vars = new HashMap<>();
	
	public boolean done = false;
	
	public abstract void step();
}
