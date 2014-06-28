package mobi.rectour.mapa.funcoes.foursquare.entidadesvenues;

import java.util.List;

public class Venues {
	private List categories;
	private Contact contact;
	private HereNow hereNow;
	private String id;
	private Location location;
	private String name;
	private String referralId;
	private Stats stats;
	private boolean verified;

	public List getCategories() {
		return this.categories;
	}

	public void setCategories(List categories) {
		this.categories = categories;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public HereNow getHereNow() {
		return this.hereNow;
	}

	public void setHereNow(HereNow hereNow) {
		this.hereNow = hereNow;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReferralId() {
		return this.referralId;
	}

	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}

	public Stats getStats() {
		return this.stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public boolean getVerified() {
		return this.verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
