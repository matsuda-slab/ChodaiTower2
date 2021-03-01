/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Sakai extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Sakai() {
    this.setName("酒井准教授");
    this.setHp(670);
    this.setMp(110);
    this.setATK(370);
    this.setMATK(320);
    this.setDEF(62);
    this.setMDEF(75);
    this.setSpeed(85);

    this.setExp(610);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(5);
    if (action == 0) {
      this.takopa();
    }
    else if (action == 1) {
      this.meteor();
    }
    else {
      this.attack();
    }
  }

  public void takopa() {
    if (this.getMp() < 8) {
      try {
        System.out.println(this.getName() + "は タコパ を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は タコパ を使った！");
        this.setMp(this.getMp() - 8);
        Thread.sleep(500);
        System.out.println("\n\tたこ焼きの熱がダメージを与える！\n");
        Thread.sleep(1000);
        damage = this.getATK() * 9 / opponent.getDEF() + opponent.getFloor() / 3 + opponent.getFloor() / 4;
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t痛恨の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        System.out.println("\t" + opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
        Thread.sleep(1000);
        this.setATK((int)(this.getATK() / 0.9));
        System.out.println("\tさらに" + this.getName() + " の 物理攻撃 が上がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }
  }

  public void meteor() {
    if (this.getMp() < 15) {
      try {
        System.out.println(this.getName() + "は メテオ を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は メテオ を使った！");
        this.setMp(this.getMp() - 15);
        System.out.println("\n\t大量の隕石（たこやき）が降り注ぐ！！");
        System.out.println("\n\t「『ウヒャヒャヒャヒャヒャヒャヒャ！\n");
        damage = this.getMATK() * 18 / opponent.getMDEF() + this.getMATK() / 5;
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t痛恨の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        System.out.println("\t" + opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }	
  }
}
