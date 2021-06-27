# android-app
Android app to control flight simulator

Attached videos:
1. Video of the presentation - https://youtu.be/OhK1k7A_K4I
2. An example video of the plane flying through the app - https://youtu.be/SyOzbuZPKU0

Application description:

The app is a very simple app, and there is one action that can be performed. The app will have
Option to connect to the IP where the flight simulator is running, and specify the listening simulator, with a button
Login. The app will have Seek Bars for the Throttle and Rudder, as well as a virtual joystick
To communicate with the simulator - the y-axis for the Elevators and the x-axis for the Ailerons - put it together
We have to write.

Note The app is designed to work with the flight gear flight simulator.

Application installation instructions:
1. Download the files from github
2. Extract the files and select the folder named src
3. Open a new project in Android Studio
  Minimum version Android 5
4. Look for the src folder in the new project
  Then replace the folder src in project with folder named src that you found in section 2
5.The app is now ready to use

Operating instructions together with the flight simulator:
1. Download the flight gear app to your computer and install the necessary installations
2. Enter settings
3. Insert the following line in the space provided
--telent=socket,in,10,localIp,port,tcp
local ip - enter your local ip of your computer
port - enter a port and remember it
For example:
--telent=socket,in,10,10.0.0.5,5000,tcp
4.Click fly
5.Launch our app and Enter the IP and port in the appropriate place in the application and click connect
6. You can now use joystick
and in the throttle as well as in the rudder for the purpose of flying the aircraft
7.Have a nice flight.

