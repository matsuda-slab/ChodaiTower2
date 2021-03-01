/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Masada extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Masada() {
    this.setName("正田准教授");
    this.setHp(330);
    this.setMp(90);
    this.setATK(230);
    this.setMATK(200);
    this.setDEF(45);
    this.setMDEF(55);
    this.setSpeed(60);

    this.setExp(210);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(5);
    if (action == 0) {
      this.mysteryBeam();
    }
    else if (action == 1) {
      this.touchHead();
    }
    else {
      this.attack();
    }
  }

  public void touchHead() {
    if (this.getMp() < 5) {
      try {
        System.out.println(this.getName() + "は 頭を触る を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 頭を触る を使った！");
        this.setMp(this.getMp() - 5);
        Thread.sleep(500);
        System.out.println("\n\t『ワタシはこうすればいいのではないかと..思いマスネェ....(ﾜｼｬﾜｼｬ...)』\n");
        Thread.sleep(1000);
        this.setATK((int)(this.getATK() / 0.9));
        this.setMATK((int)(this.getMATK() / 0.9));
        System.out.println(this.getName() + " の 物理攻撃 と 特殊攻撃 が上がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }

    }
  }

  public void mysteryBeam() {
    if (this.getMp() < 10) {
      try {
        System.out.println(this.getName() + "は 怪光線 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 怪光線 を使った！");
        this.setMp(this.getMp() - 10);
        Thread.sleep(500);
        System.out.println("\n\t" + this.getName() + " の目から赤い光線が飛び出す！！\n");
        damage = this.getMATK() * 10 / opponent.getMDEF() + this.getMATK() / 8 + ran.nextInt(6);
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t痛恨の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        Thread.sleep(1000);
        System.out.println(opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }

    }	
  }
}
