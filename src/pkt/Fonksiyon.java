package pkt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fonksiyon {
	/*
	 * Bir fonksiyonun ismi, satýr aralýðý, javadoc yorum sayýsý, tek satýrlý
	 * yorumlarýnýn sayýsý, çok satýrlý yorumlarýnýn sayýsý, javadoc yorumlarý, tek
	 * satýrlý yorumlarý, çok satýrlarý yorumlarý bulunur.
	 */
	private String isim;
	private int[] satirAraligi = new int[2];
	private int javadocYorumSayisi = -1;
	private int tekSatirYorumSayisi = -1;
	private int cokSatirYorumSayisi = -1;
	private JavadocYorum[] javadocYorumlar = new JavadocYorum[100];
	private TekSatirliYorum[] tekSatirliYorumlar = new TekSatirliYorum[100];
	private CokSatirliYorum[] cokSatirliYorumlar = new CokSatirliYorum[100];

	public void setName(String isim) {
		this.isim = isim;
	}

	public String getName() {
		return isim;
	}

	public void setSatirAraligi(int[] satirAraligi) {
		this.satirAraligi = satirAraligi;
	}

	public int[] getSatirAraligi() {
		return satirAraligi;
	}

	// tek satýrlý
	public void setTekSatirYorum(TekSatirliYorum tekStrYrm) {
		tekSatirliYorumlar[++tekSatirYorumSayisi] = tekStrYrm;
	}

	public TekSatirliYorum getTekSatirYorum() {
		return tekSatirliYorumlar[tekSatirYorumSayisi];
	}

	public int getTekSatirYorumSayisi() {
		return tekSatirYorumSayisi + 1;
	}

	// çok satýrlý
	public void setCokSatirYorum(CokSatirliYorum cokSatirYrm) {
		cokSatirliYorumlar[++cokSatirYorumSayisi] = cokSatirYrm;
	}

	public CokSatirliYorum getCokSatirYorum() {
		return cokSatirliYorumlar[cokSatirYorumSayisi];
	}

	public int getCokSatirYorumSayisi() {
		return cokSatirYorumSayisi + 1;
	}

	// javadoc
	public void setJavadocYorum(JavadocYorum jvdcYorum) {
		javadocYorumlar[++javadocYorumSayisi] = jvdcYorum;
	}

	public JavadocYorum getJavadocYorum() {
		return javadocYorumlar[javadocYorumSayisi]; // bu kod sadece javadoc yorum nesnesinin oluþturulduðu anda o anki
													// indisteki yorumu getirerek çalýþýr.
	}

	public int getJavadocYorumSayisi() {
		return javadocYorumSayisi + 1;
	}

	private static String icerik(String regexStr, String aramaYapilacakSatir) {
		String str = "";
		Pattern MY_PATTERN = Pattern.compile(regexStr);
		Matcher m = MY_PATTERN.matcher(aramaYapilacakSatir);
		if (m.find()) {
			str = m.group();
		}
		return str;
	}

	// txt dosyalarýna yorumlarý yazdýrma metodu
	// yazdýrma sýrasýnda yorumlar baþlarýndaki * /* */ // gibi karakterlerden
	// temizlenir.
	public void yazdir() {
		String filePath = Program.getFilePath();
		String txtPath;

		try {
			String txtName = "javadocYorumlar.txt";
			txtPath = filePath + txtName;
			FileWriter myWriter = new FileWriter(txtPath, true);
			if (javadocYorumSayisi != -1) {
				myWriter.write("\n");
				myWriter.write("\tFonksiyon:" + isim + "\n");
				myWriter.write("\n");
				for (int i = 0; i <= javadocYorumSayisi; i++) {
					myWriter.write("\t\t" + (i + 1) + ".Javadoc Yorum:\n");
					for (int j = 0; j < javadocYorumlar[i].getSatirSayisi(); j++) {
						myWriter.write("\t\t\t" + icerik("([\\'\\\"a-zA-Z0-9@ÇÖÜÝÞÐýöüðþç\\.]+\\s{0,})+",
								javadocYorumlar[i].getSatirdakiIcerik(j)) + "\n");
					}
					myWriter.write("\n");
				}
			}

			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			String txtName = "cokSatirliYorumlar.txt";
			txtPath = filePath + txtName;
			FileWriter myWriter = new FileWriter(txtPath, true);

			if (cokSatirYorumSayisi != -1) {
				myWriter.write("\n");
				myWriter.write("\tFonksiyon:" + isim + "\n");
				myWriter.write("\n");
				for (int i = 0; i <= cokSatirYorumSayisi; i++) {
					myWriter.write("\t\t" + (i + 1) + ".Çok Satýrlý Yorum:\n");
					for (int j = 0; j <= cokSatirliYorumlar[i].getSatirSayisi(); j++)
						myWriter.write("\t\t\t" + icerik("([\\'\\\"a-zA-Z0-9@ÇÖÜÝÞÐýöüðþç\\.]+\\s{0,})+",
								cokSatirliYorumlar[i].getSatirdakiIcerik(j)) + "\n");
					myWriter.write("\n");
				}
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			String txtName = "tekSatirliYorumlar.txt";
			txtPath = filePath + txtName;
			FileWriter myWriter = new FileWriter(txtPath, true);
			if (tekSatirYorumSayisi != -1) {
				myWriter.write("\n");
				myWriter.write("\tFonksiyon:" + isim + "\n");
				myWriter.write("\n");
				for (int i = 0; i <= tekSatirYorumSayisi; i++) {
					myWriter.write("\t\t" + (i + 1) + ".Tek Satýrlý Yorum: \n\t\t\t"
							+ icerik("([\\'\\\"a-zA-Z0-9@ÇÖÜÝÞÐýöüðþç\\.]+\\s{0,})+",
									tekSatirliYorumlar[i].getSatirdakiIcerik())
							+ "\n");
					myWriter.write("\n");
				}
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}
