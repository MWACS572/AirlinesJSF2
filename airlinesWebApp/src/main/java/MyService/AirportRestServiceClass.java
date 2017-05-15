package MyService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cs545.airline.dao.AirportDao;
import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;


@Path("airportServicesPath")
public class AirportRestServiceClass {

	private AirportService airportService = new AirportService(new AirportDao());
	private FlightService flightService = new FlightService(new FlightDao());
	
	public AirportRestServiceClass(){
		
	}
	
	//1. the url given in the path is used to set the airport properties for the new Airport object
	//2. The Http method used is POST to create a new Airport object
	//3. the response message is XML format
	@Path("/createAirport/airportcode/{code}/name/{name}/city/{city}/country/{country}")
	@POST
	@Produces({"application/xml","application/json"})
	public Airport createAirport(@PathParam("code") String code, @PathParam("name") String name, @PathParam("city") String city, @PathParam("country") String country){
		Airport airport = new Airport();
		airport.setAirportcode(code);
		airport.setName(name);
		airport.setCity(city);
		airport.setCountry(country);
		airportService.create(airport);
		return airport;
	}
	
	//1. the url given in the path finds all Airports
	//2. The Http method used is GET to get all the airports objects
	//3. the response message is XML format
	@Path("/findAll")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airport> doFindAll(){
		
		List<Airport> airport = airportService.findAll();
	
		return airport;
	}
	
	//1. the url given in the path has the airport's object properties, and they are used to delete the airport object
	//2. The Http method used is Delete to delete the flight objects
	//3. the response message is XML format
	@Path("/deleteAirport/id/{id}/airportcode/{code}/name/{name}/city/{city}/country/{country}")
	@DELETE
	@Produces({"application/xml","application/json"})
	public Airport deleteAirport(@PathParam("id") long id, @PathParam("code") String code, @PathParam("name") String name, @PathParam("city") String city, @PathParam("country") String country){
		Airport airport = new Airport();
		airport.setId(id);
		airport.setAirportcode(code);
		airport.setName(name);
		airport.setCity(city);
		airport.setCountry(country);
			for(Flight fl: flightService.findByAirport(airport)){
				flightService.delete(fl);
			}
			
			airportService.delete(airport);
		
		return airport;
	}
	
	
	//1. the url given in the path has the properties for Airport Object and it updates the airport object
	//2. The Http method used is PUT to update the airport object
	//3. the response message is XML format
	@Path("/updateAirport/id/{id}/airportcode/{code}/name/{name}/city/{city}/country/{country}")
	@PUT
	@Produces({"application/xml","application/json"})
	public Airport updateAirport(@PathParam("id") long id, @PathParam("code") String code, @PathParam("name") String name, @PathParam("city") String city, @PathParam("country") String country){
		Airport airport = new Airport();
		airport.setId(id);
		
		for(Airport air: airportService.findAll()){
			if(air.getId()==id){
				airport.setAirportcode(code);
				airport.setName(name);
				airport.setCity(city);
				airport.setCountry(country);
				break;
			}
		}
		airportService.update(airport);
		return airport;
	}
	
	//1. the url given in the path finds airport by airport name
	//2. The Http method used is GET to get all the flight objects
	//3. the response message is XML format
	@Path("/findAirportName/{name}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airport> findAirportByName(@PathParam("name") String name){
		List<Airport> airportList= new ArrayList();
		Airport airport = new Airport();
		for(Airport air: airportService.findAll()){
			if(air.getName().equals(name)){
				airport=air;
				airportList.add(airport);
			}
		}
		
		return airportList;
	}
	
	
	@Path("/findAirportByObject")
	@PUT
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public Airport findAirportByAirportObject(Airport airport){
		
		return airportService.find(airport);
	}
	
	@Path("/findAirportByArrivalFlight/{flightNo}")
	@GET
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public List<Airport> findAirportByArrivalFlight(@PathParam("flightNo") String flightNo){
		List<Airport> airportList = new ArrayList();
		
		for(Flight fl: flightService.findAll()){
			if(fl.getFlightnr().equals(flightNo)){
				airportList = airportService.findByArrival(fl);
				break;
			}
		}
		
		return airportList;
	}
	
	@Path("/findAirportByDepartureFlight/{flightNo}")
	@GET
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public List<Airport> findAirportByDepartureFlight(@PathParam("flightNo") String flightNo){
		List<Airport> airportList = new ArrayList();
		
		for(Flight fl: flightService.findAll()){
			if(fl.getFlightnr().equals(flightNo)){
				airportList = airportService.findByDeparture(fl);
				break;
			}
		}
		
		return airportList;
	}
	
	@Path("/findAirportByAirportcode/{code}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airport> findAirportByCode(@PathParam("code") String code){
		List<Airport> airportList= new ArrayList();
		Airport airport = new Airport();
		for(Airport air: airportService.findAll()){
			if(air.getAirportcode().equals(code)){
				airport=air;
				airportList.add(airport);
			}
		}
		
		return airportList;
	}
	
	@Path("/findAirportByAirportCity/{city}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airport> findAirportByCity(@PathParam("city") String city){
		List<Airport> airportList= new ArrayList();
		Airport airport = new Airport();
		for(Airport air: airportService.findAll()){
			if(air.getCity().equals(city)){
				airport=air;
				airportList.add(airport);
			}
		}
		
		return airportList;
	}
	
	@Path("/findAirportByAirportCountry/{country}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airport> findAirportByCountry(@PathParam("country") String country){
		List<Airport> airportList= new ArrayList();
		Airport airport = new Airport();
		for(Airport air: airportService.findAll()){
			if(air.getCountry().equals(country)){
				airport=air;
				airportList.add(airport);
			}
		}
		
		return airportList;
	}
}
