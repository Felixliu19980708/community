package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}