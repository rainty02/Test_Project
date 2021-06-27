package Test_627;

public class Menu {				// 메뉴 변수, 생성자 정의

	private int menuCode;		// 메뉴 고유번호
	private int rowNum;			// 행번호
	private String mname;		// 메뉴 이름
	private int price;			// 가격
	private String editName;	// 수정하려는 이름

	// 생성자 1 : 관리자가  메뉴 전체 테이블 조회하기 위한 생성자
	public Menu(int menuCode, int rowNum, String mname, int price) {
		this.menuCode = menuCode;
		this.rowNum = rowNum;
		this.mname = mname;
		this.price = price;
	}
	
	// 생성자 2  : 메뉴 출력 위한 생성자
	public Menu(int rowNum, String mname, int price) {
		this.rowNum = rowNum;
		this.mname = mname;
		this.price = price;
	}
	
	// 생성자 3 : 관리자가  메뉴 수정을 위한 생성자
	public Menu(String mname, String editName, int price) {
		this.mname = mname;
		this.editName = editName;
		this.price = price;
	}
	
	// 생성자 4 : 관리자가  메뉴 추가를 위한 생성자
	public Menu(String mname, int price) {
		this.mname = mname;
		this.price = price;
	}
			
	// 생성자 5 : 기본 생성자
	public Menu() {}
	
	// getter, setter
	public String getEditName() {
		return editName;
	}

	public void setEditName(String editName) {
		this.editName = editName;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}
		
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// toString
	@Override
	public String toString() {
		if(mname.length() >= 8) {
			return rowNum +".\t" + mname + "\t" + price;
		} else {
			return rowNum + ".\t" + mname + "\t\t" + price;
		}
	}
}