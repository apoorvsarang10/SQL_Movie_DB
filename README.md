# SQL_Movie_DB
Application comprises of SQL database, JDBC, Java Swing GUI

The application is similar to the rotten tomato database.
It has 4 main parts:

1.createdb.sql  : This file will create database tables and indices required for the GUI application.
2.dropdb.sql    : This file will drop the database tables & indices created by createdb.sql.
3.populate.java : This file will populate the tables with the data present in the DATA folders. Data is present in .dat format.
                  Program will first connect to the database using JDBC. Then it will pick the .dat files from DATA folder and push the                       data to the tables.
4.HW3.java      : This file generates the GUI for the movie search based on the filters.
