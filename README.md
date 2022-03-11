<h1>The Parliament of Finland Android application</h1>

The operating principle of the application is to retrieve the information of a specific Member of Parliament according to the parliamentary parties.

The information is retrieved from the parliamentary database and the application automatically updates the database to the SQLite Room database, 
which is automatically updated if there are updates to the database.

The architecture of the application was implemented using four views.
The first view with a picture of a Finnish lion and a button to move to the next view, where the parties are listed.


The list views were implemented using Android’s ViewModel, RecyclerView, and its adapter.
The images of the parties are uploaded to the drawable folder of the application, where each party gets the correct image and name in the right line.

In the Party List view, the user can select the party from which the user is directed to the party list view.

From the list of parties, the user can navigate by clicking on the name of a specific parliament, which opens the parliament info screen.
The information screen generally retrieves information about the user's name, constituency, party, and age.
