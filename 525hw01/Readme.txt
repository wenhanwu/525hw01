Lab01: Javarmi
--------------

Names and student numbers: 
--------------------------
Jingbo Yu, 87026126
Wenhan Wu, 86573128
Haijun Qiao, 87727129

Operating system: 
-----------------
Windows 7
Mac OS X 10.8

Java compiler(version):
-----------------------
javac 1.7

Compilation instructions: 
-------------------------
Linux:
	mkdir bin
	make
Windows:
	md bin
	make or make.bat

Execution instructions:
-----------------------
Linux: 
	java -classpath ./bin -Djava.rmi.server.codebase=file:./bin/ com.server.Server&
	java -classpath ./bin com.client.Client
	java -classpath ./bin com.client.Client

Windows:
	server 
	client
	client

	or

	start java -classpath ./bin -Djava.rmi.server.codebase=file:./bin/ com.server.Server
	start java -classpath ./bin com.client.Client
	start java -classpath ./bin com.client.Client 

- A user can check stock price, buy, sell, and list the stocks she owns. 
- An administrator can list all the stocks the server is tracking, update the price of a stock. 
- The stock price is updated every two minutes by querying yahoo financial.
- The user and stock information is saved every two miniutes, and when the user logs out. 
- The server can support up to 5 users and 5 administrators online at the same time, and easy to scale to support more clients. 

Known bugs:
-----------
Not found.