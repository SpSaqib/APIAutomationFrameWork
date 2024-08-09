Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddplaceAPI
		Given Add Place Payload "<name>" "<language>" "<address>"
		When user calls "AddPlaceAPI" with "POST" http request
		Then the API call is success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And Verify place_id is created map to "<name>" using "getPlaceAPI"
		
Examples:
     |name     | language     | address            |
     |AAhouse  | English      | World Cross center |
#     |BBhouse  | Spanish      | Sea Cross Center   |
     
@DeletePlace
Scenario: Verify if Delete Place functionality is working

      Given DeletePlace Payload
      When user calls "deletePLaceAPI" with "POST" http request
      Then the API call is success with status code 200
      And "status" in response body is "OK" 