# Hackathon Tarnow 2022
## Idea
We assumed that we would make an app for waste management services that could optimize their time and fuel costs.
It's a mobile app that uses the travelling salesman algorithm to find the optimal route. Also it uses embeeded system with ToF sensor which would be placed inside bin to determine its level.

## Implementation
### Technologies
- Android API 32,
- Java 7,
- C,
- NXP development board,
- ThingSpeak service.
### Code
The data from the sensor is sent to the server via the wifi module, and then it's read by the mobile app.
In the app, user can establish a route based on different bin levels. Once computed, the route is uploaded and displayed on Google Maps.
## UI
![Screenshot_20230409_160839](https://user-images.githubusercontent.com/54991097/231132594-b7757422-6d56-4efd-96a1-5545c52cc124.png)
