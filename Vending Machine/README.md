Uses Spring Dependency Injection with the applicationContext.xml file. 

BigDecimal for all monetary calculations.

The program displays all of the items and their respective prices when the program starts, along with an option to exit the program.
The user must put in some amount of money before an item can be selected.
Only one item can be vended at a time.
If the user selects an item that costs more than the amount the user put into the vending machine, the program displays a message indicating insufficient funds and then redisplay the amount the user had put into the machine.
If the user selects an item that costs equal to or less than the amount of money that the user put in the vending machine, the program displays the change returned to the user. 
Change displayed as the number of quarters, dimes, nickels, and pennies returned to the user.
Vending machine items stored in a file. Inventory for the vending machine read from this file when the program starts and written out to this file just before the program exits. 

Each user action is logged and stored in an audit.txt file.

The program tracks the following properties for each item:
Item name,
Item cost,
Number of items in inventory


When an item is vended, the program updates the inventory level appropriately. If the machine runs out of an item, it is no longer be available as an option to the user. However, the items that have an inventory level of zero still must be read from and written to the inventory file and stored in memory. 
