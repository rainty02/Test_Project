package Test;

import java.util.Scanner;

public class AdminPage {

	Scanner sc = new Scanner(System.in);
	MemberManager memManager = new MemberManager(MemberDao.getInstance());
	SaleManager saleManager = new SaleManager(SaleDao.getInstance(), MenuDao.getInstance());
	MenuManager menuManager = new MenuManager(MenuDao.getInstance());

	void calling() {
		System.out.println("관리자  모드 입니다.");
		System.out.println("1. 회원 정보 보기");
		System.out.println("2. 판매 정보 보기");
		System.out.println("3. 메뉴 관리 ");
		System.out.println("4. 관리자 모드 종료");


		int choice = Integer.parseInt(sc.nextLine());

		switch (choice) {
		case 1:
			memManager.memList();
			break;
		case 2:
		
			// 2. 판매 정보 보기
			//      1. 판매 전체 리스트 보기
			//		2. 오늘 총 판매액
			//		3. 오늘 메뉴별 판매액
			//		3. 인기 상품 조회
			System.out.println("판매 정보 보기");
			System.out.println("1. 판매 전체 리스트 보기");
			System.out.println("2. 당일 총 판매액");
			System.out.println("3. 당일 메뉴별 판매액");
			System.out.println("4. 인기 상품 조회");
			
			choice  = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				saleManager.saleList();
				break;
			case 2:
				int totalSalePrice = saleManager.totalSalePrice();
				System.out.println("당일 총 판매액 : " + totalSalePrice);
				break;
			case 3:
				saleManager.menuSalePrice();
				break;
			case 4:
//				saleManager.saleBestList();
				break;
			}

			break;

		case 3:
			
			// 3. 메뉴 관리
			//		1. 메뉴 보기
			//		2. 메뉴 수정
			//		3. 메뉴 삭제
			System.out.println("메뉴 관리");
			System.out.println("1. 현재 판매 중인 메뉴 보기");
			System.out.println("2. 메뉴 추가");
			System.out.println("3. 메뉴 수정 ");
			System.out.println("4. 메뉴 삭제");
			
			
			choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				menuManager.menuList();
				break;
			case 2:
				menuManager.menuAdd();;
				break;
			case 3:
				menuManager.menuEdit();;
				break;
			case 4:
				menuManager.menuDel();
				break;
			}
			
			
			System.exit(0);
			break;
		}
	}

}