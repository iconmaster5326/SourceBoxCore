package com.iconmaster.srcbox.execute;

import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.TypeDef;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FunctionExecutor extends Executor {
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
		
		switch (op.op) {
			case MOVN:
				if (op.type==TypeDef.INT8) {
					setVar(op.args[0], new SourceObject(op.type, Byte.parseByte(op.args[1])));
				} else if (op.type==TypeDef.INT16) {
					setVar(op.args[0], new SourceObject(op.type, Short.parseShort(op.args[1])));
				} else if (op.type==TypeDef.INT || op.type==TypeDef.INT32) {
					setVar(op.args[0], new SourceObject(op.type, Integer.parseInt(op.args[1])));
				} else if (op.type==TypeDef.INT64) {
					setVar(op.args[0], new SourceObject(op.type, Long.parseLong(op.args[1])));
				} else if (op.type==TypeDef.REAL || op.type==TypeDef.REAL32) {
					setVar(op.args[0], new SourceObject(op.type, Float.parseFloat(op.args[1])));
				} else if (op.type==TypeDef.REAL64) {
					setVar(op.args[0], new SourceObject(op.type, Double.parseDouble(op.args[1])));
				}
				break;
			case MOVS:
				setVar(op.args[0], new SourceObject(TypeDef.STRING, op.args[1]));
				break;
			case MOV:
				setVar(op.args[0], getVar(op.args[1]));
				break;
		}
		
		pc++;
		if (pc>=code.size()) {
			done = true;
		}
	}
}
