package com.example.proyectomascotita;

import javafx.scene.control.Alert;

//Pet class definition
public class Pet {
    //Pet attributes
    private String name;//Pet name (not race) stores the name of the pet
    private String race;//Pet race (not name) stores the race of the pet
    private String trait;//Pet trait stores the trait of the pet

    private int energy;//Pet energy level, 0 is tired and 100 is full of energy
    private int hunger;//Pet hunger level, 0 is full, 100 is starving
    private int cleanliness;//Pet cleanliness level, 0 is dirty and 100 is clean
    private int mood;//Pet mood level, 0 is sad and 100 is happy
    private int friendship;//Pet friendship level, 0 is no friendship and 100 is the best friendship

    //Generate pet class constructor
    public Pet(String name, String race) {
        this.name = name;//Assign name
        this.race = race;//Assign race
        this.trait = assignTrait(race);//Assign trait based on race
        this.energy = 100;//Assign default energy
        this.hunger = 0;//Assign default hunger
        this.cleanliness = 100;//Assign default cleanliness
        this.mood = 100;//Assign default mood
        this.friendship = 0;//Assign default friendship
    }

    //Method to assign a trait based on the race
    private String assignTrait(String race) {

        //Switch-case statement to assign trait based
        return switch (race) {
            case "Wanyamon" -> "Cariñoso/a";//Wanyamon is lovely (cariñoso)
            case "Koromon" -> "Travieso/a";//Koromon is naughty (travieso)
            case "Tokomon" -> "Dormilón";//Tokomon is sleepy (dormilón)
            case "Tanemon" -> "Amable";//Tanemon is kind (amable)
            case "Puttimon" -> "Alegre";//Puttimon is cheerful (alegre)
            case "Tsunomon" -> "Valiente";//Tsunomon is brave (valiente)
            case "Dorimon" -> "Curioso/a";//Dorimon is curious (curioso)
            case "Gigimon" -> "Juguetón";//Gigimon is playful (juguetón)
            case "Panxomon" -> "Hablador";//Panxomon is talkative (hablador)
            default -> "Desconocido";//Unknown (desconocido) in case of invalid/null race
        };
    }

    //Constructor for pet image from path
    public void getPetImage() {
        return;
    }
    //Getters for all attributes, a getter is a method that returns the value of a private attribute (encapsulation) to be accessed from outside the class (public)
    public String getName() {return name;}
    public String getRace() {return race;}
    public String getTrait() {return trait;}
    public int getEnergy() {return energy;}
    public int getHunger() {return hunger;}
    public int getCleanliness() {return cleanliness;}
    public int getMood() {return mood;}
    public int getFriendship() {return friendship;}

    //Setters for all attributes, a setter is a method that sets the value of a private attribute (encapsulation) to be accessed from outside the class (public) and can be changed (settable) or assigned (not settable) at creation
    //Race is not settable, it is assigned at creation
    //Trait is not settable, it is assigned at creation
    //Name, energy, hunger, cleanliness, mood and friendship are settable
    public void setName(String name) {this.name = name;}//Name is settable, can be changed
    public void setEnergy(int energy) {this.energy = Math.max(0, Math.min(energy, 100));}//Energy is clamped between 0 and 100
    public void setHunger(int hunger) {this.hunger = Math.max(0, Math.min(hunger, 100));}//Hunger is clamped between 0 and 100
    public void setCleanliness(int cleanliness) {this.cleanliness = Math.max(0, Math.min(cleanliness, 100));}//Cleanliness is clamped between 0 and 100
    public void setMood(int mood) {this.mood = Math.max(0, Math.min(mood, 100));}//Mood is clamped between 0 and 100
    public void setFriendship(int friendship) {this.friendship = Math.max(0, Math.min(friendship, 100));}//Friendship is clamped between 0 and 100
    //Values are assigned between 0 and 100, if they are less than 0, they are set to 0, if they are greater than 100, they are set to 100

    //Method to show pet status, shows all pet attributes
    public void showStatus() {
        System.out.println("\uD83D\uDC3E Estado de la mascota \uD83D\uDC3E");//Print pet status title
        System.out.println("Nombre: " + name);//Print pet name
        System.out.println("Raza: " + race);//Print pet race
        System.out.println("Personalidad: " + trait);//Print pet trait
        System.out.println("Energía: " + energy + "/100");//Print pet energy
        System.out.println("Hambre: " + hunger + "/100");//Print pet hunger
        System.out.println("Limpieza: " + cleanliness + "/100");//Print pet cleanliness
        System.out.println("Ánimo: " + mood + "/100");//Print pet mood
        System.out.println("Amistad: " + friendship + "/100");//Print pet friendship
        System.out.println("------------------------------");//Print separator
    }

