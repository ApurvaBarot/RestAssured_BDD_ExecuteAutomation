Feature: Verify Delete POSTS
	Test the delete operation

Scenario Outline: Verify DELETE operation after POST
	Given I ensure to perform  POST operation for "/posts" with body as 
		|	id	|	title							|	author				|
		|	10		|	Automation			|	Kriyansh Barot|		
	And I perform GET operation with path parameter for "/posts/{postId}"
		|	postId	|
		|	10			|
	And I perform DELETE operation for "/posts/{postId}/"
		|	postId	|
		|	10			|		
	And I perform GET operation with path parameter for "/posts/{postId}"
		|	postId	|
		|	10			|
	Then I "should not" see the body with title as "Rest Assured API"
		
