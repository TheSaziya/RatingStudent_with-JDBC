import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RatingCalculation {
	public static void calculateRatingPerStudent(Connection con, String name) throws SQLException {
		try {

			int points = 0;
			int weight = 0;

			System.out.println("Student :" + name);
			System.out.println("\n");

			ArrayList<Double> scorelist = new ArrayList<Double>();

			Statement stmt = con.createStatement();

			Statement stmt1 = con.createStatement();

			HashSet<String> subhs = new HashSet<String>();
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("select * from Student_Info");

			while (rs2.next()) {

				subhs.add(rs2.getString(2));
			}
			String[] sublist = subhs.toArray(new String[subhs.size()]);

			for (int i = 0; i < sublist.length; i++) {
				String tempsub = sublist[i];
				System.out.println(tempsub);
				ResultSet rs1 = stmt1.executeQuery("Select * from Distribution");
				while (rs1.next()) {
					int count = 0;

					double score = 0.0;
					double finalscore = 0.0;
					double tempscore = 0.0;

					ResultSet rs = stmt.executeQuery("select * from Student_Info");
					while (rs.next()) {
						if (rs.getString(1).equals(name) && rs.getString(2).equals(tempsub)) {
							String cat = rs.getString(3);

							String tempcat[] = cat.split("_");

							if (rs1.getString(1).equals(tempcat[0])) {
								weight = rs1.getInt(2);

								points = rs.getInt(5);

								count++;

								tempscore = weight / 1 * points / 100;

								score = score + tempscore;

								finalscore = score / count;

							}
						}
					}

					scorelist.add(finalscore);

				}
				double sum = 0.0;

				Statement stmt3 = con.createStatement();
				ResultSet rs3 = stmt3.executeQuery("Select * from Distribution");

				ArrayList<String> scores = new ArrayList<String>();
				while (rs3.next()) {
					scores.add(rs3.getString(1));
				}

				for (int l = 0; l < scorelist.size(); l++) {
					sum = sum + scorelist.get(l);

					System.out.println(scores.get(l) + " :" + scorelist.get(l));

				}
				System.out.println("Overall Rating :" + sum);

				for (int l = 0; l < scorelist.size(); l++) {
					scorelist.removeAll(scorelist);
				}

				System.out.println("-----------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void calculateRatingPerSubject(Connection con, String sub) {
		try {

			int points = 0;
			int weight = 0;

			System.out.println("Subject :" + sub);
			System.out.println("\n");

			ArrayList<Double> scorelist = new ArrayList<Double>();

			Statement stmt = con.createStatement();

			Statement stmt1 = con.createStatement();

			HashSet<String> studhs = new HashSet<String>();
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("select * from Student_Info");
			while (rs2.next()) {

				studhs.add(rs2.getString(1));
			}
			String[] sublist = studhs.toArray(new String[studhs.size()]);
			Arrays.sort(sublist);

			for (int i = 0; i < sublist.length; i++) {
				String tempstud = sublist[i];
				System.out.println(tempstud);

				ResultSet rs1 = stmt1.executeQuery("Select * from Distribution");
				while (rs1.next()) {
					int count = 0;
					double score = 0.0;
					double finalscore = 0.0;
					double tempscore = 0.0;

					ResultSet rs = stmt.executeQuery("select * from Student_Info");
					while (rs.next()) {
						if (rs.getString(1).equals(tempstud) && rs.getString(2).equals(sub)) {
							String cat = rs.getString(3);
							String tempCat[] = cat.split("_");

							if (rs1.getString(1).equals(tempCat[0])) {
								weight = rs1.getInt(2);
								points = rs.getInt(5);
								count++;

								tempscore = weight / 1 * points / 100;
								// System.out.println("TempScore :" + tempscore);
								score = score + tempscore;
								// System.out.println("Score :" + score);
								finalscore = score / count;
								// System.out.println("FinalScore :" + finalscore);
							}
						}
					}
					scorelist.add(finalscore);

				}
				double sum = 0.0;
				// System.out.println(sublist[i]);
				Statement stmt3 = con.createStatement();
				ResultSet rs3 = stmt3.executeQuery("Select * from Distribution");

				ArrayList<String> scores = new ArrayList<String>();
				while (rs3.next()) {
					scores.add(rs3.getString(1));
				}

				for (int l = 0; l < scorelist.size(); l++) {
					sum = sum + scorelist.get(l);

					System.out.println(scores.get(l) + " : " + scorelist.get(l));

				}
				System.out.println("Overall Rating :" + sum);

				for (int l = 0; l < scorelist.size(); l++) {
					scorelist.removeAll(scorelist);
				}

				System.out.println("-----------------------");

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
