package com.iconmaster.srcbox.execute;

import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author iconmaster
 */
public class VirtualMachine {
	public HashMap<String,SourceObject> fields = new HashMap<>();
	public SourcePackage pkg;
	public Stack<Executor> execs;

	public VirtualMachine(SourcePackage pkg) {
		this.pkg = pkg;
	}
	
	public Executor exec() {
		return execs.peek();
	}
	
	public void loadFunction(Function fn) {
		execs.push(new FunctionExecutor(this, fn));
	}
	
	public void step() {
		
	}
}
