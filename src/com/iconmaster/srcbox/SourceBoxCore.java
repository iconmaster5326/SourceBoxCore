package com.iconmaster.srcbox;

import com.iconmaster.source.compile.CompileUtils;
import com.iconmaster.source.link.Platform;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.srcbox.library.CoreFunctions;
import com.iconmaster.srcbox.library.LibraryCore;

/**
 *
 * @author iconmaster
 */
public class SourceBoxCore {
	public static void registerLibs(Platform plat) {
		SourcePackage lib;
		
		lib = new LibraryCore();
		plat.registerLibrary(lib);
		CoreFunctions.registerFunctions(lib);
		
		plat.transforms.add(CompileUtils.gotoReplacer);
	}
}
