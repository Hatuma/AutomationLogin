package mscwork.db;

public enum FilePathEnum {

	NEXT("src/db/DBnext.json"),
	SUBMIT("src/db/DBsubmit.json"),
	PASSWORD("src/db/DBpassword.json"),
	USERNAME("src/db/DBusername.json"),
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
