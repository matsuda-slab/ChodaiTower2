/*
 * Copyright (c) 2019 Masatomo Matsuda, All rights reserved.
 */

package chTower.event;

import java.io.*;
import java.util.Base64;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import chTower.character.Player;

public class Saver {
	public static void save(Player player) {
		try {
			// シリアライズ
			FileOutputStream fos = new FileOutputStream("save_temp.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(player);
			oos.flush();
			oos.close();


			// 暗号化
			String key = "chodaitower12345";
			String initVector = "vfh48fj2hdguv3cj";
			String algorithm = "AES/CBC/PKCS5Padding";
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(initVector.getBytes()));

			FileOutputStream fos2 = new FileOutputStream("save.dat");
			BufferedOutputStream bos = new BufferedOutputStream(fos2);
			CipherOutputStream out = new CipherOutputStream(bos, c);
			
			
			FileInputStream fis = new FileInputStream("save_temp.dat");
			byte[] data = new byte[16];
			int size;
			size = fis.read(data);
			while ( size > -1) {
				out.write(data, 0, size);
				size = fis.read(data);
			}
			// c.doFinal();
			out.flush();
			out.close();
			fis.close();

			File file = new File("save_temp.dat");
			file.delete();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Player load(Player player) {
		try {
			
			String key = "chodaitower12345";
			String initVector = "vfh48fj2hdguv3cj";
			String algorithm = "AES/CBC/PKCS5Padding";
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(initVector.getBytes()));

			FileInputStream fis = new FileInputStream("save.dat");
			BufferedInputStream bis = new BufferedInputStream(fis);
			CipherInputStream in = new CipherInputStream(bis, c);
			
			FileOutputStream fos = new FileOutputStream("save_temp2.dat");
			int size;
			byte[] data = new byte[16];
			size = in.read(data);
			while ( size > -1) {
				fos.write(data, 0, size);
				size = in.read(data);
			}
			// c.doFinal();
			fos.flush();
			fos.close();
			in.close();

			// デシリアライズ
			FileInputStream fis2 = new FileInputStream("save_temp2.dat");
			ObjectInputStream ois = new ObjectInputStream(fis2);
			player = (Player) ois.readObject();
			ois.close();

			File file = new File("save_temp2.dat");
			file.delete();
		} catch (Exception e) {
			try {
				System.out.println("セーブデータを開けません");
				Thread.sleep(500);
				System.out.println("最初から始めます");
			} catch (InterruptedException ee) {
				e.printStackTrace();
			}
		}
		return player;
	}
}
