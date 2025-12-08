UMBC EV Charging Map – Terminal Simulation
CIS-147 Object-Oriented Programming Group Project

Project Overview:

UMBC provides 19 electric vehicle charging stations across three parking areas, but there is currently no system that shows real-time availability.
This project simulates such a system in a terminal environment.
Users can log in, view parking lots, check the status of all charging stations, refresh real-time availability, and select a station to "use" in the simulation.

Team Members and Roles:

Iliyan Slavov – Project Manager / UI Developer
- Responsible for menu design, user interaction flow, login logic, and coordinating how classes work together.
Jonathan Benton – Backend Developer
- Responsible for core Java logic, class structure, repository implementation, randomization engine, and the "I’m driving now" workflow.
Arshan Dhaliwal – Database Designer
- Designed the conceptual structure of parking lots and charging stations, and helped define the repository pattern for possible future expansion to databases.
Alemayehu (Alex) Tewodros – Tester and Documentation Writer
- Performed testing of program functionality, validated menu flows and logic, and wrote documentation.
Quenten Conley – UX and UI Designer
= Worked on improving user flow, menu clarity, and overall terminal user experience.

Key Features:
1. Randomized charger availability that updates each time the user selects the live-refresh option.
The probability model is: 60 percent Available, 30 percent In Use, 10 percent Out of Service.
The system always keeps at least one available charger.

2. Realistic charger codes based on parking location.
Walker Garage uses G1 through G10.
Lot 9 uses L1 through L7.
Stadium Lot uses D1 and D2.

3.Simulated permit lookup.
The user enters a name and campus ID, and the system randomly assigns a parking permit type such as Daily, Annual, or Hourly.

4.Station selection.
The user can choose a station code.
The system ensures it is still available, marks it as In Use, and displays a summary.

5.Object-oriented architecture using interfaces and services.
A repository interface stores lot and station data.
A service interface provides counting and filtering logic.
An enum defines all charging statuses.
A controller class manages user actions and menu navigation.

How to Run the Program:
Open the project in IntelliJ.
Run the main file (IS147_GD_Main.java).
Follow the instructions shown in the terminal.

Additional Included Materials:
UML diagram file
Javadoc documentation generated from the source code
GitHub Project Board showing tasks and responsibilities
README file (this document)
