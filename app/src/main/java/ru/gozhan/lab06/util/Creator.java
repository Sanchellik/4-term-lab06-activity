package ru.gozhan.lab06.util;

import com.yandex.mapkit.geometry.Point;

import java.util.ArrayList;
import java.util.Arrays;

import ru.gozhan.lab06.model.Sight;

public class Creator {

    public static ArrayList<Sight> createSights() {
        Point point1 = new Point(55.753930, 37.620795);
        Sight sight1 = new Sight(
                "Красная площадь",
                "Красная площадь - это знаменитая площадь в центре Москвы, являющаяся символом России. На площади расположены такие исторические и культурные достопримечательности, как Собор Василия Блаженного, Государственный исторический музей, Кремль и Национальный мавзолей. Красная площадь часто используется для проведения парадов, праздников и других крупных мероприятий.",
                point1,
                "Moskva");

        // Эрмитаж
        Point point2 = new Point(59.939095, 30.315868);
        Sight sight2 = new Sight(
                "Эрмитаж",
                "Эрмитаж - это один из крупнейших художественных и культурно-исторических музеев в мире, расположенный в Санкт-Петербурге. Он является домом для огромной коллекции произведений искусства, включая живопись, скульптуру, античные артефакты и предметы прикладного искусства. Здание Эрмитажа само по себе является архитектурным шедевром, а его коллекция включает произведения таких мастеров, как Леонардо да Винчи, Рембрандт, Пикассо и Ван Гог.",
                point2,
                "Spb");

        // Большой театр
        Point point3 = new Point(55.760186, 37.618620);
        Sight sight3 = new Sight(
                "Большой театр",
                "Большой театр - это исторический оперный и балетный театр в Москве. Он является одной из самых известных и престижных оперных и балетных сцен в мире. Большой театр расположен напротив Красной площади и является архитектурным шедевром. Здесь можно насладиться высококлассными выступлениями оперных и балетных артистов, исполняющих произведения классического и современного репертуара.",
                point3,
                "Moskva");

        // Петропавловская крепость
        Point point4 = new Point(59.950340, 30.316229);
        Sight sight4 = new Sight(
                "Петропавловская крепость",
                "Петропавловская крепость - это историческая крепость, расположенная на острове Заячий в Санкт-Петербурге. Крепость была основана Петром Великим в 1703 году и служила оборонительной и политической центральной точкой города. Внутри крепости находятся такие достопримечательности, как Петропавловский собор, Музей истории Санкт-Петербурга и Петербургская монетная лавка. С крепости открывается великолепный вид на Неву и городские пейзажи.",
                point4,
                "Spb");

        // Байкальское озеро
        Point point5 = new Point(53.518192, 107.656611);
        Sight sight5 = new Sight(
                "Байкальское озеро",
                "Байкальское озеро - это глубочайшее и старейшее пресноводное озеро на планете, расположенное в Восточной Сибири. Оно является одним из самых известных природных чудес России. Байкал известен своим уникальным биоразнообразием и чистотой воды. Озеро окружено живописными горами, лесами и пляжами. Здесь можно насладиться красотой природы, пойти в поход, заняться рыбной ловлей или просто насладиться тишиной и спокойствием.",
                point5,
                "Irkutsk");

        return new ArrayList<>(Arrays.asList(sight1, sight2, sight3, sight4, sight5));
    }

}
