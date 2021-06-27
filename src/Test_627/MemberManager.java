package Test_627;

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

public class MemberManager extends Manager {

/*
	MemberManager 클래스 정의 (메소드명 : 메소드기능 - 사용된 Dao)
	
	1. void memList() : 전체 멤버리스트 출력 (관리자용) - MemberDao.getList		
	2. void myInfo(String currentId) : 내 정보 출력 (회원용) - MemberDao.getList		
	3. String chKOverlap() : 아이디 중복체크 (회원가입시) - MemberDao.chKId			
	4. boolean chkPw(String pw) : 회원가입, 수정시 비밀번호 일치여부 
	5. void memAdd() : 멤버 데이터 입력(회원가입) - MemberDao.chKOverlap, MemberDao.insertMem			
	6. void memEdit(String currentId) : 내 정보 수정 (회원용) -	MemberDao.editMem				
	7. void memDel(String currentId) : 회원 탈퇴 (회원용) - MemberDao.deleteMem				
	8. void myAdminInfo(String currentId) : 관리자 정보 조회 (관리자용) - MemberDao.getAdminList		
	9. void memAdminEdit(String currentId) : 관리자 비밀번호 재설정 (관리자용) - MemberDao.editAdminMem
	
*/	

	private MemberDao dao;
	private Scanner sc;

	public MemberManager(MemberDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}

