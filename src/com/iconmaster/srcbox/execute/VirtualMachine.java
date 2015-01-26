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
	public Executor last;
	public boolean done = true;

	public VirtualMachine(SourcePackage pkg) {
		this.pkg = pkg;
	}
	
	public Executor exec() {
		return execs.peek();
	}
	
	public void loadFunction(Function fn) {
		execs.push(new FunctionExecutor(this, fn));
		done = false;
	}
	
	public void step() {
		if (!done) {
			exec().step();
			if (exec().done) {
				ret();
			}
		}
	}
	
	public void ret() {
		last = execs.pop();
		if (execs.isEmpty()) {
			done = true;
		}
	}
}
