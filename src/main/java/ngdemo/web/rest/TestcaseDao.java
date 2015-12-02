    package ngdemo.web.rest;  
      
    import java.sql.Connection;  
    import java.sql.DriverManager;  
    import java.sql.PreparedStatement;  
    import java.sql.ResultSet;  
    import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ngdemo.domain.Testcase;  
      
    public class TestcaseDao { 
    	
    	 public static List<Testcase> getTestcases() {          
             boolean status = false;  
             Connection conn = null;  
             PreparedStatement pst = null;  
             ResultSet rs = null;  
             List<Testcase> tc = new ArrayList<Testcase>();
       
             String url = "jdbc:mysql://localhost:3306/";  
             String dbName = "mcellblocks";  
             String driver = "com.mysql.jdbc.Driver";  
             String userName = "root";  
             String password = "password";  
             try {  
                 Class.forName(driver).newInstance();  
                 conn = DriverManager  
                         .getConnection(url + dbName, userName, password);  
       
                 pst = conn  
                         .prepareStatement("select name from testcase_details");  
       
                 rs = pst.executeQuery();  
                 while(rs.next()){
                	 Testcase t = new Testcase();
                	 t.setTestcase(rs.getString("name"));
                	 tc.add(t);
                 }
       
             } catch (Exception e) {  
                 System.out.println(e);  
             } finally {  
                 if (conn != null) {  
                     try {  
                         conn.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (pst != null) {  
                     try {  
                         pst.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (rs != null) {  
                     try {  
                         rs.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
             }  
             return tc;  
         } 
    	 public static int getDevicesCount() {   
    		 int count=0;
             boolean status = false;  
             Connection conn = null;  
             PreparedStatement pst = null;  
             ResultSet rs = null;  
             List<Testcase> tc = new ArrayList<Testcase>();
       
             String url = "jdbc:mysql://localhost:3306/";  
             String dbName = "mcellblocks";  
             String driver = "com.mysql.jdbc.Driver";  
             String userName = "root";  
             String password = "password";  
             try {  
                 Class.forName(driver).newInstance();  
                 conn = DriverManager  
                         .getConnection(url + dbName, userName, password);  
       
                 pst = conn  
                         .prepareStatement("SELECT COUNT(DISTINCT deviceid) FROM testcases");  
       
                 rs = pst.executeQuery();  
                 if (rs.next()) {
                     count= rs.getInt(1);
                     System.out.println("Count= " + count);
                   } 
       
             } catch (Exception e) {  
                 System.out.println(e);  
             } finally {  
                 if (conn != null) {  
                     try {  
                         conn.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (pst != null) {  
                     try {  
                         pst.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (rs != null) {  
                     try {  
                         rs.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
             }  
             return count;  
         }  
    	 
    	 public static int getTestcasesCount() {   
    		 int count=0;
             boolean status = false;  
             Connection conn = null;  
             PreparedStatement pst = null;  
             ResultSet rs = null;  
             List<Testcase> tc = new ArrayList<Testcase>();
       
             String url = "jdbc:mysql://localhost:3306/";  
             String dbName = "mcellblocks";  
             String driver = "com.mysql.jdbc.Driver";  
             String userName = "root";  
             String password = "password";  
             try {  
                 Class.forName(driver).newInstance();  
                 conn = DriverManager  
                         .getConnection(url + dbName, userName, password);  
       
                 pst = conn  
                         .prepareStatement("SELECT COUNT(*) FROM testcases");  
       
                 rs = pst.executeQuery();  
                 if (rs.next()) {
                     count= rs.getInt(1);
                     System.out.println("Count= " + count);
                   } 
       
             } catch (Exception e) {  
                 System.out.println(e);  
             } finally {  
                 if (conn != null) {  
                     try {  
                         conn.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (pst != null) {  
                     try {  
                         pst.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (rs != null) {  
                     try {  
                         rs.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
             }  
             return count;  
         }  
    	 public static int getErrorsCount() {   
    		 int count=0;
             boolean status = false;  
             Connection conn = null;  
             PreparedStatement pst = null;  
             ResultSet rs = null;  
             List<Testcase> tc = new ArrayList<Testcase>();
       
             String url = "jdbc:mysql://localhost:3306/";  
             String dbName = "mcellblocks";  
             String driver = "com.mysql.jdbc.Driver";  
             String userName = "root";  
             String password = "password";  
             try {  
                 Class.forName(driver).newInstance();  
                 conn = DriverManager  
                         .getConnection(url + dbName, userName, password);  
       
                 pst = conn  
                         .prepareStatement(" SELECT COUNT(*) FROM testcases where error!='  '");  
       
                 rs = pst.executeQuery();  
                 if (rs.next()) {
                     count= rs.getInt(1);
                     System.out.println("Count= " + count);
                   } 
       
             } catch (Exception e) {  
                 System.out.println(e);  
             } finally {  
                 if (conn != null) {  
                     try {  
                         conn.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (pst != null) {  
                     try {  
                         pst.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
                 if (rs != null) {  
                     try {  
                         rs.close();  
                     } catch (SQLException e) {  
                         e.printStackTrace();  
                     }  
                 }  
             }  
             return count;  
         }  
        public static boolean validate(String name, String pass) {          
            boolean status = false;  
            Connection conn = null;  
            PreparedStatement pst = null;  
            ResultSet rs = null;  
      
            String url = "jdbc:mysql://localhost:3306/";  
            String dbName = "form";  
            String driver = "com.mysql.jdbc.Driver";  
            String userName = "root";  
            String password = "password";  
            try {  
                Class.forName(driver).newInstance();  
                conn = DriverManager  
                        .getConnection(url + dbName, userName, password);  
      
                pst = conn  
                        .prepareStatement("select * from login where user=? and password=?");  
                pst.setString(1, name);  
                pst.setString(2, pass);  
      
                rs = pst.executeQuery();  
                status = rs.next();  
      
            } catch (Exception e) {  
                System.out.println(e);  
            } finally {  
                if (conn != null) {  
                    try {  
                        conn.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
                if (pst != null) {  
                    try {  
                        pst.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
                if (rs != null) {  
                    try {  
                        rs.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
            return status;  
        }  
        
        public static int insertTestcase(String username, String deviceId, String testcase, String status, String errormsg) {          
            //status = "";  
            Connection conn = null;  
            PreparedStatement pst = null;  
            ResultSet rs = null;  
            int i=0;
      
            String url = "jdbc:mysql://localhost:3306/";  
            String dbName = "mcellblocks";  
            String driver = "com.mysql.jdbc.Driver";  
            String userName = "root";  
            String password = "password";  
            try {  
                Class.forName(driver).newInstance();  
                conn = DriverManager  
                        .getConnection(url + dbName, userName, password);  

        		String insertTableSQL = "INSERT INTO testcases"
        				+ "(userid, deviceid, testcaseid, status, error) VALUES"
        				+ "(?,?,?,?,?)";
                pst = conn  
                        .prepareStatement(insertTableSQL);  
                pst.setString(1, username);  
                pst.setString(2, deviceId);  
                pst.setString(3, testcase);  
                pst.setString(4, status);  
                pst.setString(5, errormsg);  
                i = pst.executeUpdate(); 
                System.out.println("i::::::"+i);
                return i; 
      
            } catch (Exception e) {  
                System.out.println(e);  
            } finally {  
                if (conn != null) {  
                    try {  
                        conn.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
                if (pst != null) {  
                    try {  
                        pst.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
                if (rs != null) {  
                    try {  
                        rs.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
            return i;  
        }  
        
        public static int insert(String name, String pass, String email) {          
            boolean status = false;  
            Connection conn = null;  
            PreparedStatement pst = null;  
            ResultSet rs = null;  
            int i=0;
      
            String url = "jdbc:mysql://localhost:3306/";  
            String dbName = "mcellblocks";  
            String driver = "com.mysql.jdbc.Driver";  
            String userName = "root";  
            String password = "password";  
            try {  
                Class.forName(driver).newInstance();  
                conn = DriverManager  
                        .getConnection(url + dbName, userName, password);  

        		String insertTableSQL = "INSERT INTO login"
        				+ "(userid, username, password, emailaddr) VALUES"
        				+ "(?,?,?,?)";
                pst = conn  
                        .prepareStatement(insertTableSQL);  
                pst.setString(1, name);  
                pst.setString(2, name);  
                pst.setString(3, pass);  
                pst.setString(4, email);  
                i = pst.executeUpdate(); 
                System.out.println("i::::::"+i);
                return i; 
      
            } catch (Exception e) {  
                System.out.println(e);  
            } finally {  
                if (conn != null) {  
                    try {  
                        conn.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
                if (pst != null) {  
                    try {  
                        pst.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
                if (rs != null) {  
                    try {  
                        rs.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
            return i;  
        }  
        
    }  