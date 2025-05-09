Algorithm Analysis
Objective
The objective is to control appliance power usage so that the overall wattage usage stays below a predetermined warning threshold. The algorithm lowers consumption by shutting off smart appliances first and, if required, lower-priority appliances if the wattage surpasses the limit.
Algorithm Design
Total Wattage Check: The total wattage is computed at every interval. Results are recorded and the system advances to the following interval if it falls below the warning level.
Above Warning Level: The system initially shuts off smart appliances if the wattage surpasses the threshold. Selectively, lower-priority appliances are turned off if the issue continues.
Impact Tracking: For analysis purposes, the algorithm records every appliance that was cycled or turned off during each interval.
Strengths
Real-Time Monitoring: The system actively monitors wattage and adjusts as needed to stay below the warning level.
Smart Appliance Management: Prioritizing smart appliances for load shedding helps minimize the impact on critical appliances.
Selective Control: Appliances can be turned off based on priority or location, ensuring essential services stay on.
Tracking Impacted Appliances: The system logs which appliances were affected, providing transparency and data for future optimization.
Challenges & Improvements
Priority System: Introducing a priority system to determine what appliances to turn off would give our algorithm an added layer of detail. For example, appliances like refrigerators and ovens should have a higher priority than a TV.
Time of Day: Peak hours should be handled more aggressively, with non-essential devices being turned off first during these times.
Have a Single Appliance Object: Having a single appliance object with an added boolean parameter “isSmart” to differentiate smart and normal appliances might have proven to be simpler to manage rather than separate classes inheriting from Appliance.
Conclusion
Our algorithm effectively manages appliance power consumption but could benefit from a priority system, adjustments based on the time of day, and the consolidation of the NormalAppliance and SmartAppliance objects.
