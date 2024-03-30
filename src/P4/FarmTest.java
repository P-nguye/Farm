package P4;

//this class is similar to P3 but has more code for testing
public class FarmTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		Farm myFarm = new Farm("stat.dat");
		myFarm.getAnimals().addFirst(new Cow());
		myFarm.getAnimals().addLast(new Cow());
		myFarm.getAnimals().add(2, new Cow());
		Animal temp = myFarm.getAnimals().removeFirst();
		System.out.println("removed " + temp.getName());
		myFarm.getAnimals().set(0, new Llama());
		//you may want to test other linked list methods here
		for(Animal a: myFarm.getAnimals())
		a.setEnergy(10+Math.random()*90);
		System.out.printf("\nAvailable food before feeding: %.2f\n",
		myFarm.getAvailableFood());
		System.out.println("\nInitial list of animals:\n-------------------------");
		myFarm.printAnimals();
		System.out.println("Adding a clone of the second animal\n----------------");
		myFarm.addClone(myFarm.getAnimals().get(1)); //change
		myFarm.printAnimals();
		System.out.println("List of starving animals:\n------------");
		System.out.println(myFarm.getAnimals().getStarvingAnimals());
		System.out.println("List of hungry animals:\n------------");
		System.out.println(myFarm.getAnimals().getHungryAnimals());
		System.out.printf("Amount of food needed to feed all animals:%.2f\n",myFarm.getAnimals().getRequiredFood());
		System.out.println("\nFeeding animals:\n--------------");
		myFarm.feedAnimals();
		System.out.printf("\nAvailable food after feeding: %.2f\n",
		myFarm.getAvailableFood());
		System.out.println("\nFarm summary:\n--------------");
		myFarm.printSummary();
		myFarm.exit("stat.dat");
		/*
		 * Q: Why number of animals increase every time we run FarmTest?
		 * A: we are adding a CLONE every time and at the end we SAVE
		 * the next time we LOAD the previous list of animals and add another clone and so on. 
		 */
	}
}
//  We need to delete the previous stat.dat file because that file stores the list of animals as an array and not an AnimalList which we created.So if we try to read it now when we have made changes to the farm class to store animals in an AnimalList we will get an error.
