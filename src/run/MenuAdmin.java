package run;

public class MenuAdmin {

    private static MenuAdmin menuAdmin = new MenuAdmin();

    public static MenuAdmin getInstance(){
        return menuAdmin;
    }
    //mục đích Signleton là ko cho phép khởi tạo đối tượng, mà chỉ truy cập đối tượng qua getInstance,
    //vì vậy cần khởi tạo lại constructor với private. nếu ko
    private MenuAdmin(){

    };

    public void displayMenuAdmin()
    {
        System.out.println("Welcome to Admin page");
    }
}
