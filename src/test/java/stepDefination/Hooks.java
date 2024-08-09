package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		// place_id finding code
		
		
		StepDefination m=new StepDefination();
		if(StepDefination.place_id==null)
		{
		m.add_Place_Payload("Rahul", "Hindi", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		// This will give you the place_id
		m.verify_place_id_is_created_map_to_using("Rahul", "getPlaceAPI");
		}
	}

}
