/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Shibata extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Shibata() {
    this.setName("柴田教授");
    this.setHp(1000);
    this.setMp(220);
    this.setATK(650);
    this.setMATK(720);
    this.setDEF(82);
    this.setMDEF(95);
    this.setSpeed(96);

    this.setExp(1400);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(8);
    if (action == 0) {
      this.fpga();
    }
    else if (action == 1) {
      this.summonMatsuo();
    }
    else if (action == 2) {
      this.speedUp();
    }
    else if (action == 3) {
      this.getBest();
    }
    else {
      this.attack();
    }
  }

  public void fpga() {
    if (this.getMp() < 12) {
      try {
        System.out.println(this.getName() + "は FPGA を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は FPGA を使った！");
        this.setMp(this.getMp() - 12);
        Thread.sleep(500);
        System.out.println("\n\tFPGAボードを使ったハードな一撃！\n");
        Thread.sleep(1000);
        damage = this.getATK() * 25 / opponent.getDEF() + opponent.getFloor() / 2 + opponent.getFloor() / 5 + ran.nextInt(10);
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

  public void summonMatsuo() {
    if (this.getMp() < 16) {
      try {
        System.out.println(this.getName() + "は 松尾さん召喚 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 松尾さん召喚 を使った！");
        this.setMp(this.getMp() - 16);
        Thread.sleep(1000);
        System.out.println("\n\t『アニキ、やっちゃってください』\n");
        Thread.sleep(1000);
        damage = this.getMATK() * 7 / opponent.getMDEF() + opponent.getFloor() / 5;
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t痛恨の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        System.out.println("\t" + opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
        this.setDEF((int)(this.getDEF() / 0.9));
        Thread.sleep(500);
        System.out.println("\t" + this.getName() + "の 物理防御 が上がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void speedUp() {
    if (this.getMp() < 7) {
      try {
        System.out.println(this.getName() + "は 高速化 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 高速化 を使った！");
        this.setMp(this.getMp() - 7);
        Thread.sleep(500);
        System.out.println("\n\t高いスキルを使って高速化を行った！\n");
        Thread.sleep(1000);
        this.setSpeed((int)(this.getSpeed() / 0.9));
        System.out.println("\t" + this.getName() + "の 素早さ が上がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }
  }

  public void getBest() {
    if (this.getMp() < 12) {
      try {
        System.out.println(this.getName() + "は 最適化 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 最適化 を使った！");
        this.setMp(this.getMp() - 12);
        Thread.sleep(500);
        if (opponent.getMDEF() < opponent.getDEF()) {
          this.setMATK((int)(this.getMATK() / 0.7));
          System.out.println("\t" + this.getName() + "の 特殊攻撃 が大きく上がった！");
        }
        else {
          this.setATK((int)(this.getATK() / 0.7));
          System.out.println("\t" + this.getName() + "の 物理攻撃 が大きく上がった！");
        }
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }
  }
}
