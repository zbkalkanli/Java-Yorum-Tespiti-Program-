package pkt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sinif {
	/*
	 * Bir s�n�f�n 	ismi,
	 * 				sat�r aral���,
	 * 				fonksiyonlar�,
	 * 				fonksiyon say�s�
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

	// Fonksiyon olu�turulan metot
	public void fonkOlustur(Fonksiyon fonk) {
		fonk = new Fonksiyon();
		fonksiyonlar.add(fonk);
	}

	public Fonksiyon getFonksiyon(int indis) {
		return fonksiyonlar.get(indis);
	}

	// txt dosyalar�na yazd�rma metodu
	// Bu metotta txt dosyalar� olu�turulur ve her txt'nin ba��na s�n�f ismi yaz�l�r.
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

		myWriter.write("------------------------------------S�n�f:" + isim + "------------------------------------\n");
		myWriter1.write("------------------------------------S�n�f:" + isim + "------------------------------------\n");
		myWriter2.write("------------------------------------S�n�f:" + isim + "------------------------------------\n");

		myWriter.close();
		myWriter1.close();
		myWriter2.close();

		for (int i = 0; i < fonksiyonlar.size(); i++)
			fonksiyonlar.get(i).yazdir();

	}
}
