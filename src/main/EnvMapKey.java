package main;

import com.vividsolutions.jts.geom.Envelope;

class EnvMapKey{
	private Envelope env;
	private int day;
	
	
	public EnvMapKey(Envelope env, int day){
		this.env = env;
		this.day = day;
	}
	public Envelope getEnv() {
		return env;
	}
	public void setEnv(Envelope env) {
		this.env = env;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}

