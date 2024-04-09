package run;

public class MenuUser {

    private static MenuUser menuUser = new MenuUser();

    public static MenuUser getInstance(){
        return menuUser;
    }

    private MenuUser(){};
    public void displayMenuUser()
    {
        System.out.println("Welcome to user page");
    }
}
