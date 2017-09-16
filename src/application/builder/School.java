package application.builder;

public enum School {
	DARK(SchoolType.MAIOR_SCHOOL), 
	HOLY(SchoolType.MAIOR_SCHOOL), 
	MIND(SchoolType.MAIOR_SCHOOL), 
	WAR(SchoolType.MAIOR_SCHOOL), 
	ARCANE(SchoolType.MAIOR_SCHOOL), 
	NATURE(SchoolType.MAIOR_SCHOOL), 
	FIRE(SchoolType.MINOR_SCHOOL), 
	WATER(SchoolType.MINOR_SCHOOL), 
	EARTH(SchoolType.MINOR_SCHOOL), 
	AIR(SchoolType.MINOR_SCHOOL);
	
	public SchoolType schoolType;
	
	private School(SchoolType schoolType) {
		this.schoolType = schoolType;
	}
}
