/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.util.Random;
import java.io.Serializable;

public class PupilC extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	private final int BASE_HP = 13;
	private final int BASE_MP = 12;
	private final int BASE_ATK = 10;
	private final int BASE_MATK = 10;
	private final int BASE_DEF = 9;
	private final int BASE_MDEF = 9;
	private final int BASE_SPEED = 9;

	private final int BASE_EXP = 5;

	Random ran = new Random();

	public PupilC(int floor) {
		this.setName("情報工学生C");
		this.setHp( BASE_HP + (int)(3.3 * floor + ran.nextInt(3)) );
		this.setMp( BASE_MP + ran.nextInt(floor) );
		this.setATK( BASE_ATK + (int)(floor * 4.6) + floor);
		this.setMATK( BASE_MATK + (int)(floor * 4.6) + floor);
		this.setDEF( BASE_DEF + (int)(floor * 0.9) );
		this.setMDEF( BASE_MDEF + (int)(floor * 0.9) );
		this.setSpeed( BASE_SPEED + (int)(floor * 0.9) );

		this.setExp( BASE_EXP * floor );
		this.setExpOrig(this.getExp());
	}
}
