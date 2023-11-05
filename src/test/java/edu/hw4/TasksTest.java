package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class TasksTest {

    private final static List<Animal> input = List.of(
        new Animal("Котик", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
        new Animal("Пёсик", Animal.Type.DOG, Animal.Sex.M, 6, 35, 15, true),
        new Animal("Птенчик", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false),
        new Animal("Котек", Animal.Type.CAT, Animal.Sex.M, 4, 24, 11, false),
        new Animal("Рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, true),
        new Animal("Паучок", Animal.Type.SPIDER, Animal.Sex.F, 3, 7, 3, true)
    );

    private final static List<List<Animal>> input2 = List.of(
        List.of(
            new Animal("Рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 7, true),
            new Animal("Паучок", Animal.Type.SPIDER, Animal.Sex.F, 3, 7, 1, true)
        ),
        List.of(
            new Animal("Рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 4, true),
            new Animal("Пёсик", Animal.Type.DOG, Animal.Sex.M, 6, 35, 15, true)
        ),
        List.of(
            new Animal("Котик", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 3, true)
        )
    );

    Tasks tasks = new Tasks();

    @Test
    void getSmallToBig() {
        List<Animal> expected = List.of(
            input.get(4),
            input.get(5),
            input.get(2),
            input.get(3),
            input.get(0),
            input.get(1)
        );
        List<Animal> result = tasks.getSmallToBig(input);
        assertEquals(expected, result);
    }

    @Test
    void getHeavyToLite() {
        List<Animal> expected = List.of(
            input.get(1),
            input.get(3),
            input.get(0),
            input.get(5),
            input.get(2),
            input.get(4)
        );
        List<Animal> result = tasks.getHeavyToLite(input);
        assertEquals(expected, result);
    }

    @Test
    void countDifferentType() {
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.CAT, 2,
            Animal.Type.DOG, 1,
            Animal.Type.BIRD, 1,
            Animal.Type.FISH, 1,
            Animal.Type.SPIDER, 1
        );
        Map<Animal.Type, Integer> result = tasks.countDifferentType(input);
        assertEquals(expected, result);
    }

    @Test
    void mostLongName() {
        Animal expected = input.get(2);
        Animal result = tasks.mostLongName(input);
        assertEquals(expected, result);
    }

    @Test
    void maleOrFemale() {
        Animal.Sex expected = Animal.Sex.M;
        Animal.Sex result = tasks.maleOrFemale(input);
        assertEquals(expected, result);
    }

    @Test
    void mostWeightOfEveryType() {
        Map<Animal.Type, Animal> expected = Map.of(
            Animal.Type.CAT, input.get(3),
            Animal.Type.DOG, input.get(1),
            Animal.Type.BIRD, input.get(2),
            Animal.Type.FISH, input.get(4),
            Animal.Type.SPIDER, input.get(5)
        );
        Map<Animal.Type, Animal> result = tasks.mostWeightOfEveryType(input);
        assertEquals(expected, result);
    }

    @Test
    void kthOldestAnimal() {
        Animal expected = input.get(1);
        Animal result = tasks.kthOldestAnimal(input, 0);
        assertEquals(expected, result);
    }

    @Test
    void heaviestAmongLowerKCm() {
        Optional<Animal> expected = Optional.of(input.get(5));
        Optional<Animal> result = tasks.heaviestAmongLowerKCm(input, 10);
        assertEquals(expected, result);
    }

    @Test
    void countOfPaws() {
        int expected = 22;
        int result = tasks.countOfPaws(input);
        assertEquals(expected, result);
    }

    @Test
    void ageIsNotPaws() {
        List<Animal> expected = List.of(
            input.get(0),
            input.get(1),
            input.get(4),
            input.get(5)
        );
        List<Animal> result = tasks.ageIsNotPaws(input);
        assertEquals(expected, result);
    }

    @Test
    void canBiteAndTall() {
        List<Animal> expected = new ArrayList<>();
        List<Animal> result = tasks.canBiteAndTall(input);
        assertEquals(expected, result);
    }

    @Test
    void countWeightMorThanHeight() {
        int expected = 0;
        int result = tasks.countWeightMorThanHeight(input);
        assertEquals(expected, result);
    }

    @Test
    void namesMoreThanTwoWords() {
        List<Animal> localInput = List.of(
            input.get(0),
            input.get(1),
            new Animal("А Б В", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false)
        );

        List<Animal> expected = List.of(new Animal("А Б В", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false));
        List<Animal> result = tasks.namesMoreThanTwoWords(localInput);
        assertEquals(expected, result);
    }

    @Test
    void isThereADogHeightMoreThanK() {
        boolean expected = true;
        boolean result = tasks.isThereADogHeightMoreThanK(input, 34);
        assertEquals(expected, result);
    }

    @Test
    void summaryWeightOfEveryAnimalTypeWhichAreBetweenKAndLYearsOld() {
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.CAT, 21,
            Animal.Type.SPIDER, 3
        );
        Map<Animal.Type, Integer> result =
            tasks.summaryWeightOfEveryAnimalTypeWhichAreBetweenKAndLYearsOld(input, 3, 5);
        assertEquals(expected, result);
    }

    @Test
    void sortedByTypeSexName() {
        List<Animal> expected = List.of(
            input.get(2),
            input.get(3),
            input.get(0),
            input.get(1),
            input.get(4),
            input.get(5)
        );
        List<Animal> result = tasks.sortedByTypeSexName(input);
        assertEquals(expected, result);
    }

    @Test
    void spidersBitesMoreThanDogs() {
        List<Animal> localInput = List.of(
            input.get(2),
            input.get(3),
            input.get(0),
            input.get(1),
            input.get(4),
            input.get(5),
            new Animal("А Б В", Animal.Type.DOG, Animal.Sex.M, 2, 10, 2, false)
        );
        boolean expected = true;
        boolean result = tasks.spidersBitesMoreThanDogs(localInput);
        assertEquals(expected, result);
    }

    @Test
    void findHeaviestFish() {
        Animal expected = input2.get(0).get(0);
        Animal result = tasks.findHeaviestFish(input2);
        assertEquals(expected, result);
    }

    @Test
    void findMistakes() {
        List<Animal> localInput = List.of(
            input.get(2),
            input.get(3),
            input.get(0),
            new Animal("А Б В", Animal.Type.DOG, Animal.Sex.M, -2, -10, -2, false)
        );
        Map<String, Set<Tasks.ValidationError>> expected = Map.of(
            "А Б В",
            Set.of(Tasks.ValidationError.INVALID_AGE,
                Tasks.ValidationError.INVALID_HEIGHT,
                Tasks.ValidationError.INVALID_WEIGHT
            )
        );
        Map<String, Set<Tasks.ValidationError>> result = tasks.findMistakes(localInput);
        assertEquals(expected, result);
    }

    @Test
    void findMistakesChanged() {
        List<Animal> localInput = List.of(
            input.get(2),
            input.get(3),
            input.get(0),
            new Animal("А Б В", Animal.Type.DOG, Animal.Sex.M, -2, -10, -2, false)
        );
        Map<String, String> expected = Map.of(
            "А Б В", "INVALID_AGE, INVALID_HEIGHT, INVALID_WEIGHT"
        );
        Map<String, String> result = tasks.findMistakesChanged(localInput);
        assertEquals(expected, result);
    }
}
