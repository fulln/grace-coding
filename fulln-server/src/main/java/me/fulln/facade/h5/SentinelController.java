package me.fulln.facade.h5;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Slf4j
@Api(value = "sentinel 测试接口", tags = "sentinel 测试接口")
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    public static final String RESOURCE_NAME = "flowLimit";

    @ApiOperation(value = "限流接口", notes = "QPS限流接口")
    @GetMapping("/flowLimit")
    public String flowLimit() {
        Entry entry = null;
        try {
            entry = SphU.entry(RESOURCE_NAME);
            return "返回正常的业务逻辑 SUCCESS";
        } catch (BlockException e) {
            log.error("限流了，不能访问，返回限流信息", e);
            return "接口已经被流量限制";
        } finally {
            //SphU 需要与 exit() 成对出现，否则会抛出异常
            if (entry != null) {
                entry.exit();
            }
        }
    }

    @ApiOperation(value = "限流接口关联注解", notes = "限流接口关联注解")
    @GetMapping("/flowLimitRelate/1")
    @SentinelResource(value = "flowLimitRelate1", blockHandler = "flowLimitRelateFallback")
    public String flowLimitRelate1() {
        log.info("flowLimitRelate1 接口被访问");
        return "返回正常的业务逻辑 SUCCESS";
    }

    @ApiOperation(value = "限流接口关联注解", notes = "限流接口关联注解")
    @GetMapping("/flowLimitRelate/2")
    @SentinelResource(value = "flowLimitRelate2", blockHandler = "flowLimitRelateFallback")
    public String flowLimitRelate2() {
        log.info("flowLimitRelate2 接口被访问");
        return "返回正常的业务逻辑 SUCCESS";
    }


    @ApiOperation(value = "限流接口预热", notes = "限流接口预热")
    @GetMapping("/flowWarmUp")
    @SentinelResource(value = "flowWarmUp", blockHandler = "flowWarmUpFallback")
    public String flowWarmUp() {
        log.info("flowWarmUp 接口被访问");
        return "返回正常的业务逻辑 SUCCESS";
    }

    @ApiOperation(value = "限流接口匀速等待", notes = "限流接口匀速等待")
    @GetMapping("/flowBehaviorRate")
    @SentinelResource(value = "flowBehaviorRate", blockHandler = "flowBehaviorRateFallback")
    public String flowBehaviorRate() {
        log.info("flowWarmUp 接口被访问");
        return "返回正常的业务逻辑 SUCCESS";
    }

    public String flowBehaviorRateFallback(BlockException e) {
        log.error("flowBehaviorRateFallback 限流了，不能访问，返回限流信息", e);
        return "接口已经被流量限制";
    }

    public String flowWarmUpFallback(BlockException e) {
        log.error("flowWarmUpFallback 限流了，不能访问，返回限流信息", e);
        return "接口已经被流量限制";
    }

    public String flowLimitRelateFallback(BlockException e) {
        log.error("flowLimitRelate 接口被限流了，不能访问，返回限流信息", e);
        return "接口已经被流量限制";
    }


    @PostConstruct
    public void initFlowRules() {
        // 修改系统规则
        FlowRule flowRule = new FlowRule();
        flowRule.setResource(RESOURCE_NAME);
        flowRule.setCount(2);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setLimitApp("default");
        // 修改关联规则
        FlowRule flowLimitRule = new FlowRule("flowLimitRelate1")
                .setCount(1)
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                .setStrategy(RuleConstant.STRATEGY_RELATE)
                .setRefResource("flowLimitRelate2");

        // warmup 规则
        FlowRule flowWarmUpRule = new FlowRule("flowWarmUp")
                .setCount(20)
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);

        flowLimitRule.setWarmUpPeriodSec(10);
        flowWarmUpRule.setLimitApp("default");

        // 匀速等待规则
        FlowRule lineUp = new FlowRule();
        lineUp.setResource("testLineUp");
        lineUp.setCount(10);
        lineUp.setGrade(RuleConstant.FLOW_GRADE_QPS);
        lineUp.setLimitApp("default");
        lineUp.setMaxQueueingTimeMs(100 * 20);
        lineUp.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);

        // 黑白名单规则
        AuthorityRule authorityRule = new AuthorityRule();
        authorityRule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        authorityRule.setLimitApp("appA,appB");
        authorityRule.setResource("test");
        // 黑白名单校验
        AuthorityRuleManager.loadRules(Collections.singletonList(authorityRule));

        FlowRuleManager.loadRules(List.of(flowRule, flowLimitRule, flowWarmUpRule , lineUp));
    }


}
