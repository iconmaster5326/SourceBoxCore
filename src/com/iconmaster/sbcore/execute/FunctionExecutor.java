package com.iconmaster.sbcore.execute;

import com.iconmaster.sbcore.library.CoreFunctions.CustomFunction;
import com.iconmaster.source.compile.Operation;
import com.iconmaster.source.prototype.Field;
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
	public boolean inCall = false;
	public SourceObject[] args;

	public FunctionExecutor(VirtualMachine vm, Function fn, SourceObject... args) {
		this.vm = vm;
		this.fn = fn;
		this.code = fn.getCode();
		this.args = args;
		
		for (int i = 0;i<args.length;i++) {
			Field arg = fn.getArguments().get(i);
			setVar(arg.getName(), args[i]);
		}
	}

	@Override
	public void step() {
		Operation op = code.get(pc);
		
		if (inCall) {
			setVar(op.args[0], ((FunctionExecutor)vm.last).returns);
			
			inCall = false;
			incPC();
			return;
		}
		
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
			case CALL:
				String fnName = op.args[1];
				Function fn = vm.pkg.getFunction(fnName);
				ArrayList<SourceObject> a = new ArrayList<>();
				for (int i=2;i<op.args.length;i++) {
					a.add(getVar(op.args[i]));
				}
				SourceObject[] aa = a.toArray(new SourceObject[0]);
				if (fn.data.containsKey("onRun")) {
					setVar(op.args[0], ((CustomFunction)fn.data.get("onRun")).execute(vm, aa));
				} else {
					vm.loadFunction(fn, aa);
					inCall = true;
					return;
				}
			case RET:
				done = true;
				if (op.args.length>0) {
					returns = getVar(op.args[0]);
				} else {
					returns = null;
				}
				return;
			case DO:
				break;
		}
		
		incPC();
	}

	public void incPC() {
		pc++;
		if (pc>=code.size()) {
			done = true;
		}
	}
}
