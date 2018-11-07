package com.easydressup.util;

import com.easydressup.exception.CreateCategoryException;
import com.easydressup.exception.CreateClothException;
import com.easydressup.exception.CreateUserException;
import com.easydressup.exception.DeleteClothException;
import com.easydressup.exception.FindCategoryException;
import com.easydressup.exception.FindClothsException;
import com.easydressup.exception.FindUserException;
import com.easydressup.model.Category;
import com.easydressup.model.Cloth;
import com.easydressup.model.User;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for database
 *
 * @author
 */
public class DatabaseUtil {

    private static String host = "localhost";
    private static String port = "3306";
    private static String databaseName = "easydressup";
    private static String userName = "root";
    private static String password = "root";
    private static Connection connection = null;

    /**
     * Create the connection.
     *
     */
    private static void createConnection() {
        if(null==connection){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB

            StringBuilder sb = new StringBuilder("jdbc:mysql://");
            sb.append(host);
            sb.append(":");
            sb.append(port);
            sb.append("/");
            sb.append(databaseName);
            sb.append("?user=");
            sb.append(userName);
            sb.append("&password=");
            sb.append(password);
            connection = DriverManager.getConnection(sb.toString());
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        }
    }

    /**
     * Add user
     *
     * @param userName - User name
     * @param password - User password
     * @param firstName - User first name
     * @param lastName - user last name
     * @param role
     * @throws com.easydressup.exception.CreateUserException
     */
    public static void addUser(String userName, String password, String firstName, String lastName, String role) throws CreateUserException {
        createConnection();
        if (null != connection) {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO user(`userName`,`password`,`firstName`,`lastName`,`role`) VALUES (?,?,?,?,?)");
                stmt.setString(1, userName);
                stmt.setString(2, password);
                stmt.setString(3, firstName);
                stmt.setString(4, lastName);
                stmt.setString(5, role);
                stmt.executeUpdate();

            } catch (SQLException ex) {
                throw new CreateUserException("Failed to add the user", ex);
            } finally {
                close();
            }
        } else {
            throw new CreateUserException("Failed to add the user");
        }
    }

    /**
     * Get the user by user name
     *
     * @param userName - User name
     * @return User instance if exist or null
     * @throws com.cloths.servlet.FindUserException
     */
    public static User getUserByUserName(String userName) throws FindUserException {
        User user = null;
        createConnection();
        if (null != connection) {
            try {

                try {
                    PreparedStatement stmt = connection.prepareStatement("select * from user where userName=?");
                    stmt.setString(1, userName);
                    ResultSet rs = stmt.executeQuery();
                    if (null != rs && rs.next()) {
                        user = new User();
                        user.setUserId(rs.getInt(1));
                        user.setUserName(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setFirstName(rs.getString(4));
                        user.setLastName(rs.getString(5));
                        user.setRole(rs.getString(6));
                    }
                } catch (SQLException ex) {
                    throw new FindUserException("Failed to get user", ex);
                }

            } finally {
                close();
            }
        } else {
            throw new FindUserException("Failed to add the user");
        }
        return user;
    }

    public static List<Category> getAllCategory() throws FindCategoryException {
        List<Category> categrories = new ArrayList<>();
        createConnection();
        if (null != connection) {
            try {

                try {

                    PreparedStatement stmt = connection.prepareStatement("select * from  category");
                    ResultSet rs = stmt.executeQuery();
                    if (null != rs) {
                        while (rs.next()) {
                            Category category = new Category(rs.getInt(1), rs.getString(2), getParentCategory(rs.getInt(3)));
                            categrories.add(category);
                        }

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new FindCategoryException("Failed to get category", ex);
                }

            } finally {
                close();
            }
        } else {
            throw new FindCategoryException("Failed to get category");
        }
        return categrories;
    }

    public static Category getParentCategory(int categoryId) throws FindCategoryException {
        Category categrory = null;
        createConnection();
        if (null != connection) {
            try {

                try {
                    PreparedStatement stmt = connection.prepareStatement("select * from  category where categoryId=?");
                    stmt.setInt(1, categoryId);
                    ResultSet rs = stmt.executeQuery();
                    if (null != rs && rs.next()) {
                        categrory = new Category(rs.getInt(1), rs.getString(2), null);
                    }
                } catch (SQLException ex) {
                    throw new FindCategoryException("Failed to get category", ex);
                }

            } finally {
                close();
            }
        } else {
            throw new FindCategoryException("Failed to get category");
        }
        return categrory;
    }

    public static Category getCategoryByName(String name) throws FindCategoryException {
        Category categrory = null;
        createConnection();
        if (null != connection) {
            try {

                try {
                    PreparedStatement stmt = connection.prepareStatement("select * from  category where  categoryName=?");
                    stmt.setString(1, name);
                    ResultSet rs = stmt.executeQuery();
                    if (null != rs && rs.next()) {
                        categrory = new Category(rs.getInt(1), rs.getString(2), getParentCategory(rs.getInt(3)));
                    }
                } catch (SQLException ex) {
                    throw new FindCategoryException("Failed to get category", ex);
                }

            } finally {
                close();
            }
        } else {
            throw new FindCategoryException("Failed to get category");
        }
        return categrory;
    }

    public static void addCategory(String categroyName, int parent) throws CreateCategoryException {
        createConnection();
        if (null != connection) {
            try {

                try {
                    PreparedStatement stmt = connection.prepareStatement("insert into category(categoryName,parentCategory) values(?,?)");
                    stmt.setString(1, categroyName);
                    stmt.setInt(2, parent);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new CreateCategoryException("Failed to add category", ex);
                }

            } finally {
                close();
            }
        } else {
            throw new CreateCategoryException("Failed to add category");
        }
    }

    public static void updateCategory(int categoryId, String categroyName, int parent
    ) throws CreateCategoryException {
        createConnection();
        if (null != connection) {

            try {
                PreparedStatement stmt = connection.prepareStatement("update  categroy set categroyName=?,parentCategory=? where categoryId=?");
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new CreateCategoryException("Failed to add category", ex);
            } finally {
                close();
            }
        } else {
            throw new CreateCategoryException("Failed to add category");
        }
    }

    public static void uploadCloths(int categoryId, InputStream inputStream, int userId, String contenttype, String fileName) throws CreateClothException {
        createConnection();
        if (null != connection) {
            try {
                PreparedStatement stmt = connection.prepareStatement("insert into cloths(image,categoryId,userId,contenttype,fileName)values(?,?,?,?,?)");
                stmt.setBlob(1, inputStream);
                stmt.setInt(2, categoryId);
                stmt.setInt(3, userId);
                stmt.setString(4, contenttype);
                stmt.setString(5, fileName);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new CreateClothException("Failed to update cloths");
            } finally {
                close();
            }
        } else {
            throw new CreateClothException("Failed to upload cloths");
        }
    }

    public static void updateCloths(InputStream inputStream, int clothId, String contenttype, String fileName) throws CreateClothException {
        createConnection();
        if (null != connection) {
            try {
                PreparedStatement stmt = connection.prepareStatement("update cloths set image=?,contenttype=?,fileName=? where clothId=?");
                stmt.setBlob(1, inputStream);
                stmt.setString(2, contenttype);
                stmt.setString(3, fileName);
                stmt.setInt(4, clothId);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new CreateClothException("Failed to update cloths");
            } finally {
                close();
            }
        } else {
            throw new CreateClothException("Failed to upload cloths");
        }
    }

    public static Category getCategoryById(int categoryId) throws FindCategoryException {
        Category categrory = null;
        createConnection();
        if (null != connection) {

            try {
                PreparedStatement stmt = connection.prepareStatement("select * from  category where  categoryId=?");
                stmt.setInt(1, categoryId);
                ResultSet rs = stmt.executeQuery();
                if (null != rs && rs.next()) {
                    categrory = new Category(rs.getInt(1), rs.getString(2), getParentCategory(rs.getInt(3)));
                }
            } catch (SQLException ex) {
                throw new FindCategoryException("Failed to get category", ex);
            } finally {
                close();
            }
        } else {
            throw new FindCategoryException("Failed to get category");
        }
        return categrory;
    }

    public static List<Integer> getClothIdsByCategoryIdAndUserId(int categoryId, int userId) throws FindClothsException {
        createConnection();
        List<Integer> clothIds = new ArrayList<>();
        if (null != connection) {

            try {
                PreparedStatement stmt = connection.prepareStatement("select clothId from  cloths where  categoryId=? and userid=?");
                stmt.setInt(1, categoryId);
                stmt.setInt(2, userId);
                ResultSet rs = stmt.executeQuery();
                if (null != rs) {
                    while (rs.next()) {
                        clothIds.add(rs.getInt(1));
                    }
                }
            } catch (SQLException ex) {
                throw new FindClothsException("Failed to get cloth", ex);
            } finally {
                close();
            }
        } else {
            throw new FindClothsException("Failed to get cloth");
        }
        return clothIds;
    }

    public static Cloth getClothByClothId(int clothId) throws FindClothsException {
        createConnection();
        Cloth cloth = null;
        if (null != connection) {

            try {
                PreparedStatement stmt = connection.prepareStatement("select * from  cloths where  clothId=?");
                stmt.setInt(1, clothId);
                ResultSet rs = stmt.executeQuery();
                if (null != rs && rs.next()) {
                    final Blob blob = rs.getBlob(2);
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    blob.free();
                    String contentType=rs.getString(5);
                    String fileName=rs.getString(6);
                
                    cloth = new Cloth(rs.getInt(1), getCategoryById(rs.getInt(3)), blobAsBytes,contentType ,fileName);
                }
            } catch (SQLException | FindCategoryException ex) {
                throw new FindClothsException("Failed to get cloth", ex);
            } finally {
                close();
            }
        } else {
            throw new FindClothsException("Failed to get cloth");
        }
        return cloth;
    }

    private static void close() {
      /*  try {
            connection.close();
        } catch (SQLException ex) {
        }*/
    }

    /**
     * Remove cloths
     *
     * @param clothId - Cloth id
     * @throws DeleteClothException
     */
    public static void removeClothById(int clothId) throws DeleteClothException {
        createConnection();
        if (null != connection) {

            try {
                PreparedStatement stmt = connection.prepareStatement("delete from  cloths where  clothId=?");
                stmt.setInt(1, clothId);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new DeleteClothException("Failed to delete cloth", ex);
            } finally {
                close();
            }
        } else {
            throw new DeleteClothException("Failed to delete cloth");
        }
    }

    public static void updateUser(String userName, String password, String firstName, String lastName) throws CreateUserException {
        createConnection();
        if (null != connection) {
            try {
                if (password.trim().isEmpty()) {
                    PreparedStatement stmt = connection.prepareStatement("update user set firstName=?,lastName=? where userName =?");
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.setString(3, userName);
                    stmt.executeUpdate();
                } else {
                    PreparedStatement stmt = connection.prepareStatement("update user set firstName=?,lastName=?,password=? where userName =?");
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.setString(3, password);
                    stmt.setString(4, userName);
                    stmt.executeUpdate();
                }

            } catch (SQLException ex) {
                throw new CreateUserException("Failed to update the user", ex);
            } finally {
                close();
            }
        } else {
            throw new CreateUserException("Failed to update the user");
        }
    }
}