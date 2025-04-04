# Appliance-Project
Requirements Document for a Power Usage Simulation System

Our project specifically has to do with managing smart appliances. Read this article about the smart grid. https://sympower.net/articles/smart-grid-explained


An appliance consists of a type (string), the "on" wattage used (integer), the "off" wattage used (integer), the probability (floating point, i.e..01=1%) that the appliance is "on" at any time, the location (represented by an 8 digit numeric account number), and a unique, system-generated appliance ID. Multiple appliances are at each location, and duplicate appliances can be at the same location (each appliance with its own unique appliance ID). Appliances can either be regular or smart. Smart appliances (if "on") can sense the load on the power grid and reduce their average wattage used by a given percentage (floating point, i.e. .33=33%).


A Power Usage Simulation System needs to be able to manage a collection of appliances (both regular and smart). A user should be allowed to find, add and delete an appliance, or use an input file to add appliances.


A user also should be able to view subsets of appliances (all appliances for a location, or all appliances of a particular type across all locations). The user should be able to view a summary report of the total number of locations, and total number of each appliance of each type.


Finally, using the Power Usage Simulation System, the user will be able to run a simulation loop (see below information on using iteration for a Discrete Event Simulator) for a user specified time length and user timing interval. This simulation loop will determine in each time interval the total wattage on the power grid by summing the wattage used by each appliance (if an appliance is "on" or not is determined by its probability). If the total wattage exceeds the user defined warning level for the grid, the simulation must determine which "on" smart appliances to cycle off for that interval, while minimizing the noticeable effect on each location. In extreme cases, turning off the power to locations for an interval may be necessary (a brown out). When the next interval starts, each appliance's "on"/"off" status is again determined by its probability of being on. The simulation system must publish a report of which appliances/locations were affected during each interval.


Power Usage Simulation System input/output details:

· Prompt the user for a wattage warning level for the grid.

· Allow the user to take (or change) the default simulation time length (24 hours), and the default simulation timing interval (5 minutes).

· Run the simulation and apply your algorithm to manage the appliances to keep the usage below the wattage warning level.

· When the simulation is complete, output the following:

o A summary report to the screen the total number of locations effected in each interval for the entire simulation run, and the max effected location.

o A detailed report to a file containing the appliances/locations that were affected during each interval.


Using iteration for a Discrete Event Simulator

We are going to do a basic Discrete Event Simulator to introduce the concept that will be used in the project. A Discrete Event Simulator is using a computer program (basically a loop counting some time intervals, minutes for example) to simulate time moving forward and random arrival and handling of some sort of event. Every iteration of the loop is equal to one time interval, and we will randomly generate the time the next event will occur. When we reach that time, we will handle the event (here just count it and output it) and generate the time for the next event. A random exponential integer will tell us how many minutes until the next event, the interval between events time (this time can be zero!).


Sample run of EventSimulator.java

How many minutes long is the simulation? 100

Minute:0 Event#1

Minute:2 Event#2

Minute:6 Event#3

Minute:6 Event#4

Minute:17 Event#5

Minute:17 Event#6

Minute:22 Event#7

Minute:36 Event#8

Minute:42 Event#9

Minute:42 Event#10

Minute:42 Event#11

Minute:43 Event#12

Minute:45 Event#13

Minute:57 Event#14

Minute:58 Event#15

Minute:58 Event#16

Minute:73 Event#17

Minute:96 Event#18

Number of events = 18


Input File Generation - See the program in ApplianceGenerator.zip to generate random input files. It reads from an input file of standard appliance types and generates comma delimited input files for your Power Usage Simulation System. There are three constants you can vary:

· LOCATION_COUNT=100 // how many locations in the input file

· APPLIANCES_PER_LOCATION_LOW=15 // the lower limit for the number of appliances at a location

· APPLIANCES_PER_LOCATION_HIGH=20 // the upper limit for the number of appliances at a location


Each user-defined class needs its own individual test program. Each user-defined class needs its own exception class and should throw exceptions for any invalid action. Other classes need to catch these exceptions where appropriate and write out details to log.txt


Design Submission (20 points) - Create the following for the above requirements. No code is necessary. See UML_Design_Reference.pdf:

1. (10 points) Identify the classes required and answer the following questions for each class

a. What are the instance attributes, their data types and valid ranges?

b. What are instance methods needed, their arguments and return types?

c. What are the class constants needed, their data type and value?

d. What are the class attributes, their data types and valid ranges?

e. What are class methods needed, their arguments and return types?

2. (2 points) Show the associations between the classes and any classes that are compositions of other classes.

3. (2 points) Give ideas for your algorithm on how to manage the appliances (determine which "on" smart appliances to cycle off for that interval) to keep the usage below the wattage warning level.

4. (4 points) Create test cases for each class (tables or short descriptions).

5. (2 points) Submit at least 5 questions you have about the above Requirements Document


Coding Submission (80 points)

1. (25 points) Menu System - A user should be allowed to find, add and delete an appliance, or use an input file to add appliances. A user also should be able to view subsets of appliances (all appliances for a location, or all appliances of a particular type across all locations).

2. (25 points) Simulation - The user will be able to run a simulation loop. The simulation system must publish a report of which appliances/locations were affected during each interval. The user should be able to view a summary report of the total number of locations, and total number of each appliance of each type.

3. (10 points) Test programs for each non "void main" class

4. (10 points) Exception classes implemented for each user-defined class, output to log.txt

5. (10 points) Analysis (1 page) - How well did your algorithm work to manage the appliances (determine which "on" smart appliances to cycle off for that interval) to keep the usage below the wattage warning level? What changes might you try to make to improve it?
