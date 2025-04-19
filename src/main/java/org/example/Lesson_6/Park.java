package org.example.Lesson_6;

public class Park {

    private String name;

    public Park(String name) {
        this.name = name;
    }


    public class Attraction {
        private String name;
        private String time;
        private float cost;

        public Attraction(String name, String time, float cost) {
            this.name = name;
            this.time = time;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public float getCost() {
            return cost;
        }
    }

    public void printAttraction(Attraction attraction) {
        System.out.println("Парк " + name + "\n" + "Аттракцион: " + attraction.getName() + "\n" +
                "Время работы: " + attraction.getTime() + "\n" +
                "Стоимость: " + attraction.getCost());
    }
}


