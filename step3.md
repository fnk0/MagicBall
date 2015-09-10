## Step 3 - Connecting the UI to the Java code

### Let's start with the Shake code...

So... The shake code is not very complicated (just a little bit of math) but for the sake of keeping the Codelab short I will just give it to you and tell you to copy / paste into your project.

1. Create a Java class Name **ShakeListener** next to where your **MainActivity** is
2. Copy the entire contents of [this file](https://github.com/fnk0/MagicBall/blob/master/mobile/src/main/java/com/gabilheri/magicball/ShakeListener.java) into that **ShakeListener**
3. Create a Java interface named **ShakeSensorCallback** next to your other files
    * Put this code inside ShakeSensorCallback:

    ```java
    void executeShakeAction(@ShakeListener.ShakeEvent int action);
    ```

Feel free to read the ShakeListener code later on and make sense out of it. If you do not understanding anything in that code come back in a few months and you will be surprised at how much you have learned!

### Now to the interesting part!

Open MainActivity

###### Housekeeping:

* Delete onCreateOptionsMenu
* Delete onOptionsItemSelected

Those 2 methods are used only if we had an menu in our top Toolbar. We are not going to use this right now. Using thw Android toolbar deserves a tutorial of it's own!

##### So... What is onCreate?

You probably have noticed that your Activity has an **onCreate** method right? So if we compared this with regular java the onCreate would be the **main** function of that activity.

When the system starts the activity the first thing called is onCreate. This is where our initialization code will happen.
As opposite to a command line Java program where it executes and them terminates the program unless specified an Android activity will launch (execute) and wait for user input (i.e. user touching somewhere, shake, type, etc..)

##### Declaring [Instance Variables](https://en.wikipedia.org/wiki/Instance_variable)

Inside your Activity but outside onCreate declare the following variables.

```java

// This will hold a reference to the Android sensor that controls the sensors such as
// Accelerometer, Gyroscope, etc..
SensorManager mSensorManager;

// This will hold a reference to the ShakeListener that we will be using to detect when a shake happens
ShakeListener mListener;

// We initialize a Random() object that will take care of giving us a random phrase
Random mRandom = new Random();

// Will hold the Array with strings that represents our answers
String[] mAnswers;

// The TextView from your XML layout that will display the answer
TextView mAnswerTextView;

```

##### Adding our phrases to Strings.xml

Before we do anything else we need to add our phrases into the **res/values/strings.xml** file

Open res/values/strings.xml and copy the following phrases:

```xml

 <!-- This is the array of strings used for answers -->
 <!-- Feel free to modify the phrases here to say anything you want!! -->
 <string-array name="magic_answers">
    <item>It is certain</item>
    <item>It is decidedly so</item>
    <item>Without a doubt</item>
    <item>Yes definitely</item>
    <item>You may rely on it</item>
    <item>As I see it, yes</item>
    <item>Most likely</item>
    <item>Outlook good</item>
    <item>Yes</item>
    <item>Signs point to yes</item>
    <item>Reply hazy try again</item>
    <item>Ask again later</item>
    <item>Better not tell you now</item>
    <item>Cannot predict now</item>
    <item>Concentrate and ask again</item>
    <item>Don\'t count on it</item>
    <item>My reply is no</item>
    <item>My sources say no</item>
    <item>Outlook not so good</item>
    <item>Very doubtful</item>
  </string-array>

```

##### Putting everything together

Now that we have our Layout, the phrases and have our instance variables defined we are going to initialize them and hook it up to our UI

###### 1. Implement the ShakeSensorCallback interface!

In your activity after the ** extends AppCompatActivity** add the following: ``` implements ShakeSensorCallback ```

Your activity declaration should look like this:

```java
public class MainActivity extends AppCompatActivity implements ShakeSensorCallback
```

You should be getting a complaint from Android Studio because you are not implementing the necessary methods declared from that interface.

**Trick!**

If you are on a Mac press CMD + N and if you are on a Windows machine press CTRL + N

You should have seen a Generate menu pop up. Select **Implement Methods**

A method that looks like this should have been generated for you:

```java
@Override
public void executeShakeAction(int shakeAction) {
    // This will be called anytime a shake action happen
}
```

###### 2. Instantiating our instance variables

Inside onCreate add the following code:

```java

// We get a reference to our TextView
mAnswerTextView = (TextView) findViewById(R.id.answer);

// Get the StringArray that we declared in the strings.xml
mAnswers = getResources().getStringArray(R.array.magic_answers);

// Get a reference to the Sensor Manager
mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

// Instantiate our ShakeListener
// Note: this is the activity.
// We can use tis because we implemented the ShakeSensorCallback in our activity
// Otherwise that would not be possible.
mListener = new ShakeListener(this, mSensorManager);

```

###### 3. Displaying the text to the user

Inside the **executeShakeAction** method we are going to add code that displays text to the user

Here is what we want to do:

1. If the user shakes vertically and the text is empty them we display a text to the user
2. If the user shakes vertically and the text is not empty we don't do anything
3. If the user shakes horizontally we clear the text

You can change the Text of the mAnswerTextView with the following code:

```java
mAnswerTextView.setText("myAmazingText");
```

We can get a Random string with the following code:

```java
String randomString = mAnswers[mRandom.nextInt(mAnswers.length)];
```

Your turn! With that information try to make the text show to the user

You can check out the complete solution [here](https://gist.github.com/fnk0/25fbd5f83186607b0467)

### You are done!! Congratulations on writing your first app!!

###### Note: If you want to make the ball spin check out [this code](https://github.com/fnk0/MagicBall/blob/master/mobile/src/main/java/com/gabilheri/magicball/MainActivity.java) and see how easy is to animate something!

### Where to go from here?

###### [Check out google's free Android for Beginners course on Udacity](https://www.udacity.com/course/android-development-for-beginners--ud837)

It's free to watch the videos! You only have to pay if you want to get a certificate
