package com.iconmaster.srcbox.library;

import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.srcbox.execute.SourceObject;

/**
 *
 * @author iconmaster
 */
public class CoreFunctions {
	public static interface CustomFunction {
		public SourceObject execute();
	}
	
	public static void registerFunctions(SourcePackage pkg) {
		
	}
}
