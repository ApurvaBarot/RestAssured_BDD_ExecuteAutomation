Feature: Verify Delete POSTS
	Test the delete operation

Scenario Outline: Verify DELETE operation after POST
	Given I ensure to perform  POST operation for "/posts" with body as 
		|	id		|	title						|	author				|
		|	5			|	Automation			|	Kriyansh Barot|		
	And I perform PUT operation for "/posts/{postId}/"
		|	id			|	title								|	author				|
		|	5				|	Automation Testing	|	Kriyansh Barot|		
	And I perform GET operation with path parameter for "/posts/{postId}"
		|	postId	|
		|	5				|
	Then I "should" see the body with title as "Automation Testing"
		
