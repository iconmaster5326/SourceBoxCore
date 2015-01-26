package com.iconmaster.srcbox.library;

import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.srcbox.execute.SourceObject;
import com.iconmaster.srcbox.execute.VirtualMachine;

/**
 *
 * @author iconmaster
 */
public class CoreFunctions {
	public static interface CustomFunction {
		public SourceObject execute(VirtualMachine vm, SourceObject... args);
	}
	
	public static void registerFunctions(SourcePackage pkg) {
		pkg.getFunction("core.print").data.put("onRun",(CustomFunction) (vm, args) -> {
			System.out.println(args[0].data);
			return null;
		});
	}
}
