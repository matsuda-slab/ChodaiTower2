/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Harasawa extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	public Harasawa() {
		this.setName("原澤助教");
		this.setHp(200);
		this.setMp(45);
		this.setATK(120);
		this.setMATK(130);
		this.setDEF(30);
		this.setMDEF(30);
		this.setSpeed(38);

		this.setExp(160);
		this.setExpOrig(this.getExp());

		this.BOSS = true;
	}

	public void act() {
		if (ran.nextInt(4) == 0) {
			this.yusei();
		}
		else {
			this.attack();
		}
	}

	public void yusei() {
		if (this.getMp() < 6) {
			try {
				System.out.println(this.getName() + "は 油性マーカー を使おうとした！");
				Thread.sleep(500);
				System.out.println("しかしMPが足りない！");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				System.out.println(this.getName() + "は 油性マーカー を使った！");
				this.setMp(this.getMp() - 6);
				Thread.sleep(500);
				System.out.println("\n\t『あれ...？おかしいな.....消えない...！！』\n");
				Thread.sleep(1000);
				damage = this.getMATK() * 8 / opponent.getMDEF() + opponent.getFloor() / 8 + ran.nextInt(5);
				opponent.setDamage((int)damage);
				System.out.println("\t" + opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 

}
