# QuiZzz
12.10.2023

Functionality
1. There is a splash screen that prompts you for username and password and two buttons underneath. 
2. Currently only the new user button works with full functionality (that is if you enter a username that is already present in the database, nothing will happen however if it's new, it will go on to the mcq section.
3. If you click the existing user button on the splash screen, it will perform a check on if the username and password are there. If so we will move to the mcq section if not, nothing will happen when you click the button
4. The MCQ question has been added with 10 Computer Science questions
5. A final score is shown in the end with the high score (number). If your score is equal to the highscore, "Your Score is the High Score!!" will be displayed.

Bugs
1. If you click the existing user button with everything valid, it will go to the mcq section but your score will not be stored in thr row associated with the username. This is because it is using the incriment id primary key to add the score.

-splitmoong

14.10.2023

Functionality Added
1. The final UI now shows your personal best and the high score (comparing all users) in the top.
2. The previous bug was fixed by implimenting a function that stores the personal best score based on the username and not the id (primary incriment key)

-splitmoong

16.10.2023

Functionality Added
1. The UI elements like the buttons have been made more coherent with each other and the text fields have been made bigger for easy readability. An icon has also been added to the program.

Bugs
1. If you leave the feilds blank in the splash screen and click on new user, it will create an entry in the database with blank username and password. This has to be fixed by adding a check in the getConnection function of the DatabaseConnector class.

-splitmoong.
