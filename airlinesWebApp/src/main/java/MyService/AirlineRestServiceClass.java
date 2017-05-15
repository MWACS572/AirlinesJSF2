package MyService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cs545.airline.dao.AirlineDao;
import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;




@Path("/airlineServicesPath")
public class AirlineRestServiceClass {
	
	
	private AirlineService airlineService = new AirlineService(new AirlineDao());
	private FlightService flightService = new FlightService(new FlightDao());
	
	public AirlineRestServiceClass(){
		
	}
	//1. the url given in the path is used to set the airline property for the new airline object
	//2. The Http method used is POST to create a new Airline object
	//3. the response message is XML format
	@Path("/createAirline/{airlineName}")
	@POST
	@Produces({"application/xml","application/json"})
	public Airline createAirline(@PathParam("airlineName") String airlineName){
		Airline airline = new Airline();
		airline.setName(airlineName);
		airlineService.create(airline);
		return airline;
	}
	
	//1. the url given in the path is used to find all the airlines
	//2. The Http method used is GET to get all the airline objects
	//3. the response message is XML format
	@Path("/findAll")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airline> doFindAll(){
		
		List<Airline> airlines = airlineService.findAll();
	
		return airlines;
	}
	
	//1. the url given in the path is used to delete the airline object with the given property
	//2. The Http method used is DELETE to delete the Airline object
	//3. the response message is XML format
	@Path("/deleteAirline/{airlineName}/id/{id}")
	@DELETE
	@Produces({"application/xml","application/json"})
	public Airline deleteAirline(@PathParam("airlineName") String airlineName, @PathParam("id") long id){
		Airline airline = new Airline();
		airline.setName(airlineName);
		airline.setId(id);
		System.out.println(airline.getName()+" name id "+airline.getId()+" airlinename "+airlineName);
			for(Flight fl: flightService.findByAirline(airline)){
				flightService.delete(fl);
			}
			
			airlineService.delete(airline);
		
		return airline;
	}
	
	//1. the url given in the path is used to update the airline property for the airline object
	//2. The Http method used is UPDATE to update the Airline object
	//3. the response message is XML format
	@Path("/updateAirline/id/{id}/airlineName/{airlineName}")
	@PUT
	@Produces({"application/xml","application/json"})
	public Airline updateAirline(@PathParam("airlineName") String airlineName, @PathParam("id") long id){
		Airline airline = new Airline();
		airline.setId(id);
		//airline.setName(airlineName);
		for(Airline air: airlineService.findAll()){
			if(air.getId()==id){
				airline.setName(airlineName);
				break;
			}
		}
		airlineService.update(airline);
		return airline;
	}
	
	//1. the url given in the path is used to get the airline object by the given property
	//2. The Http method used is GET to get the required Airline object
	//3. the response message is XML format
	@Path("/findAirline/{airlineName}")
	@GET
	@Produces({"application/xml","application/json"})
	public Airline findAirlineByName(@PathParam("airlineName") String airlineName){
		Airline airline=new Airline();
		for(Airline air: airlineService.findAll()){
			if(air.getName().equals(airlineName)){
				airline=air;
				break;
			}
		}
		
		return airline;
	}
	
	/*@Path("/findAirlineByObject")
	@PUT
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public Airline findAirlineByAirlineObject(Airline airline){
		
		return airlineService.find(airline);
	}*/
	
	//1. the url given in the path is used to get the airline object by the given property
	//2. The Http method used is GET to get the required Airline object
	//3. the response message is XML format
	@Path("/findAirlineByFlight/{flightNo}")
	@GET
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public List<Airline> findAirlineByFlight(@PathParam("flightNo") String flightNo){
		List<Airline> airlineList = new ArrayList();
		
		for(Flight fl: flightService.findAll()){
			if(fl.getFlightnr().equals(flightNo)){
				airlineList = airlineService.findByFlight(fl);
				break;
			}
		}
		
		return airlineList;
	}
}
