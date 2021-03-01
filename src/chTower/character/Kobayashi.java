/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Kobayashi extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Kobayashi() {
    this.setName("小林透教授");
    this.setHp(770);
    this.setMp(150);
    this.setATK(560);
    this.setMATK(500);
    this.setDEF(64);
    this.setMDEF(70);
    this.setSpeed(83);

    this.setExp(750);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(5);
    if (action == 0) {
      this.moneyPower();
    }
    else if (action == 1) {
      this.elite();
    }
    else {
      this.attack();
    }
  }

  public void moneyPower() {
    if (this.getMp() < 16) {
      try {
        System.out.println(this.getName() + "は カネのちから を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は カネのちから を使った！");
        this.setMp(this.getMp() - 16);
        Thread.sleep(500);
        System.out.println("\n\t『 $カネ だよ $カネぇぇぇ！！\n");
        Thread.sleep(500);
        damage = this.getATK() * 15 / opponent.getDEF() + this.getATK() / 8 + ran.nextInt(6);
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

  public void elite() {
    if (this.getMp() < 10) {
      try {
        System.out.println(this.getName() + "は エリートだぜっ を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は エリートだぜっ を使った！");
        this.setMp(this.getMp() - 15);
        Thread.sleep(500);
        System.out.println("\n\t『まあ、私はエリートだったので...（ドヤァ...）\n");
        this.setATK((int)(this.getATK() / 0.9));
        this.setDEF((int)(this.getDEF() / 0.9));
        Thread.sleep(1000);
        System.out.println(this.getName() + "の 物理攻撃 が上がった！");
        Thread.sleep(500);
        System.out.println(this.getName() + "の 物理防御 が上がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }	
  }
}
