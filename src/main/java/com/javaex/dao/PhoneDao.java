package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;


	
@Repository
public class PhoneDao {

	@Autowired	
	private SqlSession sqlSession;

	//전체 리스트 가져오기
	public List<PersonVo> getPersonList(){
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		
		return personList;
		
	}


	
	//등록
	public int personInsert(PersonVo pvo) {
		
		System.out.println(pvo.toString());
		int count = sqlSession.insert("phonebook.insert", pvo);
		
		return count;
	}
	
	//삭제
	public int PersonDelete(int person_id) {
		
		System.out.println("dao: personDelete() " + person_id);
		int count = sqlSession.delete("phonebook.delete", person_id);
		System.out.println(count);
		
		return count;
	}
	
	
	//1명 데이터 가져오기
	public PersonVo getPerson(int person_id) {
		System.out.println("dao: getPerson()" + person_id);
		
		PersonVo pvo = sqlSession.selectOne("phonebook.selectOne", person_id);
		
		return pvo;
		
	}
	
	//1명 데이터 가져오기2
	public Map<String, Object> getPerson2(int person_id) {
		System.out.println("dao: getPerson2()" + person_id);
		
		Map<String,Object> pmap = sqlSession.selectOne("phonebook.selectOne2", person_id);
		System.out.println(pmap.toString());
		
		/*
		int id = Integer.parseInt((String.valueOf(pmap.get("person_id"))));
		System.out.println(id);
		
		String name = (String)pmap.get("name");
		System.out.println(name); 
		*/
		
		return pmap;
		
	}
	
	
	//수정
	public int personUpdate(PersonVo pvo) {
		System.out.println("dao:personUpdate() :" + pvo.toString());
		
		int count = sqlSession.update("phonebook.update",pvo);
		System.out.println(count);
		
		return count;
	}
	
	//수정2
	public int personUpdate2(int person_id, String name, String hp, String company) {
		System.out.println("dao: personUpdate2()" + person_id + "," + name + "," + hp + "," + company);
		
		//vo대신 -->map
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("person_id", person_id);
		pmap.put("name", name);
		pmap.put("hp", hp);
		pmap.put("company", company);
		
		System.out.println(pmap.toString());
		
		return sqlSession.update("phonebook.update2", pmap);
	
	}
	
}
