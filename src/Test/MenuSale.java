package Test;

public class MenuSale {
	// 메뉴당 판매수와 판매액 확인하기 위해 만든 클래스
	
	
	//Menu == Product
	private String pName; // 메뉴 이름
	private int pNumSales; // 메뉴 당 몇개를 팔았는지
	private int pSalePrice; // 메뉴 당 판매액
	
	
	
	public MenuSale(String pName, int pNumSales, int pSalePrice) {
		this.pName = pName;
		this.pNumSales = pNumSales;
		this.pSalePrice = pSalePrice;
	}


	// getter, Setter
	
	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}



	public int getpNumSales() {
		return pNumSales;
	}



	public void setpNumSales(int pNumSales) {
		this.pNumSales = pNumSales;
	}



	public int getpSalePrice() {
		return pSalePrice;
	}



	public void setpSalePrice(int pSalePrice) {
		this.pSalePrice = pSalePrice;
	}
	
	
	
}