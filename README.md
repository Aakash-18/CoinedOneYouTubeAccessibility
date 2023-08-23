# CoinedOneYouTubeAccessibility
CoinedOne App for collecting the channel name, timestamp and the name of the video which plays on the YouTube .


Task:Accessibility Service: The primary mechanism to achieve this task is the Android Accessibility Service. If you're unfamiliar with this, you'll need to learn about it and understand how it can be used to monitor user activities on other apps.

Data Collection: The app should log the following details when a user opens a video on the YouTube app:
Time at which the video was opened
Video title
Channel name


User Consent: Before monitoring, the app should request the necessary accessibility permissions from the user.


Display Data: When the PoC app is opened, it should display a list of videos watched by the user on the YouTube app, showcasing the data points mentioned above.


Things done:Accessibility service is created and the data is fetched, the video name is fetched using the package name and accessibility node info,content description is used for taking channel name. The time is also taken. 

Coroutines is used to add the data and access the complete data, room is used to store the data and view model is used to get the data and frageent is used to dynamically display the logs that is collected by the accessibility service.

Accessibility consent is asked during opening of the app after that when we open the youtube app starts to collect the logs and store the data.




