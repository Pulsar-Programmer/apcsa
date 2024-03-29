import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<String> animals = new ArrayList<String>(Arrays.asList("snake","snake","babycat","cat","dog","dog","dog"));
        lifeHappens(animals);
        System.out.println(animals);
    }




    public static void lifeHappens(ArrayList<String> animals) {
        
        for(int i = 0; i < animals.size() - 1; i += 1){
            //first, eliminate babies
            String elem = animals.get(i);
            if(elem.contains("baby")){
                elem = elem.substring(4);
                continue;
            }
            if(elem.equals(animals.get(i + 1))){
                animals.add(i + 1, "baby".concat(elem));
            }
        }
    }
}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * you can copy this into a driver to test  lifeHappens




Specs
write a method called lifeHappens
it takes in an ArrayList of Strings of animal names

If two same animals are next to each other then they make a "baby"
animal.
All "baby" animals will no longer be babies
THEY DON'T REPRODUCE

snake, snake, babycat, cat, dog, dog,dog

snake,babySnake,snake,cat,cat,dog,babydog,dog,babydog,dog
 */