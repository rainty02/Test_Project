package Test_627;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Point {
	// Point 클래스 정의
	// 1. Point 읽어온다 -> readPoint -> read (select)
	// 2. Point 적립한다. -> savePoint -> update
	// 3. Point 사용한다. ->usePoint ->update
	// 파라미터로 currentId를 받아온다. 
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	int readPoint(String currentId) {
		int havePoint = 0;

		try {
			//1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");


			//2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";


			conn= DriverManager.getConnection(jdbcUrl, user, pw);
			String readPoint = "select point from member where id = ?";
			pstmt= conn.prepareStatement(readPoint);

			pstmt.setString(1, currentId);
			rs = pstmt.executeQuery();


			while(rs.next()) {
				havePoint = rs.getInt("point");

			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("현재 사용가능한 포인트: " + havePoint);
		return havePoint;
	}



	void savePoint(String currentId, int expectedPoint) {
		 //포인트를 적립한다. savePoint = 원래 가지고 있는 point +expectedPoint
		
		try {
			//1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			String updatePoint = "update member set point= point + ? where id = ? ";
			pstmt = conn.prepareStatement(updatePoint);
			int result = 0;

			pstmt.setInt(1, expectedPoint);
			pstmt.setString(2, currentId);

			result = pstmt.executeUpdate();


//			if(result > 0) {
//				System.out.println("업데이트 완료");
//			}else {
//				System.out.println("업데이트 실패");
//			}
			
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("포인트가 "+expectedPoint+"점 적립되었습니다.");
		
	}


	
	 void usePoint(String currentId, int totalPrice) {
		// 포인트를 사용한다. 
		// 가지고 있는 포인트가 결제 금액 보다 클 때 호출한다.
		// beforePoint >= totalPrice
		
		try {
			//1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			// Point > toalPrice
			String updatePoint = "update member set point= point - ? where id = ? ";
			pstmt = conn.prepareStatement(updatePoint);
			int result = 0;
			
			
			//point = point -  havePoint 포인트 사용하기! 마이너스 시키기
			pstmt.setInt(1, totalPrice);
			pstmt.setString(2, currentId);

			result = pstmt.executeUpdate();
			
//			
//			if(result > 0) {
//				System.out.println("업데이트 완료");
//			}else {
//				System.out.println("업데이트 실패");
//			}
//			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	 void usePoint2(String currentId) {
		// 포인트를 사용한다. 
		// 가지고 있는 포인트보다 결제 금액이 클때 호출 ->모든 포인트 다쓰고 0이된다. point = 0
		// beforePoint < totalPrice
		
		try {
			//1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			// Point > toalPrice
			String updatePoint = "update member set point= ? where id = ? ";
			pstmt = conn.prepareStatement(updatePoint);
			int result = 0;
			
			
			//모든
			pstmt.setInt(1, 0);
			pstmt.setString(2, currentId);

			result = pstmt.executeUpdate();
			
//			
//			if(result > 0) {
//				System.out.println("업데이트 완료");
//			}else {
//				System.out.println("업데이트 실패");
//			}
//			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}


	
}