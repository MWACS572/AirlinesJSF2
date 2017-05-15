package cs545.airline.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

@Named("flightService")
@RequestScoped
public class FlightService {
	
	private static final ArrayList<String> one1=new ArrayList<String>(Arrays.asList(new String("onet")));
	private FlightDao flightDao;
	
	public List<Flight> stringOne(){
		System.out.println("sdfadsfasdfasdfgilhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		Flight fl= new Flight();
		fl.setId(12);
		ArrayList<Flight> st= new ArrayList();
		st.add(fl);
		return st;
	}
	// These services should be evaluated to reconsider which methods should be public 
	
	public FlightService(FlightDao flightDao){
		this.flightDao = flightDao;
	}
	
	public void create(Flight flight) {
		flightDao.create(flight);
	}

	public void delete(Flight flight) {
		flightDao.delete(flight);
	}

	public Flight update(Flight flight) {
		return flightDao.update(flight);
	}
		
	public Flight find(Flight flight) {
		return flightDao.findOne(flight.getId());
	}

	public List<Flight> findByNumber(String flightnr) {
		return flightDao.findByFlightnr(flightnr);
	}

	public List<Flight> findByAirline(Airline airline) {
		return flightDao.findByAirline(airline.getId());
	}

	public List<Flight> findByOrigin(Airport airport) {
		return flightDao.findByOrigin(airport.getId());
	}

	public List<Flight> findByDestination(Airport airport) {
		return flightDao.findByDestination(airport.getId());
	}

	public List<Flight> findByArrival(Airplane airplane) {
		return flightDao.findByAirplane(airplane.getId());
	}

	public List<Flight> findByArrival(Date datetime) {
		return flightDao.findByArrival(datetime, datetime);
	}

	public List<Flight> findByArrivalBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByArrivalBetween(datetimeFrom, datetimeFrom, datetimeTo, datetimeTo);
	}

	public List<Flight> findByDeparture(Date datetime) {
		return flightDao.findByDeparture(datetime, datetime);
	}

	public List<Flight> findByDepartureBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByDepartureBetween(datetimeFrom, datetimeFrom, datetimeTo, datetimeTo);
	}

	public List<Flight> findAll() {
		System.out.println("flight string");
		return flightDao.findAll();
	}

}
