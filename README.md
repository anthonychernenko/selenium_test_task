# Selenium Test Task

## Project Description

This project was created to solve the test task described below:

    In this task, you will be required to create a simple Java application in order to accomplish several sub-tasks. 
    The sub-tasks will be described below.
    
    Pay attention, that the application should contain all the best practicies used in the automation you know. 
    Also it should look like the real world automation project.
    
    1. Write test to automate the buying process in the internet shop:
    
       - Navigate to this site: https://magento.softwaretestingboard.com/
       - Use the menu in order to navigate into the bags section
       - Add one of the bags to your cart
       - Proceed to checkout and capture the HTTP request that sends user details to server
       - Validate that the above request contains the expected data
       - Place the order
       - Collect the order ID and write it into a new file called test_data.txt
    
    
    
    
    2. Create a utility method that initializes Firefox driver using Selenium
    
       - Create a Firfox driver instance with rejected third-party cookies and use this user agent:
    
            Mozilla/5.0 (iPhone; CPU iPhone OS 16_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Mobile/15E148 Safari/604.1
    
       - Open the link https://www.whatismybrowser.com/detect/what-is-my-user-agent/ and verify that the user agent is correct
       - Open the firefox settings page where the cookies setting can be verified (no need to add the verification)
    
    
    
    3. Create a utility method that initializes Chrome driver using Selenium
    
       - Create Chrome driver instance with rejected third party cookies
       - Open the chrome settings page where this setting can be verified (no need to add the verification)

The project was created with the YAGNI and KISS principles in mind. Thus, the minimum required list of libraries and frameworks was chosen for the project, namely Selenium WebDriver, JUnit 5, Allure. The project was created using Java 8.

Certain aspects are described using comments directly in the project code.

## How To Run Tests

Tests can be run separately using the capabilities of an IDE (for example, IntelliJ IDEA).
Alternatively, you can run tests with Maven using the IDE's features or by running the following command in CMD (make sure Maven is added to the System Path) in the project directory:

`mvn clean test`

After running the tests using Maven, you can start the Allure reporter server using the IDE or by running the command in CMD:

`mvn allure:serve`