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
	 * Baþka sýnýflarda da kullanýlacaðý için filePath bu þekilde tanýmlandý. File
	 * path'in içeriði C:\Users\[...]\Desktop\[...]\PDP_Odev_G191210044\records
	 * þeklinde olacaktýr. Bu sayede records klasörünün içinde bulunan .java
	 * uzantýlý dosyalarý bulabilecek txt uzantýlý dosyalar o klasöre
	 * kaydedilecektir.
	 */
	private static String filePath;

	/*
	 * java uzantýlý dosyanýn satýr satýr okunmasý sýrasýnda aþaðýda tanýmlanmýþ
	 * olan Array List içine dosyanýn satýrlarý kaydedilecek.
	 */
	private static ArrayList<String> dosyaninSatirlari = new ArrayList<String>();

	/*
	 * filePath deðerini Sinif ve Fonksiyon sýnýflarýndan alabilmek için yazýlmýþ
	 * olan static fonksiyon
	 */
	public static String getFilePath() {
		return filePath;
	}

	/*
	 * Belirtilen dizindeki java dosyasýný okumak için yazýlmýþ olan fonksiyon Satýr
	 * satýr okuma yapar ve dosyaninSatirlari isimli Array List'e bunlarý kaydeder.
	 */
	private static void readFile(String filepath) throws FileNotFoundException {
		try {
			File myObj = new File(filepath);
			// Türkçe karakter sorunu olmasýn diye 'UTF-8' deðeeri ve locale sýnýfý eklendi.
			Locale loc = new Locale("tr", "TR");
			Scanner myReader = new Scanner(myObj, "UTF-8");
			myReader.useLocale(loc);

			String data = "";
			// Dosya bitene kadar okur
			while (myReader.hasNext()) {
				data = myReader.nextLine();
				// Dosyanýn o an okunan satýrýný Array List'e ekler.
				dosyaninSatirlari.add(data);
			}

			myReader.close();

		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Error...");
		}
	}

	/*
	 * Bir metinde regex ifadesinin kaç defa eþleþme yaptýðýný öðrenmek için
	 * yazýlmýþ bir fonksiyondur. java dosyasýnda kaç tane sýnýfýn olduðunu, sýnýfýn
	 * içinde kaç tane fonksiyonun olduðunu ve fonksiyonda kaç adet yorum olduðunu
	 * bilmek için bu fonksiyon kullanýlmýþtýr.
	 */
	private static int kacTaneVar(String regexStr, ArrayList<String> aramaYapilacakMetin) {
		int sayac = 0;

		for (int i = 0; i < aramaYapilacakMetin.size(); i++) {
			Pattern MY_PATTERN = Pattern.compile(regexStr);
			Matcher m = MY_PATTERN.matcher(aramaYapilacakMetin.get(i));
			// Eþleþme olduðunda if bloðuna girilir.
			if (m.find()) {
				sayac++;
			}
		}
		return sayac;
	}

	/*
	 * Verilen metinde aranan ifadenin kaçýncý sýrada olduðunu döndüren
	 * fonksiyondur. Bir sýnýfýn java dosyasýnýn kaçýncý sýrasýnda olduðu öðrenilir,
	 * Bir fonksiyonun ait olduðu sýnýfýnýn kaçýncý sýrasýnda olduðu öðrenilir.
	 */
	private static ArrayList<Integer> kacinciIndis(String regexStr, ArrayList<String> aramaYapilacakMetin) {
		/*
		 * Bulunduðu indisleri tutmak için bir array list tanýmlandý. Kaç tane eþleþme
		 * olmadýðýný bilmediðimizden ötürü array list tanýmlandý.
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
	 * Verilen metinden regex ifadesinin eþleþme yaptýðý blok döndürülür. Örneðin
	 * sýnýflarýn ismi, fonksiyonlarýn ismi ve yorumlarýn içeriklerini almak için bu
	 * fonksiyon kullanýlýr.
	 */
	private static String icerik(String regexStr, String aramaYapilacakSatir) {
		String str = "";
		Pattern MY_PATTERN = Pattern.compile(regexStr);
		Matcher m = MY_PATTERN.matcher(aramaYapilacakSatir);
		if (m.find()) {
			// Diðer fonksiyonlardan farklý olarak group() fonksiyonunu kullanýrýz.
			// Bu fonksiyon eþleþme yapýlan bloðu döndürür.
			str = m.group();
		}
		return str;
	}

	/*
	 * Metinde verilen regex ifadesini çalýþtýrýr. Eþleþme olduðunda true,
	 * olmadýðýnda false döndürür.
	 */
	private static boolean varmi(String regexStr, String aramaYapilacakSatir) {
		Pattern MY_PATTERN = Pattern.compile(regexStr);
		Matcher m = MY_PATTERN.matcher(aramaYapilacakSatir);
		return m.find();
	}

	/*
	 * satirAraligi fonksiyonu verilen metnin içerisinde bulunan sýnýf, fonksiyon
	 * gibi birkaç satýrdan oluþan yapýlarýn kaçýncý satýrda baþlayýp bittiðini
	 * döndürür.
	 */
	private static int[] satirAraligi(String acilisKarakteri, String kapanisKarakteri, int baslangicIndis,
			ArrayList<String> aramaYapilacakMetin) {

		/*
		 * satirAralik[0] --> yapýnýn ilk satýrýnýn indis numarasý satirAralik[1] -->
		 * yapýnýn son satýrýnýn indis numarasý
		 */
		int[] satirAralik = new int[2];

		/*
		 * baslangicIndis parametre olarak alýnýyor ve önceden veriliyor, böylece
		 * yapýnýn nerede baþladýðýný aramakla uðraþýlmýyor. O yapýnýn kaçýncý indisten
		 * baþladýðý bilgisi kacinciIndis fonksiyonu kullanýlarak öðreniliyor.
		 */
		satirAralik[0] = baslangicIndis;

		/*
		 * acikBlokSayisi -1 ile baþlatýlýyor çünkü alttaki if bloðunun içinde bulunan
		 * if acikBlokSayisi = 0 olduðunda çalýþýyor. En baþta bu zaten 0 olacaðý için
		 * hatalý olabilir. Bu hatanýn olma ihtimaline karþýlýk önlem alýnmýþtýr.
		 */
		int acikBlokSayisi = -1;

		// i = baslangicIndis --> yapýnýn öncesi ve sonrasýna bakýlmadan direk
		// baþlangýcý itibariyle bakýlýyor.
		for (int i = baslangicIndis; i < aramaYapilacakMetin.size(); i++) {

			// acilisKarakteri = '{' benzeri bloklarýn baþlangýcýný belirten karakterler
			// olur.
			// eðer o satýrda açýlýþ karakterinden bulunuyorsa
			if (varmi(acilisKarakteri, aramaYapilacakMetin.get(i))) {
				// ilk satýrý mý okuyoruz diye kontrol edilir
				if (i == baslangicIndis)
					// öyleyse +1 eklemek yerine açýk blok sayýsýný 1 yapýyoruz.
					acikBlokSayisi = 1;
				else
					// deðilse bir artýrýyoruz.
					acikBlokSayisi++;
			}
			// kapanisKarakteri = '}' benzeri, bloklarýn kapandýðýný belirten
			// karakterlerdir.
			// o satýrda kapanýþ karakteri varsa
			if (varmi(kapanisKarakteri, aramaYapilacakMetin.get(i))) {
				// açýk blok sayýsý bir azalýr
				acikBlokSayisi--;
				// açýk bloklar bittiyse
				if (acikBlokSayisi == 0) {
					// yapýnýn son satýrýna ulaþýlmýþ demektir.
					satirAralik[1] = i;
					break;
				}
			}
		}
		return satirAralik;
	}

	/*
	 * Sýnýf, fonksiyon yapýlarýný parça parça tutmak için kullanýlan fonksiyon
	 * 
	 * Örneðin Bir sýnýf verilen dosyanýn 3.satýrýnda baþlayýp 25. satýrýnda
	 * bitiyorsa o satýrlar da dahil edilerek arasý alýnýr ve sýnýf yapýsý diðer tüm
	 * satýrlardan ayrýþtýrýlmýþ olur parça halinde tutulur
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
		// Okunacak olan dosyanýn adýný tutacak olan deðiþken
		String file;

		/*
		 * Okunacak dosyayý alma biçimi 3 þekilde olabilir 1. program cmd üzerinden
		 * çalýþtýrýlýrken komut satýrý parametresi olarak verilebilir. 2. program cmd
		 * üzerinden çalýþtýrýlýrken komut satýrý parametresi verilmez, sonradan
		 * kullanýcýdan girdi alýnýr. 3. program eclipse üzerinden çalýþtýrýlýrken komut
		 * satýrý parametresi verilemeyeceði için kullanýcýdan girdi alýnýr.
		 */
		// 1.durum gerçekleþtiyse
		if (args.length != 0) {
			// parametre verilmiþ, dosya adý file deðiþkenine atanýr.
			file = args[0];

			/*
			 * program komut satýrýndan çalýþtýrýldýðý için dist klasörünün içinden açýlmýþ
			 * demektir. o halde bir klasör geri gitmeliyiz, bunun için de ..\ kullanýrýz
			 * ardýndan ana ödev klasöründen records klasörüne gideriz ve dosyanýn bulunduðu
			 * dizine gelmiþ oluruz bunu filePath deðiþkenine kaydederiz
			 */
			filePath = ".." + File.separator + "records" + File.separator;
			// filePath'e file deðiþkeninde tutulan okunmasý istenen dosyanýn adý eklenir
			// böylece okunacak olan dizinin tam yolu elde edilmiþ olur.
			String filePath1 = filePath + file;

			// dosya okunur.
			readFile(filePath1);
		} else {
			// Komut satýrý parametresi verilmemiþ
			Scanner scn = new Scanner(System.in);
			// Dosya adý kullanýcýdan istenir.
			System.out.print("Dosya ismi: ");
			file = scn.nextLine();

			// Bilgisayarda o an bulunduðumuz dizini öðreniriz.
			String suankiDizin = System.getProperty("user.dir");
			/*
			 * try - catch bloklarý oluþturuldu çünkü kullanýcýdan alýnan dosya adý file
			 * path ile birleþtirildiðinde sistem yolu bulamýyor ise 3 ihtimal olabilir 1.
			 * dist klasöründen çalýþtýrýlmýþtýr, bundan dolayý records klasörünü
			 * bulamýyordur, 2. kullanýcý dosyanýn adýný yanlýþ girmiþtir 3. veya öyle bir
			 * dosya records klasörü içerisinde bulunmamaktadýr.
			 */
			try {
				// Eclipse üzerinden çalýþtýrýlma durumunda çalýþýr.
				// dizine records ve dosyanýn ismi de eklenerek dizin tamamlanmýþ olur.
				filePath = suankiDizin + File.separator + "records" + File.separator;
				String filePath1 = filePath + file;
				readFile(filePath1);
			} catch (FileNotFoundException e) {
				// dizin bulunamadý
				try {
					// dist klasöründe bulunan jar dosyasý çalýþtýrýlmýþtýr
					// bir geri gidip ana klasöre dönülür ve records klasörü bulunur.
					filePath = ".." + File.separator + "records" + File.separator;
					// dosyanýn ismi de eklenerek tam dizin tanýmlanmýþ olur.
					String filePath1 = filePath + file;
					readFile(filePath1);
				} catch (FileNotFoundException x) {
					// yine yol bulunamadý.
					// öyleyse kullanýcýya hata mesajý yazmamýz gerekmektedir.
					System.out.println("Sistem yolu bulamýyor.");
					System.out.println("Girdiðiniz dosya ismini kontrol edin.");
					System.out.println("Dosya isminin doðru olduðunu düþünüyorsanýz");
					System.out.println("dosyanýz records klasörü içerisinde bulunmuyor olabilir.");
					System.out.println("Lütfen dosyanýzý records klasörü içinde yerleþtiriniz.");
				}
			}
			scn.close();
		}

		// Dosyada kaç tane sýnýf var?
		Sinif[] siniflar = new Sinif[kacTaneVar("class [a-zA-Z]+", dosyaninSatirlari)];

		// Bu sýnýflar dosyanýn kaçýncý indislerinden baþlýyor?
		ArrayList<Integer> siniflarKacinciIndislerde = new ArrayList<Integer>();
		siniflarKacinciIndislerde = kacinciIndis("class [a-zA-Z]+", dosyaninSatirlari);

		/*
		 * Yorumlarýn tespiti yapýldýktan sonra txt dosyalarýna yazýlacak. Bu dosyalarý
		 * oluþturuyoruz, önceden oluþturulmuþlarsa da içeriklerini siliyoruz.
		 * Oluþtururken yine ayný path'i kullanacaðýz. Yani txt dosyalarý records
		 * klasöründe bulunacak.
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
		 * Sýnýf sayýsý kadar dönecek bir for döngüsü oluþtururuz.
		 */
		for (int i = 0; i < siniflar.length; i++) {
			// Yukarýda tanýmladýðýmýz siniflar dizisinin nesnelerini burada teker teker
			// oluþturuyoruz.
			siniflar[i] = new Sinif();

			// Sinif nesnesinin adýný ayarlýyoruz. Bunun için icerik fonksiyonunu ve regex
			// ifadesi kullanýyoruz.
			siniflar[i].setName(icerik("[^(public)|(private)|(protected) class][a-zA-Z]+",
					dosyaninSatirlari.get(siniflarKacinciIndislerde.get(i))));

			// O sýnýfýn hangi indislerin arasýnda olduðunu buluyoruz.
			int[] satirAraligi = satirAraligi("\\{", "}", siniflarKacinciIndislerde.get(i), dosyaninSatirlari);
			siniflar[i].setSatirAraligi(satirAraligi);

			// O sýnýfýn kod parçacýðýný diðer kodlardan ayrýþtýrýyoruz ve parçayý bir array
			// list'e kaydediyoruz.
			ArrayList<String> sinifinKodu = kodBolme(siniflar[i].getSatirAraligi(), dosyaninSatirlari);

			// O sýnýfta bulunan fonksiyonlarýn sayýsýný tespit ediyoruz.
			int siniftaBulunanFonkSayisi = kacTaneVar(
					"(public|private|protected) ?(static)? (ArrayList<Integer>|ArrayList<String>|int\\[\\]|void|int|float|String|double|boolean)?(\\s)*[a-zA-Z]+[0-9]*\\(.*\\)\\s?(throws)?\\s?[a-zA-Z]{0,}\\s\\{",
					sinifinKodu);
			siniflar[i].setFonksiyonSayisi(siniftaBulunanFonkSayisi);
			System.out.println(
					"Sýnýf:" + siniflar[i].getName() + " -- Fonksiyon Sayýsý: " + siniflar[i].getFonksiyonSayisi());
			System.out.println();

			// Kaç tane fonksiyon varsa o kadar Fonksiyon nesnesi oluþturacaðýz.
			Fonksiyon[] fonksiyonlar = new Fonksiyon[siniflar[i].getFonksiyonSayisi()];

			// Fonksiyonlarýn kaçýncý indislerde olduðunu tespit ediyoruz.
			ArrayList<Integer> fonksiyonlarKacinciIndislerde = new ArrayList<Integer>();
			fonksiyonlarKacinciIndislerde = kacinciIndis(
					"(public|private|protected) ?(static)? (ArrayList<Integer>|ArrayList<String>|int\\[\\]|void|int|float|String|double|boolean)?(\\s)*[a-zA-Z]+[0-9]*\\(.*\\)\\s?(throws)?\\s?[a-zA-Z]{0,}\\s\\{",
					sinifinKodu);

			// O sýnýfta kaç tane fonksiyon varsa o kadar dönecek bir for döngüsü yazýyoruz.
			for (int j = 0; j < siniflar[i].getFonksiyonSayisi(); j++) {
				String fonkAdi = "";

				// Dizi nesnesi olarak tanýmladýðýmýz fonksiyonlarýn þimdi teker teker
				// nesnelerini oluþturmak için
				// Sinif sýnýfýnda bulunan fonkOlustur metodunu kullanýyoruz.
				siniflar[i].fonkOlustur(fonksiyonlar[i]);

				// Fonksiyon adýný regex ifadesi ile çýkartýyoruz.
				fonkAdi = icerik("(?m)(?<=\\bpublic ).*$|(?m)(?<=\\bprivate ).*$|(?m)(?<=\\bprotected ).*$",
						sinifinKodu.get(fonksiyonlarKacinciIndislerde.get(j)));
				// Fonksiyon adýný tüm karakterlerden ayýrýyor ve yalnýzca ismini býrakýyoruz.
				fonkAdi = icerik(".+?(?=\\()", fonkAdi);
				siniflar[i].getFonksiyon(j).setName(fonkAdi);

				// Fonksiyonun kaçýncý indiste baþlayýp bittiðini öðreniyoruz.
				int[] fonkSatirAraligi = satirAraligi("\\{", "}", fonksiyonlarKacinciIndislerde.get(j), sinifinKodu);
				siniflar[i].getFonksiyon(j).setSatirAraligi(fonkSatirAraligi);

				// Fonksiyonun kaç satýr olduðunu hesaplýyoruz.
				int fonkSatirSayisi = siniflar[i].getFonksiyon(j).getSatirAraligi()[1]
						- siniflar[i].getFonksiyon(j).getSatirAraligi()[0];

				// Fonksiyonun adýný ekrana yazdýrýyoruz.
				System.out.println("\tFonksiyon: " + siniflar[i].getFonksiyon(j).getName());

				// Fonksiyonun ilk satýrýný deðiþkende tutuyoruz
				// Böylece yorum ararken fonksiyonda o deðiþkenle gezerek kodu daha okunur
				// yapýyoruz.
				int fonkIlkSatiri = siniflar[i].getFonksiyon(j).getSatirAraligi()[0];
				int x = fonkIlkSatiri;

				// Fonksiyonun üstünde javadoc var mý diye bakýyoruz.
				if (varmi("\\*\\/", sinifinKodu.get(x - 1))) {
					// Fonksiyonun üstünde bulunan javadoc'un sýnýf kod parçacýðýndaki satýr
					// numaralarý kaydedilsin diye array list tanýmlanýr.
					ArrayList<Integer> javadocSatirNolari = new ArrayList<Integer>();
					// Javadoc'un önce bitiþ satýrý kaydedilir çünkü bir fonksiyonun üstündeki
					// javadoc'un önce bitiþ satýrýna ulaþabiliriz.
					int javadocBitisSatir = x - 1;

					// varsa javadoc bitene kadar ilerliyor ve satýrlarý kaydediyoruz
					JavadocYorum jvdYorum = new JavadocYorum();

					// x'i azaltýyoruz ve fonksiyonun üstünde birer birer yukarý çýkartýyoruz.
					// Böylece javadoc'un baþlangýç karakterine gelene kadar içeriði okuyoruz.
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

				// Fonksiyonun içindeki yorumlara bakmak için for döngüsü
				for (int k = 0; k < fonkSatirSayisi; k++) {
					ArrayList<String> fonkKodu = kodBolme(siniflar[i].getFonksiyon(j).getSatirAraligi(), sinifinKodu);

					// Fonksiyonun içinde javadoc var mý diye bakýyoruz.
					if (varmi("\\/\\*\\*", fonkKodu.get(k))) {
						int javadocBaslangicSatir = k;
						// varsa javadoc bitene kadar ilerliyor ve satýrlarý kaydediyoruz
						JavadocYorum jvdYorum = new JavadocYorum();
						while (!varmi("\\*\\/", fonkKodu.get(++k))) {
							jvdYorum.setIcerik(fonkKodu.get(k));
						}
						int javadocBitisSatir = k;
						siniflar[i].getFonksiyon(j).setJavadocYorum(jvdYorum);
						siniflar[i].getFonksiyon(j).getJavadocYorum()
								.setSatirSayisi(javadocBitisSatir - javadocBaslangicSatir);
					}

					// Fonksiyonun içinde tek satýrlý yorum var mý diye bakýyoruz.
					if (varmi("\\/\\/", fonkKodu.get(k))) {
						TekSatirliYorum tekSatirYorum = new TekSatirliYorum();
						if (varmi("\\/\\/$", fonkKodu.get(k)))
							tekSatirYorum.setIcerik(icerik("\\/\\/[\\sA-Za-z_ðüþýöçÐÜÞÝÖÇ]+\\/\\/", fonkKodu.get(k)));
						else if (varmi("^\\/\\/", fonkKodu.get(k)))
							tekSatirYorum.setIcerik(fonkKodu.get(k));
						else
							tekSatirYorum.setIcerik(icerik("\\/\\/.*", fonkKodu.get(k)));
						siniflar[i].getFonksiyon(j).setTekSatirYorum(tekSatirYorum);
					}

					// Fonksiyonun içinde çok satýrlý yorum var mý diye bakýyoruz.
					if (varmi("\\/\\*$|\\/\\*[\\sA-Za-z_ðüþýöçÐÜÞÝÖÇ0-9]+", fonkKodu.get(k))) {
						int cokSatirliBaslangicSatir = k;
						int cokSatirliBitisSatir = -1;
						CokSatirliYorum cokSatirYorum = new CokSatirliYorum();

						/*
						 * Çok satýrlý yorum'un üç çeþidi bulunur.
						 * 
						 * 1. aþaðýdaki gibi tek bir satýrda bitebilir 
						 * 		/* abc */

						/*
						 * 2. aþaðýdaki gibi ilk satýrýnda da içeriði olabilir 
						 * 		/* abc
						 	 	 */

						/*
						 * 3. aþaðýdaki gibi olabilir 
						 * 
						 * 		/* 
						 * 		 * abc
						 		 */

						// 1.durum
						if (varmi("\\/\\*[\\sA-Za-z_ðüþýöçÐÜÞÝÖÇ]+\\*\\/", fonkKodu.get(k))) {
							// Tek satýrda toplanmýþ olan çok satýrlý yapýsýndaki yorumun içeriði alýnýr.
							cokSatirYorum.setIcerik(icerik("\\/\\*.*?\\*\\/", fonkKodu.get(k)));
							// Yorum nesnesi fonksiyon nesnesine eklenir.
							siniflar[i].getFonksiyon(j).setCokSatirYorum(cokSatirYorum);
							// Bu yorumun satýr sayýsý birdir.
							siniflar[i].getFonksiyon(j).getCokSatirYorum().setSatirSayisi(1);
							cokSatirliBitisSatir = k;
						} else if (varmi("\\/\\*[\\sA-Za-z_ðüþýöçÐÜÞÝÖÇ]+", fonkKodu.get(k))) {
							// 2.durum
							if (!varmi("\\/\\*[\\s]{2,}", fonkKodu.get(k))) {
								// Eðer "/*" 'ndan sonra 2 veya daha fazla kere boþluk yoksa (hata kontrolü diye
								// düþünülebilir.)
								while (!varmi("\\*\\/", fonkKodu.get(k))) {
									// Yorumun kapanýþ karakterleri yani */ gelene kadar yorum içeriði kaydedilmeye
									// devam eder.
									cokSatirYorum.setIcerik(fonkKodu.get(k++));
								}
								// Kapanýþ karakterleri gelince yorumun son satýrýna ulaþýldýðý için
								// bitisSatir deðiþkenine deðer atamasý yapýlýr.
								cokSatirliBitisSatir = k;
								// Oluþturulan yorum nesnesi fonksiyon nesnesine eklenir.
								siniflar[i].getFonksiyon(j).setCokSatirYorum(cokSatirYorum);
								// Çok satýrlý yorumun kaç satýrdan oluþtuðu bilgisi yorum nesnesinin özelliðine
								// yüklenir.
								siniflar[i].getFonksiyon(j).getCokSatirYorum()
										.setSatirSayisi(cokSatirliBitisSatir - cokSatirliBaslangicSatir);
							}
						} else if (varmi("\\/\\*[\\s]{2,}", fonkKodu.get(k)) || varmi("\\/\\*$", fonkKodu.get(k))) {
							// 3.durum
							while (!varmi("\\*\\/", fonkKodu.get(k))) {
								// Çok satýrlý yorum kapanýþ karakterlerinin gelmesine kadar içeriði okunur ve
								// nesnenin yapýsýna eklenir.
								cokSatirYorum.setIcerik(fonkKodu.get(++k));
							}
							// Çok satýrlý yorum bitmiþtir ve bitiþ satýrý deðeri deðiþkene atanýr.
							cokSatirliBitisSatir = k;
							// Yorum nesnemiz fonksiyon nesnesinin yapýsýna eklenir.
							siniflar[i].getFonksiyon(j).setCokSatirYorum(cokSatirYorum);
							siniflar[i].getFonksiyon(j).getCokSatirYorum()
									.setSatirSayisi(cokSatirliBitisSatir - cokSatirliBaslangicSatir);
						}
					}
				}
				// Ekrana yazdýrýlacak olan bilgiler
				// Fonksiyonda bulunan tek satýrlý yorum sayýsý
				// Fonksiyonda bulunan çok satýrlý yorum sayýsý
				// Fonksiyonda bulunan javadoc yorum sayýsý
				System.out.println(
						"\t\tTek Satýrlý Yorum Sayýsý: " + siniflar[i].getFonksiyon(j).getTekSatirYorumSayisi());
				System.out.println(
						"\t\tÇok Satýrlý Yorum Sayýsý: " + siniflar[i].getFonksiyon(j).getCokSatirYorumSayisi());
				System.out.println("\t\tJavadoc Yorum Sayýsý: " + siniflar[i].getFonksiyon(j).getJavadocYorumSayisi());
				System.out.println();
			}
			System.out.println("----------------------------------------------------");

			// Txt dosyalarýna bilgileri yazdýrmak için aþaðýdaki fonksiyon çalýþtýrýlýr.
			siniflar[i].fileWriter();
		}
	}

}
