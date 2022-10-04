import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentInformation {
	static Scanner sc = new Scanner(System.in);

	public static void displayStudentData(Connection con) {

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Student_Info");
			while (rs.next()) {
				System.out.println(rs.getString(1) + " ---- " + rs.getString(2) + "----  " + rs.getString(3) + " ----- "
						+ rs.getDate(4) + " -----" + " " + rs.getInt(5));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addStudentInfo(Connection con) {
		try {
			System.out.println("Enter Student Name");
			String sname = sc.next();

			System.out.println("Enter Subject");
			String sub = sc.next();

			System.out.println("Enter Assignment Category");
			String asscat = sc.next();

			System.out.println("Enter Date Of Submission");
			String date = sc.next();

			System.out.println("Enter Points");
			int points = sc.nextInt();

			String sql = "Insert into Student_Info(Student_Name,Subject,Assignment_Cat,Date_Of_Sub,Points) Values(?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sname);
			stmt.setString(2, sub);
			stmt.setString(3, asscat);
			stmt.setString(4, date);

			stmt.setInt(5, points);

			int i = stmt.executeUpdate();

			if (i > 0)
				System.out.println("Successfully Added !");
			else
				System.out.println("Unsuccessful Insertion !");

			// stmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteStudentInfo(Connection con) {
		try {

			System.out.println("Enter Student Name");
			String sname = sc.next();

			System.out.println("Enter Subject");
			String sub = sc.next();

			System.out.println("Enter Assignment Category");
			String asscat = sc.next();

			String sql = "Delete from Student_Info where Student_Name=? and Subject=? and Assignment_Cat=?";

			PreparedStatement psmt = con.prepareStatement(sql);

			psmt.setString(1, sname);
			psmt.setString(2, sub);
			psmt.setString(3, asscat);
			int i = psmt.executeUpdate();

			if (i > 0)
				System.out.println("Successfully Deleted");
			else
				System.out.println("Unsuccessful Deletion");

		} catch (Exception e) {

			System.out.println(e);

		}
	}
}
