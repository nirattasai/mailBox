# Work Update #

### Week 1 ###

Creat reposity
	
### Week 2 ###

Add maven to project
	
### Week 3 ###

Add admin page  
Add Login UI  
Add staff page  
	
### Week 4 ###

Add user.csv to keep user to login  
Add feature add staff to admin  
Write staff detail to staff.csv  
Add interface allFunction  
	
### Week 5 ###

Add staff.csv to keep staff detail  
Add getLog to get log from staff login  
Add StaffInterface add method   
Fixed bug when login then log lost  
Admin can change password  
Create acountClass and staffClass  
Add alertbox when login failed  
Admin can blocked unblocked and check staff's status  
Add counttime to check when blocked-staff login  
Add roomerPage and staffPage  
Update GUI  
	
### Week 6 ###

Fixed bug admin change password  
Edit format User.csv  
Edit interface  
Add staff to TableView  
Admin can upload image of staff  
Staff can AddRoom CheckRoom AddResident  
Send username's staff between scene  
Staff can change password  
Add mailBox  
Add Item  
Add item.csv  
Staff can AddItem to mailBox,Clear mailBox  
Build jarfile  
Add staff Picture    
Update GUI  
Update history itembox  
Edit Class Letter.java to add ResidentReceived  

### Week 7 ###

Fixed some bugs  
Update textDialog  
Add search room number in mailbox  
Add sort mailbox by roomNumber and by item  
Edit GUI of room detail  
Add Remove residents  
Update room number form  
Add residents account  
Resident show item and change password  
Add manual  
Add UML DIAGRAM  
	
# Directory Structure #
```
-CSV 											(Keep csv file in intelliJ)  
-Mailbox Manage QWERTY	 						(Jar file build)  
	|-CSV 										(Keep csv file to use in jar file)  
	|-image 									(Keep image of item and staff)  
	    |-staff									(Staff's picture)  
		|-Letter								(Letter's picture)  
		|-Package								(Package's picture)  
		|-Document								(Document's picture)  
		|-6210407455.pdf						(Handbook)  
-src											(Keep source code of project)  
	|-main  
		|-java  
			|-Final								(Contain main)  
				|-Controller					(Contain controller of program)  
					|-Account					(Contain Account class)  
					|-AdminController			(Contain AdminPage's controller)  
					|-Building					(Contain Room Class)  
					|-Item 						(Contain Item class ex. letter, document, mailbox, package)  
					|-ResidentController		(Contain Controller of resident page)  
					|-StaffController			(Contain StaffPage's controller)  
						|-Mailbox				(Contain Controller of mailbox page)  
							|-HistoryItemDetail	(Contain Controller of item in history page)  
							|-ItemDetail		(Contain Controller of item in mailbox page)  
		|-resource								(Contain all FXML scene)  
			|-css								(Contain css file)  
			|-Elements							(Contain picture embeded in jar file)
```
# How to use #

* Double click program to run
* Run through terminal use command `java -jar 6210407455.jar`
* Read handbook first.
	
# User test #

admin username : admin  
admin password : admin  
staff username : staff  
staff password : staff  
resident username : roomer  
resident password : roomer