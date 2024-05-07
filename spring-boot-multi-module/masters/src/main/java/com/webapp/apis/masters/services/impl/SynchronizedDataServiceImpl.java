package com.webapp.apis.masters.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.apis.masters.constants.MastersConstants;
import com.webapp.apis.masters.domain.SynchronizedData;
import com.webapp.apis.masters.mapper.SynchronizedDataMapper;
import com.webapp.apis.masters.model.SynchronizedDataFormBean;
import com.webapp.apis.masters.repository.SynchronizedDataRepository;
import com.webapp.apis.masters.service.SynchronizedDataService;

@Service
public class SynchronizedDataServiceImpl implements SynchronizedDataService {

	@Inject
	SynchronizedDataRepository synchronizedDataRepository;
	@Value("${spring.interface.datasource.url}")
	public String url;
	@Value("${spring.interface.datasource.username}")
	public String username;
	
	@Value("${spring.interface.datasource.password}")
	public String password;
	
	@Autowired
	@Qualifier("dataSource")
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public List<SynchronizedDataFormBean> getAllSynchronizedData() {
		List<SynchronizedData> listscreenMaster = synchronizedDataRepository.findAll();
		List<SynchronizedDataFormBean> listscreen = new ArrayList<SynchronizedDataFormBean>();
		for (SynchronizedData synchronizedData : listscreenMaster) {
			SynchronizedDataFormBean synchronizedDataFormBean = new SynchronizedDataFormBean();
			BeanUtils.copyProperties(synchronizedData, synchronizedDataFormBean);
			listscreen.add(synchronizedDataFormBean);
		}

		return listscreen;
	}

	@Transactional
	@Override
	public String saveSynchronizedData(SynchronizedDataFormBean synchronizedDataFormBean) {
		SynchronizedData rolesMaster = new SynchronizedData();

		rolesMaster = synchronizedDataRepository.save(SynchronizedDataMapper.mapFormToDomain(synchronizedDataFormBean));

		if (rolesMaster != null) {
			return MastersConstants.SAVE;
		}
		return MastersConstants.FAIL;
	}

	@Override
	public Optional<SynchronizedData> getIdSynchronizedData(int n) {

		return synchronizedDataRepository.findById(n);
	}

	@Override
	public String controlSlabs(SynchronizedDataFormBean synchronizedDataFormBean) {
		SynchronizedData sd = new SynchronizedData();

		sd = synchronizedDataRepository.save(SynchronizedDataMapper.mapFormToDomain(synchronizedDataFormBean));

		if (sd != null) {
			return MastersConstants.SAVE;
		}
		return MastersConstants.FAIL;
	}

