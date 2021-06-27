package Test_627;

public class Member {		// 회원정보 변수, 생성자 정의

	private int memcode; 	// 회원의 고유번호
	private String name; 	// 이름
	private String id;		// 아이디
	private String pw;		// 비밀번호
	private String address;	// 주소
	private String phone;	// 전화번호
	private int point;		// 포인트

	// 생성자 1 : 관리자가  회원 전체 테이블 조회하기 위한 생성자
	public Member(int memcode, String name, String id, String pw, String address, String phone, int point) {
		this.memcode = memcode;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.address = address;
		this.phone = phone;
		this.point = point;
	}
	
	// 생성자 2: 회원이 내 정보 확인하기 위한 생성자
	public Member(String name, String id, String pw, String address, String phone) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.address = address;
		this.phone = phone;
	}
	
	// 생성자 3 : 회원이 내 정보 수정할 때 사용하기 위한 생성자
	public Member(String name, String pw, String address, String phone) {
		this.name = name;
		this.pw = pw;
		this.address = address;
		this.phone = phone;
		
	}

	// 생성자 4: 아이디 체크
	public Member(String id) {
		this.id = id;
	}
		
	// 생성자 5 : 관리자모드에서 관리자가 점포 확인할 때
	public Member(String address, String phone) {
		this.address = address;
		this.phone=phone;
	}
		
	// getter, setter
	public int getMemcode() {
		return memcode;
	}

	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	// toString
	@Override
	public String toString() {
		return "Member [memcode=" + memcode + ", name=" + name + ", id=" + id + ", pw=" + pw + ", address=" + address
				+ ", phone=" + phone + ", point=" + point + "]";
	}
}