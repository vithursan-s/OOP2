package sample;

public class Student {

    private final IAnswerDAO iAnswerDAO;
    private final IUserDAO iUserDAO;
    private final IClassRoomDAO iClassRoomDAO;
    private final IFeedbackDAO iFeedbackDAO;

    public Student(IUserDAO buildUserDAO, IClassRoomDAO buildClassroomDAO, IAnswerDAO buildAnswerDAO, IFeedbackDAO buildFeedbackDAO) throws Exception {
        this.iUserDAO = buildUserDAO;
        this.iClassRoomDAO = buildClassroomDAO;
        this.iAnswerDAO = buildAnswerDAO;
        this.iFeedbackDAO = buildFeedbackDAO;
        iUserDAO.connect();
        iClassRoomDAO.connect();
        iAnswerDAO.connect();
        iFeedbackDAO.connect();
    }
}
