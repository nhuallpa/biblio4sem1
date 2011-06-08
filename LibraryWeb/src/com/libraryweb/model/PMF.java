package com.libraryweb.model;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {

private static PersistenceManagerFactory pmfInstance;
	
	public static PersistenceManagerFactory get(){
		if(pmfInstance == null){
			pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		}
		return pmfInstance;
	}

    private PMF() {}
	
}
