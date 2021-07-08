package sample;

import java.sql.SQLException;
import java.util.List;

public interface IFeedbackDAO extends IDAO{
    boolean insertFeedback(FeedbackModel feedbackModel);
    boolean updateFeedback(FeedbackModel feedbackModel) throws SQLException;
    boolean deleteFeedback(FeedbackModel feedbackModel);

    //    List<ExamModel> findExamByProperty(Types.ExamSearchType examSearchType, Object value);
    List<FeedbackModel> findAllAnswers();
}
