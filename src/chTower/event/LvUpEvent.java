/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.event;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import chTower.character.Player;
import chTower.character.Monster;

public class LvUpEvent {
	Player p;
	final int BASE_EXP = 20;
	Random ran = new Random();

	Map<Integer, String> skills = new HashMap<Integer, String>();

	public LvUpEvent(Player p) {
		this.p = p;
		skills.put(3, "ファイア");
		skills.put(6, "居眠り");
		skills.put(10, "ほしがる");
		skills.put(14, "集中する");
		skills.put(18, "バイト");
		skills.put(25, "メガファイア");
		skills.put(30, "一日休む");
		skills.put(36, "コピペ");
		skills.put(42, "一夜漬け");
		skills.put(48, "みんなで落ちれば怖くない");
		skills.put(60, "100点答案");
    skills.put(100, "絶");
	}

	public void gainExp(Monster opponent) {
		try {
			p.setExp(p.getExp() + opponent.getExp());
			System.out.println("\t" + opponent.getExp() + " の経験値を手に入れたえ！");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void levelUp() {
		try {
			// レベルを1上げる
			int surplus = p.getExp() - p.getNeedExp();
			p.setLevel(p.getLevel() + 1);
			System.out.println("\t\t" + p.getName() + " は レベル" + p.getLevel() + " になった！");
			Thread.sleep(500);

			// ステータスを上げる
			p.setHp(p.getHp() + 15);
			p.setMaxHp(p.getMaxHp() + 15);
			p.setMp(p.getMp() + 4);
			p.setMaxMp(p.getMaxMp() + 4);
			p.setATK(p.getATK() + 6 + ran.nextInt(3));
			p.setMATK(p.getMATK() + 6 + ran.nextInt(3));
			p.setDEF(p.getDEF() + 3 + ran.nextInt(3));
			p.setMDEF(p.getMDEF() + 3 + ran.nextInt(3));
			p.setSpeed(p.getSpeed() + 3 + ran.nextInt(3));
			System.out.println("\t\t各ステータスが上昇した！");
			Thread.sleep(1000);

			// スキルを覚える
			if (skills.containsKey(p.getLevel())) {
				p.setSkCount(p.getSkCount() + 1);
				System.out.println("\t\t\t" + p.getName() + " は " + skills.get(p.getLevel()) + " を習得した！");
				Thread.sleep(2000);
			}

			// 次の必要経験値を設定
			p.setNeedExp(BASE_EXP * p.getLevel());
			p.setExp(surplus);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 負けペナルティ
	public void penalty() {
		try {
      if (p.getLevel() == 1) {      // Levelが1の時は、1以下にならない
      }
      else if (1 < p.getLevel() && p.getLevel() < 10) {
				p.setLevel(p.getLevel() - 1);
				System.out.println( p.getName() + " のレベルが 1 下がった...");
				p.setHp(p.getHp() - 15);
				p.setMaxHp(p.getMaxHp() - 15);
				p.setMp(p.getMp() - 4);
				p.setMaxMp(p.getMaxMp() - 4);
				p.setATK(p.getATK() - 6);
				p.setMATK(p.getMATK() - 6);
				p.setDEF(p.getDEF() - 4);
				p.setMDEF(p.getMDEF() - 4);
				p.setSpeed(p.getSpeed() - 4);

        if (p.getHp() < 50) {
          p.setHp(50);
          p.setMaxHp(50);
        }
        if (p.getMp() < 3) {
          p.setMp(3);
          p.setMaxMp(3);
        }
        if (p.getATK() < 10)
          p.setATK(10);
        if (p.getMATK() < 10)
          p.setMATK(10);
        if (p.getDEF() < 7)
          p.setDEF(7);
        if (p.getMDEF() < 7)
          p.setMDEF(7);
        if (p.getSpeed() < 7)
          p.setSpeed(7);
			}
			else if (p.getLevel() < 20) {
				p.setLevel(p.getLevel() - 3);
				System.out.println( p.getName() + " のレベルが 3 下がった...");
				p.setHp(p.getHp() - 45);
				p.setMaxHp(p.getMaxHp() - 45);
				p.setMp(p.getMp() - 12);
				p.setMaxMp(p.getMaxMp() - 12);
				p.setATK(p.getATK() - 18);
				p.setMATK(p.getMATK() - 18);
				p.setDEF(p.getDEF() - 12);
				p.setMDEF(p.getMDEF() - 12);
				p.setSpeed(p.getSpeed() - 12);
			}
			else {
				p.setLevel(p.getLevel() - 5);
				System.out.println( p.getName() + " のレベルが 5 下がった...");
				p.setHp(p.getHp() - 75);
				p.setMaxHp(p.getMaxHp() - 75);
				p.setMp(p.getMp() - 20);
				p.setMaxMp(p.getMaxMp() - 20);
				p.setATK(p.getATK() - 30);
				p.setMATK(p.getMATK() - 30);
				p.setDEF(p.getDEF() - 20);
				p.setMDEF(p.getMDEF() - 20);
				p.setSpeed(p.getSpeed() - 20);
			}

      // スキルを忘れる
      if (p.getLevel() < 3)
        p.setSkCount(1);
      else if (p.getLevel() < 6)
        p.setSkCount(2);
      else if (p.getLevel() < 10)
        p.setSkCount(3);
      else if (p.getLevel() < 14)
        p.setSkCount(4);
      else if (p.getLevel() < 18)
        p.setSkCount(5);
      else if (p.getLevel() < 25)
        p.setSkCount(6);
      else if (p.getLevel() < 30)
        p.setSkCount(7);
      else if (p.getLevel() < 36)
        p.setSkCount(8);
      else if (p.getLevel() < 42)
        p.setSkCount(9);
      else if (p.getLevel() < 48)
        p.setSkCount(10);
      else if (p.getLevel() < 60)
        p.setSkCount(11);
      else if (p.getLevel() < 100)
        p.setSkCount(12);

			p.setNeedExp(BASE_EXP * p.getLevel());
      p.setExp(0);

			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
