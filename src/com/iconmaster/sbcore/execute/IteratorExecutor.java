package com.iconmaster.sbcore.execute;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.compile.Operation.OpType;
import com.iconmaster.source.prototype.Iterator;

/**
 *
 * @author iconmaster
 */
public class IteratorExecutor extends FunctionExecutor {
	public Iterator iter;
	public FunctionExecutor creator;

	public IteratorExecutor(VirtualMachine vm, FunctionExecutor creator, Iterator fn, SourceObject... args) {
		super(vm, fn, args);
		
		iter = fn;
		this.creator = creator;
	}

	@Override
	public void step() {
		Operation op = code.get(pc);
		
		if (op.op==OpType.RET) {
			vm.ret();
			vm.loadExecutor(creator);
			creator.done = false;
		} else {
			super.step();
			
			if (pc>=code.size()) {
				creator.pc = creator.blockStack.peek().blockOp;
				creator.done = false;
			}
		}
	}
	
}
