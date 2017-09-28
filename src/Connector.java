
public class Connector {
	private ConnectionType connectionType;
	private Gender gender;
	
	public Connector(ConnectionType connectionType, Gender gender) {
		this.connectionType = connectionType;
		this.gender = gender;
	}
	
	public static Connector defaultConnector() {
		return new Connector(ConnectionType.Diamond, Gender.Male);
	}
	
	public boolean fits(Connector b) {
		return getConnectionType() == b.getConnectionType()
				&& getGender() != b.getGender();
	}

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
