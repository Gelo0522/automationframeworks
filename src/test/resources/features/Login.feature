Feature: Login Feature

	Background: 
	  Given User opens "chrome" and navigates to "https://qa-practice.netlify.app/" page
	
	Scenario Outline: Login Functionality
	  When I select "Ecommerce - Login, Add to Cart, Submit order, Logout" from the menu
      When I login as "<username>" with password "<username>"

	  Examples:
	    | username | password   |
	    | user1    | pass123    |
	    | user2    | secret456  |
	    | user3    | mypassword |
	