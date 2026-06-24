import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class InvalidInputException extends Exception {
    public InvalidInputException(String msg) { super(msg); }
}

class BookingException extends Exception {
    public BookingException(String msg) { super(msg); }
}

// INTERFACES
interface Storable {
    String toRecord();
}

interface Payment {
    boolean pay(double amount);
    String paymentDetails();
}

interface PricingStrategy {
    double calculate(double baseFare);
}

interface Validator {
    boolean validate();
    String getErrorMessage();
}

//  ABSTRACT PERSON CLASS
abstract class Person {
    protected String id;
    protected String name;
    protected int age;
    protected String gender;

    public Person(String id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }

    public abstract String roleDescription();
}

// PASSENGER CLASS
class Passenger extends Person implements Storable, Validator {
    private String contact;
    private double wallet;

    public Passenger(String id, String name, int age, String gender, String contact) {
        super(id, name, age, gender);
        this.contact = contact;
        this.wallet = 0.0;
    }

    public String getContact() { return contact; }
    public double getWallet() { return wallet; }
    public void setContact(String contact) { this.contact = contact; }

    public void addToWallet(double amt) { wallet += amt; }
    public boolean deductFromWallet(double amt) {
        if (wallet >= amt) { wallet -= amt; return true; }
        return false;
    }

    @Override
    public String roleDescription() {
        return "Passenger: " + name + " (" + id + ")";
    }

    @Override
    public boolean validate() {
        return name != null && !name.trim().isEmpty()
                && age > 0 && age < 150
                && contact != null && !contact.trim().isEmpty();
    }

    @Override
    public String getErrorMessage() {
        if (name == null || name.trim().isEmpty()) return "Name cannot be empty";
        if (age <= 0 || age >= 150) return "Age must be between 1-149";
        if (contact == null || contact.trim().isEmpty()) return "Contact cannot be empty";
        return "";
    }

    @Override
    public String toRecord() {
        return id + "|" + escape(name) + "|" + age + "|" + gender + "|" + contact + "|" + wallet;
    }

    public static Passenger fromRecord(String rec) {
        String[] parts = rec.split("\\|");
        if (parts.length < 6) return null;
        String pid = parts[0];
        String name = unescape(parts[1]);
        int age = Integer.parseInt(parts[2]);
        String gender = parts[3];
        String contact = parts[4];
        double wallet = Double.parseDouble(parts[5]);
        Passenger p = new Passenger(pid, name, age, gender, contact);
        p.wallet = wallet;
        return p;
    }
//escape is to replace any spaces with _ in case of name..
    //unescape is to replace _ with " " and return the string.
    private static String escape(String s) { return s.replace(" ", "_"); }
    private static String unescape(String s) { return s.replace("_", " "); }
}

// ADMIN CLASS
class Admin extends Person {
    private String username;
    private String password;

    public Admin(String id, String name, int age, String gender, String username, String password) {
        super(id, name, age, gender);
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public boolean checkPassword(String pass) { return this.password.equals(pass); }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String roleDescription() {
        return "Admin: " + name + " (" + username + ")";
    }
}

// PRICING STRATEGIES
class EconomyPricing implements PricingStrategy {
    @Override
    public double calculate(double baseFare) { return baseFare; }
}

class BusinessPricing implements PricingStrategy {
    @Override
    public double calculate(double baseFare) { return baseFare * 1.5; }
}

class FirstPricing implements PricingStrategy {
    @Override
    public double calculate(double baseFare) { return baseFare * 2.0; }
}

// PAYMENT IMPLEMENTATION
class CardPayment implements Payment, Validator {
    private String cardNumber;
    private String cardHolder;
    private String expiry;

    public CardPayment(String cardNumber, String cardHolder, String expiry) {
        this.cardNumber = cardNumber.replaceAll("\\s", "");
        this.cardHolder = cardHolder;
        this.expiry = expiry;
    }

