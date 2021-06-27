package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class AppMain {

	public static void main(String[] args) {

		MemberManager memberManager = new MemberManager(MemberDao.getInstance());
		MenuManager menuManager = new MenuManager(MenuDao.getInstance());
		SaleManager saleManager = new SaleManager(SaleDao.getInstance(), MenuDao.getInstance());
		AdminPage adminPage = new AdminPage();
		MemberPage memeberPage = new MemberPage();
		Login login = new Login(MemberDao.getInstance());
		Point point = new Point();
		Scanner sc = new Scanner(System.in);

		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			while(true) {
			System.out.println("로그인 화면");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.print("메뉴를 선택하세요 > ");
			int num = Integer.parseInt(sc.nextLine().trim());
			switch (num) {
			case 1:
				memberManager.memAdd();
			case 2:
				login.chkLogin();
				if (login.currentId.equals("admin")) {
					adminPage.mainOpen();
				}
				memeberPage.memberPage();
				continue;
			default:
				System.out.println("잘못 눌렀습니다.");
				break;
			}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}