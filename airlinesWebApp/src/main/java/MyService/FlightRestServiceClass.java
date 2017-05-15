package MyService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cs545.airline.dao.AirlineDao;
import cs545.airline.dao.AirplaneDao;
import cs545.airline.dao.AirportDao;
import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Path("/flightServicesPath")
public class FlightRestServiceClass {
	
	FlightService flightService =new FlightService(new FlightDao());
	AirlineService airlineService = new AirlineService(new AirlineDao());
	AirplaneService airplaneService = new AirplaneService(new AirplaneDao());
	AirportService airportService = new AirportService(new AirportDao());
	
	public FlightRestServiceClass(){}
	
	//1. the url given in the path finds all flight
	//2. The Http method used is GET to get all the flight objects
	//3. the response message is XML format
	@Path("/findAll")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Flight> findAllFlights(){
		
		return flightService.findAll();
	}
	
	

	/*@Path("/createFlight/flightnr/{nr}/depDate/{dDate}/depTime/"
			+ "{dTime}/arDate/{arDate}/arTime/{arTime}/airlineName/{airName}/airportOriginCode/{oCode}"
			+ "/airportDestinationCode/{dCode}/airplaneSerialnr/{serialnr}")
	@POST
	@Produces({"application/xml","application/json"})
	public Flight createFlight(@PathParam("nr") String nr, @PathParam("dDate") 
				String dDate, @PathParam("dTime") String dTime, @PathParam("arDate") String arDate, 
				@PathParam("arTime") String arTime, @PathParam("airName") String airName, 
				@PathParam("oCode") String oCode, @PathParam("dCode") String dCode, 
				@PathParam("serialnr") String serialnr ){
		
		Flight flight = new Flight();
		flight.setFlightnr(nr);
		flight.setDepartureDate(dDate);
		flight.setDepartureTime(dTime);
		flight.setArrivalDate(arDate);
		flight.setArrivalTime(arTime);
		flight.setAirline(airlineService.findByName(airName));
		flight.setOrigin(airportService.findByCode(oCode));
		flight.setDestination(airportService.findByCode(dCode));
		flight.setAirplane(airplaneService.findBySrlnr(serialnr));
		flightService.create(flight);
		return flight;
	}*/
	
	//1. the url given in the path is information to delete the flight object
	//2. The Http method used is DELETE to delete the flight object
	//3. the response message is XML format
	@Path("/deleteFlight/flightnr/{nr}")
	@DELETE
	@Produces({"application/xml", "application/json"})
	public Flight deleteFlight(@PathParam("nr") String nr){
		Flight flight = new Flight();
		
		for(Flight fl : flightService.findAll()){
			if(nr.equals(fl.getFlightnr())){
				flight=fl;
				break;
			}
		}
		flightService.delete(flight);
		return flight;
	}
	
	
	//1. the url given in the path is information to update the flight object
	//2. The Http method used is PUT to update the flight object
	//3. the response message is XML format
	@Path("/updateFlight/id/{id}/flightnr/{nr}/depDate/{dDate}/depTime/"
			+ "{dTime}/arDate/{arDate}/arTime/{arTime}/airlineName/{airName}/airportOriginCode/{oCode}"
			+ "/airportDestinationCode/{dCode}/airplaneSerialnr/{serialnr}")
	@PUT
	@Produces({"application/xml","application/json"})
	public Flight createUpdate(@PathParam("id") long id, @PathParam("nr") String nr, @PathParam("dDate") 
				String dDate, @PathParam("dTime") String dTime, @PathParam("arDate") String arDate, 
				@PathParam("arTime") String arTime, @PathParam("airName") String airName, 
				@PathParam("oCode") String oCode, @PathParam("dCode") String dCode, 
				@PathParam("serialnr") String serialnr ){
		
		Flight flight = new Flight();
		flight.setId(id);
		flight.setFlightnr(nr);
		flight.setDepartureDate(dDate);
		flight.setDepartureTime(dTime);
		flight.setArrivalDate(arDate);
		flight.setArrivalTime(arTime);
		flight.setAirline(airlineService.findByName(airName));
		flight.setOrigin(airportService.findByCode(oCode));
		flight.setDestination(airportService.findByCode(dCode));
		flight.setAirplane(airplaneService.findBySrlnr(serialnr));
		flightService.update(flight);
		return flight;
	}
	
