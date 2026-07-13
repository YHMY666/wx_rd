package edu.fdzc.wx1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.fdzc.wx1.entity.Record;
import edu.fdzc.wx1.mapper.RecordMapper;
import edu.fdzc.wx1.dto.StatisticsVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 账单记录业务逻辑层 - 处理账单相关的业务操作
 */
@Service
public class RecordService {

    @Resource
    private RecordMapper recordMapper;
    /**
     * 添加账单记录
     * 如果未选择日期，默认使用当天
     */
    public boolean addRecord(Record record) {
        if (record.getRecordDate() == null) {
            record.setRecordDate(LocalDate.now());
        }
        return recordMapper.insert(record) > 0;
    }
    /**
     * 查询某用户指定月份的账单列表
     * 按记录日期和创建时间倒序排列
     */
    public List<Record> listRecords(Long userId, String month) {
        if (month == null || month.isEmpty()) {
            month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        }
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId)
                .apply("DATE_FORMAT(record_date, '%Y-%m') = {0}", month)
                .orderByDesc("record_date", "create_time");
        return recordMapper.selectList(wrapper);
    }
    /**
     * 删除账单记录（需验证用户ID，防止越权删除）
     */
    public boolean deleteRecord(Long recordId, Long userId) {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.eq("id", recordId)
                .eq("user_id", userId);
        return recordMapper.delete(wrapper) > 0;
    }

    /**
     * 获取月度统计数据
     * 包含：总收入、总支出、结余、各分类统计
     */
    public StatisticsVO getStatistics(Long userId, String month) {
        if (month == null || month.isEmpty()) {
            month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        }

        StatisticsVO vo = new StatisticsVO();
        // 查询月收入
        BigDecimal income = recordMapper.sumByUserAndType(userId, 1, month);
        vo.setTotalIncome(income != null ? income : BigDecimal.ZERO);
        // 查询月支出
        BigDecimal expense = recordMapper.sumByUserAndType(userId, 0, month);
        vo.setTotalExpense(expense != null ? expense : BigDecimal.ZERO);
        // 计算结余
        vo.setBalance(vo.getTotalIncome().subtract(vo.getTotalExpense()));
        // 查询支出分类统计
        List<Map<String, Object>> categoryStats = recordMapper.groupByCategory(userId, 0, month);
        vo.setCategoryStats(categoryStats != null ? categoryStats : new ArrayList<>());

        return vo;
    }
}