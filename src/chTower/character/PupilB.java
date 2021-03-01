/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.util.Random;
import java.io.Serializable;

public class PupilB extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	private final int BASE_HP = 10;
	private final int BASE_MP = 10;
	private final int BASE_ATK = 5;
	private final int BASE_MATK = 5;
	private final int BASE_DEF = 3;
	private final int BASE_MDEF = 3;
	private final int BASE_SPEED = 4;

	private final int BASE_EXP = 3;

	Random ran = new Random();

	public PupilB(int floor) {
		this.setName("情報工学生B");
		this.setHp( BASE_HP + (int)(2.8 * floor + ran.nextInt(3)) );
		this.setMp( BASE_MP + ran.nextInt(floor) );
		this.setATK( BASE_ATK + (int)(floor * 3.6) + floor);
		this.setMATK( BASE_MATK + (int)(floor * 3.6) + floor);
		this.setDEF( BASE_DEF + (int)(floor * 0.6) );
		this.setMDEF( BASE_MDEF + (int)(floor * 0.6) );
		this.setSpeed( BASE_SPEED + (int)(floor * 0.6) );

		this.setExp( BASE_EXP * floor );
		this.setExpOrig(this.getExp());
	}
}
