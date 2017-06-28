package de.hdm.itprojekt.shared.bo.reports;

import de.hdm.itprojekt.shared.bo.BusinessObjekt;

/**
 * Repot Objekt für Fan in Fan Out
 * @author Jiayi
 *
 */
public class FanInFanOut extends BusinessObjekt {
	private static final long serialVersionUID = 8043483903461054492L;
	
	private String teilnehmerName;
	private int fanIn;
	private int fanOut;
	public FanInFanOut(){}
	public String getTeilnehmerName() {
		return teilnehmerName;
	}
	public void setTeilnehmerName(String teilnehmerName) {
		this.teilnehmerName = teilnehmerName;
	}
	public int getFanIn() {
		return fanIn;
	}
	public void setFanIn(int fanIn) {
		this.fanIn = fanIn;
	}
	public int getFanOut() {
		return fanOut;
	}
	public void setFanOut(int fanOut) {
		this.fanOut = fanOut;
	}
	
	public String getAnalyse() {
		if(this.fanOut == 0)
			return "Zero Fan Out";
		if(this.fanIn == 0)
			return "Zero Fan In";
		return String.valueOf((double) this.fanOut / (double) this.fanIn);
	}
	

}
