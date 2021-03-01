/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.event;

import java.util.Random;
import chTower.character.*;

public class BattleSetting {
	public Monster set(int floor) {
		Monster m;
		Random ran = new Random();
		int whichPupil;

		switch (floor) {
			case 5:
			m = new Takada();
			break;

			case 10:
			m = new Arai();
			break;

			case 15:
			m = new Sonoda();
			break;

			case 20:
			m = new Harasawa();
			break;

			case 25:
			m = new Kiyasu();
			break;

			case 30:
			m = new Masada();
			break;

			case 40:
			m = new Matsunaga();
			break;

			case 50:
			m = new Fujimura();
			break;

			case 60:
			m = new Sakai();
			break;

			case 70:
			m = new Kobayashi();
			break;

			case 80:
			m = new Narazaki();
			break;

			case 90:
			m = new Shibata();
			break;

			case 100:
			m = new Boss();
			break;

			default:
			whichPupil = ran.nextInt(4);
			switch (whichPupil) {
				case 0:
				m = new PupilC(floor);
				break;

				case 1:
				m = new PupilB(floor);
				break;

				default:
				m = new PupilA(floor);
			}
			break;
		}

		return m;
	}
}
