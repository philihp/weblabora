package com.philihp.weblabora.model;

public class UsageParamDouble extends UsageParam {

	public UsageParamDouble(String in) throws WeblaboraException {
		super(in);
	}

	private UsageParam secondary = null;
	
	public UsageParam getSecondary() {
		if(secondary == null) {
			try {
				secondary = new UsageParam("");
			}
			catch(WeblaboraException e) {
				throw new RuntimeException(e);
			}
		}
		return this.secondary;
	}
	
	protected void setSecondary(UsageParam usageParam) {
		this.secondary = usageParam;
	}
	
}
