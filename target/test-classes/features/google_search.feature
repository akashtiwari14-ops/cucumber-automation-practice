Feature: Google Search Feature 

Scenario: Search for Tools QA on Google 
	Given User opens Google homepage 
	When User searches for "Amazon" 
	Then User should see page title containing "amazon"