# Evolutionary Design Without* Tests

In late 2020 and early 2021 we are doing some ensemble/mob programming on the theme of _evolutionary design without tests_. 
These sessions are part of a program provided through [pubmob.com](https://pubmob.com/offerings/jbrains-evolutionary-design-without-tests/).
In these sessions, we experience evolutionary design without spending time on writing tests. This has two essential goals:

- Spending more time guiding the design to evolve.
- Exploring ways to add safety without automated tests.

I believe that this constraint will nudge us towards tiny steps and relentless refactoring, especially code that reveals
its intent with uncommon effectiveness. We'll see!

## The Project

We're working on the Point of Sale system that I use as one of my standard teaching examples. It forms the basis not only of these sessions, but also of my training course [The World's Best Intro to Test-Driven Development: Level 1](https://tdd.training). We build a simple command-line interface that mimics a general-purpose cash register in a typical (North American) shop.

### Deployment Architecture

We have a standalone Java application that reads input from `stdin` and writes output to `stdout`, all in the form of text.

### Tools

- Java
- gradle
- jshell
- Markdown (writing documentation)

### Setup

`PROJECT_ROOT` is the root directory of this project, meaning the directory into which you cloned the repository.

1. Install Java/the JDK. The JDK should already have bundled jshell. This project builds and runs correctly with Java 11 or higher. You might like to do this with <https://sdkman.io> especially if you have to manage multiple versions of Java on your system.
1. In the project root directory, run `./gradlew clean run --warning-mode all --console plain`. The switch `--console plain` is important, because otherwise gradle's rich console deletes your input as the application processes it. This could confuse you if you didn't know that
gradle would do that. It confused me the first time.
1. In the project root directory, try running `jshell --class-path $PROJECT_ROOT/build/classes/java/main` to run the REPL.
1. When importing this project into your IDE, please note the minimum Java language level of 11. We'll raise this if we decide to use more advanced language features in the future. Use your IDE's feature for importing a Gradle project in particular. If you don't know how to use that feature, then search the web for "import existing gradle project" along with the name of your IDE. If you've never done this before, it might take 30 minutes and a few tries to get it working.

### Command Language

- `q`: quit the application
- `total`: end a shopper's purchase and print the total amount of money that the shopper needs to pay
- `receipt`: print a receipt for the most-recently-completed purchase (feature in progress)  
- any other text: the barcode of the product to add to the shopper's purchase in progress

### Further Documentation

Look in the `documents/` directory. If the name of the file doesn't explain itself, then we need to rename it.

### Future Features

As of the time of writing, the session lead (that's me, @jbrains) acts as the Customer for this project, and will select the next feature as the ensemble completes a feature to his satisfaction.

