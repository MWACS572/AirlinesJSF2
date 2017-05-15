package Trial;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("personServiceName")
@RequestScoped
public class PersonService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Person> list=new ArrayList<Person>();
	
	public PersonService(){
		Person p = new Person();
		p.setName("Will");
		Person p2 = new Person();
		p2.setName("Rob");
		list.add(p);
		list.add(p2);
	}

	public ArrayList<Person> getList() {
		return list;
	}

	public void setList(ArrayList<Person> list) {
		this.list = list;
	}
	
	
}
