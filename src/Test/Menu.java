package Test;

public class Menu {

	private int menuCode;
	private int rowNum;
	private String mname;
	private int price;

	public Menu(int menuCode, int rowNum, String mname, int price) {
		this.menuCode = menuCode;
		this.rowNum = rowNum;
		this.mname = mname;
		this.price = price;
	}
	
	public Menu(int rowNum, String mname, int price) {
		this.rowNum = rowNum;
		this.mname = mname;
		this.price = price;
	}
	
	public Menu(String mname, int price) {
		this.mname = mname;
		this.price = price;
	}
	
	
	public Menu() {}
	
//	public Menu(int menuCode, String menuName, int price) {
//		this.menuCode = menuCode;
//		this.mname = mname;
//		this.price = price;
//	}

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

	@Override
	public String toString() {
		if(mname.length() >= 8) {
			return rowNum +".\t" + mname + "\t" + price;
		} else {
			return rowNum + ".\t" + mname + "\t\t" + price;
		}
	}
}