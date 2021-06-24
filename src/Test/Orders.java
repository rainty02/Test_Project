package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {

	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Sale> list ;
	static String memId =  "park1234";

	public static void main(String[] args) {

		int americano = 4100;
		int latte = 4600;
		int sandwich =6200;
		int salad = 5000;
		int cake= 5500;

		list = new ArrayList<>();
		
		System.out.println("주문하기");

		System.out.println("메뉴 입니다.");
		System.out.println("------------------------------------");
		System.out.println("1. Amerciano : 4100");
		System.out.println("2. Latte : 4600");
		System.out.println("3. Sandwich : 6200");
		System.out.println("4. salad : 5000");
		System.out.println("5. cake : 5500");
		System.out.println("6. 주문 완료");
		System.out.println("------------------------------------");

		//while(true)
		System.out.println("원하시는 메뉴의 번호와 수량을 입력하세요.");
		System.out.println("예시)1 3");
		String inputData = scanner.nextLine();
		String[] inputDatas = inputData.split(" ");

		//샀을 때 sale객체로 정보 넣기
		

		switch(inputDatas[0]) {

		case "1":
			list.add(new Sale("americano", Integer.parseInt(inputDatas[1])*americano));
			System.out.println("americano"+ inputDatas[1]+"잔 주문");
			break;
		case "2":
			list.add(new Sale("latte", Integer.parseInt(inputDatas[1])*latte));
			System.out.println("latte"+ inputDatas[1]+"잔 주문");
			break;
		case "3":
			list.add(new Sale("sandwich", Integer.parseInt(inputDatas[1])*sandwich));
			System.out.println("sandwich"+ inputDatas[1]+"개 주문");
			break;
		case "4":
			list.add(new Sale("salad", Integer.parseInt(inputDatas[1])*salad));
			System.out.println("salad"+ inputDatas[1]+"개 주문");
			break;
		case "5": 
			list.add(new Sale("cake", Integer.parseInt(inputDatas[1])*cake));
			System.out.println("cake"+ inputDatas[1]+"개 주문");
			break;
		case "6":
			System.exit(0);
		}
	
//------------------------------------------------------------------------------------------------------------------------------
		//JAVA -> DB
		// 자바에서 입력받은 데이터를 DB로 저장한다. 
		// 이 부분을 메소드로 saleDao 로 넣기
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");


			//2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";

			conn= DriverManager.getConnection(jdbcUrl, user, pw);
			int result = 0;

			// sql 작성 ->list 반복하기
			for(int i = 0; i <list.size(); i++) {
				String sql = "insert into sale (salecode, sname, price) values(sale_salecode_seq.nextval, ?, ?)";
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1,list.get(i).getSname());
				pstmt.setInt(2, list.get(i).getPrice());
				result = pstmt.executeUpdate();
			}

			if(result > 0) {
				System.out.println("입력되었습니다.");
			}else {
				System.out.println("입력실패!");
			}

//--------------------------------------------------------------------------------------------------------------------------------------				
			System.out.println("----------------------------------------");
			int totalPrice = 0;

			for(int i = 0; i <list.size(); i++) {
				totalPrice += list.get(i).getPrice();
			}


			int point = (int)(totalPrice * 0.01);

			System.out.println("총 예상 결제 금액: " +totalPrice +"원 입니다.");	
			System.out.println("총 예상 적립 포인트:"+ point +"점입니다."); 

//--------------------------------------------------------------------------------------------------------------------------------------				

			System.out.println("---------------------------------------");


			// 회원 DB에서 point를 read하기

			String readPoint = "select point from member where id = ?";
			pstmt= conn.prepareStatement(readPoint);
			
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			int currPoint = 0;
			while(rs.next()) {
				currPoint = rs.getInt("point");
			}
			
			
			System.out.println("현재 사용가능한 포인트: " + currPoint);
			System.out.println("포인트를 사용하시겠습니까? 1. 예 2. 아니오");
			System.out.println("(포인트를 사용할시 현재 결제하시는 상품의 포인트는 적립이 되지 않습니다.)");
			//int answer = Integer.parseInt(scanner.nextLine());
//---------------------------------------------------------------------------------------------------------
			System.out.println("------------------------------------");
			
			//DB에 포인트 적립하기 ->update 하기
			String updatePoint = "update member set point= point + ? where id = ? ";
			pstmt = conn.prepareStatement(updatePoint);
			
			pstmt.setInt(1, point);
			pstmt.setString(2, memId);
			
			result = pstmt.executeUpdate();
			
			
			if(result > 0) {
				System.out.println("업데이트 완료");
			}else {
				System.out.println("업데이트 실패");
			}
			

			System.out.println("포인트가 "+point+"점 적립되어"+ (currPoint+point)+" 점 있습니다.");
			


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
