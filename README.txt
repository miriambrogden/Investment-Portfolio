Student Name: Miriam Snow
Date: November 29th, 2017

Description of Program: 
    This program is an investment portfolio which tracks a users stock and mutual fund investments. This program uses only one ArrayList to manage all the investments. Each investment is of type Stock or MutualFund which are both subclasses of the parent Investment class. The user is able to buy or sell new or existing investments. They can also update all the prices of each investment or get the total gain of all investments. Lastly, the user can search for an investment by symbol, price and/or keyword(s). The keyword search features the use of a Hashmap for a quicker search. The program uses a file to get input and output so the program can save its state and the user doesn't have to enter their list of investments every time they run the program. The program will not run without a command line argument specifying the file input/output name. Apart from the functionality, this program also implements a GUI interface utilizing components such as JPanel, JTextArea, JTextField, JButton, JLabel etc. All functionality occurs through the GUI instead of inputting and reading text from the terminal, making it more user friendly and visually appealing. 

Assumptions and limitations: 
    - The user can only invest in stocks or mutual funds. 
    - The program will not run if there is no command line argument
    - The first command line argument (after the program name) will be the one used as input/output for the program
    - Only files created by this program can be used for input
    - When performing a keyword(s) search, all keywords must be present in the name of the investment item
    - Every symbol is unique to that investment
    
How to run the program: 
    To run the program download the assignment3 package containing all the .java files and locate them from your terminal. Type "java Assignment3 insertFileName.txt". Replace insertFileName.txt with the actual name of the file you want to create or open. The program will begin to run and you will see a welcome message. The commands menu bar at the top will allow you to choose the various functionality options for the investment portfolio. Error messages or program feedback will appear in a textbox at the bottom of the screen. To quit the program choose "Quit" from the commands menu, or simply click "X" in the top right hand corner. Your program state will be automatically saved to a file with the name you indicated as a command line argument.
    
Improvements: 
    If I had more time to complete this assignment, I would spend more time on the GUI and learning different layouts and how to manipulate the panels better. I think that was probably the hardest part of this assignment, and if I spent more time on learning how to use the different layouts, what their disadvantages and advantages are, and how to implement them, my GUI could've been even better. 

Test Plan: 
    1. Test: main command loop
    Input: 6.5
    Expected output: reprints menu
    Actual output: reprints menu

    2. Test: buy an investment
    Input: investment
    Expected output: reprints menu
    Actual output: reprints menu

    3. Test: buy new stock
    Input: (quantity) 0.3
    Expected output: Error: please enter an integer
    Actual output: Error: please enter an integer

    4. Test: buy new stock
    Input: (price) 0
    Expected output: Error: please enter a double
    Actual output: Error: please enter a double

    5. Test: buy new stock
    Input: (price) -5
    Expected output: Error: please enter a double
    Actual output: Error: please enter a double

    6. Test: sell a stock
    Input: symbol that does not exist
    Expected output: Sorry, that investment does not exist
    Actual output: Sorry, that investment does not exist

    7. Test: sell a stock
    Input: quantity larger than available
    Expected output: investment removed from list
    Actual output: investment removed from list

    8. Test: sell a stock
    Input: quantity is equal to current amount
    Expected output: investment removed from list
    Actual output: investment removed from the list

    9. Test: update prices
    Input: nothing in list
    Expected output: There are no investments to update
    Actual output: There are no investments to update

    10. Test: update prices (current price)
    Input: hello
    Expected output: Error: please enter a double
    Actual output: Error: please enter a double

    11. Test: buy an exisiting mutual fund
    Input: no current mutual funds
    Expected output: There are no existing mutual fund investments
    Actual output: There are no existing mutual fund investments

    12. Test: get gain
    Input: no investments in list
    Expected output: There are no investments to get gain
    Actual output: There are no investments to get gain

    13. Test: get gain of a stock without updating price
    Input: one stock in list
    Expected output: -19.98
    Actual output: -19.98

    14. Test: search
    Input: no symbol, keyword or price range entered
    Expected output: prints all investments in both lists
    Actual output: prints all investments in both lists

    15. Test: search (price range)
    Input: hello
    Expected output: prompt user for price range again
    Actual output: prompt user for price range again

    16. Test: search (keyword)
    Input: heLLo  ("Hello" is in the list)
    Expected output: "Hello" list item returned
    Actual output: "Hello" list item returned

    17. Test: search (price range)
    Input: 50-  (item with price $50 in list)
    Expected output: $50 item returned
    Actual output: $50 item returned

    18. Test: search (symbol)
    Input: 80
    Expected output: nothing
    Actual output: nothing

    19. Test: search (keyword)
    Input: Hello (Hello World, Hello Miriam, Say Hellow in list)
    Expected output: Hello World, Hello Miriam
    Actual output: Hello World, Hello Miriam

    20. Test: file I/O
    Input: no command line arguments
    Expected output: creates a file called InvestmentList.txt when program is done running
    Actual output: creates a file called InvestmentList.txt when program is done running