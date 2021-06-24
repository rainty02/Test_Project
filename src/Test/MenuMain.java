package Test;

import java.util.Scanner;

public class MenuMain {

	public static void main(String[] args) {
		
		MenuManager menuManager = new MenuManager(MenuDao.getInstance());
		Scanner sc = new Scanner(System.in);
		
		while(true) {
		
		System.out.println("관리자모드");
		System.out.println("1. 회원정보");
		System.out.println("2. 판매정보");
		System.out.println("3. 메뉴정보");
		System.out.print("메뉴를 선택하세요 > ");
		int num = Integer.parseInt(sc.nextLine());
		
		switch(num) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			System.out.println("1. 메뉴 전체리스트");
			System.out.println("2. 메뉴 데이터 추가");
			System.out.println("3. 메뉴 데이터 수정");
			System.out.println("4. 메뉴 삭제");
			System.out.print("메뉴를 선택하세요 > ");
			num = Integer.parseInt(sc.nextLine());
			
			switch(num) {
			case 1:
				menuManager.menuList();
				break;
			case 2:
				menuManager.menuAdd();
				break;
			case 3:
				menuManager.menuEdit();
				break;
			case 4:
				menuManager.menuDel();
				break;	
			}
			break;
		}
		
	}
	}

}
