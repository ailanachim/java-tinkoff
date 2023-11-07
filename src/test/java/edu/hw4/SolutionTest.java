package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolutionTest {

    private final Animal cat = new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 2, 30, 3000, true);
    private final Animal dog = new Animal("Dara", Animal.Type.DOG, Animal.Sex.F, 5, 70, 10000, true);
    private final Animal bird = new Animal("Sparrow", Animal.Type.BIRD, Animal.Sex.M, 4, 20, 100, false);
    private final Animal fish = new Animal("Salmon", Animal.Type.FISH, Animal.Sex.M, 3, 8, 300, false);
    private final Animal spider = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 10, true);

    @Test
    void task1Test() {
        assertThat(Solution.sortByHeight(List.of(cat, dog, bird, fish, spider))).isEqualTo(List.of(
            spider,
            fish,
            bird,
            cat,
            dog
        ));
    }

    @Test
    void task2Test() {
        assertThat(Solution.kMaxByWeight(List.of(cat, dog, bird, fish, spider), 1)).isEqualTo(List.of(dog));
        assertThat(Solution.kMaxByWeight(List.of(cat, dog, bird, fish, spider), 2)).isEqualTo(List.of(dog, cat));
        assertThat(Solution.kMaxByWeight(List.of(cat, dog, bird, fish, spider), 3)).isEqualTo(List.of(dog, cat, fish));
        assertThat(Solution.kMaxByWeight(List.of(cat, dog, bird, fish, spider), 4)).isEqualTo(List.of(
            dog,
            cat,
            fish,
            bird
        ));
        assertThat(Solution.kMaxByWeight(List.of(cat, dog, bird, fish, spider), 5)).isEqualTo(List.of(
            dog,
            cat,
            fish,
            bird,
            spider
        ));
    }

    @Test
    void task3Test() {
        Animal cat1 = new Animal("Murka", Animal.Type.CAT, Animal.Sex.F, 1, 27, 1800, true);
        assertThat(Solution.countForEachType(List.of(cat, dog, bird, fish, spider, cat1))).isEqualTo(Map.of(
            Animal.Type.CAT, 2,
            Animal.Type.DOG, 1,
            Animal.Type.SPIDER, 1,
            Animal.Type.BIRD, 1,
            Animal.Type.FISH, 1
        ));
    }

    @Test
    void task4Test() {
        assertThat(Solution.maxByNameLength(List.of(cat, dog, bird, fish, spider))).isEqualTo(bird);
    }

    @Test
    void task5Test() {
        assertThat(Solution.mostCommonSex(List.of(cat, dog, bird, fish, spider))).isEqualTo(Animal.Sex.M);
    }

    @Test
    void task6Test() {
        Animal cat1 = new Animal("Murka", Animal.Type.CAT, Animal.Sex.F, 1, 27, 2400, true);
        Animal dog1 = new Animal("Sharik", Animal.Type.DOG, Animal.Sex.M, 4, 60, 6500, true);
        Animal bird1 = new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.F, 10, 40, 1700, false);
        Animal fish1 = new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 250, false);
        Animal spider1 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 15, true);

        assertThat(Solution.maxByWeightForEachType(List.of(
            cat,
            dog,
            bird,
            fish,
            spider,
            cat1,
            dog1,
            bird1,
            fish1,
            spider1
        ))).isEqualTo(
            Map.of(
                Animal.Type.CAT, Optional.of(cat),
                Animal.Type.DOG, Optional.of(dog),
                Animal.Type.SPIDER, Optional.of(spider1),
                Animal.Type.BIRD, Optional.of(bird1),
                Animal.Type.FISH, Optional.of(fish)
            ));
    }

    @Test
    void task7Test() {
        assertThat(Solution.kThMaxByAge(List.of(cat, dog, bird, fish, spider), 1)).isEqualTo(dog);
        assertThat(Solution.kThMaxByAge(List.of(cat, dog, bird, fish, spider), 2)).isEqualTo(bird);
        assertThat(Solution.kThMaxByAge(List.of(cat, dog, bird, fish, spider), 3)).isEqualTo(fish);
        assertThat(Solution.kThMaxByAge(List.of(cat, dog, bird, fish, spider), 4)).isEqualTo(cat);
        assertThat(Solution.kThMaxByAge(List.of(cat, dog, bird, fish, spider), 5)).isEqualTo(spider);
    }

    @Test
    void task8Test() {
        assertThat(Solution.maxByWeightWithHeightLessK(
            List.of(cat, dog, bird, fish, spider),
            10
        )).isEqualTo(Optional.of(fish));

        assertThat(Solution.maxByWeightWithHeightLessK(
            List.of(cat, dog, bird, fish, spider),
            40
        )).isEqualTo(Optional.of(cat));

        assertThat(Solution.maxByWeightWithHeightLessK(
            List.of(cat, dog, bird, fish, spider),
            100
        )).isEqualTo(Optional.of(dog));
    }

    @Test
    void task9Test() {
        assertThat(Solution.sumOfPaws(List.of(cat, dog, bird, fish, spider))).isEqualTo(18);
    }

    @Test
    void task10Test() {
        Animal cat1 = new Animal("Murka", Animal.Type.CAT, Animal.Sex.F, 4, 27, 2400, true);
        assertThat(Solution.hasNotEqualAgeAndPaws(List.of(cat, dog, bird, fish, spider, cat1))).isEqualTo(List.of(
            cat,
            dog,
            bird,
            fish,
            spider
        ));
    }

    @Test
    void task11Test() {
        Animal dog1 = new Animal("Sharik", Animal.Type.DOG, Animal.Sex.M, 4, 120, 6500, true);
        assertThat(Solution.bitesAndHigher100(List.of(cat, dog, bird, fish, spider, dog1)))
            .isEqualTo(List.of(dog1));
    }

    @Test
    void task12Test() {
        Animal spider1 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 1, 20, 15, true);
        assertThat(Solution.countWhereWeightMoreHeight(List.of(cat, dog, bird, fish, spider, spider1))).isEqualTo(5);
    }

    @Test
    void task13Test() {
        Animal cat1 = new Animal("The Smelly cat", Animal.Type.CAT, Animal.Sex.F, 1, 27, 2400, true);
        assertThat(Solution.hasMoreThanTwoWordsName(List.of(
            cat,
            dog,
            bird,
            fish,
            spider,
            cat1
        ))).isEqualTo(List.of(cat1));
    }

    @Test
    void task14Test() {
        assertThat(Solution.containsDogHigherK(List.of(cat, dog, bird, fish, spider), 50)).isEqualTo(true);
        assertThat(Solution.containsDogHigherK(List.of(cat, dog, bird, fish, spider), 100)).isEqualTo(false);
    }

    @Test
    void task15Test() {
        assertThat(Solution.sumOfWeightWhereAgeBetweenKLForEachType(List.of(cat, dog, bird, fish, spider), 2, 4))
            .isEqualTo(Map.of(
                    Animal.Type.CAT, cat.weight(),
                    Animal.Type.BIRD, bird.weight(),
                    Animal.Type.FISH, fish.weight()
                )
            );
    }

    @Test
    void task16Test() {
        Animal cat1 = new Animal("Murka", Animal.Type.CAT, Animal.Sex.F, 1, 27, 2400, true);
        Animal dog1 = new Animal("Sharik", Animal.Type.DOG, Animal.Sex.M, 4, 60, 6500, true);
        Animal bird1 = new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 10, 40, 1700, false);
        Animal fish1 = new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 250, false);
        Animal spider1 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 15, true);

        assertThat(Solution.sortByTypeSexName(
            List.of(
                cat,
                dog,
                bird,
                fish,
                spider,
                cat1,
                dog1,
                bird1,
                fish1,
                spider1
            ))).isEqualTo(List.of(
            cat,
            cat1,
            dog1,
            dog,
            bird1,
            bird,
            fish,
            fish1,
            spider,
            spider1
        ));
    }

    @Test
    void task17Test() {
        Animal dog1 = new Animal("Sharik", Animal.Type.DOG, Animal.Sex.M, 4, 60, 6500, false);
        Animal spider1 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 1, 20, 15, false);

        assertThat(Solution.spidersBiteMoreOftenThanDogs(List.of(cat, dog, bird, fish, spider))).isEqualTo(false);
        assertThat(Solution.spidersBiteMoreOftenThanDogs(List.of(cat, dog, bird, fish, spider, dog1))).isEqualTo(true);
        assertThat(Solution.spidersBiteMoreOftenThanDogs(List.of(cat, dog, bird, fish, spider, spider1))).isEqualTo(
            false);
    }

    @Test
    void task18Test() {
        Animal fish1 = new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 10, 250, false);
        Animal fish2 = new Animal("Dori", Animal.Type.FISH, Animal.Sex.F, 3, 15, 400, false);

        List<Animal> list1 = List.of(cat, dog, bird, fish, spider);
        List<Animal> list2 = List.of();
        List<Animal> list3 = List.of(fish1, cat, fish2);
        List<Animal> list4 = List.of(dog);

        assertThat(Solution.fishWithMaxWeight(list1, list2, list3, list4)).isEqualTo(fish2);
    }

    @Test
    void task19Test() {
        Animal cat1 = new Animal("Murka", Animal.Type.CAT, Animal.Sex.F, -1, 27, 2400, true);
        Animal dog1 = new Animal("Sharik", Animal.Type.DOG, Animal.Sex.M, 4, -60, 6500, true);
        Animal bird1 = new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 10, 40, -1700, false);
        Animal fish1 = new Animal("   ", Animal.Type.FISH, Animal.Sex.F, 1, 10, 250, false);
        Animal spider1 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 15, true);

        Map<String, Set<ValidationError>> errors = Map.of(
            "Murka", Set.of(new ValidationError("age is negative")),
            "Sharik", Set.of(new ValidationError("height is negative")),
            "Parrot", Set.of(new ValidationError("weight is negative")),
            "   ", Set.of(new ValidationError("name is blank")),
            "Spider2", Set.of()
        );

        assertThat(Solution.getValidationErrors(List.of(cat1, dog1, bird1, fish1, spider1))).isEqualTo(errors);
    }

    @Test
    void task20Test() {
        Map<String, Set<ValidationError>> errors = Map.of(
            "Murka", Set.of(new ValidationError("age is negative"), new ValidationError("height is negative")),
            "Sharik", Set.of(),
            "   ", Set.of(new ValidationError("name is blank"))
        );

        Map<String, String> result = Solution.transformErrorsToString(errors);

        assertThat(result.get("Murka")).isIn(
            "age is negative, height is negative",
            "height is negative, age is negative"
        );
        assertThat(result.get("Sharik")).isEqualTo("");
        assertThat(result.get("   ")).isEqualTo("name is blank");
    }
}
