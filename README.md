This program can saved your purchases. Sort by date, and terurn summ about all parchases in the specific currency.
Datas save to SQLite database which namad database.db and must be avalible in folder whith program.

src - located source folder
lib - located libs for project
db - located purge database if will need 


 * The program accepts the following command
 *  
 * add - insert data to DB example add yyyy-mm-dd ZZ CUR world | "world world"
 *    yyyy-mm-dd - date format, ZZ - Summ amount money, CUR - Carrency, world | "world world" - Product name
 * list - select from db all information and sort by date (has not arguments)
 * clear yyyy-mm-dd - delete data at the date
 * total CUR - return summ the all positions from db in CUR currency.
 * 
For correctly work the program must have internet connection.

In the directory "jar" is builded java program

========================================================================================
 Create by JDK in linux

 Create some directory and download to directory source files (src/ and lib/ directories must have)

 run next command from directory:

 1. mkdir jar
 2. cp -r lib/ jar
 3. javac -verbose -d jar -cp lib/gson-2.8.0.jar:lib/sqlite-jdbc-3.18.0.jar -sourcepath src  src/Purchases.java
 4. jar cfm Purchases.jar lib/MANIFEST.MF ./*
 5. rm *.class
 6. rm lib/MANIFEST.MF

for test run

 * java -jar Purchases.jar
========================================================================================

