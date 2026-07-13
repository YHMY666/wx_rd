package edu.fdzc.wx1.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 统计数据响应VO - 返回给前端的月度统计信息
 */
@Data
public class StatisticsVO {

    /**
     * 月度总收入
     */
    private BigDecimal totalIncome;

    /**
     * 月度总支出
     */
    private BigDecimal totalExpense;

    /**
     * 月度结余（总收入 - 总支出）
     */
    private BigDecimal balance;

    /**
     * 各分类统计列表
     * 每个Map包含：category（分类名）、total（该分类总金额）
     * 示例：[{"category": "餐饮", "total": 1200.00}, {"category": "交通", "total": 800.00}]
     */
    private List<Map<String, Object>> categoryStats;
}