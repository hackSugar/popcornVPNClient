# popcorn_vpn

Official client app of popcorn

## Task List

Hi! This is the client app for popcorn. Currently, this is going to be a crude list of things we need done. If you feel like helping, you can open a PR for any of the following (If it's a big project, please make this in either multiple small PRs or in one PR with multiple small, granular commits):

- popcorn Android NDK openvpn integration (This is a HUGE ONE, look at how [OpenVPN for Android](https://github.com/schwabe/ics-openvpn) does this)
  - Split this into multiple projects/PRs, each of these sub-problems can be a separare PR
  - Work with connecting to an ovpn through Android, again look at OpenVPN for Android & then bring that to Flutter with a method channel (preferably named connect taking an openVPN profile as a parameter)
    - With this, you will need to add specific Java code used in OpenVPN for Android that parses said file and connects to it using native android APIs (specifically VPNService). 
    - Look at how OpenVPN for Android does this and bring it over to this app in a neat fashion.
  - Work on getting a profile from a specific server and connecting upon recieving that profile (may not be able to be done at this moment as we don't have a distribution server set up)
    - For when something is set up, you will most likely be getting a profile and connecting through some secure channel (not yet decided). 
    - Look at how to implement this into Android, most likely you will get the file all through Flutter, eliminating the need for another method channel (File downloads work I believe in flutter, just not sure how we will be executing this).
    - Then you will take the downloaded file and calling a the method channel of the method `connect()`.
- popcorn iOS native [tunnelkit](https://github.com/passepartoutvpn/tunnelkit) integration (This is also a pretty big one)
  - This can also be split into multiple separate PRs
  - Start woth integrating the source of tunnelkit into the app (NOT THE EXISTING COCOAPOD as we will need to make our own patches to tunnelkit as desciubed in the making of an XOR obfuscate patch for tunnelkit)
    - To do this, it will need to be used as a Test CocoaPod, but when the code is set, we will use that for now until we may submit our own pod with the xor patches
  - Second, look at ways to connect a VPN on iOS
    - There are 2 places that this can be seen using tunnelkit. The first is the `Demo` folder of tunnelkit, which shows a rudimentary usage of tunnelkit, and the second is [passepartout](https://github.com/passepartoutvpn/passepartout-ios). 
    - Using both of these make the iOS counterpart to the flutter method channel `connect()` method, taking the profile in and connecting to the server with it using tunnelkit
    - Just like Android, make this in a neat, clean, and documented fashion
- popcorn iOS tunnelkit XOR patch support (Tunnelkit has an [xormask patch](https://github.com/passepartoutvpn/tunnelkit/pull/121), but we need to build on it with an xor obfuscase patch) 
  - Look at the PR and pull it into tunnelkit
  - Then look at [Tunnelblick's xor patches](https://github.com/Tunnelblick/Tunnelblick/tree/master/third_party/sources/openvpn/openvpn-2.4.8/patches)
  - Using those as a reference, write the equivalent for the `xor obfuscate` parameter in tunnelkit.
  - Submit that PR to [our clone of Tunnelkit](https://github.com/tmthecoder/tunnelkit) (yes. I have to move this to the organization)
  - One more time, in a nice, clean, and documented fashion
- Server integration/creation (Another extremely huge one, which needs a lot of discussion between our team as to how we should setup a server, its mirrors, and provision profiles)
  - Not much I can write since we don't really know what we're gonna do here yet
- VPN Hopping integration (iOS)
  - Write another method channel to hop VPNs(preferably called `hop()`, which is called after connecting if a parameter to hop is set in flutter
  - This will take a duration
  - The duration will be user-set or randomized, depending on user preference
  - Use that to set a timer, and after it is done, disconenct from the VPN and connect again using the Flutter-level methods (VERY IMPORTANT as this ensures the hopping timer will be set again if the user wants or will not be set if they do not)
  - All you need to do for this is call the flutter-level disconnect and connect with the hop method if it is enabled. 
  - The server will eventually return an auto-generated different profile each time, so you don't have to worry about selecting a random profile
- VPN Hopping integration (Android)
  - Write a coinciding method channel with the `hop()` method of iOS (or write the channel if this is done first) to hop VPNs on the androud side
  - This will also take a duration
  - That duration, again will be randomized or user-set
  - Use that duration to set an alarm and hop when it is done, returning to flutter and calling the flutter-level disconnect and flutter-level connect (VERY IMPORTANT as this ensures hopping will be reset as the hop method will be called from flutter)
  - Again, all you will need to do is call the flutter-level disconnect and connect methods when done
  - The server should give a random profile each time connect is called, making sure no more than one profile is currently on the device.
  
Currently, this is all I can think of that needs to be done. If anything else does come up, this will be regularly updated and I will be making a projets tab on this project soon as well.

# Contact Me

If you have any questions, feel free to write me an email: tmthecoder@gmail.com
