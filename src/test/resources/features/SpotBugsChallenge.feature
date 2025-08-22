Feature: SpotBugsChallenge
	
	Background:
    Given User opens "chrome" and navigates to "https://qa-practice.netlify.app/" page
    @SmokeTest
    Scenario Outline: SpotBugs Challenge <Scenario Name>
        When I select "Spot the BUGS CHALLENGE" from the menu	
        And I fill in the registration form with the following details:
            | FirstName    | <FirstName>   |
            | LastName     | <LastName>    |
            | PhoneNumber  | <Phonenumber> |
            | Country       | <Country>      |
            | EmailAddress | <Emailaddress>|
            | Password      | <Password>     |
            | IsAgreeOnTermsAndConditions | <IsAgreeOnTermsAndConditions> |
        And I click on "Register" button
        Then I should see the message "<Message>"
        	| FirstName    | <FirstName>   |
            | LastName     | <LastName>    |
            | PhoneNumber  | <Phonenumber> |
            | Country       | <Country>      |
            | EmailAddress | <Emailaddress>|
        	
       	
    Examples:
	|ScenarioName | FirstName | LastName | Phonenumber | Country | Emailaddress | Password | IsAgreeOnTermsAndConditions| Message|
	|Success Registration | Gelo | Lavapiez | 09154252542 | Philippines | gelo@abc.com | gelo1234 | false | Successfully registered the following information |
	|No FirstName |  | Lavapiez | 09154252542 | Philippines | gelo@abc.com | gelo1234 | false | Successfully registered the following information |
	|No LastName | Gelo |  | 09154252542 | Philippines | gelo@abc.com |  | false | LastName is Required |
	|No Country | Gelo | Lavapiez | 09154252542 | | gelo@abc.com | gelo1234 | false | Successfully registered the following information |
	|No EmailAddress | Gelo | Lavapiez | 09154252542 | Philippines |  | gelo1234 | false | Email Address is Required |
	|No Password | Gelo | Lavapiez | 09154252542 | Philippines | gelo@abc.com |  | false | The password should contain between [6,20] characters! |
	|Less than 10 digit Phone Number| Gelo | Lavapiez | 123 | Philippines | gelo@abc.com | gelo1234 | false | The phone number should contain at least 10 characters! |
	|Alphanumeric Phone Number| Gelo | Lavapiez | abc1^&*(*2313 | Philippines | gelo@abc.com | gelo1234 | false | Successfully registered the following information |
	|Lessthan 6 Password| Gelo | Lavapiez | 09154252542 | Philippines | gelo@abc.com | gelo | false | The password should contain between [6,20] characters! |
	|Lessthan 6 Password| Gelo | Lavapiez | 09154252542 | Philippines | gelo@abc.com | GreateThan20Password:) | false | The password should contain between [6,20] characters! |
	
	
        