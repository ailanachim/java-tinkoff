package edu.hw4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private static final String ERROR_MESSAGE = "list is empty";

    private Solution() {
    }

    // task 1
    public static List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    // task 2
    public static List<Animal> kMaxByWeight(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    // task 3
    public static Map<Animal.Type, Integer> countForEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(value -> 1)));
    }

    // task 4
    public static Animal maxByNameLength(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    // task 5
    public static Animal.Sex mostCommonSex(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE))
            .getKey();
    }

    // task 6
    public static Map<Animal.Type, Optional<Animal>> maxByWeightForEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    // task 7
    public static Animal kThMaxByAge(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .limit(k)
            .min(Comparator.comparing(Animal::age))
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    // task 8
    public static Optional<Animal> maxByWeightWithHeightLessK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    // task 9
    public static Integer sumOfPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    // task 10
    public static List<Animal> hasNotEqualAgeAndPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    // task 11
    public static List<Animal> bitesAndHigher100(List<Animal> animals) {
        final int minHeight = 100;
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > minHeight)
            .toList();
    }

    // task 12
    public static Integer countWhereWeightMoreHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    // task 13
    public static List<Animal> hasMoreThanTwoWordsName(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    // task 14
    public static Boolean containsDogHigherK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    // task 15
    public static Map<Animal.Type, Integer> sumOfWeightWhereAgeBetweenKLForEachType(
        List<Animal> animals,
        int k,
        int l
    ) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    // task 16
    public static List<Animal> sortByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    // task 17
    public static Boolean spidersBiteMoreOftenThanDogs(List<Animal> animals) {
        int spiders = 0;
        int bitingSpiders = 0;
        int dogs = 0;
        int bitingDogs = 0;

        for (Animal animal : animals) {
            if (animal.type() == Animal.Type.SPIDER) {
                spiders++;
                if (animal.bites()) {
                    bitingSpiders++;
                }
            } else if (animal.type() == Animal.Type.DOG) {
                dogs++;
                if (animal.bites()) {
                    bitingDogs++;
                }
            }
        }

        double spiderProportion = (double) bitingSpiders / spiders;
        double dogProportion = (double) bitingDogs / dogs;

        return spiderProportion > dogProportion;
    }

    // task 18
    public static Animal fishWithMaxWeight(List<Animal> list1, List<Animal> list2, List<Animal>... animals) {
        Stream<Animal> stream = Stream.concat(list1.stream(), list2.stream());
        for (List<Animal> list : animals) {
            stream = Stream.concat(stream, list.stream());
        }

        return stream.filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow(() -> new IllegalArgumentException("lists don't have a fish"));
    }

    // task 19
    public static Map<String, Set<ValidationError>> getValidationErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::name, Solution::validateAnimal));
    }

    // task 20
    public static Map<String, String> transformErrorsToString(Map<String, Set<ValidationError>> errorMap) {
        return errorMap.entrySet()
            .stream()
            .map(entry -> Map.entry(entry.getKey(), entry.getValue()
                .stream()
                .map(ValidationError::message)
                .reduce(((s, s2) -> s + ", " + s2))
                .orElse("")))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ));
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal == null) {
            errors.add(new ValidationError("animal is null"));
            return errors;
        }

        if (animal.name() == null) {
            errors.add(new ValidationError("name is null"));
        } else if (animal.name().isBlank()) {
            errors.add(new ValidationError("name is blank"));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError("age is negative"));
        }

        if (animal.height() < 0) {
            errors.add(new ValidationError("height is negative"));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("weight is negative"));
        }

        return errors;
    }
}
