package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named("airlineNavigation")
@RequestScoped
public class AirlineNavigation implements Serializable{
	private static final long serialVersionUID = 4L;
	
	@Inject
	AirlineService airlineService;
	@Inject
	FlightService flightService;
	@Inject
	AirportService airportService;
	
	private ArrayList<Airline> airlineList;
	private List<Flight> flightList;
	private ArrayList<Airport> airportList;
	
	


	public AirlineNavigation(){
		flightList = new ArrayList<Flight>();
		airlineList = new ArrayList<Airline>();
		airportList = new ArrayList<Airport>();
	}
	
	
	public String displayFlightByAirline(Airline airline){
		
		for(Airline air: airlineService.findAll()){
			if(airline.getName().equals(air.getName())){
				airlineList.add(air);
			}
			
		}
		System.out.println("print airline id: "+ airlineList.get(0).getId()+" airlineList name: "+ airlineList.get(0).getName());
		flightList=new ArrayList();
		flightList = (flightService.findByAirline(airlineList.get(0)));
		return "displayFlightByAirlineName";
	}
	
	public String displayFlightByOrigin(Airport airport){
		
		for(Airport airpo: airportService.findAll()){
			if(airport.getName().equals(airpo.getName())){
				airportList.add(airpo);
			}
		}
		flightList=new ArrayList();
		flightList=(flightService.findByOrigin(airportList.get(0)));
		return "dispalyFlightByOriginAirportName";
	}
	
	public String displayFlightByDestination(Airport airport){
		
		for(Airport airpo: airportService.findAll()){
			if(airport.getName().equals(airpo.getName())){
				airportList.add(airpo);
			}
		}
		flightList=new ArrayList();
		flightList=(flightService.findByDestination(airportList.get(0)));
		return "displayFlightByDestinationAirportName";
	}
	
	public ArrayList<Airline> getAirlineList() {
		return airlineList;
	}
	public void setAirlineList(ArrayList<Airline> airlineList) {
		this.airlineList = airlineList;
	}
	public List<Flight> getFlightList() {
		return flightList;
	}
	public void setFlightList(List<Flight> flightList) {
		this.flightList = flightList;
	}
	
	public ArrayList<Airport> getAirportList() {
		return airportList;
	}


	public void setAirportList(ArrayList<Airport> airportList) {
		this.airportList = airportList;
	}
	
}
