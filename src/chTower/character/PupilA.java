/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.util.Random;
import java.io.Serializable;

public class PupilA extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	private final int BASE_HP = 10;
	private final int BASE_MP = 10;
	private final int BASE_ATK = 7;
	private final int BASE_MATK = 7;
	private final int BASE_DEF = 5;
	private final int BASE_MDEF = 5;
	private final int BASE_SPEED = 6;

	private final int BASE_EXP = 4;

	Random ran = new Random();

	public PupilA(int floor) {
		this.setName("情報工学生A");
		this.setHp( BASE_HP + (int)(3.0 * floor + ran.nextInt(3)) );
		this.setMp( BASE_MP + ran.nextInt(floor) );
		this.setATK( BASE_ATK + (int)(floor * 4) + floor);
		this.setMATK( BASE_MATK + (int)(floor * 4) + floor);
		this.setDEF( BASE_DEF + (int)(floor * 0.8) );
		this.setMDEF( BASE_MDEF + (int)(floor * 0.8) );
		this.setSpeed( BASE_SPEED + (int)(floor * 0.8) );

		this.setExp( BASE_EXP * floor );
		this.setExpOrig(this.getExp());
	}
}
