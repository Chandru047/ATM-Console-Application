import java.time.LocalDate;
import java.util.Scanner;

public class AdminActions
{
    static Scanner in = new Scanner(System.in);
    Admin_POJO login(Scanner in, String id) {
        while (true) {
            int index = -1;


            for (int i = 0; i < BookMyShow_POJO.getAdminList().size(); i++) {
                if (BookMyShow_POJO.getAdminList().get(i).getId().equals(id)) {
                    index = i;
                    break;
                }
            }


            if (index != -1) {
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (BookMyShow_POJO.getAdminList().get(index).getPass().equals(password)) {
                    System.out.println("Login successful!");
                    BookMyShow.adminOptions();
                    return BookMyShow_POJO.getAdminList().get(index);
                } else {
                    System.out.println("Invalid password. Try again.");
                    return null;
                }
            }

            else {
                System.out.println("ID does not exist. Creating a new account...");
                int enteredPin = 0;
                try {
                    System.out.println("Enter the pin: ");
                    enteredPin = Integer.parseInt(in.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input. Please enter a Numeric value");
                    return null;
                }


                if (enteredPin != Admin_POJO.getPIN()) {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return null;
                }


                Admin_POJO admin = new Admin_POJO();
                admin.setId(id);
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();
                admin.setPass(newPassword);

                BookMyShow_POJO.getAdminList().add(admin);

                System.out.println("Account created successfully!");
                return admin;
            }
        }
    }


    static void addMovie()
    {
        loop :while (true)
        {
            System.out.println("Enter the Movie name");
            String movieName = in.nextLine();
            System.out.println("Enter the Location of the Movie");
            String location = in .nextLine();
            for (Movie movie : BookMyShow_POJO.getMovie())
            {
                if (movie.getMovieName().equals(movieName))
                {
                    if (movie.getLocation().equals(location))
                    {
                        System.out.println("Movie Already exist in this Location");
                        continue loop;
                    }
                }
            }
            System.out.println("Enter the start range of the Movie");
            LocalDate startDate = java.time.LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter());
            System.out.println("Enter the end range of the Movie");
            LocalDate endDate = java.time.LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter());
            Movie movie = new Movie();
            movie.setMovieName(movieName);
            movie.setStartDate(startDate);
            movie.setEndDate(endDate);
            movie.setLocation(location);
            BookMyShow_POJO.getMovie().add(movie);
            System.out.println("Movie Added Successfully");
            break ;
        }



    }

    static void viewAllMovies()
    {
        for (Movie movie : BookMyShow_POJO.getMovie())
        {
            System.out.println("---------------------------------");
            System.out.println("Movie Name : " + movie.getMovieName());
            System.out.println("Movie Location : " + movie.getLocation());
            System.out.println("Start Date : " + movie.getStartDate());
            System.out.println("End Date : " + movie.getEndDate());
            System.out.println("---------------------------------");
        }
    }
}
