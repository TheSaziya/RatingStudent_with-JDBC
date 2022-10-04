
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Distribution {
	static Scanner sc = new Scanner(System.in);

	public static void displayData(Connection con)

	{
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Distribution");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "  " + rs.getInt(2));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addAssignmentCat(Connection con) {
		try {
			System.out.println("Enter Assignment Category");
			String AssCat = sc.next();

			System.out.println("Enter Weight out of 100");
			int weight = sc.nextInt();

			String sql = "Insert into Distribution(Assignment_Category,Weight) Values(?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, AssCat);
			stmt.setInt(2, weight);

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

	public static void deleteAssignmentCat(Connection con) {
		try {

			System.out.println("Enter Assignment Category");
			String cat = sc.next();

			String sql = "Delete from Distribution where Assignment_Category=?";

			PreparedStatement psmt = con.prepareStatement(sql);

			psmt.setString(1, cat);
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