	//1. the url given in the path finds flight by flight number
	//2. The Http method used is GET to get the flight by the flight number
	//3. the response message is XML format
	@Path("/findFlightByFlightNo/{nr}")
	@GET
	@Produces({"application/xml", "application/json"})
	public Flight findFlightByFlightnr(@PathParam("nr") String nr){
		Flight flight = new Flight();
		for(Flight fl : flightService.findAll()){
			if(nr.equals(fl.getFlightnr())){
				flight=fl;
				break;
			}
		}
		flight = flightService.find(flight);
		return flight;
	}
	
	//1. the url given in the path finds flight by Airline name
	//2. The Http method used is GET to get the flight by the airline name
	//3. the response message is XML format
	@Path("/findFlightByAirline/airlineName/{name}")
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Flight> findFlightByAirline(@PathParam("name") String name){
		List<Flight> flightList = new ArrayList<Flight>();
		Airline airline=new Airline();
		for(Flight fl : flightService.findAll()){
			if(name.equals(fl.getAirline().getName())){
				airline=fl.getAirline();
			}
		}
		flightList = flightService.findByAirline(airline);
		return flightList;
	}
	
	//1. the url given in the path finds flight by airplane model
	//2. The Http method used is GET to get the flight by airplane model
	//3. the response message is XML format
	@Path("/findFlightByAirplane/airplaneModel/{model}")
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Flight> findFlightByAirplane(@PathParam("model") String model){
		List<Flight> flightList = new ArrayList<Flight>();
		Airplane airplane = new Airplane();
		for(Flight fl : flightService.findAll()){
			if(model.equals(fl.getAirplane().getModel())){
				airplane = fl.getAirplane();
				break;
			}
		}
		flightList = flightService.findByAirplane(airplane);
		return flightList;
	}
	
	
	//1. the url given in the path finds flight by Oirigin Airport using airport code
	//2. The Http method used is GET to get the flight by the airport code
	//3. the response message is XML format
	@Path("/findFlightByOrigin/airportCode/{code}")
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Flight> findFlightByOrigin(@PathParam("code") String code){
		List<Flight> flightList = new ArrayList<Flight>();
		Airport origin = new Airport();
		for(Flight fl : flightService.findAll()){
			if(code.equals(fl.getOrigin().getAirportcode())){
				origin = fl.getOrigin();
				break;
			}
		}
		flightList = flightService.findByOrigin(origin);
		return flightList;
	}
	
	//1. the url given in the path finds flight by destination Airport code
	//2. The Http method used is GET to get the flight by the airplane code
	//3. the response message is XML format
	@Path("/findFlightByDestination/airportCode/{code}")
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Flight> findFlightByDestination(@PathParam("code") String code){
		List<Flight> flightList = new ArrayList<Flight>();
		Airport destination = new Airport();
		for(Flight fl : flightService.findAll()){
			if(code.equals(fl.getDestination().getAirportcode())){
				destination = fl.getDestination();
				break;
			}
		}
		flightList = flightService.findByDestination(destination);
		return flightList;
	}
	
	//1. the url given in the path finds flight by arrival airplane using the airplanes serial number
	//2. The Http method used is GET to get the flight by the airplane serial number
	//3. the response message is XML format 
	@Path("/findFlightByArrivalAirplane/airplaneSerialnr/{nr}")
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Flight> findFlightByArrivalAirplane(@PathParam("nr") String nr){
		List<Flight> flightList = new ArrayList<Flight>();
		Airplane airplane = new Airplane();
		for(Flight fl : flightService.findAll()){
			if(nr.equals(fl.getAirplane().getSerialnr())){
				airplane=fl.getAirplane();
				break;
			}
		}
		flightList = flightService.findByArrival(airplane);
		return flightList;
	}
	
	
	//This method has a problem converting the Date
	@Path("/findFlightByArrivalDate/arrivalDate/{date}")
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Flight> findFlightByArrivalDate(@PathParam("date") String date) throws ParseException{
		List<Flight> flightList = new ArrayList<Flight>();
		Flight flight = new Flight();
		flight.setArrivalDate(date);
		Date da = new Date();
		for(Flight fl : flightService.findAll()){
			if(date.equals(fl.getArrivalDate())){
				flight = fl;
				break;
			}
		}
		da = DateFormat.getDateInstance(DateFormat.SHORT,
				Locale.US).parse(date);
		flightList = flightService.findByArrival(da);
		return flightList;
	}
	
	
}
