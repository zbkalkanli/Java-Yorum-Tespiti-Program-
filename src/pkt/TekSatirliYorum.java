package pkt;

public class TekSatirliYorum {
	/*
	 * Tek sat�rl� bir yorumun i�eri�i bulunur.
	 */
	private String icerik = new String();

	public void setIcerik(String icerikSatiri) {
		this.icerik = icerikSatiri;
	}

	public String getSatirdakiIcerik() {
		return icerik;
	}
}
