
This is a MobiShop Application 

MobiShop is an end to end project which basically list down various mobile phones on the screen along
with different features available with each mobile devices.

It gives the liberty to user to select his/her specific available features with the device.

If de-highlights the features not compatible with other features.

The code is build on Clean Architecture using MVVM design pattern underneath it.
There are 3 layers - Data, Domain, Presentation

1- Data layer - It basically fetches data from API or room database on the basis of network availablity
2 - Domain layer - It contains App specific code like enitities
3 - Presentation layer - It contains view classes like activity and viewmodel whose main purpose is to show data on the screen

Application also has offline support i.e. whenever any api call is made, its data is saved in the room database
and in case internet losses then application shows data from room database

To run this code, 
First clone the project
Then after build, run in any emulator

