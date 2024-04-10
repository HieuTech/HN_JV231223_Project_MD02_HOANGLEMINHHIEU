package run;

import utils.ErrorAndRegex;
import utils.IOFile;
import utils.QuizConFig;

import java.sql.SQLOutput;

public class MenuUser {

    private static MenuUser menuUser = new MenuUser();

    public static MenuUser getInstance() {
        return menuUser;
    }

    private MenuUser() {
    }



    public void displayMenuUser() {
      while (true){
          System.out.println("Welcome to user page");
          System.out.println("1. Hiển thị danh sách đề thi");
          System.out.println("2. Tìm kiếm đề thi theo danh mục");
          System.out.println("3. Bắt đầu thi");
          System.out.println("4. Hiển thị thông tin cá nhân");
          System.out.println("5. Chỉnh sửa thông tin cá nhân");
          System.out.println("6. Hiển thị lịch sử bài thi");
          System.out.println("6. Đồi mật khẩu");
          System.out.println("7. Đăng xuất");

          byte choice = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
          switch (choice) {
              case 1:

                  break;
              case 2:

                  break;
              case 3:

                  break;
              case 4:

                  break;
              case 5:

                  break;
              case 6:

                  break;
              case 7:
                LoginMenu.user = null;
                  IOFile.writePerObject(IOFile.USER_LOGIN_PATH,LoginMenu.user);
                  return;

              default:
                  System.out.println("Your choice out of range");
                  break;

          }
      }
    }
}
