package run;

import bussiness.design.IAdmin;
import bussiness.entity.RoleName;
import bussiness.impl.AdminService;
import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

public class MenuAdmin {

    private static MenuAdmin menuAdmin = new MenuAdmin();

    public static MenuAdmin getInstance() {
        return menuAdmin;
    }

    //mục đích Signleton là ko cho phép khởi tạo đối tượng, mà chỉ truy cập đối tượng qua getInstance,
    //vì vậy cần khởi tạo lại constructor với private. nếu ko
    private MenuAdmin() {

    }

    private static IAdmin iAdmin = new AdminService();

    public void displayMenuAdmin() {
        while (true) {
            System.out.println("-----Welcome to Admin page----");
            System.out.println("1. Hiển thị danh sách toàn bộ user ");
            System.out.println("2. Khóa / mở người dùng  ");
            System.out.println("3. Tìm kiếm thông tin người dùng theo tên  ");
            System.out.println("4. thống kê danh sách người dự thi  ");
            System.out.println("5. thống kê danh sách bài thi  ");
            System.out.println("6. thông kê điểm thi trung bình theo tháng  ");
            System.out.println("7. thống kê tôp 10 bạn có điểm thi cao nhất trong tháng  ");
            System.out.println("8. Đăng Xuất ");

            byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
            switch (choice) {
                case 1:
            iAdmin.displayAllUser();
                    break;
                case 2:
                    iAdmin.blockAndUnblockUser();
                    break;
                case 3:
                    iAdmin.findUserByName();
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    break;
                case 8:
                    LoginMenu.user = null;
                    IOFile.writePerObject(IOFile.USER_LOGIN_PATH, LoginMenu.user);
                    return;
                default:
                    System.out.println("Your choice out of range");
                    break;

            }

        }


    }
}
