package Test;

public class Sale {
	//판매 된 데이터 저장 하기 위해

	private int salecode;
	private String sname;
	private int price;
	private String saledate;
	private String id;
			
	public Sale(int salecode, String sname, int price, String saledate) {
		this. salecode = salecode;
		this.sname = sname;
		this.price = price;
		this.saledate = saledate;
	}
	
	
	//생성자 오버로딩 2
	public Sale(String sname, int price) { 
		this.sname = sname;
		this.price = price;
	}
	
	public Sale(String sname, int price, String id) {
		this.sname = sname;
		this.price = price;
		this.id = id;	
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

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
}