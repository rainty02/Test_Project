package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDao {

	PreparedStatement pstmt = null;
	private MenuDao() {}
	static private MenuDao dao = new MenuDao();
	public static MenuDao getInstance() {
		return dao;
	}
	
	// 메뉴 데이터 전체 출력
	ArrayList<Menu> getList(Connection con) {
		
		ArrayList<Menu> list = null;
		ResultSet rs= null;
				
		try {
			String sql = "select rownum, mname, price from menu";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				Menu menu = new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(menu);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;		
	}
	
	// 2. 메뉴 데이터 입력
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
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	// 3. 메뉴 데이터 수정
	int editMenu(Connection con, Menu menu) {
		int result = 0;		
		
		String sql = "update menu set mname = ?, price =? where menucode = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu.getMname());
			pstmt.setInt(2, menu.getPrice());
			pstmt.setInt(3, menu.getMenuCode());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	// 4. 메뉴 데이터 삭제
	int deleteMenu(Connection con, int menuCode) {
		int result = 0;
		
		try {	
			String sql = "delete from menu where menucode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menuCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}	
	
	void close() {
		if(pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}