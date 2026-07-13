package edu.fdzc.wx1.controller;

import edu.fdzc.wx1.entity.Record;
import edu.fdzc.wx1.dto.RecordAddDTO;
import edu.fdzc.wx1.dto.StatisticsVO;
import edu.fdzc.wx1.service.RecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 账单记录控制器 - 处理账单相关请求
 */
@RestController
@RequestMapping("/api/record")
public class RecordController {

    @Resource
    private RecordService recordService;
    /**
     * 添加账单记录
     * POST /api/record/add
     */
    @PostMapping("/add")
    public Result<Boolean> add(@Valid @RequestBody RecordAddDTO dto) {
        Record record = new Record();
        BeanUtils.copyProperties(dto, record);
        if (dto.getRecordDate() != null) {
            record.setRecordDate(LocalDate.parse(dto.getRecordDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        boolean success = recordService.addRecord(record);
        return success ? Result.success(true) : Result.error("添加失败");
    }
    /**
     * 查询账单列表
     * GET /api/record/list?userId=1&month=2026-07
     */
    @GetMapping("/list")
    public Result<List<Record>> list(@RequestParam Long userId, @RequestParam(required = false) String month) {
        List<Record> list = recordService.listRecords(userId, month);
        return Result.success(list);
    }
    /**
     * 获取月度统计数据
     * GET /api/record/statistics?userId=1&month=2026-07
     */
    @GetMapping("/statistics")
    public Result<StatisticsVO> statistics(@RequestParam Long userId, @RequestParam(required = false) String month) {
        StatisticsVO vo = recordService.getStatistics(userId, month);
        return Result.success(vo);
    }
    /**
     * 删除账单记录
     * DELETE /api/record/delete/{id}?userId=1
     */
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id, @RequestParam Long userId) {
        boolean success = recordService.deleteRecord(id, userId);
        return success ? Result.success(true) : Result.error("删除失败");
    }
}
