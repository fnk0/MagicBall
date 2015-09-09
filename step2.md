## Step 2 - Understanding the Anatomy of a Android App & Creating your first Layout

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

Open activity_main.xml in the res/layout folder
You should be seeing something similar to this

![main_layout](https://github.com/fnk0/MagicBall/blob/master/images/main_layout.png?raw=true)

Let's start modifying it!

Replace ```RelativeLayout``` with ```LinearLayout``` and delete the ```TextView``` placeholder
Remove the anything that says ```android:padding="..."``` from the code and add ``` android:orientation="vertical" ```

After the changes your code should look like this:

```xml

<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:orientation="vertical"
  tools:context=".MainActivity">

</LinearLayout>

```

##### Understanding the code:

* LinearLayout: Specifies that the layout should place the Widgets/Views in a linear fashion, one after the other
    * ```android:orientantion="vertical"``` : We tell Android that this LinearLayout will place the views vertically


Now we gonna click on the tab at the bottom which says "Design" and we will add our Widgets to make the screen

Drag a LargeTextView a ImageView and another LargeTextView to the UI. (in this order)

Them go back to the "Text" tab.

Your code should now look like this:

```xml

<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:orientation="vertical"
  tools:context=".MainActivity">

  <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textAppearance="?android:attr/textAppearanceLarge"
    android:text="Large Text"
    android:id="@+id/textView"
    android:layout_gravity="center_horizontal"/>

  <ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/imageView"
    android:layout_gravity="center_horizontal"/>

  <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textAppearance="?android:attr/textAppearanceLarge"
    android:text="Large Text"
    android:id="@+id/textView2"
    android:layout_gravity="center_horizontal"/>
</LinearLayout>

```

Let's add our image!

[Click here to download the Magic Ball image](https://github.com/fnk0/MagicBall/blob/master/images/magic_ball.png?raw=true)

Now copy this image inside the res/drawable folder.

With the image added we can specify the image that we want our ImageView to use:

We gonna specify the size of our ImageView and the image to use. We also changre the id to  be something more descriptive

Make your ImageView code look like this:

```xml

 <ImageView
    android:id="@+id/imageBall"
    android:layout_width="330dp"
    android:layout_height="330dp"
    android:src="@drawable/magic_ball"
  />

```
#### Adding a image to the project

So far we just added a image direct to the drawable folder... but that will not work for all situations.
Android phones and tables have lots of screen sizes and we want our assets in all of them! That's why there's a great way of generating those icons for us.

[Android asset studio](https://romannurik.github.io/AndroidAssetStudio/)

[Download this image](https://github.com/fnk0/MagicBall/blob/master/images/vibrate_horizontal.png?raw=true) and [this one](https://github.com/fnk0/MagicBall/blob/master/images/vibrate_vertical.png?raw=true)

1. Click on Generic Icons
2. Click on Image
3. Select the one of the images you just dowloaded
4. Remove the padding but keep the original size of 24dp
5. Change the color and give it any name you wish
  * Note: asset names (such as images, sounds, etc.. should only be written use lower case letters and underscore)
  * Note2: for Icons is a good practice to place a ic_ in front of the name (i.e. ic_vibrate_vertical)

6. Download the zip file with the image and unzip the folder
7. When you open the folder you should see something like this:
  * res/
    * drawable-mdpi
    * drawable-xdpi
    .....

8. Copy all the drawable folders into the res folder in your application nd if they ask you to override say yes
9. Repeat for the other asset!

##### Adding the image to the text

Now that we have our images we are going to add them to the text so we can have some nice icons in our app.

Make your first TextView look like this:

```xml

<TextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="8dp"
    android:layout_marginTop="16dp"
    android:drawableTop="@drawable/ic_vibrate_horizontal"
    android:gravity="center"
    android:text="Shake horizontally to get a answer"
    android:textColor="@android:color/white"
    android:textSize="24sp"
    />

```

Repeat the same thing for the bottom TextView but this time use the vibrate vertical drawable and place the drawable on the bottom

##### Now my text is white? What should I do!!

So... in our last step we changed our TextView and the text of the TextView is White which is the same color as our background
Wel... we are going to change this!

Inside the folder **res/values** open the file **styles.xml**

You should see this:

```xml

<!-- Base application theme. -->
  <style name="AppTheme" parent="Theme.AppCompat.Light">
    <!-- Customize your theme here. -->
  </style>

```

The first change we gonna make is where it says ```parent="Theme.AppCompat.Light" ``` you should change to ``` parent="Theme.AppCompat.Light.NoActionBar" ```

Second we are going to add our custom colors to our app. Right below it says customize your theme here and before ``` </style>``` add the following:

```xml

<item name="colorPrimary">#4DB6AC</item>
<item name="colorPrimaryDark">#009688</item>
<item name="colorAccent">#3F51B5</item>
<item name="android:windowBackground">?colorPrimary</item>

```

That will define the primary and accent color of your app as well a darker version of the primary color to be used on the phone status bar at the top.

Your final styles.xml should look like this

```xml

<resources>
  <!-- Base application theme. -->
  <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">#4DB6AC</item>
    <item name="colorPrimaryDark">#009688</item>
    <item name="colorAccent">#3F51B5</item>
    <item name="android:windowBackground">?colorPrimary</item>
  </style>
</resources>

```

### Finishing the UI

So... we are almost done with our UI setup for this app!

We need a TextView to be placed on top of the image to display the text that we gonna show to the user.

But... how we place something on top of something if we are stacking things linearly? That's a great question my padawan!

##### RelativeLayout to the rescue!

We gonna wrap our ImageView inside a RelativeLayout. This will allow us to have a TextView on top of the ImageView

What we want is to have this:

```

<LinearLayout>
  <TextView />
  <RelativeLayout />
    <ImageView />
    <TextView />
  </RelativeLayout>
  <TextView />
</LinearLayout>

```

Let's do it them!!

I will give you the code for the RelativeLayout and the modified version of the ImageVIew. I will leave the TextView code for your own pleasure!

```xml

  <RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <!-- We added => android:layout_centerInParent="true" -->
    <ImageView
      android:id="@+id/imageBall"
      android:layout_width="330dp"
      android:layout_height="330dp"
      android:layout_centerInParent="true"
      android:src="@drawable/magic_ball"
    />

    <!-- Your TextView should go here -->

  </RelativeLayout>

```

So... what is doing the magic here is that we are telling the ImageView to center itself on the parent which at this moment is the RelativeLayout.

can you guess what you need to do with the TextView? Also the TextView should have a height and width of 140dp.

You can check out the full activity_mail.xml solution [here](https://github.com/fnk0/MagicBall/blob/master/mobile/src/main/res/layout/activity_main.xml)

If everything was right... your app should be looking somewhat like this:

![screenshot 1](https://github.com/fnk0/MagicBall/blob/master/screenshots/screenshot_1.png)

### Running the app!

Before we go to the next and final step of the app let's run the app just to make sure everything is looking good on ur phones:

1. Connect your phone to the computer
2. [Enable USB Debugging](http://www.phonearena.com/news/How-to-enable-USB-debugging-on-Android_id53909)
3. At the menu bar you should see a green "play" button. That's the Run button. CLick on that button to run the app. This will install the app on your phone and open the app.


### Congratulations!!

Take a minute to be proud of your first Android layout! Now lets go to the [final Step](https://github.com/fnk0/MagicBall/blob/master/step3.md) and make our app actually do something.