    //Create and define showAlert method to show alerts with custom title and message
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);//Create alert with specified type (error, warning, information, etc.) (AlertType)
        alert.setTitle(title);//Set alert title
        alert.setHeaderText(null);//Set alert header text to null
        alert.setContentText(message);//Set alert content text
        alert.showAndWait();//Show alert and wait for user to close it
    }

    //Methods to interact with the pet ---------------------------------------------------------------------------------

    //Feed method -25 hunger, -5 cleanliness & +5 energy, no return value (void), public (accessible from outside)
    public void feed() {
        //Condition to check if hunger is greater than 0, if it is, decrease hunger by 25 points (meaning, it is hungry), if it's full (hunger == 0), do nothing, cant eat
        if (hunger > 0) {
            hunger = Math.max(0, hunger - 25);//Decrease hunger by 25 points, clamp between 0 and 100, Math.max avoids it from falling below 0
            cleanliness = Math.max(0, cleanliness - 5);//Decrease cleanliness by 5 points, clamp between 0 and 100, Math.max avoids it from falling below 0
            energy = Math.min(100, energy + 5);//Increase energy by 5 points, clamp between 0 and 100

            //EXAMPLE OF SHOWING AN ALERT EVERYTIME I FEED, COULD BE USED IN THE FUTURE
            // showAlert(Alert.AlertType.INFORMATION,name + " ha comido \uD83D\uDE38", "Alimentando a " + name + "\nHambre: -25.\nLimpieza: -5.\nEnergía: +5.");//Print pet status after eating, showing changes
            //System.out.println(name + " ha comido.");//Print pet status after eating, showing changes
        } else {
            showAlert(Alert.AlertType.INFORMATION,name + " no puede comer.", "No se puede alimentar a " + name + ", ya que no tiene hambre.");
        }
    }

    //Play method -20 energy, +10 mood, +15 hunger & +3 friendship, no return value (void), public (accessible from outside)
    public void play() {
        //Condition to check if energy is greater than 0, if it is, decrease energy by 25 points (meaning, it has energy to play), if it's tired (energy == 0), do nothing, cant play
        if (energy > 0) {
            energy = Math.max(0, energy - 25);//Decrease energy by 25 points, clamp between 0 and 100, Math.max avoids it from falling below 0
            mood = Math.min(100, mood + 25);//Increase mood by 25 points, clamp between 0 and 100
            hunger = Math.min(100, hunger + 20);//Increase hunger by 20 points, clamp between 0 and 100
            friendship = Math.min(100, friendship + 3);//Increase friendship by 3 points, clamp between 0 and 100
            cleanliness = Math.max(0, cleanliness - 10);//Decrease cleanliness by 10 points, clamp between 0 and 100, Math.max avoids it from falling below 0
            System.out.println(name + " está jugando. Energía: " + energy + ", Ánimo: " + mood + ", Hambre: " + hunger + ", Amistad: " + friendship + ", Limpieza: " + cleanliness);//Print pet status after playing, showing changes
        } else {
            System.out.println(name + " está demasiado cansado/a para jugar.");//Print message if pet is too tired to play
        }
    }

    //Rest method +25 energy, +15 hunger, +5 mood, -10 cleanliness, no return value (void), public (accessible from outside)
    public void rest() {
        energy = Math.min(100, energy + 25);//Increase energy by 20 points, clamp between 0 and 100
        hunger = Math.min(100, hunger + 15);//Increase hunger by 15 points, clamp between 0 and 100
        mood = Math.min(100, mood + 10);//Increase mood by 10 points, clamp between 0 and 100
        cleanliness = Math.max(0, cleanliness - 15);//Decrease cleanliness by 15 points, clamp between 0 and 100, Math.max avoids it from falling below 0

        System.out.println(name + " está descansando. Energía: " + energy + ", Hambre: " + hunger + ", Ánimo: " + mood + ", Limpieza: " + cleanliness);//Print pet status after resting, showing changes
    }

    //Clean method +20 cleanliness, +5' mood & -10 energy, no return value (void), public (accessible from outside)
    public void clean() {
        if (energy > 0) {
            cleanliness = Math.min(100, cleanliness + 25);//Increase cleanliness by 25 points, clamp between 0 and 100, Math.min avoids it from going above 100
            mood = Math.min(100, mood + 15);//Increase mood by 15 points, clamp between 0 and 100, Math.min avoids it from going above 100
            energy = Math.max(0, energy - 15);//Decrease energy by 15 points, clamp between 0 and 100, Math.max avoids it from falling below 0
        } else {
            System.out.println(name + " está demasiado cansado/a para limpiarlo/a.");//Print message if pet is too tired to clean
        }
//        System.out.println(name + " está siendo limpiado. Limpieza: " + cleanliness + ", Ánimo: " + mood + ", Energía: " + energy);//Print pet status after cleaning, showing changes
    }

    //Chat method +10 mood, +3 friendship, -5 energy, no return value (void), public (accessible from outside)
    public void chat() {
        if (energy > 0) {
            mood = Math.min(100, mood + 10);//Increase mood by 10 points, clamp between 0 and 100, Math.min avoids it from going above 100
            friendship = Math.min(100, friendship + 3);//Increase friendship by 3 points, clamp between 0 and 100, Math.min avoids it from going above 100
            energy = Math.max(0, energy - 5);//Decrease energy by 5 points, clamp between 0 and 100, Math.max avoids it from falling below 0

        } else {
            System.out.println(name + " está demasiado cansado/a para charlar.");//Print message if pet is too tired to chat
        }
//        System.out.println(name + " está charlando. Ánimo: " + mood + ", Amistad: " + friendship + ", Energía: " + energy);//Print pet status after chatting, showing changes
    }

    //Pet method +10 mood, +3 friendship, -5 energy, no return value (void), public (accessible from outside)
    public void pet() {
        if (energy > 0) {
            mood = Math.min(100, mood + 10);//Increase mood by 10 points, clamp between 0 and 100, Math.min avoids it from going above 100
            friendship = Math.min(100, friendship + 3);//Increase friendship by 3 points, clamp between 0 and 100, Math.min avoids it from going above 100
            energy = Math.max(0, energy - 5);//Decrease energy by 5 points, clamp between 0 and 100, Math.max avoids it from falling below 0
        } else {
            System.out.println(name + " está demasiado cansado/a para acariciarlo/a.");//Print message if pet is too tired to pet
        }
//        System.out.println(name + " está siendo acariciado/a. Ánimo: " + mood + ", Amistad: " + friendship + ", Energía: " + energy);//Print pet status after petting, showing changes
    }
}