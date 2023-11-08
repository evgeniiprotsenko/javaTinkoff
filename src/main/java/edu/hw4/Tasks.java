package edu.hw4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tasks {

    //    Задача 1
    //    Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public List<Animal> getSmallToBig(List<Animal> input) {

        return input.stream().sorted(new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return Integer.compare(o1.height(), o2.height());
            }
        }).collect(Collectors.toList());
    }

    //    Задача 2
    //    Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public List<Animal> getHeavyToLite(List<Animal> input) {
        return input.stream().sorted(new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return Integer.compare(o2.weight(), o1.weight());
            }
        }).collect(Collectors.toList());
    }

    //    Задача 3
    //    Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public Map<Animal.Type, Integer> countDifferentType(List<Animal> input) {
        return input.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    //    Задача 4
    //    У какого животного самое длинное имя -> Animal
    public Animal mostLongName(List<Animal> input) {
        return input.stream().max(Comparator.comparingInt(o -> o.name().length())).get();
    }

    //    Задача 5
    //    Каких животных больше: самцов или самок -> Sex
    public Animal.Sex maleOrFemale(List<Animal> input) {
        return input.stream().collect(Collectors.groupingBy(Animal::sex))
            .entrySet().stream().max(Comparator.comparing(a -> a.getValue().size())).map(Map.Entry::getKey)
            .orElse(null);
    }

    //    Задача 6
    //    Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public Map<Animal.Type, Animal> mostWeightOfEveryType(List<Animal> input) {
        return input.stream().collect(Collectors.toMap(
            Animal::type,
            Function.identity(),
            (a1, a2) -> (a1.weight() > a2.weight() ? a1 : a2)
        ));
    }

    //    Задача 7
    //    K-е самое старое животное -> Animal
    public Animal kthOldestAnimal(List<Animal> input, int k) {
        if (k >= input.size() || k < 0) {
            return null;
        }
        return input.stream().sorted(Comparator.comparingInt(Animal::age)).toList().get(input.size() - 1 - k);
    }

    //    Задача 8
    //    Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public Optional<Animal> heaviestAmongLowerKCm(List<Animal> input, int k) {
        return input.stream().filter(animal -> animal.height() < k).max(Comparator.comparing(Animal::weight));
    }

    //    Задача 9
    //    Сколько в сумме лап у животных в списке -> Integer
    public Integer countOfPaws(List<Animal> input) {
        return input.stream().mapToInt(Animal::paws).sum();
    }

    //    Задача 10
    //    Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public List<Animal> ageIsNotPaws(List<Animal> input) {
        return input.stream().filter(animal -> animal.age() != animal.paws()).collect(Collectors.toList());
    }

    //    Задача 11
    //    Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    private static final int HEIGHT = 100;

    public List<Animal> canBiteAndTall(List<Animal> input) {
        return input.stream().filter(animal -> animal.bites() && animal.height() > HEIGHT).collect(Collectors.toList());
    }

    //    Задача 12
    //    Сколько в списке животных, вес которых превышает рост -> Integer
    public Integer countWeightMorThanHeight(List<Animal> input) {
        return input.stream().filter(animal -> animal.height() < animal.weight()).toList().size();
    }

    //    Задача 13
    //    Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public List<Animal> namesMoreThanTwoWords(List<Animal> input) {
        return input.stream().filter(animal -> animal.name().split(" ").length > 2).collect(Collectors.toList());
    }

    //    Задача 14
    //    Есть ли в списке собака ростом более k см -> Boolean
    public Boolean isThereADogHeightMoreThanK(List<Animal> input, int k) {
        return input.stream().anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    //    Задача 15
    //    Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public Map<Animal.Type, Integer> summaryWeightOfEveryAnimalTypeWhichAreBetweenKAndLYearsOld(
        List<Animal> input,
        int k,
        int l
    ) {
        return input.stream().filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    //    Задача 16
    //    Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public List<Animal> sortedByTypeSexName(List<Animal> input) {
        return input.stream()
            .sorted(Comparator.comparing((Animal animal) -> animal.type().name())
                .thenComparing((Animal animal) -> animal.sex().name())
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    //    Задача 17
    //    Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)

    //    Как я понял, вопрос состоит в том, чтобы узнать, у кого выше % "кусаемости"
    public Boolean spidersBitesMoreThanDogs(List<Animal> input) {

        if (input.stream().filter(animal -> animal.type().equals(Animal.Type.DOG)).toList().isEmpty()
            && !input.stream().filter(animal -> animal.type().equals(Animal.Type.SPIDER)).filter(Animal::bites).toList()
            .isEmpty()) {
            return true;
        }

        return
            (double) input.stream()
                .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
                .count()
                /
                (double) input.stream()
                    .filter(animal -> animal.type() == Animal.Type.SPIDER)
                    .count()
                >
                (double) input.stream()
                    .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
                    .count()
                    /
                    (double) input.stream()
                        .filter(animal -> animal.type() == Animal.Type.DOG)
                        .count();
    }

    //    Задача 18
    //    Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    public Animal findHeaviestFish(List<List<Animal>> input) {
        return input.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH))
            .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    //    Задача 19
    //    Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
    //    Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.

    public enum ValidationError {
        INVALID_AGE,
        INVALID_HEIGHT,
        INVALID_WEIGHT
    }

    private static final int MIN = 0;

    private Set<ValidationError> errorCheck(Animal animal) {
        Set<ValidationError> set = new HashSet<>();
        if (animal.age() < MIN) {
            set.add(ValidationError.INVALID_AGE);
        }
        if (animal.height() < MIN) {
            set.add(ValidationError.INVALID_HEIGHT);
        }
        if (animal.weight() < MIN) {
            set.add(ValidationError.INVALID_WEIGHT);
        }
        return set;
    }

    public Map<String, Set<ValidationError>> findMistakes(List<Animal> input) {
        return input.stream()
            .filter(animal -> !errorCheck(animal).isEmpty())
            .collect(Collectors.toMap(Animal::name, animal -> errorCheck(animal)));
    }

    //    Задача 20
    //    Сделать результат предыдущего задания более читабельным:
    //    вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
    public Map<String, String> findMistakesChanged(List<Animal> input) {
        return input.stream()
            .filter(animal -> !errorCheck(animal).isEmpty())
            .collect(Collectors.toMap(Animal::name, animal -> errorCheck(animal).stream().map(Enum::name)
                .sorted()
                .collect(Collectors.joining(", "))));
    }

}
