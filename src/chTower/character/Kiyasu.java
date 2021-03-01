/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Kiyasu extends Monster implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	public Kiyasu() {
		this.setName("喜安教授");
		this.setHp(260);
		this.setMaxHp(260);
		this.setMp(60);
		this.setMaxMp(60);
		this.setATK(180);
		this.setMATK(160);
		this.setDEF(40);
		this.setMDEF(45);
		this.setSpeed(43);

		this.setExp(210);
		this.setExpOrig(this.getExp());

		this.BOSS = true;
	}

	public void act() {
		if (ran.nextInt(4) == 0) {
			this.summonFujii();
		}
		else {
			this.attack();
		}
	}

	public void summonFujii() {
		if (this.getMp() < 8) {
			try {
				System.out.println(this.getName() + "は 藤井さん召喚 を使おうとした！");
				Thread.sleep(500);
				System.out.println("しかしMPが足りない！");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				System.out.println(this.getName() + "は 藤井さん召喚 を使った！");
				this.setMp(this.getMp() - 8);
				Thread.sleep(500);
				System.out.println("\n\tいつでもみなさんの力になります^^\n");
				Thread.sleep(1000);
				damage = this.getMATK() * 3 / opponent.getMDEF();
				if (damage > opponent.getMp()) {
					damage = opponent.getMp();
				}
				opponent.setMp(opponent.getMp() - (int)damage);
				this.setMp((int)(this.getMp() + damage));
				if (this.getMp() > this.getMaxMp()) {
					this.setMp(this.getMaxMp());
				}
				System.out.println("\tMPを " + (int)damage + " 吸い取られた！");
				System.out.println("\t" + this.getName() + " のMPが回復した！\n");
				Thread.sleep(1000);

				this.setDEF((int)(this.getDEF() / 0.9));
				System.out.println("\t" + this.getName() + " の防御が上がった！");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();	
			}

		}
	}
}
