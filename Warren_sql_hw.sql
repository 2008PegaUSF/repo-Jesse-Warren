/*Jesse Warren
  08/30/2020
  Revature 
  Chinook SQL Assignment*/

/*Question 1*/

select  "EmployeeId","LastName","Email" 
from "Employee" 
where "LastName"='King';

/*here I'm clarifying the 3 fields and what table then asserting the last field will be king*/

/*Question 2*/

select "City","State" 
from "Employee" 
where "FirstName"='Andrew' 
and "ReportsTo" 
is null;

/* pulling the city and state from employee table where the firstnames value is andrew and the report to field is holding a null value */

/*Question 3*/

select * 
from "Album"
where "ArtistId" 
in (select "ArtistId"				
from "Artist"
where "Name"='AC/DC');

/*selecting every record from album where the artist id matchesd up with records in artist which also have the name ACDC*/
			
/*Question 4*/
			
select * 
from "Album" 
order by "Title" 
desc;

/*selecting everything from album, ordering it by title in descending order*/

/*Question 5*/

select "FirstName" 
from "Customer" 
order by "City" 
asc;

/*select all of the first names in customer and order them by the city field in ascending order*/

/*Question 6*/

select * 
from "Invoice" 
where "Total" 
between 15 and 50;

/*Selecting everything from the Invoice table where the total falls between 15 and 50*/

/*Question 7*/

select * 
from "Employee" 
where "HireDate" 
between '2003-06-01' and '2004-03-01';

/*Selecting everything from Employee table where the hire date falls between the designated range*/

/*Question 8*/

insert into "Genre" values (100, 'RugCuttinRagTag');
insert into "Genre" values (101, 'TagCuttingRagRug');

/*creating two new values*/

/*Question 9*/

insert into "Employee" values (4947,'Warren','Jesse','Programmer', 1,'1993-05-26','2020-08-17','1716 Albritton Rd','Columbia','SC','United States','29204','8433644938','8433644938', 'jtwarrenz@gmail.com');
insert into "Employee" values (1738,'Fetty','Wap', 'Flowmaster',4947, '2001-07-11', '2007-01-15', '100 Somewhere Steet','New York City', 'NY','United States','200020','8038675309','8038675309','mrwap@gmail.com');

/*Inserting two new entries in employee table*/

/*Question 10*/

insert into "Customer" values (350,'John','Doe','Deere','Fields Dr','Summerville','SC','29483','804555555','8045555555','john.doe@deere.com',1);
insert into "Customer" values (351,'Jane','Doe','Deere','Fields Dr','Summerville','SC','29483','804555556','8045555556','jane.doe@deere.com',1);

/*Inserting two new entries into customer*/


/*Question 11*/

update "Customer"
set "FirstName"='Robert',"LastName"='Walter'
where "FirstName"='Aaron' and "LastName"='Mitchell';

/*Updating the customer table to change aaron mitchell to robert walter*/

update "Artist"
set "Name"='CCR'
where "Name"='Creedence Clearwater Revival';

/*Updating Creedence Clearwater Revival to CCR*/


/*Question 12*/ 

alter table "Invoice" drop constraint "FK_InvoiceCustomerId";

/*dropping the foreign key constraint*/

delete from "Customer"
where "FirstName"='Robert' and "LastName"='Walter';

/*removing the customer Robert Walter*/

/*Question 13*/

select current_timestamp; 

/*displaying the current time*/ 

/*Question 14*/

select "Name","length"("Name")
as "Length of Name"
from "MediaType";

/*Showing the length of the name column*/

/*Question 15*/

select AVG("Total") as "Average Invoice Total"
from "Invoice";

/*Displaying the average of the total column from invoice*/

/*Question 16*/

select MAX("UnitPrice") as "Highest Track Price"
from "Track";

/*Displaying the Max Price from the Track Table*/

/*Question 17*/

create or replace function avgInvoice()
returns numeric 
language plpgsql
as 
$$
declare theCount int;
declare theTotal numeric;
begin 
	select count("UnitPrice") into theCount from "InvoiceLine";
	select sum("UnitPrice") into theTotal from "InvoiceLine";
return theTotal/theCount;
end
$$

/*Creating a function to return the average of the unitprice from invoiceline*/

select avgInvoice();

/*Question 18*/

create or replace function EmployeeAge()
returns setof "Employee"
language sql 
as 
$$
select * from "Employee" where "BirthDate" > '1968-12-31''00:00:00';
$$

/*a function that selects all the employees who were born after 1968*/

select EmployeeAge();


/*Question 19*/


create or replace function trigger_phone()
returns trigger
language plpgsql
as 
$$
begin
update "Employee" set "Phone" = '8675309' where "EmployeeId"=new."EmployeeId";
return new; 
end 
$$

/*a function to set the phone number on new entries to 8675309*/

create trigger "phone_numb"
after insert 
on "Employee" 
for each row
execute  function trigger_phone();

/*A trigger which after insert sets the inserted values phone number to 8675309*/

insert into "Employee" values (1739,'Fetty1','Wap1', 'Flowmaster1',4947, '2001-07-11', '2007-01-15', '100 Somewhere Steet','New York City', 'NY','United States','200020','8038675309','8038675309','mrwap@gmail.com');

select "Phone" from "Employee" where "EmployeeId"=1739;

/*Question 20*/

create or replace function comp_replace()
returns trigger 
language plpgsql
as 
$$
begin 
	if (TG_OP='INSERT') then 
	new."Company"='Revature';
	end if;
	return new;
end
$$

/* the function to insert revature as the company on new entries*/

create trigger "comp_name"
before insert 
on "Customer"
for each row 
execute function comp_replace();


/*Creating a trigger before insertion on the customer table which changes the company to Revature*/

insert into "Customer" values(120,'Jdog','Bilzerian','','101 Street','NYC','NY','USA','29209','8033334595','8033334595','jdog@gmail.com','1');

select "Company" from "Customer" where "CustomerId"=120;




/*Question 21*/

select "Customer"."FirstName", "Customer"."LastName", "Invoice"."InvoiceId" 
from "Invoice"  
inner join "Customer"  
on "Invoice"."CustomerId" = "Customer"."CustomerId";

/*Inner join on customer and invoice on the id column*/

/*Question 22*/

select "Customer"."CustomerId", "Customer"."FirstName","Customer"."LastName","Invoice"."InvoiceId","Invoice"."Total"
from "Customer"
full outer join "Invoice"
on "Customer"."CustomerId"="Invoice"."CustomerId";

/*joinging the customer and invoice table as an outer join on the id column*/

/*Question 23*/

select "Album"."Title", "Artist"."Name" 
from "Album"
right join "Artist"
on "Album"."ArtistId"="Artist"."ArtistId";

/*a right join of album and artist on the artist id column*/

/*Question 24*/

select *
from "Artist"
cross join "Album"
order by "Artist"."Name" asc;

/*a cross join on artist and album on the name column in ascending order*/

/*Question 25*/

select *
from "Employee" "A", "Employee" "B"
where "A"."ReportsTo"="B"."ReportsTo";

/*a self join on the employee table on the reportsto column*/

/*Question 26*/

select distinct "FirstName","LastName","Phone" from "Customer"
union
select distinct "FirstName","LastName","Phone" from "Employee";

/*a select statement bringing the unique info from customer into the unique info from employee, distinct will disclude duplicate info*/

/*Question 27*/

select "City","State","PostalCode" from "Customer"
except all 
select "City","State","PostalCode" from "Employee";

/*a select statement that only returns customer info that isn't contained on the employee select statement*/




