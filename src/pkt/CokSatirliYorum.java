package pkt;

import java.util.ArrayList;

public class CokSatirliYorum {
	/*
	 * Bir çok satýrlý yorumun	satýr sayýsý,
	 * 							içeriði
	 * bulunur.
	 */
	private int satirSayisi;
	private ArrayList<String> icerik = new ArrayList<String>();

	public void setSatirSayisi(int strSayi) {
		this.satirSayisi = strSayi;
	}

	public int getSatirSayisi() {
		return satirSayisi - 1;
	}

	public void setIcerik(String icerikSatiri) {
		this.icerik.add(icerikSatiri);
	}

	public String getSatirdakiIcerik(int satir) {
		return icerik.get(satir);
	}
}
