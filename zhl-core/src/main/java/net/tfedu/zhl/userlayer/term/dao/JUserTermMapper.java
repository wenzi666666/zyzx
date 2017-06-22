package net.tfedu.zhl.userlayer.term.dao;


import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.userlayer.term.entity.JUserTerm;

public interface JUserTermMapper extends CoreMapper<JUserTerm> {

    public void updateUserTerm(Long userId, Long termId);
}