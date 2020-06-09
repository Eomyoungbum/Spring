package com.coderby.myapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.member.model.MemberVO;

@Repository
public class MemberRepository implements IMemberRepository {

	@Autowired
	JdbcTemplate jt;
	
	@Override
	public void insertMember(MemberVO mem) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		jt.update(sql,mem.getUserId(),mem.getName(),mem.getPassword(),
				mem.getEmail(),mem.getAddress(),mem.getEnabled());
	}

	@Override
	public void insertAuth(String userId) {
		String sql = "insert into authorities values(?,?)";
		jt.update(sql,userId,"ROLE_USER");
	}
	
	@Override
	public MemberVO getMember(String userId) {
		String sql = "select m.userid, name, password, email, address,"
				+ "enabled, authority from member m "
				+ "join authorities au "
				+ "on m.userid=au.userid "
				+ "where m.userid=?";
		return jt.queryForObject(sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mem = new MemberVO();
				mem.setUserId(rs.getString("userid"));
				mem.setName(rs.getString("name"));
				mem.setPassword(rs.getString("password"));
				mem.setEmail(rs.getString("email"));
				mem.setAddress(rs.getString("address"));
				mem.setEnabled(rs.getInt("enabled"));
				mem.setAuth(rs.getString("authority"));
				return mem;
			}
		},userId);
	}

	@Override
	public String getPassword(String userId) {
		String sql = "select password from member where userid=?";
		return jt.queryForObject(sql, String.class, userId);
	}

}
