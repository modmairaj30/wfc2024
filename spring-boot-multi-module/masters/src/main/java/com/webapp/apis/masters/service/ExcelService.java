package com.webapp.apis.masters.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.apis.masters.domain.Tutorial;
import com.webapp.apis.masters.repository.TutorialRepository;
import com.webapp.apis.utility.ExcelHelper;



@Service
public class ExcelService {
  @Autowired
  TutorialRepository repository;
  @Autowired
  @Qualifier("dataSource")
 private JdbcTemplate jdbcTemplateObject;

  public void save(MultipartFile file) {
    try {
      List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public void saveToTable(MultipartFile file,String table) {
	    try {
	      List<String> ls = ExcelHelper.excelToTable(file.getInputStream());
	      Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			
			try {
				StringBuilder query = new StringBuilder();
				conn = jdbcTemplateObject.getDataSource().getConnection();
				stmt = conn.createStatement();
				query.append("insert into "+table+" ("+ls.get(0).replaceAll("'", "")+") values ");
				// LOGGER.info("Query=" + query.toString());
				ls.remove(0);
				for(String value :ls) {
					
					query.append("("+value+"),");
				}
				
				stmt.execute(query.toString().substring(0,query.toString().length()-1));
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
				}
			}
			

	    } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }
  public ByteArrayInputStream load() {
    List<Tutorial> tutorials = repository.findAll();

    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
    return in;
  }

  public List<Tutorial> getAllTutorials() {
    return repository.findAll();
  }
}
