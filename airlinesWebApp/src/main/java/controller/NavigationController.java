package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;





@Named("navigationController")
@RequestScoped
public class NavigationController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	
	private String pageId;
	
	public NavigationController(){}
	
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
	
	public String showAllFlights(){
		return "displayFlightInfo";
	}
	public String showPage(){
		System.out.println("page Id"+ pageId);
		if(pageId==null){
			return "index";
		}
//		if (pageId.equals("byDate")){
//			return "byDateDisplay";
//		}
		if(pageId.equals("showAllFlights")){
			return "displayFlightInfo";
		}
		else if(pageId.equals("byAirline")){
			return "byAirlineDisplay";
		}
		else if(pageId.equals("byOrigin")){
			return "byOriginDisplay";
		}else if(pageId.equals("byDestination")){
			return "byDestinationDisplay";
		}
		else if(pageId.equals("createNewAirline")){
			return "createAirline";
		}
		else if(pageId.equals("listAirlines")){
			return "listAirline";
		}
		else if(pageId.equals("updateAirline")){
			return "updateAirline";
		}
		else if(pageId.equals("deleteAirline")){
			return "deleteAirline";
		}
		else{
			return "index";
		}
		
	}
	
}
