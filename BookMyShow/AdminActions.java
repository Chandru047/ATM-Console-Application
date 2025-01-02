import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions
{
    static Scanner in = new Scanner(System.in);
    Admin_POJO login(Scanner in, String id) {
        while (true) {
            int index = -1;

            // for loop to check if the user already exist
            for (int i = 0; i < BookMyShow_POJO.getAdminList().size(); i++) {
                if (BookMyShow_POJO.getAdminList().get(i).getId().equals(id)) {
                    index = i; // if a user is found with a same id then change the index value
                    break;
                }
            }


            if (index != -1) // if the index remains unchanged it means the user already exist
            {
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (BookMyShow_POJO.getAdminList().get(index).getPass().equals(password)) // if the password matches then get to admin operations
                {
                    System.out.println("Login successful!");
                    BookMyShow.adminOptions();
                    return BookMyShow_POJO.getAdminList().get(index);
                } else {
                    System.out.println("Invalid password. Try again.");
                    return null;
                }
            }

            else // if id does not exist then procedure to create a new admin account
            {
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
            boolean available = false ;
            for (Theatre_POJO theatrePojo : BookMyShow_POJO.getTheatre()) // for loop to check if any theatre exist in the location
            {
                if (theatrePojo.getTheatreLocation().equals(location)) // if the location already exists in theatre then change available to true
                {
                    available = true ;
                    break ;
                }

            }
            if (!available) // if no theatre is available in that location then exit out of addMovie logic
            {
                System.out.println("------------------ No Theatres Available in your Current Location --------------------");
                return;
            }
            for (Movie movie : BookMyShow_POJO.getMovie()) // for loop to check if the movie already exist in the location
            {
                if (movie.getMovieName().equals(movieName))
                {
                    if (movie.getLocation().equals(location))
                    {
                        System.out.println("Movie Already exist in this Location");
                        continue loop; // if movie already exist then start the logic again
                    }
                }
            }
            System.out.println("Enter the start range of the Movie");
            LocalDate startDate = java.time.LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter());
            System.out.println("Enter the end range of the Movie");
            LocalDate endDate = java.time.LocalDate.parse(in.nextLine(), BookMyShow_POJO.getDateFormatter());
            Movie movie = new Movie();  // create a new movie object
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
        for (Movie movie : BookMyShow_POJO.getMovie()) // for loop to print all the details of the available movies
        {
            System.out.println("---------------------------------");
            System.out.println("Movie Name : " + movie.getMovieName());
            System.out.println("Movie Location : " + movie.getLocation());
            System.out.println("Start Date : " + movie.getStartDate());
            System.out.println("End Date : " + movie.getEndDate());
            System.out.println("---------------------------------");
        }
    }

    static void addTheatre()
    {
        System.out.println("Enter the name of the Theatre");
        String theatreName = in.nextLine();
        System.out.println("Enter the Location : ");
        String location = in.nextLine();

        for (Theatre_POJO theatre : BookMyShow_POJO.getTheatre()) // for loop to iterate between all theatre objects
        {
            if (theatre.getTheatreName().equals(theatreName) && theatre.getTheatreLocation().equals(location)) // if condition to check if the theatre already exist in that location
            {
                System.out.println("Location already exists !");
                return;
            }
        }

        ArrayList<ScreenPOJO> theatreScreen = new ArrayList<>();


        System.out.println("Enter the  Screen count :");
        int screenCount = Integer.parseInt(in.nextLine());

        while (screenCount != 0) // while loop to get the details of all the available screens
        {
            System.out.println("Enter the name of the Screen ");
            String screenName = in.nextLine();
            System.out.println("Enter the number of Seats in Screen" + screenName);
            String numberSeats = in.nextLine();
            System.out.println("Enter the Screen Grid (eg:2*8*2)");
            String screenGrid = in.nextLine();
            var grid = Utilities.generateSeatingPatterns(numberSeats, screenGrid);
            theatreScreen.add(new ScreenPOJO(screenName, numberSeats, grid));

            for(var seats: grid.entrySet())
            {
                System.out.println(seats.getKey()+"---"+seats.getValue());
            }
            screenCount--;
        }
        Theatre_POJO theatre = new Theatre_POJO(); // creating a new theatre object
        theatre.setTheatreName(theatreName); // setting the theatre name to the created object
        theatre.setTheatreLocation(location); // setting the theatre location to the created object
        theatre.setScreen(theatreScreen); // setting the theatre screen arrayList to the created object
        BookMyShow_POJO.getTheatre().add(theatre); // add the created object to the arraylist
        System.out.println("Theatre added Successfully");
    }
}
