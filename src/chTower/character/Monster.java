/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.util.Random;

public class Monster {
	private String name;
	private int hp;
	private int MAX_HP;
	private int mp;
	private int MAX_MP;
	private int atk;
	private int matk;
	private int def;
	private int mdef;
	private int speed;

	private int EXP;
	private int EXP_orig;

	protected Player opponent;

	double damage;
	int CRITICAL = 20;

	boolean BOSS = false;

	Random ran = new Random();

	public void setOpponent(Player p) {
		this.opponent = p;
	}

	public void setDamage(int damage) {
		this.hp -= damage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return this.MAX_HP;
	}
	
	public void setMaxHp(int maxhp) {
		this.MAX_HP = maxhp;
	}

	public int getMp() {
		return this.mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMaxMp() {
		return this.MAX_MP;
	}
	
	public void setMaxMp(int maxmp) {
		this.MAX_MP = maxmp;
	}

	public int getATK() {
		return this.atk;
	}

	public void setATK(int atk) {
		this.atk = atk;
	}

	public int getMATK() {
		return this.matk;
	}

	public void setMATK(int matk) {
		this.matk = matk;
	}

	public int getDEF() {
		return this.def;
	}

	public void setDEF(int def) {
		this.def = def;
	}

	public int getMDEF() {
		return this.mdef;
	}

	public void setMDEF(int mdef) {
		this.mdef = mdef;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getExp() {
		return this.EXP;
	}

	public void setExp(int exp) {
		this.EXP = exp;
	}

	public int getExpOrig() {
		return this.EXP_orig;
	}

	public void setExpOrig(int exp_orig) {
		this.EXP_orig = exp_orig;
	}

	public void act() {
		this.attack();
	}

	public void attack() {
		try {
			System.out.println(this.name + " の攻撃！");
			Thread.sleep(500);
			damage = this.atk * 4 / opponent.getDEF() + ran.nextInt(5);
			if (this.BOSS) {
				damage += opponent.getFloor() / 4;
			}
			if (ran.nextInt(CRITICAL) == 0) {
				System.out.println("\n\t痛恨の一撃！\n");
				damage *= 2.5;
				Thread.sleep(1000);
			}
			opponent.setDamage((int)damage);
			System.out.println("\t" + (int)damage + " のダメージを受けた！");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	// ラスボス専用	
	public void pre_emptive() {
		try {
			System.out.println("\t" + this.getName() + " の先制パンチ！");
			Thread.sleep(500);
			System.out.println("\n\t『やっHu〜い』\n");
			Thread.sleep(500);
			damage = this.getATK() * 5 / opponent.getDEF();
			opponent.setDamage((int)damage);
			System.out.println("\t" + (int)damage + " のダメージを受けた！");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