	// 1. 전체 리스트 출력 (관리자용)
	void memList() {

		List<Member> list = dao.getList(super.getCon());

		System.out.println();
		System.out.println("\t\t\t        고객 정보 리스트");
		System.out.println("\t\t\t*****************");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("멤버코드\t아이디\t비밀번호\t이름\t전화번호\t\t주소\t포인트");
		for (Member mem : list) {
			System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%d\n", mem.getMemcode(), mem.getId(),
					mem.getPw(), mem.getName(), mem.getPhone(), mem.getAddress(), mem.getPoint());
		}
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
	}

	// 2. 내 정보 출력 (회원용)
	// 매개변수 - Login 클래스 : currentId
	void myInfo(String currentId) {
		
		List<Member> list = dao.getList(super.getCon(), currentId);

		System.out.println();
		System.out.println("\t\t                  내 정보");
		System.out.println("\t\t  *****************");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t주소\t포인트");
		for (Member mem : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%d\n", mem.getId(), mem.getPw(), mem.getName(), mem.getPhone(),
					mem.getAddress(), mem.getPoint());
		}
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
	}

	// 3. 아이디 중복체크 (회원가입시)
	// 반환타입 String
	String chKOverlap() {

		String id = null;
		boolean run = true;
		while (run) {
			System.out.print("아이디를 입력하세요 > ");
			id = sc.nextLine().trim();
			run = dao.chKId(super.getCon(), id);
		}
		return id;
	}

	// 4. 회원가입, 수정시 비밀번호 일치여부
	// 매개변수 - 사용자에게 입력받은 패스워드
	// 데이터베이스가 아닌 사용자에게 입력받은 값을 비교이므로 
	// 멤버클래스의 변수나 생성자를 사용하지 않았음
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

	// 5. 멤버 데이터 입력(회원가입)
	void memAdd() {

		System.out.println("\t  회원가입 이용약관");
		System.out.println("\t***************  ");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t        제1조 목적");
		System.out.println("본 이용약관은 「스타벅스」(이하 \"회사\")와 회사의");
		System.out.println("스타벅스 어플서비스(이하 \"서비스\")를 이용하는 자");
		System.out.println("(이하 \"사용자\")간의 관계와 권리 의무 및 책임사항,");
		System.out.println("기타서비스 이용에 관한 제반사항을 정함이 목적이지만");
		System.out.println("아무도 안 읽을테니 끝까지 읽는 사람 있으면 커피 사줌");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\n");

		// 3. 아이디 중복체크 후 아이디값 반환
		String id = chKOverlap();
		String password = null;
		boolean run = true;

		// 비밀번호 일치체크
		while (run) {
			System.out.print("비밀번호를 입력하세요 > ");
			password = sc.nextLine().trim();
			// 4. 비밀번호 중복체크 후 반복문 탈출
			run = chkPw(password);
		}

		System.out.print("이름를 입력하세요 > ");
		String cafename = sc.nextLine().trim();
		System.out.print("주소를 입력하세요 > ");
		String address = sc.nextLine().trim();
		System.out.print("전화번호를 입력하세요 > ");
		String phone = sc.nextLine().trim();

		Member mem = new Member(cafename, id, password, address, phone);

		int result = dao.insertMem(super.getCon(), mem);

		if (result > 0) {
			System.out.println("가입되었습니다.");
		} else {
			System.out.println("오류가 발생하여 가입실패했습니다.");
		}
	}

	// 6. 내 정보 수정
	// 매개변수 - Login 클래스 : currentId
	void memEdit(String currentId) {

		System.out.println("회원 정보를 수정합니다.");
		System.out.print("이름를 입력하세요 > ");
		String cafename = sc.nextLine().trim();

		// 비밀번호 일치체크
		String password = null;
		boolean run = true;
		while (run) {
			System.out.print("비밀번호를 입력하세요 > ");
			password = sc.nextLine().trim();
			run = chkPw(password);
		}

		System.out.print("주소를 입력하세요 > ");
		String address = sc.nextLine().trim();
		System.out.print("전화번호를 입력하세요 > ");
		String phone = sc.nextLine().trim();

		Member mem = new Member(cafename, password, address, phone);

		int result = dao.editMem(super.getCon(), mem, currentId);

		if (result > 0) {
			System.out.println("정보가 수정되었습니다.");
		} else {
			System.out.println("오류가 발생하여 수정실패하였습니다.");
		}
	}

	// 7. 회원 탈퇴
	// 매개변수 - Login 클래스 : currentId
	void memDel(String currentId) {

		System.out.println("탈퇴시 취소나 복구가 되지 않습니다.");
		System.out.print("탈퇴하시려면 '탈퇴'를 입력하세요 > ");
		String yes = sc.nextLine().trim();

		if (yes.equals("탈퇴")) {
			int result = dao.deleteMem(super.getCon(), currentId);
			if (result > 0) {
				System.out.println("탈퇴되었습니다.");
				System.out.println("이용해주셔서 감사합니다.");
				Login.logout();
			} else {
				System.out.println("오류가 발생하여 탈퇴실패하였습니다.");
			}
		}
	}

	// 추가사항
	// 8. 관리자 정보 조회
	// 매개변수 - Login 클래스 : currentId
	void myAdminInfo(String currentId) {

		List<Member> list = dao.getAdminList(super.getCon(), currentId);

		System.out.println();
		System.out.println("\t            점포정보");
		System.out.println("\t****************");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t위치\t전화번호");
		for (Member mem : list) {
			System.out.printf("\t%s\t%s\n", mem.getAddress(), mem.getPhone());
		}
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();
	}

	// 추가사항
	// 9. 관리자 비밀번호 재설정
	// 매개변수 - Login 클래스 : currentId
	void memAdminEdit(String currentId) {

		System.out.println();
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("\t+------------------------+");
		System.out.println("\t|    비밀번호를 재설정 합니다          |");
		System.out.println("\t+------------------------+");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println();

		// 비밀번호 일치체크
		String pw = null;
		boolean run = true;
		while (run) {
			System.out.print("새 비밀번호 > ");
			pw = sc.nextLine().trim();
			run = chkPw(pw);
		}

		int result = dao.editAdminMem(super.getCon(), pw, currentId);

		if (result > 0) {
			System.out.println("정보가 수정되었습니다.");
		} else {
			System.out.println("\t●●●");
			System.out.println("\t오류가 발생하여 수정에 실패하였습니다.");
			System.out.println("\t●●●");
		}
	}
}