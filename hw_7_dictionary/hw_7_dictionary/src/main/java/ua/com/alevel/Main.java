package ua.com.alevel;

import ua.com.alevel.Dictionary.Entry;

public class Main {
    public static void main(String[] args) {

        Dictionary<String, String> dictionary = new Dictionary<>();
        System.out.println();
        System.out.println("START TESTING DICTIONARY");
        System.out.println("_________________________________");
        System.out.println("Put 10 pair key-value");
        dictionary.put("1", "First");
        dictionary.put("2", "Second");
        dictionary.put("3", "Third");
        dictionary.put("4", "Fourth");
        dictionary.put("5", "Fifth");
        dictionary.put("6", "Sixth");
        dictionary.put("7", "Seventh");
        System.out.println("_________________________________");
        System.out.println("Check isEmpty method");
        System.out.println("My dictionary is empty? " + dictionary.isEmpty());
        System.out.println("_________________________________");
        System.out.println("Check size");
        System.out.println("Size: " + dictionary.size());
        System.out.println("_________________________________");
        System.out.println("Let`s try get some value");
        String getKeyOne = dictionary.get("1");
        String getKeyFive = dictionary.get("5");
        String getKeySeven = dictionary.get("7");
        System.out.println("Value key number 1,5,7 is " + getKeyOne + " " + getKeyFive + " " + getKeySeven);
        System.out.println("_________________________________");
        System.out.println("Check contains key ");
        System.out.println("Contains Key 1: " + dictionary.containsKey("1"));
        System.out.println("Contains Key 2: " + dictionary.containsKey("2"));
        System.out.println("Contains Key 13: " + dictionary.containsKey("13"));
        System.out.println("_________________________________");
        System.out.println("Check keySet method");
        System.out.print("Keys are: ");
        dictionary.keySet().forEach(k -> System.out.print(k + " "));
        System.out.println();
        System.out.println("_________________________________");
        System.out.println("Check Collection Values method");
        System.out.print("Values are: ");
        dictionary.values().forEach(v -> System.out.print(v + " "));
        System.out.println();
        System.out.println("_________________________________");
        System.out.println();
        System.out.println("Pair key -> value is: ");
        for (Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("_________________________________");
        System.out.println("Check method containsValue");
        System.out.println("Is dictionary contains value Fifth =>" + dictionary.containsValue("Fifth"));
        System.out.println();
        System.out.println("Is dictionary contains value Zero =>" + dictionary.containsValue("Zero"));
        System.out.println("_________________________________");
        System.out.println("Check putAll method ");
        System.out.println("Create new exemplar of class and put 3 elements");
        Dictionary<String, String> newDictionary = new Dictionary<>();
        dictionary.put("8", "Eighth");
        dictionary.put("9", "Ninth");
        dictionary.put("10", "Tenth");
        System.out.println("Put new dictionary to old one");
        dictionary.putAll(newDictionary);
        System.out.println("Check result");
        System.out.println("Pair key -> value is: ");
        for (Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("_________________________________");
        System.out.println("Check size again: " + dictionary.size());
        System.out.println("_________________________________");
        System.out.println("Check contains key again: ");
        System.out.println("Contains Key 8: " + dictionary.containsKey("8"));
        System.out.println("Contains Key 9: " + dictionary.containsKey("9"));
        System.out.println("Contains Key 13: " + dictionary.containsKey("13"));
        System.out.println("_________________________________");
        System.out.println("Check remove method ");
        System.out.println("Remove pair with key 5, 9");
        dictionary.remove("5");
        dictionary.remove("9");
        System.out.println("Check result");
        System.out.println("Pair key -> value is: ");
        for (Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("_________________________________");
        System.out.println("Check clear method ");
        dictionary.clear();
        System.out.println("Check result");
        System.out.println("Pair key -> value is: EMPTY");
        for (Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
