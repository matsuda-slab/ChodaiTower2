/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.character;

import java.util.Random;
import java.util.HashMap;
import chTower.event.Item;
import java.io.Serializable;

public class Player implements Serializable {
  private static final long serialVersionUID = 7356304301L;
  private String name;
  private int    hp;
  private int    MAX_HP;
  private int    mp;
  private int    MAX_MP;
  private int    atk;  // 物理攻撃力
  private int    matk;  // 特殊攻撃力
  private int    def;  // 物理防御力
  private int    mdef;  // 特殊防御力
  private int    speed;

  private int    level;
  private int    skillCount;

  private int    exp;
  private int    needExp;

  private int    floor;
  private int    count20sFloor;

  private int    countCramming;

  private boolean BASE; // 拠点ならtrue 階層途中ならfalse

  public boolean CONT = false;	// CONT;CONTINUE は、つづきから始めるときは true, 最初から始めるときは false

  double damage;  // ダメージ保有変数
  public int CRITICAL = 20;

  transient private Monster opponent;

  public Item item;

  public Random ran = new Random();

  public Player() {
    this.hp = 50;
    this.MAX_HP = this.hp;
    this.mp = 3;
    this.MAX_MP = this.mp;
    this.atk = 10;
    this.matk = 10;
    this.def = 7;
    this.mdef = 7;
    this.speed = 7;
    this.level = 1;
    this.skillCount = 1;
    this.exp = 0;
    this.needExp = 20;

    this.floor = 0;
    this.count20sFloor = 0;

    this.item = new Item(this);
  }

