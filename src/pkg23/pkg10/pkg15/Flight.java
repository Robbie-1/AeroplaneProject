package pkg23.pkg10.pkg15;

import java.util.ArrayList;

/**
 * @author rm13030
 */
public class Flight {
    
    String flightID;
    Airline airline;
    int totalSeats;
    int freeSeats;
	ArrayList<Passenger> passengerMap = new ArrayList<Passenger>();
	ArrayList<Flight> flightMap = new ArrayList<Flight>();
    
    public Flight(String flightID, Airline airline, int totalSeats, ArrayList<Passenger> passengers) {
        this.flightID = flightID;
        this.airline = airline;
        this.totalSeats = totalSeats;
        this.passengerMap = passengers;
    }
    
    public int getFreeSeats() {
    	return (totalSeats - passengerMap.size());
    }
    
    public Airline getAirline() {
		return airline;
	}

	public ArrayList<Passenger> getPassengers() {
        return passengerMap;
    }

    public String getID() {
        return flightID;
    }

    public int getSeats() {
        return totalSeats;
    }

	public void setSeats(int freeSeats) {
		this.freeSeats = freeSeats;
	}
	
	public enum Airline {
		VIRGIN(),
		BRITISH_AIRWAYS(),
		EASYJET();
	}
    
}
