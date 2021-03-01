/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Matsunaga extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Matsunaga() {
    this.setName("松永教授");
    this.setHp(410);
    this.setMp(100);
    this.setATK(260);
    this.setMATK(240);
    this.setDEF(50);
    this.setMDEF(60);
    this.setSpeed(55);

    this.setExp(330);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(5);
    if (action == 0) {
      this.heavySound();
    }
    else if (action == 1) {
      this.summonYamashita();
    }
    else {
      this.attack();
    }
  }

  public void summonYamashita() {
    if (this.getMp() < 8) {
      try {
        System.out.println(this.getName() + "は 山下さん召喚 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 山下さん召喚 を使った！");
        this.setMp(this.getMp() - 8);
        Thread.sleep(500);
        System.out.println("\n\t『演習返しまぁすぅ』\n");
        Thread.sleep(1000);
        System.out.println("\n\t演習用紙が乱れ飛んでくる！\n");
        Thread.sleep(1000);
        damage = this.getATK() * 9 / opponent.getDEF() + opponent.getFloor() / 5;
        opponent.setDamage((int)damage);
        System.out.println("\t" + opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
        this.setDEF((int)(this.getDEF() / 0.9));
        Thread.sleep(500);
        System.out.println("\tさらに " + this.getName() + " の物理防御が上がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }

    }
  }

  public void heavySound() {
    if (this.getMp() < 12) {
      try {
        System.out.println(this.getName() + "は 爆音波 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 爆音波 を使った！");
        this.setMp(this.getMp() - 12);
        Thread.sleep(500);
        System.out.println("\n\t音響装置を使って凄まじい爆音を出している！\n");
        Thread.sleep(1000);
        System.out.println("\n\t『これが音響工学の力です。』\n");
        Thread.sleep(500);
        damage = this.getMATK() * 15 / opponent.getMDEF() + this.getMATK() / 8 + ran.nextInt(7);
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
