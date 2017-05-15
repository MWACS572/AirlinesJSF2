package MyService;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;



@Path("/rest")
public class RestService {
	
	
	private AirlineService airlineService = new AirlineService(new AirlineDao());
	
	public RestService(){
		
	}
	
	

	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getService(){
		return "asdf";
	}



		
}
