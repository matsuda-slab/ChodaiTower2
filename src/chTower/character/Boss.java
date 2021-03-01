/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.io.Serializable;

public class Boss extends Monster implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  int action;
  public Boss() {
    this.setName("魔王モリヤーマ");
    this.setHp(1350);
    this.setMaxHp(1350);
    this.setMp(450);
    this.setMaxMp(450);
    this.setATK(960);
    this.setMATK(800);
    this.setDEF(110);
    this.setMDEF(105);
    this.setSpeed(50);

    this.setExp(2000);
    this.setExpOrig(this.getExp());

    this.BOSS = true;
  }

  public void act() {
    action = ran.nextInt(8);
    if (action == 0) {
      this.despise();
    }
    else if (action == 1) {
      this.intimidate();
    }
    else if (action == 2) {
      this.satellite();
    }
    else if (action == 3) {
      this.gradToFailure();
    }
    else if (action == 4) {
      if (this.getSpeed() < opponent.getSpeed()) {
        this.counterAttack();
      }
      else {
        this.attack();
      }
    }
    else
      this.attack();
  }

  public void despise() {
    if (this.getMp() < 6) {
      try {
        System.out.println(this.getName() + "は 見下す を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 見下す を使った！");
        this.setMp(this.getMp() - 6);
        Thread.sleep(500);
        System.out.println("\n\t『へっへっへ、おまえらザコだからな』\n");
        Thread.sleep(1000);
        opponent.setATK((int)(opponent.getATK() * 0.9));
        System.out.println(opponent.getName() + " の 物理攻撃 が下がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
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
        Thread.sleep(1000);
        System.out.println("\n\t" + this.getName() + "は独特の雰囲気を放っている！\n");
        Thread.sleep(1000);
        opponent.setSpeed((int)(opponent.getSpeed() * 0.9));
        opponent.setMATK((int)(opponent.getMATK() * 0.9));
        Thread.sleep(500);
        System.out.println(opponent.getName() + " の 素早さ が下がった！");
        Thread.sleep(500);
        System.out.println(opponent.getName() + " の 特殊攻撃 が下がった！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void satellite() {
    if (this.getMp() < 25) {
      try {
        System.out.println(this.getName() + "は 衛星 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 衛星 を使った！");
        this.setMp(this.getMp() - 25);
        Thread.sleep(500);
        System.out.println("\n\t衛星から放たれる強力な一撃！！\n");
        Thread.sleep(1000);
        damage = this.getMATK() * 70 / opponent.getMDEF() + this.getMATK() / 8 + ran.nextInt(20);
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t痛恨の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        System.out.println(opponent.getName() + " は " + (int)damage + " のダメージを受けた！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }
  }

  public void gradToFailure() {
    if (this.getMp() < 15) {
      try {
        System.out.println(this.getName() + "は 人の落単を喜ぶ を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は 人の落単を喜ぶ を使った！");
        this.setMp(this.getMp() - 15);
        Thread.sleep(500);
        System.out.println("\t『やーい！ ザマーみろ！^^』");
        opponent.setDEF((int)(opponent.getDEF() * 0.9));
        opponent.setMDEF((int)(opponent.getMDEF() * 0.9));
        System.out.println("\t" + opponent.getName() + " の 物理防御 が下がった！");
        System.out.println("\t" + opponent.getName() + " の 特殊防御 が下がった！");
        opponent.setHp(opponent.getHp() + 150);
        if (opponent.getMaxHp() < opponent.getHp()) {
          opponent.setHp(opponent.getMaxHp());
        }
        Thread.sleep(1000);
        System.out.println(this.getName() + " は少し元気になった！");
        Thread.sleep(500);
        System.out.println(this.getName() + " の HP が少し回復した！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }
  }

  public void counterAttack() {
    if (this.getMp() < 7) {
      try {
        System.out.println(this.getName() + "は カウンター を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りない！");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        System.out.println(this.getName() + "は カウンター を使った！");
        Thread.sleep(500);
        if (opponent.damage == 0) {
          System.out.println("しかしうまく決まらなかった！");
        }
        else {
          this.setMp(this.getMp() - 7);
          Thread.sleep(500);
          System.out.println("\n\t『お前らのガードが下がったところでカウンターぶち込むのが、一番好きなんだよ！！！』\n");
          Thread.sleep(1000);
          damage = opponent.damage * 2;
          opponent.setDamage((int)damage);
          System.out.println("\t" + (int)damage + " のダメージを受けた！");
          Thread.sleep(1000);
        }
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();	
      }
    }	
  }
}
