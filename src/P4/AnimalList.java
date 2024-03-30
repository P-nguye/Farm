package P4;
import java.util.*;
import java.io.*;
public class AnimalList implements Iterable<Animal>, Serializable{
	private int size;
	AnimalNode head = null;
	AnimalNode tail = null;
	
	
	
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0 ;
	}
	public void addFirst(Animal animal) {
		if (isEmpty()) {
			
			head = tail = new AnimalNode(animal);
		}else {
			AnimalNode newNode = new AnimalNode(animal);
			newNode.next = head;
			head = newNode;
		}
		size++;
		
	}
	public void addLast(Animal animal) {
		if (isEmpty() ) {
			head = tail = new AnimalNode(animal);
		}else {
			AnimalNode newNode = new AnimalNode(animal);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		
		
	}
	public void add(int index, Animal animal) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}else if (index == 0){
			addFirst(animal);
		}else if (index == size ) {
			addLast(animal);
		}else {
			AnimalNode newNode = new AnimalNode(animal);
			AnimalNode previous = head;
			for (int i = 0 ; i < index - 1 ; i++) {
				previous = previous.next;
			}
			newNode.next = previous.next;
			previous.next = newNode;
			size++;
		}
	}
	public Animal removeFirst() {
		if (size == 0 ) {
			return null;
		}else if (size == 1) {
			AnimalNode temp = head;
			head = tail = null;
			size--;
			return temp.animal;
			
		}else {
			AnimalNode temp = head;
			head = head.next;
			size--;
			return temp.animal;
			
			
		}
	}
	public Animal removeLast() {
		if (isEmpty() ) {
			return null;
		}else if (size == 1) {
			return removeFirst();
		}else {
			AnimalNode temp = tail;
			AnimalNode previous = head;
			for (int i = 0 ; i <  size - 2; i++) {
				previous = previous.next;
			}
			tail = previous;
			previous.next = null;
			size--;
			return temp.animal;
			
		}
	}
	public Animal remove(int index  ) {
		if (index < 0 || index > (size - 1)) {
			throw new IndexOutOfBoundsException();
		}else if (index  == 0) {
			return removeFirst();	
		}else if (index == (size -1 )) {
			return removeLast();
		}else {
			AnimalNode previous = head;
			for (int i = 0 ; i < index -1 ; i++) {
				previous = previous.next;
			}
			AnimalNode temp = previous.next;
			previous.next = temp.next;
			size--;
			return temp.animal;
			
			
		}
		
	}
	public Animal getFirst() {
		if (isEmpty()) {
			return null;
		}else {
			return head.animal;
		}
	}
	public Animal getLast() {
		if (isEmpty()) {
			return null;
		}else {
			return tail.animal;
		}
	}
	public Animal get(int index) {
		if (index  < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}else if (index == 0 ){
			return getFirst();
		}else if (index == (size -1 )) {
			return getLast();
		}else {
			AnimalNode previous = head;
			for (int i = 0 ; i < index - 1; i++) {
				previous = previous.next;
			}
			return previous.next.animal;
		}
	}
	public Animal set(int index, Animal animal) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}else if (index ==  0 ) {
			Animal temp = head.animal;
			head.animal = animal;
			return temp;
		}
		else {
			AnimalNode previous = head;
			for (int i = 0 ; i < index - 1; i++) {
				previous = previous.next;
			}
			Animal temp = previous.next.animal;  
			previous.next.animal = animal;
			return temp;
		}
	}
	public String toString() {
		if(isEmpty()) {
			return null;
		}else {
			StringBuilder s = new StringBuilder();
			AnimalNode curr = head;
			for (int i = 0 ; i < size ; i++ ) {
				s.append(curr.animal.toString());
				s.append("\n");
				curr = curr.next;
				
			}
			return s.toString();
			
		}
	}
	public Iterator<Animal> iterator(){
		return new MyIterator();
		
	}
	public AnimalList getHungryAnimals() {
		AnimalList x = new AnimalList();
		Iterator<Animal> s = this.iterator();
		while(s.hasNext()) {
			Animal anim = s.next();
			if (anim.getEnergy() < 50  ) {
				x.addLast(anim);
				
			}
		}
		if (x.size == 0)
			return null;
		else
			return x;
		
		
	}
	public AnimalList getStarvingAnimals() {
		AnimalList x = new AnimalList();
		Iterator<Animal> s = this.iterator();
		while(s.hasNext()) {
			Animal anim = s.next();
			if (anim.getEnergy() < 17  ) {
				x.addLast(anim);
				
			}
		}
		if (x.size == 0)
			return null;
		else
			return x;

		
	}
	public AnimalList getAnimalsInBarn() {
		AnimalList barn = new AnimalList();
		Iterator<Animal> it = this.iterator();
		while(it.hasNext()) {
			Animal s = it.next();
			if ((s.getX() > 450 && s.getX() < 550) && (s.getY() > 50 && s.getY() < 150) ) {
				barn.addLast(s );
			}
			
		}
		if (barn.size == 0) {
			return null;
		}else {
			return barn;
		}
		
	}
	public double getRequiredFood() {
		double sum = 0 ;
		Iterator<Animal> it = this.iterator();
		while(it.hasNext()) {
			sum += 100 - it.next().getEnergy();
		}
		return sum;
	}

	public AnimalList getByType(Class c) {
		AnimalList ret = new AnimalList();
		Iterator<Animal> it = this.iterator();
		while (it.hasNext()) {
			Animal anim = it.next();
			if (anim.getClass() == c) {
				ret.addLast(anim);

			}

		}
		if (ret.size() == 0) {
			return null;
		} else {
			return ret;
		}

	}
	
	
	class AnimalNode implements Serializable{
		Animal animal;
		AnimalNode next;
		
		public AnimalNode(Animal animal) {
			this.animal = animal;
		}
	}
	class MyIterator implements Iterator<Animal>{
		private AnimalNode node = head;
		
		public boolean hasNext() {
			if (node != null)
				return true;
			else
				return false;
		}


		public Animal next() {
			Animal temp = node.animal;
			node = node.next;
			return temp;
			
		}
		
	}

}
