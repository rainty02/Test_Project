package Test;

import java.io.DataOutput;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


//import SaleTest.Menu_test;


public class SaleManager {

	private SaleDao dao;
	private MenuDao mdao;
	Scanner scanner;
	private String currentId;
	Point pManager;

	// Connection 객체 생성 
	Connection conn = null;

	// 연결
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";

	public SaleManager(MenuDao mdao){
		// 초기화
		this.mdao = mdao;
	}
	
	public SaleManager(SaleDao dao){
		// 초기화
		this.dao = dao;
		scanner= new Scanner(System.in);
		this.currentId = currentId;
		pManager = new Point();

	}

	// 1. 관리자가 sale DB의 전체리스트틑 확인할 수 있다.
	void saleList() {

		try {
			conn= DriverManager.getConnection(jdbcUrl, user, pw);

			List<Sale> list = dao.getSaleList(conn);

			System.out.println("판매 정보 리스트");
			System.out.println("-------------------------------------");
			System.out.println("판매코드 \t 상품명  \t 가격  \t 판매 날짜");
			System.out.println("-------------------------------------");

			for(Sale sale : list) {
				System.out.printf("%d \t %s \t %d \t %s \n", 
						sale.getSalecode(), sale.getSname(), sale.getPrice(), sale.getSaledate()
						);
			}
			System.out.println("-------------------------------------");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	// 2. 관리자가  오늘 전체 판매액을 확인할 수 있다.

	int totalSalePrice() {
		int totalSalePrice = 0;

		try {
			conn= DriverManager.getConnection(jdbcUrl, user, pw);

			totalSalePrice= dao.getTotalSalePrice(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalSalePrice;
	}




	// 3. 관리자가 오늘 메뉴당 판매수와 판매액을 확인할 수 있다.
	void menuSalePrice() {

		try {
			conn= DriverManager.getConnection(jdbcUrl, user, pw);

			List<MenuSale> list = dao.getMenuSalePrice(conn);

			System.out.println("오늘 메뉴별  판매된 갯수 와 판매액을 조회합니다.");
			System.out.println("------------------------------------------");
			System.out.println("메뉴 \t              판매수 \t             판매액 ");



			for(MenuSale menu : list) {
				if(menu.getpName().equals("americano") || menu.getpName().equals("sandwich")) {
					System.out.println(menu.getpName() + "\t" + menu.getpNumSales()+ "\t" + menu.getpSalePrice());
				}else {
					System.out.println(menu.getpName() + "\t\t" + menu.getpNumSales()+ "\t" + menu.getpSalePrice());
				}
			}
			System.out.println("------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	// 4. 주문하기 메소드 -> SALE DB에 저장된다. 
	void order(String currentId) {
		ArrayList<Sale> list = new ArrayList<>();

		// 주문하기 = sale DB에 저장한다. 

		//connection객체 생성

		int americano = 4100;
		int latte = 4600;
		int sandwich =6200;
		int salad = 5000;
		int cake = 5500;

		Connection conn = null;

		// 연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";

		try {
			conn = DriverManager.getConnection(jdbcUrl,user,pw);

//			HashMap<String, Integer> map = new HashMap<>();
//			
//			List<Menu> menu = mdao.getList(conn);
//			// mem의 크기만큼 반복 후 map에 아이디를 키값으로 비밀번호를 밸류값으로 저장
//			for (int i = 0; i < menu.size(); i++) {
//				map.put(menu.get(i).getRowNum(), menu.get(i).getPrice());
//			}
			
			MenuManager menuManager = new MenuManager(MenuDao.getInstance());
						
			menuManager.menuList();
//			System.out.println("메뉴 입니다.");
//			System.out.println("--------------------------------------------------");
//			System.out.println("1. Amerciano : 4100");
//			System.out.println("2. Latte : 4600");
//			System.out.println("3. Sandwich : 6200");
//			System.out.println("4. salad : 5000");
//			System.out.println("5. cake : 5500");
//			System.out.println("6. 주문 완료");
//			System.out.println("--------------------------------------------------");

			while(true) {
				System.out.println("원하시는 메뉴의 번호와 수량을 입력하세요.");
				System.out.println("예시)1 3");
				String inputData = scanner.nextLine();
				String[] inputDatas = inputData.split(" ");
			
				//샀을 때 sale객체로 정보 넣기

				switch(inputDatas[0]) {
			
				case "1":
					list.add(new Sale("americano", Integer.parseInt(inputDatas[1])*americano));
					System.out.println("americano "+ inputDatas[1]+"잔 주문");
					break;
				case "2":
					list.add(new Sale("latte", Integer.parseInt(inputDatas[1])*latte));
					System.out.println("latte "+ inputDatas[1]+"잔 주문");
					break;
				case "3":
					list.add(new Sale("sandwich", Integer.parseInt(inputDatas[1])*sandwich));
					System.out.println("sandwich "+ inputDatas[1]+"개 주문");
					break;
				case "4":
					list.add(new Sale("salad", Integer.parseInt(inputDatas[1])*salad));
					System.out.println("salad "+ inputDatas[1]+"개 주문");
					break;
				case "5": 
					list.add(new Sale("cake", Integer.parseInt(inputDatas[1])*cake));
					System.out.println("cake "+ inputDatas[1]+"개 주문");
					break;
				case "6":
					// 주문완료시에 데이터 저장하고, 포인트 적립 및 결제 한다. 

					int result = dao.insertSale(conn, list);  //SaleDao 로 넘겨서 Sale DB에 저장하기

					//	------------------------------------------------------------------------------------------

					// 고객에게 예상 적립 포인트와 총 결제 금액 보여주기

					System.out.println("--------------------------------------------------");
					int totalPrice = 0;

					for(int i = 0; i <list.size(); i++) {
						totalPrice += list.get(i).getPrice();
					}


					int expectedPoint = (int)(totalPrice * 0.01);

					System.out.println("총 예상 결제 금액: " +totalPrice +"원 입니다.");	
					System.out.println("총 예상 적립 포인트:"+ expectedPoint +"점입니다."); 


					//-------------------------------------------------------------------------------------------
					//회원 DB에서 point를 read하기

					int beforePoint = pManager.readPoint(currentId);
					System.out.println("현재 사용가능한 포인트 : " + beforePoint);

					//-----------------------------------------------------------------------------------
					System.out.println("--------------------------------------------------");
					System.out.println("포인트를 사용하시겠습니까? 1. 예 2. 아니오"); //예 아니오 분기하기
					System.out.println("(포인트를 사용할시 현재 결제하시는 상품의 포인트는 적립이 되지 않습니다.)");
					int answer = Integer.parseInt(scanner.nextLine());

					if(answer == 1) { //포인트 사용하기


						int afterPoint;

						if(beforePoint >= totalPrice) {
							// 포인트 10000 > 상품 금액 4000 -> 결제= 0, 남은 포인트 = 10000-4000
							System.out.println("--------------------------------------------------");

							//point 사용 하기
							pManager.usePoint(currentId, totalPrice);

							//상품 금액 만큼의 포인트 사용하기
							System.out.println("포인트를 "+totalPrice+"점 사용하였습니다"); 

							//포인트 사용할 경우  결제금액에서 마이너스 시킨다.
							System.out.println("결제 금액은  0원 입니다.");

							// 포인트 사용후 사용가능한 포인트 확인
							// 남은 포인트 = 10000-4000

							afterPoint = pManager.readPoint(currentId);
							System.out.println("결제 후 포인트 : " + afterPoint); 

							System.out.println("--------------------------------------------------");


						} else if(beforePoint < totalPrice){
							// 포인트 4000 < 상품 금액 10000 -> 결제하실 금액 10000-4000, 남은 포인트 = 0

							System.out.println("--------------------------------------------------");

							pManager.usePoint2(currentId);

							System.out.println("포인트를 "+beforePoint+"점 사용하였습니다"); 
							System.out.println("결제 금액은 " + (totalPrice-beforePoint)+ "원 입니다.");
							System.out.println("결제 후 포인트 : 0 점" ); 

							System.out.println("--------------------------------------------------");
						} 


					}else {//포인트 사용하지 않고 그대로 적립하기
						System.out.println("--------------------------------------------------");

						pManager.savePoint(currentId, expectedPoint); //포인트 적립
						System.out.println("결제 금액은  " +totalPrice +"원 입니다.");
						System.out.println("포인트가 "+expectedPoint+"점 적립되어 "+ (beforePoint+expectedPoint)+"점 있습니다.");

						System.out.println("--------------------------------------------------");
					}


					System.exit(0);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}