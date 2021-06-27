package Test_627;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MenuManager extends Manager {

/*
	MenuManager 클래스 정의 (메소드명 : 메소드기능 - 사용된 Dao)
	
 	1. void menuList() : 전체 메뉴리스트 출력 (회원, 관리자 공용) - MenuDao.getList
 	2. void menuAdd() : 메뉴 데이터 추가 (관리자용) - MenuDao.insertMenu
  	3. void menuEdit() : 내 정보 수정 (회원용) -	MenuDao.editMenu				
	4. void menuDel() : 회원 탈퇴 (회원용) - MenuDao.deleteMenu	 
 */

	private MenuDao dao;
	private Scanner sc;

	public MenuManager(MenuDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 1. 메뉴 전체 리스트 출력 (회원, 관리자 공용)
	void menuList() {

		List<Menu> list = dao.getList(super.getCon());

		System.out.println();
		System.out.println("\t           메뉴리스트");
		System.out.println("\t***************  ");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
	}

	// 2. 메뉴 데이터 입력 (관리자용)
	void menuAdd() {

		// 전체리스트 출력
		menuList();

		System.out.println("메뉴를 추가합니다.");
		System.out.print("이름를 입력하세요 > ");
		String mName = sc.nextLine().trim();
		System.out.print("가격를 입력하세요 > ");
		int price = Integer.parseInt(sc.nextLine().trim());

		Menu menu = new Menu(mName, price);
		int result = dao.insertMenu(super.getCon(), menu);

		if (result > 0) {
			System.out.println("추가되었습니다.");
		} else {
			System.out.println("오류가 발생하여 추가되지 않았습니다.");
		}
	}

	// 3. 메뉴 데이터 수정 (관리자용)
	void menuEdit() {

		// 전체리스트 출력
		menuList();

		// 수정하려는 메뉴이름 입력
		System.out.print("수정하려는 메뉴이름을 입력하세요 > ");
		String menuName = sc.nextLine().trim();

		// 컬럼별 수정값 입력
		System.out.println("메뉴이름을 입력하세요 > ");
		String editName = sc.nextLine().trim();
		System.out.print("가격을 입력하세요 > ");
		int price = Integer.parseInt(sc.nextLine());

		Menu menu = new Menu(menuName, editName, price);
		int result = dao.editMenu(super.getCon(), menu);
		if (result > 0) {
			System.out.println("정보가 수정되었습니다.");
		} else {
			System.out.println("오류가 발생하여 수정실패하였습니다.");
		}
	}

	// 4. 메뉴 데이터 삭제 (관리자용)
	void menuDel() {

		// 전체리스트 출력
		menuList();
		System.out.print("삭제하려는 메뉴이름를 입력하세요 > ");
		String menuName = sc.nextLine().trim();

		int result = dao.deleteMenu(super.getCon(), menuName);
		if (result > 0) {
			System.out.println("삭제되었습니다.");
		} else {
			System.out.println("오류가 발생하여 삭제실패하였습니다.");
		}
	}
}