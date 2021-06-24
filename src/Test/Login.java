package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

	private MemberDao dao;
	private Scanner sc;
	public static String currentId;

	public Login(MemberDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 로그인시 아이디, 비밀번호 확인
	void chkLogin() {
		
		// 객체 생성
		Connection con = null;

		// 연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
		// ArrayList, HashMap 인스턴스 생성
		ArrayList<Member> mem = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();

		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			// ArrayList mem으로  회원정보 대입 
			mem = dao.getList(con);
			// mem의 크기만큼 반복 후 map에 아이디를 키값으로 비밀번호를 밸류값으로 저장
			for (int i = 0; i < mem.size(); i++) {
				map.put(mem.get(i).getId(), mem.get(i).getPw());
			}
			
			// 입력횟수 제한을 위한 cnt변수
			int cnt = 3;
			
			while(true) {
				// 3회 초과시 프로그램 종료
				if (cnt == 0) {
					System.out.println("아이디와 비밀번호를 확인해주세요.");
					logout();
					System.exit(0);
				}
				System.out.println("아이디와 비밀번호를 입력합니다. (입력횟수 " + cnt +"회)");
				System.out.print("아이디를 입력하세요 > ");
				currentId = sc.nextLine().trim();
				System.out.print("비밀번호를 입력하세요 > ");
				String password = sc.nextLine().trim();
				System.out.println();
				
				// 입력한 ID와 맵 키값 비교
				if (!map.containsKey(currentId)) {
					System.out.println("입력하신 아이디는 존재하지 않습니다. 다시 입력하세요.");
					cnt--;
					continue;
				} else {
					// 입력한 비밀번호와 아이디 비교
					if (!(map.get(currentId)).equals(password)) {
						System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요.");
						cnt--;
					} else {
						System.out.println("로그인하셨습니다.");
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	// 로그아웃시 스태틱 currntId = null;
	static void logout() {
		currentId = null;
	}
}