package pkt;

public class TekSatirliYorum {
	/*
	 * Tek satýrlý bir yorumun içeriði bulunur.
	 */
	private String icerik = new String();

	public void setIcerik(String icerikSatiri) {
		this.icerik = icerikSatiri;
	}

	public String getSatirdakiIcerik() {
		return icerik;
	}
}
