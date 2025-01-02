import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminActions extends Actions {
    static Scanner in = new Scanner(System.in);

    String login(String id) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int index = -1;

            for (int i = 0; i < BookMyShow.getAdminList().size(); i++) {
                if (BookMyShow.getAdminList().get(i).getId().equals(id)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                System.out.println("Enter the password: ");
                String password = in.nextLine();

                if (BookMyShow.getAdminList().get(index).getPass().equals(password)) {
                    System.out.println("Login successful!");
                    BookMyShow.adminOptions();
                    return "Success";
                } else {
                    System.out.println("Invalid password. Try again.");
                    return "Invalid";
                }
            } else {
                System.out.println("ID does not exist. Creating a new account...");
                System.out.println("Enter the pin: ");
                int enteredPin = Integer.parseInt(in.nextLine());


                if (enteredPin != Admin.getPIN()) {
                    System.out.println("Incorrect pin. Account creation failed.");
                    return "Invalid";
                }

                Admin admin = new Admin();
                admin.setId(id);
                System.out.println("Enter the new password: ");
                String newPassword = in.nextLine();

                admin.setPass(newPassword);
                BookMyShow.getAdminList().add(admin);

                System.out.println("Account created successfully!");
                return "Created";
            }
        }
    }

    static void addLocation() {
        System.out.println("Enter the location:");
        String location = in.nextLine();
        if (BookMyShow.getLocationAndTheatre().containsKey(location)) {
            System.out.println("Location already exists");
        } else {
            BookMyShow.getLocationAndTheatre().put(location, new ArrayList<TheatrePOJO>());
            System.out.println("Location added Successfully");
        }
    }

    static void addTheatre() {
        System.out.println("Enter the Location : ");
        String location = in.nextLine();
        if (!BookMyShow.getLocationAndTheatre().containsKey(location)) {
            System.out.println("Enter Does not exist");
            System.out.println("Please enter an valid Location");
            return;
        }
        ArrayList<Screens> theatreScreen = new ArrayList<>();
        System.out.println("Enter the name of the Theatre");
        String theatreName = in.nextLine();
        if (!(BookMyShow.getLocationAndTheatre().containsValue(theatreName))) {
            System.out.println("Enter the  Screen count :");
            int screenCount = Integer.parseInt(in.nextLine());
            while (screenCount != 0) {
                System.out.println("Enter the name of the Screen " + screenCount);
                String screenName = in.nextLine();
                System.out.println("Enter the number of Seats in Screen" + screenName);
                String numberSeats = in.nextLine();
                System.out.println("Enter the Screen Grid (eg:2*8*2)");
                String screenGrid = in.nextLine();
                var grid = Utilities.generateSeatingPatterns(numberSeats, screenGrid);
                theatreScreen.add(new Screens(screenName, numberSeats, grid));
                screenCount--;
            }
            System.out.println("Theatre added Successfully");
            BookMyShow.getLocationAndTheatre().get(location).add(new TheatrePOJO(theatreName, theatreScreen));
        } else {
            System.out.println("Theatre already Exists !!!");
        }

    }

    static void addMovie() {
        System.out.println("Enter the location of the Theatre: ");
        String location = in.nextLine();
        ArrayList<MoviePOJO> movie = BookMyShow.getLocationAndMovie().get(location);
        if (movie == null)
        {
            movie = new ArrayList<>();
        }

        if (!BookMyShow.getLocationAndTheatre().containsKey(location)) {
            System.out.println("Location not available.");
            return;
        }


        ArrayList<TheatrePOJO> theatres = BookMyShow.getLocationAndTheatre().get(location);
        System.out.println("Theatres at " + location + ":");
        for (TheatrePOJO theatre : theatres) {
            System.out.println("- " + theatre.getTheatreName());
        }


        System.out.println("Select from the available theatres:");
        String selectedTheatre = in.nextLine();

        for (TheatrePOJO theatre : theatres) {
            if (!(theatre.getTheatreName().equals(selectedTheatre))) {
                System.out.println("Please enter a valid Theatre");
            } else {
                System.out.println("Available screens in theatre - " + selectedTheatre);

                for (var screen : theatre.getScreen()) {
                    System.out.println(screen.getScreenName());
                }

            }

            System.out.println("Select the Screen for the Film: ");
            String selectedScreen = in.nextLine();
            if (theatre.getTheatreName().equals(selectedTheatre))
            {
                System.out.println("Enter the Movie Name: ");
                String movieName = in.nextLine();
                System.out.println("Enter the Date for the movie :");
                String dateS = in.nextLine();
                LocalDate date = java.time.LocalDate.parse(dateS, BookMyShow.getDateFormatter());
                System.out.println("Enter the Start Time (24 Hrs) :");
                String startTimeS = in.nextLine();
                LocalTime startTime = LocalTime.parse(startTimeS ,BookMyShow.getTimeFormatter());
                System.out.println("Enter the duration of the movie : ");
                long duration = Long.parseLong(in.nextLine());
                LocalTime endTime = startTime.plusMinutes(duration).plusMinutes(30);
                movie.add(new MoviePOJO(movieName));
                System.out.println("Movie added Sucessfully");

            }

        }
        BookMyShow.getLocationAndMovie().put(location , movie );
    }
}
