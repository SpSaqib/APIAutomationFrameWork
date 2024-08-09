package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.Pojo1JSON;

public class TestDataBuild {

	public Pojo1JSON addPlacePayload(String name, String language, String address) {
		Pojo1JSON p = new Pojo1JSON();
		// Serialization
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);

		// Creating a List
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		// Add created list set method
		p.setTypes(myList);

		// set data for set location method it expects return type as Location
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);

		return p;
	}
	

	public String deletePlacePayload(String place_id) {
		return "{\r\n    \"place_id\":\"" + place_id + "\"\r\n}";
	}

}
