/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.main;

import java.util.Scanner;
import java.io.File;
import chTower.event.*;
import chTower.character.Player;
import chTower.character.Monster;

public class Main {

	public static void main(String[] args) {
		String next;
		BattleSetting bs = new BattleSetting();
		ShowOption so = new ShowOption();
		Scanner sc = new Scanner(System.in);
		boolean END = false;
		boolean WIN = false;
		Player player = new Player();

		try {
		    System.out.println("ようこそ長大タワーへ");
		    Thread.sleep(1000);
		    
		    File file = new File("save.dat");
		    
		    if (file.exists()){
				System.out.println("1 : はじめから始める  2 : つづきから始める");
				String startOption = sc.next();
				while (!startOption.equals("1") && !startOption.equals("2")) {
				    System.out.println("※1か2で選択");
				    startOption = sc.next();
				}
			
				if (startOption.equals("2")) {
				    player.CONT = true;
				    WIN = true;
					player = Saver.load(player);
					/*
				    player.setSkCount(12);
				    player.setMATK(50);
				    player.setMp(30);
				    player.setMaxMp(30);
				    player.setHp(100);
				    player.setMaxHp(100);
				    player.item.setCount_cheatPaper(10);
				    */
				}
				else {
				    System.out.println("このゲームは情報工学コースに棲む魔物たちを倒し、最上階を目指すゲームです。");
				    Thread.sleep(1000);
				    System.out.println("1階層クリアするたびに、戦闘中のステータス変化(攻撃・防御の変化)は元に戻り、次の階に進むか拠点に戻るか選択できます。");
				    Thread.sleep(1000);
				    System.out.println("拠点では、HPとMPが全回復します。");
				    Thread.sleep(1000);
				    System.out.println("Enterキーで次に進む...");
				    sc.nextLine();
				    sc.nextLine();
				    Thread.sleep(1000);
            System.out.println("プレイヤーの名前を決めましょう。あなたの名前は？ (* 一度決めると変更できません)");
            String player_name = sc.nextLine();
            player.setName(player_name);
            System.out.println("プレイヤー名を " + player.getName() + " に設定しました。");
				    Thread.sleep(1000);
            System.out.println("では早速行きましょう");
            Thread.sleep(1000);
				}
		    }
		    else {
				System.out.println("このゲームは情報工学コースに棲む魔物たちを倒し、最上階を目指すゲームです。");
				Thread.sleep(1000);
				System.out.println("1階層クリアするたびに、戦闘中のステータス変化(攻撃・防御の変化)は元に戻り、次の階に進むか拠点に戻るか選択できます。");
				Thread.sleep(1000);
				System.out.println("拠点では、HPとMPが全回復します。");
				Thread.sleep(1000);
				System.out.println("Enterキーで次に進む...");
				sc.nextLine();
        Thread.sleep(1000);
        System.out.println("プレイヤーの名前を決めましょう。あなたの名前は？ (* 一度決めると変更できません)");
        String player_name = sc.nextLine();
        player.setName(player_name);
        System.out.println("プレイヤー名を " + player.getName() + " に設定しました。");
        Thread.sleep(1000);
				System.out.println("では早速行きましょう");
        Thread.sleep(1000);
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		so.setPlayer(player);
		LvUpEvent lue = new LvUpEvent(player);
		SetOriginal setorig = new SetOriginal();
		
		
		while(true) {
			if (!player.CONT) {
		    	player.setFloor(player.getFloor() + 1);
		    	System.out.println("\t\t\t--- " + player.getFloor() + "階層 ---");
		    	try {
		    		Thread.sleep(1000);
		    	} catch (InterruptedException e) {
				    e.printStackTrace();
				}
			
				Monster m = bs.set(player.getFloor());
				System.out.println("\t\t" + m.getName() + " が現れた！");
				player.setOpponent(m);
				m.setOpponent(player);
				setorig.setTemp(player);
				player.setCountCramming(3);   // 「一夜漬け」 は3回まで使える
				
				if (m.getName().equals("魔王モリヤーマ")) {
				    m.pre_emptive();
				}
				
				WIN = false;
			
				// ・戦闘ループ
				while(true) {
				    System.out.println("どうする？\n");
            player.showHpMp();
				    so.showCommand(player.getSkCount());
				    next = sc.next();
				    
				    // 「逃げる」 を選択した場合
				    if (next.equals("0")) {
						if (player.escape()) {
						    WIN = true;
						    player.CONT = true;
						    player.setBase(false);
						    setorig.getOrig(player);
						    player.setFloor(player.getFloor() - 1);
						    break;    // 「逃げる」 に成功した場合は、戦闘ループを抜ける
						}
				    }
			    
				    // 	素早さによる行動順序
				    if (player.getSpeed() < m.getSpeed()) {
						m.act();
						System.out.print("\n");

						// 負けイベント
						if (player.getHp() <= 0) {
						    try {
								System.out.println(player.getName() + " は力尽きた...");
								WIN = false;
								player.CONT = true;
								setorig.getOrig(player);
								Thread.sleep(1500);	
								lue.penalty();
								System.out.println("\t\t\t\t" + player.getName() + " は塔からはじき出された！\n");
								Thread.sleep(3000);
								System.out.println("\t\t拠点へ戻ります");
								Thread.sleep(500);
								System.out.println("...");
								Thread.sleep(500);
								
								System.out.println("HPとMPが全回復した！");
								player.setHp(player.getMaxHp());
								player.setMp(player.getMaxMp());
								player.setBase(true);
								break;
						    } catch (InterruptedException e) {
								e.printStackTrace();
						    }
						}
				
						player.act(next);
						System.out.print("\n");

						// 勝ちイベント
						if (m.getHp() <= 0) {
						    System.out.println(m.getName() + " を倒した！\n");
						    WIN = true;
						    player.CONT = true;
						    player.setBase(false);
						    setorig.getOrig(player);
						    lue.gainExp(m);
					    
						    while ((player.getExp() >= player.getNeedExp()) && (player.getLevel() < 100)) {
								lue.levelUp();
						    }
				    
						    if (m.getName().equals("情報工学生C")) {
							int get = player.ran.nextInt(4);
								if (get == 0) {
								    try {
										Thread.sleep(1000);
										System.out.println(m.getName() + " は カンペ を落とした");
										Thread.sleep(500);
										System.out.println("\t" + player.getName() + " は カンペ を入手した！\n");
										player.item.setCount_cheatPaper(player.item.getCount_cheatPaper() + 1);
										Thread.sleep(1000);
								    } catch (InterruptedException e) {
										e.printStackTrace();
								    }
								}
						    }
				    
						    if (m.getName().equals("魔王モリヤーマ")) {
								try {
								    Thread.sleep(1000);
								    System.out.println("\n\tよくやったよ、おめぇ\n\n大したもんだ。\n");
								    Thread.sleep(1000);
								} catch (InterruptedException e) {
								    e.printStackTrace();
								}
						    }
				    
						    // ワープできる階層についたら、それを記録する
						    if (player.getFloor() == 60 && player.getCount20sFloor() < 3) {
								try {
								    System.out.println("拠点から61階層へジャンプできるようになった！\n");
								    player.setCount20sFloor(3);
								    Thread.sleep(1000);
								} catch (InterruptedException e) {
								    e.printStackTrace();
								}
						    }
						    if (player.getFloor() == 40 && player.getCount20sFloor() < 2) {
								try {
								    System.out.println("拠点から41階層へジャンプできるようになった！\n");
								    player.setCount20sFloor(2);
								    Thread.sleep(1000);
								} catch (InterruptedException e) {
								    e.printStackTrace();
								}
						    }
						    if (player.getFloor() == 20 && player.getCount20sFloor() < 1) {
								try {
								    System.out.println("拠点から21階層へジャンプできるようになった！\n");
								    player.setCount20sFloor(1);
								    Thread.sleep(1000);
								} catch (InterruptedException e) {
								    e.printStackTrace();
								}
						    }
				    
						    break;
						}
				
				    }
			    
				    else {
						player.act(next);
						System.out.print("\n");
						// ・勝ちイベント
						if (m.getHp() <= 0) {
						    System.out.println(m.getName() + " を倒した！\n");
						    WIN = true;
						    player.CONT = true;
						    player.setBase(false);
						    setorig.getOrig(player);
						    lue.gainExp(m);
				    
						    while ((player.getExp() >= player.getNeedExp()) && (player.getLevel() < 100)) {
								lue.levelUp();
						    }
				    
						    // カンペ落とす
						    if (m.getName().equals("情報工学生C")) {
								int get = player.ran.nextInt(4);
								if (get == 0) {
								    try {
										System.out.println(m.getName() + " は カンペ を落とした");
										Thread.sleep(500);
										System.out.println("\t" + player.getName() + " は カンペ を入手した！\n");
										player.item.setCount_cheatPaper(player.item.getCount_cheatPaper() + 1);
										Thread.sleep(1000);
								    } catch (InterruptedException e) {
										e.printStackTrace();
								    }
							    
								}
						    }
				    
						    if (m.getName().equals("魔王モリヤーマ")) {
								try {
								    Thread.sleep(1000);
								    System.out.println("\n\tよくやったよ、おめぇ\n\n大したもんだ。\n");
								    Thread.sleep(1000);
								} catch (InterruptedException e) {
								    e.printStackTrace();
								}
						    }
				    
						    // ワープできる階層についたら、それを記録する
						    if (player.getFloor() == 60 && player.getCount20sFloor() < 3) {
								System.out.println("拠点から61階層へジャンプできるようになった！");
								player.setCount20sFloor(3);
						    }
						    if (player.getFloor() == 40 && player.getCount20sFloor() < 2) {
								System.out.println("拠点から41階層へジャンプできるようになった！");
								player.setCount20sFloor(2);
						    }
						    if (player.getFloor() == 20 && player.getCount20sFloor() < 1) {
								System.out.println("拠点から21階層へジャンプできるようになった！");
								player.setCount20sFloor(1);
						    }
				    
						    break;
						}
				
						m.act();
						System.out.print("\n");
						// 負けイベント
						if (player.getHp() <= 0) {
						    try {
								System.out.println(player.getName() + " は力尽きた...");
								WIN = false;
								player.CONT = true;
								setorig.getOrig(player);
								Thread.sleep(1500);
								lue.penalty();
								System.out.println("\t\t\t\t" + player.getName() + "は塔からはじき出された！\n");
								Thread.sleep(3000);
								System.out.println("\t\t拠点へ戻ります");
								Thread.sleep(500);
								System.out.println("...");
								Thread.sleep(500);
								
								System.out.println("HPとMPが全回復した！");
								player.setHp(player.getMaxHp());
								player.setMp(player.getMaxMp());
								player.setBase(true);
								break;
						    } catch (InterruptedException e) {
								e.printStackTrace();
						    }
						}
					} // ・素早さが遅いとき終わり
				} // ・戦闘ループ終わり

				if (player.getFloor() == 100) {
				    try {
						System.out.println("塔を制覇した！！");
						Thread.sleep(500);
						System.out.println("これで長大卒業だ！！");
						Thread.sleep(1000);
						System.out.println("\n\n\t\t\tThank you for playing !!\n");
						Thread.sleep(5000);
						Saver.save(player);
						System.out.println("\nセーブしました\n");
						Thread.sleep(1000);
						System.out.println("\t\t拠点へ戻ります");
						Thread.sleep(500);
						System.out.println("...");
						Thread.sleep(500);
						
						System.out.println("HPとMPが全回復した！");
						player.setHp(player.getMaxHp());
						player.setMp(player.getMaxMp());
						
						player.CONT = true;
						WIN = false;
						Thread.sleep(250);
				    } catch (InterruptedException e) {
						e.printStackTrace();
				    }
				}
			}
		    
			// つづきから始めるとき  
			if (player.CONT) {
				// 階層途中にいる場合（前回、階層途中でセーブして終了した場合）
				if (!player.getBase() && player.getFloor() != 0) {
				    // 次の階層 or 拠点 or セーブ or ゲーム終了
				    while (WIN) {
				    	player.setBase(false);
						System.out.println("\n\n\nどうする？");
						next = so.showNext();
						if (next.equals("0")) {
						    try {
								System.out.println("ゲームを終了します");
								Thread.sleep(1000);
								END = true;
								break;
						    } catch (InterruptedException e) {
								e.printStackTrace();
						    }
						}
			
						else if (next.equals("2")) {
						    try {
								System.out.println("拠点へ戻ります");
								Thread.sleep(500);
								System.out.println("...");
								Thread.sleep(500);
								
								System.out.println("HPとMPが全回復した！");
								player.setHp(player.getMaxHp());
								player.setMp(player.getMaxMp());
								Thread.sleep(500);
								player.setFloor(so.waitIn(player.getCount20sFloor()));
								break;
						    } catch (InterruptedException e) {
								e.printStackTrace();
						    }
						}
				
						else if (next.equals("3")) {
						    try {
								Saver.save(player);
								System.out.println("セーブしました\n");
								Thread.sleep(1000);
						    } catch (InterruptedException e) {
								e.printStackTrace();
						    }
						}
			
						else if (next.equals("4")) {
						    player.item.useCheatPaper();
						}

            else if (next.equals("5")) {
                so.showStatus();
            }
				
						else {
						    break;
						}
			
				    }
				}
				else {
			    	player.setFloor(so.waitIn(player.getCount20sFloor()));
				}    
		    
			}
			
		    if (player.getFloor() == -1)
				END = true;
	    
		    if (END)
				break;
		    
		    // 次の階層へ
		    try {
		    	player.CONT = false;
				Thread.sleep(1000);
        System.out.println(".....");
        Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }
    sc.close();
  }

}
