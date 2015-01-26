package com.iconmaster.srcbox.execute;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.prototype.Function;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FunctionExecutor extends Executor {
	public VirtualMachine vm;
	public Function fn;
	
	public SourceObject returns;
	public int pc = 0;
	public ArrayList<Operation> code;

	public FunctionExecutor(VirtualMachine vm, Function fn) {
		this.vm = vm;
		this.fn = fn;
		this.code = fn.getCode();
	}

	@Override
	public void step() {
		Operation op = code.get(pc);
		
		
		
		pc++;
		if (pc>=code.size()) {
			done = true;
		}
	}
}
