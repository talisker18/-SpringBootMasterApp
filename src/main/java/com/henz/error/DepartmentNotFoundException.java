package com.henz.error;

public class DepartmentNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DepartmentNotFoundException(String message) {
		super(message);
	}

}
