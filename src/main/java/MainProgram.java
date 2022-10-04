
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainProgram {

	public static void main(String[] args) throws SQLException {

		Connection con1 = null;

		Scanner sc = new Scanner(System.in);
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "xe");

			System.out.println("      WELCOME     ");
			System.out.println("----------------------");

			String firstChoice = "yes";

			System.out.println("Select Operation You Want To Perform ");

			while (firstChoice.equals("yes")) {
				System.out.println("\n");
				System.out.println("1. Operations on Distribution Table");
				System.out.println("2. Operations on Student Information Table");
				System.out.println("3. Calculate Student Ratings");
				System.out.println("4. Exit");

				System.out.println("Enter Your Choice ");
				int ch = sc.nextInt();

				switch (ch) {
				case 1: {
					// Operations On Distribution Table

					String choice = "yes";
					System.out.println(" Operations On Distribution Table ");

					while (choice.equals("yes")) {

						System.out.println("\n");
						System.out.println("1. Add Assignment Category ");
						System.out.println("2. Remove Assignment Category ");
						System.out.println("3. Display Distribution Table ");
						System.out.println("4. Exit ");
						// System.out.println("\n");

						System.out.println("Enter Your Choice ");
						int ch1 = sc.nextInt();

						switch (ch1) {
						case 1: {
							// Add Assignment Category
							Distribution.addAssignmentCat(con1);
							System.out.println("--------------------------------------");
							break;

						}
						case 2: {
							// Delete Assignment Category
							Distribution.deleteAssignmentCat(con1);
							System.out.println("--------------------------------------");
							break;

						}
						case 3: {

							// Display Distribution Table
							Distribution.displayData(con1);
							System.out.println("--------------------------------------");
							break;

						}
						default: {
							System.out.println("Oops ! Wrong Choice.");
						}

						}

						System.out.println("Do You Wish To Continue ? yes/no");
						choice = sc.next();

					}
					break;
				}
				case 2: {

					// Operations On Student Info Table

					System.out.println("\n");

					String choice1 = "yes";
					System.out.println(" Operations On Student Information Table ");

					while (choice1.equals("yes")) {

						System.out.println("\n");
						System.out.println("1. Add Student Information ");
						System.out.println("2. Remove Student Informaton ");
						System.out.println("3. Display Student Information ");
						System.out.println("4. Exit ");
						// System.out.println("\n");

						System.out.println("Enter Your Choice ");
						int ch2 = sc.nextInt();

						switch (ch2) {
						case 1: {
							// Add StudentInfo
							StudentInformation.addStudentInfo(con1);
							System.out.println("--------------------------------------");
							break;

						}
						case 2: {
							// Delete StudentInfo
							StudentInformation.deleteStudentInfo(con1);
							System.out.println("--------------------------------------");
							break;

						}
						case 3: {

							// Display StudentInfo Table
							StudentInformation.displayStudentData(con1);
							System.out.println("--------------------------------------");
							break;

						}
						default: {
							System.out.println("Oops ! Wrong Choice.");
						}

						}

						System.out.println("Do You Wish To Continue ? yes/no");
						choice1 = sc.next();

					}
					break;
				}
				case 3: {
					// Operations For Calculating Ratings

					System.out.println("\n");

					String choice2 = "yes";
					System.out.println(" Calculate Student Ratings ");

					while (choice2.equals("yes")) {

						System.out.println("\n");
						System.out.println("1. Calculate Rating Per Student ");
						System.out.println("2. Calculate Rating Per Subject  ");
						System.out.println("3. Exit ");
						// System.out.println("\n");

						System.out.println("Enter Your Choice ");
						int ch3 = sc.nextInt();

						switch (ch3) {
						case 1: {
							// Rating Per Student
							System.out.println("Enter Student Name :");
							String name = sc.next();
							RatingCalculation.calculateRatingPerStudent(con1, name);
							// System.out.println("--------------------------------------");
							break;

						}
						case 2: {
							// Rating Per Subject
							sc.nextLine();
							System.out.print("Enter Subject :");
							String subject = sc.nextLine();

							// System.out.println(subject);
							RatingCalculation.calculateRatingPerSubject(con1, subject);
							// System.out.println("--------------------------------------");
							break;

						}
						default: {
							System.out.println("Oops ! Wrong Choice.");
						}

						}

						System.out.println("Do You Wish To Continue ? yes/no");
						choice2 = sc.next();

					}
					break;
				}
				}

				System.out.println("Want to Continue Operations?yes/no");
				firstChoice = sc.next();

			}

			System.out.println("     Thank You     ");

		}

		catch (Exception e) {
			System.out.println(e);
		} finally {
			con1.close();
		}

	}

}
