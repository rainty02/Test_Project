package Test;

import java.util.Scanner;

public class MemberPage {

	MemberManager memberManager = new MemberManager(MemberDao.getInstance());
	SaleManager saleManager = new SaleManager(SaleDao.getInstance(), MenuDao.getInstance());
	Login login = new Login(MemberDao.getInstance());
	Point point = new Point();
	Scanner sc = new Scanner(System.in);

	void memberPage() {

		while (true) {
			System.out.println(login.currentId + "님 환영합니다.");
			System.out.println("현재 적립 포인트 " + point.readPoint(login.currentId) + "점");
			System.out.println("1. 주문하기");
			// 포인트 추가
			System.out.println("2. 내 정보 확인");
			System.out.println("3. 로그아웃");
			int num3 = Integer.parseInt(sc.nextLine().trim());

			switch (num3) {
			case 1:
				saleManager.order(login.currentId);
				saleManager.pay(login.currentId);
				continue;
			case 2:
				memberManager.myInfo(login.currentId);
				System.out.println("1. 정보수정");
				System.out.println("2. 회원탈퇴");
				System.out.println("3. 이전 메뉴");
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
			case 3:
				login.logout();
				return;
			}
		}
	}
}