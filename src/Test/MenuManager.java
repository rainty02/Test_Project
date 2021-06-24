package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MenuManager {

	private MenuDao dao;
	private Scanner sc;
	
	// 객체 생성
	private Connection con = null;
	
	// 연결
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String pw = "tiger";
	
	public MenuManager() {};
	public MenuManager(MenuDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}
	
	// 1. 메뉴 전체 리스트 출력
	public void menuList() {
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			
			List<Menu> list = dao.getList(con);
			
			System.out.println("■■■■■■■■■■■메뉴리스트■■■■■■■■■■■");
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));				
			}
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");	
		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void menuAdd() {
		
		// 전체리스트 출력
		menuList();
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("메뉴를 추가합니다.");

			System.out.print("이름를 입력하세요 > ");
			String mName = sc.nextLine().trim();
			System.out.print("가격를 입력하세요 > ");
			int price = Integer.parseInt(sc.nextLine().trim());
			
			Menu menu = new Menu(mName, price);
			int result = dao.insertMenu(con, menu);
			
			if(result > 0) {
				System.out.println("추가되었습니다.");
			} else {
				System.out.println("오류가 발생하여 추가되지 않았습니다.");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2. 메뉴 데이터 수정
	public void menuEdit() {
		
		// 전체리스트 출력
		menuList();
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			
			// 수정하려는 메뉴코드 입력
			System.out.print("수정하려는 메뉴코드를 입력하세요 > ");
			int menuCode = Integer.parseInt(sc.nextLine().trim());

			// 컬럼별 수정값 입력
			System.out.print("메뉴이름을 입력하세요 > ");
			String menuName = sc.nextLine().trim();
			System.out.print("가격을 입력하세요 > ");
			int price = Integer.parseInt(sc.nextLine());
			
			Menu menu = new Menu(menuCode, menuName, price);
			
			int result = dao.editMenu(con, menu);

			if (result > 0) {
				System.out.println("정보가 수정되었습니다.");
			} else {
				System.out.println("오류가 발생하여 수정실패하였습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	// 3. 메뉴 데이터 삭제
	public void menuDel() {
		
		// 전체리스트 출력
		menuList();
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.print("삭제하려는 메뉴코드를 입력하세요 > ");
			int menuCode = Integer.parseInt(sc.nextLine());
			
			int result = dao.deleteMenu(con, menuCode);
			if(result > 0) {
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("오류가 발생하여 삭제실패하였습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
