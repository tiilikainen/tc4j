# TC4J - Tiili's Cardinalizer for Java

This project provides a JAR that will render a `Long` value into its cardinal equivalent. Some examples of such numbers and their equivalents:

* 1: One
* 11: Eleven
* 111: One hundred and eleven
* 1001: One thousand and one
* 1111: One thousand one hundred and eleven
* 1000001: One million and one

## Usage

To build, execute the following command from the repository root:

    ./gradlew build

This will build the JAR containing the sources and place it in `build/libs`. After adding the JAR to your classpath, you can call `new net.tiilikainen.tc4j.LongCardinalNumber( Long l ).asString()` to render a long integer into its cardinal equivalent.

## Running it interactively

To run it interactively from Gradle:

    ./gradlew run
    
To build a portable JAR file and run that:

    ./gradlew jar
    java -jar build/libs/tc4j-<insert version here>.jar

## Testing

Running this command will execute unit tests:

    ./gradlew test

Test results are viewable at `build/reports/tests/test/index.html`.