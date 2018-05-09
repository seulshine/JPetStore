package com.example.jpetstore.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.jpetstore.dao.*;
import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.dao.ItemDao;
import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.dao.ProductDao;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Coupon;
import com.example.jpetstore.domain.Diary;
import com.example.jpetstore.domain.GoodsItem;
import com.example.jpetstore.domain.GoodsProduct;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Note;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.Tender;

*//**
 * JPetStore primary business object.
 * 
 * <p>This object makes use of five DAO objects, decoupling it
 * from the details of working with persistence APIs. So
 * although this application uses iBATIS for data access,
 * a different persistence tool could be dropped in without
 * breaking this class.
 *
 * <p>The DAOs are made available to the instance of this object
 * using Dependency Injection. (The DAOs are in turn configured using
 * Dependency Injection themselves.) We use Setter Injection here,
 * exposing JavaBean setter methods for each DAO. This means there is
 * a JavaBean property for each DAO. In the present case, the properties
 * are write-only: there are no corresponding getter methods. Getter
 * methods for configuration properties are optional: Implement them
 * only if you want to expose those properties to other business objects.
 *
 * <p>There is one instance of this class in the JPetStore application.
 * In Spring terminology, it is a "singleton", referring to a
 * per-Application Context singleton. The factory creates a single
 * instance; there is no need for a private constructor, static
 * factory method etc as in the traditional implementation of
 * the Singleton Design Pattern. 
 *
 * <p>This is a POJO. It does not depend on any Spring APIs.
 * It's usable outside a Spring container, and can be instantiated
 * using new in a JUnit test. However, we can still apply declarative
 * transaction management to it using Spring AOP.
 *
 * <p>This class defines a default transaction annotation for all methods.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified by Changsup Park
 *//*
@Service
@Transactional
public class PitAPetImpl implements PitAPetFacade { 
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CategoryDao categoryDao; // 동물 카테고리 / 용품 카테고리  (같이 사용)
	@Autowired
	private ProductDao productDao; // 카테고리에 맞는 동물리스트
	
	@Autowired
	private ItemDao itemDao; // 동물 상세페이지

	@Autowired
	private AuctionDao auctionDao; // 동물 경매 
	
	
	@Autowired
	private OrderDao orderDao; // 굿즈 주문
	@Autowired
	private CartDao cartDao; // 상품 카트 (테이블 추가해야함)
	@Autowired
	private GoodsItemDao goodsItemDao; // 굿즈 상세페이지
	@Autowired
	private GoodsProductDao goodsProductDao; // 굿즈 동물별 페이지
	
	@Autowired
	private DiaryDao diaryDao; 
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private NoteDao noteDao;
	
	
	//-------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	//-------------------------------------------------------------------------

	public Account getAccount(String username) {
		return accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) throws DataAccessException {
		accountDao.insertAccount(account);
	}
	public void updateAccount(Account account) throws DataAccessException {
		accountDao.updateAccount(account);
	}
	public void deleteAccount(Account account) throws DataAccessException {
		accountDao.deleteAccount(account);
	}
	public List<String> getUsernameList() throws DataAccessException {
		return accountDao.getUsernameList();
	}
	public List<Category> getCategoryList() throws DataAccessException {
		return categoryDao.getCategoryList();
	}
	public Category getCategory(String categoryId) throws DataAccessException {
		return categoryDao.getCategory(categoryId);
	}
	public List<Product> getProductListByCategory(String CategoryId) throws DataAccessException {
		return productDao.getProductListByCategory(CategoryId);
	}
	public List<Product> searchProductList(String keywords) throws DataAccessException {
		return productDao.searchProductList(keywords);
	}
	public Product getProduct(String ProductId) throws DataAccessException {
		return productDao.getProduct(ProductId);
	}
	
	public boolean isItemInStock(String itemId) throws DataAccessException {
		return itemDao.isItemInStock(itemId);
	}

	public List<Item> getItemListByProduct(String productId) throws DataAccessException {
		return itemDao.getItemListByProduct(productId);
	}

	public Item getItem(String itemId, String username) throws DataAccessException {
		return itemDao.getItem(itemId, username);
	}
	
