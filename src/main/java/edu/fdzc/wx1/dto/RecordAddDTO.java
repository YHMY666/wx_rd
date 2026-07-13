package edu.fdzc.wx1.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 添加账单记录DTO - 接收前端添加账单时传递的参数
 */
@Data
public class RecordAddDTO {

    /**
     * 用户ID（从全局数据中获取，标识该账单属于哪个用户）
     */
    @NotNull(message = "userId不能为空")
    private Long userId;

    /**
     * 账单类型：0=支出，1=收入
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 分类名称（如：餐饮、交通、工资等）
     */
    @NotNull(message = "分类不能为空")
    private String category;

    /**
     * 金额（使用BigDecimal保证精度，避免浮点数误差）
     */
    @NotNull(message = "金额不能为空")
    private BigDecimal amount;

    /**
     * 备注（可选）
     */
    private String remark;

    /**
     * 记录日期（格式：YYYY-MM-DD，可选，默认当天）
     */
    private String recordDate;
}