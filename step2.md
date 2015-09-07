## Step 2 - Understanding the Anatomy of a Android App

##### Activity:

A single screen in an Application. An Activity consists of a Java file with the code and a XML file that represents the UI.

##### AndroidManifest.xml:

The manifest is a description of your app to the system. The manifest holds declaration of the Activities an app has, the permissions it requires, and much more!

##### View:

A view is anything that is displayed on the screen. Any view is enclosed within a rectangular frame that represents its bounds.

##### Widget:

A widget is a View that has already been defined by the system or someone else. Ex: TextView, Button, etc..

##### Theme:

A theme is a set of rules that will define the overall theme of an App. Such as default background color, text color, etc..


There's many more important therms that you will be learning with time! Just keep this ones in mind for now.


## Taking a look into the Structure

![structure 1](https://github.com/fnk0/MagicBall/blob/master/images/structure1.png?raw=true)

* mobile -> src -> main -> java -> packageName: Here's where our Java code will go
* mobile -> src -> main -> res -> layout: Here's where our .xml layouts will go
* mobile -> src -> main -> res -> values -> strings.xml: Here's where we put our app Strings.
* mobile -> src -> main -> res -> drawable: Here is where our images will go


#### Enough!! Let's code!!

Ok Ok... let's start coding


## Setting up the Layout


1. Open activity_main.xml in the res/layout folder

You should be seeing something similar to this

![main_layout](https://github.com/fnk0/MagicBall/blob/master/images/main_layout.png?raw=true)