	public List<Auction> getAuctionsByCategoryId(String categoryId) throws DataAccessException {
		return auctionDao.getAuctionsByCategoryId(categoryId);
	}
	public List<Auction> getAuctionsByProductId(String categoryId, String ProductId) throws DataAccessException {
		return auctionDao.getAuctionsByProductId(categoryId, ProductId);
	}
	public List<Auction> getMadeAuctionsByUsername(String username) throws DataAccessException {
		return auctionDao.getMadeAuctionsByUsername(username);
	}
	public Auction getMadeAuction(int auctionId, String username) throws DataAccessException {
		return auctionDao.getMadeAuction(auctionId, username);
	}
	public void makeAuction(Auction auction) throws DataAccessException {
		auctionDao.makeAuction(auction);
	}
	public void deleteAuction(Auction auction) throws DataAccessException {
		auctionDao.deleteAuction(auction);
	}
	public List<Auction> getTendersByUsername(String username) throws DataAccessException {
		return auctionDao.getTendersByUsername(username);
	}
	public Auction getTenders(int auctionId) throws DataAccessException {
		return auctionDao.getTenders(auctionId);
	}
	public void insertTender(Tender tender) throws DataAccessException {
		auctionDao.insertTender(tender);
	}
	public List<Order> getOrdersByUsername(String username) throws DataAccessException {
		return orderDao.getOrdersByUsername(username);
	}
	public Order getOrder(int orderId) throws DataAccessException {
		return orderDao.getOrder(orderId);
	}
	public void insertOrder(Order order, String username) throws DataAccessException {
		orderDao.insertOrder(order, username);
	}
	public void deleteOrder(Order order) throws DataAccessException {
		orderDao.deleteOrder(order);
	}
	public List<GoodsItem> getGoodsItemByUsername(String username) throws DataAccessException {
		return cartDao.getGoodsItemByUsername(username);
	}
	public void insertGoodsItemToCart(GoodsItem goodsItem, String username) throws DataAccessException {
		cartDao.insertGoodsItemToCart(goodsItem, username);
	}
	public void deleteGoodsItemInCart(GoodsItem goodsItem, String username) throws DataAccessException {
		cartDao.deleteGoodsItemInCart(goodsItem, username);
	}
	public void updateQuantity(Order order) throws DataAccessException {
		goodsItemDao.updateQuantity(order);
	}
	public boolean isGoodsItemInStock(String goodsItemId) throws DataAccessException {
		return goodsItemDao.isGoodsItemInStock(goodsItemId);
	}
	public List<GoodsItem> getGoodsItemListByProduct(String goodsCategoryId) throws DataAccessException {
		return goodsItemDao.getGoodsItemListByProduct(goodsCategoryId);
	}
	public List<GoodsItem> getGoodsItemListByProduct(String goodsCategoryId, String goodsProductId)
			throws DataAccessException {
		return goodsItemDao.getGoodsItemListByProduct(goodsCategoryId, goodsProductId);
	}
	public GoodsItem getyGoodsItem(String goodsItemId) throws DataAccessException {
		return goodsItemDao.getyGoodsItem(goodsItemId);
	}
	public void insertGoodsItem(GoodsItem goodsItem) throws DataAccessException {
		goodsItemDao.insertGoodsItem(goodsItem);
	}
	public void deleteGoodsItem(GoodsItem goodsItem) throws DataAccessException {
		goodsItemDao.deleteGoodsItem(goodsItem);
	}
	public List<GoodsProduct> getGoodsProductListByCategory(String goodsCategoryId) throws DataAccessException {
		return goodsProductDao.getGoodsProductListByCategory(goodsCategoryId);
	}
	public List<GoodsProduct> searchGoodsProductList(String keywords) throws DataAccessException {
		return goodsProductDao.searchGoodsProductList(keywords);
	}
	public GoodsProduct getGoodsProduct(String goodsProductId) throws DataAccessException {
		return goodsProductDao.getGoodsProduct(goodsProductId);
	}
	public Diary getDiary(String username, String petname, String date) throws DataAccessException {
		return diaryDao.getDiary(username, petname, date);
	}
	public void insertDiaryList(String petName, String diaryTitle, String username) throws DataAccessException {
		diaryDao.insertDiaryList(petName, diaryTitle, username);
	}
	public List<Diary> getDiaryList(String username) throws DataAccessException {
		return diaryDao.getDiaryList(username);
	}
	public void insertDiary(String username, String petname) throws DataAccessException {
		diaryDao.insertDiary(username, petname);
	}
	public void updateDiary(Diary diary, String username, String petname) throws DataAccessException {
		diaryDao.updateDiary(diary, username, petname);
	}
	public void deleteDiary(Diary diary, String username, String petname) throws DataAccessException {
		diaryDao.deleteDiary(diary, username, petname);
	}
	public Coupon getCoupon(String username, String couponId) throws DataAccessException {
		return couponDao.getCoupon(username, couponId);
	}
	public void issueCoupon(Coupon coupon, String username) throws DataAccessException {
		couponDao.issueCoupon(coupon, username);
	}
	public void deleteCoupon(String couponId, String username) throws DataAccessException {
		couponDao.deleteCoupon(couponId, username);
	}
	public List<Coupon> getCouponList(String username) throws DataAccessException {
		return couponDao.getCouponList(username);
	}
	public void sendNote(Note note) throws DataAccessException {
		noteDao.sendNote(note);
	}
	public void deleteNote(String noteId, String username) throws DataAccessException {
		noteDao.deleteNote(noteId, username);
	}
	public List<Note> getNoteList(String username) throws DataAccessException {
		return noteDao.getNoteList(username);
	}
	public void selectNote(String noteId, String username) throws DataAccessException {
		noteDao.selectNote(noteId, username);
	}
	public List<Note> searchNoteList(String username) throws DataAccessException {
		return noteDao.searchNoteList(username);
	}

	
	
	
}*/