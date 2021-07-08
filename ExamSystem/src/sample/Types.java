package sample;

public class Types {
    public enum UserSearchType {
        ID, NAME, USERNAME, ROLE;
    }

    public enum newUserRole{
        ADMIN, TEACHER, STUDENT ;
    }

    public enum ClassroomSearchType{
        NAME, LEVEL, DIVISION
    }

    public enum ExamSearchType {
        ID, NAME, CREATEDBY, ACTIVITY, LOGIN
    }

    public enum QuestionSearchType {
        TYPE, EXAM
    }
}
