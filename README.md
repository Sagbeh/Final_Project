# Final_Project
Record Store Program
This program allows users to manage a record store.  The user can add consignors, 
search for consignors, add record albums, search for record albums, sell records, keep records of sales, view payments
to consignors, and more.  It uses a java GUI so to make using the program easier and requires a blank mysql database.
The database should be called "RecordStore".  A user should be created for the database database with all privilages.

Create database RecordStore;

create user 'username'@'localhost'
identified by 'password';
Replace 'username' with your own name
Replace 'password' with the password of your choice
Use a unique password, different to the root user

grant all privileges on RecordStore.* to 'user'@'localhost'

This will allow your user to use the event scheduler
GRANT SUPER ON *.* TO 'user'@'localhost' IDENTIFIED BY 'password';

Replace the user/password in the DB class file with your user and password

There's an issue when using the invoices menu where after totaling the payments for a driver.  Not all the columns get populated so you see sql errors.  The code still runs but it's just not as pretty. 
