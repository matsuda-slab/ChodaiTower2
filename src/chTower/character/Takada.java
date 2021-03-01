/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Takada extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	public Takada() {
		this.setName("高田助教");
		this.setHp(45);
		this.setMp(20);
		this.setATK(25);
		this.setMATK(20);
		this.setDEF(10);
		this.setMDEF(10);
		this.setSpeed(5);

		this.setExp(30);
		this.setExpOrig(this.getExp());

		this.BOSS = true;
	}

	public void act() {
		if (ran.nextInt(3) == 0) {
			this.teiri();
		}
		else {
			this.attack();
		}
	}

	public void teiri() {
		if (this.getMp() < 5) {
			try {
				System.out.println(this.getName() + "は 中心極限定理 を唱えようとした！");
				Thread.sleep(500);
				System.out.println("しかしMPが足りない！");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				System.out.println(this.getName() + " は 中心極限定理 を唱えた！");
				this.setMp(this.getMp() - 5);
				Thread.sleep(1000);
				System.out.println("\n\t『...中心極限定理ていうのが...もごもご...』\n");
				Thread.sleep(1000);
				damage = (double)this.getMATK() * 6.5 / opponent.getMDEF() + this.getMATK() / 5 + ran.nextInt(3);
				System.out.println("\t" + opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
				opponent.setDamage((int)damage);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 
}
