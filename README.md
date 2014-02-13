Flextrade Unit Test & Code Review Problem
=========================================

Notes
---------
This project was created using Maven. It uses the compiler (I have compiled this with Java 1.7) and sources plugins which generate JARs (these are placed in /target).
The additional libraries I used were: Mockito and TestNG for testing (I used TestNG for the @DataProvider and expectedExceptions features,it helped minimizing code written for tests).
I have also used the Google Collections library for the creation of lists using one line of code to make the code more readable.
I have created two Stubs for the two provided interfaces in the SimpleOrderManager class.

The classes that were subject to code review are placed in the com.flextrade.review package and the class subject to unit testing is placed in com.flextrade.test. Every other helper class is placed in the root com.flextrade package.

To compile the application, simply run:

	mvn clean install
