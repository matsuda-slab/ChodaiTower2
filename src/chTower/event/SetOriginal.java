/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.event;

import chTower.character.Player;

public class SetOriginal {
	private int tempATK;
	private int tempMATK;
	private int tempDEF;
	private int tempMDEF;

	private int tempCRITICAL;

	public void setTemp(Player p) {
		this.tempATK = p.getATK();
		this.tempMATK = p.getMATK();
		this.tempDEF = p.getDEF();
		this.tempMDEF = p.getMDEF();

		this.tempCRITICAL = p.CRITICAL;
	}

	public void getOrig(Player p) {
		p.setATK(this.tempATK);
		p.setMATK(this.tempMATK);
		p.setDEF(this.tempDEF);
		p.setMDEF(this.tempMDEF);

		p.CRITICAL = this.tempCRITICAL;
	}
}
