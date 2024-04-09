package run;

import java.awt.*;

public class MenuTeacher {

    private static MenuTeacher menuTeacher = new MenuTeacher();

    public static MenuTeacher getInstance(){
        return menuTeacher;
    }

    private MenuTeacher(){}

    public void displayMenuTeacher()
    {
        System.out.println("Welcome to teacher page");
    }
}
