Sudoku Solver
===

Introduction to the Sudoku Game
---

Sudoku is a century old game first created in France, then popularised by the Japanese game company Nikoli in 1986. It is time consuming and challenging, requiring strong critical thinking and analytical skills to solve the puzzle quickly and correctly. The structure of the Sudoku board is laid in a 9 by 9 square, separated into 9 3x3 squares. The number set consists of the numbers 1-9, and it is necessary for the player to complete the rows horizontally, vertically, and within each specific 3x3 square without repeating any numbers in order to win the game. The games can be printed on paper and solved with a pen or pencil, making it portable and convenient to carry when the player wants to kill time. Sudoku games often appears on newspapers as a result of its convenience.

Nowadays, with the advancement of technology, it is possible to code the sudoku board on the computer, allowing anyone with internet access and a personal computer to play the game. In the Sudoku game, the creators used the computer programming language Java to code Sudoku puzzles that are user friendly and fun to play. This manual will serve to explain to the reader how the code is set up, and how each class is made so that it plays its specific function in making the code work.

How Does it Work?
---

The Sudoku program can be separated into many components. These include many technical details that would cause confusion if explained, so this will just be a brief overview of what’s going on. A structural outline of the code can be seen below:

![diagram](https://user-images.githubusercontent.com/41842161/50476369-bfcd2c80-097c-11e9-89d0-309dba773257.PNG)

For the player, however, they see a different user interface. Since the code works behind the screen, they will not see all the methods, yet the methods will process their specific actions through the interface. First, we have a stopwatch clock to keep track of the time that the user takes to play the game, and below that, we have set up a point system to calculate the amount of points that the user can earn. The clock is just a track for the user’s progress. The point value system works like this; for each number the user gets right, he or she will earn 100 points, more or less, though the relative position of the board will effect the number of points earned. If the user gets it wrong, he or she will lose 20 points.

![points](https://user-images.githubusercontent.com/41842161/50476443-028f0480-097d-11e9-884f-c16b900e4dce.PNG)

The actual game board, however, will look like the diagram below. It consists of a 9x9 square separated into 9 3x3 cells. There are 9 numbers in each of the squares, numbering from (1-9), the number that the user needs to play the game. On the right, there is an AI board in which the user cannot access; it shows the speed of the AI in solving the puzzle in comparison to the player. In the middle, there is the panel with the ‘Action’, ‘Done!’ , and ‘Match if Done’ buttons, all of which the user can access. The Action button helps the user to start the game by creating a new board. The Done! button helps to save the board at the last move and then solve the AIBoard. The Match if Done button is used to gain extra points in the end. Essentially, the computer will solve a sudoku of equal difficulty. If the user can place corresponding numbers in his own board, legally, the user gains extra points. 

![snapshot](https://user-images.githubusercontent.com/41842161/50476457-1470a780-097d-11e9-9f71-5e7cc01b6277.PNG)

How to Play?
---

There are specific steps to start a game, but they shouldn’t be too hard for the user to interact. To start the game, the user presses the start game button, which allows them the classes to independently generate a random game board for the user and and the AI. At the same time, the button also starts the timer, which starts counting the passed time. The user then starts to fill out the numbers by clicking the cell, which returns a message that states that the user can enter a number 1-9, as shown below:

![snapshot2](https://user-images.githubusercontent.com/41842161/50476605-982a9400-097d-11e9-9b63-62689e829ef3.PNG)

The user can continue to solve the sudoku puzzle till whenever, but the clock is always ticking (the AI technically “solves” the board in 50ms) and when the game is over, the user can see the solution, the time spent, and the points he or she earned from that specific game. It is basically a simple sudoku game with a more ‘competitive’ twist, with the user playing against an AI (well, if the user gets lucky in the end).

Functional Specification
===

GUI Classes
---

**Action Class:**

Requirements: A class that could control the different actions for each specific event.

Functional Specifications: This class is separated into different methods that see if the actions are valid and can be used in the code. Methods such as setActionState and setRunstate help to see if the action is legitimate; a string is returned if it is right, and the code continues to function. It is important that several serialcodes are set up in an array. These codes provide the string password for communication between different classes. 
Additionally, the Action class must contain a parameter for the state of an action (true/ false or valid/invalid). These states can be stored as strings or booleans, but they must be implemented in a separate constructor. 

**AIBoard Class:**

Requirements: A class that holds everything for the AI Board. This includes the board arrays (initial and final), and methods for permuting and solving these arrays.

Functional Specifications: This class is separated into different methods that sets the game frame, and also codes the AI algorithm. A transpose method must be provided to scale two array parameters into their graphable form. Furthermore, two boolean variables are curcial to the functionality of the program as a whole: one which denotes an a-okay for solving and one which denotes an a-okay for comparing between this class and a player class. 
The solving algorithm should be split into recursive statements, and they must be constructed by starting at the top left box, and then placing/backtracking through placing strategic numbers at each square and then checking. To be able to test the validity of number-placements on the board, three methods must be developed to check the concurrency of rows, columns, and boxes.


**AIPuzzle Class:**

Requirements: A class that controls the AI puzzle, the puzzle the AI needs to do

Functional Specifications: Renamed version of AI Board; please check above for more specific details.

**Matcher Class:**

Requirements: A class that could control the different actions for each specific event.

Functional Specifications: This class allows for the comparison of the AI and user sudoku puzzles. Under the condition that if the user gets lucky, one of his squares will be the same as an AI square, and he gets some points. We took this idea from the game battleship; the user can gain some points or at least an advantage if it manages to figure out or luck on what the AI is doing, and will get extra bonus points that can be added on to his or her high score. It is really a special feature. To properly implement the class, a reference to a buttonpanel must be made in order to gain access to the call for cross-board-checking. Furthermore, a constructor must be implemented which takes the buttonpanel into account.


**MoveData Class:**

Requirements: A class that saves all the data on the moves the user and the AI makes

Functional Specifications: DID NOT NEED; Eliminated.

**PlayerBoard Class:**

Requirements: A class that could control the different actions for each specific event.

Functional Specifications: This class is separated into different methods that help the player board GUI to function. It basically sets the layout of how the player board should function with the player puzzle, and stores the 2D arrays that the GUI needs to access in order to run the game. All of the puzzle information is stored there, and the 2D arrays of puzzles and solutions can be found. Also, there is a method that transposes the code and switches it around, a special feature of the puzzle. Importantly, a draw method must be provided which takes into account the individual font and placement of the sudokucells. Additionally, a puzzlewidth must be established to properly offset the display. 
Make sure to implement several checker methods on the state of the board. Importantly, an accuracy and completion test must be provided. Another important algorithm which must be developed through this class is the removePossibleValue algorithm. It must take a desired sudoku cell as a parameter and remove the affected values it has on the board. Conversely, a method must also be provided to gain access to all the possible values, and a complementary draw method must display all values after any user input (this can be implemented in the buttonpanel class). The playerboard class must also be able to set and get any cell values and provide an array method which returns the array of the cell objects. 

**PlayerPuzzle Class:**

Requirements: A class that control the player puzzle setups

Functional Specifications: This class basically is used to setup the puzzle that the player is going to play on: the frames, the model of the type of board the player is playing, and the different buttons the players can access to start the game, get hints, or find the solution.
This class was overridden by the playerboard class.

**PointData Class:**

Requirements: A class that could controls the different points that the player can earn throughout the game

Functional Specifications: This class basically does what its name suggests: it stores all the points the user gains and stores them into a queue. Importantly, the action class directly interacts  with this class and any panel classes. Thus any permission to add or remove items from the points queue must be granted only through an appropriate Action object. Furthermore, the PointData class must have to methods in particular which set or update point values and/or return the total point values.


GameCode Classes
---

**ButtonPanel Class:**

Requirements: A class that could control the different buttons that the user can press. They will be placed in the middle of the GameFrame Class.

Functional Specifications: This class basically sets up all the buttons on the GUI for the user to interact. It sets the layout of the buttons and the different placements that they are put in. The only button, the Start game button, is placed in the middle of the Button Panel, which will then be implemented in the GameFrame class. The button can use, really, any layout. But make sure that they can be linearly aligned in the middle of the frame structure. Furthermore, JToggleButtons seem to satisfy functional specifications best through providing boolean states, which help in the Action communication to the points and stopwatch while the button is being pressed or released.

**GameFrame Class:**

Requirements: A class that controls and stores all the methods and layout regarding the user interface. This class basically holds the structure of the GUI interface the user sees.

Functional Specifications: This class basically covers all of the visible parts of the GUI interface except for the Stopwatch clock, which is set in its own specific class. The layout is set as follows: the “West” holds the player’s board, in which the player can interact and do the game; the center holds the ‘Action’ button in which the user can start his game, and the ‘East’ holds the AI board which the user cannot interact with but where the AI is trying to solve its own puzzle. A resizable screen would do well for this frame, and additionally, it is important to include an exit procedure out to the frame, but in general, sticking with java protocol in frame construction will usually be fine. Both the AIPanel and main panel are to be constructed in this area. Finally, relevant methods which allow for repainting after changes to the panels must be provided. Getters and setters for the frame and the sudokuPanel are required in order to properly interact with the ButtonPanel class.

**LoginArrays Class:**

Requirements: A class that starts the program after the Sudoku Game is run; it creates a login window that requires the user to login before continuing to the game.

Functional Specifications: This class only needs to hold the database of arrays considered for demonstration or project run. It is to be used along side the main SudokuGame class in completing the setup of the game.

**SingleSudokuPanel Class:**

Requirements: A class contains a panel of cells; this class creates the layout of the interface, and selects the model in which the player should play.

Functional Specifications: This class basically creates a single sudoku panel, which is where the sudoku board should go. The single sudoku panel controls the type of puzzle used, the model, and the buttons that go on the panel. It also implements the mouse listeners and button listeners whose methods are stored in the Toggle and the UserTypeInput classes. The single sudoku panel and the other button panels will together form the SudokuGame class, where the whole game can be started and played. 3 structures summarize the functional necessity of this class: MouseListener, UserTypeInputListener, and a paintComponent. Adding these three structures, along with a constructor which passes reference to the frame, player model, and button panel, will complete the necessary functional specification.

**Stopwatch Class:**

Requirements: A class that could hold the stopwatch clock that keeps track of the time the user and the AI has spent on the puzzle.

Functional Specifications: This class basically creates a separate interface which holds the time (and also the points) that have passed since the user started the game. It is important because it helps to determine if the user is ahead of the AI. The points were also an interesting addition; there are two separate conditions with the control the points. If the player gets the game wrong, they will lose 20 points, while if they get the number right, they will earn 100 points. Some point implementations are up to the user, and it is advised to use board location as a driving force for accumulating points. At the end of the game, the user enters a luck round in which the user is compared against the AI solve and then given extra points for matches. This portion may be removed without the reduction of functional specification. However, it is important to provide stop and start functionality to the stopwatch. As well as a launcher class for proper functionality.

**SudokuCell Class:**

Requirements: A class basically draws out each of the specific cells needed by the Sudoku board. They are the small squares that are later ordered in a 9x9 square to create the whole sudoku board.

Functional Specifications:

This class draws out each of the specific cells in the Sudoku game board. These can later be replicated into a 9 by 9 square to form the whole Sudoku board. The first few methods are direct methods that return specific values that could be accessed by other people. The draw method is basically the main focus of this class; it shows how the cell is created and how it is drawn. Methods must be provided to get and set values stored in the sudoku cell. Additionally, a “maxvalue” parameter must be utilized in order to cap the value stored in the cell. Graphics methods involving setting the bounds for the cell must be used and a List<Integer> possible cell values must be constantly updated (as is aforementioned). Copy methods are optional, but will greatly ease calculation on the AIBoard. Lastly, be sure to implement all standard Graphics methods (including draw) in order to display the cell of your choice.

**SudokuGame Class:**

Requirements: A class that could control everything and run the program.

Functional Specifications: This class contains two methods. The run method, and the main method (which is used to run the code). The run method basically set up so that the player’s board and the AI board are both created, and that they run the specific puzzles that they’re meant to run. Overall, it basically covers everything that needs to be run, and that is why it has the main method.

**ToggleListener Class:**

Requirements: A class that the different button events, which are linked to the ButtonPanel class.

Functional Specifications:

SetToggleButtons: This method is used to store the values of the Button array in a Jtogglebutton. The difference between a JButton and a JToggleButton is that the JToggleButton, when pressed, stays pressed even when the mouse is removed. The JButton, on the other hand, returns back to normal after the mouse is removed from the button. The reason we want the JToggleButton is because that when we start the game, we want the game to continue until either the AI or the user has won, and not just stop right after the mouse leaves the start game button. This may be downloaded off of preexisting implementation.

**UserTypeInputListener Class:**

Requirements: A class that controls the mouse listeners and mouse controls, and also the values contained inside each specific cell in the sudoku board.

Functional Specifications: This class, in comparison to toggle, stores the Mouse Listeners and the get value methods of the cell than the Button listeners. It provides the same purpose as the ToggleListener Class, as they are both used to aide the SingleSudokuPanel class with functional improvements, such as mouse actions and button actions, and also is used to get the values within the specific cells on the sudoku board. This may be downloaded off of preexisting implementation.
