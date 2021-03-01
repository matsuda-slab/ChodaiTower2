/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.event;

import java.util.Scanner;
import chTower.character.Player;

public class ShowOption {
	int i;  // カウンタ
	String option;  // 拠点での選択肢格納
	boolean variable = false;
	String command[] = {"にげる", "通常攻撃", "ファイア (MP:2)", "居眠り (MP:3)", "ほしがる (MP:6)", "集中する (MP:8)", "バイト (MP:5)", 
	"メガファイア (MP:12)", "一日休む (MP:20)", "コピペ (MP:17)", "一夜漬け (MP:0)", "みんなで落ちれば怖くない (MP:15)", "100点答案 (MP:40)", "絶 (MP:すべて)"};
	String floorCommand[] = {"1階層へ", "21階層へ", "41階層へ", "61階層へ"};
	Scanner sc = new Scanner(System.in);
	Player p;

	public void setPlayer(Player p) {
		this.p = p;
	}

	public void showCommand(int skillCount) {
		for(i = 0; i <= skillCount; i++) {
			System.out.println("\t\t\t" + i + " : " + this.command[i]);
		}
	}

	public void showBaseCommand(int count20sFloor) {
		System.out.println("0 : ゲーム終了");
		System.out.println("1 : ステータスを見る");
		System.out.println("2 : セーブ");
		for(i = 0; i <= count20sFloor; i++) {
			System.out.println((i+3) + " : " + this.floorCommand[i]);
		}
		System.out.printf("\n");
	}

	public String showNext() {
		String next;
		System.out.println("\t\t\t--- " + p.getFloor() + "階層 ---");
		System.out.println("0 : 終了\n1 : 次の階へ進む\n2 : 拠点へ戻る\n3 : セーブ\n4 : カンペを使う (カンペ : " + p.item.getCount_cheatPaper() + "個)\n5 : ステータスを見る\n");
		next = sc.next();
		while (!(next.equals("0")) && !(next.equals("1")) && !(next.equals("2")) && !(next.equals("3")) && !(next.equals("4")) && !(next.equals("5"))) {
			System.out.println("※0,1,2,3,4,5で選択");
			next = sc.next();
		}

		return next;
	}

	public void showStatus() {
    System.out.println("\t\t\t\tステータス");
		System.out.println("\t\t\tLv : " + p.getLevel());
		System.out.println("\t\t\tHP : " + p.getHp() + " / " + p.getMaxHp());
		System.out.println("\t\t\tMP : " + p.getMp() + " / " + p.getMaxMp());
		System.out.println("\t\t\t物理攻撃力 : " + p.getATK());
		System.out.println("\t\t\t物理防御力 : " + p.getDEF());
		System.out.println("\t\t\t特殊攻撃力 : " + p.getMATK());
		System.out.println("\t\t\t特殊防御力 : " + p.getMDEF());
		System.out.println("\t\t\t素早さ : " + p.getSpeed());
		System.out.println("\t\t\t経験値 : " + p.getExp() + " / " + p.getNeedExp());
	}

	public int waitIn(int count20sFloor) {
		p.setFloor(0);
		int nextFloor = 0;
		variable = false;
		try {
			/*
			System.out.println("HPとMPが全回復した！");
			p.setHp(p.getMaxHp());
			p.setMp(p.getMaxMp());
			Thread.sleep(500);
			*/

			while (true) {
				System.out.println("\n\n\t\t\t--- 拠点 ---");
				System.out.println("どうする？");
				showBaseCommand(p.getCount20sFloor());
				option = sc.next();

				switch (option) {
					case "0":
					try {
						nextFloor = -1;
						variable = true;
						System.out.println("ゲームを終了します");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;

					case "1":
					showStatus();
					break;

					case "2":
					try {
						Saver.save(p);
						System.out.println("セーブしました\n");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;

					case "3":
					nextFloor = 0;
					variable = true;
					break;

					case "4":
					if (p.getCount20sFloor() < 1) {
						System.out.println("無効なコマンド\n");
						Thread.sleep(500);
					}
					else {
						nextFloor = 20;
						variable = true;
					}
					break;

					case "5":
					if (p.getCount20sFloor() < 2) {
						System.out.println("無効なコマンド\n");
						Thread.sleep(500);
					}
					else {
						nextFloor = 40;
						variable = true;
					}
					break;

					case "6":
					if (p.getCount20sFloor() < 3) {
						System.out.println("無効なコマンド\n");
						Thread.sleep(500);
					}
					else {
						nextFloor = 60;
						variable = true;
					}
					break;

					default:
					try {
						System.out.println("無効なコマンド\n");
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}

				if (variable)
					break;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return nextFloor;
	}
}
