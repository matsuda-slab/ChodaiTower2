/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Fujimura extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Fujimura() {
    this.setName("藤村准教授");
    this.setHp(500);
    this.setMp(130);
    this.setATK(280);
    this.setMATK(360);
    this.setDEF(53);
    this.setMDEF(70);
    this.setSpeed(70);

    this.setExp(480);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(6);
    if (action == 0) {
      this.rgb();
    }
    else if (action == 1) {
      this.polygon();
    }
    else if (action == 2) {
      this.luster();
    }
    else {
      this.attack();
    }
  }

  public void polygon() {
    if (this.getMp() < 9) {
      try {
        System.out.println(this.getName() + "は ポリゴンショック を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は ポリゴンショック を使った！");
        this.setMp(this.getMp() - 9);
        Thread.sleep(500);
        System.out.println("\n\t画面からの突然の閃光が、視聴者にダメージを与える！！\n\t(ポリゴンは悪くない)\n");
        Thread.sleep(1000);
        damage = this.getMATK() * 12 / opponent.getMDEF() + this.getMATK() / 15;
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t痛恨の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        System.out.println("\t" + (int)damage + " のダメージを受けた！");
        Thread.sleep(500);
        opponent.setATK((int)(opponent.getATK() * 0.9));
        System.out.println("\tさらに" + opponent.getName() + " の 物理攻撃 が下がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }
  }

  public void luster() {
    if (this.getMp() < 7) {
      try {
        System.out.println(this.getName() + "は ラスタースキャン を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は ラスタースキャン を使った！");
        this.setMp(this.getMp() - 7);
        Thread.sleep(500);
        System.out.println("\n\t眩い光による左から右へのスキャン！\n");
        Thread.sleep(500);
        damage = this.getMATK() * 10 / opponent.getMDEF() + opponent.getFloor() / 2;
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t痛恨の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        System.out.println("\t" + (int)damage + " のダメージを受けた！");
        Thread.sleep(500);
        if (opponent.CRITICAL >= 80) {
          System.out.println("\t" + opponent.getName() + " の会心率はもう下がらない！");
        }
        else {
          opponent.CRITICAL *= 2;
          System.out.println("\tさらに" + opponent.getName() + " の会心率が下がった！");
        }
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }	
  }

  public void rgb() {
    if (this.getMp() < 15) {
      try {
        System.out.println(this.getName() + "は RGB を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は RGB を使った！");
        this.setMp(this.getMp() - 15);
        Thread.sleep(500);
        System.out.println("\n\t3つの原色による多様な光が、" + opponent.getName() + " を弱らせる！\n");
        Thread.sleep(1000);
        opponent.setATK((int)(opponent.getATK() * 0.9));
        System.out.println("\t" + opponent.getName() + " の 物理攻撃 が下がった！");
        Thread.sleep(250);
        opponent.setDEF((int)(opponent.getDEF() * 0.9));
        System.out.println("\t" + opponent.getName() + " の 物理防御 が下がった！");
        Thread.sleep(250);
        opponent.setMATK((int)(opponent.getMATK() * 0.9));
        System.out.println("\t" + opponent.getName() + " の 特殊攻撃 が下がった！");
        Thread.sleep(250);
        opponent.setMDEF((int)(opponent.getMDEF() * 0.9));
        System.out.println("\t" + opponent.getName() + " の 特殊防御 が下がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }	
  }
}
