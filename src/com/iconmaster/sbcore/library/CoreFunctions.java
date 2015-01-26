package com.iconmaster.sbcore.library;

import com.iconmaster.sbcore.execute.SourceObject;
import com.iconmaster.sbcore.execute.VirtualMachine;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class CoreFunctions {
	public static interface CustomFunction {
		public SourceObject execute(VirtualMachine vm, SourceObject... args);
	}
	
	public static interface CustomIterator {
		public SourceObject[][] execute(VirtualMachine vm, SourceObject... args);
	}
	
	public static void registerFunctions(SourcePackage pkg) {
		pkg.getFunction("core.print").data.put("onRun",(CustomFunction) (vm, args) -> {
			PrintWriter pw = new PrintWriter(vm.outputStream);
			pw.println(args[0].data);
			pw.flush();
			return null;
		});
		
		pkg.getFunction("core.int._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)+((int)args[1].data));
		});
		pkg.getFunction("core.int64._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((long)args[0].data)+((long)args[1].data));
		});
		pkg.getFunction("core.int32._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)+((int)args[1].data));
		});
		pkg.getFunction("core.int16._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((short)args[0].data)+((short)args[1].data));
		});
		pkg.getFunction("core.int8._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((byte)args[0].data)+((byte)args[1].data));
		});
		pkg.getFunction("core.real._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)+((float)args[1].data));
		});
		pkg.getFunction("core.real64._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((double)args[0].data)+((double)args[1].data));
		});
		pkg.getFunction("core.real32._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)+((float)args[1].data));
		});
		
		pkg.getIterator("core.range").data.put("onRun",(CustomIterator) (vm, args) -> {
			ArrayList<SourceObject[]> pairs = new ArrayList<>();
			int begin = ((Number)args[0].data).intValue();
			int end = ((Number)args[1].data).intValue();
			for (int i=begin;i<=end;i++) {
				pairs.add(new SourceObject[] {new SourceObject(TypeDef.INT, i)});
			}
			return pairs.toArray(new SourceObject[0][0]);
		});
	}
}
