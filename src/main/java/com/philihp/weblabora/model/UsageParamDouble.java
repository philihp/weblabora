package com.philihp.weblabora.model;

public class UsageParamDouble extends UsageParam {

	public UsageParamDouble(String in) throws WeblaboraException {
		super(in);
	}

	private UsageParamSingle secondary = null;
	
	public UsageParamSingle getSecondary() {
		if(secondary == null) {
			try {
				secondary = new UsageParamSingle("");
			}
			catch(WeblaboraException e) {
				throw new RuntimeException(e);
			}
		}
		return this.secondary;
	}
	
	public void setSecondary(UsageParamSingle usageParam) {
		this.secondary = usageParam;
	}
	
}
