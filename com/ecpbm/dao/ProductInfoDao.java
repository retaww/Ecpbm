package com.ecpbm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.ecpbm.dao.provider.ProductInfoDynaSqlProvider;
import com.ecpbm.pojo.ProductInfo;

public interface ProductInfoDao {
	//分页获取商品
	@Results({
		@Result(id = true,column = "id",property = "id"),
		@Result(column = "code",property = "code"),
		@Result(column = "pic",property = "pic"),
		@Result(column = "brand",property = "brand"),
		@Result(column = "num",property = "num"),
		@Result(column = "price",property = "price"),
		@Result(column = "intro",property = "intro"),
		@Result(column = "status",property = "status"),
		@Result(column = "tid",property = "type",one = @One(select="com.ecpbm.dao.TypeDao.selectById",fetchType = FetchType.EAGER))
	})
	@SelectProvider(type = ProductInfoDynaSqlProvider.class,method = "selectWithParam")
	List<ProductInfo> selectByPage(Map<String,Object> params);
	
	//根据条件查询商品总数
	@SelectProvider(type = ProductInfoDynaSqlProvider.class,method = "count")
	Integer count(Map<String,Object> params);
	
	//添加商品
	@Insert("insert into product_info(code,name,tid,brand,num,price,intro,status)"+
			"values(#{code},#{name},#{type.id},#{brand},#{num},#{price},#{intro},#{status})")
	@Options(useGeneratedKeys = true,keyColumn = "id")
	void save(ProductInfo pi);
	
	//修改商品
	@Update("update product_info set code=#{code},name=#{name},tid=#{type.id},brand=#{brand},pic=#{pic},"+
			"num=#{num},price=#{price},intro=#{intro},status=#{status} where id=#{id}")
	void edit(ProductInfo pi);
	
	//更新商品状态
	@Update("update product_info set status=#{flag} where id in (${ids})")
	void updateStatus(@Param("ids")String ids,@Param("flag") int flag);
	
	//获取在售商品列表
	@Select("select * from product_info where status=1")
	List<ProductInfo> getonSaleProduct();
	
	//根据商品id获取商品对象
	@Select("select * from product_info where id=#{id}")
	ProductInfo getProductInfoById(int id);
	
}
