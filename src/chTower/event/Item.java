/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.event;

import java.io.Serializable;
import chTower.character.Player;

public class Item implements Serializable {
	private static final long serialVersionUID = 7356304301L;
	private int count_cheatPaper;
	private Player player;

	public Item(Player p) {
		this.player = p;
		this.count_cheatPaper = 0;
	}

	public int getCount_cheatPaper() {
		return this.count_cheatPaper;
	}

	public void setCount_cheatPaper(int count) {
		this.count_cheatPaper = count;
	}

	public void useCheatPaper() {
		try {
			if (this.count_cheatPaper == 0) {
				System.out.println("カンペを持っていない！\n");
			}
			else {
				this.count_cheatPaper -= 1;
				System.out.println(player.getName() + " は カンペ を使った！");
				player.setMp(player.getMp() + 30);
				int sub = 30;
				if (player.getMaxMp() < player.getMp()) {
					sub = 30 - (player.getMp() - player.getMaxMp());
					player.setMp(player.getMaxMp());
				}
				Thread.sleep(500);
				System.out.println("\t" + player.getName() + " のMPが " + sub + " 回復した！\n");
			}
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
