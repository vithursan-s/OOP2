package sample;

import java.sql.SQLException;
import java.util.List;

public interface IQuestionDAO extends IDAO {
    boolean insertQuestion(QuestionModel questionModel);
    boolean updateQuestion(QuestionModel questionModel) throws SQLException;
    boolean deleteQuestion(QuestionModel questionModel);

    List<QuestionModel> findQuestionsByProperty(Types.QuestionSearchType questionSearchType, Object value);
    List<QuestionModel> findAllQuestions(int examID);
}
