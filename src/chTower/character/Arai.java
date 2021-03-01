/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Arai extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	public Arai() {
		this.setName("荒井助教");
		this.setHp(80);
		this.setMp(36);
		this.setATK(45);
		this.setMATK(14);
		this.setDEF(20);
		this.setMDEF(14);
		this.setSpeed(15);

		this.setExp(60);
		this.setExpOrig(this.getExp());

		this.BOSS = true;
	}

	public void act() {
		if (ran.nextInt(3) == 0) {
			this.timeOver();
		}
		else {
			this.attack();
		}
	}

	public void timeOver() {
		if (this.getMp() < 6) {
			try {
				System.out.println(this.getName() + "は 時間オーバー を使おうとした！");
				Thread.sleep(500);
				System.out.println("しかしMPが足りない！");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				System.out.println(this.getName() + "は 時間オーバー を使った！");
				this.setMp(this.getMp() - 6);
				Thread.sleep(500);
				System.out.println("\n\t時間を過ぎているのに...授業が終わらない...！！\n");
				Thread.sleep(1000);
				damage = this.getATK() * 8 / opponent.getDEF() + this.getATK() / 4 + ran.nextInt(3);
				opponent.setDamage((int)damage);
				System.out.println("\t" + opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
