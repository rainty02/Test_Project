package Test_627;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {

/*
	MemberDao 클래스 정의 (메소드명 : 메소드기능)
	
	1. ArrayList<Member> getList(Connection con) : 전체 회원 데이터 반환 (관리자용)
	2. ArrayList<Member> getList(Connection con, String currentId) : 현재 로그인 중인  본인의 데이터 반환 (오버로딩 / 회원용)	
	3. int insertMem(Connection con, Member mem) : 회원 데이터 입력 (회원가입용)	
	4. int editMem(Connection con, Member mem, String currentId) : 현재 로그인 중인  본인의 데이터 수정 (회원용)	
	5. int deleteMem(Connection con, String currentId) : 현재 로그인 중인  본인의 회원탈퇴 (회원용) 	
	6. ArrayList<Member> getAdminList(Connection con, String currentId) : 관리자 정보 보기 (관리자용)	
	7. int editAdminMem(Connection con, String pw, String currentId) : 관리자 비밀번호 수정 (관리자용)	
	8. boolean chKId(Connection con, String id) : 아이디 중복체크 (회원가입시)
	9. void close() : PreparedStatement close
	
 */
	private PreparedStatement pstmt = null;
	
	// 싱글톤 패턴
	private MemberDao() {}
	static private MemberDao dao = new MemberDao();
	public static MemberDao getInstance() {
		return dao;
	}

	// 1. 전체 멤버 데이터 반환
	// 반환 타입 List<Dept>
	// 매개변수 - Connection 객체 : Statement
	ArrayList<Member> getList(Connection con) {

		ArrayList<Member> list = null;
		ResultSet rs = null;

		try {
			// sql문
			String sql = "select * from member order by memcode";
			pstmt = con.prepareStatement(sql);

			// 결과 받아오기
			rs = pstmt.executeQuery(sql);

			// 리스트 초기화 후 결과값 저장
			list = new ArrayList<>();
			while (rs.next()) {
				Member mem = new Member(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getInt(7));
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 2. 현재 로그인 중인  본인의 데이터 반환
	// 반환 타입 List<Dept>
	// 매개변수 - Connection 객체 : Statement, Login 클래스 : currentId
	ArrayList<Member> getList(Connection con, String currentId) {

		ArrayList<Member> list = null;
		ResultSet rs = null;

		try {
			// sql문
			String sql = "select * from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentId);
			pstmt.executeUpdate();
			
			// 결과 받아오기
			rs = pstmt.executeQuery(sql);
			
			// 리스트 초기화 후 결과값 저장
			list = new ArrayList<>();
			while (rs.next()) {
				Member mem = new Member(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getInt(7));
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 3. 멤버 데이터 입력
	// 반환 타입 int
	// 매개변수 - Connection 객체 : Statement, Member타입 ArrayList
	int insertMem(Connection con, Member mem) {

		// 인서트 반환값
		int result = 0;

		try {
			String sql = "insert into member (memcode, name, id, pw, address, phone) values (member_sq.nextval, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getId());
			pstmt.setString(3, mem.getPw());
			pstmt.setString(4, mem.getAddress());
			pstmt.setString(5, mem.getPhone());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 4. 멤버 데이터 수정
	// 반환 타입 int
	// 매개변수 - Connection 객체 : Statement, Member타입 ArrayList, Login 클래스 : currentId
	int editMem(Connection con, Member mem, String currentId) {

		int result = 0;

		try {
			// 로그인 아이디를 where절 사용
			String sql = "update member set name = ?, pw =?, address = ?, phone = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getPw());
			pstmt.setString(3, mem.getAddress());
			pstmt.setString(4, mem.getPhone());
			pstmt.setString(5, currentId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 5. 데이터 삭제
	// 반환 타입 int
	// 매개변수 - Connection 객체 : Statement, Login 클래스 : currentId
	int deleteMem(Connection con, String currentId) {

		int result = 0;

		try {
			String sql = "delete from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 6. 관리자 정보 보기 (추가사항)
	// 반환 타입 List<Dept>
	// 매개변수 - Connection 객체 : Statement, Login 클래스 : currentId
	ArrayList<Member> getAdminList(Connection con, String currentId) {

		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// sql문
			String sql = "select address, phone from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, currentId);

			// 결과 받아오기
			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			// 리스트 저장
			while (rs.next()) {
				Member mem = new Member(rs.getString(1), rs.getString(2));
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 7. 관리자 비밀번호 수정 (추가사항)
	// 반환 타입 int
	// 매개변수 - Connection 객체 : Statement, adminPage : pw, Login 클래스 : currentId
	int editAdminMem(Connection con, String pw, String currentId) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			// sql문 where절 수정해야함. 현재 임의값
			String sql = "update member set pw = ? where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, currentId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 8. 아이디 중복체크 (회원가입시)
	// 반환타입  boolean
	// 매개변수 - Connection 객체 : Statement, 사용자 입력 id
	boolean chKId(Connection con, String id) {

		ResultSet rs = null;
		boolean result = true;

		try {
			pstmt = con.prepareStatement("select id from member where id = ?");
			pstmt.setString(1, id);
			
			// 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요. ");
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// 9. PreparedStatement close
	void close() {
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}