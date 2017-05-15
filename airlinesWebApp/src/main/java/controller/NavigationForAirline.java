package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named("navigationForAirline")
@RequestScoped
public class NavigationForAirline implements Serializable{
	private static final long serialVersionUID = 11L;
	private static int generatedVal = 30;
	private List<Airline> airlineList;
	private Airline airlineEdit;
	private Airline airlineDelete;
	
	

	

	@Inject
	AirlineService airlineService;
	
	public NavigationForAirline(){
		airlineList = new ArrayList();
		setAirlineEdit(new Airline());
		airlineDelete=new Airline();
	}
	
	public String createAirline(Airline airline){
		
		airlineService.create(airline);
		
		System.out.println(airline.getName()+ " "+airline.getId());
		
		return "index";
	}
	public String editAirline(Airline airline){
		System.out.println(airline.getName()+ " "+airline.getId());
		airlineService.update(airline);
		return "updateAirline";
	}
	
	public String deleteAirline(Airline airline){
		System.out.println(airline.getName()+ " "+airline.getId());
		for(Airline air: airlineService.findAll()){
			if(air.getName().equals(airline.getName())){
				airlineService.delete(air);
				break;
			}
		}
		
		return "deleteAirline";
	}
	
	public List<Airline> getAirlineList() {
		return airlineService.findAll();
	}

	public void setAirlineList(List<Airline> airlineList) {
		this.airlineList = airlineList;
	}

	public Airline getAirlineEdit() {
		return airlineEdit;
	}

	public void setAirlineEdit(Airline airlineEdit) {
		this.airlineEdit = airlineEdit;
	}
	public Airline getAirlineDelete() {
		return airlineDelete;
	}

	public void setAirlineDelete(Airline airlineDelete) {
		this.airlineDelete = airlineDelete;
	}
	
}
