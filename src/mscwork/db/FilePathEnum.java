package mscwork.db;

public enum FilePathEnum {

	NEXT("src/mscwork/db/DBnext.json"),
	SUBMIT("src/mscwork/db/DBsubmit.json"),
	PASSWORD("src/mscwork/db/DBpassword.json"),
	USERNAME("src/mscwork/db/DBusername.json"),
	;
	
	private FilePathEnum(String filepath) {
		this.filepath = filepath;
	}

	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}
