package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class MemberMain {

	public static void main(String[] args) {

		MemberManager manager = new MemberManager(MemberDao.getInstance());
		MenuManager menuManager = new MenuManager(MenuDao.getInstance());
		SaleManager saleManager = new SaleManager(SaleDao.getInstance(), MenuDao.getInstance());
		AdminPage adminPage = new AdminPage();
		Login login = new Login(MemberDao.getInstance());
		Point point = new Point();
		Scanner sc = new Scanner(System.in);

		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println("로그인 화면");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.print("메뉴를 선택하세요 > ");
			int num = Integer.parseInt(sc.nextLine().trim());
			switch (num) {
			case 1:
				manager.memAdd();
				login.chkLogin();
				break;
			case 2:
				login.chkLogin();
				if (login.currentId.equals("admin")) {
					adminPage.calling();
				}
				break;
			default:
				System.out.println("잘못 눌렀습니다.");
				break;
			}
			while (true) {
				System.out.println(login.currentId + "님 환영합니다.");
				System.out.println("현재 적립 포인트 " + point.readPoint(login.currentId) + "점");
				System.out.println("1. 주문하기");
				System.out.println("2. 내 정보 확인");
				int num3 = Integer.parseInt(sc.nextLine().trim());

				switch (num3) {
				case 1:
					saleManager.order(login.currentId);
					break;
				case 2:
					manager.myInfo(login.currentId);
					System.out.println("1. 정보수정");
					System.out.println("2. 회원탈퇴");
					num3 = Integer.parseInt(sc.nextLine().trim());
					switch (num3) {
					case 1:
						manager.memEdit(login.currentId);
						break;
					case 2:
						manager.memDel(login.currentId);
						return;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}