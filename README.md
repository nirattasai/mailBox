###### Work Update ######

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
Add StaffInterface add method 	- write data to csv  
								- read csv to write data  
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
	
###### Directory Structure ######

-CSV 											(Keep csv file in intelliJ)  
-MailBox/6210407455 							(Jar file build)  
	|-CSV 										(Keep csv file to use in jar file)  
	|-image 									(Keep image of item and staff)  
-src											(Keep source code of project)  
	|-main  
		|-java  
			|-Final								(Contain main)  
				|-Controller					(Contain control program)  
					|-Account					(Contain Account class)  
					|-AdminController			(Contain AdminPage's controller)  
					|-Building					(Contain Room Class)  
					|-Item 						(Contain Item class ex. letter, document, mailbox, package)  
					|-StaffController			(Contain StaffPage's controller)  
		|-resource								(Contain all FXML scene)  
	
###### How to use ######

* Double click program to run
* Run through terminal use command `java -jar 6210407455-jar.jar`
	
###### User test ######

admin username : admin  
admin password : admin  
staff username : a  
staff password : a  