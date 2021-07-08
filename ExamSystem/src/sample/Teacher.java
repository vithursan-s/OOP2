package sample;

import java.sql.SQLException;
import java.util.List;

public class Teacher {
    private final IExamDAO iExamDAO;
    private final IQuestionDAO iQuestionDAO;
    private final IFeedbackDAO iFeedbackDAO;

    public Teacher(IExamDAO buildExamDAO, IQuestionDAO buildQuestionDAO, IFeedbackDAO buildFeedbackDAO) throws Exception {
        this.iExamDAO = buildExamDAO;
        this.iQuestionDAO = buildQuestionDAO;
        this.iFeedbackDAO = buildFeedbackDAO;
        iExamDAO.connect();
        iQuestionDAO.connect();
        iFeedbackDAO.connect();
    }


    public void addNewExam(String name, String createdBy, String activity, int questionAmount, int login, int passcode){
        ExamModel examModel = new ExamModel();
        examModel.setName(name);
        examModel.setCreatedBy(createdBy);
        examModel.setActivity(activity);
        examModel.setQuestionAmount(questionAmount);
        examModel.setLogin(login);
        examModel.setPasscode(passcode);

        iExamDAO.insertExam(examModel);
    }

    public boolean updateExam(ExamModel examModel) throws SQLException {
        return iExamDAO.updateExam(examModel);
    }

    public List<ExamModel> searchExam(Types.ExamSearchType examSearchType, String value){
        return iExamDAO.findExamByProperty(examSearchType, value);
    }

    public List<ExamModel> findAllExam(){
        return iExamDAO.findAllExams("t.will");
    }

    public boolean assignExamToClass(int examID, int ClassID){
        return iExamDAO.assignExamToClass(1, 1);
    }

    public void close() throws Exception {
        iExamDAO.close();
    }
}
