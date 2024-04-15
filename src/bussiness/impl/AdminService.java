package bussiness.impl;

import bussiness.design.IAdmin;
import bussiness.entity.Exam;
import bussiness.entity.Result;
import bussiness.entity.RoleName;
import bussiness.entity.User;
import utils.ErrorAndRegex;
import utils.QuizConFig;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AdminService implements IAdmin {


    @Override
    public User findById(int userId) {
        return UserService.userList.stream().filter(user -> user.getUserId() == userId).findFirst().orElse(null);

    }

    @Override
    public void displayAllUser() {
        if (UserService.userList.isEmpty()) {
            System.out.println(ErrorAndRegex.NOTIFY_EMPTY);
        } else {
            UserService.userList.forEach(User::displayPerUser);
        }
    }


    @Override
    public void blockAndUnblockUser() {
        displayAllUser();
        System.out.println("Input UserId You Want To Change Status");
        int userId = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        User user = findById(userId);
        if (user != null) {
            user.setUserStatus(!user.isUserStatus());
            System.out.println("Change Successfully");
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }


    }


    @Override
    public void findUserByName() {
        System.out.println("Input User Name you want to search");
        String userName = QuizConFig.inputFromUser(ErrorAndRegex.REGEX_STRING, ErrorAndRegex.ERROR_VALUE);
        UserService.userList.stream().filter(user -> user.getUserName().contains(userName)).forEach(User::displayPerUser);
    }

    @Override
    public void staticalStudentTookExamInMonth() {
        System.out.println("Input The Month To Find Student Who Took Exam");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {

            List<Result> results = ResultService.resultList.stream().filter(result -> result.getCreatedDate().getMonthValue() == getMonth).toList();

            System.out.printf("Total Student %s \n", results.size());
            for (Result result : results) {
                UserService.userList.stream().filter(user -> user.getUserId() == result.getUserId()).forEach(User::displayPerUser);
            }
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }


    @Override
    public void staticalExamCreatedInMonth() {
        System.out.println("Input The Month To Find How Many Exam Created");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
           ExamService.examList.stream().filter(exam -> exam.getCreateAt().getMonthValue() == getMonth).forEach(Exam::displayData);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void findExamMostStudentTookInMonth() {
        //tim trong result, result nao co nhieu examId nhat, thi do dc nhieu nguoi tham gia nhat
        System.out.println("Input The Month To Exam Most people took");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
            List<Exam> examList = ExamService.examList.stream().filter(exam -> exam.getCreateAt().getMonthValue() == getMonth).toList();
            Map<Exam,Integer> map=new HashMap<>();
            for (Exam exam : examList) {
                int count = (int) ResultService.resultList.stream().filter(result -> result.getExamId()==exam.getExamId()).count();
                map.put(exam,count);
            }

//        map.entrySet().stream() tạo một stream từ các entry của map.
//        sorted(Map.Entry.comparingByValue().reversed()) sắp xếp các entry theo giá trị giảm dần.
//                limit(2) giới hạn kết quả đầu ra chỉ gồm hai entry đầu tiên sau khi sắp xếp.
//        collect(Collectors.toMap(...)) thu thập hai entry này vào một HashMap mới.
            Map<Exam, Integer> topTwo = map.entrySet()
                    .stream()
                    .sorted(Map.Entry.<Exam, Integer>comparingByValue().reversed())
                    .limit(1)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            HashMap::new));

            for (Map.Entry<Exam, Integer> examIntegerEntry : topTwo.entrySet()) {
                examIntegerEntry.getKey().displayData();

            }
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }

    }

    @Override
    public void findExamWithTheLeastNumberPeopleLookFor() {
        System.out.println("Input The Month To Find Exam Least Student Search");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
            List<Exam> exams = ExamService.examList.stream().filter(exam -> exam.getCreateAt().getMonthValue() == getMonth).toList();
            exams.stream().sorted(Comparator.comparing(Exam::getSearchNumber)).limit(1).forEach(Exam::displayData);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void findExamWithTheMostNumberPeopleLookFor() {
        System.out.println("Input The Month To Find Exam Most Student Search");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
            List<Exam> exams = ExamService.examList.stream().filter(exam -> exam.getCreateAt().getMonthValue() == getMonth).toList();
            exams.stream().sorted(Comparator.comparing(Exam::getSearchNumber).reversed()).limit(1).forEach(Exam::displayData);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void findTheMostWrongQuestionInExam() {

    }

    @Override
    public void findTheMostTrueQuestionInExam() {


    }

    @Override
    public void findNumberUserAccountCreatedInMonth() {
        System.out.println("Input The Month To Find User Account Created");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
           UserService.userList.stream().filter(user -> user.getRoleName().equals(RoleName.ROLE_USER)).forEach(User::displayPerUser);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void findNumberTeacherAccountCreatedInMonth() {
        System.out.println("Input The Month To Find User Account Created");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
            UserService.userList.stream().filter(user -> user.getRoleName().equals(RoleName.ROLE_TEACHER)).forEach(User::displayPerUser);
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }
    }

    @Override
    public void findStudentWhoGetHighestScore() {
        //tim ra 2 result co diem cao nhat, lay 2 userId do ra push vao 1 mang. roi so sanh voi listUser tim ra 2 object do
        System.out.println("Input The Month To Find Student Who Get Highest Score");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
            List<Result> resultsHighestScore = ResultService
                    .resultList.stream().sorted(Comparator.comparing(Result::getTotalPoint).reversed())
                    .limit(1).toList();
            List<Integer> listUserHigestScore = new ArrayList<>();
            for (Result result : resultsHighestScore){
                listUserHigestScore.add(result.getUserId());
            }

            for (int userId : listUserHigestScore){
                UserService.userList.stream().filter(user -> user.getUserId() == userId).forEach(User::displayPerUser);
            }

        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }

    }

    @Override
    public void findStudentWhoGetLowestScore() {
        //tim ra 2 result co diem thap nhat, getUserID cua 2 result do ra, push vao mang List moi,
        //so sanh de lay ra 2 sinh vien do.
        System.out.println("Input The Month To Find Student Who Get Highest Score");
        byte getMonth = QuizConFig.getByte(ErrorAndRegex.REGEX_NUMBER, ErrorAndRegex.ERROR_VALUE);
        if (getMonth >= 1 && getMonth <= 12) {
            List<Result> resultsHighestScore = ResultService
                    .resultList.stream().sorted(Comparator.comparing(Result::getTotalPoint))
                    .limit(1).toList();
            List<Integer> listUserHigestScore = new ArrayList<>();
            for (Result result : resultsHighestScore){
                listUserHigestScore.add(result.getUserId());
            }
            for (int userId : listUserHigestScore){
                UserService.userList.stream().filter(user -> user.getUserId() == userId).forEach(User::displayPerUser);
            }
        } else {
            System.out.println(ErrorAndRegex.ERROR_NOT_FOUND);
        }

    }

}