  public void setOpponent(Monster m) {
    this.opponent = m;
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

  public void setMaxHp(int maxHP) {
    this.MAX_HP = maxHP;
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

  public void setMaxMp(int maxMP) {
    this.MAX_MP = maxMP;
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

  public int getSkCount() {
    return this.skillCount;
  }

  public void setSkCount(int skCount) {
    this.skillCount = skCount;
  }

  public int getExp() {
    return this.exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public int getNeedExp() {
    return this.needExp;
  }

  public void setNeedExp(int needExp) {
    this.needExp = needExp;
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int lv) {
    this.level = lv;
  }

  public void setDamage(int damage) {
    this.hp -= damage;
  }

  public int getCountCramming() {
    return this.countCramming;
  }

  public void setCountCramming(int count) {
    this.countCramming = count;
  }

  public boolean getBase() {
    return this.BASE;
  }

  public void setBase(boolean b) {
    this.BASE = b;
  }

  public int getFloor() {
    return this.floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getCount20sFloor() {
    return this.count20sFloor;
  }

  public void setCount20sFloor(int count) {
    this.count20sFloor = count;
  }

  public void act(String command) {
    switch (command) {
      case "0":
        break;

      case "1":
        this.attack();
        break;

      case "2":
        if (this.skillCount >= 2)
          this.fire();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + " は行動できない！");
        break;

      case "3":
        if (this.skillCount >= 3)
          this.heal();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "4":
        if (this.skillCount >= 4)
          this.want();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "5":
        if (this.skillCount >= 5)
          this.concentrate();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "6":
        if (this.skillCount >= 6)
          this.job();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "7":
        if (this.skillCount >= 7)
          this.megafire();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "8":
        if (this.skillCount >= 8)
          this.restOneDay();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "9":
        if (this.skillCount >= 9)
          this.copyPaste();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "10":
        if (this.skillCount >= 10)
          this.cramming();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "11":
        if (this.skillCount >= 11)
          this.dontScare();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "12":
        if (this.skillCount >= 12)
          this.score100();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      case "13":
        if (this.skillCount >= 13)
          this.zetu();
        else
          System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;

      default:
        System.out.println("無効なコマンド！\n" + this.getName() + "は行動できない！");
        break;
    }
  }

  public boolean escape() {
    boolean successEsc = false;
    try {
      System.out.println(this.getName() + " は逃げ出した！");
      Thread.sleep(1000);
      if (opponent.BOSS == true) {
        System.out.println("しかし逃げられなかった！");
      }
      // 敵より素早い場合、逃げる成功確率:90%
      else if (opponent.getSpeed() < this.speed) {
        int success = ran.nextInt(10);
        if (success == 0) {
          System.out.println("しかし逃げられなかった！");
        }
        else {
          System.out.println("うまく逃げ切れた！");
          successEsc = true;
        }
      }
      // 敵より遅い場合、逃げる成功確率:30%
      else {
        int success = ran.nextInt(10);
        if (success == 0 || success == 1 || success == 2) {
          System.out.println("うまく逃げ切れた！");	
          successEsc = true;
        }
        else {
          System.out.println("しかし逃げられなかった！");	
        }
      }
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return successEsc;
  }

  public void attack() {
    try {
      System.out.println(this.getName() + " の攻撃！");
      Thread.sleep(500);
      damage = this.atk * 4 / opponent.getDEF() + (ran.nextInt(5));
      if (ran.nextInt(CRITICAL) == 0) {
        System.out.println("\n\t会心の一撃！\n");
        damage *= 2.5;
        Thread.sleep(1000);
      }
      opponent.setDamage((int)damage);
      System.out.println("\t" + (int)damage + " のダメージを与えた！");
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();			
    }
  }

  public void fire() {
    try {
      if (this.mp < 2) {
        System.out.println(this.getName() + " は ファイア を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);
      }
      else {
        System.out.println(this.getName() + " は ファイア を使った！");
        Thread.sleep(500);
        System.out.println("\n\tパソコンの熱がダメージを与える！！\n");
        damage = this.matk * 5.5 / opponent.getMDEF() + (ran.nextInt(3) + 4);
        this.mp -= 2;
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t会心の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        Thread.sleep(1000);
        System.out.println("\t" + (int)damage + " のダメージを与えた！");
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void heal() {
    try {
      if (this.mp < 3) {
        System.out.println(this.getName() + " は 居眠り を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);
      }
      else {
        System.out.println(this.getName() + " は 居眠り を使った！");
        this.mp -= 3;
        damage = this.matk * 0.5 + ran.nextInt(5) - (this.getFloor() / 3) - (this.getFloor() / 4);
        this.hp += (int)damage;
        if (this.hp > this.MAX_HP) {
          damage = (damage - (this.hp - this.MAX_HP));
        }
        Thread.sleep(500);
        System.out.println("\t" + this.getName() + " はHPが " + (int)damage + " 回復した！");
        if (this.hp > this.MAX_HP) {
          this.hp = this.MAX_HP;
        }
        Thread.sleep(1000);
      }
      damage = 0;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void want() {
    try {
      if (this.mp < 6) {
        System.out.println(this.getName() + " は 欲しがる を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);	
      }
      else {
        System.out.println(this.getName() + " は 欲しがる を使った！");
        this.mp -= 6;
        Thread.sleep(500);
        System.out.println("\n\t" + this.getName() + " は必死で単位を欲しがっている！\n");
        damage = this.atk * 6 / opponent.getDEF() + (ran.nextInt(2) + 1);
        opponent.setDamage((int)damage);
        Thread.sleep(1000);
        System.out.println("\t" + (int)damage + " のダメージを与えた！");
        Thread.sleep(1000);

        System.out.println("\tさらに "+ opponent.getName() + " の 攻撃力 を下げた！");
        opponent.setATK((int)(opponent.getATK() * 0.9));
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void concentrate() {
    try {
      if (this.mp < 8) {
        System.out.println(this.getName() + " は 集中する を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);
      }
      else {
        System.out.println(this.getName() + " は 集中する を使った！");
        this.mp -= 8;
        Thread.sleep(500);
        if (CRITICAL < 5) {
          System.out.println("\t" + this.getName() + " の 会心率 はもう上がらない！");
          Thread.sleep(1000);
        }
        else {
          System.out.println("\n\tテストに向けて集中が増す...！！\n");
          Thread.sleep(1000);
          CRITICAL /= 2;
          if (CRITICAL < 5)
            CRITICAL = 3;
          System.out.println("\t" + this.getName() + " の 会心率 が上がった！");
          Thread.sleep(1000);
        }
      }
      damage = 0;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public void job() {
    try {
      if (this.mp < 5) {
        System.out.println(this.getName() + " は バイト を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);
      }
      else {
        System.out.println(this.getName() + " は バイト を使った！");
        this.mp -= 5;
        Thread.sleep(500);
        if (opponent.getExpOrig() != opponent.getExp()) {
          System.out.println("\tこれ以上この戦闘での経験値は増えない！");
        }
        else {
          opponent.setExp((int)(opponent.getExp() * 1.1));
          System.out.println("\tこの戦闘でもらえる経験値が少し増えた！");
        }
        Thread.sleep(1000);
      }
      damage = 0;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void megafire() {
    try {
      if (this.mp < 12) {
        System.out.println(this.getName() + " は メガファイア を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);	
      }
      else {
        System.out.println(this.getName() + " は メガファイア を使った！");
        this.mp -= 12;
        Thread.sleep(500);
        System.out.println("\n\t化物コース生にお願いし、巨大な炎を生みだす！\n");
        damage = this.matk * 15 / opponent.getMDEF() + (ran.nextInt(4) + 3);
        if (ran.nextInt(CRITICAL) == 0) {
          System.out.println("\n\t会心の一撃！\n");
          damage *= 2.5;
          Thread.sleep(1000);
        }
        opponent.setDamage((int)damage);
        Thread.sleep(1000);
        System.out.println("\t" + (int)damage + " のダメージを与えた！");
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void restOneDay() {
    try {
      if (this.mp < 20) {
        System.out.println(this.getName() + " は 一日休む を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);	
      }
      else {
        System.out.println(this.getName() + " は 一日休む を使った！");
        this.mp -= 20;
        Thread.sleep(500);
        System.out.println("\n\tその日の授業をすべて休み、休養を取った！\n");
        damage = this.matk * 1.6 + ran.nextInt(5);
        this.hp += damage;
        if (this.MAX_HP < this.hp) {
          damage = damage - (this.hp - this.MAX_HP);
          this.hp = this.MAX_HP;
        }
        Thread.sleep(500);
        System.out.println("\t" + this.getName() + " はHPが " + (int)damage + " 回復した！");

        Thread.sleep(500);
        this.atk *= 0.7;
        System.out.println(this.getName() + " の 物理攻撃 が大きく下がった！");
        Thread.sleep(500);
        this.matk *= 0.7;
        System.out.println(this.getName() + " の 特殊攻撃 が大きく下がった！");
        Thread.sleep(1000);
      }
      damage = 0;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void copyPaste() {
    try {
      if (this.mp < 17) {
        System.out.println(this.getName() + " は コピペ を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);	
      }
      else {
        System.out.println(this.getName() + " は コピペ を使った！");
        this.mp -= 17;
        Thread.sleep(500);
        System.out.println("\n\tコピペを繰り返してレポートを提出した！\n");
        int attackCount = ran.nextInt(4) + 2;
        System.out.println("\t" + opponent.getName() + "に連続ダメージ！");
        for (int i = 0; i < attackCount; i++) {
          damage = this.atk * 2.5 / opponent.getDEF() + ran.nextInt(6);
          if (ran.nextInt(CRITICAL) == 0) {
            System.out.println("\n\t会心の一撃！\n");
            damage *= 2.5;
            Thread.sleep(1000);
          }
          opponent.setDamage((int)damage);
          Thread.sleep(200);
          System.out.println("\t" + (int)damage + " のダメージを与えた！");
        }
        Thread.sleep(1000);

        this.def *= 0.9;
        System.out.println("\t" + this.getName() + " の 物理防御 が下がった！");
        // opponent.setATK((int)(opponent.getATK() * 0.9));
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void cramming() {
    try {
      System.out.println(this.getName() + " は 一夜漬け を使った！");
      Thread.sleep(500);
      if (this.getCountCramming() == 0) {
        System.out.println("\n\tこれ以上は頭に入らない！\n");
      }
      else {
        this.setCountCramming(this.getCountCramming() - 1);
        System.out.println("\n\t一日を費やして必死に知識を詰め込んでいる！\n");
        damage = this.matk * 0.3 - (this.getLevel() * 1.3);
        Thread.sleep(1000);
        this.mp += damage;
        if (this.mp > this.MAX_MP) {
          damage = damage - (this.mp - this.MAX_MP);
          this.mp = this.MAX_MP;
        }
        System.out.println(this.getName() + " のMPが " + (int)damage + " 回復した！");

        this.def *= 0.7;
        this.mdef *= 0.7;
        System.out.println("\t" + this.getName() + " の 物理防御 が大きく下がった！");
        Thread.sleep(500);
        System.out.println("\t" + this.getName() + " の 特殊防御 が大きく下がった！");
        damage = 0;
      }
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }	
  }

  public void dontScare() {
    try {
      if (this.mp < 15) {
        System.out.println(this.getName() + " は みんなで落ちれば怖くない を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);	
      }
      else {
        System.out.println(this.getName() + " は みんなで落ちれば怖くない を使った！");
        this.mp -= 15;
        Thread.sleep(500);
        System.out.println("\n\t他の人も勉強していないことを知り、少し安心した！\n");
        this.def /= 0.9;
        this.mdef /= 0.9;
        Thread.sleep(500);
        System.out.println("\t" + this.getName() + " の 物理防御 が上がった！");
        Thread.sleep(500);
        System.out.println("\t" + this.getName() + " の 特殊防御 が上がった！");
        Thread.sleep(1000);
      }
      damage = 0;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }	
  }

  public void score100() {
    try {
      if (this.mp < 40) {
        System.out.println(this.getName() + " は 100点答案 を使おうとした！");
        Thread.sleep(500);
        System.out.println("しかしMPが足りなかった！");
        Thread.sleep(1000);	
      }
      else {
        System.out.println(this.getName() + " は 100点答案 を使った！");
        this.mp -= 40;
        Thread.sleep(500);
        System.out.println("\n\t試験が会心の出来だった！！\n");
        Thread.sleep(500);
        damage = (this.atk * 4 / opponent.getDEF() + (ran.nextInt(3) + 1)) * 2.5;
        System.out.println("\n\t会心の一撃！\n");
        Thread.sleep(500);
        System.out.println("\t" + (int)damage + " のダメージを与えた！");
        opponent.setDamage((int)damage);
        Thread.sleep(1000);
        this.atk /= 0.9;
        this.matk /= 0.9;
        System.out.println("さらに" + this.getName() + " の 物理攻撃 が上がった！");
        Thread.sleep(500);
        System.out.println("さらに" + this.getName() + " の 特殊攻撃 が上がった！");
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }	
  }

  public void zetu() {
    try {
      System.out.println(this.getName() + " は 絶 を使った！");
      damage = (this.getMp() * this.getMATK() / 8) / opponent.getMDEF() + this.getMp() * 0.8;
      Thread.sleep(500);
      System.out.println("\n\tすべてのエネルギーを使い、フルパワーの一撃を放つ！！\n");
      Thread.sleep(500);
      System.out.println("\t" + (int)damage + " のダメージを与えた！");
      opponent.setDamage((int)damage);
      Thread.sleep(1000);
      this.mp = 0;
      System.out.println(this.getName() + " のMPが0になった！");
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }	
  }

  public void showHpMp() {
    int max = 50;
    int rest;        // 残りHP, MPの割合を表す

    HashMap<String, String> color = new HashMap<>();

    color.put("red", "\u001b[00;41m");
    color.put("yellow", "\u001b[00;43m");
    color.put("green", "\u001b[00;42m");
    color.put("pink", "\u001b[00;45m");
    color.put("none", "\u001b[00m");
    // show HP
    rest = (int)((double)this.getHp() / this.getMaxHp() * max);
    System.out.printf("[HP : %4d / %4d", this.getHp(), this.getMaxHp());
    if (rest < 11)
      System.out.print("  [" + color.get("red"));
    else if (rest < 25)
      System.out.print("  [" + color.get("yellow"));
    else
      System.out.print("  [" + color.get("green"));

    int i;
    for (i=0; i < rest; i++)
      System.out.print(" ");
    System.out.print(color.get("none"));
    for (i=0; i < max-rest; i++)
      System.out.print(" ");
    System.out.println("] ]");

    // show MP
    rest = (int)((double)this.getMp() / this.getMaxMp() * max);
    System.out.printf("[MP : %4d / %4d", this.getMp(), this.getMaxMp());
    System.out.print("  [" + color.get("pink"));

    for (i=0; i < rest; i++)
      System.out.print(" ");
    System.out.print(color.get("none"));
    for (i=0; i < max-rest; i++)
      System.out.print(" ");
    System.out.println("] ]");
  }

}
