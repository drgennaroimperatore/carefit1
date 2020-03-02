package com.example.game_.other01_app.NonDBObjects;

public class Messages {
    private final String message;
    private final String categories;

    public Messages(String message, String categories) {
        this.message = message;
        this.categories = categories;
    }

    public String getCategories() {
        return categories;
    }

    public String getMessage() {
        return message;
    }

    public static Messages[] getAllMessages(){
        return new Messages[] {
                new Messages("Strength exercises can help to tone your muscles, which means you will be less likely to have an accident.","Strength" ),
                new Messages("Regular exercise can help your ability to continue with everyday activities.","All"),
                new Messages("Exercise is good for your brain and memory.","All" ),
                new Messages("Regular exercise can benefit your sense of wellbeing and self esteem.","All"),
                new Messages("Older adults should aim to do about 30 minutes of moderate exercise per day.","All"),
                new Messages("Aerobic exercise can be done anywhere and anytime - even while taking the stairs!","Aerobic"),
                new Messages("Listening to an audiobook while you exercise can make it more fun!","All"),
                new Messages("For a different aerobic activity, why not put on some music and dance around the living room?","Aerobic"),
                new Messages("Regular strength exercises keep your muscles strong  and will make it easier to carry out daily activities.","Strength"),
                new Messages("Even carrying the shopping has the hidden benefit of working out your muscles.","All"),
                new Messages("Even a small amount of exercise can improve your mood.","All"),
                new Messages("Sitting exercises can help you to improve posture and balance.","Balance,Sitting"),
                new Messages("If you haven’t exercised in a while, start with some sitting exercises to build up your fitness.","Sitting"),
                new Messages("To stay healthy, it is important to do some aerobic exercises and strength exercises every week.","Aerobic,Strength"),
                new Messages("The amount of physical activity you need to do each week depends on your age. For further advice, talk to a physician or GP.","All"),
                new Messages("Try practicing strength exercises at least 2 times a week.","Strength"),
                new Messages("When practicing strength exercises, it’s important to try a different variety to work all the major muscles.","Strength"),
                new Messages("A general rule of thumb is that 1 minute of vigorous activity provides the same health benefits as 2 minutes of moderate activity.","All"),
                new Messages("It’s important to break up long periods of sitting with light exercise.","All"),
                new Messages("Performing balance exercises on at least 2 days a week can strengthen your legs and improve coordination.","Balance,Legs"),
                new Messages("One way to tell if you’re exercising at a moderate level is if you can still talk, but can’t sing the words to a song.","All"),
                new Messages("Although daily chores such as housework and cooking don’t count as moderate exercise, they are still important, as they break up periods of sitting.","All"),
                new Messages("One way to get in exercise is to do some while you’re waiting one something to cook.","All"),
                new Messages("One way to get in exercise is to do some while you’re watching TV.","All"),
                new Messages("Strength exercises are good for building and maintaining strong bones.","Strength"),
                new Messages("Strength exercises are good for regulating blood sugar and blood pressure.","Strength"),
                new Messages("Strength exercises are good for maintaining a healthy weight.","Strength"),
                new Messages("To gain health benefits from strength exercises, you should do them to the point where you find it hard to complete another repetition.","Strength"),
                new Messages("You may already be doing Strength exercises - carrying or moving heavy loads, such as groceries counts as a Strength exercise.","All"),
                new Messages("You may already be doing Strength exercises - activities that involve stepping and jumping, such as dancing and climbing the stairs count as Strength exercises","All"),
                new Messages("Regular exercise will make you feel more energetic.","All"),
                new Messages("Before performing any higher intensity exercises, it is best advised that you warm up using some low to mid intensity exercises for 10 minutes first.","All"),
                new Messages("To maximise the benefits of exercise, perform them in a slow and controlled manner, going through the full range of motion and lifting within your comfort zone.","All"),
                new Messages("Flexibility exercises can ease you into the habit of regular exercise and trying new exercises.","Flexibility"),
                new Messages("Exercising together is a good way to stay motivated for exercise.","Together"),
                new Messages("Exercising together adds a social element, which can make exercising more fun.","Together"),
                new Messages("Try to attempt flexibility exercises twice a week to improve muscle strength, balance, and co-ordination.","Flexibility"),
                new Messages("Flexibility exercises can be good for loosening tight muscles.","Flexibility"),
                new Messages("Flexibility exercises can be a good way to relax.","Flexibility")
        };
    }

}
