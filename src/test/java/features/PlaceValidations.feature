Feature: Validating Place API's

Scenario Outline: Verify if place is being successfully add using AddPlaceAPI

Given Add place Payload with "<name>" "<langauge>" "<address>"
When user calls "getPlaceAPI" with "GET" HTTP request
Then the API call is success with status code 200
And  "status" in response body is "OK"
#And "Scope" in response body is "APP"
And Verify place id created maps to "<name>" using "getPlaceAPI"

Examples: 
| name  | langauge | address  |
| house | Japanese | Dunwoody |
#| Sathish | Chinese | Miramar |
#| kumar | Tamil | Trichy |



  