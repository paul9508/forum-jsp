/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.project.dao;

import edu.ouhk.comps380f.project.model.Poll;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author s1158854
 */
public class PollRepositoryImpl implements PollRepository {

    private DataSource dataSource;
    private JdbcOperations jdbcOp;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }

    private static final String SQL_SELECT_ALLPOLL
            = "select * from poll";

    @Override
    public List<Poll> findAllPoll() {
        List<Poll> polls = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList(SQL_SELECT_ALLPOLL);
        for (Map<String, Object> row : rows) {
            Poll poll = new Poll();
            poll.setId((Integer) row.get("poll_id"));
            poll.setQuestion((String) row.get("question"));
            poll.setAnswer1((String) row.get("answer1"));
            poll.setAnswer2((String) row.get("answer2"));
            poll.setAnswer3((String) row.get("answer3"));
            poll.setAnswer4((String) row.get("answer4"));
            poll.setCount1((Integer) row.get("count1"));
            poll.setCount2((Integer) row.get("count2"));
            poll.setCount3((Integer) row.get("count3"));
            poll.setCount4((Integer) row.get("count4"));
            polls.add(poll);
        }
        return polls;
    }

    private static final String SQL_INSERT_POLL
            = "insert into poll (Question, Answer1, Answer2, Answer3, Answer4) values (?, ?, ?, ?, ?)";

    @Override
    public void adminCreatePoll(Poll poll) {
        jdbcOp.update(SQL_INSERT_POLL,
                poll.getQuestion(),
                poll.getAnswer1(),
                poll.getAnswer2(),
                poll.getAnswer3(),
                poll.getAnswer4());
    }

    private static final String SQL_SELECT_POLL
            = "select * from poll WHERE poll_id = (SELECT MAX(poll_id) FROM poll)";

    @Override
    public List<Poll> findPoll() {
        List<Poll> polls = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList(SQL_SELECT_POLL);
        for (Map<String, Object> row : rows) {
            Poll poll = new Poll();
            poll.setId((Integer) row.get("poll_id"));
            poll.setQuestion((String) row.get("question"));
            poll.setAnswer1((String) row.get("answer1"));
            poll.setAnswer2((String) row.get("answer2"));
            poll.setAnswer3((String) row.get("answer3"));
            poll.setAnswer4((String) row.get("answer4"));
            poll.setCount1((Integer) row.get("count1"));
            poll.setCount2((Integer) row.get("count2"));
            poll.setCount3((Integer) row.get("count3"));
            poll.setCount4((Integer) row.get("count4"));
            polls.add(poll);
        }
        return polls;
    }

    private static final String SQL_UPDATE_POLL_1
            = "update poll set count1=count1+1 where poll_id=(SELECT MAX(poll_id) FROM poll)";

    private static final String SQL_UPDATE_POLL_2
            = "update poll set count2=count2+1 where poll_id=(SELECT MAX(poll_id) FROM poll)";

    private static final String SQL_UPDATE_POLL_3
            = "update poll set count3=count3+1 where poll_id=(SELECT MAX(poll_id) FROM poll)";

    private static final String SQL_UPDATE_POLL_4
            = "update poll set count4=count4+1 where poll_id=(SELECT MAX(poll_id) FROM poll)";

    @Override
    public void addUserPoll_1() {
        jdbcOp.update(SQL_UPDATE_POLL_1);
    }

    @Override
    public void addUserPoll_2() {
        jdbcOp.update(SQL_UPDATE_POLL_2);
    }

    @Override
    public void addUserPoll_3() {
        jdbcOp.update(SQL_UPDATE_POLL_3);
    }

    @Override
    public void addUserPoll_4() {
        jdbcOp.update(SQL_UPDATE_POLL_4);
    }

    private static final String SQL_SELECT_POLL_ID
            = "select * form poll where poll_id= ?";

    @Override
    public Poll findByPollId(int id) {
        return jdbcOp.queryForObject(SQL_SELECT_POLL_ID, new PollRowMapper(), id);
    }

    private static final class PollRowMapper
            implements RowMapper<Poll> {

        @Override
        public Poll mapRow(ResultSet rs, int i) throws SQLException {
            Poll poll = new Poll();
            poll.setId(rs.getInt("poll_id"));
            poll.setQuestion(rs.getString("Question"));
            poll.setAnswer1(rs.getString("Answer1"));
            poll.setAnswer2(rs.getString("Answer2"));
            poll.setAnswer3(rs.getString("Answer3"));
            poll.setAnswer4(rs.getString("Answer4"));
            poll.setCount1(rs.getInt("count1"));
            poll.setCount2(rs.getInt("count2"));
            poll.setCount3(rs.getInt("count3"));
            poll.setCount4(rs.getInt("count4"));
            return poll;
        }
    }
}
