package com.blhx.community.service;

import com.blhx.community.dto.PageinationDTO;
import com.blhx.community.dto.QuestionDTO;
import com.blhx.community.mapper.QuestionMapper;
import com.blhx.community.mapper.UserMapper;
import com.blhx.community.model.Question;
import com.blhx.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageinationDTO list ( Integer page, Integer size ){
        Integer offset=size * (page - 1);
        List<Question> questions=questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        PageinationDTO pageinationDTO=new PageinationDTO();
        for (Question question : questions) {
            User user=userMapper.findBYID(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageinationDTO.setQuestions(questionDTOList);
        Integer totalCount=questionMapper.count();
        pageinationDTO.setPagination(totalCount,page,size);
        return pageinationDTO;
    }
}
