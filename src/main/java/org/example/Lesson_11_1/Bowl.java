package org.example.Lesson_11_1;

class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    //уменьшить еду
    public void reduceFood(int food) {
        if (this.food >= food) {
            this.food -= food;
        } else {
            this.food = 0; // Или выбросить исключение, если это недопустимо
        }
    }

    //добавить еду
    public void addFood(int food) {
        this.food += food;
        System.out.println("В миску добавлено " + food + " еды. Теперь в миске " + this.food + " еды.");
    }
}

