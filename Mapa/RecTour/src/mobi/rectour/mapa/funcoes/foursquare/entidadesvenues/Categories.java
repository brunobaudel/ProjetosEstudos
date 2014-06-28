package mobi.rectour.mapa.funcoes.foursquare.entidadesvenues;

public class Categories {
	private Icon icon;
	private String id;
	private String name;
	private String pluralName;
	private boolean primary;
	private String shortName;

	public Icon getIcon() {
		return this.icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPluralName() {
		return this.pluralName;
	}

	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
	}

	public boolean getPrimary() {
		return this.primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
