Feature: Verify different GET operationsing Rest-Assured

Scenario: Verify POST operation for profile
	Given I perform POST operation for "/posts/{profileNo}/profile" with body
	|	name		|	profileNo |
	|	Poojary	|	3					|
	Then I should see the body has name as "Poojary"