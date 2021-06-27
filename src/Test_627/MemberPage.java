package Test_627;

import java.util.Scanner;

public class MemberPage {

/*	
	MemberManager 클래스 정의
	
	회원용 페이지 - 주문, 포인트조회, 개인정보확인, 수정, 탈퇴
	인스턴스 생성 - Login, MemberManager, SaleManager, Point
*/
	
	MemberManager memberManager = new MemberManager(MemberDao.getInstance());
	SaleManager saleManager = new SaleManager(SaleDao.getInstance(), MenuDao.getInstance());
	Login login = new Login(MemberDao.getInstance());
	Point point = new Point();
	Scanner sc = new Scanner(System.in);

	void memberPage() {

		while (true) {
			System.out.println();
			System.out.println(String.format("%30s님", login.currentId));
			System.out.println("\t          회원 메뉴");
			System.out.println("\t***************  ");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t  1. 주문하기");
			System.out.println("\t  2. 포인트 확인");
			System.out.println("\t  3. 내 정보");
			System.out.println("\t  4. 로그아웃");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println();
			int num3 = Integer.parseInt(sc.nextLine().trim());

			switch (num3) {
			case 1:
				saleManager.order(login.currentId);
				saleManager.pay(login.currentId);
				continue;
			case 2:
				System.out.println();
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("    " + login.currentId + "님의 적립포인트는 " + point.readPoint(login.currentId) + "점입니다.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				continue;
			case 3:
				memberManager.myInfo(login.currentId);
				System.out.println();
				System.out.println("\t            내 정보");
				System.out.println("\t***************  ");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("\t   1. 정보수정");
				System.out.println("\t   2. 회원탈퇴");
				System.out.println("\t   3. 이전 메뉴");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println();
				num3 = Integer.parseInt(sc.nextLine().trim());
				switch (num3) {
				case 1:
					memberManager.memEdit(login.currentId);
					continue;
				case 2:
					memberManager.memDel(login.currentId);
					break;
				case 3:
					continue;
				}
			case 4:
				login.logout();
				return;
			default:
				System.out.println("잘못 선택하셨습니다.");
				continue;
			}
		}
	}
}