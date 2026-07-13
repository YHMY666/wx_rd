package edu.fdzc.wx1.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fdzc.wx1.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
 * 账单记录数据访问层 - 继承MyBatis-Plus提供的基础CRUD方法
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    /**
     * 按用户、类型、月份统计总金额
     *
     * @param userId 用户ID
     * @param type   0=支出，1=收入
     * @param month  月份，格式：YYYY-MM
     * @return 总金额，无数据返回0
     */
    @Select("SELECT IFNULL(SUM(amount), 0) FROM record " +
            "WHERE user_id = #{userId} AND type = #{type} " +
            "AND DATE_FORMAT(record_date, '%Y-%m') = #{month}")
    BigDecimal sumByUserAndType(Long userId, Integer type, String month);

    /**
     * 按分类分组统计金额（用于排行榜）
     *
     * @param userId 用户ID
     * @param type   0=支出，1=收入
     * @param month  月份，格式：YYYY-MM
     * @return 分类统计列表，每个Map包含category和total
     */
    @Select("SELECT category, SUM(amount) as total FROM record " +
            "WHERE user_id = #{userId} AND type = #{type} " +
            "AND DATE_FORMAT(record_date, '%Y-%m') = #{month} " +
            "GROUP BY category")
    List<Map<String, Object>> groupByCategory(Long userId, Integer type, String month);
}