package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Strategy.Str;


public enum Model {
	INSTANCE;

	public Str printer;
	
	String base_server = "gofrie.mysql.ukraine.com.ua";
	String base_name = "gofrie_slava";
	String base_user = "gofrie_slava";
	String base_pass = "8xa5ktrg";
	String dbName = "";
	Connection connection;

	private Model() {
		dbName = "//" + base_server + "/" + base_name + "?user="
				+ base_user + "&password=" + base_pass;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//link to database wirelessly
			connection = DriverManager.getConnection("jdbc:mysql:" + dbName);
			
			//link to localhost
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/gofrie_slava", base_user, base_pass);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeCon(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ResultSet doIt(String s) {

		ResultSet rs = null;
		try {

			PreparedStatement ps = connection.prepareStatement(s);
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	public boolean doLogInAdmin(String log, String pswd) {
		if (log.equals("admin") && pswd.equals("12345"))
			return true;
		return false;
	}

	public boolean doLogInStudent(String log, String pswd) throws SQLException {
		if (findStudent(log) && pswd.equals("11111"))
			return true;
		return false;
	}

	public boolean doLogInComend(String log, String pswd) {
		if (log.equals("comend") && pswd.equals("qwerty"))
			return true;
		return false;
	}

	private boolean findStudent(String surname) throws SQLException {

		ResultSet rs = doIt("SELECT * FROM student WHERE surname = '" + surname
				+ "'");

		if (rs.first() == false) {
			return false;
		} else {
			return true;
		}
	}

	public void helper(String log) throws SQLException {
		ResultSet rs = doIt("SELECT room FROM student WHERE surname = '" + log
				+ "'");
		if (rs.next()) {
			System.out.println(rs.getString("room"));
		}
	}

	public String getRoomToStudent(String log) {
		String result = "";
		ResultSet rs = doIt("SELECT room FROM student WHERE surname = '" + log
				+ "'");

		try {
			if (rs.next()) {
				result = rs.getString("room");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getCapacToStudent(String log) {
		String result = "";

		ResultSet rs = doIt("SELECT capacity, free "
				+ "FROM (Room INNER JOIN student ON Room.number = student.room) "
				+ "WHERE surname = '" + log + "'");

		try {
			if (rs.next()) {
				result = rs.getString("capacity") + " \\ "
						+ rs.getString("free");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public String getHeadToStudent(String log) {
		String result = "";
		ResultSet rs = doIt("SELECT name,surname "
				+ "FROM student "
				+ "WHERE id_code IN (SELECT identStarosta "
				+ "FROM Block "
				+ "WHERE numbers IN (SELECT numbers "
				+ "FROM Block INNER JOIN Room ON Block.numbers = Room.numbersInBlock "
				+ "WHERE number IN (SELECT room " + "FROM student St "
				+ "WHERE St.surname ='" + log + "')))");

		try {
			if (rs.next()) {
				result = rs.getString("name") + " " + rs.getString("surname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDormToStudent(String log) {
		String result = "";
		ResultSet rs = doIt("SELECT adress " + "FROM Dormitory "
				+ "WHERE EXISTS(SELECT * " + "FROM Block "
				+ "WHERE Dormitory.dormNumber = Block.dormNumber AND numbers IN (SELECT numbersInBlock " + "FROM Room "
				+ "WHERE number IN (SELECT room " + "FROM student "
				+ "WHERE surname = '" + log + "')))");

		try {
			if (rs.next()) {
				result = rs.getString("adress");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String[] getDorms() {
		int amount = 0;
		ResultSet am = doIt("SELECT COUNT(dormNumber) AS AM FROM Dormitory");
		try {
			if (am.next()) {
				amount = am.getInt("AM");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String[] result = new String[amount];
		int i = 0;
		ResultSet rs = doIt("SELECT adress FROM Dormitory");
		try {
			while (rs.next()) {
				result[i++] = rs.getString("adress");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
public String[] getBlocks(String dorm){
	
	int amount = 0;
	ResultSet am = doIt("SELECT COUNT(numbers) AS NB FROM Block WHERE dormNumber IN (SELECT dormNumber FROM Dormitory WHERE adress ='"+dorm+"')");
	try {
		if (am.next()) {
			amount = am.getInt("NB");
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
	int i = 0;
	String[] result = new String[amount];
	ResultSet rs = doIt("SELECT numbers FROM Block WHERE dormNumber IN (SELECT dormNumber FROM Dormitory WHERE adress ='"+dorm+"')");
	try {
		while (rs.next()) {
			result[i++] = rs.getString("numbers");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return result;
}
public String[] getStBlDorm(String dorm, String bl){
	int amount = 0;
	ResultSet am = doIt("SELECT COUNT(S.name) AS NB FROM Dormitory D, Block B, Room R, student S WHERE D.dormNumber = B.dormNumber AND B.numbers = R.numbersInBlock AND R.number = S.room AND D.adress='"+dorm+"' AND B.numbers = '"+bl+"'");
	try {
		if (am.next()) {
			amount = am.getInt("NB");
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
	int i = 0;
	String[] result = new String[amount];
	ResultSet rs = doIt("SELECT name, surname FROM Dormitory D, Block B, Room R, student S WHERE D.dormNumber = B.dormNumber AND B.numbers = R.numbersInBlock AND R.number = S.room AND D.adress='"+dorm+"' AND B.numbers = '"+bl+"' ORDER BY surname, name ASC");
	try {
		while (rs.next()) {
			result[i++] = rs.getString("surname")+" "+rs.getString("name");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return result;
}
public void doStarost(String dorm, String bl, String st){
	int id_code =0;
	String numbers = "";
	String dNumb = "";
	String [] sn = st.split(" ");
	System.out.println(sn[0] + sn[1]);
	ResultSet id = doIt("SELECT id_code FROM student WHERE surname ='"+sn[0]+"' AND name ='"+sn[1]+"'");
	try {
		if (id.next()) {
			id_code = id.getInt("id_code");
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	System.out.println(id_code);
	ResultSet rs = doIt("SELECT numbers, dormNumber FROM Block B, Room R, student S WHERE B.numbers = R.numbersInBlock AND R.number = S.room AND S.id_code = '"+id_code+"'");
	try {
		if (rs.next()) {
			numbers = rs.getString("numbers");
			dNumb = rs.getString("dormNumber");
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	System.out.println(numbers);
	System.out.println(dNumb);
	String upd = "UPDATE `Block` SET `identStarosta`='"+id_code+"' WHERE dormNumber ='"+dNumb+"' AND numbers='"+numbers+"'";
	PreparedStatement ps;
	try {
		ps = connection.prepareStatement(upd);
		int res = ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	
}
	public String[] getDormsAndBlocks() {
		int amount = 0;
		ResultSet am = doIt("SELECT COUNT(numbers) AS NB FROM Block");
		try {
			if (am.next()) {
				amount = am.getInt("NB");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String[] result = new String[amount];
		int i = 0;
		ResultSet rs = doIt("SELECT adress,numbers FROM Dormitory INNER JOIN Block ON Dormitory.dormNumber = Block.dormNumber ORDER BY adress, numbers ASC");
		try {
			while (rs.next()) {
				result[i++] = rs.getString("adress") + " "
						+ rs.getString("numbers");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
public String[] getDormsAndRoomsFree(){
		int amount = 0;
		ResultSet am = doIt("SELECT COUNT(number) AS NB FROM Room");
		try {
			if (am.next()) {
				amount = am.getInt("NB");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String[] result = new String[amount];
		int i = 0;
		ResultSet rs = doIt("SELECT adress, number "
				+ "FROM Dormitory D INNER JOIN (Block Bl INNER JOIN Room R ON Bl.numbers = R.numbersInBlock ) ON D.dormNumber = Bl.dormNumber "
				+ "WHERE free <> 0 " +
				"ORDER BY adress, number ASC");
		try {
			while (rs.next()) {
				result[i++] = rs.getString("adress") + " "
						+ rs.getString("number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String[] getDormsAndRooms() {
		int amount = 0;
		ResultSet am = doIt("SELECT COUNT(number) AS NB FROM Room");
		try {
			if (am.next()) {
				amount = am.getInt("NB");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String[] result = new String[amount];
		int i = 0;
		ResultSet rs = doIt("SELECT adress, number "
				+ "FROM Dormitory D INNER JOIN (Block Bl INNER JOIN Room R ON Bl.numbers = R.numbersInBlock ) ON D.dormNumber = Bl.dormNumber "
				+ "ORDER BY adress, number ASC");
		try {
			while (rs.next()) {
				result[i++] = rs.getString("adress") + " "
						+ rs.getString("number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getFreeToComend() {
		String result = "";
		ResultSet rs = doIt("SELECT adress, SUM(free) AS Free "
				+ "FROM Dormitory D INNER JOIN (Block Bl INNER JOIN Room R ON Bl.numbers = R.numbersInBlock ) ON D.dormNumber = Bl.dormNumber "
				+ "GROUP BY adress");
		try {
			while (rs.next()) {
				result += rs.getString("adress") + " - " + rs.getInt("Free")
						+ " місць\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	public String getHeadManToComend(String s) {
		String result = "Старости немає!\n";
		String blockNumbers = s.substring(s.length() - 7);
		ResultSet rs = doIt("SELECT name, surname "
				+ "FROM Block INNER JOIN student ON Block.identStarosta = student.id_code "
				+ "WHERE numbers ='" + blockNumbers + "'");
		System.out.println(blockNumbers);
		try {
			if (rs.next()) {
				result = rs.getString("name") + " " + rs.getString("surname")
						+ "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;

	}

	public String getAboutRoomToComend(String s) {
		
		String result = " ";
		String dorm = s.substring(0, s.length() - 4);
		String room = s.substring(s.length() - 3);
		/*
		ResultSet rs = doIt("SELECT free, capacity, (SELECT name, surname FROM student WHERE room = number) NS"
							+ "FROM Dormitory D INNER JOIN (Block Bl INNER JOIN Room R ON Bl.numbers = R.numbersInBlock ) ON D.dormNumber = Bl.dormNumber "
							+ "WHERE number ='" + room + "' AND adress ='" + dorm + "'");*/
		ResultSet rs = doIt("SELECT free, capacity "
				+ "FROM Dormitory D INNER JOIN (Block Bl INNER JOIN Room ON Bl.numbers = Room.numbersInBlock ) ON D.dormNumber = Bl.dormNumber "
				+ "WHERE number ='" + room + "' AND adress ='" + dorm + "'");
		System.out.print(dorm);
		System.out.println(room);
		try {
			if (rs.next()) {
				
				result = "Вільних місць: " + rs.getString("free")	+ ", місткість: " + rs.getString("capacity");
				
					result += "\n";
					
					
			}
			if (! rs.getString("free").equals(rs.getString("capacity"))){
				rs = doIt("SELECT free, capacity, name, surname "
						+ "FROM Dormitory D INNER JOIN (Block Bl INNER JOIN ( Room INNER JOIN student ON Room.number = student.room ) ON Bl.numbers = numbersInBlock) ON D.dormNumber = Bl.dormNumber "
						+ "WHERE number ='" + room + "' AND adress ='" + dorm + "'");
				result += "Жителі:\n";
				while(rs.next()){					
					result+= rs.getString("surname") +" "+ rs.getString("name") +"\n";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
		return result;
	}
	
	public String getRFToComend(){
		String result = "";
		
		//ResultSet rs = doIt("SELECT number, free "
			//	+ "FROM Room");
		ResultSet rs = doIt("SELECT number, free, (SELECT adress FROM Dormitory WHERE Block.dormNumber = Dormitory.dormNumber) AS D "
				+ "FROM Room INNER JOIN Block ON Room.numbersInBlock = Block.numbers "
				+ "ORDER BY D, number");
		try {
			String dn = "";
			while(rs.next()){
				
				if (dn.equals(rs.getString("D"))){
					//result += "__________________________________\n";
					result += "| Кімната: " + rs.getString("number")
							+ "  | місць: " + rs.getInt("free") +" |";
					result += "\n";
					//result += "__________________________________\n";
				}else{
					result += "******************************************\n";
					result +=rs.getString("D")+"\n";
					result += "******************************************\n";
					//result += "__________________________________\n";
					result += "| Кімната: " + rs.getString("number")
							+ "  | місць: " + rs.getInt("free") +" |";
					result += "\n";
					//result += "__________________________________\n";
					dn = rs.getString("D");
				}
			}
			/*while (rs.next()) {
				result += rs.getString("number")
						+ "\\ " + rs.getInt("free");
					result += "\n";
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getBadStudent(){
		String result = "";
		ResultSet rs = doIt("SELECT name, surname "
				+ "FROM student WHERE payedTo < Now()");
		try {
			while (rs.next()) {
				result += rs.getString("name")
						+ " " + rs.getString("surname")+"\n";
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String[] getAllStud(){
		int amount = 0;
		ResultSet am = doIt("SELECT COUNT(*) AS AM FROM student");
		try {
			if (am.next()) {
				amount = am.getInt("AM");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String[] result = new String[amount];
		int i = 0;
		ResultSet rs = doIt("SELECT name, surname "
				+ "FROM student "
				+ "ORDER BY surname, name ASC");
		try {
			while (rs.next()) {
				result[i++] = rs.getString("surname") + " "
						+ rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
 public void evictStudent(String s){
	 String[] ns = s.split(" ");	
	 
	 String sql = "DELETE FROM student WHERE name ='"+ns[1]+"' AND surname ='"+ns[0]+"'";
	 String getRoom = "SELECT room FROM student WHERE surname = '"+ns[0]+"'";
	 
	 PreparedStatement ps;
	 ResultSet res = doIt(getRoom);
	 int room = 0;
	 try {
		if(res.next()){
			 room = res.getInt("room");
		 }
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	 
	 System.out.println(room);
	 String updateFree = "UPDATE Room SET `free`=`free`+1 WHERE  `number` ='"+room+"'";
	 
	 try {		
		ps = connection.prepareStatement(sql);
		int rs = ps.executeUpdate();
		
		ps = connection.prepareStatement(updateFree);
		rs = ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 
 }
 public void accomodateStudent(String name, String surname, String id, String DormRoom, int d, int m, int y, int smr){
	 String dorm = DormRoom.substring(0, DormRoom.length() - 4);
	String room = DormRoom.substring(DormRoom.length() - 3);

	 Date date = new Date(y, m, d);
	 String ins = "INSERT INTO student(`id_code`, `name`, `surname`, `paySummer`, `room`, `payedTo`) "
		 		+ "VALUES ('"+id+"', '"+name+"', '"+surname+"', "+smr+", "+room+", '"+y+"-"+m+"-"+d+"')";
	 String updateFree = "UPDATE Room SET `free`=`free`-1 WHERE  `number` ='"+room+"' AND numbersInBlock IN (SELECT numbers " +
																											 "FROM Block "+
																											 "WHERE dormNumber IN (SELECT dormNumber " +
																											                      "FROM Dormitory " +
																											                     "WHERE adress = '"+dorm+"'))";
	PreparedStatement ps;
	try {
		ps = connection.prepareStatement(ins);
		int rs = ps.executeUpdate();
		
		ps = connection.prepareStatement(updateFree);
		rs = ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 
	 
	 }
 private void addBlHelp(int n, Integer s, Integer e){
	 int res = e % 100;
	 String [] rooms = new String[4];
	 if (n == 1){
		 
		 
		 if (res == 32){
			 s = s - s % 100 + 101;
			 e = e - e%100 + 104;
			 
		 }
		 else{
			 s += 4;
			 e += 4;
		 }
		 int a1 = s;
		 int a2 = s+1;
		 int a3 = s+2;
		 int a4 = s+3;
			 rooms[0] = "INSERT INTO `room`(`number`, `numbersInBlock`, `capacity`, `free`) "
				 		+ "VALUES ('"+a1+"','"+s+"-"+e+"', 4, 4)";
			 rooms[1] = "INSERT INTO `room`(`number`, `numbersInBlock`, `capacity`, `free`) "
				 		+ "VALUES ('"+a2+"','"+s+"-"+e+"', 1, 1)";
			 rooms[2] = "INSERT INTO `room`(`number`, `numbersInBlock`, `capacity`, `free`) "
				 		+ "VALUES ('"+a3+"','"+s+"-"+e+"', 2, 2)";
			 rooms[3] = "INSERT INTO `room`(`number`, `numbersInBlock`, `capacity`, `free`) "
				 		+ "VALUES ('"+a4+"','"+s+"-"+e+"', 4, 4)";
	 }
	 
	 if (n == 2){
		 
		 
		 if (res == 21){
			 s = s - s % 100 + 101;
			 e = e - e%100 + 103;
		 }
		 else{
			 s += 3;
			 e += 3;
		 }
		 int a1 = s;
		 int a2 = s+1;
		 int a3 = s+2;
			 rooms[0] = "INSERT INTO `room`(`number`, `numbersInBlock`, `capacity`, `free`) "
				 		+ "VALUES ('"+a1+"','"+s+"-"+e+"', 4, 4)";
			 rooms[1] = "INSERT INTO `room`(`number`, `numbersInBlock`, `capacity`, `free`) "
				 		+ "VALUES ('"+a2+"','"+s+"-"+e+"', 4, 4)";
			 rooms[2] = "INSERT INTO `room`(`number`, `numbersInBlock`, `capacity`, `free`) "
				 		+ "VALUES ('"+a3+"','"+s+"-"+e+"', 4, 4)";
	 }
	 
	 String addBl = "INSERT INTO Block(`numbers`, `dormNumber`) VALUES ('"+s+"-"+e+"','"+n+"')";
	 String addR = "";
	 PreparedStatement ps;
		
			try {
				ps = connection.prepareStatement(addBl);
				int rs = ps.executeUpdate();
				
				if (n == 1){
					for (int i = 0;i < 4;i++){
						ps = connection.prepareStatement(rooms[i]);
						rs = ps.executeUpdate();
					}
				}
				if (n == 2){
					for (int i = 0;i < 3;i++){
						ps = connection.prepareStatement(rooms[i]);
						rs = ps.executeUpdate();					
										}
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		
	 
 }
 public void addBlock(String d){
	 String lastBl = "";
	 int dorm = 0;
	 String getLastBl = "SELECT max(B.numbers) as MX, D.dormNumber as dorm FROM Block B INNER JOIN Dormitory D ON B.dormNumber = D.dormNumber WHERE D.adress = '"+d+"' GROUP BY D.dormNumber";
	 ResultSet rs = doIt(getLastBl);
	 try {
		if(rs.next()){
			 lastBl = rs.getString("MX");
			 dorm = rs.getInt("dorm");
		 }
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 String [] res = lastBl.split("-");
	 Integer st = Integer.parseInt(res[0]);
	 Integer en = Integer.parseInt(res[1]);
	 
	 addBlHelp(dorm, st, en);
	 
 }
 public void getInfoToAdmin(String d){
	 String sql = "SELECT zavid, comend, dNumber, cNumber, zNumber FROM Dormitory WHERE adress = '"+d+"'";
 }
 public String [] getAllSt(){
	 int amount = 0;
		ResultSet am = doIt("SELECT COUNT(name) AS AM FROM student");
		try {
			if (am.next()) {
				amount = am.getInt("AM");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String[] result = new String[amount];
		int i = 0;
		ResultSet rs = doIt("SELECT name, surname FROM student ORDER BY surname, name  ASC");
		try {
			while (rs.next()) {
				result[i++] = rs.getString("surname")+" "+ rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
 }
 public String getAboutSt(String s){
	 String [] sn = s.split(" ");
	 String res = "";
	 ResultSet rs = doIt("SELECT * FROM student WHERE surname ='"+sn[0]+"' AND name ='"+sn[1]+"'");
		try {
			if (rs.next()) {
				res = rs.getString("name")+" "+ rs.getString("surname")+"\nКімната: "+rs.getInt("room") + "\nОплачено до: "+ rs.getString("payedTo")+"\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	 
 }
 public int [] getDataOfStudent(String s){
	 String [] sn = s.split(" ");
	 int [] res = new int[4];
	 ResultSet rs = doIt("SELECT * FROM student WHERE surname ='"+sn[0]+"' AND name ='"+sn[1]+"'");
		try {
			if (rs.next()) {
				Date d = rs.getDate("payedTo");
				System.out.println(d);
				res[0] = d.getDate();
				res[1] = d.getMonth()+1;
				res[2] = d.getYear()+1990;
				res[3] = rs.getInt("paySummer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int p : res) System.out.println(p);
		return res;
 }
 public void updateStudent(String sn, int d, int m, int y, int s){
	 String[] res = sn.split(" ");
	 String upd ="UPDATE `student` SET `payedTo`='"+y+"-"+m+"-"+d+"',`paySummer`="+s+" WHERE surname ='"+res[0]+"' AND name ='"+res[1]+"'";
	 PreparedStatement ps;
		try {
			ps = connection.prepareStatement(upd);
			int rs = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
 }
}
