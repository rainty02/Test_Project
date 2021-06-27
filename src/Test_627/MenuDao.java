package Test_627;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDao {

/*
	MemberDao 클래스 정의 (메소드명 : 메소드기능)
	
	1. ArrayList<Menu> getList(Connection con) : 전체 메뉴 데이터 반환 (관리자용 / 회원 주문시 메뉴출력)
	2. int insertMenu(Connection con, Menu menu) : 메뉴 데이터 입력 (관리자용)	
	3. int editMenu(Connection con, Menu menu) : 메뉴 데이터 수정 (관리자용)	
	4. int deleteMenu(Connection con, String menuName) : 메뉴 데이터 삭제 (관리자용)
	5. void close() : PreparedStatement close
	
 */
	
	private PreparedStatement pstmt = null;

	// 싱글톤 패턴
	private MenuDao() {
	}
	static private MenuDao dao = new MenuDao();
	public static MenuDao getInstance() {
		return dao;
	}

	// 1. 전체 메뉴 데이터 반환
	// 반환 타입 List<Dept>
	// 매개변수 - Connection 객체 : Statement
	ArrayList<Menu> getList(Connection con) {

		ArrayList<Menu> list = null;
		ResultSet rs = null;

		try {
			// sql문
			String sql = "select rownum, mname, price from menu";
			pstmt = con.prepareStatement(sql);

			// 결과 받아오기
			rs = pstmt.executeQuery();

			// 리스트 초기화 후 결과값 저장
			list = new ArrayList<>();
			while (rs.next()) {
				Menu menu = new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 2. 메뉴 데이터 입력
	// 반환 타입 List<Dept>
	// 매개변수 - Connection 객체 : Statement, Menu타입 ArrayList
	int insertMenu(Connection con, Menu menu) {
		int result = 0;

		String sql = "insert into menu(menucode, mname, price) values (menu_sq.nextval, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu.getMname());
			pstmt.setInt(2, menu.getPrice());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 3. 메뉴 데이터 수정
	// 반환 타입 int
	// 매개변수 - Connection 객체 : Statement, Menu타입 ArrayList
	int editMenu(Connection con, Menu menu) {
		int result = 0;

		String sql = "update menu set mname = ?, price = ? where mname = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu.getEditName());
			pstmt.setInt(2, menu.getPrice());
			pstmt.setString(3, menu.getMname());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 4. 메뉴 데이터 삭제
	// 반환 타입 int
	// 매개변수 - Connection 객체 : Statement, menuName
	int deleteMenu(Connection con, String menuName) {
		int result = 0;

		try {
			String sql = "delete from menu where mname = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menuName);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 5. pstmt close
	void close() {
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}