package com.example.game_.other01_app.Database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

//Represents the data for a single row in the exercise table, constructed
//using an annotated java data object. Each entity is persisted into its own
//table.
@Entity(tableName = "OTHER01exercises")
public class Exercise {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private final int id;
    @NonNull
    @ColumnInfo(name = "name")
    private final String name;
    @NonNull
    @ColumnInfo (name ="description")
    private final String desc;
    @NonNull
    @ColumnInfo (name = "image")
    private final String image;
    @NonNull
    @ColumnInfo (name = "intensity")
    private final String intensity;
    @NonNull
    private final String categories;

    public Exercise(int id, String name, String desc, String image,
                    String intensity, String categories){

        this.id = id;
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.intensity = intensity;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public static Exercise[] populateData() {
        return new Exercise[] {
                new Exercise(1, "Chest Stretch",
                        "Sit upright and away from the back of the chair. Pull your shoulders back and down. " +
                        "Extend arms out to the side.\n" +
                        "Gently push your chest forwards and up until you feel a stretch across your chest.\n" +
                        "Hold for five to 10 seconds and repeat five times.", "cheststretch",
                        "low",
                        "Sitting,Back,Stretch"),
                new Exercise(2, "Upper Body Twist",
                        "Sit upright with feet flat on the floor, cross your arms and reach for your shoulders.\n" +
                        "Without moving your hips, turn your upper body to the left as far as is comfortable.\n" +
                        "Hold for five seconds. Repeat going right. Do five of each.", "upperbodytwist",
                        "low",
                        "Sitting,Flexibility,Back"),
                new Exercise(3, "Hip Marching",
                        "Sit upright and away from the back of the chair. Hold on to the sides of the chair.\n" +
                        "Lift your left leg, with your knee bent, as far as is comfortable. Place foot down with control.\n" +
                        "Repeat with the opposite leg. Do five lifts with each leg.", "hipmarching",
                        "low",
                        "Sitting,Flexibility,Legs"),
                new Exercise(4, "Ankle Stretch",
                        "Sit upright, hold on to the side of the chair and straighten your left leg with your foot off the floor.\n" +
                        "With leg straight and raised, point your toes away from you.\n" +
                        "Point your toes back towards you.", "anklestretch",
                        "low",
                        "Sitting,Flexibility,Legs,Stretch"),
                new Exercise(5, "Arm Raises",
                        "Sit or stand upright, arms by your sides.\n" +
                        "With palms forwards, raise both arms out and to the side.\n" +
                                "Raise them up as far as is comfortable. Then return.\n" +
                        "Keep your shoulders down and arms straight throughout.\n" +
                        "Breathe out as you raise your arms and breathe in as you lower them.", "armraises",
                        "low",
                        "Sitting,Strength,Arms"),
                new Exercise(6, "Neck Rotation",
                        "Sit upright with shoulders down. Look straight ahead.\n" +
                        "Slowly turn your head towards your left shoulder as far as is comfortable.\n" +
                        "Hold for five seconds and return to starting position. Repeat going in the other direction.",
                        "neckrotation","low",
                        "Sitting,Flexibility,Back"),
                new Exercise(7,"Calf Stretch",
                        "Place your hands against a wall for stability.\n" +
                                "Bend the right leg and step the left leg back at least a foot’s distance, keeping it straight\n" +
                                "Both feet should be flat on the floor.\n" +
                                "The left calf muscle is stretched by keeping the left leg as straight as possible and the left heel on the floor.\n" +
                                "Repeat with the opposite leg.","calfstretch","low",
                        "Flexibility,Legs,Stretch"),
                new Exercise(8,"Sit To Stand",
                                "Sit on the edge of the chair, feet hip-width apart. Lean slightly forwards.\n" +
                                "Stand up slowly, using your legs, not arms. Keep looking forwards, not down.\n" +
                                "Stand upright before slowly sitting down, bottom-first. The slower the better.",
                        "sittostand","mid",
                        "Sitting,Legs,Aerobic"),
                new Exercise(9,"Mini Squats",
                        "Rest your hands on the back of the chair for stability and stand with your feet hip-width apart.\n" +
                                "Slowly bend your knees as far as is comfortable, keeping them facing forwards.\n" +
                                "Aim to get them over your big toe. Keep your back straight at all times.\n" +
                                "Gently come up to standing, squeezing your buttocks as you do.",
                        "minisquats","mid",
                        "Strength,Legs"),
                new Exercise(10,"Calf Raises",
                        "Rest your hands on the back of a chair for stability.\n" +
                                "Lift both heels off the floor as far as is comfortable. The movement should be slow and controlled.\n" +
                                "For more difficulty, perform this exercise without support.",
                        "calfraises","low",
                        "Strength,Legs"),
                new Exercise(11,"Leg Lift",
                        "Rest your hands on the back of a chair for stability. Raise your left leg to the side as far as is comfortable\n" +
                        "Keep your back and hips straight. Avoid tilting to the right.\n" +
                                "Return to the starting position. Then raise your right leg to the side as far as possible.",
                        "leglift","mid",
                        "Strength,Legs"),
                new Exercise(12,"Wall Press Up",
                        "Stand at arm’s length from the wall. Place your hands flat against the wall, at chest level,\n" +
                                "With fingers pointing upwards and back straight, slowly bend your arms, keeping elbows by your side.\n" +
                                "Aim to close the gap between you and the wall as much as you can.\n" +
                                "Slowly return to the starting position.",
                        "wallpressup","mid",
                        "Strength,Arms"),
                new Exercise(13,"Bicep Curls",
                        "Hold a pair of light weights (filled water bottles will do) and stand with your feet hip-width apart.\n" +
                                "Keeping your arms by your side, slowly bend them until the weight in your hand reaches your shoulder.\n" +
                                "Lower again slowly.\n","bicepcurls","mid",
                        "Sitting,Strength,Arms"),
                new Exercise(14,"Sideways Walk",
                        "Stand with your feet together, knees slightly bent.\n" +
                                "Step sideways in a slow and controlled manner, moving one foot to the side first.\n" +
                                "Move the other to join it. Avoid dropping your hips as you step.\n" +
                                "Perform 10 steps each way or step from one side of the room to the other.",
                        "sidewayswalk","mid",
                        "Balance,Legs"),
                new Exercise(15,"Grapevine",
                        "Start by crossing your right foot over your left.\n" +
                                "Bring your left foot to join it.\n" +
                                "Attempt five cross steps on each side.\n" +
                                "If necessary, put your fingers against a wall for stability.\n" +
                                "The smaller the step, the more you work on your balance.",
                        "grapevine","high",
                        "Balance,Legs"),
                new Exercise(16,"Heel To Toe",
                        "Standing upright, place your right heel on the floor directly in front of your left toe.\n" +
                                "Then do the same with your left heel. Make sure you keep looking forwards at all times.\n" +
                                "If necessary, put your fingers against a wall for stability.",
                        "heeltotoe","mid","Balance,Legs"),
                new Exercise(17,"One Leg Stand",
                        "Start by standing facing the wall, with arms outstretched and your fingertips touching the wall.\n" +
                                "Lift your leg, keep your hips level and keep a slight bend in the opposite leg. Hold the lift for 5 to 10 seconds.\n" +
                        "Gently place your foot back on the floor. Swap legs.",
                        "onelegstand","mid",
                        "Balance,Legs"),
                new Exercise(18,"Step Up",
                        "Use a step, preferably with a railing or near a wall to use as support.\n" +
                                "Step up with your right leg. Then bring your left leg up to join it.\n" +
                                "Step down again and return to the start position.\n" +
                                "The key for building balance is to step up and down slowly and in a controlled manner.",
                        "stepup","high",
                        "Balance,Legs,Aerobic"),
                new Exercise(19,"Burpees",
                        "From a standing position, drop into a squat with your hands on the ground.\n" +
                                "Kick your feet back into a push-up position.\n" +
                                "Jump your feet back into a squat.\n" +
                                "Jump up with your arms extending overhead.\n" +
                                "For an easier burpee, don't kick out into the push-up position and stand up instead of jumping.",
                        "burpees","high",
                        "Strength,Legs,Arms,Aerobic"),
                new Exercise(20,"Buttock Stretch",
                        "Lie on your back and bring your knees up to your chest.\n" +
                                "Cross your right leg over your left thigh.\n" +
                                "Grasp the back of your left thigh with both hands.\n" +
                                "Pull your left leg towards your chest.\n" +
                                "Repeat with the opposite leg.",
                        "buttockstretch","low",
                        "Balance,Legs,Stretch"),
                new Exercise(21,"Inner Thigh Stretch",
                        "Sit down with your back straight and your legs bent.\n" +
                                "Put the soles of your feet together.\n" +
                                "Holding on to your feet, try to lower your knees towards the floor.",
                        "innerthighstretch","low",
                        "Balance,Legs,Stretch"),
                new Exercise(22,"Hamstring Stretch",
                        "Lie on your back and raise your right leg.\n" +
                                "Hold your right leg with both hands, below your knee.\n" +
                                "Keeping your left leg bent with your foot on the floor, pull your right leg towards you keeping it straight.\n" +
                                "Repeat with the opposite leg.",
                        "hamstringstretch","mid",
                        "Balance,Legs,Stretch"),
                new Exercise(23,"Rocket Jumps",
                        "Stand with your feet hip-width apart, legs bent and hands on your thighs.\n" +
                                "Jump up, driving your hands straight above your head and extending your entire body. Land softly, reposition your feet and repeat.\n" +
                                "For more of a challenge, start in a lower squat position and hold a weight or a bottle of water in both hands at the centre of your chest.",
                        "rocketjump","high",
                        "Strength,Legs,Arms,Aerobic"),
                new Exercise(24,"Squats",
                        "Stand with your feet shoulder-width apart and your hands down by your sides or stretched out in front for extra balance.\n" +
                                "Lower yourself by bending your knees until they're nearly at a right angle, with your thighs parallel to the floor.\n" +
                                "Keep your back straight and don't let your knees extend over your toes.",
                        "squats","mid",
                        "Balance,Strength,Legs,Aerobic"),
                new Exercise(25,"Star Jumps",
                        "To do a star jump, stand tall with your arms by your side and knees slightly bent.\n" +
                                "Jump up, extending your arms and legs out into a star shape in the air.\n" +
                                "Land softly, with your knees together and hands by your side.\n" +
                                "Keep your abs tight and back straight during the exercise.",
                        "starjump","high",
                        "Legs,Arms,Aerobic"),
                new Exercise(26,"Tap Backs",
                        "To start, step your right leg back and swing both arms forward.\n" +
                                "Repeat with the opposite leg in a continuous rhythmic movement.\n" +
                                "Look forward and keep your hips and shoulders facing forward.\n" +
                                "Don't let your front knee extend over your toes as you step back.\n" +
                                "For more of a challenge, switch legs by jumping (also known as spotty dog), remembering to keep the knees soft as you land. Your back heel needs to be off the floor at all times.",
                        "tapbacks","high",
                        "Balance,Legs,Aerobic"),
                new Exercise(27,"Thigh Stretch",
                        "Lie on your right side.\n" +
                                "Grab the top of your left foot and gently pull your heel towards your left buttock to stretch the front of the thigh.\n" +
                                "Keep your knees touching.\n" +
                                "Repeat on the other side.\n",
                        "thighstretch","low",
                        "Balance,Legs,Stretch")
        };
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getImage() {
        return image;
    }

    public String getCategories() {
        return categories;
    }
}
