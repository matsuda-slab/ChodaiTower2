/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Narazaki extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Narazaki() {
    this.setName("楢崎准教授");
    this.setHp(930);
    this.setMp(170);
    this.setATK(700);
    this.setMATK(480);
    this.setDEF(77);
    this.setMDEF(62);
    this.setSpeed(130);

    this.setExp(900);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(5);
    if (action == 0) {
      this.intimidate();
    }
    else if (action == 1) {
      this.bike();
    }
    else {
      this.attack();
    }
  }

  public void intimidate() {
    if (this.getMp() < 10) {
      try {
        System.out.println(this.getName() + "は 威圧感 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 威圧感 を使った！");
        this.setMp(this.getMp() - 10);
        Thread.sleep(500);
        System.out.println("\n\t.....わかりますよね..?（圧力）......考えてください\n");
        Thread.sleep(1000);
        opponent.setATK((int)(opponent.getATK() * 0.9));
        opponent.setMATK((int)(opponent.getMATK() * 0.9));
        System.out.println("\tプレイヤーの 物理攻撃 が下がった！");
        System.out.println("\tプレイヤーの 特殊攻撃 が下がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }
  }

  public void bike() {
    if (this.getMp() < 8) {
      try {
        System.out.println(this.getName() + "は ロードバイク を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は ロードバイク を使った！");
        this.setMp(this.getMp() - 8);
        Thread.sleep(1000);
        System.out.println("\n\t愛用ロードバイクで突進する！\n");
        Thread.sleep(1000);
        damage = this.getATK() * 20 / opponent.getDEF() + this.getATK() / 13 + ran.nextInt(10);
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
