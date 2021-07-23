Feature: Verify different GET operationsing Rest-Assured

# Scenario: Verify one author of the post
#	Given I perform GET operation for the "/posts"
#	Then I should see the author name as "Apurva"

# Scenario: Verify collection of authors in the post
#	Given I perform GET operation for the "/posts"
#	Then I should see the author names
	
# Scenario: Verify parameters of GET
#	Given I perform GET operation for the "/posts"
#	Then I should verify parameters
	
Scenario: Verify GET operation with bearer authentication token
	Given I perform authentication operation for "/auth/login" with body
		|	email						|	password	|
		|ruchi@gmail.com	|	ruchi123	|
	Given I perform GET operation for the "/posts/1"
	Then I should see the author name as "Apurva"	
	
#Scenario: Verify GET operation with bearer authentication token 
#	Given I perform authentication operation for "/auth/login" with body
#		|	email						|	password	|
#		|ruchi@gmail.com	|	ruchi123	|
#	Given I perform GET operation for the "/posts/1"
#	Then I should see the author name as "Apurva" with JSON validation	