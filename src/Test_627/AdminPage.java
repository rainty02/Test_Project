package Test_627;

import java.util.Scanner;

public class AdminPage {

	Scanner sc = new Scanner(System.in);
	MemberManager memManager = new MemberManager(MemberDao.getInstance());
	SaleManager saleManager = new SaleManager(SaleDao.getInstance());
	MenuManager menuManager = new MenuManager(MenuDao.getInstance());
	Login login = new Login(MemberDao.getInstance());

	void mainOpen() {

		while (true) {
			System.out.println();
			System.out.println("\t         관리자 모드");
			System.out.println("\t***************  ");;
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t1. 회원 정보 보기");
			System.out.println("\t2. 판매 정보 보기");
			System.out.println("\t3. 메뉴 관리 ");
			System.out.println("\t4. 관리자 설정");
			System.out.println("\t5. 관리자 모드 종료");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				memManager.memList();
				break;
			case 2:
				two();
				break;
			case 3:
				three();
				break;
			case 4:
				four();
				break;
			case 5:
				login.logout();
				System.exit(0);
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
			}
		}
	}

	// case 2: 판매 정보 보기
	void two() {
		while (true) {
			System.out.println();
			System.out.println("\t           판매 정보");
			System.out.println("\t***************  ");;
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t1. 판매 전체 리스트 보기");
			System.out.println("\t2. 당일 총 판매액");
			System.out.println("\t3. 당일 메뉴별 판매액");
			System.out.println("\t4. 인기 상품 조회");
			System.out.println("\t5. 이전 단계로 가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
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
				saleManager.saleBestList();
				break;
			case 5:
				return;
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
			}
		}

	}

	// case 3: 메뉴 관리
	void three() {
		while (true) {
			System.out.println();
			System.out.println("\t        메뉴 관리");
			System.out.println("\t***************");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t1. 현재 판매 중인 메뉴 보기");
			System.out.println("\t2. 메뉴 추가");
			System.out.println("\t3. 메뉴 수정 ");
			System.out.println("\t4. 메뉴 삭제");
			System.out.println("\t5. 이전 단계로 가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
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
			case 5:
				return;
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
			}
		}
	}

	// case 4: 관리자 설정
	void four() {
		while(true) {
			System.out.println();
			System.out.println("\t        관리자 설정");
			System.out.println("\t***************  ");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t1. 점포 정보 조회 "); // 현재 판매 중인 메뉴 보기
			System.out.println("\t2. 비밀번호 재설정");
			System.out.println("\t3. 이전 단계로 가기");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();

			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				memManager.myAdminInfo(Login.currentId);
				break;
			case 2:
				memManager.memAdminEdit(Login.currentId);
				break;
			case 3:
				return;
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
			}
		}
	}
}