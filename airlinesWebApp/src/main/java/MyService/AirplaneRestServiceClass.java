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


import cs545.airline.dao.AirplaneDao;
import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;

import cs545.airline.service.AirplaneService;
import cs545.airline.service.FlightService;

@Path("/airplaneServicesPath")
public class AirplaneRestServiceClass  {
	private AirplaneService airplaneService = new AirplaneService(new AirplaneDao());
	private FlightService flightService = new FlightService(new FlightDao());
	
	public AirplaneRestServiceClass(){
		
	}
	
	//1. the url given in the path is used to set the airplane property for the new airline object
	//2. The Http method used is POST to create a new Airplane object
	//3. the response message is XML format
	@Path("/createAirplane/model/{model}/serialnr/{nr}/capacity/{ca}")
	@POST
	@Produces({"application/xml","application/json"})
	public Airplane createAiplane(@PathParam("model") String model, @PathParam("nr") String nr, @PathParam("ca") int ca){
		Airplane airplane = new Airplane();
		airplane.setModel(model);
		airplane.setSerialnr(nr);
		airplane.setCapacity(ca);
		airplaneService.create(airplane);
		return airplane;
	}
	
	//1. the url given in the path is used to find all the Airplane objects
	//2. The Http method used is GET to get all the Airplane objects
	//3. the response message is XML format
	@Path("/findAll")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airplane> doFindAll(){
		
		List<Airplane> airplane = airplaneService.findAll();
	
		return airplane;
	}
	
	//1. the url given in the path is used to delete the airplane object with the given property
	//2. The Http method used is DELETE to delete the Airplane object
	//3. the response message is XML format
	@Path("/deleteAirplane/id/{id}/serialnr/{nr}/model/{model}/capacity/{ca}")
	@DELETE
	@Produces({"application/xml","application/json"})
	public Airplane deleteAirplane(@PathParam("nr") String nr, @PathParam("id") long id, @PathParam("model") String model, @PathParam("ca") int ca){
		Airplane airplane = new Airplane();
		airplane.setId(id);
		airplane.setSerialnr(nr);
		airplane.setModel(model);
		airplane.setCapacity(ca);
		
		System.out.println(airplane.getModel()+" name id "+airplane.getId());
			for(Flight fl: flightService.findByAirplane(airplane)){
				flightService.delete(fl);
			}
			
			airplaneService.delete(airplane);
		
		return airplane;
	}
	
	//1. the url given in the path is used to update the airplane property for the airplane object
	//2. The Http method used is UPDATE to update the Airplane object
	//3. the response message is XML format
	@Path("/updateAirplane/id/{id}/serialnr/{nr}/model/{model}/capacity/{ca}")
	@PUT
	@Produces({"application/xml","application/json"})
	public Airplane updateAirplane(@PathParam("nr") String nr, @PathParam("id") long id, @PathParam("model") String model, @PathParam("ca") int ca){
		Airplane airplane = new Airplane();
		airplane.setId(id);
		//airline.setName(airlineName);
		for(Airplane air: airplaneService.findAll()){
			if(air.getId()==id){
				airplane.setSerialnr(nr);
				airplane.setModel(model);
				airplane.setCapacity(ca);
				break;
			}
		}
		airplaneService.update(airplane);
		return airplane;
	}
	
/*	@Path("/findAirplaneByObject")
	@PUT
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public Airplane findAirplaneByAirplaneObject(Airplane airplane){
		
		return airplaneService.find(airplane);
	}
	*/
	
	//1. the url given in the path is used to get the airplane object for the given property
	//2. The Http method used is GET to get the required Airplane object
	//3. the response message is XML format
	@Path("/findAirplane/model/{model}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Airplane> findAirplaneByModel(@PathParam("model") String model){
		List<Airplane> airplaneList = new ArrayList();
		Airplane airplane = new Airplane();
		for(Airplane air: airplaneService.findAll()){
			if(air.getModel().equals(model)){
				airplane=air;
				airplaneList.add(airplane);
			}
		}
		
		return airplaneList;
	}
	
	
	//1. the url given in the path is used to get the airplane object for the given property
	//2. The Http method used is GET to get the required Airplane object
	//3. the response message is XML format
	@Path("/findAirplaneByFlight/{flightNo}")
	@GET
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public List<Airplane> findAirplaneByFlight(@PathParam("flightNo") String flightNo){
		List<Airplane> airplaneList = new ArrayList();
		
		for(Flight fl: flightService.findAll()){
			if(fl.getFlightnr().equals(flightNo)){
				airplaneList = airplaneService.findByFlight(fl);
				break;
			}
		}
		
		return airplaneList;
	}
	
	//1. the url given in the path is used to get the airplane object for the given property
	//2. The Http method used is GET to get the required Airplane object
	//3. the response message is XML format
	@Path("/findAirplaneByserialnr/{serialnr}")
	@GET
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public Airplane findAirplaneBySerialnr(@PathParam("serialnr") String serialnr){
		Airplane airplane= new Airplane();
		
		airplane =  airplaneService.findBySrlnr(serialnr);
		
		return airplane;
	}
}
