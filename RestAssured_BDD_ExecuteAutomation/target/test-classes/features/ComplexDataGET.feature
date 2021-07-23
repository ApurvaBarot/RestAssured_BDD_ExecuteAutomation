Feature: Complex data GET operation
	Verify Complex data

Scenario: Verify GET operation for complex data
	Given I perform authentication operation for "/auth/login" with body
		|	email							|	password	|
		|	apurva@gmail.com	|	apurva123	|
	And I perform GET operation with path paramater for address "/location"
		|	id	|
		|	1		|
	Then I should see the street name as "S V Road" for the "primary" address