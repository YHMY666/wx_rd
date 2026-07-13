package edu.fdzc.wx1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("record")//指定数据库表名字
/**
 * 账单记录实体类 - 对应数据库 record 表
 */
public class Record {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Integer type;
    private String category;
    private BigDecimal amount;
    private String remark;
    private LocalDate recordDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}