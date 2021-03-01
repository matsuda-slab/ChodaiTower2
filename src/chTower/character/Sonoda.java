/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Sonoda extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	public Sonoda() {
		this.setName("園田助教");
		this.setHp(130);
		this.setMp(30);
		this.setATK(150);
		this.setMATK(50);
		this.setDEF(25);
		this.setMDEF(25);
		this.setSpeed(20);

		this.setExp(100);
		this.setExpOrig(this.getExp());

		this.BOSS = true;
	}
}
