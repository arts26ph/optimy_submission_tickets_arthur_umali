Feature: Submission

  Scenario: Submit new application
    Given User is on Login page  
    When User logs in
    Then The Submit new application page will be displayed
 		When The Continue with the submission of the application is present
		Then User clicks the Submit a new application button and fill-out the forms
		When User clicks the Next screen button 
		Then User verifies all entered values are correct
		When User clicks the Validate and send button
		Then User is redirected to Thank you for submitting your project page
		
   