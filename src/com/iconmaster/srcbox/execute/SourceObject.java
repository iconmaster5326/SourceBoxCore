package com.iconmaster.srcbox.execute;

import com.iconmaster.source.compile.DataType;

/**
 *
 * @author iconmaster
 */
public class SourceObject {
	public DataType type;
	public Object data;

	public SourceObject(DataType type, Object data) {
		this.type = type;
		this.data = data;
	}
}
