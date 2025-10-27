Team Members & Roles

• Iliyan Slavov – Project Manager / Frontend Developer (UI for charger map and status display)
• Jonathan Benton – Backend Developer (APIs for charger data, status updates, and user requests)
• Arshan Dhaliwal – Database Designer (MySQL schema for chargers, users, and status logs)
• Alemayehu (Alex) Tewodros – Tester / Documentation Writer (QA, bug tracking, documentation)
• Quenten Conley – UX/UI Designer (wireframes, user flows, interface enhancements)

Project Problem Statement:
With the growing number of electric vehicles (EVs) on the UMBC campus, students, faculty, and staff increasingly struggle to find available charging stations. At present, there is no centralized system that allows users to conveniently view the locations of EV chargers AND check their real-time availability. This lack of information results in wasted time, unnecessary driving around campus, and inefficient use of charging resources. To solve this problem, this project will develop a software application that features an interactive map displaying all EV charging stations on campus along with their current status that shows whether they’re occupied or available. 

The system aims to enhance campus sustainability initiatives, minimize driver frustration, and encourage the continued adoption of electric vehicles by improving access to charging infrastructure.


Password Validator Pseudo-code:

The program will take in an input from the user that is their password. Next it will check to see if it meets the requirements through the following steps:

By default, the program sets all checks to return as true
Checks if the password is empty (null) -> Returns false statement
Checks if the password’s length is under 8 characters -> Returns false statement
Checks if the password contains a digit using a pre-made Java class
Checks if the password contains a special character from the list provided using a for loop that checks every single character in the string

If all checks are returned as true, then it will say the password is strong. If any of the checks are returned false, then it will output that the password does not meet the required conditions


Task 3: Docker and Java
For this task, we containerized a Java class and JDK using Docker. 
