package Test;

public class Sale {
	//판매 된 데이터 저장 하기 위해

	private int salecode; // 판매코드
	private String sname; // 메뉴이름
	private int price; // 가격
	private String saledate; // 판매된 날짜
	private int count; // 인기순위 매기기 위한 변수
	
	private String pName; // 메뉴 이름 (메뉴당)
	private int pNumSales; // 메뉴 당 몇개를 팔았는지 count값
	private int pSalePrice; // 메뉴 당 판매액 sum 값
	
	private String id; // 고객 아이디
	
	

	//생성자 1 : DB에서 전체 SaleTable가져올 때 사용
	public Sale(int salecode, String sname, int price, String saledate) {
		this. salecode = salecode;
		this.sname = sname;
		this.price= price;
		this.saledate = saledate;
	}
	//생성자 1-6/25일 추가 : 테스트 생성자, SaleTable전체 리스트 가져올 때 회원 아이디 추가
	public Sale(int salecode, String sname, int price, String saledate, String id) {
		this. salecode = salecode;
		this.sname = sname;
		this.price= price;
		this.saledate = saledate;
		this.id = id;
	}
	
	//생성자 오버로딩 2 : SaleDB에 저장하게 될 때 사용자
	public Sale(String sname, int price) { 
		this.sname = sname;
		this.price= price;
	}
	
	//생성자 오버로딩 2-6/25일 추가 ->Sale DB에 저장하게 될 때 사용자 아이디도 같이 저장하기 2021.06.25
	public Sale(String sname, int price, String id) { 
		this.sname = sname;
		this.price= price;
		this.id= id;
	}
	
	// 생성자 오버로딩 3 -> saleBestManager에 쓸 수 있는 생성자
	
	public Sale( int count, String sname) {
		this.sname = sname;
		this.count = count;
	}
	
	// 생성자 오버로딩4 -> MenuSalePrice/ 메뉴당 판매액과 판매수 확인할 때 사용하는 생성자
	public Sale(String pName, int pNumSales, int pSalePrice) {
		this.pName = pName;
		this.pNumSales = pNumSales;
		this.pSalePrice = pSalePrice;
	}
	

	//Getter, Setter 
	public int getSalecode() {
		return salecode;
	}

	public void setSalecode(int salecode) {
		this.salecode = salecode;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSaledate() {
		return saledate;
	}

	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	
	// 추가 21.06.24
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String getpName() {
		return pName;
	}

	//추가2 21.06.25
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
	
	//추가 데이터 21.06.25
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

}