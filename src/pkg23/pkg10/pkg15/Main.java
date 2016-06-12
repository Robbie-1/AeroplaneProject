package pkg23.pkg10.pkg15;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pkg23.pkg10.pkg15.Flight.Airline;
import pkg23.pkg10.pkg15.Passenger.Type;

/**
 * @author rm13030
 */
public class Main {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {
		Flight flight1 = new Flight("BA8462", Airline.BRITISH_AIRWAYS, 54, generatePassengers());
		bookTicket(flight1);
    }
	
	public static void bookTicket(Flight f) {
		if (checkAvailability(f)) {
			//seats are available
			ArrayList<Object> info = new ArrayList<Object>();
			System.out.println("What is your name?");
			info.add(sc.next());
			System.out.println("What is your age?");
			info.add(sc.next());
			System.out.println("Are you male or female? (M/F)");
			info.add(sc.next());
			
			if (f.getPassengers().contains(info.get(0))) {
				System.out.println("A double booking has been prevented!\nRestarting the booking service...");
				bookTicket(f);
			} else {
				f.getPassengers().add(new Passenger((String) info.get(0), Integer.valueOf((String) info.get(1)), getGender((String) info.get(2))));
				f.setSeats(f.getFreeSeats()-1); //decrement number of free seats
			
				System.out.println("Congratulations! Your ticket on the flight by "+f.getAirline()+" has been booked successfully!\n[free seats: "+f.getFreeSeats()+" of a total of " +f.getSeats()+"]");
			
				printInfo(f); //debugging
				saveToFile(f);
			}
		} else {
			//seats are NOT available
			return;
		}
	}
	
	public static void saveToFile(Flight f) {
		File file = new File(System.getProperty("user.home"), "flightData.txt");
	    String newLine = System.getProperty("line.separator");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("Flight ID: "+f.getID()+newLine
					+"Airline: "+f.getAirline()+newLine
					+"Percentage of available seats: "+(getFreePerc(f.getFreeSeats(), f.getSeats()))+"%"+newLine
					+"Passengers:"+newLine);
			for(int i=0; i < f.getPassengers().size(); i++) {
				Passenger p = f.getPassengers().get(i);
				fw.write((i+1)+") "+p.getName()+", "+p.getAge()+", "+p.getGender()+newLine);
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Saved to file successfully! (location: "+System.getProperty("user.home")+")");
		}
	}
	
	public static Type getGender(String UI) {
		if (UI.equalsIgnoreCase("M")) {
			return Type.MALE;
		} else {
			return Type.FEMALE;
		}
	}
	
	/**
	 * Debugging
	 */
	public static void printInfo(Flight f) {
		System.out.println("Spitting info...\n");
		System.out.println("Flight ID: "+f.getID()+"\n"
						  +"Airline: "+f.getAirline()+"\n"
						  +"Percentage of available seats: "+(getFreePerc(f.getFreeSeats(), f.getSeats()))+"%\n"
						  +"Passengers:\n");
		for(int i=0; i < f.getPassengers().size(); i++) {
			Passenger p = f.getPassengers().get(i);
			System.out.println((i+1)+") "+p.getName()+", "+p.getAge()+", "+p.getGender());
		}
	}
	
	public static double getFreePerc(int freeSeats, int totalSeats) {
		return ((double) freeSeats / totalSeats * 100);
	}
    
	/**
	 * Debugging
	 */
    public static ArrayList<Passenger> generatePassengers() {
    	ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    	
    	passengers.add(new Passenger("George Taylor", 17, Type.MALE));
		
    	return passengers;
    }
    
    public static boolean checkAvailability(Flight f) {
    	if (f.getFreeSeats() > 0) {
    		System.out.println("Congratulations! There are seats available for this flight.");
    		return true;
    	} else {
    		System.out.println("Sorry, no seats are available at this time.");
    		return false;
    	}
    }
    
}
