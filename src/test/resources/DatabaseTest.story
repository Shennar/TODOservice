Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Get all records from database

Given I create an in-memory database with data
When I open the home page
Then I see all posts

AfterStory Drop database


Scenario: Add new record to database

Given I create an in-memory database with data
When I send correct data for new post: <datum>, <whatTODO>, <doneStatus>
Then I get a record with id <id> and error code <error>


AfterStory Drop database

|id   |datum   |whatTODO   |doneStatus |error  |
|9    |22022019|From story |TO DO      ||


Scenario: Change status

Given I create an in-memory database with data
When I change status of record with id <id>
Then I get status of record with <statusCode> and <doneStatus>
AfterStory Drop database

|id   |statusCode   |doneStatus |
|1    |200          |Done       |


Scenario: Delete a record from database

Given I create an in-memory database with data
When I delete record with <id>
Then I get status code <statusCode>
And The record with id <id> not present
AfterStory Drop database

|id   |statusCode  |
|8    |200         |
