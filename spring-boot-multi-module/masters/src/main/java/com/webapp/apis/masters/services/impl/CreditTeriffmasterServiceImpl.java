package com.webapp.apis.masters.services.impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.apis.masters.domain.CreditTeriffmaster;
import com.webapp.apis.masters.mapper.CreditTeriffmasterMapper;
import com.webapp.apis.masters.model.CreditTeriffmasterFormBean;
import com.webapp.apis.masters.model.SearchCriteriaFormBean;
import com.webapp.apis.masters.model.ServerSidePagination;
import com.webapp.apis.masters.repository.CreditTeriffmasterRepository;
import com.webapp.apis.masters.service.CreditTeriffmasterService;
import com.webapp.apis.masters.repository.specs.CreditTeriffMasterSpecification;
import com.webapp.apis.masters.repository.specs.SearchCriteria;
import com.webapp.apis.masters.repository.specs.SearchOperation;

@Service
public class CreditTeriffmasterServiceImpl implements CreditTeriffmasterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditTeriffmasterServiceImpl.class);
	@Inject
	private CreditTeriffmasterRepository creditTeriffmasterRepository;

	@Autowired
	@Qualifier("dataSource")
	private JdbcTemplate jdbcTemplateObject;

	/*
	 * @Override public List<CreditTeriffmasterFormBean> getAllCreditTeriffmaster()
	 * { List<CreditTeriffmaster> listCreditTeriffmaster =
	 * creditTeriffmasterRepository.findAllByOrderByIdDesc(); return
	 * CreditTeriffmasterMapper.mapDomainListToFormList(listCreditTeriffmaster);
	 * 
	 * }
	 */
	@Override
	public List<CreditTeriffmasterFormBean> getAllCreditTeriffmaster(ServerSidePagination serverSidePagination) {

		Page<CreditTeriffmaster> page = null;
		// Pageable requestedPage = PageRequest.of(serverSidePagination.getPage(),
		// serverSidePagination.getSize());
		Pageable requestedPage = PageRequest.of(serverSidePagination.getPage(), serverSidePagination.getSize(),
				Sort.by("id").descending());
		page = creditTeriffmasterRepository.findAllByOrderByIdDesc(requestedPage);
		return CreditTeriffmasterMapper.mapDomainListToFormList(page.getContent());
	}

	@Transactional
	@Override
	public String saveCreditTeriffmaster(CreditTeriffmasterFormBean creditTeriffmasterFormBean) {
		for(int i=0;i<creditTeriffmasterFormBean.getLocationIds().size();i++) {
			creditTeriffmasterFormBean.setLocationId(creditTeriffmasterFormBean.getLocationIds().get(i));
		CreditTeriffmaster obj = creditTeriffmasterRepository
				.save(CreditTeriffmasterMapper.mapFormToDomain(creditTeriffmasterFormBean));
		}
		return "Saved Successfully";
	}

	@Override
	public List<CreditTeriffmasterFormBean> findByWhereCondition(SearchCriteriaFormBean searchCriteriaFormBean) {
		CreditTeriffMasterSpecification msCreditRating = new CreditTeriffMasterSpecification();
		msCreditRating.add(new SearchCriteria(searchCriteriaFormBean.getKey(), searchCriteriaFormBean.getValue(),
				SearchOperation.valueOf(searchCriteriaFormBean.getOperation())));
		List<CreditTeriffmaster> creditTeriffMaster = creditTeriffmasterRepository.findAll(msCreditRating);

		return CreditTeriffmasterMapper.mapDomainListToFormList(creditTeriffMaster);
	}

	@Override
	public String conditionSlabs(CreditTeriffmasterFormBean creditTeriffmasterFormBean) {
		Connection con = null;
		
		try {
			String type = creditTeriffmasterFormBean.getType();
			String loc = creditTeriffmasterFormBean.getLocationId();
			String party_id = creditTeriffmasterFormBean.getPartyId() + "";
			String payment = creditTeriffmasterFormBean.getPaymentType() + "";
			String consign = creditTeriffmasterFormBean.getModeConsign() + "";
			double qty = Double.parseDouble(creditTeriffmasterFormBean.getFromWeight()+"");
			System.out.println("Qty" + qty);

			con = jdbcTemplateObject.getDataSource().getConnection();

			String qry = "SELECT rate,add_rate,add_qty,from_weight  from tariff_view1   where  from_weight<=" + qty
					+ " and to_weight>=" + qty + "  and Location_id='" + loc + "'  and  type='" + type
					+ "' and party_id='" + party_id + "' and payment_type='" + payment + "' and mode_consign='"
					+ consign + "'";
			System.out.println("Query==" + qry);

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(qry);
			if(rs.next()) {
			double rate = rs.getDouble("rate");
			double result;
			double add_r = rs.getDouble("add_rate");

			double frm = rs.getDouble("from_weight");
			double add_qty = rs.getDouble("add_qty");
			result = (qty - frm) / add_qty;
			result = Token(result);
			System.out.println("result===" + result);
			double amt = rate + (result * add_r);
			amt = roundTwoDecimals(amt);

			System.out.println("Result==" + amt);
			return amt+"";
			}

		} catch (SQLException ex) {

			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "0";
	}

	public double Token(double f) {
		String key = "0";
		String val = "0";
		double f2 = 0.00;
		String in = Double.toString(f);
		StringTokenizer st = new StringTokenizer(in, ".");
		while (st.hasMoreTokens()) {
			key = st.nextToken();
			val = st.nextToken();
			System.out.println(key + ":" + val);
		}
		double f1 = Double.valueOf(val.trim()).doubleValue();
		if (f1 > 0) {
			f2 = Double.valueOf(key.trim()).doubleValue();
			f2 = f2 + 1;
			System.out.println("f2==" + f2);
		} else {
			f2 = f;
		}
		return (f2);
	}

	private double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}

}