	@Override
	public String uploadTables(SynchronizedDataFormBean synchronizedDataFormBean) {
		Connection con = null;

		String qry = "";
		String base_qry = "";
		String det_qry = "";
		ArrayList<String> txn_base_id_arr = new ArrayList<String>();// txn_id
		HashMap<String, String> stock_map = new HashMap<String, String>();// for Stock

		HashMap<String, String> where_cond_map = new HashMap<String, String>();// for Wher Cond
		HashMap<String, String> base_txn_name_map = new HashMap<String, String>();// for base txn_name
		ArrayList<String> txn_base_data_arr = new ArrayList<String>();// for base txn data

		HashMap<String, String> det_txn_name_map = new HashMap<String, String>();// for det txn_name
		ArrayList<String> txn_det_data_arr = new ArrayList<String>();//// for det txn data

		ArrayList<String> id_arr = new ArrayList<String>();// it stores row id

		HashMap<String, ArrayList<String>> base_data_map = new HashMap<String, ArrayList<String>>();// id,base txn data
		HashMap<String, ArrayList<ArrayList<String>>> det_data_map = new HashMap<String, ArrayList<ArrayList<String>>>();// base_id,det
																															// txn
																															// data
		HashMap<String, String> id_txn = new HashMap<String, String>();// row id,txn_id

		String vch_main_flds = "";
		ArrayList<String> vch_main_arr = new ArrayList<String>();
		HashMap<String, ArrayList<String>> vch_main_map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> vch_det_arr = new ArrayList<String>();
		HashMap<String, ArrayList<ArrayList<String>>> vch_det_map = new HashMap<String, ArrayList<ArrayList<String>>>();

		String vch_det_flds = "";
		ArrayList<String> vch_id_arr = new ArrayList<String>();

		try {
			// out = response.getWriter();
			// response.setContentType("text/xml");
			// out.print("<result>");

			con = jdbcTemplateObject.getDataSource().getConnection();

			// con.setAutoCommit(false);
			Statement st = con.createStatement();
			Statement st_det = con.createStatement();
			Statement st_base_data = con.createStatement();
			Statement st_det_data = con.createStatement();
			Statement st_loc = con.createStatement();

			qry = "show columns from tbl_vchmain where field not in('vch_id')";
			Statement st_vch_main = con.createStatement();
			ResultSet rs_vch_main = st_vch_main.executeQuery(qry);
			while (rs_vch_main.next()) {
				vch_main_flds += rs_vch_main.getString("field") + ",";
			}
			vch_main_flds = vch_main_flds.substring(0, vch_main_flds.length() - 1);

			qry = "show columns from tbl_vchdet where field not in('vch_det_id')";
			Statement st_vch_det = con.createStatement();
			ResultSet rs_vch_det = st_vch_det.executeQuery(qry);
			while (rs_vch_det.next()) {
				vch_det_flds += rs_vch_det.getString("field") + ",";
			}
			vch_det_flds = vch_det_flds.substring(0, vch_det_flds.length() - 1);

			qry = "Select txn_id,txn_name,if(where_cond is null,'',where_cond) as where_cond,upload,download From tbl_upload_download where upload='YES'";
			ResultSet rs = st.executeQuery(qry);
			while (rs.next()) {
				where_cond_map.put(rs.getString("txn_id"), rs.getString("where_cond"));
				qry = "Select * from tbl_transaction where txn_id=" + rs.getInt("txn_id") + " or txn_parent="
						+ rs.getInt("txn_id") + " ";
				ResultSet rs_det = st_det.executeQuery(qry);
				System.out.println("MAIN QRY============" + qry);
				while (rs_det.next()) {

					if (rs_det.getString("txn_type").equals("base")) {
						if (!txn_base_id_arr.contains(rs_det.getString("txn_id"))) {
							txn_base_id_arr.add(rs_det.getString("txn_id"));
						}
						base_txn_name_map.put(rs_det.getString("txn_id"), rs_det.getString("txn_name"));
					} else if (rs_det.getString("txn_type").equals("detail")) {
						det_txn_name_map.put(rs_det.getString("txn_parent"), rs_det.getString("txn_name"));
					}

				}

			}

			System.out.println("SIZE====================" + txn_base_id_arr.size());
			ArrayList<String> upload_ids = new ArrayList<String>();
			for (int i = 0; i < txn_base_id_arr.size(); i++) {
				if (base_txn_name_map.containsKey(txn_base_id_arr.get(i))) {
					System.out.println("WHERE CONDITION===============" + where_cond_map.get(txn_base_id_arr.get(i)));
					if (where_cond_map.containsKey(txn_base_id_arr.get(i))
							&& !where_cond_map.get(txn_base_id_arr.get(i)).equals("")) {
						base_qry = "select * from " + base_txn_name_map.get(txn_base_id_arr.get(i))
								+ " where upload_flag='false' and " + where_cond_map.get(txn_base_id_arr.get(i));
					} else {
						base_qry = "select * from " + base_txn_name_map.get(txn_base_id_arr.get(i))
								+ " where upload_flag='false'";
					}
					System.out.println("Base Qry===" + base_qry);
					ResultSet rs_base_data = st_base_data.executeQuery(base_qry);
					ResultSetMetaData rsmd_base = rs_base_data.getMetaData();
					int base_col_count = rsmd_base.getColumnCount();
					// vch_main_map.clear();

					while (rs_base_data.next()) {
						txn_base_data_arr = new ArrayList<String>();
						vch_main_arr = new ArrayList<String>();
						String id = "";
						for (int b = 1; b <= base_col_count; b++) {

							if (rsmd_base.getColumnName(b).equals("id")) {
								id = rs_base_data.getString(b) + "_TM_" + txn_base_id_arr.get(i);
								upload_ids.add(rs_base_data.getString(b));
								id_arr.add(id);
								id_txn.put(id, txn_base_id_arr.get(i));
							} else if (!rsmd_base.getColumnName(b).equals("upload_flag")) {
								if (rs_base_data.getString(b) != null) {
									txn_base_data_arr.add("'" + rs_base_data.getString(b) + "'");
								} else {
									txn_base_data_arr.add(rs_base_data.getString(b));
								}

							}
							if (rsmd_base.getColumnName(b).equals("vch_id")) {
								if (rs_base_data.getString(b) != null) {

									vch_id_arr.add(rs_base_data.getString(b));
									String vch_main_qry = "select " + vch_main_flds + " from tbl_vchmain where vch_id="
											+ rs_base_data.getString(b);
									System.out.println("VCH MAIN QRY======================" + vch_main_qry);
									Statement st_vch_main_data = con.createStatement();
									ResultSet rs_vch_main_data = st_vch_main_data.executeQuery(vch_main_qry);
									ResultSetMetaData rsmd_main = rs_vch_main_data.getMetaData();
									int vch_main_colcount = rsmd_main.getColumnCount();
									while (rs_vch_main_data.next()) {
										for (int vm = 1; vm <= vch_main_colcount; vm++) {
											if (rs_vch_main_data.getString(vm) != null) {
												vch_main_arr.add("'" + rs_vch_main_data.getString(vm) + "'");
											} else {
												vch_main_arr.add(rs_vch_main_data.getString(vm));
											}
										}
									}
									vch_main_map.put(rs_base_data.getString(b), vch_main_arr);
									String vch_det_qry = "select " + vch_det_flds
											+ " from tbl_vchdet where vch_main_id=" + rs_base_data.getString(b);
									Statement st_vch_det_data = con.createStatement();
									ResultSet rs_vch_det_data = st_vch_det_data.executeQuery(vch_det_qry);
									ResultSetMetaData rsmd_det = rs_vch_det_data.getMetaData();
									int vch_det_colcount = rsmd_det.getColumnCount();
									while (rs_vch_det_data.next()) {
										vch_det_arr = new ArrayList<String>();
										for (int vd = 1; vd <= vch_det_colcount; vd++) {
											if (!rsmd_det.getColumnName(vd).equals("vch_main_id")) {
												if (rs_vch_det_data.getString(vd) != null) {
													vch_det_arr.add("'" + rs_vch_det_data.getString(vd) + "'");
												} else {
													vch_det_arr.add(rs_vch_det_data.getString(vd));
												}
											}
										}
										ArrayList<ArrayList<String>> vch_det_data = new ArrayList<ArrayList<String>>();
										if (vch_det_map.containsKey(rs_base_data.getString(b))) {
											vch_det_data = vch_det_map.get(rs_base_data.getString(b));
											vch_det_map.remove(rs_base_data.getString(b));
										}
										vch_det_data.add(vch_det_arr);
										vch_det_map.put(rs_base_data.getString(b), vch_det_data);
									}
								}
							}

						}
						base_data_map.put(id, txn_base_data_arr);

					}

				}
				/*
				 * String
				 * upload_base_qry="update "+base_txn_name_map.get(txn_base_id_arr.get(i))
				 * +" set upload_flag='true' where id  in ("+upload_ids+")";
				 * upload_base_qry=upload_base_qry.replace("[","");
				 * upload_base_qry=upload_base_qry.replace("]","");
				 * st_loc.executeUpdate(upload_base_qry); sandeep 28-04-2014
				 */

				// System.out.println("Upload Update Qry====="+upload_base_qry);
				System.out.println("Upload Updated Ids===============" + upload_ids);
				if(upload_ids.size()>0)
				if (det_txn_name_map.containsKey(txn_base_id_arr.get(i))) {
					det_qry = "select * from " + det_txn_name_map.get(txn_base_id_arr.get(i)) + " where base_id in ("
							+ upload_ids + ")";
					det_qry = det_qry.replace("[", "");
					det_qry = det_qry.replace("]", "");
					System.out.println("Det Qry===" + det_qry);
					ResultSet rs_det_data = st_det_data.executeQuery(det_qry);
					ResultSetMetaData rsmd_det = rs_det_data.getMetaData();
					int det_col_count = rsmd_det.getColumnCount();
					while (rs_det_data.next()) {
						txn_det_data_arr = new ArrayList<String>();
						String base_id = "";
						for (int d = 1; d <= det_col_count; d++) {
							if (rsmd_det.getColumnName(d).equals("base_id")) {
								base_id = rs_det_data.getString(d);
								id_txn.put(base_id, txn_base_id_arr.get(i));
							}
							if (!rsmd_det.getColumnName(d).equals("id")
									&& !rsmd_det.getColumnName(d).equals("base_id")) {
								if (rs_det_data.getString(d) != null) {
									txn_det_data_arr.add("'" + rs_det_data.getString(d) + "'");
								} else {
									txn_det_data_arr.add(rs_det_data.getString(d));
								}
							}

						}
						ArrayList<ArrayList<String>> base_data = new ArrayList<ArrayList<String>>();
						if (det_data_map.containsKey(base_id)) {
							base_data = det_data_map.get(base_id);
							det_data_map.remove(base_id);
						}
						base_data.add(txn_det_data_arr);
						det_data_map.put(base_id, base_data);

					}

				}

			}

			Connection con_server = this.getConnection();
			// Connection con_server=ds.getConnection();
			
			String server_qry = "";
			String server_base_txn = "";
			String server_det_txn = "";
			String base_col = "";
			String det_col = "";
			String stock_flds = "";

			System.out.println("Id's Length=====" + id_arr.size());

			for (int n = 0; n < txn_base_id_arr.size(); n++) {
				if (base_txn_name_map.containsKey(txn_base_id_arr.get(n))) {
					base_col = "";
					server_base_txn = base_txn_name_map.get(txn_base_id_arr.get(n));
					qry = "show columns from " + server_base_txn + " where field not in( 'id','upload_time','upload_flag')";
					Statement st_ser = con_server.createStatement();
					ResultSet rs_server = st_ser.executeQuery(qry);
					while (rs_server.next()) {
						base_col += rs_server.getString("field") + ",";
					}
					base_col = base_col.substring(0, base_col.length() - 1);
				}
				if (det_txn_name_map.containsKey(txn_base_id_arr.get(n))) {
					det_col = "";
					server_det_txn = det_txn_name_map.get(txn_base_id_arr.get(n));
					qry = "show columns from " + server_det_txn + " where field not in( 'id')";
					Statement st_ser = con_server.createStatement();
					ResultSet rs_server = st_ser.executeQuery(qry);
					while (rs_server.next()) {
						det_col += rs_server.getString("field") + ",";
					}
					det_col = det_col.substring(0, det_col.length() - 1);
				}
				String stock_qry = "";
				for (int k = 0; k < id_arr.size(); k++) {
					if (base_data_map.containsKey(id_arr.get(k)) && id_txn.containsKey(id_arr.get(k))
							&& txn_base_id_arr.get(n).equals(id_arr.get(k).split("_TM_")[1].toString())) {

						server_qry = "insert into " + server_base_txn + "  (" + base_col + ") values ("
								+ base_data_map.get(id_arr.get(k)) + ") ";
						server_qry = server_qry.replace("[", "");
						server_qry = server_qry.replace("]", "");
						System.out.println("Server Base Qry===" + server_qry);
						Statement st_base_qry = con_server.createStatement();
						st_base_qry.executeUpdate(server_qry,Statement.RETURN_GENERATED_KEYS);
						ResultSet numero = st_base_qry.getGeneratedKeys();

						if (numero.next()) {
							String upload_base_qry = "update " + server_base_txn + " set upload_flag='true' where id ='"
									+ id_arr.get(k).split("_TM_")[0].toString() + "'";
							st_loc.executeUpdate(upload_base_qry);
							System.out.println("update qry===" + upload_base_qry);

							System.out.println("id_arrr====" + id_arr.get(k) + "=====det_data_map==="
									+ det_data_map.containsKey(id_arr.get(k)) + "====id_txn===="
									+ id_txn.containsKey(id_arr.get(k)));
							System.out.println("ddddd===" + det_data_map.toString());
							if (det_data_map.containsKey(id_arr.get(k).split("_TM_")[0].toString())
									&& id_txn.containsKey(id_arr.get(k))) {
								ArrayList<ArrayList<String>> det_arrc = new ArrayList<ArrayList<String>>();
								det_arrc = det_data_map.get(id_arr.get(k).split("_TM_")[0].toString());

								for (int l = 0; l < det_arrc.size(); l++) {
									server_qry = "insert into " + server_det_txn + "  (" + det_col + ") values ('"
											+ numero.getInt(1) + "'," + det_arrc.get(l) + ") ";
									server_qry = server_qry.replace("[", "");
									server_qry = server_qry.replace("]", "");
									Statement st_det_qry = con_server.createStatement();
									System.out.println("Server Det Qry===" + server_qry);
									st_det_qry.executeUpdate(server_qry);

								}
								// Return parent Array collection that contains child Array list we have to loop
								// this parent Array List and we get child Array List,and directly we insert
								// this child array collection into server det table
							}

							Statement st_txnname = con_server.createStatement();
							String base = "";
							String details = "";
							String txn_fun = "NEUTRAL";
							ResultSet rs_txnname = st_txnname.executeQuery(
									"select txn_name,txn_type,txn_function from tbl_transaction where txn_id='"
											+ id_txn.get(id_arr.get(k)) + "' or txn_parent='"
											+ id_txn.get(id_arr.get(k)) + "'");
							while (rs_txnname.next()) {
								if (rs_txnname.getString("txn_type").equals("base")) {
									base = rs_txnname.getString("txn_name");
									txn_fun = rs_txnname.getString("txn_function");
								} else
									details = rs_txnname.getString("txn_name");
							}
							if (!txn_fun.equals("NEUTRAL")) {

								String column_names = "";// "doc_no,doc_dt,party_id,product_id,location_id,batch_no,batch_tracker,qty,rate,exp_dt,";
								ResultSet rs_fldname = st_txnname.executeQuery(
										"select fld_name  from view_transaction_details where txn_name in('" + base
												+ "','" + details
												+ "') and (fld_name like 'fld_%' or fld_name in ('doc_no','doc_dt','party_id','product_id','location_id','batch_no','batch_tracker','qty','rate','exp_dt'))");
								while (rs_fldname.next()) {
									column_names = column_names + rs_fldname.getString("fld_name") + ",";

								}
								if (details.equals(""))
									qry = "insert into tbl_stock_info(" + column_names
											+ "txn_function,txn_id,primary_key)   select " + column_names
											+ "@txn_fun:='" + txn_fun + "' as txn_function,@txn_id:="
											+ id_txn.get(id_arr.get(k)) + " as txn_id,@primary:=" + numero.getInt(1)
											+ " as primary_key    from  " + base + "  where  id=" + numero.getInt(1);
								else
									qry = "insert into tbl_stock_info(" + column_names
											+ "foriegn_key,txn_function,txn_id,primary_key)   select " + column_names
											+ details + ".id,@txn_fun:='" + txn_fun + "' as txn_function,@txn_id:="
											+ id_txn.get(id_arr.get(k)) + " as txn_id,@primary:=" + numero.getInt(1)
											+ " as primary_key    from  " + base + "," + details + "  where  " + base
											+ ".id=" + details + ".base_id and base_id=" + numero.getInt(1);

								stock_qry = "select " + column_names + details + ".id,@txn_fun:='" + txn_fun
										+ "' as txn_function,@txn_id:=" + id_txn.get(id_arr.get(k))
										+ " as txn_id,@primary:=" + numero.getInt(1) + " as primary_key    from  "
										+ base + "," + details + "  where  " + base + ".id=" + details
										+ ".base_id and base_id=" + numero.getInt(1);
								 System.out.println("Stock qry==="+qry);
								st_txnname.executeUpdate(qry);
								Statement st_update = con_server.createStatement();
								ResultSet rs_update = st_update.executeQuery(
										"Select * from tbl_stock_info where txn_id='" + id_txn.get(id_arr.get(k))
												+ "' and primary_key='" + numero.getInt(1) + "'");
								while (rs_update.next()) {
									Statement st_last = con_server.createStatement();
									Float qty = rs_update.getFloat("qty");
									if (rs_update.getString("txn_function").equals("MINUS"))
										qty = -qty;

									System.out.println("Qty==" + qty);

									ResultSet rs_cum = st_last.executeQuery(
											"select cum_qty from tbl_stock_info where id=(select max(id) from tbl_stock_info where  location_id='"
													+ rs_update.getString("location_id") + "' and product_id='"
													+ rs_update.getString("product_id") + "' and batch_no='"
													+ rs_update.getString("batch_no") + "'  and cum_qty is not null)");
									Statement st_cum = con_server.createStatement();
									if (rs_cum.next())
										st_cum.executeUpdate(
												"update tbl_stock_info  set cum_qty=" + rs_cum.getFloat("cum_qty") + "+"
														+ qty + " where id=" + rs_update.getInt("ID"));

									else
										st_cum.executeUpdate("update tbl_stock_info  set cum_qty=" + qty + " where id="
												+ rs_update.getInt("ID"));

									if(rs_update.getString("location_id")!=null) {
									String uquery="update tbl_stock_info set last_row='FALSE' where location_id='"
											+ rs_update.getString("location_id") + "' and product_id='"
											+ rs_update.getString("product_id") + "' and batch_no='"
											+ rs_update.getString("batch_no") + "'  and id<>'"
											+ rs_update.getInt("ID") + "' ";
									st_cum.executeUpdate(uquery);
									}
								}
								// UpdateOnlineStock(tranxID, con, parentid);
							}

						}
					}
				}

			}
			System.out.println("LENGTH OF VCH IDS==============" + vch_id_arr.size());
			for (int v_id = 0; v_id < vch_id_arr.size(); v_id++) {
				if (vch_main_map.containsKey(vch_id_arr.get(v_id))) {
					qry = "insert into tbl_vchmain (" + vch_main_flds + ") values ("
							+ vch_main_map.get(vch_id_arr.get(v_id)) + ") ";
					qry = qry.replace("[", "");
					qry = qry.replace("]", "");
					System.out.println("Server Vch Main Qry===" + qry);
					Statement st_vch_main_qry = con_server.createStatement();
					st_vch_main_qry.executeUpdate(qry);
					ResultSet numero_vchmain = st_vch_main_qry.getGeneratedKeys();
					if (numero_vchmain.next()) {
						if (vch_det_map.containsKey(vch_id_arr.get(v_id))) {
							ArrayList<ArrayList<String>> det_arrc_v = new ArrayList<ArrayList<String>>();
							det_arrc_v = vch_det_map.get(vch_id_arr.get(v_id));

							for (int l = 0; l < det_arrc_v.size(); l++) {
								server_qry = "insert into tbl_vchdet  (" + vch_det_flds + ") values ('"
										+ numero_vchmain.getInt(1) + "'," + det_arrc_v.get(l) + ") ";
								server_qry = server_qry.replace("[", "");
								server_qry = server_qry.replace("]", "");
								Statement st_det_qry1 = con_server.createStatement();
								System.out.println("Server Vch Det Qry===" + server_qry);
								st_det_qry1.executeUpdate(server_qry);

							}
						}
					}
				}
			}
			// out.print("success");
		} catch (Exception ex) {
			// con.rollback();
			// out.print("<error>"+ex.getMessage()+"</error>");
			// out.print("fail");
			ex.printStackTrace();
		} finally {
			System.out.println("query == " + qry);
			// out.print("</result>");
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	@Override
	public String downloadTables(SynchronizedDataFormBean synchronizedDataFormBean) {

		Connection con = null;
		// PrintWriter out = null;
		Connection con_server = null;

		String server_qry = "";
		ArrayList<String> txn_id_arr = new ArrayList<String>();
		ArrayList<String> txt_name_arr = new ArrayList<String>();
		ArrayList<String> txn_base_arr = new ArrayList<String>();
		ArrayList<String> txn_det_arr = new ArrayList<String>();
		String qry = "";
		String base_qry = "";
		String det_qry = "";
		ArrayList<String> txn_base_id_arr = new ArrayList<String>();// txn_id
		HashMap<String, String> stock_map = new HashMap<String, String>();// for Stock

		HashMap<String, String> where_cond_map = new HashMap<String, String>();// for Wher Cond
		HashMap<String, String> base_txn_name_map = new HashMap<String, String>();// for base txn_name
		ArrayList<String> txn_base_data_arr = new ArrayList<String>();// for base txn data

		HashMap<String, String> det_txn_name_map = new HashMap<String, String>();// for det txn_name
		ArrayList<String> txn_det_data_arr = new ArrayList<String>();//// for det txn data

		ArrayList<String> id_arr = new ArrayList<String>();// it stores row id

		HashMap<String, ArrayList<ArrayList<String>>> base_data_map = new HashMap<String, ArrayList<ArrayList<String>>>();
		HashMap<String, ArrayList<ArrayList<String>>> det_data_map = new HashMap<String, ArrayList<ArrayList<String>>>();// base_id,det
																															// txn
																															// data
		HashMap<String, String> id_txn = new HashMap<String, String>();// row id,txn_id
		HashMap<String, String> id_txn_name = new HashMap<String, String>();

		String vch_main_flds = "";
		ArrayList<String> vch_main_arr = new ArrayList<String>();
		HashMap<String, ArrayList<String>> vch_main_map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> vch_det_arr = new ArrayList<String>();
		HashMap<String, ArrayList<ArrayList<String>>> vch_det_map = new HashMap<String, ArrayList<ArrayList<String>>>();

		String vch_det_flds = "";
		ArrayList<String> vch_id_arr = new ArrayList<String>();

		try {
			// out = response.getWriter();
			// response.setContentType("text/xml");
			// out.print("<result>");

			con = jdbcTemplateObject.getDataSource().getConnection();
			System.out.println("*********************IN DOWNLOADTABLES***********************************");
			Statement st = con.createStatement();
			Statement st_del = con.createStatement();

			qry = "select txn_id,txn_name,if(where_cond is null,'',where_cond) as where_cond,upload,download,if(download_flag is null,'',download_flag) as download_flag from tbl_upload_download where download='YES'";
			ResultSet rs = st.executeQuery(qry);
			HashMap<String, String> hs_where = new HashMap<String, String>();
			HashMap<String, String> hs_txn_func = new HashMap<String, String>();
			HashMap<String, ArrayList<String>> hs_txn_id = new HashMap<String, ArrayList<String>>();
			HashMap<String, String> hs_det_txn = new HashMap<String, String>();
			HashMap<String, String> hs_down_load = new HashMap<String, String>();
			HashMap<String, String> hs_update = new HashMap<String, String>();

			while (rs.next()) {
				txn_id_arr.add(rs.getString("txn_id"));
				qry = "SELECT * FROM tbl_transaction WHERE txn_id=" + rs.getString("txn_id") + " OR txn_parent="
						+ rs.getString("txn_id");
				ResultSet rs_det = st_del.executeQuery(qry);
				String where_base = "";

				while (rs_det.next()) {
					if (rs.getString("download_flag").equalsIgnoreCase("yes")) {
						hs_down_load.put(rs_det.getString("txn_name"), rs_det.getString("txn_name"));

					}

					if (rs_det.getString("txn_type").equals("base")) {
						txn_base_arr.add(rs_det.getString("txn_name"));
						hs_where.put(rs_det.getString("txn_name"), rs.getString("where_cond"));
						hs_txn_func.put(rs_det.getString("txn_name"), rs_det.getString("txn_function"));
						where_base = rs_det.getString("txn_name");
						id_txn_name.put(rs_det.getString("txn_name").toString(), rs_det.getString("txn_id"));
					} else if (rs_det.getString("txn_type").equals("detail")) {
						txn_det_arr.add(rs_det.getString("txn_name"));
						if (!rs.getString("where_cond").equals(""))
							hs_where.put(rs_det.getString("txn_name"), " base_id in ( Select id from " + where_base
									+ " where " + rs.getString("where_cond") + ")");
						hs_det_txn.put(where_base, rs_det.getString("txn_name"));
					}
				}

			}

			// javax.sql.DataSource ds_server =
			con_server = this.getConnection();
			Statement st_server = con_server.createStatement();
			ResultSet rs_server = null;
			String base_col_names = "";
			Statement st_base_data = con_server.createStatement();
			Statement st_det_data = con_server.createStatement();

			System.out.println("LENGTH =============================" + txn_base_arr.size());
			for (int b1 = 0; b1 < txn_base_arr.size(); b1++) {
				String where_cond = hs_where.get(txn_base_arr.get(b1).toString());
				if (!where_cond.equals(""))
					where_cond = " where " + where_cond;
				if (hs_down_load.containsKey(txn_base_arr.get(b1).toString())) {
					if (where_cond.equals(""))
						where_cond = " where  upload_flag='YES'";
					else
						where_cond += " and  upload_flag='YES'";
				}

				base_qry = "select * from " + txn_base_arr.get(b1) + where_cond;
				System.out.println("base query====" + base_qry);
				ResultSet rs_base_data = st_base_data.executeQuery(base_qry);
				ResultSetMetaData rsmd_base = rs_base_data.getMetaData();
				int base_col_count = rsmd_base.getColumnCount();
				ArrayList<String> base_id = new ArrayList<String>();
				while (rs_base_data.next()) {
					ArrayList<ArrayList<String>> base_data_mul = new ArrayList<ArrayList<String>>();
					txn_base_data_arr = new ArrayList<String>();
					vch_main_arr = new ArrayList<String>();

					for (int b = 1; b <= base_col_count; b++) {
						if (rs_base_data.getString(b) != null) {
							txn_base_data_arr.add("'" + rs_base_data.getString(b) + "'");
						} else {
							txn_base_data_arr.add(rs_base_data.getString(b));
						}
					}

					if (base_data_map.containsKey(txn_base_arr.get(b1))) {
						base_data_mul = base_data_map.get(txn_base_arr.get(b1));
						base_data_map.remove(txn_base_arr.get(b1));
					}
					base_data_mul.add(txn_base_data_arr);
					base_data_map.put(txn_base_arr.get(b1), base_data_mul);
					base_id.add(rs_base_data.getString("id"));

				}
				hs_txn_id.put(txn_base_arr.get(b1).toString(), base_id);
				if (hs_down_load.containsKey(txn_base_arr.get(b1).toString())) {
					hs_update.put(txn_base_arr.get(b1).toString(),
							"update " + txn_base_arr.get(b1).toString() + " set  upload_flag='NO' " + where_cond);
				}

			}

			for (int d = 0, m = 0; d < txn_det_arr.size(); d++) {
				String where_cond_det = "";
				if (hs_where.containsKey(txn_det_arr.get(d)))
					where_cond_det = "  where " + hs_where.get(txn_det_arr.get(d));

				if (hs_down_load.containsKey(txn_det_arr.get(d).toString())) {
					if (where_cond_det.equals(""))
						where_cond_det = " where  upload_flag='YES'";
					else
						where_cond_det = where_cond_det.replace("))", ")") + " and  upload_flag='YES')";
				}

				det_qry = "select * from " + txn_det_arr.get(d) + where_cond_det;
				System.out.println("164====" + det_qry);
				ResultSet rs_det_data = st_det_data.executeQuery(det_qry);
				ResultSetMetaData rsmd_det = rs_det_data.getMetaData();
				int det_col_count = rsmd_det.getColumnCount();
				while (rs_det_data.next()) {
					txn_det_data_arr = new ArrayList<String>();
					String base_id = "";
					for (int d1 = 1; d1 <= det_col_count; d1++) {

						if (rs_det_data.getString(d1) != null) {
							txn_det_data_arr.add("'" + rs_det_data.getString(d1) + "'");
						} else {
							txn_det_data_arr.add(rs_det_data.getString(d1));
						}

					}
					ArrayList<ArrayList<String>> base_data = new ArrayList<ArrayList<String>>();
					if (det_data_map.containsKey(txn_det_arr.get(d))) {
						base_data = det_data_map.get(txn_det_arr.get(d));
						det_data_map.remove(txn_det_arr.get(d));
					}
					base_data.add(txn_det_data_arr);
					det_data_map.put(txn_det_arr.get(d), base_data);

				}

			}

			for (int b1 = 0; b1 < txn_base_arr.size(); b1++) {
				if (hs_update.containsKey(txn_base_arr.get(b1).toString())) {
					st_base_data.executeUpdate(hs_update.get(txn_base_arr.get(b1).toString()));
				}

			}

			con_server.close();

			Statement st_delete = con.createStatement();
			Statement st_update = con.createStatement();
			for (int d = 0; d < txn_det_arr.size(); d++) {

				if (!hs_down_load.containsKey(txn_det_arr.get(d).toString())) {

					qry = "delete from " + txn_det_arr.get(d);
					System.out.println("DELETE DET QRY===" + qry);
					st_delete.executeUpdate(qry);
					/*
					 * qry="ALTER TABLE "+txn_det_arr.get(d)+" MODIFY COLUMN `id` INT(10) NOT NULL";
					 * st_update.executeUpdate(qry);
					 */
					qry = "SHOW INDEX FROM " + txn_det_arr.get(d) + " WHERE Key_name like 'PRIMARY'";
					Statement st_index_det = con.createStatement();
					ResultSet rs_index_det = st_index_det.executeQuery(qry);
					if (rs_index_det.next()) {
						qry = "ALTER TABLE " + txn_det_arr.get(d) + " CHANGE COLUMN "
								+ rs_index_det.getString("Column_name")+" "+rs_index_det.getString("Column_name") + " INT NOT NULL";
						st_update.executeUpdate(qry);
					}
				}
			}
			for (int b1 = 0; b1 < txn_base_arr.size(); b1++) {

				if (!hs_down_load.containsKey(txn_base_arr.get(b1))) {

					qry = "delete from " + txn_base_arr.get(b1);
					System.out.println("DELETE BASE QRY===" + qry);
					st_delete.executeUpdate(qry);
					/*
					 * qry="ALTER TABLE "+txn_base_arr.get(b1)
					 * +" MODIFY COLUMN `id` INT(10) NOT NULL"; st_update.executeUpdate(qry);
					 */
					qry = "SHOW INDEX FROM " + txn_base_arr.get(b1) + " WHERE Key_name like 'PRIMARY'";
					Statement st_index = con.createStatement();
					ResultSet rs_index = st_index.executeQuery(qry);
					if (rs_index.next()) {
						qry = "ALTER TABLE " + txn_base_arr.get(b1) + " CHANGE COLUMN "
								+ rs_index.getString("Column_name")+"  "+rs_index.getString("Column_name") + " INT NOT NULL";
						st_update.executeUpdate(qry);
					}
				}
			}

			Statement st_base_ins = con.createStatement();
			Statement st_det_ins = con.createStatement();
			String base_col = "";
			Statement st_base_qry = con.createStatement();

			for (int b = 0; b < txn_base_arr.size(); b++) {
				base_col = "";
				if (base_data_map.containsKey(txn_base_arr.get(b))) {
					base_col = "";
					qry = "show columns from " + txn_base_arr.get(b);
					Statement st_ser = con.createStatement();
					ResultSet rs_base_col = st_ser.executeQuery(qry);
					while (rs_base_col.next()) {
						base_col += rs_base_col.getString("field") + ",";
					}
					base_col = base_col.substring(0, base_col.length() - 1);
					ArrayList<ArrayList<String>> base_arrc = new ArrayList<ArrayList<String>>();
					base_arrc = base_data_map.get(txn_base_arr.get(b));

					for (int v = 0; v < base_arrc.size(); v++) {
						qry = "insert into " + txn_base_arr.get(b) + "  (" + base_col + ") values (" + base_arrc.get(v)
								+ ")";
						qry = qry.replace("[", "");
						qry = qry.replace("]", "");
						System.out.println("Server Base Qry===" + qry);
						st_base_qry.executeUpdate(qry);

					}
				}
			}

			for (int d = 0; d < txn_det_arr.size(); d++) {
				if (det_data_map.containsKey(txn_det_arr.get(d))) {
					ArrayList<ArrayList<String>> det_arrc = new ArrayList<ArrayList<String>>();
					det_arrc = det_data_map.get(txn_det_arr.get(d));

					for (int l = 0; l < det_arrc.size(); l++) {
						qry = "insert into " + txn_det_arr.get(d) + "  values (" + det_arrc.get(l) + ") ";
						qry = qry.replace("[", "");
						qry = qry.replace("]", "");
						Statement st_det_qry = con.createStatement();
						System.out.println("Server Det Qry Before===" + qry);
						st_det_qry.executeUpdate(qry);

					}
					// Return parent Array collection that contains child Array list we have to loop
					// this parent Array List and we get child Array List,and directly we insert
					// this child array collection into server det table
				}
			}

			Statement st_update_tab = con.createStatement();
			for (int d = 0; d < txn_det_arr.size(); d++) {
				// qry="ALTER TABLE "+txn_det_arr.get(d)+" MODIFY COLUMN `id` INT(10) NOT NULL
				// DEFAULT NULL AUTO_INCREMENT";
				// st_update_tab.executeUpdate(qry);
				qry = "SHOW INDEX FROM " + txn_det_arr.get(d) + " WHERE Key_name like 'PRIMARY'";
				Statement st_index_det = con.createStatement();
				ResultSet rs_index_det = st_index_det.executeQuery(qry);
				if (rs_index_det.next()) {
					qry = "ALTER TABLE " + txn_det_arr.get(d)
							+ " CHANGE COLUMN `id`  `id` INT NOT NULL  AUTO_INCREMENT";
					st_update_tab.executeUpdate(qry);
				}
			}
			for (int b1 = 0; b1 < txn_base_arr.size(); b1++) {
				// qry="ALTER TABLE "+txn_base_arr.get(b1)+" MODIFY COLUMN `id` INT(10) NOT NULL
				// DEFAULT NULL AUTO_INCREMENT";
				// st_update_tab.executeUpdate(qry);
				qry = "SHOW INDEX FROM " + txn_base_arr.get(b1) + " WHERE Key_name like 'PRIMARY'";
				Statement st_index = con.createStatement();
				ResultSet rs_index = st_index.executeQuery(qry);
				if (rs_index.next()) {
					qry = "ALTER TABLE " + txn_base_arr.get(b1) + " CHANGE COLUMN " + rs_index.getString("Column_name")+"  "+ rs_index.getString("Column_name")
							+ " INT  NOT NULL  AUTO_INCREMENT";
					st_update_tab.executeUpdate(qry);
				}

			}

			for (int b1 = 0; b1 < txn_base_arr.size(); b1++) {
				String txn_fun = hs_txn_func.get(txn_base_arr.get(b1).toString());

				if (!txn_fun.equals("NEUTRAL")) {
					Statement st_txnname = con.createStatement();
					String stock_qry = "";
					String base = txn_base_arr.get(b1).toString();
					String details = hs_det_txn.get(base);

					String column_names = "";// "doc_no,doc_dt,party_id,product_id,location_id,batch_no,batch_tracker,qty,rate,exp_dt,";
					ResultSet rs_fldname = st_txnname
							.executeQuery("select fld_name  from view_transaction_details where txn_name in('" + base
									+ "','" + details
									+ "') and (fld_name like 'fld_%' or fld_name in ('doc_no','doc_dt','party_id','product_id','location_id','batch_no','batch_tracker','qty','rate','exp_dt'))");
					while (rs_fldname.next()) {
						column_names = column_names + rs_fldname.getString("fld_name") + ",";

					}

					ArrayList<String> all_base_ids = new ArrayList<String>();
					all_base_ids = hs_txn_id.get(base);

					for (int k = 0; k < all_base_ids.size(); k++) {

						if (details.equals(""))
							qry = "insert into tbl_stock_info(" + column_names
									+ "txn_function,txn_id,primary_key)   select " + column_names + "@txn_fun:='"
									+ txn_fun + "' as txn_function,@txn_id:="
									+ id_txn_name.get(txn_base_arr.get(b1).toString()) + " as txn_id,@primary:="
									+ all_base_ids.get(k) + " as primary_key    from  " + base + "  where  id="
									+ all_base_ids.get(k);
						else
							qry = "insert into tbl_stock_info(" + column_names
									+ "foriegn_key,txn_function,txn_id,primary_key)   select " + column_names + details
									+ ".id,@txn_fun:='" + txn_fun + "' as txn_function,@txn_id:="
									+ id_txn_name.get(txn_base_arr.get(b1).toString()) + " as txn_id,@primary:="
									+ all_base_ids.get(k) + " as primary_key    from  " + base + "," + details
									+ "  where  " + base + ".id=" + details + ".base_id and base_id="
									+ all_base_ids.get(k);

						stock_qry = "select " + column_names + details + ".id,@txn_fun:='" + txn_fun
								+ "' as txn_function,@txn_id:=" + id_txn_name.get(txn_base_arr.get(b1).toString())
								+ " as txn_id,@primary:=" + all_base_ids.get(k) + " as primary_key    from  " + base
								+ "," + details + "  where  " + base + ".id=" + details + ".base_id and base_id="
								+ all_base_ids.get(k);
						System.out.println("Stock qry===" + qry);
						st_txnname.executeUpdate(qry);
						Statement st_update_loc = con.createStatement();
						ResultSet rs_update = st_update_loc.executeQuery("Select * from tbl_stock_info where txn_id='"
								+ id_txn_name.get(txn_base_arr.get(b1).toString()) + "' and primary_key='"
								+ all_base_ids.get(k) + "'");
						while (rs_update.next()) {
							Statement st_last = con.createStatement();
							Float qty = rs_update.getFloat("qty");
							if (rs_update.equals("MINUS"))
								qty = -qty;

							System.out.println("Qty==" + qty);

							ResultSet rs_cum = st_last.executeQuery(
									"select cum_qty from tbl_stock_info where id=(select max(id) from tbl_stock_info where  location_id='"
											+ rs_update.getString("location_id") + "' and product_id='"
											+ rs_update.getString("product_id") + "' and batch_no='"
											+ rs_update.getString("batch_no") + "' and batch_tracker='"
											+ rs_update.getString("batch_tracker") + "' and cum_qty is not null)");
							Statement st_cum = con.createStatement();
							if (rs_cum.next())
								st_cum.executeUpdate("update tbl_stock_info  set cum_qty=" + rs_cum.getFloat("cum_qty")
										+ "+" + qty + " where id=" + rs_update.getInt("ID"));

							else
								st_cum.executeUpdate("update tbl_stock_info  set cum_qty=" + qty + " where id="
										+ rs_update.getInt("ID"));

							st_cum.executeUpdate("update tbl_stock_info set last_row='FALSE' where location_id='"
									+ rs_update.getString("location_id") + "' and product_id='"
									+ rs_update.getString("product_id") + "' and batch_no='"
									+ rs_update.getString("batch_no") + "' and batch_tracker='"
									+ rs_update.getString("batch_tracker") + "' and id<>'" + rs_update.getInt("ID")
									+ "' ");
						}
						// UpdateOnlineStock(tranxID, con, parentid);
					}
				}

			}

			// out.print("success");
		} catch (Exception ex) {
			// out.print("<error>"+ex.getMessage()+"</error>");
			// out.print("fail");
			ex.printStackTrace();
		} finally {
			// System.out.println("query == "+qry);
			// out.print("</result>");
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established successfully");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}

}
