# Pokemon-Database
This program allows you to view all the original 151 Kanto Pokemon along with their attributes. 

HOW TO RUN:
First download the 3TierArchitecture folder and all of its contents. Then go into the src and run the 'DatabaseGUI' into Eclipse.
Now you should be able to see the database and play with it as you please!
Here is a demo of the program: ![Pokedex-demo](https://user-images.githubusercontent.com/59658246/93866990-a5c19a80-fc85-11ea-89fb-9ec486b06ed9.gif)

HOW IT WORKS:
The Pokedex GUI is designed so that you can visualize and manipulate the data without having to learn SQL. There are four main functions
of the program: Add, import, modify, delete, and join. These all have to do with the SQL queries that do the same thing. You may notice
that there are two sets of these methods, and that's because they modify different databases. The first set of methods are for modifying
the data that contains the Pokemon, while the one on the bottom changes the data for the moves.

The Add function: This adds a new Pokemon or move (again depending on which button you press). There are specific dialogues for each of these
that require a certain kind of input so that you can't break the system (e.g., Having the same 2 types for one Pokemon).
![Add-function-demo](https://user-images.githubusercontent.com/59658246/93865067-056a7680-fc83-11ea-8e5f-69afdd6bf10c.gif)

The Import function: This is meant for CSV files. If you have a CSV file and want to try using it to enter bulk data, then this is for you.
Note that the CSV data must have valid parameters for it to be added into the database.
I don't want to show my file folders, but try it out for yourself!

The Modify function: This function allows you to change entries within the database.
![Modify-function-demo](https://user-images.githubusercontent.com/59658246/93867019-b3772000-fc85-11ea-849f-90305fa81c91.gif)

The Delete function: Delete an entry. 
![Delete-function-demo](https://user-images.githubusercontent.com/59658246/93867088-cee22b00-fc85-11ea-9e24-b6ce4d3ee85b.gif)

The Join function: This method only works for the primary database (namely, the Pokemons) and allows you to join moves with Pokemon. This feature
was manely a take on the original games where you can teach your Pokemon new moves. Usually there was a 4 move limit, but this program allows you
to have as many moves as you like for your Pokemon. 
![Join-function-demo](https://user-images.githubusercontent.com/59658246/93867047-bd991e80-fc85-11ea-93bf-2e202f554936.gif)

TECHNOLOGY USED:
This program was made using SQL, Java, and Microsoft Access. SQL and Access were used to create and manipulate the
database, while Java was used to create the GUI. 

INFO ABOUT DEVELOPMENT:
This project was made under a huge time constraint and stress. At the time I was taking AP (advanced placement courses), applying
for university, and volunteering at the same time. If that wasn't enough, SQL was an entirely new language to me and Microsoft Access
was something I never knew existed. Despite these circumstances I managed to persevere and calmy organize myself to be able to 
manage my time effectively to finish this project and maintain my priorities and sanity. 

I think it was because I decided to base my project on something I loved (Pokemon) rather than something I didn't care
about (like just making a database of baseball teams) I think that motivated me the most to complete this program.

I also had a lot of help from my wonderful friends and patient teacher who I couldn't have done this project without. My friends helped
me to test the program while my teacher helped me fix the bugs. I will never forget the things they have done for me and how lucky
I was to have them!


HOW TO CONTRIBUTE:
Tell me what you think of this and if you are also a big fan of Pokemon and/or video games!
