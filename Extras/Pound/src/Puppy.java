import java.lang.reflect.Array;

public class Puppy {
	private String name; private int age; private String breed;
	private static int puppyCount;
	public Puppy() {
		puppyCount++;
		name = "Puppy"+puppyCount; age = 0; breed = "Dog";
	}
	public Puppy(String name)
	{
		this();
		this.name = name;
		
	}
	public Puppy(String name, int age)
	{
		this(name);
		this.age = age;
		
	}
	public String speak()
	{
		return "Bark";
	}
	
	public String speak(int count)
	{
		String output = "";
		for(int i = 0; i<count; i++)
			output +=this.speak() + " ";
		return output;
	}
	public String speak(int count, String newSpeak)
	{
		return this.speak(count).replaceAll("Bark", newSpeak);
	}
	public String speak(String word1, String word2, int count)
	{
		return this.speak(count,word1)+this.speak(count,word2);
	}
	public String getName() {
		return name;
	}
	public Puppy setName(String name) {
		this.name = name;
		return this;
	}
	public int getAge() {
		return age;
	}
	public Puppy setAge(int age) {
		this.age = age;
		return this;
	}
	public String getBreed() {
		return breed;
	}
	public Puppy setBreed(String breed) {
		this.breed = breed;
		return this;
	}
	
	
	
}



class Pound{

	Pound(){
		Puppy[] puppies = new Puppy[10];
		for (int i = 0; i<puppies.length; i++){
			if(Math.random() < 0.5){
				puppies[i] = new Puppy();
			}
		}
		for (var puppy:puppies){
			if(puppy!=null){
				System.out.println(puppy.speak());
			}
		}
		String[] names = new String[] {"Fluffy", "Barky", "Cujo", "Mommy"};
		shuffle(names);
		int finger = 0;
		for(int i = 0; i<puppies.length; i++){
			if(puppies[i] == null)
			puppies[i] = new Puppy(names[finger++ % puppies.length]);
			// if(finger == names.length){

			// }
		}
		//(++finger - 1) == finger++
	}



	static <T> void shuffle(T[] arr){
		for (int i =0; i < arr.length; i++){
			int random = (int)(Math.random() * arr.length);
			T temp = arr[i];
			arr[i] = arr[random];
			arr[random] = temp;
			drop(temp);
		}
	}
	static <T> void drop(T t){
		t = null;
	}



}