    @Override
    public boolean validate() {
        return cardNumber != null && cardNumber.length() >= 12
                && cardHolder != null && !cardHolder.trim().isEmpty()
                && expiry != null && expiry.matches("\\d{2}/\\d{2}");
    }

    @Override
    public String getErrorMessage() {
        if (cardNumber == null || cardNumber.length() < 12) return "Invalid card number";
        if (cardHolder == null || cardHolder.trim().isEmpty()) return "Card holder name required";
        if (expiry == null || !expiry.matches("\\d{2}/\\d{2}")) return "Expiry format: MM/YY";
        return "";
    }

    @Override
    public boolean pay(double amount) {
        if (!validate() || amount <= 0) return false;
        return true;
    }

    @Override
    public String paymentDetails() {
        return "Card:" + last4();
    }

    private String last4() {
        return cardNumber.length() <= 4 ? cardNumber :
                cardNumber.substring(cardNumber.length() - 4);
    }
}

// DATA MANAGER .. Helper for GUI
class DataManager {

    // Read all passengers
    public static ArrayList<String[]> readPassengers() {
        ArrayList<String[]> passengers = new ArrayList<>();
        File f = new File("passengers.txt");
        if (!f.exists()) return passengers;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    passengers.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public static void saveTickets(List<String[]> tickets) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("tickets.txt"))) {
            for (String[] t : tickets) {
                pw.println(String.join("|", t));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Append passenger
    public static void appendPassenger(String[] passengerData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("passengers.txt", true))) {
            bw.write(String.join("|", passengerData));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read all tickets
    public static ArrayList<String[]> readTickets() {
        ArrayList<String[]> tickets = new ArrayList<>();
        File f = new File("tickets.txt");
        if (!f.exists()) return tickets;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 9) {
                    tickets.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Append ticket
    public static void appendTicket(String[] ticketData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.txt", true))) {
            bw.write(String.join("|", ticketData));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save all tickets (for updates/deletions)
    public static void saveAllTickets(ArrayList<String[]> tickets) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.txt"))) {
            for (String[] ticket : tickets) {
                bw.write(String.join("|", ticket));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reduce seat count for a flight
    public static void reduceSeat(String flightID, String seatClass) {
        // This is handled by Flight object directly in the GUI
    }
}

// MAIN AIRLINE SYSTEM CLASS
public class AirlineSystem {

    // FLIGHT CLASS
    public static class Flight implements Storable, Validator {
        private String flightID;
        private String source;
        private String destination;
        private String date;
        private String time;
        private int firstSeats;
        private int businessSeats;
        private int economySeats;
        private double baseFare;

        public Flight(String flightID, String source, String destination, String date, String time,
                      double baseFare, int firstSeats, int businessSeats, int economySeats) {
            this.flightID = flightID;
            this.source = source;
            this.destination = destination;
            this.date = date;
            this.time = time;
            this.baseFare = baseFare;
            this.firstSeats = firstSeats;
            this.businessSeats = businessSeats;
            this.economySeats = economySeats;
        }

        // Getters
        public String getFlightID() { return flightID; }
        public String getSource() { return source; }
        public String getDestination() { return destination; }
        public String getDate() { return date; }
        public String getTime() { return time; }
        public int getFirstSeats() { return firstSeats; }
        public int getBusinessSeats() { return businessSeats; }
        public int getEconomySeats() { return economySeats; }
        public double getBaseFare() { return baseFare; }

        // Setters
        public void setFirstSeats(int firstSeats) { this.firstSeats = firstSeats; }
        public void setBusinessSeats(int businessSeats) { this.businessSeats = businessSeats; }
        public void setEconomySeats(int economySeats) { this.economySeats = economySeats; }

        public int getTotalAvailableSeats() {
            return firstSeats + businessSeats + economySeats;
        }

        public boolean reserveSeat(String seatClass) {
            switch (seatClass.toLowerCase()) {
                case "first":
                    if (firstSeats <= 0) return false;
                    firstSeats--;
                    return true;
                case "business":
                    if (businessSeats <= 0) return false;
                    businessSeats--;
                    return true;
                case "economy":
                    if (economySeats <= 0) return false;
                    economySeats--;
                    return true;
                default: return false;
            }
        }

        public void restoreSeat(String seatClass) {
            switch (seatClass.toLowerCase()) {
                case "first": firstSeats++; break;
                case "business": businessSeats++; break;
                case "economy": economySeats++; break;
            }
        }

        @Override
        public boolean validate() {
            return flightID != null && !flightID.trim().isEmpty()
                    && source != null && !source.trim().isEmpty()
                    && destination != null && !destination.trim().isEmpty()
                    && date != null && !date.trim().isEmpty()
                    && baseFare > 0;
        }

        @Override
        public String getErrorMessage() {
            if (flightID == null || flightID.trim().isEmpty()) return "Flight ID cannot be empty";
            if (source == null || source.trim().isEmpty()) return "Source cannot be empty";
            if (destination == null || destination.trim().isEmpty()) return "Destination cannot be empty";
            if (date == null || date.trim().isEmpty()) return "Date cannot be empty";
            if (baseFare <= 0) return "Base fare must be positive";
            return "";
        }

        @Override
        public String toRecord() {
            return flightID + "|" + source + "|" + destination + "|" + date + "|" + time + "|"
                    + baseFare + "|" + firstSeats + "|" + businessSeats + "|" + economySeats;
        }

        public static Flight fromRecord(String rec) {
            String[] p = rec.split("\\|");
            if (p.length < 9) return null;
            return new Flight(p[0], p[1], p[2], p[3], p[4],
                    Double.parseDouble(p[5]), Integer.parseInt(p[6]),
                    Integer.parseInt(p[7]), Integer.parseInt(p[8]));
        }

        @Override
        public String toString() {
            return String.format("%s: %s → %s (%s %s) [F:%d B:%d E:%d] $%.2f",
                    flightID, source, destination, date, time,
                    firstSeats, businessSeats, economySeats, baseFare);
        }
    }

    // BOOKING CLASS
    public static class Booking implements Storable {
        private String bookingID;
        private String passengerID;
        private String flightID;
        private String seatClass;
        private String seatNumber;
        private double price;
        private String timestamp;
        private String status;

        public Booking(String bookingID, String passengerID, String flightID,
                       String seatClass, String seatNumber, double price) {
            this.bookingID = bookingID;
            this.passengerID = passengerID;
            this.flightID = flightID;
            this.seatClass = seatClass;
            this.seatNumber = seatNumber;
            this.price = price;
            this.timestamp = now();
            this.status = "CONFIRMED";
        }

        public String getBookingID() { return bookingID; }
        public String getPassengerID() { return passengerID; }
        public String getFlightID() { return flightID; }
        public String getSeatClass() { return seatClass; }
        public String getSeatNumber() { return seatNumber; }
        public double getPrice() { return price; }
        public String getTimestamp() { return timestamp; }
        public String getStatus() { return status; }

        public void setStatus(String status) { this.status = status; }

        private static String now() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }

        @Override
        public String toRecord() {
            return bookingID + "|" + passengerID + "|" + flightID + "|" +
                    seatClass + "|" + seatNumber + "|" + price + "|" +
                    timestamp.replace(" ", "_") + "|" + status;
        }

        public static Booking fromRecord(String rec) {
            String[] p = rec.split("\\|");
            if (p.length < 8) return null;
            Booking b = new Booking(p[0], p[1], p[2], p[3], p[4], Double.parseDouble(p[5]));
            b.timestamp = p[6].replace("_", " ");
            b.status = p[7];
            return b;
        }
    }

    // FLIGHT HANDLER
    public static class FlightHandler {
        private static final String FLIGHTS_FILE = "flights.txt";

        public static List<Flight> getAllFlights() {
            List<Flight> flights = new ArrayList<>();
            File f = new File(FLIGHTS_FILE);
            if (!f.exists()) return flights;

            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Flight flight = Flight.fromRecord(line);
                    if (flight != null) flights.add(flight);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return flights;
        }

        public static Flight getFlightByID(String flightID) {
            for (Flight f : getAllFlights()) {
                if (f.getFlightID().equalsIgnoreCase(flightID)) {
                    return f;
                }
            }
            return null;
        }

        public static void addFlight(Flight flight) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FLIGHTS_FILE, true))) {
                bw.write(flight.toRecord());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static boolean removeFlight(String flightID) {
            List<Flight> flights = getAllFlights();
            boolean removed = flights.removeIf(f -> f.getFlightID().equalsIgnoreCase(flightID));
            if (removed) {
                saveAllFlights(flights);
            }
            return removed;
        }

        public static void updateFlights(List<Flight> flights) {
            saveAllFlights(flights);
        }

        private static void saveAllFlights(List<Flight> flights) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FLIGHTS_FILE))) {
                for (Flight f : flights) {
                    bw.write(f.toRecord());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // BOOKING HANDLER
    public static class BookingHandler {
        private static final String BOOKINGS_FILE = "bookings.txt";

        public static List<Booking> getAllBookings() {
            List<Booking> bookings = new ArrayList<>();
            File f = new File(BOOKINGS_FILE);
            if (!f.exists()) return bookings;

            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Booking booking = Booking.fromRecord(line);
                    if (booking != null) bookings.add(booking);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bookings;
        }

        public static List<Booking> getBookingsByPassenger(String passengerID) {
            List<Booking> result = new ArrayList<>();
            for (Booking b : getAllBookings()) {
                if (b.getPassengerID().trim().equals(passengerID)) {
                    result.add(b);
                }
            }
            return result;
        }


        public static boolean cancelBookingAndRestoreSeat(String bookingID) {
            List<Booking> bookings = getAllBookings();
            Booking toCancel = null;

            for (Booking b : bookings) {
                if (b.getBookingID().equalsIgnoreCase(bookingID)) {
                    toCancel = b;
                    break;
                }
            }

            if (toCancel == null) return false;

            // Restore seat in flight
            //get the flight to be edited..
            Flight flight = FlightHandler.getFlightByID(toCancel.getFlightID());
            if (flight != null) {
                //calling get seat class to get the class of seat and then pass it to restore seat..
                flight.restoreSeat(toCancel.getSeatClass());
                List<Flight> flights = FlightHandler.getAllFlights();
//                now we loop through all flights until we find the flight to be updated..
                for (int i = 0; i < flights.size(); i++) {
                    if (flights.get(i).getFlightID().equals(flight.getFlightID())) {
                        flights.set(i, flight);//updating the flight..
                        break;
                    }
                }
                FlightHandler.updateFlights(flights);
            }

            // Mark booking as cancelled
            toCancel.setStatus("CANCELLED");
            //finding that booking to be removed and removing it
            for (int i = 0; i < bookings.size(); i++) {
                if (bookings.get(i).getBookingID().equals(bookingID)) {
                    bookings.set(i, toCancel);
                    break;
                }
            }
            saveAllBookings(bookings);
            // ALSO remove from tickets.txt
            ArrayList<String[]> ticketList = DataManager.readTickets();
            ticketList.removeIf(t -> t[0].equalsIgnoreCase(bookingID));
            DataManager.saveAllTickets(ticketList);


            return true;
        }

        private static void saveAllBookings(List<Booking> bookings) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKINGS_FILE))) {
                for (Booking b : bookings) {
                    bw.write(b.toRecord());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {
        // Initialize default admin


        // Start GUI
        java.awt.EventQueue.invokeLater(() -> {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // This opens WelcomePage
            new WelcomePage().setVisible(true);
        });
    }}