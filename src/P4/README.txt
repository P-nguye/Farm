Starter code:
	The starter code here is the solution to P3. 
	Compared to previous version:
	- Animal: now it is Serializable (to allow saving)
	- Farm: 
	  	- new method exit: saves farm data to a file 
	  	- new method load: loads farm data from a file. if file not found, use default values
  	  	- minor change to constructor: on creating a farm, call load()
  	- Cow,Chicken,Llama: no changes

Required: 
	1) create a custom linked list called AnimalList
	2) use AnimalList instead of standard arrays in Farm and make necessary changes
	3) Test code