package pkt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sinif {
	/*
	 * Bir sýnýfýn 	ismi,
	 * 				satýr aralýðý,
	 * 				fonksiyonlarý,
	 * 				fonksiyon sayýsý
	 * bulunur.
	 */
	private String isim;
	private int[] satirAraligi = new int[2];
	ArrayList<Fonksiyon> fonksiyonlar = new ArrayList<Fonksiyon>();
	private int fonksiyonSayisi;

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

	public void setFonksiyonSayisi(int fonkSayisi) {
		this.fonksiyonSayisi = fonkSayisi;
	}

	public int getFonksiyonSayisi() {
		return fonksiyonSayisi;
	}

	// Fonksiyon oluþturulan metot
	public void fonkOlustur(Fonksiyon fonk) {
		fonk = new Fonksiyon();
		fonksiyonlar.add(fonk);
	}

	public Fonksiyon getFonksiyon(int indis) {
		return fonksiyonlar.get(indis);
	}

	// txt dosyalarýna yazdýrma metodu
	// Bu metotta txt dosyalarý oluþturulur ve her txt'nin baþýna sýnýf ismi yazýlýr.
	public void fileWriter() throws IOException {
		String filePath = Program.getFilePath();
		String txtPath;
		String txtName = "tekSatirliYorumlar.txt";
		txtPath = filePath + txtName;
		FileWriter myWriter = new FileWriter(txtPath, true);

		txtName = "cokSatirliYorumlar.txt";
		txtPath = filePath + txtName;
		FileWriter myWriter1 = new FileWriter(txtPath, true);

		txtName = "javadocYorumlar.txt";
		txtPath = filePath + txtName;
		FileWriter myWriter2 = new FileWriter(txtPath, true);

		myWriter.write("------------------------------------Sýnýf:" + isim + "------------------------------------\n");
		myWriter1.write("------------------------------------Sýnýf:" + isim + "------------------------------------\n");
		myWriter2.write("------------------------------------Sýnýf:" + isim + "------------------------------------\n");

		myWriter.close();
		myWriter1.close();
		myWriter2.close();

		for (int i = 0; i < fonksiyonlar.size(); i++)
			fonksiyonlar.get(i).yazdir();

	}
}
