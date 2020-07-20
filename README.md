TennisKata - Ground Tennis game score counter
=============================================

##General info
  - A simple ground tennis game score counter for each player following tennis rules

##Features
  - Can add player names for each games
  - lets you see the progress of the game
  - each players has their own score boards to follow their score

## Language and Architecture
  - TDD approach is utilised to cover most edge cases
  - MVVM approach is used with Repository pattern for isolating data from the view and the viewModel
    for modularity purpose
  - 100% Kotlin
  - Utilised Material Design, Mockk and JUnit libraries

## Usage
 - works on android Api 21 and above
 - requires Android studio 4.0 and above
 - steps
  1) Clone it with `git clone https://github.com/DEV-098/TennisKata.git`
  2) Open Android Studio
  3) Select Open Existing Project and Navigate to cloned directory
  4) Let the gradle sync operation finish
  5) Run the project

## To DO
  - apply dependency injection
  - write documentation for better future understanding
  - add more test cases
  - recreate better eye catching UI design for best user experience
  - few adjustment for backward compatibility to cover older Apis
