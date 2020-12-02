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
				|-account						(Contain Account class) 
				|-building						(Contain Room Class)
				|-controller					(Contain controller of program)  
					|-adminController			(Contain AdminPage's controller)    
					|-residentController		(Contain Controller of resident page)  
					|-staffController			(Contain StaffPage's controller)  
						|-mailbox				(Contain Controller of mailbox page)  
							|-historyItemDetail	(Contain Controller of item in history page)  
							|-itemDetail		(Contain Controller of item in mailbox page)  
				|-item 							(Contain Item class ex. letter, document, mailbox, package)  
		|-resource								(Contain all FXML scene)  
			|-css								(Contain css file)  
			|-elements							(Contain picture embeded in jar file)
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

# Edit First Time #

* Update UML-DIAGRAM
* Fixed close window when complete task ex. fill informations to create room, staff, add item.
* Register resident and can login. (But it's can login when I clone project to my laptop.)
* Refactor package (Change first character to lowercase, change directory of class is not controller.)
* Update UI MailBox
* Add polymorphism to project (Red Requirements)
```
	- Add polymorphism to class letter
	- Add method getType to class letter for @Override
	- Use polymorphism in class MailBoxTestController.java in method getType for show item in one table
	
	Example Code
	
		Letter Class
		public String getType(){
            return "Letter";
        }
		
		Document Class
		@Override
    	public String getType() {
        	return "Document";
    	}
		
		Package Class
		@Override
    	public String getType() {
        	return "Package";
    	}
	
		MailBoxTestController
		typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
		
```

# Edit Second Time #

* add class ItemManage
* add polymorph to project in class ItemManage in method residentReceiv()