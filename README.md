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
For correctly work the program must internet connection. 
 
Create program: 
By maven

in this String will use Your groupId=xxx.xxxxx.xxx & artifactId=xxxxxx

mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

in generated folder delete the test folder in /generated_filder/src

change groupeId & artifactId in pom.xml

mvn package








