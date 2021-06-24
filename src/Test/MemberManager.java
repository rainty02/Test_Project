package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MemberManager {

	private MemberDao dao;
	private Scanner sc;

	
	// 객체 생성
	private Connection con = null;

	// 연결
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String pw = "tiger";

	public MemberManager(MemberDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
//		this.currentId = currentId;
	}

	// 전체 리스트 출력 - 관리자용
	void memList() {

		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);

			List<Member> list = dao.getList(con);

			System.out.println("■■■■■■■■■■■■■■■■■■■■■■고객 정보 리스트■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("멤버코드 \t 아이디 \t 비밀번호 \t 이름 \t 전화번호 \t 주소 \t 포인트");
			for (Member mem : list) {
				System.out.printf("%d \t %s \t %s \t %s \t %s \t %s \t %d \n", mem.getMemcode(), mem.getId(),
						mem.getPw(), mem.getName(), mem.getPhone(), mem.getAddress(), mem.getPoint());
			}
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 내 정보 출력
	void myInfo(String currentId) {

		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);

			List<Member> list = dao.getList(con, currentId);

			System.out.println("■■■■■■■■■■■■■■■■■■■■■■고객 정보 리스트■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("아이디 \t 비밀번호 \t 이름 \t 전화번호 \t 주소 \t 포인트");
			for (Member mem : list) {
				System.out.printf("%s \t %s \t %s \t %s \t %s \t %d\n", mem.getId(), mem.getPw(), mem.getName(),
						mem.getPhone(), mem.getAddress(), mem.getPoint());
			}
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 아이디 중복체크(회원가입에서 사용)
	String chKOverlap() {
		ArrayList<Member> mem = new ArrayList<>();
		String id = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			mem = dao.getList(con);

			while (true) {
				System.out.print("아이디를 입력하세요 > ");
				id = sc.nextLine().trim();
				pstmt = con.prepareStatement("select id from member where id = ?");
				pstmt.setString(1, id);
				
				// 실행
				rs = pstmt.executeQuery(); 
				if (!rs.next()) {
					break;
				} else {
					System.out.println("중복된 아이디입니다. 다시 입력하세요. ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	// 회원가입시 비밀번호 일치여부
	boolean chkPw(String pw) {
		boolean result = true;
		System.out.print("비밀번호를 한번 더 입력하세요 > ");
		String chkPassword = sc.nextLine().trim();
		if (!pw.equals(chkPassword)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	

	// 멤버 데이터 입력(회원가입)
	void memAdd() {
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			System.out.println("환영합니다.");

			// System.out.print("아이디를 입력하세요 > ");
			// String id = sc.nextLine();

			// 아이디 중복체크		
			String id = chKOverlap();
			String password = null;
			boolean run = true;
			
			// 비밀번호 일치체크
			while(run) {
			System.out.print("비밀번호를 입력하세요 > ");
			password = sc.nextLine().trim();
			run = chkPw(password);
			}
			
			System.out.print("이름를 입력하세요 > ");
			String cafename = sc.nextLine().trim();
			System.out.print("주소를 입력하세요 > ");
			String address = sc.nextLine().trim();
			System.out.print("전화번호를 입력하세요 > ");
			String phone = sc.nextLine().trim();

			Member mem = new Member(cafename, id, password, address, phone);

			int result = dao.insertMem(con, mem);

			if (result > 0) {
				System.out.println("가입되었습니다.");
			} else {
				System.out.println("오류가 발생하여 가입실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 데이터 수정
	void memEdit(String currentId) {

		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
 
			System.out.println("회원 정보를 수정합니다.");
			System.out.print("이름를 입력하세요 > ");
			String cafename = sc.nextLine().trim();
			System.out.print("비밀번호를 입력하세요 > ");
			String password = sc.nextLine().trim();
			System.out.print("주소를 입력하세요 > ");
			String address = sc.nextLine().trim();
			System.out.print("전화번호를 입력하세요 > ");
			String phone = sc.nextLine().trim();

			Member mem = new Member(cafename, password, address, phone);

			int result = dao.editMem(con, mem, currentId);

			if (result > 0) {
				System.out.println("정보가 수정되었습니다.");
			} else {
				System.out.println("오류가 발생하여 수정실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원 탈퇴
	void memDel(String currentId) {

		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);

			System.out.println("탈퇴시 취소나 복구가 되지 않습니다.");
			System.out.print("탈퇴하시려면 '탈퇴'를 입력하세요 > ");
			String yes = sc.nextLine().trim();

			if (yes.equals("탈퇴")) {
				int result = dao.deleteMem(con, currentId);
				if (result > 0) {
					System.out.println("탈퇴되었습니다.");
					System.out.println("이용해주셔서 감사합니다.");
					Login.logout();
				} else {
					System.out.println("오류가 발생하여 탈퇴실패하였습니다.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}