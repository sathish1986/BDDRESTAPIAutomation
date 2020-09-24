Feature: Validating Place API's

Scenario: Verify if place is being successfully add using AddPlaceAPI

Given Add place Payload
When user calls "AddPlaceAPI" with POST HTTP request
Then the API call is success with status code 200
And  "status" in response body is "OK"
#And "Scope" in response body is "APP"


  