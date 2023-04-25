package pkt;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
	/*
	 * Ba�ka s�n�flarda da kullan�laca�� i�in filePath bu �ekilde tan�mland�. File
	 * path'in i�eri�i C:\Users\[...]\Desktop\[...]\PDP_Odev_G191210044\records
	 * �eklinde olacakt�r. Bu sayede records klas�r�n�n i�inde bulunan .java
	 * uzant�l� dosyalar� bulabilecek txt uzant�l� dosyalar o klas�re
	 * kaydedilecektir.
	 */
	private static String filePath;

	/*
	 * java uzant�l� dosyan�n sat�r sat�r okunmas� s�ras�nda a�a��da tan�mlanm��
	 * olan Array List i�ine dosyan�n sat�rlar� kaydedilecek.
	 */
	private static ArrayList<String> dosyaninSatirlari = new ArrayList<String>();

	/*
	 * filePath de�erini Sinif ve Fonksiyon s�n�flar�ndan alabilmek i�in yaz�lm��
	 * olan static fonksiyon
	 */
	public static String getFilePath() {
		return filePath;
	}

	/*
	 * Belirtilen dizindeki java dosyas�n� okumak i�in yaz�lm�� olan fonksiyon Sat�r
	 * sat�r okuma yapar ve dosyaninSatirlari isimli Array List'e bunlar� kaydeder.
	 */
	private static void readFile(String filepath) throws FileNotFoundException {
		try {
			File myObj = new File(filepath);
			// T�rk�e karakter sorunu olmas�n diye 'UTF-8' de�eeri ve locale s�n�f� eklendi.
			Locale loc = new Locale("tr", "TR");
			Scanner myReader = new Scanner(myObj, "UTF-8");
			myReader.useLocale(loc);

			String data = "";
			// Dosya bitene kadar okur
			while (myReader.hasNext()) {
				data = myReader.nextLine();
				// Dosyan�n o an okunan sat�r�n� Array List'e ekler.
				dosyaninSatirlari.add(data);
			}

			myReader.close();

		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Error...");
		}
	}

	/*
	 * Bir metinde regex ifadesinin ka� defa e�le�me yapt���n� ��renmek i�in
	 * yaz�lm�� bir fonksiyondur. java dosyas�nda ka� tane s�n�f�n oldu�unu, s�n�f�n
	 * i�inde ka� tane fonksiyonun oldu�unu ve fonksiyonda ka� adet yorum oldu�unu
	 * bilmek i�in bu fonksiyon kullan�lm��t�r.
	 */
	private static int kacTaneVar(String regexStr, ArrayList<String> aramaYapilacakMetin) {
		int sayac = 0;

		for (int i = 0; i < aramaYapilacakMetin.size(); i++) {
			Pattern MY_PATTERN = Pattern.compile(regexStr);
			Matcher m = MY_PATTERN.matcher(aramaYapilacakMetin.get(i));
			// E�le�me oldu�unda if blo�una girilir.
			if (m.find()) {
				sayac++;
			}
		}
		return sayac;
	}

	/*
	 * Verilen metinde aranan ifadenin ka��nc� s�rada oldu�unu d�nd�ren
	 * fonksiyondur. Bir s�n�f�n java dosyas�n�n ka��nc� s�ras�nda oldu�u ��renilir,
	 * Bir fonksiyonun ait oldu�u s�n�f�n�n ka��nc� s�ras�nda oldu�u ��renilir.
	 */
	private static ArrayList<Integer> kacinciIndis(String regexStr, ArrayList<String> aramaYapilacakMetin) {
		/*
		 * Bulundu�u indisleri tutmak i�in bir array list tan�mland�. Ka� tane e�le�me
		 * olmad���n� bilmedi�imizden �t�r� array list tan�mland�.
		 */
		ArrayList<Integer> bulunduguIndisler = new ArrayList<Integer>();

		for (int i = 0; i < aramaYapilacakMetin.size(); i++) {
			Pattern MY_PATTERN = Pattern.compile(regexStr);
			Matcher m = MY_PATTERN.matcher(aramaYapilacakMetin.get(i));
			if (m.find()) {
				bulunduguIndisler.add(i);
			}
		}
		return bulunduguIndisler;
	}

	/*
	 * Verilen metinden regex ifadesinin e�le�me yapt��� blok d�nd�r�l�r. �rne�in
	 * s�n�flar�n ismi, fonksiyonlar�n ismi ve yorumlar�n i�eriklerini almak i�in bu
	 * fonksiyon kullan�l�r.
	 */
	private static String icerik(String regexStr, String aramaYapilacakSatir) {
		String str = "";
		Pattern MY_PATTERN = Pattern.compile(regexStr);
		Matcher m = MY_PATTERN.matcher(aramaYapilacakSatir);
		if (m.find()) {
			// Di�er fonksiyonlardan farkl� olarak group() fonksiyonunu kullan�r�z.
			// Bu fonksiyon e�le�me yap�lan blo�u d�nd�r�r.
			str = m.group();
		}
		return str;
	}

	/*
	 * Metinde verilen regex ifadesini �al��t�r�r. E�le�me oldu�unda true,
	 * olmad���nda false d�nd�r�r.
	 */
	private static boolean varmi(String regexStr, String aramaYapilacakSatir) {
		Pattern MY_PATTERN = Pattern.compile(regexStr);
		Matcher m = MY_PATTERN.matcher(aramaYapilacakSatir);
		return m.find();
	}

	/*
	 * satirAraligi fonksiyonu verilen metnin i�erisinde bulunan s�n�f, fonksiyon
	 * gibi birka� sat�rdan olu�an yap�lar�n ka��nc� sat�rda ba�lay�p bitti�ini
	 * d�nd�r�r.
	 */
	private static int[] satirAraligi(String acilisKarakteri, String kapanisKarakteri, int baslangicIndis,
			ArrayList<String> aramaYapilacakMetin) {

		/*
		 * satirAralik[0] --> yap�n�n ilk sat�r�n�n indis numaras� satirAralik[1] -->
		 * yap�n�n son sat�r�n�n indis numaras�
		 */
		int[] satirAralik = new int[2];

		/*
		 * baslangicIndis parametre olarak al�n�yor ve �nceden veriliyor, b�ylece
		 * yap�n�n nerede ba�lad���n� aramakla u�ra��lm�yor. O yap�n�n ka��nc� indisten
		 * ba�lad��� bilgisi kacinciIndis fonksiyonu kullan�larak ��reniliyor.
		 */
		satirAralik[0] = baslangicIndis;

		/*
		 * acikBlokSayisi -1 ile ba�lat�l�yor ��nk� alttaki if blo�unun i�inde bulunan
		 * if acikBlokSayisi = 0 oldu�unda �al���yor. En ba�ta bu zaten 0 olaca�� i�in
		 * hatal� olabilir. Bu hatan�n olma ihtimaline kar��l�k �nlem al�nm��t�r.
		 */
		int acikBlokSayisi = -1;

		// i = baslangicIndis --> yap�n�n �ncesi ve sonras�na bak�lmadan direk
		// ba�lang�c� itibariyle bak�l�yor.
		for (int i = baslangicIndis; i < aramaYapilacakMetin.size(); i++) {

			// acilisKarakteri = '{' benzeri bloklar�n ba�lang�c�n� belirten karakterler
			// olur.
			// e�er o sat�rda a��l�� karakterinden bulunuyorsa
			if (varmi(acilisKarakteri, aramaYapilacakMetin.get(i))) {
				// ilk sat�r� m� okuyoruz diye kontrol edilir
				if (i == baslangicIndis)
					// �yleyse +1 eklemek yerine a��k blok say�s�n� 1 yap�yoruz.
					acikBlokSayisi = 1;
				else
					// de�ilse bir art�r�yoruz.
					acikBlokSayisi++;
			}
			// kapanisKarakteri = '}' benzeri, bloklar�n kapand���n� belirten
			// karakterlerdir.
			// o sat�rda kapan�� karakteri varsa
			if (varmi(kapanisKarakteri, aramaYapilacakMetin.get(i))) {
				// a��k blok say�s� bir azal�r
				acikBlokSayisi--;
				// a��k bloklar bittiyse
				if (acikBlokSayisi == 0) {
					// yap�n�n son sat�r�na ula��lm�� demektir.
					satirAralik[1] = i;
					break;
				}
			}
		}
		return satirAralik;
	}

	/*
	 * S�n�f, fonksiyon yap�lar�n� par�a par�a tutmak i�in kullan�lan fonksiyon
	 * 
	 * �rne�in Bir s�n�f verilen dosyan�n 3.sat�r�nda ba�lay�p 25. sat�r�nda
	 * bitiyorsa o sat�rlar da dahil edilerek aras� al�n�r ve s�n�f yap�s� di�er t�m
	 * sat�rlardan ayr��t�r�lm�� olur par�a halinde tutulur
	 */
	private static ArrayList<String> kodBolme(int[] aralik, ArrayList<String> bolunecekDosya) {

		ArrayList<String> parca = new ArrayList<String>();
		for (int i = aralik[0]; i < aralik[1]; i++) {
			parca.add(bolunecekDosya.get(i));
		}
		return parca;
	}

	/*
	 * Ana metot
	 */
	public static void main(String[] args) throws IOException {
		// Okunacak olan dosyan�n ad�n� tutacak olan de�i�ken
		String file;

		/*
		 * Okunacak dosyay� alma bi�imi 3 �ekilde olabilir 1. program cmd �zerinden
		 * �al��t�r�l�rken komut sat�r� parametresi olarak verilebilir. 2. program cmd
		 * �zerinden �al��t�r�l�rken komut sat�r� parametresi verilmez, sonradan
		 * kullan�c�dan girdi al�n�r. 3. program eclipse �zerinden �al��t�r�l�rken komut
		 * sat�r� parametresi verilemeyece�i i�in kullan�c�dan girdi al�n�r.
		 */
		// 1.durum ger�ekle�tiyse
		if (args.length != 0) {
			// parametre verilmi�, dosya ad� file de�i�kenine atan�r.
			file = args[0];

			/*
			 * program komut sat�r�ndan �al��t�r�ld��� i�in dist klas�r�n�n i�inden a��lm��
			 * demektir. o halde bir klas�r geri gitmeliyiz, bunun i�in de ..\ kullan�r�z
			 * ard�ndan ana �dev klas�r�nden records klas�r�ne gideriz ve dosyan�n bulundu�u
			 * dizine gelmi� oluruz bunu filePath de�i�kenine kaydederiz
			 */
			filePath = ".." + File.separator + "records" + File.separator;
			// filePath'e file de�i�keninde tutulan okunmas� istenen dosyan�n ad� eklenir
			// b�ylece okunacak olan dizinin tam yolu elde edilmi� olur.
			String filePath1 = filePath + file;

			// dosya okunur.
			readFile(filePath1);
		} else {
			// Komut sat�r� parametresi verilmemi�
			Scanner scn = new Scanner(System.in);
			// Dosya ad� kullan�c�dan istenir.
			System.out.print("Dosya ismi: ");
			file = scn.nextLine();

			// Bilgisayarda o an bulundu�umuz dizini ��reniriz.
			String suankiDizin = System.getProperty("user.dir");
			/*
			 * try - catch bloklar� olu�turuldu ��nk� kullan�c�dan al�nan dosya ad� file
			 * path ile birle�tirildi�inde sistem yolu bulam�yor ise 3 ihtimal olabilir 1.
			 * dist klas�r�nden �al��t�r�lm��t�r, bundan dolay� records klas�r�n�
			 * bulam�yordur, 2. kullan�c� dosyan�n ad�n� yanl�� girmi�tir 3. veya �yle bir
			 * dosya records klas�r� i�erisinde bulunmamaktad�r.
			 */
			try {
				// Eclipse �zerinden �al��t�r�lma durumunda �al���r.
				// dizine records ve dosyan�n ismi de eklenerek dizin tamamlanm�� olur.
				filePath = suankiDizin + File.separator + "records" + File.separator;
				String filePath1 = filePath + file;
				readFile(filePath1);
			} catch (FileNotFoundException e) {
				// dizin bulunamad�
				try {
					// dist klas�r�nde bulunan jar dosyas� �al��t�r�lm��t�r
					// bir geri gidip ana klas�re d�n�l�r ve records klas�r� bulunur.
					filePath = ".." + File.separator + "records" + File.separator;
					// dosyan�n ismi de eklenerek tam dizin tan�mlanm�� olur.
					String filePath1 = filePath + file;
					readFile(filePath1);
				} catch (FileNotFoundException x) {
					// yine yol bulunamad�.
					// �yleyse kullan�c�ya hata mesaj� yazmam�z gerekmektedir.
					System.out.println("Sistem yolu bulam�yor.");
					System.out.println("Girdi�iniz dosya ismini kontrol edin.");
					System.out.println("Dosya isminin do�ru oldu�unu d���n�yorsan�z");
					System.out.println("dosyan�z records klas�r� i�erisinde bulunmuyor olabilir.");
					System.out.println("L�tfen dosyan�z� records klas�r� i�inde yerle�tiriniz.");
				}
			}
			scn.close();
		}

		// Dosyada ka� tane s�n�f var?
		Sinif[] siniflar = new Sinif[kacTaneVar("class [a-zA-Z]+", dosyaninSatirlari)];

		// Bu s�n�flar dosyan�n ka��nc� indislerinden ba�l�yor?
		ArrayList<Integer> siniflarKacinciIndislerde = new ArrayList<Integer>();
		siniflarKacinciIndislerde = kacinciIndis("class [a-zA-Z]+", dosyaninSatirlari);

		/*
		 * Yorumlar�n tespiti yap�ld�ktan sonra txt dosyalar�na yaz�lacak. Bu dosyalar�
		 * olu�turuyoruz, �nceden olu�turulmu�larsa da i�eriklerini siliyoruz.
		 * Olu�tururken yine ayn� path'i kullanaca��z. Yani txt dosyalar� records
		 * klas�r�nde bulunacak.
		 */
		String txtName = "tekSatirliYorumlar.txt";
		String txtPath = filePath + txtName;
		FileWriter myWriter = new FileWriter(txtPath, false);

		txtName = "cokSatirliYorumlar.txt";
		txtPath = filePath + txtName;
		FileWriter myWriter1 = new FileWriter(txtPath, false);

		txtName = "javadocYorumlar.txt";
		txtPath = filePath + txtName;
		FileWriter myWriter2 = new FileWriter(txtPath, false);

		myWriter.close();
		myWriter1.close();
		myWriter2.close();

		/*
		 * S�n�f say�s� kadar d�necek bir for d�ng�s� olu�tururuz.
		 */
		for (int i = 0; i < siniflar.length; i++) {
			// Yukar�da tan�mlad���m�z siniflar dizisinin nesnelerini burada teker teker
			// olu�turuyoruz.
			siniflar[i] = new Sinif();

			// Sinif nesnesinin ad�n� ayarl�yoruz. Bunun i�in icerik fonksiyonunu ve regex
			// ifadesi kullan�yoruz.
			siniflar[i].setName(icerik("[^(public)|(private)|(protected) class][a-zA-Z]+",
					dosyaninSatirlari.get(siniflarKacinciIndislerde.get(i))));

			// O s�n�f�n hangi indislerin aras�nda oldu�unu buluyoruz.
			int[] satirAraligi = satirAraligi("\\{", "}", siniflarKacinciIndislerde.get(i), dosyaninSatirlari);
			siniflar[i].setSatirAraligi(satirAraligi);

			// O s�n�f�n kod par�ac���n� di�er kodlardan ayr��t�r�yoruz ve par�ay� bir array
			// list'e kaydediyoruz.
			ArrayList<String> sinifinKodu = kodBolme(siniflar[i].getSatirAraligi(), dosyaninSatirlari);

			// O s�n�fta bulunan fonksiyonlar�n say�s�n� tespit ediyoruz.
			int siniftaBulunanFonkSayisi = kacTaneVar(
					"(public|private|protected) ?(static)? (ArrayList<Integer>|ArrayList<String>|int\\[\\]|void|int|float|String|double|boolean)?(\\s)*[a-zA-Z]+[0-9]*\\(.*\\)\\s?(throws)?\\s?[a-zA-Z]{0,}\\s\\{",
					sinifinKodu);
			siniflar[i].setFonksiyonSayisi(siniftaBulunanFonkSayisi);
			System.out.println(
					"S�n�f:" + siniflar[i].getName() + " -- Fonksiyon Say�s�: " + siniflar[i].getFonksiyonSayisi());
			System.out.println();

			// Ka� tane fonksiyon varsa o kadar Fonksiyon nesnesi olu�turaca��z.
			Fonksiyon[] fonksiyonlar = new Fonksiyon[siniflar[i].getFonksiyonSayisi()];

			// Fonksiyonlar�n ka��nc� indislerde oldu�unu tespit ediyoruz.
			ArrayList<Integer> fonksiyonlarKacinciIndislerde = new ArrayList<Integer>();
			fonksiyonlarKacinciIndislerde = kacinciIndis(
					"(public|private|protected) ?(static)? (ArrayList<Integer>|ArrayList<String>|int\\[\\]|void|int|float|String|double|boolean)?(\\s)*[a-zA-Z]+[0-9]*\\(.*\\)\\s?(throws)?\\s?[a-zA-Z]{0,}\\s\\{",
					sinifinKodu);

			// O s�n�fta ka� tane fonksiyon varsa o kadar d�necek bir for d�ng�s� yaz�yoruz.
			for (int j = 0; j < siniflar[i].getFonksiyonSayisi(); j++) {
				String fonkAdi = "";

				// Dizi nesnesi olarak tan�mlad���m�z fonksiyonlar�n �imdi teker teker
				// nesnelerini olu�turmak i�in
				// Sinif s�n�f�nda bulunan fonkOlustur metodunu kullan�yoruz.
				siniflar[i].fonkOlustur(fonksiyonlar[i]);

				// Fonksiyon ad�n� regex ifadesi ile ��kart�yoruz.
				fonkAdi = icerik("(?m)(?<=\\bpublic ).*$|(?m)(?<=\\bprivate ).*$|(?m)(?<=\\bprotected ).*$",
						sinifinKodu.get(fonksiyonlarKacinciIndislerde.get(j)));
				// Fonksiyon ad�n� t�m karakterlerden ay�r�yor ve yaln�zca ismini b�rak�yoruz.
				fonkAdi = icerik(".+?(?=\\()", fonkAdi);
				siniflar[i].getFonksiyon(j).setName(fonkAdi);

				// Fonksiyonun ka��nc� indiste ba�lay�p bitti�ini ��reniyoruz.
				int[] fonkSatirAraligi = satirAraligi("\\{", "}", fonksiyonlarKacinciIndislerde.get(j), sinifinKodu);
				siniflar[i].getFonksiyon(j).setSatirAraligi(fonkSatirAraligi);

				// Fonksiyonun ka� sat�r oldu�unu hesapl�yoruz.
				int fonkSatirSayisi = siniflar[i].getFonksiyon(j).getSatirAraligi()[1]
						- siniflar[i].getFonksiyon(j).getSatirAraligi()[0];

				// Fonksiyonun ad�n� ekrana yazd�r�yoruz.
				System.out.println("\tFonksiyon: " + siniflar[i].getFonksiyon(j).getName());

				// Fonksiyonun ilk sat�r�n� de�i�kende tutuyoruz
				// B�ylece yorum ararken fonksiyonda o de�i�kenle gezerek kodu daha okunur
				// yap�yoruz.
				int fonkIlkSatiri = siniflar[i].getFonksiyon(j).getSatirAraligi()[0];
				int x = fonkIlkSatiri;

				// Fonksiyonun �st�nde javadoc var m� diye bak�yoruz.
				if (varmi("\\*\\/", sinifinKodu.get(x - 1))) {
					// Fonksiyonun �st�nde bulunan javadoc'un s�n�f kod par�ac���ndaki sat�r
					// numaralar� kaydedilsin diye array list tan�mlan�r.
					ArrayList<Integer> javadocSatirNolari = new ArrayList<Integer>();
					// Javadoc'un �nce biti� sat�r� kaydedilir ��nk� bir fonksiyonun �st�ndeki
					// javadoc'un �nce biti� sat�r�na ula�abiliriz.
					int javadocBitisSatir = x - 1;

					// varsa javadoc bitene kadar ilerliyor ve sat�rlar� kaydediyoruz
					JavadocYorum jvdYorum = new JavadocYorum();

					// x'i azalt�yoruz ve fonksiyonun �st�nde birer birer yukar� ��kart�yoruz.
					// B�ylece javadoc'un ba�lang�� karakterine gelene kadar i�eri�i okuyoruz.
					x--;
					do {
						javadocSatirNolari.add(--x);
					} while (!varmi("\\/\\*\\*", sinifinKodu.get(x)));
					int javadocBaslangicSatir = x;
					for (int z = javadocSatirNolari.size() - 1; z > 0; z--) {
						jvdYorum.setIcerik(sinifinKodu.get(javadocSatirNolari.get(z - 1)));
					}
					siniflar[i].getFonksiyon(j).setJavadocYorum(jvdYorum);
					siniflar[i].getFonksiyon(j).getJavadocYorum()
							.setSatirSayisi(javadocBitisSatir - javadocBaslangicSatir);
				}

				// Fonksiyonun i�indeki yorumlara bakmak i�in for d�ng�s�
				for (int k = 0; k < fonkSatirSayisi; k++) {
					ArrayList<String> fonkKodu = kodBolme(siniflar[i].getFonksiyon(j).getSatirAraligi(), sinifinKodu);

					// Fonksiyonun i�inde javadoc var m� diye bak�yoruz.
					if (varmi("\\/\\*\\*", fonkKodu.get(k))) {
						int javadocBaslangicSatir = k;
						// varsa javadoc bitene kadar ilerliyor ve sat�rlar� kaydediyoruz
						JavadocYorum jvdYorum = new JavadocYorum();
						while (!varmi("\\*\\/", fonkKodu.get(++k))) {
							jvdYorum.setIcerik(fonkKodu.get(k));
						}
						int javadocBitisSatir = k;
						siniflar[i].getFonksiyon(j).setJavadocYorum(jvdYorum);
						siniflar[i].getFonksiyon(j).getJavadocYorum()
								.setSatirSayisi(javadocBitisSatir - javadocBaslangicSatir);
					}

					// Fonksiyonun i�inde tek sat�rl� yorum var m� diye bak�yoruz.
					if (varmi("\\/\\/", fonkKodu.get(k))) {
						TekSatirliYorum tekSatirYorum = new TekSatirliYorum();
						if (varmi("\\/\\/$", fonkKodu.get(k)))
							tekSatirYorum.setIcerik(icerik("\\/\\/[\\sA-Za-z_������������]+\\/\\/", fonkKodu.get(k)));
						else if (varmi("^\\/\\/", fonkKodu.get(k)))
							tekSatirYorum.setIcerik(fonkKodu.get(k));
						else
							tekSatirYorum.setIcerik(icerik("\\/\\/.*", fonkKodu.get(k)));
						siniflar[i].getFonksiyon(j).setTekSatirYorum(tekSatirYorum);
					}

					// Fonksiyonun i�inde �ok sat�rl� yorum var m� diye bak�yoruz.
					if (varmi("\\/\\*$|\\/\\*[\\sA-Za-z_������������0-9]+", fonkKodu.get(k))) {
						int cokSatirliBaslangicSatir = k;
						int cokSatirliBitisSatir = -1;
						CokSatirliYorum cokSatirYorum = new CokSatirliYorum();

						/*
						 * �ok sat�rl� yorum'un �� �e�idi bulunur.
						 * 
						 * 1. a�a��daki gibi tek bir sat�rda bitebilir 
						 * 		/* abc */

						/*
						 * 2. a�a��daki gibi ilk sat�r�nda da i�eri�i olabilir 
						 * 		/* abc
						 	 	 */

						/*
						 * 3. a�a��daki gibi olabilir 
						 * 
						 * 		/* 
						 * 		 * abc
						 		 */

						// 1.durum
						if (varmi("\\/\\*[\\sA-Za-z_������������]+\\*\\/", fonkKodu.get(k))) {
							// Tek sat�rda toplanm�� olan �ok sat�rl� yap�s�ndaki yorumun i�eri�i al�n�r.
							cokSatirYorum.setIcerik(icerik("\\/\\*.*?\\*\\/", fonkKodu.get(k)));
							// Yorum nesnesi fonksiyon nesnesine eklenir.
							siniflar[i].getFonksiyon(j).setCokSatirYorum(cokSatirYorum);
							// Bu yorumun sat�r say�s� birdir.
							siniflar[i].getFonksiyon(j).getCokSatirYorum().setSatirSayisi(1);
							cokSatirliBitisSatir = k;
						} else if (varmi("\\/\\*[\\sA-Za-z_������������]+", fonkKodu.get(k))) {
							// 2.durum
							if (!varmi("\\/\\*[\\s]{2,}", fonkKodu.get(k))) {
								// E�er "/*" 'ndan sonra 2 veya daha fazla kere bo�luk yoksa (hata kontrol� diye
								// d���n�lebilir.)
								while (!varmi("\\*\\/", fonkKodu.get(k))) {
									// Yorumun kapan�� karakterleri yani */ gelene kadar yorum i�eri�i kaydedilmeye
									// devam eder.
									cokSatirYorum.setIcerik(fonkKodu.get(k++));
								}
								// Kapan�� karakterleri gelince yorumun son sat�r�na ula��ld��� i�in
								// bitisSatir de�i�kenine de�er atamas� yap�l�r.
								cokSatirliBitisSatir = k;
								// Olu�turulan yorum nesnesi fonksiyon nesnesine eklenir.
								siniflar[i].getFonksiyon(j).setCokSatirYorum(cokSatirYorum);
								// �ok sat�rl� yorumun ka� sat�rdan olu�tu�u bilgisi yorum nesnesinin �zelli�ine
								// y�klenir.
								siniflar[i].getFonksiyon(j).getCokSatirYorum()
										.setSatirSayisi(cokSatirliBitisSatir - cokSatirliBaslangicSatir);
							}
						} else if (varmi("\\/\\*[\\s]{2,}", fonkKodu.get(k)) || varmi("\\/\\*$", fonkKodu.get(k))) {
							// 3.durum
							while (!varmi("\\*\\/", fonkKodu.get(k))) {
								// �ok sat�rl� yorum kapan�� karakterlerinin gelmesine kadar i�eri�i okunur ve
								// nesnenin yap�s�na eklenir.
								cokSatirYorum.setIcerik(fonkKodu.get(++k));
							}
							// �ok sat�rl� yorum bitmi�tir ve biti� sat�r� de�eri de�i�kene atan�r.
							cokSatirliBitisSatir = k;
							// Yorum nesnemiz fonksiyon nesnesinin yap�s�na eklenir.
							siniflar[i].getFonksiyon(j).setCokSatirYorum(cokSatirYorum);
							siniflar[i].getFonksiyon(j).getCokSatirYorum()
									.setSatirSayisi(cokSatirliBitisSatir - cokSatirliBaslangicSatir);
						}
					}
				}
				// Ekrana yazd�r�lacak olan bilgiler
				// Fonksiyonda bulunan tek sat�rl� yorum say�s�
				// Fonksiyonda bulunan �ok sat�rl� yorum say�s�
				// Fonksiyonda bulunan javadoc yorum say�s�
				System.out.println(
						"\t\tTek Sat�rl� Yorum Say�s�: " + siniflar[i].getFonksiyon(j).getTekSatirYorumSayisi());
				System.out.println(
						"\t\t�ok Sat�rl� Yorum Say�s�: " + siniflar[i].getFonksiyon(j).getCokSatirYorumSayisi());
				System.out.println("\t\tJavadoc Yorum Say�s�: " + siniflar[i].getFonksiyon(j).getJavadocYorumSayisi());
				System.out.println();
			}
			System.out.println("----------------------------------------------------");

			// Txt dosyalar�na bilgileri yazd�rmak i�in a�a��daki fonksiyon �al��t�r�l�r.
			siniflar[i].fileWriter();
		}
	}

}
