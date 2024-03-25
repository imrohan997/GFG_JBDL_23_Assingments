import java.util.*;
import java.util.stream.Collectors;

public class Person {

    private int age;
    private String name;
    private String country;

    public Person(int age, String name, String country) {
        this.age = age;
        this.name = name;
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name;

    }


    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person(20, "John", "USA"),
                new Person(35, "Sam", "Italy"),
                new Person(15, "Jamie", "England"),
                new Person(30, "Robert", "Italy"),
                new Person(20, "James", "Ireland"),
                new Person(25, "Peter", "USA"),
                new Person(5, "Jessica", "Norway"),
                new Person(40, "Roger", "Netherlands"),
                new Person(50, "Jim", "USA")
        );

        //Q1.Print the avg age of all the people
        //Solution-
        double averageAge = people.stream().mapToInt(person -> person.getAge()).average().getAsDouble();
        System.out.println("Average Age : " + averageAge);

        //Q2.Create a list of all the people who are either greater than 20 or contain any vowel in their name (uppercase or lowercase)
        //Solution-
        List<Person> filteredList = people.stream().filter(person -> person.getAge() > 20
                || person.getName().contains("A")
                || person.getName().contains("E")
                || person.getName().contains("I")
                || person.getName().contains("O")
                || person.getName().contains("U")
                || person.getName().contains("a")
                || person.getName().contains("e")
                || person.getName().contains("i")
                || person.getName().contains("o")
                || person.getName().contains("u")).collect(Collectors.toList());

        System.out.println("Filtered List : " + filteredList);

        //Q3.Create a list of people, sorted in ascending order on the basis of age, if age is the same then sort in descending order of name
        //Solution-
        List<Person> sortedList = people.stream().sorted(Comparator.comparingInt(Person::getAge).thenComparing(Person::getName, Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("Sorted List : " + sortedList);

        //Q4.Create a map from this people list where the key is country name and value is count which means a map will tell how many people live in a particular country
        //Solution-
        Map<String, Long> map1 = people.stream().collect(Collectors.groupingBy(person -> person.getCountry(), Collectors.counting()));
        System.out.println("Map with Country and count : " + map1);

        //Q5.Create a map which stores avg age of people per country (key should be country and value should be average age i.e, double)
        //Solution-
        Map<String, Double> map2 = people.stream().collect(Collectors.groupingBy(person -> person.getCountry(), Collectors.averagingDouble(p -> p.getAge())));
        System.out.println("Map with Country and average age : " + map2);

        //Q6. Print the oldest person in every country
        //Solution-
        Map<String, Optional<Person>> oldestPersonByCountryOpt = people.stream().collect(Collectors.groupingBy(Person::getCountry, Collectors.maxBy(Comparator.comparingInt(Person::getAge))));
        Map <String, Person> oldestPersonByCountry = oldestPersonByCountryOpt.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().get()));
        System.out.println("Country with oldest age : " + oldestPersonByCountry);

        //Q7.Print the country with most people
        //Solution-
        Map<String, Long> mapWithCountryVsPeople = people.stream().collect(Collectors.groupingBy(Person::getCountry, Collectors.counting()));
        Map.Entry<String, Long> countryWithMaxPopulation = mapWithCountryVsPeople.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println("Country With maximum population  : " + countryWithMaxPopulation);

        //Q8.Create a list of 20 random integers in the range 0 - 1000 using Java 8 streams
        //Solution-
        List<Integer> numbers = new Random().ints(20, 0, 1001).boxed().collect(Collectors.toList());
        System.out.println("Random 20 numbers : " + numbers);
    }
}
